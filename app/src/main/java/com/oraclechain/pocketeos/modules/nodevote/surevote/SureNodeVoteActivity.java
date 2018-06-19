package com.oraclechain.pocketeos.modules.nodevote.surevote;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.adapter.AdapterManger;
import com.oraclechain.pocketeos.adapter.baseadapter.CommonAdapter;
import com.oraclechain.pocketeos.app.ActivityUtils;
import com.oraclechain.pocketeos.app.AppManager;
import com.oraclechain.pocketeos.app.MyApplication;
import com.oraclechain.pocketeos.base.BaseAcitvity;
import com.oraclechain.pocketeos.bean.AccountVoteHistoryBean;
import com.oraclechain.pocketeos.bean.DelegatebwMessageBean;
import com.oraclechain.pocketeos.bean.RegproxyMessageBean;
import com.oraclechain.pocketeos.bean.ResponseBean;
import com.oraclechain.pocketeos.bean.ResultNodeListBean;
import com.oraclechain.pocketeos.bean.ResultTableRowBean;
import com.oraclechain.pocketeos.bean.ResultVoteWeightBean;
import com.oraclechain.pocketeos.bean.VoteproducerMessageBean;
import com.oraclechain.pocketeos.blockchain.PushDatamanger;
import com.oraclechain.pocketeos.modules.leftdrawer.systemsetting.RichTextActivity;
import com.oraclechain.pocketeos.modules.nodevote.gonodevote.GoNodeVoteActivity;
import com.oraclechain.pocketeos.utils.BigDecimalUtil;
import com.oraclechain.pocketeos.utils.FilesUtils;
import com.oraclechain.pocketeos.utils.KeyBoardUtil;
import com.oraclechain.pocketeos.utils.PasswordToKeyUtils;
import com.oraclechain.pocketeos.utils.StringUtils;
import com.oraclechain.pocketeos.utils.Utils;
import com.oraclechain.pocketeos.view.RecycleViewDivider;
import com.oraclechain.pocketeos.view.dialog.passworddialog.PasswordCallback;
import com.oraclechain.pocketeos.view.dialog.passworddialog.PasswordDialog;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * The type Sure vote activity.
 */
public class SureNodeVoteActivity extends BaseAcitvity<SureNodeVoteView, SureNodeVotePresenter> implements SureNodeVoteView {

    SeekBar mMseekbar;
    EditText mVoteEosAmount;
    TextView mInputNumber;
    TextView vote_amount;
    TextView mBelocked;
    @BindView(R.id.vote_details_recycleview)
    XRecyclerView mVoteDetailsRecycleview;
    @BindView(R.id.sure_vote)
    TextView mSureVote;
    String eosAmount, stakeAmount;
    String voteWeight = "0";
    String mpassword = "";
    private ArrayList<ResultNodeListBean.DataBeanX.DataBean> mSelectNode = new ArrayList<>();
    private List<AccountVoteHistoryBean> mAccountVoteHistoryBeans = new ArrayList<>();
    private CommonAdapter mCommonAdapter;
    private String account;
    private ResultTableRowBean mResultTableRowBean = new ResultTableRowBean();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sure_vote;
    }

    @Override
    public SureNodeVotePresenter initPresenter() {
        return new SureNodeVotePresenter(this);
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
        View contentView = mInflater.inflate(R.layout.sure_nodevote_header, null);
        mMseekbar = contentView.findViewById(R.id.mseekbar);
        mMseekbar.setEnabled(true);
        mMseekbar.setFocusable(true);
        mMseekbar.setFocusableInTouchMode(true);
        mVoteEosAmount = contentView.findViewById(R.id.vote_eos_amount);
        mInputNumber = contentView.findViewById(R.id.input_number);
        mBelocked = contentView.findViewById(R.id.be_locked);
        vote_amount = contentView.findViewById(R.id.vote_amount);
        mVoteDetailsRecycleview.addHeaderView(contentView);
        eosAmount = getIntent().getStringExtra("amount");
        stakeAmount = getIntent().getStringExtra("stakedAmount");


        mVoteEosAmount.setText("0");
        vote_amount.setText(stakeAmount+" EOS");
    }

    @Override
    protected void initData() {
        account = getIntent().getStringExtra("account");

        presenter.getNowVoteWeightData();

        presenter.getAccountVoteData(account);

        mSelectNode = getIntent().getParcelableArrayListExtra("select");

        mCommonAdapter = AdapterManger.getAccountVoteAdapter(this, mAccountVoteHistoryBeans);
        mVoteDetailsRecycleview.setAdapter(mCommonAdapter);
    }

    @Override
    public void initEvent() {

        mMseekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mVoteEosAmount.setFocusable(false);
                mVoteEosAmount.setFocusableInTouchMode(false);
                mVoteEosAmount.setClickable(false);
                if (KeyBoardUtil.isSoftInputShow(SureNodeVoteActivity.this)) {
                    KeyBoardUtil.getInstance(SureNodeVoteActivity.this).hide();
                }
                BigDecimal bigDecimal = BigDecimalUtil.multiply(BigDecimalUtil.divide(new BigDecimal(progress), new BigDecimal(100), 2), new BigDecimal(eosAmount), 0);
                mVoteEosAmount.setText(bigDecimal + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                for (int i = 0; i < mAccountVoteHistoryBeans.size(); i++) {
                    mAccountVoteHistoryBeans.get(i).setNumber(BigDecimalUtil.multiply(BigDecimalUtil.multiply(BigDecimalUtil.add(new BigDecimal(mVoteEosAmount.getText().toString()), new BigDecimal(stakeAmount), 4), new BigDecimal("10000"), 4), new BigDecimal(voteWeight), 4) + "");
                }
                mCommonAdapter.notifyDataSetChanged();
            }
        });
        mInputNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVoteEosAmount.setFocusable(true);
                mVoteEosAmount.setFocusableInTouchMode(true);
                mVoteEosAmount.setClickable(true);
                mVoteEosAmount.requestFocus();
                mVoteEosAmount.setSelection(mVoteEosAmount.getText().toString().length());//将光标移至文字末尾
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    public void run() {
                        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                }, 200); // 0.2秒后自动弹出软键盘
            }
        });

//        mVoteEosAmount.addTextChangedListener(new NodeVoteTextWatcher(mVoteEosAmount, mMseekbar, "10"));


        mBelocked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("details", FilesUtils.readAssetsTxt(SureNodeVoteActivity.this, "belocked_info"));
                bundle.putString("title", "锁定说明");
                ActivityUtils.next(SureNodeVoteActivity.this, RichTextActivity.class, bundle);
            }
        });
    }

    @OnClick(R.id.sure_vote)
    public void onViewClicked() {
        eosAmount = mVoteEosAmount.getText().toString().trim();
        if (!mResultTableRowBean.getCode().equals("0") & mVoteEosAmount.getText().toString().trim().equals("0") && !BigDecimalUtil.greaterThan(new BigDecimal(stakeAmount), new BigDecimal(0))) {
            toast(getString(R.string.eos_zero_toast));
            return;
        }
        PasswordDialog mPasswordDialog = new PasswordDialog(SureNodeVoteActivity.this, new PasswordCallback() {
            @Override
            public void sure(final String password) {
                if (MyApplication.getInstance().getUserBean().getWallet_shapwd().equals(PasswordToKeyUtils.shaCheck(password))) {
                    showProgress();
                    mpassword = password;
                    DelegatebwMessageBean delegatebwMessageBean = new DelegatebwMessageBean();
                    delegatebwMessageBean.setFrom(account);
                    delegatebwMessageBean.setReceiver(account);
                    BigDecimal bigIntegerAmount = BigDecimalUtil.multiply(BigDecimalUtil.toBigDecimal(mVoteEosAmount.getText().toString().trim()), new BigDecimal(1), 4);
                    delegatebwMessageBean.setStake_cpu_quantity(StringUtils.addZero(BigDecimalUtil.divide(bigIntegerAmount, new BigDecimal(2), 4) + "") + " EOS");
                    delegatebwMessageBean.setStake_net_quantity(StringUtils.addZero(BigDecimalUtil.divide(bigIntegerAmount, new BigDecimal(2), 4) + "") + " EOS");

                    VoteproducerMessageBean voteproducerMessageBean = new VoteproducerMessageBean();
                    voteproducerMessageBean.setVoter(account);
                    String[] producers = new String[mAccountVoteHistoryBeans.size()];
                    for (int i = 0; i < mAccountVoteHistoryBeans.size(); i++) {
                        producers[i] = mAccountVoteHistoryBeans.get(i).getProducers();
                    }
                    voteproducerMessageBean.setProducers(Arrays.asList(StringUtils.stringSort(producers)));
                    voteproducerMessageBean.setProxy("");

                    if (!mResultTableRowBean.getCode().equals("0") && BigDecimalUtil.greaterThan(new BigDecimal(eosAmount), new BigDecimal(0))) {//未注册到投票系统 只要余额大于0都要进行质押 投票系统 质押 投票
                        new PushDatamanger(SureNodeVoteActivity.this, password, new PushDatamanger.Callback() {
                            @Override
                            public void getResult(String result) {
                                new PushDatamanger(SureNodeVoteActivity.this, password, new PushDatamanger.Callback() {
                                    @Override
                                    public void getResult(String result) {

                                        if (result.contains("transaction_id")) {
                                            new PushDatamanger(SureNodeVoteActivity.this, password, new PushDatamanger.Callback() {
                                                @Override
                                                public void getResult(String result) {
                                                    if (result.contains("transaction_id")) {
                                                        if (Utils.getSpUtils().getString("loginmode", "").equals("phone")) {
                                                            presenter.getcomplete_taskData();//通知完成投票
                                                        }else {
                                                            toast(getString(R.string.node_vote_success));
                                                            finish();
                                                            AppManager.getAppManager().finishActivity(GoNodeVoteActivity.class);
                                                        }
                                                    }
                                                }
                                            }).pushAction("eosio", "voteproducer",
                                                    new Gson().toJson(voteproducerMessageBean), account);
                                        }
                                    }
                                }).pushAction("eosio", "delegatebw",
                                        new Gson().toJson(delegatebwMessageBean), account);
                            }
                        }).pushAction("eosio", "regproxy", new Gson().toJson(new RegproxyMessageBean(account, "1")), account);
                    } else if (!mResultTableRowBean.getCode().equals("0") && BigDecimalUtil.greaterThan(new BigDecimal(stakeAmount), new BigDecimal(0)) && !BigDecimalUtil.greaterThan(new BigDecimal(eosAmount), new BigDecimal(0))) {//未注册到投票系统 质押资产！=0 余额=0 先注册到投票系统再进行投票
                        new PushDatamanger(SureNodeVoteActivity.this, password, new PushDatamanger.Callback() {
                            @Override
                            public void getResult(String result) {
                                if (result.contains("transaction_id")) {
                                    new PushDatamanger(SureNodeVoteActivity.this, password, new PushDatamanger.Callback() {
                                        @Override
                                        public void getResult(String result) {
                                            if (result.contains("transaction_id")) {
                                                if (Utils.getSpUtils().getString("loginmode", "").equals("phone")) {
                                                    presenter.getcomplete_taskData();//通知完成投票
                                                }else {
                                                    toast(getString(R.string.node_vote_success));
                                                    finish();
                                                    AppManager.getAppManager().finishActivity(GoNodeVoteActivity.class);
                                                }
                                            }
                                        }
                                    }).pushAction("eosio", "voteproducer",
                                            new Gson().toJson(voteproducerMessageBean), account);
                                }
                            }
                        }).pushAction("eosio", "regproxy", new Gson().toJson(new RegproxyMessageBean(account, "1")), account);
                    } else if (!BigDecimalUtil.greaterThan(new BigDecimal(stakeAmount), new BigDecimal(0)) && !BigDecimalUtil.greaterThan(new BigDecimal(eosAmount), new BigDecimal(0))) {//余额为0 ，质押为0
                        toast(getString(R.string.eos_zero_toast));
                    } else if (mResultTableRowBean.getCode().equals("0") && !BigDecimalUtil.greaterThan(new BigDecimal(eosAmount), new BigDecimal(0)) && BigDecimalUtil.greaterThan(new BigDecimal(stakeAmount), new BigDecimal(0))) {//检查余额为小于等于0，质押大于0，不用质押直接投票,
                        new PushDatamanger(SureNodeVoteActivity.this, password, new PushDatamanger.Callback() {
                            @Override
                            public void getResult(String result) {
                                if (result.contains("transaction_id")) {
                                    if (Utils.getSpUtils().getString("loginmode", "").equals("phone")) {
                                        presenter.getcomplete_taskData();//通知完成投票
                                    }else
                                    toast(getString(R.string.node_vote_success));
                                    finish();
                                    AppManager.getAppManager().finishActivity(GoNodeVoteActivity.class);
                                }
                            }
                        }).pushAction("eosio", "voteproducer",
                                new Gson().toJson(voteproducerMessageBean), account);
                    } else if (mResultTableRowBean.getCode().equals("0") && BigDecimalUtil.greaterThan(new BigDecimal(eosAmount), new BigDecimal(0))) {//账户余额大于0，质押还能进行投票进行~
                        new PushDatamanger(SureNodeVoteActivity.this, password, new PushDatamanger.Callback() {
                            @Override
                            public void getResult(String result) {

                                if (result.contains("transaction_id")) {
                                    new PushDatamanger(SureNodeVoteActivity.this, password, new PushDatamanger.Callback() {
                                        @Override
                                        public void getResult(String result) {
                                            if (result.contains("transaction_id")) {
                                                if (Utils.getSpUtils().getString("loginmode", "").equals("phone")) {
                                                    presenter.getcomplete_taskData();//通知完成投票
                                                }else {
                                                    toast(getString(R.string.node_vote_success));
                                                    finish();
                                                    AppManager.getAppManager().finishActivity(GoNodeVoteActivity.class);
                                                }
                                            }
                                        }
                                    }).pushAction("eosio", "voteproducer",
                                            new Gson().toJson(voteproducerMessageBean), account);
                                }
                            }
                        }).pushAction("eosio", "delegatebw",
                                new Gson().toJson(delegatebwMessageBean), account);
                    }
                } else {
                    toast(getResources().getString(R.string.password_error));
                }
            }

            @Override
            public void cancle() {
            }
        });
        mPasswordDialog.setCancelable(true);
        mPasswordDialog.show();

    }


    @Override
    public void getNowVoteWeightDataHttp(ResultVoteWeightBean resultVoteWeightBean) {
        if (resultVoteWeightBean.getCode().equals("0")) {
            voteWeight = resultVoteWeightBean.getData();
            for (ResultNodeListBean.DataBeanX.DataBean rowsBean : mSelectNode) {
                AccountVoteHistoryBean accountVoteHistoryBean = new AccountVoteHistoryBean();
                accountVoteHistoryBean.setProducers(rowsBean.getOwner());
                accountVoteHistoryBean.setNumber(BigDecimalUtil.multiply(new BigDecimal(Double.parseDouble(stakeAmount) * 10000), new BigDecimal(voteWeight), 4) + "");
                mAccountVoteHistoryBeans.add(accountVoteHistoryBean);
            }
            mCommonAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getAccountVoteDataHttp(ResultTableRowBean resultTableRowBean) {
        mResultTableRowBean = resultTableRowBean;
    }

    @Override
    public void postVoteTask(ResponseBean<String> data) {
        hideProgress();
        toast(getString(R.string.node_vote_success));
        finish();
        AppManager.getAppManager().finishActivity(GoNodeVoteActivity.class);
    }

    @Override
    public void getDataHttpFail(String msg) {
        hideProgress();
    }


}
