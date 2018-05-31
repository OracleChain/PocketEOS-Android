package com.oraclechain.pocketeos.modules.wallet.createwallet.bindphone;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.app.ActivityUtils;
import com.oraclechain.pocketeos.app.MyApplication;
import com.oraclechain.pocketeos.base.BaseAcitvity;
import com.oraclechain.pocketeos.bean.CodeAuthBean;
import com.oraclechain.pocketeos.bean.UserBean;
import com.oraclechain.pocketeos.gen.UserBeanDao;
import com.oraclechain.pocketeos.modules.leftdrawer.systemsetting.RichTextActivity;
import com.oraclechain.pocketeos.modules.main.MainActivity;
import com.oraclechain.pocketeos.modules.otherloginorshare.QQUserInfoBean;
import com.oraclechain.pocketeos.modules.otherloginorshare.WechatInfoBean;
import com.oraclechain.pocketeos.modules.wallet.createwallet.CreateWalletActivity;
import com.oraclechain.pocketeos.utils.FilesUtils;
import com.oraclechain.pocketeos.utils.RegexUtil;
import com.oraclechain.pocketeos.utils.Utils;
import com.oraclechain.pocketeos.view.ClearEditText;
import com.oraclechain.pocketeos.view.countdowntimer.CountDownTimerUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BindPhoneActivity extends BaseAcitvity<BindPhoneView, BindPhonePresenter> implements BindPhoneView {


    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.mobile_phone)
    ClearEditText mMobilePhone;
    @BindView(R.id.sms_password)
    ClearEditText mSmsPassword;
    @BindView(R.id.bind_phone)
    Button mBindPhone;
    @BindView(R.id.get_code)
    TextView mGetCode;
    String openid, type = null;
    QQUserInfoBean qqUserInfoBean;
    WechatInfoBean wechatInfoBean;
    @BindView(R.id.go_pocketeos_user)
    TextView mGoPocketeosUser;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bind_phone;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setCenterTitle(getString(R.string.bind_phone));
    }

    @Override
    protected void initData() {
        if (!TextUtils.isEmpty(getIntent().getStringExtra("openid"))) {
            openid = getIntent().getStringExtra("openid");
        }
        if (!TextUtils.isEmpty(getIntent().getStringExtra("type"))) {
            type = getIntent().getStringExtra("type");
        }
        if (getIntent().getParcelableExtra("qquserInfo") != null) {
            qqUserInfoBean = getIntent().getParcelableExtra("qquserInfo");
        }
        if (getIntent().getParcelableExtra("wechatInfoBean") != null) {
            wechatInfoBean = getIntent().getParcelableExtra("wechatInfoBean");
        }
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
        mGoPocketeosUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("details", FilesUtils.readAssetsTxt(BindPhoneActivity.this,"pocketeos_user"));
                bundle.putString("title", getString(R.string.setting_one));
                ActivityUtils.next(BindPhoneActivity.this, RichTextActivity.class, bundle);
            }
        });
    }

    @Override
    public BindPhonePresenter initPresenter() {
        return new BindPhonePresenter(BindPhoneActivity.this);
    }


    @OnClick({R.id.bind_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bind_phone:
                if (!TextUtils.isEmpty(mSmsPassword.getText().toString()) && !TextUtils.isEmpty(mMobilePhone.getText().toString().trim())) {
                    showProgress();
                    if (type.equals("1")) {
                        presenter.get_bind_phone_number(mMobilePhone.getText().toString(), openid, type, qqUserInfoBean.getNickname(), qqUserInfoBean.getFigureurl_qq_2(), mSmsPassword.getText().toString());
                    } else {
                        presenter.get_bind_phone_number(mMobilePhone.getText().toString(), openid, type, wechatInfoBean.getNickname(), wechatInfoBean.getHeadimgurl(), mSmsPassword.getText().toString());
                    }
                } else {
                    toast(getString(R.string.input_all_message));
                }
                break;
        }
    }


    @Override
    public void getCodeDataHttp(String message) {
        toast(message);
        mSmsPassword.setFocusable(true);
        mSmsPassword.setFocusableInTouchMode(true);
        mSmsPassword.requestFocus();
    }

    @Override
    public void getCodeAuthDataHttp(CodeAuthBean.DataBean codeAuthBean) {
        hideProgress();
        UserBean userBean0 = MyApplication.getDaoInstant().getUserBeanDao().queryBuilder().where(UserBeanDao.Properties.Wallet_phone.eq(mMobilePhone.getText().toString().trim())).build().unique();
        if (userBean0 != null) {
            toast("检测到您本地有" + mMobilePhone.getText().toString().trim() + "的钱包，请进入个人中心进行三方账号的绑定~");
            Utils.getSpUtils().put("firstUser", mMobilePhone.getText().toString().trim());//保存场上次登陆钱包
            Utils.getSpUtils().put("loginmode", "phone");//保存当前登录模式
            ActivityUtils.next(BindPhoneActivity.this, MainActivity.class, true);
        } else {
            //数据库存储数据
            UserBean userBean = new UserBean();
            userBean.setWallet_phone(mMobilePhone.getText().toString().trim());
            userBean.setWallet_uid(codeAuthBean.getUid());
            if (type.equals("1")) {
                userBean.setWallet_qq(openid);
                userBean.setWallet_name(qqUserInfoBean.getNickname());
                userBean.setWallet_img(qqUserInfoBean.getFigureurl());
            }
            if (type.equals("2")) {
                userBean.setWallet_weixin(openid);
                userBean.setWallet_name(wechatInfoBean.getNickname());
                userBean.setWallet_img(wechatInfoBean.getHeadimgurl());
            }
            MyApplication.getDaoInstant().getUserBeanDao().insert(userBean);
            MyApplication.getInstance().setUserBean(userBean);
            Utils.getSpUtils().put("firstUser", mMobilePhone.getText().toString().trim());//保存场次登陆钱包
            Utils.getSpUtils().put("loginmode", "phone");//保存当前登录模式
            ActivityUtils.next(BindPhoneActivity.this, CreateWalletActivity.class);
        }
    }

    @Override
    public void getDataHttpFail(String msg) {
        hideProgress();
        toast(msg);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
