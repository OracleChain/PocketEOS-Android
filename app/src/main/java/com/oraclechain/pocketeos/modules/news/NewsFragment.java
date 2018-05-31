package com.oraclechain.pocketeos.modules.news;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.adapter.AdapterManger;
import com.oraclechain.pocketeos.adapter.baseadapter.CommonAdapter;
import com.oraclechain.pocketeos.adapter.baseadapter.MultiItemTypeAdapter;
import com.oraclechain.pocketeos.adapter.baseadapter.wrapper.EmptyWrapper;
import com.oraclechain.pocketeos.app.ActivityUtils;
import com.oraclechain.pocketeos.base.BaseFragment;
import com.oraclechain.pocketeos.bean.CoinBean;
import com.oraclechain.pocketeos.bean.NewsBean;
import com.oraclechain.pocketeos.modules.news.newsdetails.WebNewsDetailsActivity;
import com.oraclechain.pocketeos.utils.RotateUtils;
import com.oraclechain.pocketeos.utils.Utils;
import com.oraclechain.pocketeos.view.RecycleViewDivider;
import com.oraclechain.pocketeos.view.convenientbanner.ConvenientBanner;
import com.oraclechain.pocketeos.view.convenientbanner.adapter.LocalImageHolderView;
import com.oraclechain.pocketeos.view.convenientbanner.holder.CBViewHolderCreator;
import com.oraclechain.pocketeos.view.convenientbanner.listener.OnItemClickListener;
import com.oraclechain.pocketeos.view.popupwindow.BasePopupWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 新闻
 */
public class NewsFragment extends BaseFragment<NewsView, NewsPresenter> implements NewsView {


    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.recycle_news)
    XRecyclerView mRecycleNews;

    ConvenientBanner mConvenientBanner;
    RelativeLayout choose_type;
    TextView choose_type_title;
    ImageView mLookMore;

    Boolean isSHow = false;
    BasePopupWindow basePopupWindow;


    private List<CoinBean.DataBean> mCoinBeen = new ArrayList<>();

    private List<NewsBean.DataBean> mNewInfoBeen = new ArrayList<>();
    private EmptyWrapper mEmptyWrapper;
    private List<String> ivLsit = new ArrayList<>();//banner图片地址

    private int offset = 0; //下拉加载更多起始页
    private String coinid = "0"; //选择币种

    @Override
    public NewsPresenter initPresenter() {
        return new NewsPresenter(getActivity());
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        layoutManager.setSmoothScrollbarEnabled(true);
        mRecycleNews.setLayoutManager(layoutManager);

        mRecycleNews.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL, 1, getResources().getColor(R.color.line)));
        mRecycleNews.setRefreshProgressStyle(ProgressStyle.LineSpinFadeLoader);
        mRecycleNews.setLoadingMoreProgressStyle(ProgressStyle.CubeTransition);
        LayoutInflater mInflater = LayoutInflater.from(getActivity());
        View contentView = mInflater.inflate(R.layout.news_header, null);
        mConvenientBanner = (ConvenientBanner) contentView.findViewById(R.id.banner);
        choose_type = (RelativeLayout) contentView.findViewById(R.id.choose_type);
        choose_type_title = (TextView) contentView.findViewById(R.id.choose_type_title);
        mLookMore = (ImageView) contentView.findViewById(R.id.look_more);
        mRecycleNews.addHeaderView(contentView);
        mRecycleNews.setLoadingMoreEnabled(true);
        mRecycleNews.setPullRefreshEnabled(true);
        mRecycleNews.setArrowImageView(R.drawable.arrow);
        mRecycleNews.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mNewInfoBeen.clear();
                ivLsit.clear();
                offset = 0;
                presenter.getNewsData(offset, coinid);
                presenter.getBannersData();
            }

            @Override
            public void onLoadMore() {
                presenter.getNewsData(offset + mNewInfoBeen.size(), coinid);
            }
        });
    }

    @Override
    protected void initData() {
        presenter.getBannersData();
        presenter.getCoinData();
        mEmptyWrapper = new EmptyWrapper(AdapterManger.getNewsAdapter(getActivity(), mNewInfoBeen));
        mEmptyWrapper.setEmptyView(R.layout.empty_project);
        mRecycleNews.setAdapter(mEmptyWrapper);
    }

    @Override
    public void initEvent() {
        choose_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSHow = !isSHow;
                RotateUtils.rotateArrow(mLookMore, isSHow);
                if (basePopupWindow != null && basePopupWindow.isShowing()) {
                    basePopupWindow.dismiss();
                } else {
                    basePopupWindow = new BasePopupWindow.Builder(getActivity()).
                            setView(LayoutInflater.from(getActivity()).inflate(R.layout.popuwindow_exchange_type, null))
                            .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                            .setOutsideTouchable(false)
                            .setAnimationStyle(R.style.AnimDown)
                            .create();
                    basePopupWindow.showAsDropDown(choose_type);
                    isSHow = basePopupWindow.setNewsCoinData(getActivity(), mCoinBeen, coinid, mLookMore, isSHow);
                    CommonAdapter commonAdapter = (CommonAdapter) ((RecyclerView) basePopupWindow.getContentView().findViewById(R.id.exchange_two_type)).getAdapter();
                    commonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                            basePopupWindow.dismiss();
                            choose_type_title.setText(mCoinBeen.get(position).getAssetName());
                            coinid = mCoinBeen.get(position).getId();
                            mNewInfoBeen.clear();
                            presenter.getNewsData(0, mCoinBeen.get(position).getId());
                            isSHow = !isSHow;
                            RotateUtils.rotateArrow(mLookMore, isSHow);
                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                            return false;
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        if (Utils.getSpUtils().getString("loginmode").equals("phone")) {
            mImmersionBar.statusBarDarkFont(true, 0.2f).init();
        } else {
            mImmersionBar.statusBarDarkFont(false, 0.2f).init();
        }
        ImmersionBar.setTitleBar(getActivity(), mTitle);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_news;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && mImmersionBar != null) {
            if (Utils.getSpUtils().getString("loginmode").equals("phone")) {
                mImmersionBar.statusBarDarkFont(true, 0.2f).init();
            } else {
                mImmersionBar.statusBarDarkFont(false, 0.2f).init();
            }
        }
    }

    @Override
    public void getNewsDataHttp(List<NewsBean.DataBean> newsBean) {
        mRecycleNews.loadMoreComplete();
        mRecycleNews.refreshComplete();
        hideProgress();
        for (int i = 0; i < newsBean.size(); i++) {
            NewsBean.DataBean itemdata = newsBean.get(i);
            mNewInfoBeen.add(itemdata);
        }
        mEmptyWrapper.notifyDataSetChanged();
    }

    @Override
    public void getBannerDataHttp(final List<NewsBean.DataBean> newsBean) {
        mRecycleNews.loadMoreComplete();
        mRecycleNews.refreshComplete();
        for (int i = 0; i < newsBean.size(); i++) {
            ivLsit.add(newsBean.get(i).getImageUrl());
        }
        mConvenientBanner.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new LocalImageHolderView();
            }
        }, ivLsit).setPageIndicator(new int[]{
                R.mipmap.feature_point, R.mipmap.feature_point_cur})//设置小圆点
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        mConvenientBanner.startTurning(3000);//设置停留时间
        mConvenientBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putString("details", newsBean.get(position).getNewsUrl());
                ActivityUtils.next(getActivity(), WebNewsDetailsActivity.class, bundle);
            }
        });
    }

    @Override
    public void getCoinTypeDataHttp(List<CoinBean.DataBean> coinBeen) {
        presenter.getNewsData(offset, "0");
        for (CoinBean.DataBean dataBean : coinBeen) {
            mCoinBeen.add(dataBean);
        }
    }

    @Override
    public void getDataHttpFail(String msg) {
        mRecycleNews.loadMoreComplete();
        mRecycleNews.refreshComplete();
        toast(msg);
    }
}
