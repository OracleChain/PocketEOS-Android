package com.oraclechain.pocketeos.modules.unstake;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.app.MyApplication;
import com.oraclechain.pocketeos.base.BaseAcitvity;
import com.oraclechain.pocketeos.bean.AccountDetailsBean;
import com.oraclechain.pocketeos.bean.UnstakeBean;
import com.oraclechain.pocketeos.blockchain.PushDatamanger;
import com.oraclechain.pocketeos.utils.BigDecimalUtil;
import com.oraclechain.pocketeos.utils.KeyBoardUtil;
import com.oraclechain.pocketeos.utils.PasswordToKeyUtils;
import com.oraclechain.pocketeos.utils.RegexUtil;
import com.oraclechain.pocketeos.utils.StringUtils;
import com.oraclechain.pocketeos.view.dialog.passworddialog.PasswordCallback;
import com.oraclechain.pocketeos.view.dialog.passworddialog.PasswordDialog;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.OnClick;

public class UnStakeActivity extends BaseAcitvity<UnStakeView, UnStakePresenter> implements UnStakeView {


    @BindView(R.id.unstark_cpu)
    TextView mUnstarkCpu;
    @BindView(R.id.unstark_net)
    TextView mUnstarkNet;
    @BindView(R.id.go_unstark)
    Button mGoUnstark;
    private String cpu, net;
    private String lowStake = "1";//最低剩余抵押金额

    @Override
    protected int getLayoutId() {
        return R.layout.activity_un_stake;
    }

    @Override
    public UnStakePresenter initPresenter() {
        return new UnStakePresenter(this);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setCenterTitle(getString(R.string.stake_eos));
    }

    @Override
    protected void initData() {
        showProgress();
        presenter.getAccountVoteData(getIntent().getStringExtra("account"));
    }

    @Override
    public void initEvent() {

    }


    @Override
    public void getAccountDetailsDataHttp(AccountDetailsBean accountDetailsBean) {
        hideProgress();
        if (BigDecimalUtil.greaterThan(BigDecimalUtil.toBigDecimal(lowStake), new BigDecimal(RegexUtil.subZeroAndDot(accountDetailsBean.getEos_cpu_weight())))) {//已质押的EOS数量小于最低质押数量
            mGoUnstark.setClickable(false);
            mGoUnstark.setText(R.string.dont_unstake);
            mGoUnstark.setBackgroundColor(getResources().getColor(R.color.gray_color));
            mUnstarkCpu.setText("0 EOS / " + RegexUtil.subZeroAndDot(accountDetailsBean.getEos_cpu_weight()) + " EOS ");
            mUnstarkNet.setText("0 EOS / " + RegexUtil.subZeroAndDot(accountDetailsBean.getEos_net_weight()) + " EOS");
            return;
        } else {
            mGoUnstark.setBackgroundColor(getResources().getColor(R.color.blue_button));
            mGoUnstark.setClickable(true);
            mGoUnstark.setText(R.string.sure_stake);
            cpu = BigDecimalUtil.minus(new BigDecimal(RegexUtil.subZeroAndDot(accountDetailsBean.getEos_cpu_weight())), new BigDecimal(lowStake), 4) + "";
            net = BigDecimalUtil.minus(new BigDecimal(RegexUtil.subZeroAndDot(accountDetailsBean.getEos_net_weight())), new BigDecimal(lowStake), 4) + "";
            mUnstarkCpu.setText(cpu + " EOS / " + RegexUtil.subZeroAndDot(accountDetailsBean.getEos_cpu_weight()) + " EOS");
            mUnstarkNet.setText(net + " EOS / " + RegexUtil.subZeroAndDot(accountDetailsBean.getEos_net_weight()) + " EOS");
        }

    }

    @Override
    public void getDataHttpFail(String msg) {
        hideProgress();
        toast(msg);
    }

    @OnClick(R.id.go_unstark)
    public void onViewClicked() {
        if (KeyBoardUtil.isSoftInputShow(this)) {
            KeyBoardUtil.getInstance(this).hide();
        }
        UnstakeBean unstakeBean = new UnstakeBean();
        unstakeBean.setFrom(getIntent().getStringExtra("account"));
        unstakeBean.setReceiver(getIntent().getStringExtra("account"));
        unstakeBean.setUnstake_cpu_quantity(StringUtils.addZero(cpu) + " EOS");
        unstakeBean.setUnstake_net_quantity(StringUtils.addZero(net) + " EOS");
        PasswordDialog mPasswordDialog = new PasswordDialog(this, new PasswordCallback() {
            @Override
            public void sure(String password) {
                if (MyApplication.getInstance().getUserBean().getWallet_shapwd().equals(PasswordToKeyUtils.shaCheck(password))) {

                    new PushDatamanger(UnStakeActivity.this, password, new PushDatamanger.Callback() {
                        @Override
                        public void getResult(String result) {
                            if (result.contains("transaction_id")) {
                                toast(getString(R.string.stake_success));
                                finish();
                            } else {
                                toast(getString(R.string.stake_fail));
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
}
