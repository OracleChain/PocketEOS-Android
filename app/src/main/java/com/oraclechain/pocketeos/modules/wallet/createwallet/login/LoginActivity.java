package com.oraclechain.pocketeos.modules.wallet.createwallet.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.app.ActivityUtils;
import com.oraclechain.pocketeos.app.MyApplication;
import com.oraclechain.pocketeos.base.BaseAcitvity;
import com.oraclechain.pocketeos.bean.CodeAuthBean;
import com.oraclechain.pocketeos.bean.UserBean;
import com.oraclechain.pocketeos.gen.UserBeanDao;
import com.oraclechain.pocketeos.modules.blackbox.existwalletlogin.ExistBlackBoxLoginActivity;
import com.oraclechain.pocketeos.modules.blackbox.nowalletlogin.BlackBoxLoginActivity;
import com.oraclechain.pocketeos.modules.leftdrawer.systemsetting.RichTextActivity;
import com.oraclechain.pocketeos.modules.main.MainActivity;
import com.oraclechain.pocketeos.modules.otherloginorshare.BaseUIListener;
import com.oraclechain.pocketeos.modules.otherloginorshare.OtherLoginCallBack;
import com.oraclechain.pocketeos.modules.otherloginorshare.QQUserInfoBean;
import com.oraclechain.pocketeos.modules.otherloginorshare.WxShareAndLoginUtils;
import com.oraclechain.pocketeos.modules.wallet.createwallet.CreateWalletActivity;
import com.oraclechain.pocketeos.modules.wallet.createwallet.bindphone.BindPhoneActivity;
import com.oraclechain.pocketeos.utils.AndroidBug5497Workaround;
import com.oraclechain.pocketeos.utils.FilesUtils;
import com.oraclechain.pocketeos.utils.RegexUtil;
import com.oraclechain.pocketeos.utils.Utils;
import com.oraclechain.pocketeos.view.ClearEditText;
import com.oraclechain.pocketeos.view.countdowntimer.CountDownTimerUtils;
import com.tencent.tauth.Tencent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//登录页面
public class LoginActivity extends BaseAcitvity<LoginView, LoginPresenter> implements LoginView {
    @BindView(R.id.title)
    RelativeLayout mTitle;
    @BindView(R.id.mobile_phone)
    ClearEditText mMobilePhone;
    @BindView(R.id.sms_password)
    ClearEditText mSmsPassword;
    @BindView(R.id.create_account)
    Button mCreatNumber;
    @BindView(R.id.go_weixin_login)
    ImageView mGoWeixinLogin;
    @BindView(R.id.go_qq_login)
    ImageView mGoQqLogin;
    @BindView(R.id.get_code)
    TextView mGetCode;
    @BindView(R.id.black_box)
    TextView mBlackBox;
    @BindView(R.id.other_login_txt)
    TextView mOtherLoginTxt;
    @BindView(R.id.go_pocketeos_user)
    TextView mGoPocketeosUser;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
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
        mGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(mMobilePhone.getText().toString().trim())) {
                    if (RegexUtil.isMobileNO(mMobilePhone.getText().toString().trim())) {
                        CountDownTimerUtils countDownTimerUtils = new CountDownTimerUtils(mGetCode, 60000, 1000, "#999999");
                        countDownTimerUtils.start();
                        presenter.getCodeData(mMobilePhone.getText().toString().trim());
                    } else {
                        toast(getString(R.string.phone_format));
                    }
                } else {
                    toast(getString(R.string.phone_input));
                }
            }
        });
        mBlackBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyApplication.getDaoInstant().getUserBeanDao().count() == 0) {//本地没有任何钱包跳转页面
                    ActivityUtils.next(LoginActivity.this, BlackBoxLoginActivity.class);
                } else {//本地存在有钱包跳转页面
                    ActivityUtils.next(LoginActivity.this, ExistBlackBoxLoginActivity.class);
                }
            }
        });
        mGoPocketeosUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("details", FilesUtils.readAssetsTxt(LoginActivity.this,"pocketeos_user"));
                bundle.putString("title", getString(R.string.setting_one));
                ActivityUtils.next(LoginActivity.this, RichTextActivity.class, bundle);
            }
        });
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.fitsSystemWindows(false).statusBarColor(R.color.transparent).titleBar(mTitle).statusBarDarkFont(false, 0f).init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideProgress();
    }

    @Override
    public LoginPresenter initPresenter() {
        return new LoginPresenter(LoginActivity.this);
    }

    @Override
    public void getCodeDataHttp(String msg) {
        toast(msg);
        mSmsPassword.setFocusable(true);
        mSmsPassword.setFocusableInTouchMode(true);
        mSmsPassword.requestFocus();
    }

    @Override
    public void getCodeAuthDataHttp(CodeAuthBean.DataBean codeAuthBean) {
        hideProgress();
        UserBean userBean0 = MyApplication.getDaoInstant().getUserBeanDao().queryBuilder().where(UserBeanDao.Properties.Wallet_phone.eq(mMobilePhone.getText().toString().trim())).build().unique();
        if (userBean0 != null) {
            toast("检测到您本地有" + mMobilePhone.getText().toString().trim() + "钱包");
            Utils.getSpUtils().put("firstUser", mMobilePhone.getText().toString().trim());//保存场上次登陆钱包
            Utils.getSpUtils().put("loginmode", "phone");//保存当前登录模式
            ActivityUtils.next(LoginActivity.this, MainActivity.class, true);
        } else {
            //数据库存储数据
            UserBean userBean = new UserBean();
            userBean.setWallet_phone(mMobilePhone.getText().toString().trim());
            userBean.setWallet_uid(codeAuthBean.getUid());
            userBean.setWallet_name(mMobilePhone.getText().toString().trim().substring(7, 11).toString());
            MyApplication.getDaoInstant().getUserBeanDao().insert(userBean);
            MyApplication.getInstance().setUserBean(userBean);
            Utils.getSpUtils().put("firstUser", mMobilePhone.getText().toString().trim());//保存上次登陆钱包
            Utils.getSpUtils().put("loginmode", "phone");//保存当前登录模式
            ActivityUtils.next(LoginActivity.this, CreateWalletActivity.class);
        }

    }

    @Override
    public void getDataHttpFail(String msg) {
        hideProgress();
        toast(msg);
    }

    @OnClick({R.id.create_account, R.id.go_weixin_login, R.id.go_qq_login})
    public void onViewClicked(View view) {
        showProgress();
        switch (view.getId()) {
            case R.id.create_account:
                if (!TextUtils.isEmpty(mSmsPassword.getText().toString()) && !TextUtils.isEmpty(mMobilePhone.getText().toString().trim())) {
                    presenter.getcodeAuthData(mMobilePhone.getText().toString(), mSmsPassword.getText().toString());
                } else {
                    hideProgress();
                    toast(getString(R.string.input_all_message));
                }
                break;
            case R.id.go_weixin_login:
                WxShareAndLoginUtils.WxLogin(this);
                break;
            case R.id.go_qq_login:
                MyApplication.getInstance().getTencent().login(LoginActivity.this, "all", new BaseUIListener(LoginActivity.this, new OtherLoginCallBack() {
                    @Override
                    public void userInfoData(String openid, QQUserInfoBean qqUserInfoBean) {
                        if (openid != null && qqUserInfoBean != null) {
                            UserBean userBean = MyApplication.getDaoInstant().getUserBeanDao().queryBuilder().where(UserBeanDao.Properties.Wallet_qq.eq(openid)).build().unique();
                            if (userBean != null) {
                                toast(getString(R.string.bind_qq_wallet_exist));
                                Utils.getSpUtils().put("firstUser", userBean.getWallet_phone());//保存上次登陆钱包
                                ActivityUtils.next(LoginActivity.this, MainActivity.class, true);
                            } else {
                                Bundle bundle = new Bundle();
                                bundle.putString("openid", openid);
                                bundle.putString("type", "1");//1 代表 qq 2 代表微信
                                bundle.putParcelable("qquserInfo", qqUserInfoBean);
                                ActivityUtils.next(LoginActivity.this, BindPhoneActivity.class, bundle);
                            }
                        }
                    }
                }));
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        hideProgress();
        Tencent.onActivityResultData(requestCode, resultCode, data, new BaseUIListener());
    }

    @Override
    protected void onStop() {
        super.onStop();
        hideProgress();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
