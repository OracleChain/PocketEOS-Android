package com.oraclechain.pocketeos.modules.transaction.redpacket.makeredpacket;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.widget.SpringView;
import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.adapter.AdapterManger;
import com.oraclechain.pocketeos.adapter.baseadapter.CommonAdapter;
import com.oraclechain.pocketeos.adapter.baseadapter.MultiItemTypeAdapter;
import com.oraclechain.pocketeos.adapter.baseadapter.wrapper.EmptyWrapper;
import com.oraclechain.pocketeos.app.MyApplication;
import com.oraclechain.pocketeos.base.BaseAcitvity;
import com.oraclechain.pocketeos.bean.AccountInfoBean;
import com.oraclechain.pocketeos.bean.CoinRateBean;
import com.oraclechain.pocketeos.bean.RedPacketHistoryBean;
import com.oraclechain.pocketeos.bean.SendRedPacketBean;
import com.oraclechain.pocketeos.bean.TransferEosMessageBean;
import com.oraclechain.pocketeos.blockchain.EosDataManger;
import com.oraclechain.pocketeos.utils.AndroidBug5497Workaround;
import com.oraclechain.pocketeos.utils.BigDecimalUtil;
import com.oraclechain.pocketeos.utils.JsonUtil;
import com.oraclechain.pocketeos.utils.KeyBoardUtil;
import com.oraclechain.pocketeos.utils.PasswordToKeyUtils;
import com.oraclechain.pocketeos.utils.RotateUtils;
import com.oraclechain.pocketeos.utils.StringUtils;
import com.oraclechain.pocketeos.view.ClearEditText;
import com.oraclechain.pocketeos.view.RecycleViewDivider;
import com.oraclechain.pocketeos.view.dialog.passworddialog.PasswordCallback;
import com.oraclechain.pocketeos.view.dialog.passworddialog.PasswordDialog;
import com.oraclechain.pocketeos.view.popupwindow.BasePopupWindow;
import com.oraclechain.pocketeos.view.textwatcher.MakeRedPacketMoneyTextWatcher;
import com.oraclechain.pocketeos.view.textwatcher.MakeRedPacketNumberTextWatcher;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.oraclechain.pocketeos.utils.Utils.getContext;

//发红包
public class RedPacketActivity extends BaseAcitvity<RedPacketView, RedPacketPresenter> implements RedPacketView {

    @BindView(R.id.switch_number)
    TextView mSwitchNumber;
    @BindView(R.id.look_number)
    ImageView mLookNumber;
    @BindView(R.id.switch_property)
    TextView mSwitchProperty;
    @BindView(R.id.look_property)
    ImageView mLookProperty;
    @BindView(R.id.red_packet_money)
    ClearEditText mRedPacketMoney;
    @BindView(R.id.red_packet_number)
    ClearEditText mRedPacketNumber;
    @BindView(R.id.leave_message)
    ClearEditText mLeaveMessage;
    @BindView(R.id.take_rmb_property)
    TextView mTakeRmbProperty;
    @BindView(R.id.go_transfer_accounts)
    Button mGoTransferAccounts;
    @BindView(R.id.recycle_redpacket_history)
    RecyclerView mRecycleRedpacketHistory;
    @BindView(R.id.spring)
    SpringView mSpring;
    @BindView(R.id.iv_back)
    ImageView mIvBack;

    Boolean isSHow = false;
    BasePopupWindow basePopupWindow;
    Boolean isSHow1 = false;
    BasePopupWindow basePopupWindow1;
    private List<AccountInfoBean> mAccountInfoBeanList = new ArrayList<>();
    private List<String> mCoinList = new ArrayList<>();


    private BigDecimal coinRate;//资产汇率

    private List<RedPacketHistoryBean.DataBean> mDataBeanList = new ArrayList<>();//交易历史
    private EmptyWrapper mHistoryAdapter;


    private String userPassword = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_red_packet;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        AndroidBug5497Workaround.assistActivity(activity);

        setCenterTitle(getString(R.string.red_packet_title));
        setRightImg(false);

        mRedPacketNumber.addTextChangedListener(new MakeRedPacketNumberTextWatcher(mRedPacketNumber, mRedPacketMoney, mGoTransferAccounts));//检测不为空


        mAccountInfoBeanList = JsonUtil.parseJsonToArrayList(MyApplication.getInstance().getUserBean().getAccount_info(), AccountInfoBean.class);
        mSwitchNumber.setText(getIntent().getStringExtra("account"));
        mSwitchProperty.setText(getIntent().getStringExtra("coin"));//默认选择EOS


        LinearLayoutManager layoutManager = new LinearLayoutManager(RedPacketActivity.this, LinearLayoutManager.VERTICAL, false);
        layoutManager.setSmoothScrollbarEnabled(true);
        mRecycleRedpacketHistory.setLayoutManager(layoutManager);
        mRecycleRedpacketHistory.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL, 1, getResources().getColor(R.color.line)));

        //系统刷新
        mSpring.setEnable(false);
        mSpring.setFooter(new DefaultFooter(getContext()));
        mSpring.setGive(SpringView.Give.BOTTOM);
        mSpring.setType(SpringView.Type.FOLLOW);
        mSpring.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                mSpring.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                mSpring.onFinishFreshAndLoad();
            }
        });


    }

    @Override
    protected void initData() {
        mCoinList.add("EOS");
        mCoinList.add("OCT");
        showProgress();

        if (mSwitchProperty.getText().toString().equals("OCT")) {
            presenter.getCoinRateData("oraclechain");
        } else {
            presenter.getCoinRateData("eos");
        }


    }

    @Override
    public void initEvent() {
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDataBeanList.clear();
        presenter.getRedPacketHistoryData(mSwitchNumber.getText().toString().trim(), mSwitchProperty.getText().toString().trim());
    }

    @Override
    public RedPacketPresenter initPresenter() {
        return new RedPacketPresenter(RedPacketActivity.this);
    }

    @Override
    public void getCoinRateDataHttp(CoinRateBean.DataBean coinRateBean) {
        hideProgress();
        coinRate = coinRateBean.getPrice_cny();
        mRedPacketMoney.addTextChangedListener(new MakeRedPacketMoneyTextWatcher(mRedPacketMoney, mTakeRmbProperty, coinRate, mGoTransferAccounts, mRedPacketNumber));//限制金额最多为小数点后面四位
        if (!TextUtils.isEmpty(mRedPacketMoney.getText().toString().trim())) {
            mTakeRmbProperty.setText("≈" + StringUtils.addComma(BigDecimalUtil.multiply(BigDecimal.valueOf(Double.parseDouble(mRedPacketMoney.getText().toString())), coinRate, 4) + "") + "CNY");
        }
    }

    @Override
    public void getRedPacketHistoryDataHttp(List<RedPacketHistoryBean.DataBean> dataBeanList) {
        hideProgress();
        for (RedPacketHistoryBean.DataBean dataBean : dataBeanList) {
            mDataBeanList.add(dataBean);
        }
        mHistoryAdapter = new EmptyWrapper(AdapterManger.getRedPacketHistoryAdapter(this, mDataBeanList, mSwitchNumber.getText().toString()));
        mHistoryAdapter.setEmptyView(R.layout.empty_project);

        mRecycleRedpacketHistory.setAdapter(mHistoryAdapter);
    }

    @Override
    public void sendRedPacketDataHttp(SendRedPacketBean.DataBean sendRedPacketBean) {

        if (mSwitchProperty.getText().toString().equals("EOS")) {
            new EosDataManger(RedPacketActivity.this, userPassword).setCoinRate(coinRate).setRedpacketInfo(sendRedPacketBean, mRedPacketNumber.getText().toString().trim())
                    .sendRedPacket(
                            new Gson().toJson(new TransferEosMessageBean(mLeaveMessage.getText().toString().trim()
                                    , "oc.redpacket",
                                    StringUtils.addZero(mRedPacketMoney.getText().toString().trim()) + " " + mSwitchProperty.getText().toString().trim(),
                                    mSwitchNumber.getText().toString().trim())),
                            mSwitchNumber.getText().toString().trim());
        } else {
            new EosDataManger(RedPacketActivity.this, userPassword).setRedpacketInfo(sendRedPacketBean, mRedPacketNumber.getText().toString().trim())
                    .sendRedPacket(
                            new Gson().toJson(new TransferEosMessageBean(mLeaveMessage.getText().toString().trim()
                                    , "oc.redpacket",
                                    StringUtils.addZero(mRedPacketMoney.getText().toString().trim()) + " " + mSwitchProperty.getText().toString().trim(),
                                    mSwitchNumber.getText().toString().trim())),
                            mSwitchNumber.getText().toString().trim())
            ;
        }

    }

    @Override
    public void getDataHttpFail(String msg) {
        hideProgress();
        toast(msg);
    }

    @OnClick({R.id.switch_number, R.id.switch_property, R.id.go_transfer_accounts})
    public void onViewClicked(View view) {
        if (KeyBoardUtil.isSoftInputShow(this)) {
            KeyBoardUtil.getInstance(this).hide();
        }
        switch (view.getId()) {
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

                            mDataBeanList.clear();
                            presenter.getRedPacketHistoryData(mSwitchNumber.getText().toString().trim(), mSwitchProperty.getText().toString().trim());
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
                            if (mSwitchProperty.getText().toString().equals("OCT")) {
                                presenter.getCoinRateData("oraclechain");
                            } else if (mSwitchProperty.getText().toString().equals("EOS")) {
                                presenter.getCoinRateData("eos");
                            }
                            mDataBeanList.clear();
                            presenter.getRedPacketHistoryData(mSwitchNumber.getText().toString().trim(), mSwitchProperty.getText().toString().trim());
                            if (isSHow1) {
                                isSHow1 = false;
                            } else {
                                isSHow1 = true;
                            }
                            RotateUtils.rotateArrow(mLookProperty, isSHow1);
                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                            return false;
                        }
                    });
                }
                break;
            case R.id.go_transfer_accounts:
                if (!TextUtils.isEmpty(mRedPacketMoney.getText().toString().trim()) && !TextUtils.isEmpty(mRedPacketNumber.getText().toString().trim())) {
                    PasswordDialog mPasswordDialog = new PasswordDialog(RedPacketActivity.this, new PasswordCallback() {
                        @Override
                        public void sure(final String password) {
                            if (MyApplication.getInstance().getUserBean().getWallet_shapwd().equals(PasswordToKeyUtils.shaCheck(password))) {
                                userPassword = password;
                                showProgress();
                                presenter.sendRedPacketData(mSwitchNumber.getText().toString().trim(), mRedPacketMoney.getText().toString().trim(), mRedPacketNumber.getText().toString().trim(), mSwitchProperty.getText().toString().trim());
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
                } else if (!TextUtils.isEmpty(mRedPacketMoney.getText().toString().trim()) && TextUtils.isEmpty(mRedPacketNumber.getText().toString().trim())) {
                    toast(getString(R.string.input_redpacket_number));
                } else if (TextUtils.isEmpty(mRedPacketMoney.getText().toString().trim()) && !TextUtils.isEmpty(mRedPacketNumber.getText().toString().trim())) {
                    toast(getString(R.string.input_redpacket_money));
                } else {
                    toast(getString(R.string.input_all_redpacket_info));
                }
                break;
        }
    }
}
