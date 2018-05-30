package com.oraclechain.pocketeos.modules.dapp;


import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.liaoinstan.springview.widget.SpringView;
import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.adapter.AdapterManger;
import com.oraclechain.pocketeos.adapter.baseadapter.CommonAdapter;
import com.oraclechain.pocketeos.adapter.baseadapter.wrapper.EmptyWrapper;
import com.oraclechain.pocketeos.app.ActivityUtils;
import com.oraclechain.pocketeos.app.MyApplication;
import com.oraclechain.pocketeos.base.BaseFragment;
import com.oraclechain.pocketeos.bean.DappBean;
import com.oraclechain.pocketeos.bean.DappCommpanyBean;
import com.oraclechain.pocketeos.modules.dapp.dappcommpany.DappCommpanyDetailsActivity;
import com.oraclechain.pocketeos.modules.dapp.paidanswer.paidanswerhome.activity.PaidAnswerActivity;
import com.oraclechain.pocketeos.utils.Utils;
import com.oraclechain.pocketeos.view.AppDefeatHeadView;
import com.oraclechain.pocketeos.view.RecycleViewDivider;
import com.oraclechain.pocketeos.view.convenientbanner.ConvenientBanner;
import com.oraclechain.pocketeos.view.convenientbanner.adapter.LocalImageHolderView;
import com.oraclechain.pocketeos.view.convenientbanner.holder.CBViewHolderCreator;
import com.oraclechain.pocketeos.view.convenientbanner.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 应用
 */
public class DappFragment extends BaseFragment<DappView, DappPresenter> implements DappView {


    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.banner)
    ConvenientBanner mBanner;
    @BindView(R.id.recycle_application)
    RecyclerView mRecycleApplication;
    @BindView(R.id.hot_application_img)
    ImageView mHotApplicationImg;
    @BindView(R.id.hot_application_name)
    TextView mHotApplicationName;
    @BindView(R.id.hot_application_desc)
    TextView mHotApplicationDesc;
    @BindView(R.id.recycle_bussiness_application)
    RecyclerView mRecycleBussinessApplication;
    @BindView(R.id.hot_application)
    RelativeLayout mHotApplication;
    @BindView(R.id.spring)
    SpringView mSpring;


    private List<DappCommpanyBean.DataBean> mHeaderList = new ArrayList<>(); //头部四个icon
    private List<DappBean.DataBean> mBussinessDappList = new ArrayList<>(); //企业应用列表
    private List<String> ivLsit = new ArrayList<>();//banner图片地址

    private CommonAdapter mHeaderAdapter;
    private EmptyWrapper mBussinessDappAdapter;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_application;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        if (Utils.getSpUtils().getString("loginmode").equals("phone")) {
            mImmersionBar.statusBarDarkFont(true, 0.2f).init();
        }else {
            mImmersionBar.statusBarDarkFont(false, 0.2f).init();
        }
        ImmersionBar.setTitleBar(getActivity(), mTitle);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && mImmersionBar != null) {
            if (Utils.getSpUtils().getString("loginmode").equals("phone")) {
                mImmersionBar.statusBarDarkFont(true, 0.2f).init();
            }else {
                mImmersionBar.statusBarDarkFont(false, 0.2f).init();
            }
        }
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        //系统刷新
        mSpring.setHeader(new AppDefeatHeadView(getContext()));
        mSpring.setGive(SpringView.Give.BOTH);
        mSpring.setType(SpringView.Type.FOLLOW);
        mSpring.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                mBussinessDappList.clear();
                mHeaderList.clear();
                ivLsit.clear();
                presenter.getData(); // 获取服务器数据
            }

            @Override
            public void onLoadmore() {
                mSpring.onFinishFreshAndLoad();
            }
        });
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 4);
        layoutManager.setOrientation(GridLayoutManager.VERTICAL);
        layoutManager.setSmoothScrollbarEnabled(true);
        mRecycleApplication.setLayoutManager(layoutManager);
        mHeaderAdapter = AdapterManger.getDappHeadAdapter(getActivity(), mHeaderList);
        mRecycleApplication.setAdapter(mHeaderAdapter);

        GridLayoutManager layoutManager1 = new GridLayoutManager(getActivity(), 2);
        layoutManager1.setOrientation(GridLayoutManager.VERTICAL);
        layoutManager1.setSmoothScrollbarEnabled(true);
        if (Utils.getSpUtils().getString("loginmode").equals("phone")) {
            mRecycleBussinessApplication.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL, 1, getResources().getColor(R.color.line)));
            mRecycleBussinessApplication.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.VERTICAL, 1, getResources().getColor(R.color.line)));
        }else {
            mRecycleBussinessApplication.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL, 1, getResources().getColor(R.color.blackbox_line)));
            mRecycleBussinessApplication.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.VERTICAL, 1, getResources().getColor(R.color.blackbox_line)));
        }
        mRecycleBussinessApplication.setLayoutManager(layoutManager1);
        mBussinessDappAdapter = new EmptyWrapper(AdapterManger.getDappBussnessAdapter(getActivity(), mBussinessDappList));
        mBussinessDappAdapter.setEmptyView(R.layout.empty_project);
        mRecycleBussinessApplication.setAdapter(mBussinessDappAdapter);
    }

    @Override
    protected void initData() {
        presenter.getData(); // 获取服务器数据
    }

    @Override
    public void getDappCommpanyDataHttp(final List<DappCommpanyBean.DataBean> dappCommpanyBean) {

        if (dappCommpanyBean.size() != 0 && dappCommpanyBean.size() > 3) {
            mRecycleApplication.setVisibility(View.VISIBLE);
            for (int i = 0; i < 3; i++) {
                ivLsit.add(dappCommpanyBean.get(i).getPublicImage());
            }
            mBanner.setPages(new CBViewHolderCreator() {
                @Override
                public Object createHolder() {
                    return new LocalImageHolderView();
                }
            }, ivLsit).setPageIndicator(new int[]{
                    R.mipmap.feature_point, R.mipmap.feature_point_cur})//设置小圆点
                    .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
            mBanner.startTurning(3000);//设置停留时间
            mBanner.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("bean", dappCommpanyBean.get(position));
                    ActivityUtils.next(getActivity(), DappCommpanyDetailsActivity.class, bundle);
                }
            });

            for (int i = 3; i < dappCommpanyBean.size(); i++) {
                mHeaderList.add(dappCommpanyBean.get(i));
            }
            mHeaderAdapter.notifyDataSetChanged();

        } else {
            for (int i = 0; i < dappCommpanyBean.size(); i++) {
                ivLsit.add(dappCommpanyBean.get(i).getPublicImage());
            }
            mBanner.setPages(new CBViewHolderCreator() {
                @Override
                public Object createHolder() {
                    return new LocalImageHolderView();
                }
            }, ivLsit).setPageIndicator(new int[]{
                    R.mipmap.feature_point, R.mipmap.feature_point_cur})//设置小圆点
                    .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
            mBanner.startTurning(3000);//设置停留时间
            mBanner.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("bean", dappCommpanyBean.get(position));
                    ActivityUtils.next(getActivity(), DappCommpanyDetailsActivity.class, bundle);
                }
            });
            mRecycleApplication.setVisibility(View.GONE);
        }
    }

    @Override
    public void getDappDataHttp(List<DappBean.DataBean> dappBean) {
        mSpring.onFinishFreshAndLoad();
        if (dappBean.size() != 0) {
            mHotApplicationDesc.setText(dappBean.get(0).getIntroReason());
            mHotApplicationName.setText(dappBean.get(0).getApplyName());
            MyApplication.getInstance().showImage(dappBean.get(0).getApplyIcon(), mHotApplicationImg);
            for (int i = 1; i < dappBean.size(); i++) {
                mBussinessDappList.add(dappBean.get(i));
            }
            mBussinessDappAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getDataHttpFail(String msg) {
        mSpring.onFinishFreshAndLoad();
        toast(msg);
    }

    @Override
    public void initEvent() {

    }

    @Override
    public DappPresenter initPresenter() {
        return new DappPresenter(getActivity());
    }


    @OnClick(R.id.hot_application)
    public void onViewClicked() {
        ActivityUtils.next(getActivity(), PaidAnswerActivity.class);
    }

}
