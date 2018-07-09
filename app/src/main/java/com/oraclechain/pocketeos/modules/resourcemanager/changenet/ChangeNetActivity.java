package com.oraclechain.pocketeos.modules.resourcemanager.changenet;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.app.MyApplication;
import com.oraclechain.pocketeos.base.BaseAcitvity;
import com.oraclechain.pocketeos.bean.AccountDetailsBean;
import com.oraclechain.pocketeos.bean.StakeBean;
import com.oraclechain.pocketeos.bean.UnstakeBean;
import com.oraclechain.pocketeos.blockchain.PushDatamanger;
import com.oraclechain.pocketeos.utils.BigDecimalUtil;
import com.oraclechain.pocketeos.utils.KeyBoardUtil;
import com.oraclechain.pocketeos.utils.PasswordToKeyUtils;
import com.oraclechain.pocketeos.utils.StringUtils;
import com.oraclechain.pocketeos.view.dialog.passworddialog.PasswordCallback;
import com.oraclechain.pocketeos.view.dialog.passworddialog.PasswordDialog;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 存储买卖管理
 */
public class ChangeNetActivity extends BaseAcitvity<ChangeNetView, ChangeNetPresenter> implements ChangeNetView {


    @BindView(R.id.seekbar)
    SeekBar mSeekbar;
    @BindView(R.id.number)
    TextView mNumber;
    @BindView(R.id.estimate)
    TextView mEstimate;
    @BindView(R.id.sure)
    TextView mSure;
    @BindView(R.id.can_stake_money)
    TextView mCanStakeMoney;
    @BindView(R.id.ll_can_stake)
    LinearLayout mLlCanStake;
    @BindView(R.id.desc)
    TextView mDesc;


    String tag, amount = null;
    BigDecimal canStakeAmount, userEosBanlance, total;
    String lowAmount = "1";
    String price;

    int initProgress = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_memory_manger;
    }

    @Override
    public ChangeNetPresenter initPresenter() {
        return new ChangeNetPresenter(this);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setCenterTitle(getIntent().getStringExtra("title"));
        tag = getIntent().getStringExtra("tag");
        amount = getIntent().getStringExtra("amount");
        price = getIntent().getStringExtra("price");
        if (!TextUtils.isEmpty(amount)) {
            canStakeAmount = BigDecimalUtil.minus(new BigDecimal(amount), new BigDecimal(lowAmount), 4);
        } else {
            canStakeAmount = new BigDecimal(0);
        }

        mDesc.setText(R.string.change_stake_amount);
        mLlCanStake.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        showProgress();
        presenter.getAccounteData(getIntent().getStringExtra("account"));
    }

    @Override
    public void initEvent() {
        mSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 0) {
                    mNumber.setText(lowAmount + " EOS");
                    if (tag.equals("1")) {
                        mEstimate.setText(getString(R.string.estimate) + BigDecimalUtil.divide(new BigDecimal(lowAmount), new BigDecimal(price), 4) + " ms");
                    } else {
                        mEstimate.setText(getString(R.string.estimate) + BigDecimalUtil.divide(new BigDecimal(lowAmount), new BigDecimal(price), 4) + " bytes");
                    }
                } else {
                    BigDecimal bigDecimal = BigDecimalUtil.multiply(BigDecimalUtil.divide(new BigDecimal(progress), new BigDecimal(100), 2), total, 4);
                    mNumber.setText(BigDecimalUtil.add(bigDecimal, new BigDecimal(lowAmount)) + " EOS");
                    if (tag.equals("1")) {
                        mEstimate.setText(getString(R.string.estimate)+ BigDecimalUtil.divide(BigDecimalUtil.add(bigDecimal, new BigDecimal(lowAmount)), new BigDecimal(price), 4) + " ms");
                    } else {
                        mEstimate.setText(getString(R.string.estimate) + BigDecimalUtil.divide(BigDecimalUtil.add(bigDecimal, new BigDecimal(lowAmount)), new BigDecimal(price), 4) + " bytes");
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (initProgress < seekBar.getProgress()) {
                    toast(getString(R.string.add_stake_amount_toast));
                } else if (initProgress > seekBar.getProgress()) {
                    toast(getString(R.string.minu_stake_amount_toast));
                }
            }
        });
    }

    @OnClick(R.id.sure)
    public void onViewClicked() {

        BigDecimal finalAmount = new BigDecimal(mNumber.getText().toString().substring(0, (mNumber.getText().toString().length() - 4)));
        if (BigDecimalUtil.greaterThan(finalAmount, new BigDecimal(amount))) {
            StakeBean stakeBean = new StakeBean();
            stakeBean.setFrom(getIntent().getStringExtra("account"));
            stakeBean.setReceiver(getIntent().getStringExtra("account"));
            if (tag.equals("1")) {
                stakeBean.setStake_cpu_quantity(StringUtils.addZero(BigDecimalUtil.minus(finalAmount, new BigDecimal(amount), 4).toString()) + " EOS");
                stakeBean.setStake_net_quantity("0.0000 EOS");
            } else {
                stakeBean.setStake_net_quantity(StringUtils.addZero(BigDecimalUtil.minus(finalAmount, new BigDecimal(amount), 4).toString()) + " EOS");
                stakeBean.setStake_cpu_quantity("0.0000 EOS");
            }
            PasswordDialog mPasswordDialog = new PasswordDialog(this, new PasswordCallback() {
                @Override
                public void sure(String password) {
                    if (MyApplication.getInstance().getUserBean().getWallet_shapwd().equals(PasswordToKeyUtils.shaCheck(password))) {
                        showProgress();
                        new PushDatamanger(ChangeNetActivity.this, password, new PushDatamanger.Callback() {
                            @Override
                            public void getResult(String result) {
                                if (result.contains("transaction_id")) {
                                    toast(getString(R.string.add_stake_success));
                                    finish();
                                } else {
                                    toast(getString(R.string.add_stake_fail));
                                }
                            }
                        }).pushAction("eosio", "delegatebw",
                                new Gson().toJson(stakeBean), getIntent().getStringExtra("account"));
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
        } else {
            UnstakeBean unstakeBean = new UnstakeBean();
            unstakeBean.setFrom(getIntent().getStringExtra("account"));
            unstakeBean.setReceiver(getIntent().getStringExtra("account"));
            if (tag.equals("1")) {
                unstakeBean.setUnstake_cpu_quantity(StringUtils.addZero(BigDecimalUtil.minus(new BigDecimal(amount), finalAmount, 4).toString()) + " EOS");
                unstakeBean.setUnstake_net_quantity("0.0000 EOS");
            } else {
                unstakeBean.setUnstake_net_quantity(StringUtils.addZero(BigDecimalUtil.minus(new BigDecimal(amount), finalAmount, 4).toString()) + " EOS");
                unstakeBean.setUnstake_cpu_quantity("0.0000 EOS");
            }
            PasswordDialog mPasswordDialog = new PasswordDialog(this, new PasswordCallback() {
                @Override
                public void sure(String password) {
                    if (MyApplication.getInstance().getUserBean().getWallet_shapwd().equals(PasswordToKeyUtils.shaCheck(password))) {
                        showProgress();
                        new PushDatamanger(ChangeNetActivity.this, password, new PushDatamanger.Callback() {
                            @Override
                            public void getResult(String result) {
                                if (result.contains("transaction_id")) {
                                    toast(getString(R.string.unstake_success));
                                    finish();
                                } else {
                                    toast(getString(R.string.unstake_fail));
                                }
                            }
                        }).pushAction("eosio", "undelegatebw",
                                new Gson().toJson(unstakeBean), getIntent().getStringExtra("account"));
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

        if (KeyBoardUtil.isSoftInputShow(this)) {
            KeyBoardUtil.getInstance(this).hide();
        }
    }

    @Override
    public void getAccountDetailsDataHttp(AccountDetailsBean accountDetailsBean) {
        hideProgress();
        userEosBanlance = new BigDecimal(accountDetailsBean.getEos_balance());
        total = BigDecimalUtil.add(userEosBanlance, canStakeAmount);
        initProgress = BigDecimalUtil.multiply(BigDecimalUtil.divide(canStakeAmount, total, 4), new BigDecimal(100), 0).intValue();
        mSeekbar.setProgress(initProgress);

        if (BigDecimalUtil.greaterThan(BigDecimalUtil.add(userEosBanlance, new BigDecimal(amount), 4), new BigDecimal(lowAmount))) {
            mSeekbar.setEnabled(true);
            mSeekbar.setFocusable(true);
            mSeekbar.setFocusableInTouchMode(true);
            mSure.setText(getString(R.string.sure));
            mSure.setClickable(true);
            mSure.setBackgroundColor(getResources().getColor(R.color.blue_button));
        } else {
            mSeekbar.setEnabled(false);
            mSeekbar.setFocusable(false);
            mSeekbar.setFocusableInTouchMode(false);
            mSure.setText(R.string.dont_change);
            mSure.setClickable(false);
            mSure.setBackgroundColor(getResources().getColor(R.color.gray_color));
        }
        mNumber.setText(new BigDecimal(amount) + " EOS");
        if (tag.equals("1")) {
            mEstimate.setText(getString(R.string.estimate) + BigDecimalUtil.divide(new BigDecimal(amount), new BigDecimal(price), 4) + " ms");
        } else {
            mEstimate.setText(getString(R.string.estimate) + BigDecimalUtil.divide(new BigDecimal(amount), new BigDecimal(price), 4) + " bytes");
        }
    }

    @Override
    public void getDataHttpFail(String msg) {
        hideProgress();
        toast(msg);
    }
}
