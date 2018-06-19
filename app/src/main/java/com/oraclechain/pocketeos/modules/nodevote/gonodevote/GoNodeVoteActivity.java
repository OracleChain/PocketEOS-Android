package com.oraclechain.pocketeos.modules.nodevote.gonodevote;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.adapter.baseadapter.CommonAdapter;
import com.oraclechain.pocketeos.adapter.baseadapter.base.ViewHolder;
import com.oraclechain.pocketeos.app.ActivityUtils;
import com.oraclechain.pocketeos.app.MyApplication;
import com.oraclechain.pocketeos.base.BaseAcitvity;
import com.oraclechain.pocketeos.bean.ResultNodeListBean;
import com.oraclechain.pocketeos.modules.leftdrawer.systemsetting.RichTextActivity;
import com.oraclechain.pocketeos.modules.nodevote.NodeVoteDetailsActivity;
import com.oraclechain.pocketeos.modules.nodevote.surevote.SureNodeVoteActivity;
import com.oraclechain.pocketeos.utils.BigDecimalUtil;
import com.oraclechain.pocketeos.utils.FilesUtils;
import com.oraclechain.pocketeos.view.RecycleViewDivider;
import com.oraclechain.pocketeos.view.RoundImageView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * The type Go node vote activity.
 * 获取参投的投票列表
 */
public class GoNodeVoteActivity extends BaseAcitvity<GoNodeVoteView, GoNodeVotePresenter> implements GoNodeVoteView {


    @BindView(R.id.tv_right_text)
    TextView mTvRightText;
    @BindView(R.id.node_list_recycleview)
    XRecyclerView mNodeListRecycleview;
    @BindView(R.id.choose_node_number)
    TextView mChooseNodeNumber;
    @BindView(R.id.go_node_vote)
    LinearLayout mGoNodeVote;

    private List<ResultNodeListBean.DataBeanX.DataBean> mRowsBeans = new ArrayList<>();//获取节点列表
    private int page = 0; //默认从0开始
    private CommonAdapter mCommonAdapter;
    private ArrayList<ResultNodeListBean.DataBeanX.DataBean> mSelectNode = new ArrayList<>(); //记录选中的item

    @Override
    protected int getLayoutId() {
        return R.layout.activity_go_node_vote;
    }

    @Override
    public GoNodeVotePresenter initPresenter() {
        return new GoNodeVotePresenter(this);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setCenterTitle(getString(R.string.node_vote));
        setRightTitle(getString(R.string.vote_rule), true);
        mTvRightText.setTextColor(getResources().getColor(R.color.white));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        layoutManager.setSmoothScrollbarEnabled(true);
        mNodeListRecycleview.setLayoutManager(layoutManager);
        mNodeListRecycleview.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL, 1, getResources().getColor(R.color.white_line)));
        mNodeListRecycleview.setPullRefreshEnabled(true);
        mNodeListRecycleview.setLoadingMoreEnabled(true);
        LayoutInflater mInflater = LayoutInflater.from(this);
        View emptyView = mInflater.inflate(R.layout.empty_project, null);
        mNodeListRecycleview.setEmptyView(emptyView);
        mNodeListRecycleview.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mNodeListRecycleview.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate);
        mNodeListRecycleview.setArrowImageView(R.mipmap.whitearrow);
        TextView textView = mNodeListRecycleview.getDefaultRefreshHeaderView().findViewById(R.id.refresh_status_textview);
        textView.setTextColor(getResources().getColor(R.color.white));
        mNodeListRecycleview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mRowsBeans.clear();
                mSelectNode.clear();
                page = 0;//从头开始
                presenter.getNodeListData(page);
            }

            @Override
            public void onLoadMore() {
                mCommonAdapter.notifyDataSetChanged();
                presenter.getNodeListData(page);
            }
        });
    }

    @Override
    protected void initData() {
        showProgress();

        presenter.getNodeListData(page);
        mCommonAdapter = new CommonAdapter<ResultNodeListBean.DataBeanX.DataBean>(this, R.layout.item_choose_node, mRowsBeans) {
            @Override
            protected void convert(final ViewHolder holder, ResultNodeListBean.DataBeanX.DataBean item, int position) {
                try {
                    if (item != null) {
                        holder.setText(R.id.node_name, item.getOwner());
                        RoundImageView roundImageView = holder.getConvertView().findViewById(R.id.node_img);
                        if (item.getProducerUrlInfo() != null) {
                            if (item.getProducerUrlInfo().getOrg() != null) {
                                if (item.getProducerUrlInfo().getOrg().getBranding() != null) {
                                    MyApplication.getInstance().showImage(item.getProducerUrlInfo().getOrg().getBranding().getLogo_256(), roundImageView);
                                }
                            }
                        } else {
                            roundImageView.setImageDrawable(getResources().getDrawable(R.mipmap.ic_launcher_round));
                        }
                        roundImageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Bundle bundle = new Bundle();
                                bundle.putString("title", item.getOwner());
                                bundle.putString("url", item.getUrl());
                                ActivityUtils.next(GoNodeVoteActivity.this, NodeVoteDetailsActivity.class, bundle);
                            }
                        });
                        String str = item.getTotal_votes().substring(0, item.getTotal_votes().indexOf("."));
                        String number = "";
                        if (str.equals("0")) {
                            number = "0";
                        } else {
                            number = BigDecimalUtil.divide(new BigDecimal(str.substring(0, str.length() - 4)), new BigDecimal(100000000), 2) + "";
                        }
                        holder.setText(R.id.node_total_number, number + getString(R.string.million_tickets));
                        CheckBox checkBox = holder.getView(R.id.choose_node);
                        checkBox.setOnCheckedChangeListener(null);
                        checkBox.setChecked(item.getSelect());
                        holder.getConvertView().setOnClickListener(new View.OnClickListener() {//判断item是否被点击
                            @Override
                            public void onClick(View v) {//遍历进行筛选是否
                                if (item.getSelect()) {
                                    checkBox.setChecked(false);
                                    mSelectNode.remove(mSelectNode.indexOf(item));
                                    item.setSelect(false);
                                    mChooseNodeNumber.setText(getString(R.string.be_choosed) + mSelectNode.size() + getString(R.string.other_choosed) + (30 - mSelectNode.size()) + getString(R.string.ge));
                                } else {
                                    item.setSelect(true);
                                    checkBox.setChecked(true);
                                    mSelectNode.add(item);
                                    mChooseNodeNumber.setText(getString(R.string.be_choosed) + mSelectNode.size() + getString(R.string.other_choosed) + (30 - mSelectNode.size()) + getString(R.string.ge));
                                }
                            }
                        });

                    }
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                }
            }
        };
        mNodeListRecycleview.setAdapter(mCommonAdapter);
    }

    @Override
    public void initEvent() {

    }

    @OnClick({R.id.tv_right_text, R.id.go_node_vote})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.tv_right_text:
                bundle.putString("details", FilesUtils.readAssetsTxt(GoNodeVoteActivity.this, "node_vote_info"));
                bundle.putString("title", getString(R.string.vote_rule));
                ActivityUtils.next(GoNodeVoteActivity.this, RichTextActivity.class, bundle);
                break;
            case R.id.go_node_vote:
                if (mSelectNode.size() > 0) {
                    bundle.putParcelableArrayList("select", mSelectNode);
                    bundle.putString("account", getIntent().getStringExtra("account"));
                    bundle.putString("amount", getIntent().getStringExtra("amount"));
                    bundle.putString("stakedAmount", getIntent().getStringExtra("stakedAmount"));
                    ActivityUtils.next(GoNodeVoteActivity.this, SureNodeVoteActivity.class, bundle);
                } else {
                    toast(getString(R.string.choose_node_vote_toast));
                }
                break;
        }
    }

    @Override
    public void getNodeListDataHttp(ResultNodeListBean resultNodeListBean) {
        hideProgress();
        mNodeListRecycleview.loadMoreComplete();
        mNodeListRecycleview.refreshComplete();
        if (mRowsBeans.size() == 0) {
            mChooseNodeNumber.setText(R.string.default_choose_toast);
        }
        for (ResultNodeListBean.DataBeanX.DataBean rowsBean : resultNodeListBean.getData().getData()) {
            mRowsBeans.add(rowsBean);
        }
        mCommonAdapter.notifyDataSetChanged();
        page += 1;
        if (resultNodeListBean.getData().getCurrentPage() == resultNodeListBean.getData().getTotalPages()) {
            mNodeListRecycleview.setLoadingMoreEnabled(false);
        } else {
            mNodeListRecycleview.setLoadingMoreEnabled(true);
        }
    }

    @Override
    public void getDataHttpFail(String msg) {
        hideProgress();
        mNodeListRecycleview.loadMoreComplete();
        mNodeListRecycleview.refreshComplete();
    }
}
