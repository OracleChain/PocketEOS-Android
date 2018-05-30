package com.oraclechain.pocketeos.modules.blackbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.app.ActivityUtils;
import com.oraclechain.pocketeos.app.AppManager;
import com.oraclechain.pocketeos.app.MyApplication;
import com.oraclechain.pocketeos.base.BaseAcitvity;
import com.oraclechain.pocketeos.gen.UserBeanDao;
import com.oraclechain.pocketeos.modules.blackbox.blackhome.BlackHomeFragment;
import com.oraclechain.pocketeos.modules.dapp.DappFragment;
import com.oraclechain.pocketeos.modules.leftdrawer.appupdate.AppUpdateActivity;
import com.oraclechain.pocketeos.modules.leftdrawer.messagecenter.MessageCenterActivity;
import com.oraclechain.pocketeos.modules.leftdrawer.suggestionfeedback.SuggestionFeedbackActivity;
import com.oraclechain.pocketeos.modules.leftdrawer.systemsetting.SystemSettingActivity;
import com.oraclechain.pocketeos.modules.leftdrawer.transactionhistory.TransactionHistoryActivity;
import com.oraclechain.pocketeos.modules.news.NewsFragment;
import com.oraclechain.pocketeos.modules.normalvp.NormalPresenter;
import com.oraclechain.pocketeos.modules.normalvp.NormalView;
import com.oraclechain.pocketeos.modules.wallet.createwallet.login.LoginActivity;
import com.oraclechain.pocketeos.modules.wallet.walletmanagement.WalletManagementActivity;
import com.oraclechain.pocketeos.utils.ToastUtils;
import com.oraclechain.pocketeos.utils.UpdateUtils;
import com.oraclechain.pocketeos.utils.Utils;

import butterknife.BindView;
import butterknife.OnClick;

public class BlackBoxMainActivity extends BaseAcitvity<NormalView, NormalPresenter> implements View.OnClickListener, NormalView, BlackHomeFragment.Openleft {

    @BindView(R.id.content)
    FrameLayout mContent;
    @BindView(R.id.ll_home)
    LinearLayout mLlHome;
    @BindView(R.id.ll_news)
    LinearLayout mLlNews;
    @BindView(R.id.ll_application)
    LinearLayout mLlApplication;
    @BindView(R.id.activity_main)
    LinearLayout mActivityMain;
    @BindView(R.id.wallet_management)
    TextView mWalletManagement;
    @BindView(R.id.transaction_history)
    TextView mTransactionHistory;
    @BindView(R.id.ll)
    LinearLayout mLl;
    @BindView(R.id.message_center)
    TextView mMessageCenter;
    @BindView(R.id.suggestion_feedback)
    TextView mSuggestionFeedback;
    @BindView(R.id.system_settings)
    TextView mSystemSettings;
    @BindView(R.id.app_update)
    TextView mAppUpdate;
    @BindView(R.id.logout_wallet)
    TextView mLogoutWallet;
    @BindView(R.id.navigation_view)
    LinearLayout mNavigationView;
    @BindView(R.id.drawer)
    DrawerLayout mDrawer;

    private BlackHomeFragment homeFragment;
    private NewsFragment newsFragment;
    private DappFragment applicationFragment;
    private long exitTime = 0;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_black_box_main;
    }

    @Override
    public NormalPresenter initPresenter() {
        return new NormalPresenter();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        selectedFragment(0);
        tabSelected(mLlHome);

        UpdateUtils.updateApp(this, 0);
    }

    @Override
    protected void initData() {
        MyApplication.getInstance().setUserBean(MyApplication.getDaoInstant().getUserBeanDao().queryBuilder().where(UserBeanDao.Properties.Wallet_name.eq(Utils.getSpUtils().getString("firstUser"))).build().unique());
    }

    @Override
    public void initEvent() {
        mLlHome.setOnClickListener(this);
        mLlNews.setOnClickListener(this);
        mLlApplication.setOnClickListener(this);
        mLogoutWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManager.getAppManager().finishAllActivity();
                ActivityUtils.next(BlackBoxMainActivity.this, LoginActivity.class, true);
            }
        });
    }

    private void selectedFragment(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragment(transaction);
        switch (position) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = new BlackHomeFragment();
                    transaction.add(R.id.content, homeFragment);
                } else {
                    transaction.show(homeFragment);
                }
                break;

            case 1:
                if (newsFragment == null) {
                    newsFragment = new NewsFragment();
                    transaction.add(R.id.content, newsFragment);
                } else {
                    transaction.show(newsFragment);
                }
                break;
            case 2:
                if (applicationFragment == null) {
                    applicationFragment = new DappFragment();
                    transaction.add(R.id.content, applicationFragment);
                } else {
                    transaction.show(applicationFragment);
                }
                break;
        }
        transaction.commit();
    }

    private void tabSelected(LinearLayout linearLayout) {
        mLlHome.setSelected(false);
        mLlNews.setSelected(false);
        mLlApplication.setSelected(false);
        linearLayout.setSelected(true);
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (newsFragment != null) {
            transaction.hide(newsFragment);
        }
        if (applicationFragment != null) {
            transaction.hide(applicationFragment);
        }
    }

    @OnClick({R.id.wallet_management, R.id.transaction_history, R.id.message_center, R.id.suggestion_feedback, R.id.system_settings, R.id.app_update})
    public void onViewClicked(View view) {
        //  mDrawer.closeDrawers();
        switch (view.getId()) {
            case R.id.wallet_management:
                ActivityUtils.next(BlackBoxMainActivity.this, WalletManagementActivity.class);
                break;
            case R.id.transaction_history:
                ActivityUtils.next(BlackBoxMainActivity.this, TransactionHistoryActivity.class);
                break;
            case R.id.message_center:
                ActivityUtils.next(BlackBoxMainActivity.this, MessageCenterActivity.class);
                break;
            case R.id.suggestion_feedback:
                ActivityUtils.next(BlackBoxMainActivity.this, SuggestionFeedbackActivity.class);
                break;
            case R.id.system_settings:
                ActivityUtils.next(BlackBoxMainActivity.this, SystemSettingActivity.class);
                break;
            case R.id.app_update:
                ActivityUtils.next(BlackBoxMainActivity.this, AppUpdateActivity.class);
                break;
        }
    }

    /**
     * 二次退出确认 结束软件
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO 按两次返回键退出应用程序
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // 判断间隔时间 大于2秒就退出应用
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                String msg1 = getString(R.string.drop_two_to_home);
                ToastUtils.showLongToast(msg1);
                // 计算两次返回键按下的时间差
                exitTime = System.currentTimeMillis();
            } else {
                // 返回桌面操作
                Intent home = new Intent(Intent.ACTION_MAIN);
                home.addCategory(Intent.CATEGORY_HOME);
                startActivity(home);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }

    @Override
    public void open(int tag) {
        mDrawer.openDrawer(Gravity.START);
    }    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_home:
                selectedFragment(0);
                tabSelected(mLlHome);
                break;
            case R.id.ll_news:
                selectedFragment(1);
                tabSelected(mLlNews);
                break;
            case R.id.ll_application:
                selectedFragment(2);
                tabSelected(mLlApplication);
                break;
        }
    }


}
