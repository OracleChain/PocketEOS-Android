package com.oraclechain.pocketeos.modules.dapp.paidanswer.paidanswerhome.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.adapter.Find_tab_Adapter;
import com.oraclechain.pocketeos.app.ActivityUtils;
import com.oraclechain.pocketeos.base.BaseAcitvity;
import com.oraclechain.pocketeos.modules.dapp.paidanswer.chooseaccountwithcoin.ChooseAccountWithCoinActivity;
import com.oraclechain.pocketeos.modules.dapp.paidanswer.makequestion.MakeQuestionActivity;
import com.oraclechain.pocketeos.modules.dapp.paidanswer.paidanswerhome.fragment.PaidAnswerFragment;
import com.oraclechain.pocketeos.modules.normalvp.NormalPresenter;
import com.oraclechain.pocketeos.modules.normalvp.NormalView;
import com.oraclechain.pocketeos.utils.DensityUtil;
import com.oraclechain.pocketeos.utils.Utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//有问币答
public class PaidAnswerActivity extends BaseAcitvity<NormalView, NormalPresenter> implements NormalView {


    @BindView(R.id.go_back)
    ImageView mGoBack;
    @BindView(R.id.tabs)
    TabLayout mTabs;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.frame)
    FrameLayout mFrame;

    List<Fragment> mFragments;
    List<String> mTitles = new ArrayList<>();
    String account = null;
    @BindView(R.id.appbar)
    AppBarLayout mAppbar;
    @BindView(R.id.CollapsingToolbarLayout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.main_content)
    CoordinatorLayout mMainContent;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(false, 0.2f).init();
        ImmersionBar.setTitleBar(PaidAnswerActivity.this, mCollapsingToolbarLayout);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 200) {
            account = data.getStringExtra("account");
            init(data.getStringExtra("account"));
        }
    }

    public void init(String account) {


        mTitles.add(getResources().getString(R.string.existing_problem));
        mTitles.add(getResources().getString(R.string.past_problems));
        mFragments = new ArrayList<>();
        for (int i = 0; i < mTitles.size(); i++) {
            Bundle bundle = new Bundle();
            bundle.putString("releasedLable", String.valueOf(i));//0代表现有问题1代表以往问题
            bundle.putString("account", account);//选择的账号
            PaidAnswerFragment paidAnswerFragment = new PaidAnswerFragment();
            paidAnswerFragment.setArguments(bundle);
            mFragments.add(paidAnswerFragment);
        }
        Find_tab_Adapter adapter = new Find_tab_Adapter(getSupportFragmentManager(), mFragments, mTitles);
        mViewpager.setAdapter(adapter);
        mTabs.setupWithViewPager(mViewpager);


        mTabs.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //拿到tabLayout的mTabStrip属性
                    Field mTabStripField = mTabs.getClass().getDeclaredField("mTabStrip");
                    mTabStripField.setAccessible(true);
                    LinearLayout mTabStrip = (LinearLayout) mTabStripField.get(mTabs);
                    int dp10 = DensityUtil.dip2px(mTabs.getContext(), 10);
                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);
                        //拿到tabView的mTextView属性
                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);
                        TextView mTextView = (TextView) mTextViewField.get(tabView);
                        tabView.setPadding(0, 0, 0, 0);
                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width;
                        params.leftMargin = dp10;
                        params.rightMargin = dp10;
                        tabView.setLayoutParams(params);
                        tabView.invalidate();
                    }
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_paid_answer;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        if (!Utils.getSpUtils().getString("loginmode","").equals("phone")) {
            mCollapsingToolbarLayout.setBackgroundDrawable(this.getResources().getDrawable(R.mipmap.black_box_answer_top));
        }else {
            mCollapsingToolbarLayout.setBackgroundDrawable(this.getResources().getDrawable(R.mipmap.paid_answer));
        }
        ActivityUtils.next(PaidAnswerActivity.this, ChooseAccountWithCoinActivity.class, 100);
    }

    @Override
    protected void initData() {
    }

    @Override
    public void initEvent() {
        mGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("account", account);
                ActivityUtils.next(PaidAnswerActivity.this, MakeQuestionActivity.class, bundle);
            }
        });

    }

    @Override
    protected boolean isImmersionBarEnabled() {
        return false;
    }

    @Override
    public NormalPresenter initPresenter() {
        return new NormalPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}