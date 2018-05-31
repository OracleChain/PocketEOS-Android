package com.oraclechain.pocketeos.modules.friendslist.friendsdetails;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liaoinstan.springview.widget.SpringView;
import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.adapter.AdapterManger;
import com.oraclechain.pocketeos.adapter.baseadapter.CommonAdapter;
import com.oraclechain.pocketeos.app.ActivityUtils;
import com.oraclechain.pocketeos.app.MyApplication;
import com.oraclechain.pocketeos.base.BaseAcitvity;
import com.oraclechain.pocketeos.bean.AccountInfoBean;
import com.oraclechain.pocketeos.bean.AccountWithCoinBean;
import com.oraclechain.pocketeos.bean.WalletDetailsBean;
import com.oraclechain.pocketeos.modules.switchusernumber.SwitchUserNumberActivity;
import com.oraclechain.pocketeos.modules.transaction.transferaccounts.TransferAccountsActivity;
import com.oraclechain.pocketeos.utils.BigDecimalUtil;
import com.oraclechain.pocketeos.utils.DensityUtil;
import com.oraclechain.pocketeos.utils.StringUtils;
import com.oraclechain.pocketeos.view.AppDefeatHeadView;
import com.oraclechain.pocketeos.view.CircleImageView;
import com.oraclechain.pocketeos.view.MyScrollview;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

//朋友详情页面
public class FriendsDetailsActivity extends BaseAcitvity<FriendsDetailsView, FriendsDetailsPresenter> implements FriendsDetailsView, MyScrollview.ScrollViewListener {


    @BindView(R.id.friends_title1)
    ImageView mFriendsTitle1;
    @BindView(R.id.friends_title)
    LinearLayout mFriendsTitle;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.add_friend)
    ImageView mAddFriend;
    @BindView(R.id.title)
    RelativeLayout mTitle;
    @BindView(R.id.friends_img)
    CircleImageView mFriendsImg;
    @BindView(R.id.friends_name)
    TextView mFriendsName;
    @BindView(R.id.friends_account_number)
    TextView mFriendsAccountNumber;
    @BindView(R.id.switch_number)
    TextView mSwitchNumber;
    @BindView(R.id.all_money)
    TextView mAllMoney;
    @BindView(R.id.friends_all_property)
    TextView mFriendsAllProperty;
    @BindView(R.id.property_tendency)
    TextView mPropertyTendency;
    @BindView(R.id.transfer_accounts)
    TextView mTransferAccounts;
    @BindView(R.id.make_collections)
    TextView mMakeCollections;
    @BindView(R.id.red_packet)
    TextView mRedPacket;
    @BindView(R.id.cardview)
    CardView mCardview;
    @BindView(R.id.recycle_icon)
    RecyclerView mRecycleIcon;
    @BindView(R.id.scrollView)
    MyScrollview mScrollView;
    @BindView(R.id.spring)
    SpringView mSpring;

    private TranslateAnimation mShowAction, mHiddenAction;
    private int topToCardView;//到CardView的距离
    private int showNumber;
    private int hintNumber;

    private List<AccountWithCoinBean> mAccountWithCoinBeen = new ArrayList<>();
    private CommonAdapter mCoinAdapter;

    private Boolean isFollow;//是否关注

    private List<AccountInfoBean> mAccountInfoBeanList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_friends_details;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        //下拉刷新
        mSpring.setHeader(new AppDefeatHeadView(this));
        mSpring.setGive(SpringView.Give.TOP);
        mSpring.setType(SpringView.Type.FOLLOW);
        mSpring.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                mSpring.onFinishFreshAndLoad();
                mAccountWithCoinBeen.clear();
                presenter.getAccountDetailsData(mFriendsAccountNumber.getText().toString());
            }

            @Override
            public void onLoadmore() {
                mSpring.onFinishFreshAndLoad();
            }
        });


        mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mShowAction.setDuration(300);

        mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f);
        mHiddenAction.setDuration(300);
        topToCardView = DensityUtil.dip2px(this, 260);
        mFriendsTitle.setVisibility(View.GONE);

        if (getIntent().getStringExtra("friend_type").equals("2")) {//1代表钱包2代表账号
            mFriendsName.setText(R.string.xxxwallet);
            mFriendsAccountNumber.setText(getIntent().getStringExtra("name"));
            mSwitchNumber.setClickable(false);
            presenter.getAccountDetailsData(getIntent().getStringExtra("name"));
        } else if (getIntent().getStringExtra("friend_type").equals("1")) {
            mFriendsName.setText(getIntent().getStringExtra("name") + getString(R.string.wallet));
            mSwitchNumber.setClickable(true);
            presenter.getWalletDetailsData(getIntent().getStringExtra("uid"));
        }
        MyApplication.getInstance().showCirImage(getIntent().getStringExtra("avatar"), mFriendsImg);

        if (getIntent().getStringExtra("from").equals("friend")) {
            isFollow = true;
            mAddFriend.setImageResource(R.mipmap.cancel_add);
        } else {
            presenter.IsFollow(getIntent().getStringExtra("friend_type"), getIntent().getStringExtra("uid"));
        }
    }

    @Override
    protected void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        layoutManager.setSmoothScrollbarEnabled(true);
        mRecycleIcon.setLayoutManager(layoutManager);
        mCoinAdapter = AdapterManger.getCoinAdapter(this, mAccountWithCoinBeen);
        mRecycleIcon.setAdapter(mCoinAdapter);
    }

    @Override
    public void initEvent() {
        mScrollView.setScrollViewListener(this);
        mFriendsTitle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mFriendsAccountNumber.getText().toString().equals(getString(R.string.no_account)) && !mFriendsAccountNumber.getText().toString().equals("PocketEos")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("account", mFriendsAccountNumber.getText().toString().trim().toString());
                    bundle.putString("coin", "EOS");
                    bundle.putString("from", "frienddetails");
                    ActivityUtils.next(FriendsDetailsActivity.this, TransferAccountsActivity.class, bundle);
                } else {
                    toast(getString(R.string.no_account_toast));
                }
            }
        });
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.fitsSystemWindows(false).statusBarColor(R.color.transparent).titleBar(mTitle).statusBarDarkFont(false, 0f).init();
    }

    @Override
    public FriendsDetailsPresenter initPresenter() {
        return new FriendsDetailsPresenter(FriendsDetailsActivity.this);
    }

    @Override
    public void getWalletDetailsDataHttp(List<WalletDetailsBean.DataBean> walletDetailsBean) {
        if (walletDetailsBean.size() == 0) {
            toast(getString(R.string.no_account_toast));
            mFriendsAccountNumber.setText(R.string.no_account);
            mSwitchNumber.setClickable(false);
            mTransferAccounts.setClickable(false);
            mFriendsTitle1.setClickable(false);
        } else {
            mFriendsAccountNumber.setText(walletDetailsBean.get(0).getEosAccountName());
            presenter.getAccountDetailsData(walletDetailsBean.get(0).getEosAccountName());
            mTransferAccounts.setClickable(true);
            mFriendsTitle1.setClickable(true);
            for (int i = 0; i < walletDetailsBean.size(); i++) {
                AccountInfoBean accountInfoBean = new AccountInfoBean();
                accountInfoBean.setAccount_name(walletDetailsBean.get(i).getEosAccountName());
                mAccountInfoBeanList.add(accountInfoBean);
            }
        }
    }

    @Override
    public void getAccountDetailsDataHttp(List<AccountWithCoinBean> accountWithCoinBeens) {
        mSpring.onFinishFreshAndLoad();
        if (accountWithCoinBeens.size() != 0) {
            mAccountWithCoinBeen.clear();
            for (AccountWithCoinBean accountWithCoinBean : accountWithCoinBeens) {
                mAccountWithCoinBeen.add(accountWithCoinBean);
            }
            BigDecimal eosToCny = BigDecimal.valueOf(Double.parseDouble(accountWithCoinBeens.get(0).getCoinForCny()));
            BigDecimal octToCny = BigDecimal.valueOf(Double.parseDouble(accountWithCoinBeens.get(1).getCoinForCny()));
            mFriendsAllProperty.setText("≈" + StringUtils.addComma(BigDecimalUtil.add(eosToCny, octToCny) + ""));
            mCoinAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getAddFollowsDataHttp() {
        toast(getString(R.string.follow_success));
        isFollow = true;
        mAddFriend.setImageResource(R.mipmap.cancel_add);
    }

    @Override
    public void getCancelFollow() {
        toast(getString(R.string.unfollow_success));
        isFollow = false;
        mAddFriend.setImageResource(R.mipmap.add_friend);

    }

    @Override
    public void isfollow(Boolean isFollows) {
        isFollow = isFollows;
        if (isFollow) {
            mAddFriend.setImageResource(R.mipmap.cancel_add);
        } else {
            mAddFriend.setImageResource(R.mipmap.add_friend);
        }
    }

    @Override
    public void getDataHttpFail(String msg) {
        mSpring.onFinishFreshAndLoad();
        toast(msg);
    }

    @Override
    public void onScrollChanged(MyScrollview scrollView, int x, int y, int oldx, int oldy) {
        if (y < topToCardView) {
            showNumber = 0;
            if (hintNumber != 1 && mFriendsTitle.getVisibility() == View.VISIBLE) {
                mFriendsTitle.startAnimation(mHiddenAction);
                mHiddenAction.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        hintNumber = 1;
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                mIvBack.startAnimation(mShowAction);
                mName.startAnimation(mShowAction);
                mAddFriend.startAnimation(mShowAction);
            }
            mFriendsTitle.setVisibility(View.GONE);
            mIvBack.setVisibility(View.VISIBLE);
            mName.setVisibility(View.VISIBLE);
            mAddFriend.setVisibility(View.VISIBLE);
        } else {
            hintNumber = 0;
            if (showNumber != 1 && mFriendsTitle.getVisibility() == View.GONE) {
                mFriendsTitle.startAnimation(mShowAction);

                mShowAction.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        showNumber = 1;
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                mIvBack.startAnimation(mHiddenAction);
                mAddFriend.startAnimation(mHiddenAction);
                mName.startAnimation(mHiddenAction);
            }
            mFriendsTitle.setVisibility(View.VISIBLE);
            mIvBack.setVisibility(View.GONE);
            mName.setVisibility(View.GONE);
            mAddFriend.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.iv_back, R.id.add_friend, R.id.switch_number, R.id.transfer_accounts})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.add_friend:
                if (isFollow) {
                    presenter.CancelFollowData(getIntent().getStringExtra("friend_type"), getIntent().getStringExtra("uid"), getIntent().getStringExtra("name"));
                } else {
                    presenter.AddFollowData(getIntent().getStringExtra("friend_type"), getIntent().getStringExtra("uid"), getIntent().getStringExtra("name"));
                }
                break;
            case R.id.switch_number:
                bundle.putString("account", mFriendsAccountNumber.getText().toString().trim().toString());
                bundle.putString("from", "details");
                bundle.putParcelableArrayList("allaccount", (ArrayList<? extends Parcelable>) mAccountInfoBeanList);
                ActivityUtils.next(FriendsDetailsActivity.this, SwitchUserNumberActivity.class, bundle, 100);
                break;
            case R.id.transfer_accounts:
                if (!mFriendsAccountNumber.getText().toString().equals(getString(R.string.no_account)) && !mFriendsAccountNumber.getText().toString().equals("PocketEos")) {
                    bundle.putString("getmoneyperson", mFriendsAccountNumber.getText().toString().trim().toString());
                    bundle.putString("account", MyApplication.getInstance().getUserBean().getWallet_main_account());
                    bundle.putString("coin", "EOS");
                    bundle.putString("from", "frienddetails");
                    ActivityUtils.next(this, TransferAccountsActivity.class, bundle);
                } else {
                    toast(getString(R.string.no_account_toast));
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 200) {
            mFriendsAccountNumber.setText(data.getExtras().getString("account"));
            mAccountWithCoinBeen.clear();
            presenter.getAccountDetailsData(data.getExtras().getString("account"));
        }
    }


}
