package com.oraclechain.pocketeos.modules.dapp.dappcommpany;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.adapter.AdapterManger;
import com.oraclechain.pocketeos.adapter.baseadapter.wrapper.EmptyWrapper;
import com.oraclechain.pocketeos.app.MyApplication;
import com.oraclechain.pocketeos.base.BaseAcitvity;
import com.oraclechain.pocketeos.bean.DappBean;
import com.oraclechain.pocketeos.bean.DappCommpanyBean;
import com.oraclechain.pocketeos.utils.Utils;
import com.oraclechain.pocketeos.view.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.oraclechain.pocketeos.utils.Utils.getContext;

public class DappCommpanyDetailsActivity extends BaseAcitvity<DappCommpanyDetailsView, DappCommpanyDetailsPresenter> implements DappCommpanyDetailsView {


    @BindView(R.id.commpany_img)
    ImageView mCommpanyImg;
    @BindView(R.id.hot_application_img)
    ImageView mHotApplicationImg;
    @BindView(R.id.hot_application_name)
    TextView mHotApplicationName;
    @BindView(R.id.hot_application_desc)
    TextView mHotApplicationDesc;
    @BindView(R.id.hot_application)
    RelativeLayout mHotApplication;
    @BindView(R.id.recycle_bussiness_application)
    RecyclerView mRecycleBussinessApplication;
    @BindView(R.id.commpany_desc)
    TextView mCommpanyDesc;

    private List<DappBean.DataBean> mBussinessDappList = new ArrayList<>(); //企业应用列表
    private EmptyWrapper mBussinessDappAdapter;
    private DappCommpanyBean.DataBean commpany;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dapp_commpany_details;
    }


    @Override
    protected void initViews(Bundle savedInstanceState) {
        commpany = getIntent().getParcelableExtra("bean");
        setCenterTitle(commpany.getEnterpriseName());
        MyApplication.getInstance().showImage(commpany.getPublicImage(), mCommpanyImg);
        mCommpanyDesc.setText(commpany.getSummary());

        GridLayoutManager layoutManager1 = new GridLayoutManager(this, 2);
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
        mBussinessDappAdapter = new EmptyWrapper(AdapterManger.getDappBussnessAdapter(this, mBussinessDappList));
        mBussinessDappAdapter.setEmptyView(R.layout.empty_project);
        mRecycleBussinessApplication.setAdapter(mBussinessDappAdapter);
    }

    @Override
    protected void initData() {
        showProgress();
        presenter.getData(commpany.getId());
    }

    @Override
    public void initEvent() {

    }

    @Override
    public DappCommpanyDetailsPresenter initPresenter() {
        return new DappCommpanyDetailsPresenter(DappCommpanyDetailsActivity.this);
    }


    @Override
    public void getDappCommpanyDataHttp(List<DappBean.DataBean> dappBean) {
        hideProgress();
        if (dappBean.size() != 0) {
            mHotApplicationDesc.setText(dappBean.get(0).getApplyDetails());
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
        hideProgress();
        toast(msg);
    }

    @OnClick(R.id.hot_application)
    public void onViewClicked() {
    }

}
