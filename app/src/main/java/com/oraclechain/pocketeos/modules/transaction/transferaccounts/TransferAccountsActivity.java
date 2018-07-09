package com.oraclechain.pocketeos.modules.transaction.transferaccounts;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.liaoinstan.springview.container.AliFooter;
import com.liaoinstan.springview.widget.SpringView;
import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.adapter.AdapterManger;
import com.oraclechain.pocketeos.adapter.baseadapter.CommonAdapter;
import com.oraclechain.pocketeos.adapter.baseadapter.MultiItemTypeAdapter;
import com.oraclechain.pocketeos.adapter.baseadapter.wrapper.EmptyWrapper;
import com.oraclechain.pocketeos.app.ActivityUtils;
import com.oraclechain.pocketeos.app.MyApplication;
import com.oraclechain.pocketeos.base.BaseAcitvity;
import com.oraclechain.pocketeos.bean.AccountDetailsBean;
import com.oraclechain.pocketeos.bean.AccountInfoBean;
import com.oraclechain.pocketeos.bean.AccountWithCoinBean;
import com.oraclechain.pocketeos.bean.CoinRateBean;
import com.oraclechain.pocketeos.bean.PostChainHistoryBean;
import com.oraclechain.pocketeos.bean.QrCodeMakeCollectionBean;
import com.oraclechain.pocketeos.bean.TransferEosMessageBean;
import com.oraclechain.pocketeos.bean.TransferHistoryBean;
import com.oraclechain.pocketeos.blockchain.EosDataManger;
import com.oraclechain.pocketeos.modules.scancode.ScanCodeActivity;
import com.oraclechain.pocketeos.modules.transaction.transferaccounts.switchfriend.SwitchFriendActivity;
import com.oraclechain.pocketeos.utils.AndroidBug5497Workaround;
import com.oraclechain.pocketeos.utils.BigDecimalUtil;
import com.oraclechain.pocketeos.utils.JsonUtil;
import com.oraclechain.pocketeos.utils.KeyBoardUtil;
import com.oraclechain.pocketeos.utils.PasswordToKeyUtils;
import com.oraclechain.pocketeos.utils.RotateUtils;
import com.oraclechain.pocketeos.utils.StringUtils;
import com.oraclechain.pocketeos.utils.Utils;
import com.oraclechain.pocketeos.view.ClearEditText;
import com.oraclechain.pocketeos.view.RecycleViewDivider;
import com.oraclechain.pocketeos.view.dialog.passworddialog.PasswordCallback;
import com.oraclechain.pocketeos.view.dialog.passworddialog.PasswordDialog;
import com.oraclechain.pocketeos.view.popupwindow.BasePopupWindow;
import com.oraclechain.pocketeos.view.textwatcher.TransferMoneyTextWatcher;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.ljp.permission.PermissionItem;

import static com.oraclechain.pocketeos.utils.Utils.getContext;

//资产转账页面
public class TransferAccountsActivity extends BaseAcitvity<TransferAccountsView, TransferAccountsPresenter> implements TransferAccountsView {


    @BindView(R.id.title)
    RelativeLayout mTitle;
    @BindView(R.id.img_right)
    ImageView mImgRight;
    @BindView(R.id.switch_number)
    TextView mSwitchNumber;
    @BindView(R.id.look_number)
    ImageView mLookNumber;
    @BindView(R.id.switch_property)
    TextView mSwitchProperty;
    @BindView(R.id.look_property)
    ImageView mLookProperty;
    @BindView(R.id.property_person)
    ClearEditText mPropertyPerson;
    @BindView(R.id.go_friends_list)
    ImageView mGoFriendsList;
    @BindView(R.id.take_property_number)
    ClearEditText mTakePropertyNumber;
    @BindView(R.id.can_use_property)
    TextView mCanUseProperty;
    @BindView(R.id.rmb_property)
    TextView mRmbProperty;
    @BindView(R.id.take_rmb_property)
    TextView mTakeRmbProperty;
    @BindView(R.id.go_transfer_accounts)
    Button mGoTransferAccounts;
    @BindView(R.id.recycle_transferaccounts_history)
    RecyclerView mRecycleTransferaccountsHistory;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.spring)
    SpringView mSpring;
    @BindView(R.id.leave_message)
    ClearEditText mLeaveMessage;

    Boolean isSHow = false;
    BasePopupWindow basePopupWindow;
    Boolean isSHow1 = false;
    BasePopupWindow basePopupWindow1;
    private List<AccountInfoBean> mAccountInfoBeanList = new ArrayList<>();
    private List<String> mCoinList = new ArrayList<>();

    private List<TransferHistoryBean.DataBeanX.ActionsBean> mDataBeanList = new ArrayList<>();//交易历史
    private EmptyWrapper mHistoryAdapter;
    private int size = 10; //每页加载的数量
    private int page = 0; //页数
    private PostChainHistoryBean mPostChainHistoryBean = new PostChainHistoryBean();


    private AccountWithCoinBean eos, oct;//选择账号之后重新获取最新数据信息
    private BigDecimal coinRate;//资产汇率
    private String userPassword = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_transfer_accounts;
    }

    @Override
    public TransferAccountsPresenter initPresenter() {
        return new TransferAccountsPresenter(TransferAccountsActivity.this);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        AndroidBug5497Workaround.assistActivity(activity);

        mSwitchNumber.setText(getIntent().getStringExtra("account"));
        mSwitchProperty.setText(getIntent().getStringExtra("coin"));

        if (getIntent().getStringExtra("from").equals("qrcode")) {
            QrCodeMakeCollectionBean qrCodeMakeCollectionBean = getIntent().getParcelableExtra("info");
            mPropertyPerson.setText(qrCodeMakeCollectionBean.getAccount_name());
            mPropertyPerson.setSelection(mPropertyPerson.getText().length());
            mTakePropertyNumber.setText(qrCodeMakeCollectionBean.getMoney());
            mSwitchProperty.setText(qrCodeMakeCollectionBean.getCoin());
            mGoTransferAccounts.setBackgroundColor(getResources().getColor(R.color.theme_color));
        } else if (getIntent().getStringExtra("from").equals("frienddetails")) {
            mPropertyPerson.setText(getIntent().getStringExtra("getmoneyperson"));
            mPropertyPerson.setSelection(mPropertyPerson.getText().length());
        }

        setCenterTitle(getString(R.string.title_transfer_accounts));
        if (Utils.getSpUtils().getString("loginmode", "").equals("phone")) {
            setRightImg(true);
        } else {
            setRightImg(false);
        }
        mImgRight.setImageDrawable(getResources().getDrawable(R.mipmap.scan));


        LinearLayoutManager layoutManager = new LinearLayoutManager(TransferAccountsActivity.this, LinearLayoutManager.VERTICAL, false);
        layoutManager.setSmoothScrollbarEnabled(true);
        mRecycleTransferaccountsHistory.setLayoutManager(layoutManager);
        if (Utils.getSpUtils().getString("loginmode", "").equals("phone")) {
            mRecycleTransferaccountsHistory.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL, 1, getResources().getColor(R.color.line)));
        } else {
            mRecycleTransferaccountsHistory.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL, 1, getResources().getColor(R.color.blackbox_line)));
        }

        //系统刷新
        mSpring.setFooter(new AliFooter(getContext()));
        mSpring.setGive(SpringView.Give.BOTTOM);
        mSpring.setType(SpringView.Type.FOLLOW);
        mSpring.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                mSpring.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                mPostChainHistoryBean.setPage(page);
                presenter.getTransferHistoryData(mPostChainHistoryBean);
            }
        });

    }

    @Override
    protected void initData() {
        mCoinList.add("EOS");
        mCoinList.add("OCT");
        showProgress();
        mAccountInfoBeanList = JsonUtil.parseJsonToArrayList(MyApplication.getInstance().getUserBean().getAccount_info(), AccountInfoBean.class);

        presenter.getAccountDetailsData(getIntent().getStringExtra("account"));

        if (mSwitchProperty.getText().toString().equals("OCT")) {
            presenter.getCoinRateData("oraclechain");
        } else {
            presenter.getCoinRateData("eos");
        }


        mPostChainHistoryBean.setFrom(mSwitchNumber.getText().toString());
        mPostChainHistoryBean.setTo(mSwitchNumber.getText().toString());
        mPostChainHistoryBean.setPage(page);
        mPostChainHistoryBean.setPageSize(size);
        List<PostChainHistoryBean.SymbolsBean> symbolsBeans = new ArrayList<>();
        PostChainHistoryBean.SymbolsBean symbolsBeanEos = new PostChainHistoryBean.SymbolsBean();
        symbolsBeanEos.setSymbolName("EOS");
        symbolsBeanEos.setContractName(com.oraclechain.pocketeos.base.Constants.EOSCONTRACT);
        PostChainHistoryBean.SymbolsBean symbolsBeanOCT = new PostChainHistoryBean.SymbolsBean();
        symbolsBeanOCT.setSymbolName("OCT");
        symbolsBeanOCT.setContractName(com.oraclechain.pocketeos.base.Constants.OCTCONTRACT);
        symbolsBeans.add(symbolsBeanEos);
        symbolsBeans.add(symbolsBeanOCT);
        mPostChainHistoryBean.setSymbols(symbolsBeans);
        presenter.getTransferHistoryData(mPostChainHistoryBean);


        mHistoryAdapter = new EmptyWrapper(AdapterManger.getTransferHistoryAdapter(this, mDataBeanList));
        mHistoryAdapter.setEmptyView(R.layout.empty_project);
        mRecycleTransferaccountsHistory.setAdapter(mHistoryAdapter);
    }

    @Override
    public void initEvent() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideProgress();
        if (basePopupWindow != null) {
            basePopupWindow.dismiss();
        }
        if (basePopupWindow1 != null) {
            basePopupWindow1.dismiss();
        }

    }

    @Override
    public void getCoinRateDataHttp(CoinRateBean.DataBean coinRateBean) {//获取资产汇率
        coinRate = coinRateBean.getPrice_cny();
        mTakePropertyNumber.addTextChangedListener(new TransferMoneyTextWatcher(mTakePropertyNumber, mTakeRmbProperty, coinRate, mPropertyPerson, mGoTransferAccounts));//限制金额最多为小数点后面四位
        if (mTakePropertyNumber.getText().toString().trim().length() != 0) {
            mTakeRmbProperty.setText("≈" + StringUtils.addComma(BigDecimalUtil.multiply(BigDecimal.valueOf(Double.parseDouble(mTakePropertyNumber.getText().toString())), coinRate, 4) + "") + "CNY");
        }
    }

    @Override
    public void getAccountDetailsDataHttp(AccountDetailsBean accountDetailsBean) {
        mSpring.onFinishFreshAndLoad();
        if (mSwitchProperty.getText().toString().equals("OCT")) {
            mCanUseProperty.setText(StringUtils.addComma(accountDetailsBean.getOct_balance()) + " OCT");
            mRmbProperty.setText("≈" + StringUtils.addComma(accountDetailsBean.getOct_balance_cny()) + " CNY");
        } else {
            mCanUseProperty.setText(StringUtils.addComma(accountDetailsBean.getEos_balance()) + " EOS");
            mRmbProperty.setText("≈" + StringUtils.addComma(accountDetailsBean.getEos_balance_cny()) + " CNY");
        }
        eos = new AccountWithCoinBean();
        eos.setCoinName("EOS");
        eos.setCoinForCny(StringUtils.addComma(accountDetailsBean.getEos_balance_cny()) + " CNY");
        eos.setCoinNumber(StringUtils.addComma(accountDetailsBean.getEos_balance()) + " EOS");
        eos.setCoinImg(accountDetailsBean.getAccount_icon());
        if (accountDetailsBean.getEos_price_change_in_24h().contains("-")) {
            eos.setCoinUpsAndDowns(accountDetailsBean.getEos_price_change_in_24h() + "%");
        } else {
            eos.setCoinUpsAndDowns("+" + accountDetailsBean.getEos_price_change_in_24h() + "%");
        }

        oct = new AccountWithCoinBean();
        oct.setCoinName("OCT");
        oct.setCoinForCny(StringUtils.addComma(accountDetailsBean.getOct_balance_cny()) + " CNY");
        oct.setCoinNumber(StringUtils.addComma(accountDetailsBean.getOct_balance()) + " OCT");
        oct.setCoinImg(accountDetailsBean.getAccount_icon());
        if (accountDetailsBean.getOct_price_change_in_24h().contains("-")) {
            oct.setCoinUpsAndDowns(accountDetailsBean.getOct_price_change_in_24h() + "%");
        } else {
            oct.setCoinUpsAndDowns("+" + accountDetailsBean.getOct_price_change_in_24h() + "%");
        }
    }

    @Override
    public void getTransferHistoryDataHttp(TransferHistoryBean.DataBeanX transferHistoryBean) {
        mSpring.onFinishFreshAndLoad();
        hideProgress();
        page += 1;
        for (int i = 0; i < transferHistoryBean.getActions().size(); i++) {
            if (transferHistoryBean.getActions().get(i).getDoc().getName().equals("transfer")) {
                if (transferHistoryBean.getActions().get(i).getDoc().getData().getFrom().equals(mSwitchNumber.getText().toString().trim())
                        && transferHistoryBean.getActions().get(i).getDoc().getData().getQuantity().contains(mSwitchProperty.getText().toString().trim())) {
                    if (!transferHistoryBean.getActions().get(i).getDoc().getData().getTo().equals("oc.redpacket")) {
                        TransferHistoryBean.DataBeanX.ActionsBean itemdata = transferHistoryBean.getActions().get(i);
                        mDataBeanList.add(itemdata);
                    }
                }
            }
        }
        mHistoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void getDataHttpFail(String msg) {
        mSpring.onFinishFreshAndLoad();
        hideProgress();
        toast(msg);
    }

    @OnClick({R.id.img_right, R.id.switch_number, R.id.switch_property, R.id.go_friends_list, R.id.go_transfer_accounts})
    public void onViewClicked(View view) {
        if (KeyBoardUtil.isSoftInputShow(this)) {
            KeyBoardUtil.getInstance(this).hide();
        }
        switch (view.getId()) {
            case R.id.img_right:
                List<PermissionItem> permissonItems = new ArrayList<PermissionItem>();
                permissonItems.add(new PermissionItem(Manifest.permission.CAMERA, getString(R.string.camer), R.drawable.permission_ic_camera));
                if (Utils.getPermissions(permissonItems, getString(R.string.open_camer_scan))) {
                    Bundle bundle = new Bundle();
                    bundle.putString("from", "import_account");
                    ActivityUtils.next(TransferAccountsActivity.this, ScanCodeActivity.class, bundle, 100);
                }
                break;
            case R.id.switch_number:
                isSHow = !isSHow;
                RotateUtils.rotateArrow(mLookNumber, isSHow);
                if (basePopupWindow != null && basePopupWindow.isShowing()) {
                    basePopupWindow.dismiss();
                } else {
                    basePopupWindow = new BasePopupWindow.Builder(this).
                            setView(LayoutInflater.from(this).inflate(R.layout.popuwindow_exchange_type, null))
                            .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                            .setOutsideTouchable(false)
                            .setAnimationStyle(R.style.AnimDown)
                            .create();
                    basePopupWindow.showAsDropDown(mSwitchNumber);
                    isSHow = basePopupWindow.setAccountData(this, mAccountInfoBeanList, mSwitchNumber.getText().toString().toString().trim(), mLookNumber, isSHow);
                    CommonAdapter commonAdapter = (CommonAdapter) ((RecyclerView) basePopupWindow.getContentView().findViewById(R.id.exchange_two_type)).getAdapter();
                    commonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                            basePopupWindow.dismiss();
                            mSwitchNumber.setText(mAccountInfoBeanList.get(position).getAccount_name());

                            showProgress();
                            presenter.getAccountDetailsData(mAccountInfoBeanList.get(position).getAccount_name());
                            page = 0;
                            mPostChainHistoryBean.setTo(mSwitchNumber.getText().toString());
                            mPostChainHistoryBean.setFrom(mSwitchNumber.getText().toString());
                            mPostChainHistoryBean.setPage(page);
                            mDataBeanList.clear();
                            presenter.getTransferHistoryData(mPostChainHistoryBean);
                            mHistoryAdapter = new EmptyWrapper(AdapterManger.getTransferHistoryAdapter(TransferAccountsActivity.this, mDataBeanList));
                            mHistoryAdapter.setEmptyView(R.layout.empty_project);
                            mRecycleTransferaccountsHistory.setAdapter(mHistoryAdapter);
                            isSHow = !isSHow;
                            RotateUtils.rotateArrow(mLookNumber, isSHow);
                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                            return false;
                        }
                    });
                }
                break;
            case R.id.switch_property:
                isSHow1 = !isSHow1;
                RotateUtils.rotateArrow(mLookProperty, isSHow1);
                if (basePopupWindow1 != null && basePopupWindow1.isShowing()) {
                    basePopupWindow1.dismiss();
                } else {
                    basePopupWindow1 = new BasePopupWindow.Builder(this).
                            setView(LayoutInflater.from(this).inflate(R.layout.popuwindow_exchange_type, null))
                            .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                            .setOutsideTouchable(false)
                            .setAnimationStyle(R.style.AnimDown)
                            .create();
                    basePopupWindow1.showAsDropDown(mSwitchProperty);
                    isSHow1 = basePopupWindow1.setCoinData(this, mCoinList, mSwitchProperty.getText().toString().toString().trim(), mLookProperty, isSHow1);
                    CommonAdapter commonAdapter = (CommonAdapter) ((RecyclerView) basePopupWindow1.getContentView().findViewById(R.id.exchange_two_type)).getAdapter();
                    commonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                            basePopupWindow1.dismiss();
                            mSwitchProperty.setText(mCoinList.get(position));

                            showProgress();
                            page = 0;
                            mPostChainHistoryBean.setTo(mSwitchNumber.getText().toString());
                            mPostChainHistoryBean.setFrom(mSwitchNumber.getText().toString());
                            mPostChainHistoryBean.setPage(page);
                            mDataBeanList.clear();
                            presenter.getTransferHistoryData(mPostChainHistoryBean);
                            mHistoryAdapter = new EmptyWrapper(AdapterManger.getTransferHistoryAdapter(TransferAccountsActivity.this, mDataBeanList));
                            mHistoryAdapter.setEmptyView(R.layout.empty_project);
                            mRecycleTransferaccountsHistory.setAdapter(mHistoryAdapter);

                            if (mSwitchProperty.getText().toString().equals("OCT")) {
                                mCanUseProperty.setText(oct.getCoinNumber());
                                mRmbProperty.setText("≈" + oct.getCoinForCny());
                                presenter.getCoinRateData("oraclechain");
                            } else if (mSwitchProperty.getText().toString().equals("EOS")) {
                                mCanUseProperty.setText(eos.getCoinNumber());
                                mRmbProperty.setText("≈" + eos.getCoinForCny());
                                presenter.getCoinRateData("eos");
                            }
                            isSHow1 = !isSHow1;
                            RotateUtils.rotateArrow(mLookProperty, isSHow1);
                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                            return false;
                        }
                    });
                }
                break;
            case R.id.go_friends_list:
                Bundle bundle = new Bundle();
                bundle.putString("account", mPropertyPerson.getText().toString());
                ActivityUtils.next(TransferAccountsActivity.this, SwitchFriendActivity.class, bundle, 100);
                break;
            case R.id.go_transfer_accounts:
                if (!TextUtils.isEmpty(mPropertyPerson.getText().toString().trim()) && !TextUtils.isEmpty(mTakePropertyNumber.getText().toString().trim())) {
                    PasswordDialog mPasswordDialog = new PasswordDialog(TransferAccountsActivity.this, new PasswordCallback() {
                        @Override
                        public void sure(final String password) {
                            if (MyApplication.getInstance().getUserBean().getWallet_shapwd().equals(PasswordToKeyUtils.shaCheck(password))) {
                                userPassword = password;
                                showProgress();
                                if (mSwitchProperty.getText().toString().equals("EOS")) {
                                    new EosDataManger(TransferAccountsActivity.this, userPassword).setCoinRate(coinRate).pushAction(
                                            new Gson().toJson(new TransferEosMessageBean(mLeaveMessage.getText().toString().trim()
                                                    , mPropertyPerson.getText().toString().trim(),
                                                    StringUtils.addZero(mTakePropertyNumber.getText().toString().trim()) + " " + mSwitchProperty.getText().toString().trim(),
                                                    mSwitchNumber.getText().toString().trim())),
                                            mSwitchNumber.getText().toString().trim());

                                } else {
                                    new EosDataManger(TransferAccountsActivity.this, userPassword).setCoinRate(coinRate).pushAction(
                                            new Gson().toJson(new TransferEosMessageBean(mLeaveMessage.getText().toString().trim()
                                                    , mPropertyPerson.getText().toString().trim(),
                                                    StringUtils.addZero(mTakePropertyNumber.getText().toString().trim()) + " " + mSwitchProperty.getText().toString().trim(),
                                                    mSwitchNumber.getText().toString().trim())),
                                            mSwitchNumber.getText().toString().trim());
                                }
                            } else {
                                toast(getResources().getString(R.string.password_error));
                            }
                        }

                        @Override
                        public void cancle() {
                        }
                    });
                    mPasswordDialog.setCancelable(true);
                    mPasswordDialog.show();
                } else if (TextUtils.isEmpty(mPropertyPerson.getText().toString().trim()) && !TextUtils.isEmpty(mTakePropertyNumber.getText().toString().trim())) {
                    toast(getString(R.string.input_propertyperson_info));
                } else if (!TextUtils.isEmpty(mTakePropertyNumber.getText().toString().trim()) && TextUtils.isEmpty(mPropertyPerson.getText().toString().trim())) {
                    toast(getString(R.string.input_property_number_info));
                } else {
                    toast(getString(R.string.input_transfer_all_info));
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 200) {
            mPropertyPerson.setText(data.getStringExtra("account"));
            mPropertyPerson.setSelection(mPropertyPerson.getText().length());
        }
        if (requestCode == 100 && resultCode == 300) {
            mPropertyPerson.setText(data.getStringExtra("account"));
            mPropertyPerson.setSelection(mPropertyPerson.getText().length());
            mTakePropertyNumber.setText(data.getStringExtra("money"));
            mSwitchProperty.setText(data.getStringExtra("coin"));

            if (data.getStringExtra("coin").equals("EOS")) {
                mCanUseProperty.setText(eos.getCoinNumber());
                mRmbProperty.setText("≈" + eos.getCoinForCny());
                presenter.getCoinRateData("eos");
            } else {
                mCanUseProperty.setText(oct.getCoinNumber());
                mRmbProperty.setText("≈" + oct.getCoinForCny());
                presenter.getCoinRateData("oraclechain");
            }
        }
    }

}
