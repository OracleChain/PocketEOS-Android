package com.oraclechain.pocketeos.modules.transaction.makecollections;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.oraclechain.pocketeos.app.MyApplication;
import com.oraclechain.pocketeos.base.BaseAcitvity;
import com.oraclechain.pocketeos.bean.AccountInfoBean;
import com.oraclechain.pocketeos.bean.CoinRateBean;
import com.oraclechain.pocketeos.bean.PostChainHistoryBean;
import com.oraclechain.pocketeos.bean.QrCodeMakeCollectionBean;
import com.oraclechain.pocketeos.bean.TransferHistoryBean;
import com.oraclechain.pocketeos.modules.otherloginorshare.BaseUIListener;
import com.oraclechain.pocketeos.modules.otherloginorshare.WxShareAndLoginUtils;
import com.oraclechain.pocketeos.utils.AndroidBug5497Workaround;
import com.oraclechain.pocketeos.utils.BigDecimalUtil;
import com.oraclechain.pocketeos.utils.FilesUtils;
import com.oraclechain.pocketeos.utils.JsonUtil;
import com.oraclechain.pocketeos.utils.KeyBoardUtil;
import com.oraclechain.pocketeos.utils.RotateUtils;
import com.oraclechain.pocketeos.utils.StringUtils;
import com.oraclechain.pocketeos.view.ClearEditText;
import com.oraclechain.pocketeos.view.RecycleViewDivider;
import com.oraclechain.pocketeos.view.dialog.makecollectiondialog.MakeCollectionCallBack;
import com.oraclechain.pocketeos.view.dialog.makecollectiondialog.MakeCollectionsDialog;
import com.oraclechain.pocketeos.view.popupwindow.BasePopupWindow;
import com.oraclechain.pocketeos.view.textwatcher.MakeCollectionMoneyTextWatcher;
import com.tencent.connect.common.Constants;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzonePublish;
import com.tencent.tauth.Tencent;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.oraclechain.pocketeos.utils.Utils.getContext;

//收款
public class MakeCollectionsActivity extends BaseAcitvity<MakeCollectionsView, MakeCollectionsPresenter> implements MakeCollectionsView {

    @BindView(R.id.img_right)
    ImageView mImgRight;
    @BindView(R.id.title)
    RelativeLayout mTitle;
    @BindView(R.id.switch_number)
    TextView mSwitchNumber;
    @BindView(R.id.look_number)
    ImageView mLookNumber;
    @BindView(R.id.switch_property)
    TextView mSwitchProperty;
    @BindView(R.id.look_property)
    ImageView mLookProperty;
    @BindView(R.id.get_property_number)
    ClearEditText mGetPropertyNumber;
    @BindView(R.id.take_rmb_property)
    TextView mTakeRmbProperty;
    @BindView(R.id.get_make_collections_code)
    Button mGetMakeCollectionsCode;
    @BindView(R.id.recycle_make_collections_history)
    RecyclerView mRecycleMakeCollectionsHistory;
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

    private List<TransferHistoryBean.DataBeanX.TransactionsBean> mDataBeanList = new ArrayList<>();//交易历史
    private EmptyWrapper mHistoryAdapter;
    private int size = 10; //每页加载的数量

    private PostChainHistoryBean mPostChainHistoryBean = new PostChainHistoryBean();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_make_collections;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        AndroidBug5497Workaround.assistActivity(activity);

        setCenterTitle(getString(R.string.get_makecollections));
        setRightImg(false);
        mImgRight.setImageDrawable(getResources().getDrawable(R.mipmap.makecollectionshare));

        mAccountInfoBeanList = JsonUtil.parseJsonToArrayList(MyApplication.getInstance().getUserBean().getAccount_info(), AccountInfoBean.class);
        mSwitchNumber.setText(getIntent().getStringExtra("account"));
        mSwitchProperty.setText(getIntent().getStringExtra("coin"));//默认选择EOS

        LinearLayoutManager layoutManager = new LinearLayoutManager(MakeCollectionsActivity.this, LinearLayoutManager.VERTICAL, false);
        layoutManager.setSmoothScrollbarEnabled(true);
        mRecycleMakeCollectionsHistory.setLayoutManager(layoutManager);
        mRecycleMakeCollectionsHistory.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL, 1, getResources().getColor(R.color.line)));

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
                mPostChainHistoryBean.setSkip_seq(mDataBeanList.size());
                presenter.getTransferHistoryData(mPostChainHistoryBean);
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
        mPostChainHistoryBean.setAccount_name(mSwitchNumber.getText().toString());
        mPostChainHistoryBean.setSkip_seq(0);
        mPostChainHistoryBean.setNum_seq(size);
        presenter.getTransferHistoryData(mPostChainHistoryBean);


        mHistoryAdapter = new EmptyWrapper(AdapterManger.getMakeCollectionHistoryAdapter(this, mDataBeanList));
        mHistoryAdapter.setEmptyView(R.layout.empty_project);

        mRecycleMakeCollectionsHistory.setAdapter(mHistoryAdapter);
    }

    @Override
    public void initEvent() {

    }

    @Override
    public MakeCollectionsPresenter initPresenter() {
        return new MakeCollectionsPresenter(MakeCollectionsActivity.this);
    }

    @Override
    public void getCoinRateDataHttp(CoinRateBean.DataBean coinRateBean) {
        coinRate = coinRateBean.getPrice_cny();
        mGetPropertyNumber.addTextChangedListener(new MakeCollectionMoneyTextWatcher(mGetPropertyNumber, mTakeRmbProperty, coinRate, mGetMakeCollectionsCode));//限制金额最多为小数点后面四位
        if (mGetPropertyNumber.getText().toString().trim().length() != 0) {
            mTakeRmbProperty.setText("≈" + StringUtils.addComma(BigDecimalUtil.multiply(BigDecimal.valueOf(Double.parseDouble(mGetPropertyNumber.getText().toString())), coinRate, 4) + "") + "CNY");
        }
    }

    @Override
    public void getTransferHistoryDataHttp(TransferHistoryBean.DataBeanX transferHistoryBean) {
        mSpring.onFinishFreshAndLoad();
        hideProgress();
        for (int i = 0; i < transferHistoryBean.getTransactions().size(); i++) {
            if (transferHistoryBean.getTransactions().get(i).getTransaction().getTransaction().getActions().get(0).getName().equals("transfer")) {
                if (transferHistoryBean.getTransactions().get(i).getTransaction().getTransaction().getActions().get(0).getData().getTo().equals(mSwitchNumber.getText().toString().trim())
                        && transferHistoryBean.getTransactions().get(i).getTransaction().getTransaction().getActions().get(0).getData().getQuantity().contains(mSwitchProperty.getText().toString().trim()) ) {
                    if (!transferHistoryBean.getTransactions().get(i).getTransaction().getTransaction().getActions().get(0).getData().getFrom().equals("oc.redpacket")) {
                        TransferHistoryBean.DataBeanX.TransactionsBean itemdata = transferHistoryBean.getTransactions().get(i);
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

    @OnClick({R.id.img_right, R.id.switch_number, R.id.switch_property, R.id.get_make_collections_code})
    public void onViewClicked(View view) {
        if (KeyBoardUtil.isSoftInputShow(this)) {
            KeyBoardUtil.getInstance(this).hide();
        }
        switch (view.getId()) {
            case R.id.img_right:
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
                            mPostChainHistoryBean.setAccount_name(mSwitchNumber.getText().toString());
                            mPostChainHistoryBean.setSkip_seq(0);
                            mPostChainHistoryBean.setNum_seq(size);
                            mDataBeanList.clear();
                            presenter.getTransferHistoryData(mPostChainHistoryBean);
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

                            mPostChainHistoryBean.setAccount_name(mSwitchNumber.getText().toString());
                            mPostChainHistoryBean.setSkip_seq(0);
                            mPostChainHistoryBean.setNum_seq(size);
                            mDataBeanList.clear();
                            presenter.getTransferHistoryData(mPostChainHistoryBean);

                            if (mSwitchProperty.getText().toString().equals("OCT")) {
                                presenter.getCoinRateData("oraclechain");
                            } else if (mSwitchProperty.getText().toString().equals("EOS")) {
                                presenter.getCoinRateData("eos");
                            }
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
            case R.id.get_make_collections_code:
                if (mGetPropertyNumber.getText().toString().trim().length() == 0) {
                    toast("请输入收款数额~");
                } else {

                    MakeCollectionsDialog dialog = new MakeCollectionsDialog(this, new MakeCollectionCallBack() {

                        @Override
                        public void goWeixinFriend(Bitmap bitmap) {
                            WxShareAndLoginUtils.WxBitmapShare(MakeCollectionsActivity.this, bitmap, WxShareAndLoginUtils.WECHAT_FRIEND);
                        }

                        @Override
                        public void goWeixinCircle(Bitmap bitmap) {
                            WxShareAndLoginUtils.WxBitmapShare(MakeCollectionsActivity.this, bitmap, WxShareAndLoginUtils.WECHAT_MOMENT);
                        }

                        @Override
                        public void goQQFriend(Bitmap bitmap) {
                            Bundle params = new Bundle();
                            params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_IMAGE);
                            params.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, FilesUtils.savePhoto(bitmap, Environment
                                    .getExternalStorageDirectory().getAbsolutePath() + "/pocketEos/makeCollectionCode", String
                                    .valueOf(System.currentTimeMillis())));
                            params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "pocketEos");
                            params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE);
                            MyApplication.getInstance().getTencent().shareToQQ(MakeCollectionsActivity.this, params, new BaseUIListener(MakeCollectionsActivity.this, true));
                        }

                        @Override
                        public void goQzone(Bitmap bitmap) {
                            Bundle params = new Bundle();
                            params.putInt(QzonePublish.PUBLISH_TO_QZONE_KEY_TYPE, QzonePublish.PUBLISH_TO_QZONE_TYPE_PUBLISHMOOD);
                            ArrayList<String> imgUrlList = new ArrayList<>();
                            imgUrlList.add(FilesUtils.savePhoto(bitmap, Environment
                                    .getExternalStorageDirectory().getAbsolutePath() + "/pocketEos/makeCollectionCode", String
                                    .valueOf(System.currentTimeMillis())));// 图片地址
                            params.putStringArrayList(QzonePublish.PUBLISH_TO_QZONE_IMAGE_URL,
                                    imgUrlList);// 图片地址ArrayList
                            MyApplication.getInstance().getTencent().publishToQzone(MakeCollectionsActivity.this, params, new BaseUIListener(MakeCollectionsActivity.this, true));
                        }
                    });
                    QrCodeMakeCollectionBean qrCodeMakeCollectionBean = new QrCodeMakeCollectionBean();
                    qrCodeMakeCollectionBean.setAccount_name(mSwitchNumber.getText().toString());
                    qrCodeMakeCollectionBean.setCoin(mSwitchProperty.getText().toString());
                    qrCodeMakeCollectionBean.setType("make_collections_QRCode");
                    qrCodeMakeCollectionBean.setMoney(mGetPropertyNumber.getText().toString().trim());

                    dialog.setContent(new Gson().toJson(qrCodeMakeCollectionBean), mGetPropertyNumber.getText().toString().trim(), mSwitchProperty.getText().toString());
                    dialog.setCancelable(true);
                    dialog.show();
                    break;
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        hideProgress();
//        Tencent.onActivityResultData(requestCode, resultCode, data, new BaseUIListener(MakeCollectionsActivity.this, true));
        if (requestCode == Constants.REQUEST_QQ_SHARE || requestCode == Constants.REQUEST_QZONE_SHARE|| requestCode == Constants.REQUEST_OLD_SHARE) {
                Tencent.handleResultData(data, new BaseUIListener(MakeCollectionsActivity.this, true));
        }
    }
}
