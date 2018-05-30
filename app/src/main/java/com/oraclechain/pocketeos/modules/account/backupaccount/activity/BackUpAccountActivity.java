package com.oraclechain.pocketeos.modules.account.backupaccount.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.WindowManager;

import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.adapter.baseadapter.BackUpAccount_tab_Adapter;
import com.oraclechain.pocketeos.base.BaseAcitvity;
import com.oraclechain.pocketeos.bean.AccountInfoBean;
import com.oraclechain.pocketeos.modules.account.backupaccount.fragment.BackUpAccountFragment;
import com.oraclechain.pocketeos.modules.normalvp.NormalPresenter;
import com.oraclechain.pocketeos.modules.normalvp.NormalView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 备份eos账号
 */
public class BackUpAccountActivity extends BaseAcitvity<NormalView, NormalPresenter> implements NormalView {


    @BindView(R.id.tabs)
    TabLayout mTabs;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;

    List<BackUpAccountFragment> mFragments;
    List<String> mTitles = new ArrayList<>();
    AccountInfoBean mAccountInfoBean = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_back_up_account;
    }

    @Override
    public NormalPresenter initPresenter() {
        return new NormalPresenter();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);//当前页面防截屏录屏
        setCenterTitle(getString(R.string.back_up_account));
    }

    @Override
    protected void initData() {
        mAccountInfoBean = getIntent().getParcelableExtra("account");
        init(mAccountInfoBean);
    }

    public void init(AccountInfoBean account) {
        mTitles.add(getResources().getString(R.string.pra_backup));
        mTitles.add(getResources().getString(R.string.word_backup));
        mFragments = new ArrayList<>();
        for (int i = 0; i < mTitles.size(); i++) {
            Bundle bundle = new Bundle();
            bundle.putInt("i",i);
            bundle.putParcelable("account", account);//选择的账号
            BackUpAccountFragment backUpAccountFragment = new BackUpAccountFragment();
            backUpAccountFragment.setArguments(bundle);
            mFragments.add(backUpAccountFragment);
        }
        BackUpAccount_tab_Adapter adapter = new BackUpAccount_tab_Adapter(getSupportFragmentManager(), mFragments, mTitles);
        mViewpager.setAdapter(adapter);
        mTabs.setupWithViewPager(mViewpager);
    }

    @Override
    public void initEvent() {

    }

}
