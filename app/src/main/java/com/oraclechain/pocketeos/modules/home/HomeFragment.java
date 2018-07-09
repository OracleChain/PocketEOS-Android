package com.oraclechain.pocketeos.modules.home;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import com.oraclechain.pocketeos.modules.account.accountdetails.AccountDetailsActivity;
import com.oraclechain.pocketeos.modules.coindetails.CoinDetailsActivity;
import com.oraclechain.pocketeos.modules.leftdrawer.usercenter.UserCenterActivity;
import com.oraclechain.pocketeos.modules.nodevote.NodeVoteActivity;
import com.oraclechain.pocketeos.modules.scancode.ScanCodeActivity;
import com.oraclechain.pocketeos.modules.switchusernumber.SwitchUserNumberActivity;
import com.oraclechain.pocketeos.modules.transaction.makecollections.MakeCollectionsActivity;
import com.oraclechain.pocketeos.modules.transaction.redpacket.makeredpacket.RedPacketActivity;
import com.oraclechain.pocketeos.modules.transaction.transferaccounts.TransferAccountsActivity;
import com.oraclechain.pocketeos.modules.unstake.UnStakeActivity;
import com.oraclechain.pocketeos.utils.BigDecimalUtil;
import com.oraclechain.pocketeos.utils.DensityUtil;
import com.oraclechain.pocketeos.utils.JsonUtil;
import com.oraclechain.pocketeos.utils.ShowDialog;
import com.oraclechain.pocketeos.utils.StringUtils;
import com.oraclechain.pocketeos.utils.Utils;
import com.oraclechain.pocketeos.view.AppDefeatHeadView;
import com.oraclechain.pocketeos.view.MyScrollview;
import com.oraclechain.pocketeos.view.RoundImageView;
import com.oraclechain.pocketeos.view.ScrollText;
import com.oraclechain.pocketeos.view.dialog.advertisingdialog.AdvertisingCallback;
import com.oraclechain.pocketeos.view.dialog.advertisingdialog.AdvertisingDialog;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.ljp.permission.PermissionItem;

import static com.oraclechain.pocketeos.R.id.spring;

/**
 * 首页
 */
public class HomeFragment extends BaseFragment<HomeView, HomePresenter> implements HomeView, MyScrollview.ScrollViewListener {


    @BindView(R.id.iv_seting)
    ImageView mIvSeting;
    @BindView(R.id.iv_message)
    ImageView mIvMessage;
    @BindView(R.id.iv_scan)
    ImageView mIvScan;
    @BindView(R.id.transfer_accounts)
    TextView mTransferAccounts;
    @BindView(R.id.make_collections)
    TextView mMakeCollections;
    @BindView(R.id.red_packet)
    TextView mRedPacket;

    @BindView(R.id.home_title)
    LinearLayout mHomeTitle;
    @BindView(R.id.home_title1)
    ImageView mHomeTitle1;
    @BindView(R.id.home_title2)
    ImageView mHomeTitle2;
    @BindView(R.id.home_title3)
    ImageView mHomeTitle3;
    @BindView(R.id.scrollView)
    MyScrollview mScrollView;
    @BindView(R.id.title)
    RelativeLayout mTitle;
    @BindView(R.id.cardview)
    CardView mCardview;
    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.user_img)
    RoundImageView mUserImg;
    @BindView(R.id.user_name)
    TextView mUserName;
    @BindView(R.id.user_account_number)
    TextView mUserAccountNumber;
    @BindView(R.id.switch_number)
    TextView mSwitchNumber;
    @BindView(R.id.all_money)
    TextView mAllMoney;
    @BindView(R.id.user_all_property)
    TextView mUserAllProperty;
    @BindView(R.id.property_tendency)
    TextView mPropertyTendency;
    @BindView(R.id.recycle_icon)
    RecyclerView mRecycleIcon;
    @BindView(spring)
    SpringView mSpring;
    @BindView(R.id.notice)
    ScrollText mNotice;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    Unbinder unbinder;

    private Openleft mOpenleft = null;
    private TranslateAnimation mShowAction, mHiddenAction;
    private int topToCardView;//到CardView的距离
    private int showNumber;
    private int hintNumber;
    private List<AccountWithCoinBean> mAccountWithCoinBeen = new ArrayList<>();
    private CommonAdapter mCoinAdapter;


    private String openMoney = null;
    private Boolean isOpen = true;
    private Boolean isfromWithData = false;

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && mImmersionBar != null) {
            mImmersionBar.statusBarDarkFont(false, 0f).init();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 200) {
            isfromWithData = true;
            mUserAccountNumber.setText(data.getExtras().getString("account"));
            presenter.getAccountDetailsData(data.getExtras().getString("account"));
        } else if (requestCode == 100 && resultCode == 300) {
            isfromWithData = true;
            mUserAccountNumber.setText(data.getExtras().getString("account"));
            presenter.getAccountDetailsData(data.getExtras().getString("account"));
        } else {
            isfromWithData = false;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        mOpenleft = (Openleft) activity;
        super.onAttach(activity);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
            BigDecimal eosToCny = BigDecimal.valueOf(Double.parseDouble(accountWithCoinBeens.get(0).getCoinForCny()));
            BigDecimal octToCny = BigDecimal.valueOf(Double.parseDouble(accountWithCoinBeens.get(1).getCoinForCny()));
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
                mIvMessage.startAnimation(mShowAction);
                mName.startAnimation(mShowAction);
                mIvScan.startAnimation(mShowAction);
            }
            mHomeTitle.setVisibility(View.GONE);
            mIvSeting.setVisibility(View.VISIBLE);
            mIvMessage.setVisibility(View.GONE);
            mName.setVisibility(View.VISIBLE);
            mIvScan.setVisibility(View.VISIBLE);
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
                mIvMessage.startAnimation(mHiddenAction);
                mIvScan.startAnimation(mHiddenAction);
                mName.startAnimation(mHiddenAction);
            }
            mHomeTitle.setVisibility(View.VISIBLE);
            mIvSeting.setVisibility(View.GONE);
            mIvMessage.setVisibility(View.GONE);
            mName.setVisibility(View.GONE);
            mIvScan.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.iv_message, R.id.iv_scan, R.id.transfer_accounts, R.id.make_collections, R.id.red_packet})
    public void onViewClicked(View view) {
        final Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.iv_message:
                break;
            case R.id.iv_scan:
                List<PermissionItem> permissonItems = new ArrayList<PermissionItem>();
                permissonItems.add(new PermissionItem(Manifest.permission.CAMERA, getString(R.string.camer), R.drawable.permission_ic_camera));
                if (Utils.getPermissions(permissonItems, getString(R.string.open_camer_scan))) {
                    bundle.putString("from", "home");
                    ActivityUtils.next(getActivity(), ScanCodeActivity.class, bundle);
                }
                break;
            case R.id.transfer_accounts:
                bundle.putString("account", mUserAccountNumber.getText().toString().trim().toString());
                bundle.putString("coin", "EOS");
                bundle.putString("from", "home");
                ActivityUtils.next(getActivity(), TransferAccountsActivity.class, bundle, 100);
                break;
            case R.id.make_collections:
                bundle.putString("account", mUserAccountNumber.getText().toString().trim().toString());
                bundle.putString("coin", "EOS");
                ActivityUtils.next(getActivity(), MakeCollectionsActivity.class, bundle);
                break;
            case R.id.red_packet:
                bundle.putString("account", mUserAccountNumber.getText().toString().trim().toString());
                bundle.putString("coin", "EOS");
                ActivityUtils.next(getActivity(), RedPacketActivity.class, bundle);
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mUserName.setText(MyApplication.getInstance().getUserBean().getWallet_name() + getString(R.string.wallet));
        MyApplication.getInstance().showCirImage(MyApplication.getInstance().getUserBean().getWallet_img(), mUserImg);
        if (!isfromWithData) {
            presenter.getAccountDetailsData(mUserAccountNumber.getText().toString().trim());
        }
    }

    @Override
    public HomePresenter initPresenter() {
        return new HomePresenter(getActivity());
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
                presenter.getAccountDetailsData(mUserAccountNumber.getText().toString().trim().toString());
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

        mUserName.setText(MyApplication.getInstance().getUserBean().getWallet_name() + "的钱包");
        mUserAccountNumber.setText(MyApplication.getInstance().getUserBean().getWallet_main_account());
        MyApplication.getInstance().showCirImage(MyApplication.getInstance().getUserBean().getWallet_img(), mUserImg);
        //开启广告位
        AdvertisingDialog advertisingDialog = new AdvertisingDialog(getActivity(), new AdvertisingCallback() {
            @Override
            public void callback() {
                ActivityUtils.next(getActivity(), NodeVoteActivity.class);
            }
        });
        advertisingDialog.setImg("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1528435930664&di=ac9ecd64a769ee194efea7034b1223db&imgtype=0&src=http%3A%2F%2Ff.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Fb58f8c5494eef01f2fe05953ecfe9925bd317dab.jpg");
        advertisingDialog.show();
    }

    @Override
    protected void initData() {
        ShowDialog.showDialog(getActivity(), "", true, null);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        layoutManager.setSmoothScrollbarEnabled(true);
        mRecycleIcon.setLayoutManager(layoutManager);
        mCoinAdapter = AdapterManger.getCoinAdapter(getActivity(), mAccountWithCoinBeen);
        mCoinAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("coin", mAccountWithCoinBeen.get(position));
                bundle.putString("account", mUserAccountNumber.getText().toString().trim().toString());
                ActivityUtils.next(getActivity(), CoinDetailsActivity.class, bundle);
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
        mIvSeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOpenleft.open(1);
            }
        });
        mScrollView.setScrollViewListener(this);
        Bundle bundle = new Bundle();
        mSwitchNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("account", mUserAccountNumber.getText().toString().trim().toString());
                bundle.putString("from", "home");
                ActivityUtils.next(getActivity(), SwitchUserNumberActivity.class, bundle, 100);
             /*   StringBuilder sb = new StringBuilder();
                byte[] entropy = new byte[Words.TWELVE.byteLength()];
                new SecureRandom().nextBytes(entropy);
                new MnemonicGenerator(English.INSTANCE)
                        .createMnemonic(entropy, sb::append);
                System.out.println(sb.toString());
                byte[] seed = new SeedCalculator().calculateSeed(sb.toString(), "");//种子*/
            }
        });
        mHomeTitle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("account", mUserAccountNumber.getText().toString().trim().toString());
                bundle.putString("coin", "EOS");
                bundle.putString("from", "home");
                ActivityUtils.next(getActivity(), TransferAccountsActivity.class, bundle);
            }
        });
        mHomeTitle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("account", mUserAccountNumber.getText().toString().trim().toString());
                bundle.putString("coin", "EOS");
                ActivityUtils.next(getActivity(), MakeCollectionsActivity.class, bundle);
            }
        });
        mHomeTitle3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("account", mUserAccountNumber.getText().toString().trim().toString());
                bundle.putString("coin", "EOS");
                ActivityUtils.next(getActivity(), RedPacketActivity.class);
            }
        });
        mUserImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.next(getActivity(), UserCenterActivity.class);
            }
        });
        mUserAccountNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<AccountInfoBean> mAccountInfoBeanList = JsonUtil.parseJsonToArrayList(MyApplication.getInstance().getUserBean().getAccount_info(), AccountInfoBean.class);
                for (AccountInfoBean accountInfoBean : mAccountInfoBeanList) {
                    if (accountInfoBean.getAccount_name().equals(mUserAccountNumber.getText().toString())) {
                        bundle.putParcelable("bean", accountInfoBean);
                        ActivityUtils.next(getActivity(), AccountDetailsActivity.class, bundle);
                    }
                }
            }
        });
        mAllMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });

        mFab.setOnClickListener(new View.OnClickListener() {//解除质押
            @Override
            public void onClick(View v) {
                bundle.putString("account", mUserAccountNumber.getText().toString().trim().toString());
                ActivityUtils.next(getActivity(), UnStakeActivity.class ,bundle);
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
    protected int getContentViewLayoutID() {
        return R.layout.fragment_home;
    }

    public interface Openleft {
        void open(int tag);
    }
}
