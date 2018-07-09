package com.oraclechain.pocketeos.modules.resourcemanager.resourcehome.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.adapter.Find_tab_Adapter;
import com.oraclechain.pocketeos.base.BaseAcitvity;
import com.oraclechain.pocketeos.modules.normalvp.NormalPresenter;
import com.oraclechain.pocketeos.modules.normalvp.NormalView;
import com.oraclechain.pocketeos.modules.resourcemanager.resourcehome.fragment.memory.MemoryFragment;
import com.oraclechain.pocketeos.modules.resourcemanager.resourcehome.fragment.net.NetFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 资源管理首页
 */
public class ResourceManagerActivity extends BaseAcitvity<NormalView, NormalPresenter> implements NormalView {


    @BindView(R.id.tabs)
    TabLayout mTabs;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;

    List<Fragment> mFragments;
    List<String> mTitles = new ArrayList<>();
    NetFragment mNetFragment;
    MemoryFragment mMemoryFragment;
    String account = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_resource_manager;
    }

    @Override
    public NormalPresenter initPresenter() {
        return new NormalPresenter();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setCenterTitle(getString(R.string.resource_manager));


    }

    @Override
    protected void initData() {
        account = getIntent().getStringExtra("account");
        mTitles.add(getString(R.string.net_manger));
        mTitles.add(getString(R.string.memory_manger));
        mFragments = new ArrayList<>();
        mNetFragment = new NetFragment();
        mMemoryFragment = new MemoryFragment();
        Bundle bundle = new Bundle();
        bundle.putString("account", account);//选择的账号
        mNetFragment.setArguments(bundle);
        mMemoryFragment.setArguments(bundle);
        mFragments.add(mNetFragment);
        mFragments.add(mMemoryFragment);
        Find_tab_Adapter adapter = new Find_tab_Adapter(getSupportFragmentManager(), mFragments, mTitles);
        mViewpager.setAdapter(adapter);
        mTabs.setupWithViewPager(mViewpager);

        mTabs.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(mTabs, 60, 60);
            }
        });
    }

    @Override
    public void initEvent() {

    }


    public static void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }

}
