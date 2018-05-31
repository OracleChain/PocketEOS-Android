package com.oraclechain.pocketeos.modules.blackbox.existwalletlogin;

import android.os.Bundle;
import android.widget.Button;

import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.base.BaseAcitvity;
import com.oraclechain.pocketeos.modules.normalvp.NormalPresenter;
import com.oraclechain.pocketeos.modules.normalvp.NormalView;
import com.oraclechain.pocketeos.utils.AndroidBug5497Workaround;
import com.oraclechain.pocketeos.view.ClearEditText;

import butterknife.BindView;
import butterknife.OnClick;

public class BlackBoxCreatWalletActivity extends BaseAcitvity<NormalView, NormalPresenter> implements NormalView {


    @BindView(R.id.black_box_wallet_name)
    ClearEditText mBlackBoxWalletName;
    @BindView(R.id.black_box_wallet_pwd)
    ClearEditText mBlackBoxWalletPwd;
    @BindView(R.id.black_box_two_pwd)
    ClearEditText mBlackBoxTwoPwd;
    @BindView(R.id.black_box_create_wallet)
    Button mBlackBoxCreateWallet;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_black_box_creat_wallet;
    }

    @Override
    public NormalPresenter initPresenter() {
        return new NormalPresenter();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        AndroidBug5497Workaround.assistActivity(activity);
        setCenterTitle(getString(R.string.creat_blackbox_wallet));
    }

    @Override
    protected void initData() {

    }

    @Override
    public void initEvent() {

    }

    @OnClick(R.id.black_box_create_wallet)
    public void onViewClicked() {
    }
}
