package com.oraclechain.pocketeos.modules.blackbox.blackhome;


import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.liaoinstan.springview.widget.SpringView;
import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.adapter.AdapterManger;
import com.oraclechain.pocketeos.adapter.baseadapter.CommonAdapter;
import com.oraclechain.pocketeos.adapter.baseadapter.MultiItemTypeAdapter;
import com.oraclechain.pocketeos.app.ActivityUtils;
import com.oraclechain.pocketeos.app.MyApplication;
import com.oraclechain.pocketeos.base.BaseFragment;
import com.oraclechain.pocketeos.bean.AccountInfoBean;
import com.oraclechain.pocketeos.bean.AccountWithCoinBean;
import com.oraclechain.pocketeos.modules.blackbox.blackboxcoindetails.BlackBoxCoinDetailsActivity;
import com.oraclechain.pocketeos.modules.transaction.makecollections.MakeCollectionsActivity;
import com.oraclechain.pocketeos.modules.transaction.transferaccounts.TransferAccountsActivity;
import com.oraclechain.pocketeos.utils.BigDecimalUtil;
import com.oraclechain.pocketeos.utils.DensityUtil;
import com.oraclechain.pocketeos.utils.JsonUtil;
import com.oraclechain.pocketeos.utils.RotateUtils;
import com.oraclechain.pocketeos.utils.ShowDialog;
import com.oraclechain.pocketeos.utils.StringUtils;
import com.oraclechain.pocketeos.utils.Utils;
import com.oraclechain.pocketeos.view.AppDefeatHeadView;
import com.oraclechain.pocketeos.view.MyScrollview;
import com.oraclechain.pocketeos.view.popupwindow.BasePopupWindow;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 黑匣子模式首页
 */
public class BlackHomeFragment extends BaseFragment<BlackBoxHomeView, BlackBoxHomePresenter> implements BlackBoxHomeView, MyScrollview.ScrollViewListener {

    @BindView(R.id.home_title1)
    ImageView mHomeTitle1;
    @BindView(R.id.home_title2)
    ImageView mHomeTitle2;
    @BindView(R.id.home_title)
    LinearLayout mHomeTitle;
    @BindView(R.id.iv_seting)
    ImageView mIvSeting;
    @BindView(R.id.title)
    RelativeLayout mTitle;
    @BindView(R.id.all_money)
    TextView mAllMoney;
    @BindView(R.id.user_all_property)
    TextView mUserAllProperty;
    @BindView(R.id.transfer_accounts)
    TextView mTransferAccounts;
    @BindView(R.id.make_collections)
    TextView mMakeCollections;
    @BindView(R.id.cardview)
    CardView mCardview;
    @BindView(R.id.black_box_recycle_icon)
    RecyclerView mRecycleIcon;
    @BindView(R.id.scrollView)
    MyScrollview mScrollView;
    @BindView(R.id.spring)
    SpringView mSpring;
    @BindView(R.id.choose_account)
    TextView mChooseAccount;
    @BindView(R.id.look_more)
    ImageView mLookMore;
    @BindView(R.id.choose_type)
    RelativeLayout mChooseType;


    private Openleft mOpenleft = null;
    private TranslateAnimation mShowAction, mHiddenAction;
    private int topToCardView;//到CardView的距离
    private int showNumber;
    private int hintNumber;
    private List<AccountWithCoinBean> mAccountWithCoinBeen = new ArrayList<>();
    private CommonAdapter mCoinAdapter;
    private String openMoney = null;
    private Boolean isOpen;
    private Boolean isfromWithData = false;

    private Boolean isSHow = false;
    private BasePopupWindow basePopupWindow;

    private List<AccountInfoBean> mAccountInfoBeanList = new ArrayList<>();


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_black_home;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        //系统刷新
        mSpring.setHeader(new AppDefeatHeadView(getContext()));
        mSpring.setGive(SpringView.Give.TOP);
        mSpring.setType(SpringView.Type.FOLLOW);
        mSpring.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                presenter.getAccountDetailsData(mChooseAccount.getText().toString().trim().toString());
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
        topToCardView = DensityUtil.dip2px(getActivity(), 260);
        mHomeTitle.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {
        ShowDialog.showDialog(getActivity(), "", true, null);

        mAccountInfoBeanList = JsonUtil.parseJsonToArrayList(MyApplication.getInstance().getUserBean().getAccount_info(), AccountInfoBean.class);
        mChooseAccount.setText(mAccountInfoBeanList.get(0).getAccount_name());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        layoutManager.setSmoothScrollbarEnabled(true);
        mRecycleIcon.setLayoutManager(layoutManager);
        mCoinAdapter = AdapterManger.getCoinAdapter(getActivity(), mAccountWithCoinBeen);
        mCoinAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("coin", mAccountWithCoinBeen.get(position));
                bundle.putString("account", mChooseAccount.getText().toString().trim().toString());
                ActivityUtils.next(getActivity(), BlackBoxCoinDetailsActivity.class, bundle);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        mRecycleIcon.setAdapter(mCoinAdapter);
    }

    @Override
    public void initEvent() {
        mScrollView.setScrollViewListener(this);
        mChooseType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSHow = !isSHow;
                RotateUtils.rotateArrow(mLookMore, isSHow);
                if (basePopupWindow != null && basePopupWindow.isShowing()) {
                    basePopupWindow.dismiss();
                } else {
                    basePopupWindow = new BasePopupWindow.Builder(getActivity()).
                            setView(LayoutInflater.from(getActivity()).inflate(R.layout.popuwindow_exchange_type, null))
                            .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                            .setOutsideTouchable(false)
                            .setAnimationStyle(R.style.AnimDown)
                            .create();
                    basePopupWindow.showAsDropDown(mChooseType);
                    isSHow = basePopupWindow.setAccountData(getActivity(), mAccountInfoBeanList, mChooseAccount.getText().toString().trim(), mLookMore, isSHow);
                    CommonAdapter commonAdapter = (CommonAdapter) ((RecyclerView) basePopupWindow.getContentView().findViewById(R.id.exchange_two_type)).getAdapter();
                    commonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                            basePopupWindow.dismiss();
                            presenter.getAccountDetailsData(mAccountInfoBeanList.get(position).getAccount_name());
                            isSHow = !isSHow;
                            RotateUtils.rotateArrow(mLookMore, isSHow);
                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                            return false;
                        }
                    });

                }
            }
        });
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(false, 0f).init();
        ImmersionBar.setTitleBar(getActivity(), mTitle);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isfromWithData) {
            presenter.getAccountDetailsData(mChooseAccount.getText().toString().trim());
        }
    }

    @Override
    public BlackBoxHomePresenter initPresenter() {
        return new BlackBoxHomePresenter(getActivity());
    }

    @Override
    public void onScrollChanged(MyScrollview scrollView, int x, int y, int oldx, int oldy) {
        if (y < topToCardView) {
            showNumber = 0;
            if (hintNumber != 1 && mHomeTitle.getVisibility() == View.VISIBLE) {
                mHomeTitle.startAnimation(mHiddenAction);
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
                mIvSeting.startAnimation(mShowAction);
            }
            mHomeTitle.setVisibility(View.GONE);
            mIvSeting.setVisibility(View.VISIBLE);
        } else {
            hintNumber = 0;
            if (showNumber != 1 && mHomeTitle.getVisibility() == View.GONE) {
                mHomeTitle.startAnimation(mShowAction);

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
                mIvSeting.startAnimation(mHiddenAction);
            }
            mHomeTitle.setVisibility(View.VISIBLE);
            mIvSeting.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.home_title1, R.id.home_title2, R.id.iv_seting, R.id.title, R.id.all_money, R.id.transfer_accounts, R.id.make_collections})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.home_title1:
                break;
            case R.id.home_title2:
                break;
            case R.id.iv_seting:
                mOpenleft.open(1);
                break;
            case R.id.title:
                break;
            case R.id.all_money:
                if (!isOpen) {
                    isOpen = true;
                    Utils.getSpUtils().put("isOpenMoney", false);
                    mUserAllProperty.setText("******");
                    Drawable drawable = getResources().getDrawable(R.mipmap.close_eye);
                    // 这一步必须要做,否则不会显示.
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    mAllMoney.setCompoundDrawables(null, null, drawable, null);
                } else {
                    isOpen = false;
                    Utils.getSpUtils().put("isOpenMoney", true);
                    mUserAllProperty.setText(openMoney);
                    Drawable drawable = getResources().getDrawable(R.mipmap.open_eye);
                    // 这一步必须要做,否则不会显示.
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    mAllMoney.setCompoundDrawables(null, null, drawable, null);
                }
                break;
            case R.id.transfer_accounts:
                bundle.putString("account", mChooseAccount.getText().toString().trim().toString());
                bundle.putString("coin", "EOS");
                bundle.putString("from", "home");
                ActivityUtils.next(getActivity(), TransferAccountsActivity.class, bundle);
                break;
            case R.id.make_collections:
                bundle.putString("account", mChooseAccount.getText().toString().trim().toString());
                bundle.putString("coin", "EOS");
                ActivityUtils.next(getActivity(), MakeCollectionsActivity.class, bundle);
                break;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && mImmersionBar != null) {
            mImmersionBar.statusBarDarkFont(false, 0f).init();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        mOpenleft = (Openleft) activity;
        super.onAttach(activity);
    }

    @Override
    public void getAccountDetailsDataHttp(List<AccountWithCoinBean> accountWithCoinBeens) {
        ShowDialog.dissmiss();
        mSpring.onFinishFreshAndLoad();

        if (accountWithCoinBeens.size() != 0) {
            mAccountWithCoinBeen.clear();
            for (AccountWithCoinBean accountWithCoinBean : accountWithCoinBeens) {
                mAccountWithCoinBeen.add(accountWithCoinBean);
            }
            mCoinAdapter.notifyDataSetChanged();
            BigDecimal eosToCny = BigDecimal.valueOf(Double.parseDouble(mAccountWithCoinBeen.get(0).getCoinForCny()));
            BigDecimal octToCny = BigDecimal.valueOf(Double.parseDouble(mAccountWithCoinBeen.get(1).getCoinForCny()));
            openMoney = "≈" + StringUtils.addComma((BigDecimalUtil.add(eosToCny, octToCny) + ""));
            isOpen = Utils.getSpUtils().getBoolean("isOpenMoney");
            if (!isOpen) {
                mUserAllProperty.setText("******");
                Drawable drawable = getResources().getDrawable(R.mipmap.close_eye);
                // 这一步必须要做,否则不会显示.
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                mAllMoney.setCompoundDrawables(null, null, drawable, null);
            } else {
                mUserAllProperty.setText(openMoney);
                Drawable drawable = getResources().getDrawable(R.mipmap.open_eye);
                // 这一步必须要做,否则不会显示.
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                mAllMoney.setCompoundDrawables(null, null, drawable, null);
            }
        }
    }

    @Override
    public void getDataHttpFail(String msg) {
        hideProgress();
        mSpring.onFinishFreshAndLoad();
        toast(msg);
    }

    public interface Openleft {
        void open(int tag);
    }
}
