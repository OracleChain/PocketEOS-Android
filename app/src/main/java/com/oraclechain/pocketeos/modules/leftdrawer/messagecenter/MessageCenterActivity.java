package com.oraclechain.pocketeos.modules.leftdrawer.messagecenter;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.adapter.AdapterManger;
import com.oraclechain.pocketeos.adapter.baseadapter.wrapper.EmptyWrapper;
import com.oraclechain.pocketeos.base.BaseAcitvity;
import com.oraclechain.pocketeos.bean.MessageCenterBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MessageCenterActivity extends BaseAcitvity<MessageCenterView, MessageCenterPresenter> implements MessageCenterView {

    @BindView(R.id.recycle_message)
    XRecyclerView mRecycleMessage;


    private List<MessageCenterBean.DataBean> mDataBeanList = new ArrayList<>();
    private EmptyWrapper mCommonAdapter;
    private int offset = 0; //下拉加载更多起始页

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message_center;
    }

    @Override
    public MessageCenterPresenter initPresenter() {
        return new MessageCenterPresenter(MessageCenterActivity.this);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setCenterTitle(getResources().getString(R.string.message_center));
        LinearLayoutManager layoutManager = new LinearLayoutManager(MessageCenterActivity.this, LinearLayoutManager.VERTICAL, false);
        layoutManager.setSmoothScrollbarEnabled(true);
        mRecycleMessage.setLayoutManager(layoutManager);
        mRecycleMessage.setRefreshProgressStyle(ProgressStyle.LineSpinFadeLoader);
        mRecycleMessage.setLoadingMoreProgressStyle(ProgressStyle.CubeTransition);
        mRecycleMessage.setLoadingMoreEnabled(true);
        mRecycleMessage.setPullRefreshEnabled(true);
        mRecycleMessage.setArrowImageView(R.drawable.arrow);
        mRecycleMessage.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mDataBeanList.clear();
                offset = 0;
                presenter.getData(offset);
            }

            @Override
            public void onLoadMore() {
                presenter.getData(offset + mDataBeanList.size());
            }
        });
    }

    @Override
    protected void initData() {
        showProgress();
        presenter.getData(offset);

        mCommonAdapter = new EmptyWrapper(AdapterManger.getMessageListAdapter(this, mDataBeanList));
        mCommonAdapter.setEmptyView(R.layout.empty_project);
        mRecycleMessage.setAdapter(mCommonAdapter);

    }

    @Override
    public void initEvent() {

    }

    @Override
    public void getListDataHttp(List<MessageCenterBean.DataBean> messageBean) {
        hideProgress();
        mRecycleMessage.refreshComplete();
        mRecycleMessage.loadMoreComplete();
        for (MessageCenterBean.DataBean dataBean : messageBean) {
            mDataBeanList.add(dataBean);
        }
        mCommonAdapter.notifyDataSetChanged();

    }

    @Override
    public void getDataHttpFail(String msg) {
        hideProgress();
        mRecycleMessage.refreshComplete();
        mRecycleMessage.loadMoreComplete();
        toast(msg);
    }

}
