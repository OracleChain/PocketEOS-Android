package com.oraclechain.pocketeos.modules.welcome;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.app.ActivityUtils;
import com.oraclechain.pocketeos.app.MyApplication;
import com.oraclechain.pocketeos.base.BaseAcitvity;
import com.oraclechain.pocketeos.bean.UserBean;
import com.oraclechain.pocketeos.gen.UserBeanDao;
import com.oraclechain.pocketeos.modules.blackbox.BlackBoxMainActivity;
import com.oraclechain.pocketeos.modules.main.MainActivity;
import com.oraclechain.pocketeos.modules.normalvp.NormalPresenter;
import com.oraclechain.pocketeos.modules.normalvp.NormalView;
import com.oraclechain.pocketeos.modules.wallet.createwallet.login.LoginActivity;
import com.oraclechain.pocketeos.utils.Utils;

public class WelcomeActivity extends BaseAcitvity<NormalView, NormalPresenter> implements NormalView {
    private static final int TIME = 500;
    private static final int GO_HOME = 200;
    private static final int GO_BLACKBOXHOME = 700;
    private static final int GO_GUIDE = 100;
    private static final int GO_LOGIN = 300;
    private static final int GO_CREATWALLET = 400;
    private static final int GO_BLACKBOXCREATWALLET = 600;
    private boolean isFirstIn = true;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case GO_HOME:
                    goHome();
                    break;
                case GO_GUIDE:
                    goLogin();
                    break;
                case GO_LOGIN:
                    goLogin();
                    break;
                case GO_CREATWALLET:
                    goCreat_wallet();
                    break;
                case GO_BLACKBOXCREATWALLET:
                    goCreat_black_box_wallet();
                    break;
                case GO_BLACKBOXHOME:
                    goBlackboxHome();
                    break;
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        if (isFirstIn && TextUtils.isEmpty(Utils.getSpUtils().getString("firstUser","")) && TextUtils.isEmpty(Utils.getSpUtils().getString("loginmode",""))) {
            mHandler.sendEmptyMessageDelayed(GO_LOGIN, TIME);
            isFirstIn = false;
        } else if(!TextUtils.isEmpty(Utils.getSpUtils().getString("firstUser",""))&& Utils.getSpUtils().getString("loginmode","").equals("phone")){//上次为社交模式登录
            UserBean userBean = MyApplication.getDaoInstant().getUserBeanDao().queryBuilder().where(UserBeanDao.Properties.Wallet_phone.eq(Utils.getSpUtils().getString("firstUser"))).build().unique();
            if (userBean == null) {
                mHandler.sendEmptyMessageDelayed(GO_LOGIN, TIME);
            } else if (userBean != null && userBean.getWallet_main_account() == null) {//钱包存在 但是没有账号
                    mHandler.sendEmptyMessageDelayed(GO_CREATWALLET, TIME);
            } else {
                    mHandler.sendEmptyMessageDelayed(GO_HOME, TIME);
            }
        }else if(!TextUtils.isEmpty(Utils.getSpUtils().getString("firstUser","")) && Utils.getSpUtils().getString("loginmode").equals("blackbox")){//上次登录模式为黑匣子模式
            UserBean userBean = MyApplication.getDaoInstant().getUserBeanDao().queryBuilder().where(UserBeanDao.Properties.Wallet_name.eq(Utils.getSpUtils().getString("firstUser"))).build().unique();
            if (userBean == null) {
                mHandler.sendEmptyMessageDelayed(GO_LOGIN, TIME);
            } else if (userBean != null && userBean.getWallet_main_account() == null) {//钱包存在 但是没有账号
                mHandler.sendEmptyMessageDelayed(GO_BLACKBOXCREATWALLET, TIME);
            } else {
                mHandler.sendEmptyMessageDelayed(GO_BLACKBOXHOME, TIME);
            }

        }
    }

    @Override
    public void initEvent() {

    }

    @Override
    public NormalPresenter initPresenter() {
        return new NormalPresenter();
    }

    private void goHome() {
        ActivityUtils.next(WelcomeActivity.this, MainActivity.class, true);
    }

    private void goBlackboxHome() {
        ActivityUtils.next(WelcomeActivity.this, BlackBoxMainActivity.class, true);
    }

    private void goLogin() {
        ActivityUtils.next(WelcomeActivity.this, LoginActivity.class, true);
    }

    private void goCreat_wallet() {
        ActivityUtils.next(WelcomeActivity.this, LoginActivity.class);
    }

    private void goCreat_black_box_wallet() {
        ActivityUtils.next(WelcomeActivity.this, LoginActivity.class);
    }

    /*private void goGuide() {
        ActivityUtils.next(WelcomeActivity.this, GuideActivity.class, true);
    }*/
}
