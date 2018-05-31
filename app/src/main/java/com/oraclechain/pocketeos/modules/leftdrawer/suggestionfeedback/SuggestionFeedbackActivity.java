package com.oraclechain.pocketeos.modules.leftdrawer.suggestionfeedback;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.utils.OkLogger;
import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.adapter.AdapterManger;
import com.oraclechain.pocketeos.adapter.baseadapter.wrapper.EmptyWrapper;
import com.oraclechain.pocketeos.base.BaseAcitvity;
import com.oraclechain.pocketeos.bean.SuggestionBean;
import com.oraclechain.pocketeos.view.ClearEditText;
import com.oraclechain.pocketeos.view.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.oraclechain.pocketeos.utils.Utils.getContext;

public class SuggestionFeedbackActivity extends BaseAcitvity<SuggestionFeedbackView, SuggestionFeedbackPresenter> implements SuggestionFeedbackView {


    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_right_text)
    TextView mTvRightText;
    @BindView(R.id.suggestion_details)
    ClearEditText mSuggestionDetails;
    @BindView(R.id.recycle_suggertion_history)
    XRecyclerView mRecycleSuggertionHistory;

    private List<SuggestionBean.DataBean> mDataBeans = new ArrayList<>();
    private EmptyWrapper mCommonAdapter;
    private int offset = 0; //下拉加载更多起始页

    private int limit = 140;
    private int editStart;
    private int editEnd;
    private TextWatcher watcher = new TextWatcher() {
        private CharSequence temp;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            OkLogger.d(s.toString());
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            temp = s;
        }

        @Override
        public void afterTextChanged(Editable s) {
            editStart = mSuggestionDetails.getSelectionStart();
            editEnd = mSuggestionDetails.getSelectionEnd();
            if (temp.length() > limit) {
                toast(getResources().getString(R.string.num_limit));
                s.delete(editStart - 1, editEnd);
            }

        }

    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_suggestion_feedback;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setCenterTitle(getString(R.string.suggestion_feedback));
        setRightTitle(getString(R.string.question_commit), true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(SuggestionFeedbackActivity.this, LinearLayoutManager.VERTICAL, false);
        layoutManager.setSmoothScrollbarEnabled(true);
        mRecycleSuggertionHistory.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL, 1, getResources().getColor(R.color.line)));
        mRecycleSuggertionHistory.setLayoutManager(layoutManager);
        mRecycleSuggertionHistory.setRefreshProgressStyle(ProgressStyle.LineSpinFadeLoader);
        mRecycleSuggertionHistory.setLoadingMoreProgressStyle(ProgressStyle.CubeTransition);
        mRecycleSuggertionHistory.setLoadingMoreEnabled(true);
        mRecycleSuggertionHistory.setPullRefreshEnabled(true);
        mRecycleSuggertionHistory.setArrowImageView(R.drawable.arrow);
        mRecycleSuggertionHistory.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mDataBeans.clear();
                offset = 0;
                presenter.getSuggestionList(offset);
            }

            @Override
            public void onLoadMore() {
                presenter.getSuggestionList(offset + mDataBeans.size());
            }
        });
    }

    @Override
    protected void initData() {
        showProgress();
        presenter.getSuggestionList(offset);

        mCommonAdapter = new EmptyWrapper(AdapterManger.getSuggestionListAdapter(this, mDataBeans));
        mCommonAdapter.setEmptyView(R.layout.empty_project);
        mRecycleSuggertionHistory.setAdapter(mCommonAdapter);
    }

    @Override
    public void initEvent() {
        mSuggestionDetails.addTextChangedListener(watcher);
        mTvRightText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(mSuggestionDetails.getText().toString().trim())) {
                    showProgress();
                    presenter.postSuggestionDetails(mSuggestionDetails.getText().toString().trim());
                } else {
                    toast(getString(R.string.toast_input_suggestion));
                }
            }
        });
    }

    @Override
    public SuggestionFeedbackPresenter initPresenter() {
        return new SuggestionFeedbackPresenter(SuggestionFeedbackActivity.this);
    }


    @Override
    public void postSuggestionHttp() {
        hideProgress();
        if (mSuggestionDetails.getText().toString().length() != 0) {
            mSuggestionDetails.setText("");
            mDataBeans.clear();
            offset = 0;
            presenter.getSuggestionList(offset);
        }
    }

    @Override
    public void getSuggestionListHttp(List<SuggestionBean.DataBean> suggestionBean) {
        hideProgress();
        mRecycleSuggertionHistory.refreshComplete();
        mRecycleSuggertionHistory.loadMoreComplete();
        for (SuggestionBean.DataBean data : suggestionBean) {
            mDataBeans.add(data);
        }
        mCommonAdapter.notifyDataSetChanged();
    }

    @Override
    public void getDataHttpFail(String msg) {
        mRecycleSuggertionHistory.refreshComplete();
        mRecycleSuggertionHistory.loadMoreComplete();
        hideProgress();
        toast(msg);
    }
}
