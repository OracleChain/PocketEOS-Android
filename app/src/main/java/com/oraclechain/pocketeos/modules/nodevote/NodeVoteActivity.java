package com.oraclechain.pocketeos.modules.nodevote;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.adapter.AdapterManger;
import com.oraclechain.pocketeos.adapter.baseadapter.wrapper.EmptyWrapper;
import com.oraclechain.pocketeos.app.ActivityUtils;
import com.oraclechain.pocketeos.app.MyApplication;
import com.oraclechain.pocketeos.base.BaseAcitvity;
import com.oraclechain.pocketeos.bean.AccountDetailsBean;
import com.oraclechain.pocketeos.bean.AccountInfoBean;
import com.oraclechain.pocketeos.bean.AccountVoteHistoryBean;
import com.oraclechain.pocketeos.bean.ResultTableRowBean;
import com.oraclechain.pocketeos.modules.nodevote.agencyvote.AgencyVoteActivity;
import com.oraclechain.pocketeos.modules.nodevote.gonodevote.GoNodeVoteActivity;
import com.oraclechain.pocketeos.utils.BigDecimalUtil;
import com.oraclechain.pocketeos.utils.JsonUtil;
import com.oraclechain.pocketeos.utils.RegexUtil;
import com.oraclechain.pocketeos.view.RecycleViewDivider;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class NodeVoteActivity extends BaseAcitvity<NodeVoteView, NodeVotePresenter> implements NodeVoteView {


    TextView mAccount;
    TextView mVoteAccount;
    TextView mAmount;
    @BindView(R.id.go_vote)
    TextView mGoVote;
    @BindView(R.id.go_entrust)
    TextView mGoEntrust;
    @BindView(R.id.vote_details_recycleview)
    XRecyclerView mVoteDetailsRecycleview;
    private OptionsPickerView pvCustomOptions;
    private List<AccountVoteHistoryBean> mAccountVoteHistoryBeans = new ArrayList<>();
    private List<AccountInfoBean> mAccountInfoBeanList = new ArrayList<>();
    private List<String> mStr = new ArrayList<>();
    private String stakedAmount = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_node_vote;
    }

    @Override
    public NodeVotePresenter initPresenter() {
        return new NodeVotePresenter(this);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setCenterTitle(getString(R.string.node_vote));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        layoutManager.setSmoothScrollbarEnabled(true);
        mVoteDetailsRecycleview.setLayoutManager(layoutManager);
        mVoteDetailsRecycleview.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL, 1, getResources().getColor(R.color.white_line)));
        mVoteDetailsRecycleview.setPullRefreshEnabled(false);
        mVoteDetailsRecycleview.setLoadingMoreEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);
        View contentView = mInflater.inflate(R.layout.nodevote_header, null);
        mAccount = (TextView) contentView.findViewById(R.id.account);
        mVoteAccount = (TextView) contentView.findViewById(R.id.vote_amount);
        mAmount = (TextView) contentView.findViewById(R.id.amount);
        mVoteDetailsRecycleview.addHeaderView(contentView);
    }

    @Override
    protected void initData() {
        mAccountInfoBeanList = JsonUtil.parseJsonToArrayList(MyApplication.getInstance().getUserBean().getAccount_info(), AccountInfoBean.class);
        for (int i = 0; i < mAccountInfoBeanList.size(); i++) {
            mStr.add(mAccountInfoBeanList.get(i).getAccount_name());
        }
        mAccount.setText(mStr.get(0));

    }

    @Override
    public void initEvent() {
        mAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pvCustomOptions == null) {
                    pvCustomOptions = new OptionsPickerView.Builder(NodeVoteActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                        @Override
                        public void onOptionsSelect(int options1, int option2, int options3, View v) {
                            String tx = mStr.get(options1);
                            mAccount.setText(tx);
                            showProgress();
                            presenter.getAccountVoteData(mAccount.getText().toString());
                        }
                    })
                            .setTitleBgColor(getResources().getColor(R.color.white))
                            .setSubmitText(getString(R.string.sure))//确定按钮文字
                            .setCancelText(getString(R.string.seach_cancel))//取消按钮文字
                            .setSubmitColor(getResources().getColor(R.color.blue_button))//确定按钮文字颜色
                            .setCancelColor(getResources().getColor(R.color.blue_button))//取消按钮文字颜色
                            .build();
                    pvCustomOptions.setPicker(mStr);//添加数据
                    pvCustomOptions.show();
                } else {
                    pvCustomOptions.show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        showProgress();
        presenter.getAccountVoteData(mAccount.getText().toString());
    }

    @OnClick({R.id.go_vote, R.id.go_entrust})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.go_vote:
                bundle.putString("account", mAccount.getText().toString().trim());
                bundle.putString("amount", mAmount.getText().toString().trim());
                bundle.putString("stakedAmount", stakedAmount);
                ActivityUtils.next(this, GoNodeVoteActivity.class, bundle);
                break;
            case R.id.go_entrust:
                ActivityUtils.next(this, AgencyVoteActivity.class);
                break;
        }
    }

    @Override
    public void getAccountVoteDataHttp(ResultTableRowBean resultTableRowBean) {//获取投票记录
        hideProgress();
        mAccountVoteHistoryBeans.clear();
        if (resultTableRowBean.getCode().equals("0")) {
            if (resultTableRowBean.getData().getProducers().size() != 0) {
                for (int i = 0; i < resultTableRowBean.getData().getProducers().size(); i++) {
                    AccountVoteHistoryBean accountVoteHistoryBean = new AccountVoteHistoryBean();
                    accountVoteHistoryBean.setProducers(resultTableRowBean.getData().getProducers().get(i).getOwner());
                    accountVoteHistoryBean.setNumber(resultTableRowBean.getData().getInfo().getLast_vote_weight());
                    mAccountVoteHistoryBeans.add(accountVoteHistoryBean);
                }
            }
        }
        EmptyWrapper emptyWrapper = new EmptyWrapper(AdapterManger.getAccountVoteAdapter(this, mAccountVoteHistoryBeans));
        emptyWrapper.setEmptyView(R.layout.empty_project);
        mVoteDetailsRecycleview.setAdapter(emptyWrapper);
    }

    @Override
    public void getAccountDetailsDataHttp(AccountDetailsBean accountDetailsBean) {
        mAmount.setText(RegexUtil.subZeroAndDot(accountDetailsBean.getEos_balance()));
        String stake = BigDecimalUtil.add(new BigDecimal(RegexUtil.subZeroAndDot(accountDetailsBean.getEos_cpu_weight())),new BigDecimal(RegexUtil.subZeroAndDot(accountDetailsBean.getEos_net_weight())))+"";
        stakedAmount = RegexUtil.subZeroAndDot(stake);
        mVoteAccount.setText(stakedAmount+ " EOS");
    }

    @Override
    public void getDataHttpFail(String msg) {
        hideProgress();
    }

}
