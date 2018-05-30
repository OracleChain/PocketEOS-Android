package com.oraclechain.pocketeos.modules.dapp.paidanswer.paidanswerhome.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.adapter.AdapterManger;
import com.oraclechain.pocketeos.adapter.baseadapter.wrapper.EmptyWrapper;
import com.oraclechain.pocketeos.base.BaseFragment;
import com.oraclechain.pocketeos.bean.PaidAnswerBean;
import com.oraclechain.pocketeos.bean.QuestionListBean;
import com.oraclechain.pocketeos.view.RecycleViewDivider;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 问答
 */
public class PaidAnswerFragment extends BaseFragment<PaidAnswerView, PaidAnswerPresenter> implements PaidAnswerView {
    @BindView(R.id.recyclerView)
    XRecyclerView mRecyclerView;


    private EmptyWrapper mCommonAdapter;
    private List<QuestionListBean> mDataBeanList = new ArrayList<>();

    private int pageNum = 0;
    private String releasedLable = "0";
    private String account = "";

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_paid_answer;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);

        releasedLable = getArguments().getString("releasedLable");
        account = getArguments().getString("account");

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        layoutManager.setSmoothScrollbarEnabled(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL, 1, getResources().getColor(R.color.line)));
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.LineSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.CubeTransition);
        mRecyclerView.setLoadingMoreEnabled(true);
        mRecyclerView.setPullRefreshEnabled(true);
        mRecyclerView.setArrowImageView(R.drawable.arrow);
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mDataBeanList.clear();
                pageNum = 0;
                presenter.getData(pageNum, releasedLable, "-1");
            }

            @Override
            public void onLoadMore() {
                presenter.getData(pageNum, releasedLable, "-1");
            }
        });
    }

    @Override
    protected void initData() {
        presenter.getData(pageNum, releasedLable, "-1");

        mCommonAdapter = new EmptyWrapper(AdapterManger.getPaidAnswerListAdapter(getActivity(), mDataBeanList, account));
        mCommonAdapter.setEmptyView(R.layout.empty_project);
        mRecyclerView.setAdapter(mCommonAdapter);

    }

    @SuppressLint("NewApi")
    @Override
    public void initEvent() {

    }

    @Override
    protected boolean isImmersionBarEnabled() {
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public PaidAnswerPresenter initPresenter() {
        return new PaidAnswerPresenter(getActivity());
    }

    @Override
    public void getQuestionListDataHttp(PaidAnswerBean.DataBeanX paidAnswerBean) {
        mRecyclerView.refreshComplete();
        mRecyclerView.loadMoreComplete();
        pageNum += 1;
        for (int i = 0; i < paidAnswerBean.getData().size(); i++) {
            QuestionListBean questionListBean = new QuestionListBean();
            questionListBean.setId(paidAnswerBean.getData().get(i).getId() + "");
            questionListBean.setName(paidAnswerBean.getData().get(i).getFrom());
            questionListBean.setTime(paidAnswerBean.getData().get(i).getCreatetime() + "");
            questionListBean.setReleasedLable(paidAnswerBean.getData().get(i).getReleasedLable());
            try {
                JSONObject jsonObject = new JSONObject(paidAnswerBean.getData().get(i).getAsktitle().toString());
                String jsonstrtemp = jsonObject.getString("title");
                questionListBean.setTitle(jsonstrtemp);
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            mDataBeanList.add(questionListBean);
        }
        mCommonAdapter.notifyDataSetChanged();
    }

    @Override
    public void getDataHttpFail(String msg) {
        mRecyclerView.refreshComplete();
        mRecyclerView.loadMoreComplete();
        toast(msg);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String getdata) {
        if (getdata.equals("ok")) {
            mDataBeanList.clear();
            pageNum = 0;
            presenter.getData(pageNum, releasedLable, "-1");
        }
    }
}
