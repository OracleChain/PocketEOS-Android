package com.oraclechain.pocketeos.modules.resourcemanager.changememory;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.app.MyApplication;
import com.oraclechain.pocketeos.base.BaseAcitvity;
import com.oraclechain.pocketeos.bean.AccountDetailsBean;
import com.oraclechain.pocketeos.bean.BuyRamBean;
import com.oraclechain.pocketeos.bean.SealRamBean;
import com.oraclechain.pocketeos.bean.TableResultBean;
import com.oraclechain.pocketeos.blockchain.PushDatamanger;
import com.oraclechain.pocketeos.utils.BigDecimalUtil;
import com.oraclechain.pocketeos.utils.PasswordToKeyUtils;
import com.oraclechain.pocketeos.view.dialog.passworddialog.PasswordCallback;
import com.oraclechain.pocketeos.view.dialog.passworddialog.PasswordDialog;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.OnClick;

public class ChangeMemoryActivity extends BaseAcitvity<ChangeMemoryView, ChangeMemoryPresenter> implements ChangeMemoryView {


    @BindView(R.id.desc)
    TextView mDesc;
    @BindView(R.id.seekbar)
    SeekBar mSeekbar;
    @BindView(R.id.number)
    TextView mNumber;
    @BindView(R.id.estimate)
    TextView mEstimate;
    @BindView(R.id.sure)
    TextView mSure;

    String tag = null;
    BigDecimal price, total, useRam;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_memory;
    }

    @Override
    public ChangeMemoryPresenter initPresenter() {
        return new ChangeMemoryPresenter(this);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setCenterTitle(getIntent().getStringExtra("title"));
        tag = getIntent().getStringExtra("tag");
        useRam = new BigDecimal(getIntent().getStringExtra("totalRam"));


    }

    @Override
    protected void initData() {
        showProgress();
        presenter.getTabData();

    }

    @Override
    public void initEvent() {
        mSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (tag.equals("1")) {
                    BigDecimal bigDecimal = BigDecimalUtil.multiply(BigDecimalUtil.divide(new BigDecimal(progress), new BigDecimal(100), 2), total, 4);
                    mNumber.setText(bigDecimal + " EOS");
                    mEstimate.setText(getString(R.string.estimate)+ BigDecimalUtil.divide(bigDecimal, price, 4) + " bytes");
                } else {
                    BigDecimal bigDecimal = BigDecimalUtil.multiply(BigDecimalUtil.divide(new BigDecimal(progress), new BigDecimal(100), 2), useRam, 4);
                    mNumber.setText(bigDecimal + " bytes");
                    mEstimate.setText(getString(R.string.estimate_price) + BigDecimalUtil.multiply(bigDecimal, price, 4) + " EOS");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void getAccountDetailsDataHttp(AccountDetailsBean accountDetailsBean) {
        hideProgress();
        total = new BigDecimal(accountDetailsBean.getEos_balance());
        switch (tag) {
            case "1"://买入
                mDesc.setText(R.string.change_buy_number_toast);
                if (BigDecimalUtil.greaterThan(total, new BigDecimal(0))) {
                    mNumber.setText(BigDecimalUtil.multiply(BigDecimalUtil.divide(new BigDecimal(mSeekbar.getProgress()), new BigDecimal(100), 2), total, 4) + " EOS");
                    mEstimate.setText(getString(R.string.estimate) + BigDecimalUtil.divide(BigDecimalUtil.multiply(BigDecimalUtil.divide(new BigDecimal(mSeekbar.getProgress()), new BigDecimal(100), 2), total, 4), price, 4) + " bytes");
                    mSeekbar.setEnabled(true);
                    mSeekbar.setFocusable(true);
                    mSeekbar.setFocusableInTouchMode(true);
                    mSure.setText(getString(R.string.sure));
                    mSure.setClickable(true);
                    mSure.setBackgroundColor(getResources().getColor(R.color.blue_button));
                } else {
                    mNumber.setText("0 EOS");
                    mEstimate.setText(getString(R.string.estimate) + "0 bytes");
                    mSeekbar.setProgress(0);
                    mSeekbar.setEnabled(false);
                    mSeekbar.setFocusable(false);
                    mSeekbar.setFocusableInTouchMode(false);
                    mSure.setText(R.string.banlance_not_enough);
                    mSure.setClickable(false);
                    mSure.setBackgroundColor(getResources().getColor(R.color.gray_color));
                }
                break;
            case "2"://卖出
                mDesc.setText(R.string.change_seal_number_toast);
                if (BigDecimalUtil.greaterThan(useRam, new BigDecimal(0))) {
                    mNumber.setText(BigDecimalUtil.multiply(BigDecimalUtil.divide(new BigDecimal(mSeekbar.getProgress()), new BigDecimal(100), 2), useRam, 4) + " bytes");
                    mEstimate.setText(getString(R.string.estimate_price) + BigDecimalUtil.multiply(BigDecimalUtil.multiply(BigDecimalUtil.divide(new BigDecimal(mSeekbar.getProgress()), new BigDecimal(100), 2), useRam, 4), price, 4) + " EOS");
                    mSeekbar.setEnabled(true);
                    mSeekbar.setFocusable(true);
                    mSeekbar.setFocusableInTouchMode(true);
                    mSure.setText(getString(R.string.sure));
                    mSure.setClickable(true);
                    mSure.setBackgroundColor(getResources().getColor(R.color.blue_button));
                } else {
                    mNumber.setText("0 EOS");
                    mEstimate.setText(getString(R.string.estimate) + "0 bytes");
                    mSeekbar.setProgress(0);
                    mSeekbar.setEnabled(false);
                    mSeekbar.setFocusable(false);
                    mSeekbar.setFocusableInTouchMode(false);
                    mSure.setText(R.string.dont_seal);
                    mSure.setClickable(false);
                    mSure.setBackgroundColor(getResources().getColor(R.color.gray_color));
                }
                break;
        }
    }

    @Override
    public void getTableDataHttp(TableResultBean.DataBean dataBean) {
        price = BigDecimalUtil.divide(new BigDecimal(dataBean.getRows().get(0).getQuote().getBalance().substring(0, (dataBean.getRows().get(0).getQuote().getBalance().length() - 4))), new BigDecimal(dataBean.getRows().get(0).getBase().getBalance().substring(0, dataBean.getRows().get(0).getBase().getBalance().length() - 4)), 8);
        presenter.getAccounteData(getIntent().getStringExtra("account"));
    }

    @Override
    public void getDataHttpFail(String msg) {
        hideProgress();
        toast(msg);
    }


    @OnClick(R.id.sure)
    public void onViewClicked() {

        switch (tag) {
            case "1":
                BigDecimal finalNumber = new BigDecimal(mNumber.getText().toString().trim().substring(0, mNumber.getText().toString().trim().length() - 4));
                if (!BigDecimalUtil.greaterThan(finalNumber, new BigDecimal(0))) {
                    toast(getString(R.string.number_no_zero));
                    return;
                }
                BuyRamBean buyRamBean = new BuyRamBean();
                buyRamBean.setPayer(getIntent().getStringExtra("account"));
                buyRamBean.setReceiver(getIntent().getStringExtra("account"));
                buyRamBean.setQuant(mNumber.getText().toString());
                PasswordDialog mPasswordDialog = new PasswordDialog(this, new PasswordCallback() {
                    @Override
                    public void sure(String password) {
                        if (MyApplication.getInstance().getUserBean().getWallet_shapwd().equals(PasswordToKeyUtils.shaCheck(password))) {
                            showProgress();
                            new PushDatamanger(ChangeMemoryActivity.this, password, new PushDatamanger.Callback() {
                                @Override
                                public void getResult(String result) {
                                    if (result.contains("transaction_id")) {
                                        toast(getString(R.string.bug_success));
                                        finish();
                                    } else {
                                        toast(getString(R.string.buy_fail));
                                    }
                                }
                            }).pushAction("eosio", "buyram",
                                    new Gson().toJson(buyRamBean), getIntent().getStringExtra("account"));
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
                break;
            case "2":
                BigDecimal finalNumber1 = new BigDecimal(mNumber.getText().toString().trim().substring(0, mNumber.getText().toString().trim().length() - 6));
                if (!BigDecimalUtil.greaterThan(finalNumber1, new BigDecimal(0))) {
                    toast(getString(R.string.number_no_zero));
                    return;
                }
                SealRamBean sealRamBean = new SealRamBean();
                sealRamBean.setAccount(getIntent().getStringExtra("account"));
                sealRamBean.setBytes(new BigDecimal(mNumber.getText().toString().trim().substring(0, mNumber.getText().toString().trim().length() - 6)).intValue());
                PasswordDialog mPasswordDialog1 = new PasswordDialog(this, new PasswordCallback() {
                    @Override
                    public void sure(String password) {
                        if (MyApplication.getInstance().getUserBean().getWallet_shapwd().equals(PasswordToKeyUtils.shaCheck(password))) {
                            showProgress();
                            new PushDatamanger(ChangeMemoryActivity.this, password, new PushDatamanger.Callback() {
                                @Override
                                public void getResult(String result) {
                                    if (result.contains("transaction_id")) {
                                        toast(getString(R.string.seal_success));
                                        finish();
                                    } else {
                                        toast(getString(R.string.seal_fail));
                                    }
                                }
                            }).pushAction("eosio", "sellram",
                                    new Gson().toJson(sealRamBean), getIntent().getStringExtra("account"));
                        } else {
                            toast(getResources().getString(R.string.password_error));
                        }
                    }

                    @Override
                    public void cancle() {

                    }
                });
                mPasswordDialog1.setCancelable(true);
                mPasswordDialog1.show();
                break;
        }

    }
}
