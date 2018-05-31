package com.oraclechain.pocketeos.modules.blackbox.nowalletlogin;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.app.ActivityUtils;
import com.oraclechain.pocketeos.app.MyApplication;
import com.oraclechain.pocketeos.base.BaseAcitvity;
import com.oraclechain.pocketeos.bean.UserBean;
import com.oraclechain.pocketeos.gen.UserBeanDao;
import com.oraclechain.pocketeos.modules.account.createaccount.CreateAccountActivity;
import com.oraclechain.pocketeos.modules.leftdrawer.systemsetting.RichTextActivity;
import com.oraclechain.pocketeos.modules.normalvp.NormalPresenter;
import com.oraclechain.pocketeos.modules.normalvp.NormalView;
import com.oraclechain.pocketeos.utils.AndroidBug5497Workaround;
import com.oraclechain.pocketeos.utils.EncryptUtil;
import com.oraclechain.pocketeos.utils.FilesUtils;
import com.oraclechain.pocketeos.utils.PasswordToKeyUtils;
import com.oraclechain.pocketeos.utils.Utils;
import com.oraclechain.pocketeos.view.ClearEditText;

import butterknife.BindView;
import butterknife.OnClick;

public class BlackBoxLoginActivity extends BaseAcitvity<NormalView, NormalPresenter> implements NormalView {


    @BindView(R.id.social_contact)
    TextView mSocialContact;
    @BindView(R.id.black_box_wallet_name)
    ClearEditText mBlackBoxWalletName;
    @BindView(R.id.black_box_wallet_pwd)
    ClearEditText mBlackBoxWalletPwd;
    @BindView(R.id.black_box_two_pwd)
    ClearEditText mBlackBoxTwoPwd;
    @BindView(R.id.black_box_create_wallet)
    Button mBlackBoxCreateWallet;
    @BindView(R.id.black_box_info)
    TextView mBlackBoxInfo;
    @BindView(R.id.title)
    RelativeLayout mTitle;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_black_box_login;
    }

    @Override
    public NormalPresenter initPresenter() {
        return new NormalPresenter();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        AndroidBug5497Workaround.assistActivity(activity);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.fitsSystemWindows(false).statusBarColor(R.color.transparent).titleBar(mTitle).statusBarDarkFont(false, 0f).init();
    }

    @OnClick({R.id.social_contact, R.id.black_box_create_wallet, R.id.black_box_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.social_contact:
                finish();
                break;
            case R.id.black_box_create_wallet:
                if (!TextUtils.isEmpty(mBlackBoxWalletName.getText().toString()) && !TextUtils.isEmpty(mBlackBoxWalletPwd.getText().toString().trim()) && !TextUtils.isEmpty(mBlackBoxTwoPwd.getText().toString().trim())) {
                    if (mBlackBoxWalletPwd.getText().toString().trim().equals(mBlackBoxTwoPwd.getText().toString().trim())) {
                        if (MyApplication.getDaoInstant().getUserBeanDao().queryBuilder().where(UserBeanDao.Properties.Wallet_name.eq(mBlackBoxWalletName.getText().toString().trim())).build().unique() == null) {//检测本地存在的钱包不包含该名称
                            //数据库存储数据
                            UserBean userBean = new UserBean();
                            userBean.setWallet_uid("6f1a8e0eb24afb7ddc829f96f9f74e9d");
                            userBean.setWallet_name(mBlackBoxWalletName.getText().toString().trim());
                            String randomString = EncryptUtil.getRandomString(32);
                            userBean.setWallet_shapwd(PasswordToKeyUtils.shaEncrypt(randomString+mBlackBoxWalletPwd.getText().toString().trim()));
                            MyApplication.getDaoInstant().getUserBeanDao().insert(userBean);
                            MyApplication.getInstance().setUserBean(userBean);
                            Utils.getSpUtils().put("firstUser", mBlackBoxWalletName.getText().toString().trim());//保存上次登陆钱包
                            Utils.getSpUtils().put("loginmode", "blackbox");//保存当前登录模式
                            Bundle bundle = new Bundle();
                            bundle.putInt("type", 1);
                            ActivityUtils.next(BlackBoxLoginActivity.this, CreateAccountActivity.class);
                        } else {
                            toast(getString(R.string.wallet_name_exist));
                        }
                    } else {
                        toast(getString(R.string.two_pwd));
                    }
                } else {
                    toast(getString(R.string.input_all_message));
                }
                break;
            case R.id.black_box_info:
                Bundle bundle = new Bundle();
                bundle.putString("details", FilesUtils.readAssetsTxt(this,"black_box_intro"));
                bundle.putString("title", getString(R.string.black_box));
                ActivityUtils.next(BlackBoxLoginActivity.this, RichTextActivity.class, bundle);
                break;
        }
    }
}
