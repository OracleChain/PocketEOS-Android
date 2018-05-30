package com.oraclechain.pocketeos.modules.friendslist.pelist;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.adapter.AdapterManger;
import com.oraclechain.pocketeos.adapter.baseadapter.wrapper.EmptyWrapper;
import com.oraclechain.pocketeos.base.BaseAcitvity;
import com.oraclechain.pocketeos.bean.PelistBean;
import com.oraclechain.pocketeos.view.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.oraclechain.pocketeos.utils.Utils.getContext;

//PE富豪榜
public class PelistActivity extends BaseAcitvity<PelistView, PelistPresenter> implements PelistView {


    @BindView(R.id.recycle)
    XRecyclerView mRecycle;

    private List<PelistBean.DataBean> mDataBeanList = new ArrayList<>();
    private EmptyWrapper mCommonAdapter;
    private int offset = 0; //下拉加载更多起始页

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pelist;
    }

    @Override
    public PelistPresenter initPresenter() {
        return new PelistPresenter(PelistActivity.this);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        if (getIntent().getStringExtra("type").equals("0")) {
            setCenterTitle(getResources().getString(R.string.pe_list));
        } else {
            setCenterTitle(getResources().getString(R.string.company_list));
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(PelistActivity.this, LinearLayoutManager.VERTICAL, false);
        layoutManager.setSmoothScrollbarEnabled(true);
        mRecycle.setLayoutManager(layoutManager);
        mRecycle.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL, 1, getResources().getColor(R.color.line)));
        mRecycle.setRefreshProgressStyle(ProgressStyle.LineSpinFadeLoader);
        mRecycle.setLoadingMoreProgressStyle(ProgressStyle.CubeTransition);
        mRecycle.setLoadingMoreEnabled(true);
        mRecycle.setPullRefreshEnabled(true);
        mRecycle.setArrowImageView(R.drawable.arrow);
        mRecycle.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mDataBeanList.clear();
                offset = 0;
                presenter.getData(getIntent().getStringExtra("type"), offset);
            }

            @Override
            public void onLoadMore() {
                presenter.getData(getIntent().getStringExtra("type"), offset + mDataBeanList.size());
            }
        });
    }

    @Override
    protected void initData() {
        showProgress();
        presenter.getData(getIntent().getStringExtra("type"), offset);

        mCommonAdapter = new EmptyWrapper(AdapterManger.getPeListAdapter(this, mDataBeanList, getIntent().getStringExtra("type")));
        mCommonAdapter.setEmptyView(R.layout.empty_project);
        mRecycle.setAdapter(mCommonAdapter);
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void getListDataHttp(List<PelistBean.DataBean> pelistBean) {
        hideProgress();
        mRecycle.refreshComplete();
        mRecycle.loadMoreComplete();
        for (int i = 0; i < pelistBean.size(); i++) {
            PelistBean.DataBean itemdata = pelistBean.get(i);
            mDataBeanList.add(itemdata);
        }
        mCommonAdapter.notifyDataSetChanged();
    }

    @Override
    public void getDataHttpFail(String msg) {
        hideProgress();
        mRecycle.refreshComplete();
        mRecycle.loadMoreComplete();
        toast(msg);
    }

}
