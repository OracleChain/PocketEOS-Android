package com.oraclechain.pocketeos.modules.blackbox.blackboxcoindetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.liaoinstan.springview.container.AliFooter;
import com.liaoinstan.springview.widget.SpringView;
import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.adapter.AdapterManger;
import com.oraclechain.pocketeos.adapter.baseadapter.wrapper.EmptyWrapper;
import com.oraclechain.pocketeos.app.ActivityUtils;
import com.oraclechain.pocketeos.app.MyApplication;
import com.oraclechain.pocketeos.base.BaseAcitvity;
import com.oraclechain.pocketeos.bean.AccountWithCoinBean;
import com.oraclechain.pocketeos.bean.PostChainHistoryBean;
import com.oraclechain.pocketeos.bean.SparkLinesBean;
import com.oraclechain.pocketeos.bean.TransferHistoryBean;
import com.oraclechain.pocketeos.modules.coindetails.CoinDetailsPresenter;
import com.oraclechain.pocketeos.modules.coindetails.CoinDetailsView;
import com.oraclechain.pocketeos.modules.otherloginorshare.BaseUIListener;
import com.oraclechain.pocketeos.modules.otherloginorshare.WxShareAndLoginUtils;
import com.oraclechain.pocketeos.modules.transaction.transferaccounts.TransferAccountsActivity;
import com.oraclechain.pocketeos.utils.StringUtils;
import com.oraclechain.pocketeos.view.DrawableCenterTextView;
import com.oraclechain.pocketeos.view.dialog.sharecoindetailsdialog.ShareCoinDetailsCallBack;
import com.oraclechain.pocketeos.view.dialog.sharecoindetailsdialog.ShareCoinDetailsDialog;
import com.tencent.connect.common.Constants;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzonePublish;
import com.tencent.connect.share.QzoneShare;
import com.tencent.tauth.Tencent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class BlackBoxCoinDetailsActivity extends BaseAcitvity<CoinDetailsView, CoinDetailsPresenter> implements CoinDetailsView {

    @BindView(R.id.icon_total_number)
    TextView mIconTotalNumber;
    @BindView(R.id.coin_uoanddown)
    TextView mCoinUoanddown;
    @BindView(R.id.coin_maket_cap)
    TextView mCoinMaketCap;
    @BindView(R.id.lineChart)
    LineChart mLineChart;
    @BindView(R.id.coin_upanddown_img)
    ImageView mCoinUpanddownImg;
    @BindView(R.id.recycle_coin_history)
    RecyclerView mRecycleCoinHistory;
    @BindView(R.id.spring)
    SpringView mSpring;
    @BindView(R.id.img_right)
    ImageView mImgRight;
    @BindView(R.id.go_transfer_accounts)
    DrawableCenterTextView mGoTransferAccounts;
    @BindView(R.id.go_make_collections)
    DrawableCenterTextView mGoMakeCollections;

    ShareCoinDetailsDialog dialog = null;
    private AccountWithCoinBean accountWithCoinBean = new AccountWithCoinBean();
    private List<TransferHistoryBean.DataBeanX.ActionsBean> mDataBeanList = new ArrayList<>();//交易历史
    private EmptyWrapper mHistoryAdapter;
    private int size = 10; //每页加载的数量
    private PostChainHistoryBean mPostChainHistoryBean = new PostChainHistoryBean();
    private String cointype = "eos";
    private int page = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_black_box_coin_details;
    }

    @Override
    public CoinDetailsPresenter initPresenter() {
        return new CoinDetailsPresenter(BlackBoxCoinDetailsActivity.this);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(BlackBoxCoinDetailsActivity.this, LinearLayoutManager.VERTICAL, false);
        layoutManager.setSmoothScrollbarEnabled(true);
        mRecycleCoinHistory.setLayoutManager(layoutManager);

        accountWithCoinBean = getIntent().getParcelableExtra("coin");
        if (accountWithCoinBean.getCoinName().equals("EOS")) {
            cointype = "eos";
        } else {
            cointype = "oct";
        }
        setCenterTitle(accountWithCoinBean.getCoinName());
        setRightImg(true);
        mIconTotalNumber.setText(StringUtils.addComma(accountWithCoinBean.getCoinForCny()) + " CNY");
        if (accountWithCoinBean.getCoinUpsAndDowns().contains("+")) {
            mCoinUoanddown.setTextColor(getResources().getColor(R.color.up_color));
        } else {
            mCoinUoanddown.setTextColor(getResources().getColor(R.color.down_color));
        }
        mCoinUoanddown.setText(accountWithCoinBean.getCoinUpsAndDowns() + getString(R.string.today));


        if (accountWithCoinBean.getCoinName().equals("EOS")) {
            mCoinMaketCap.setText(getString(R.string.rated_24) + accountWithCoinBean.getEos_market_cap_cny() + "CNY");
        } else {
            mCoinMaketCap.setText(getString(R.string.rated_24) + accountWithCoinBean.getOct_market_cap_cny() + "CNY");
        }
        //系统刷新
        mSpring.setFooter(new AliFooter(this));
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
        showProgress();
        presenter.getSparklinesData();

        mPostChainHistoryBean.setFrom(getIntent().getStringExtra("account"));
        mPostChainHistoryBean.setTo(getIntent().getStringExtra("account"));
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


        mHistoryAdapter = new EmptyWrapper(AdapterManger.getCoinDetailsHistoryAdapter(this, mDataBeanList, getIntent().getStringExtra("account")));
        mHistoryAdapter.setEmptyView(R.layout.empty_project);

        mRecycleCoinHistory.setAdapter(mHistoryAdapter);
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void getTransferHistoryDataHttp(TransferHistoryBean.DataBeanX transferHistoryBean) {
        hideProgress();
        mSpring.onFinishFreshAndLoad();
        page += 1;
        for (int i = 0; i < transferHistoryBean.getActions().size(); i++) {
            if (transferHistoryBean.getActions().get(i).getDoc().getName().equals("transfer")) {
                if (transferHistoryBean.getActions().get(i).getDoc().getData().getQuantity().contains(cointype.toUpperCase())) {
                    TransferHistoryBean.DataBeanX.ActionsBean itemdata = transferHistoryBean.getActions().get(i);
                    mDataBeanList.add(itemdata);
                }
            }
        }
        mHistoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void getSparklinesData(SparkLinesBean.DataBean dataBean) {
        if (accountWithCoinBean.getCoinName().equals("EOS")) {
            MyApplication.getInstance().showImage(dataBean.getSparkline_eos_png(), mCoinUpanddownImg);
        } else {
            MyApplication.getInstance().showImage(dataBean.getSparkline_oct_png(), mCoinUpanddownImg);
        }
    }

    @Override
    public void getDataHttpFail(String msg) {
        hideProgress();
        mSpring.onFinishFreshAndLoad();
        toast(msg);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        hideProgress();
        if (requestCode == Constants.REQUEST_QQ_SHARE || requestCode == Constants.REQUEST_QZONE_SHARE || requestCode == Constants.REQUEST_OLD_SHARE) {
            Tencent.handleResultData(data, new BaseUIListener(BlackBoxCoinDetailsActivity.this, true));
        }
        if (requestCode == 100 && resultCode == 300) {
            if (data.getExtras().getString("coin_type").equals(accountWithCoinBean.getCoinName())) {
                mDataBeanList.clear();
                page = 0;
                mPostChainHistoryBean.setPage(0);
                presenter.getTransferHistoryData(mPostChainHistoryBean);
            }
        }
    }


    @OnClick({R.id.img_right, R.id.go_transfer_accounts, R.id.go_make_collections})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.img_right:
                String desc = null;
                final Bundle params = new Bundle();
                if (accountWithCoinBean.getCoinName().equals("OCT")) {
                    desc = getString(R.string.reference_price_cny) + StringUtils.addComma(accountWithCoinBean.getOct_price_cny()) + getString(R.string.toast_down_up) + accountWithCoinBean.getCoinUpsAndDowns() + getString(R.string.all_market_price) + StringUtils.addComma(accountWithCoinBean.getOct_market_cap_cny());
                } else {
                    desc = getString(R.string.reference_price_cny) + StringUtils.addComma(accountWithCoinBean.getEos_price_cny()) + getString(R.string.toast_down_up) + accountWithCoinBean.getCoinUpsAndDowns() + getString(R.string.all_market_price) + StringUtils.addComma(accountWithCoinBean.getEos_market_cap_cny());
                }
                if (dialog == null) {
                    bundle.putParcelable("coin", accountWithCoinBean);
                    final String finalDesc = desc;
                    dialog = new ShareCoinDetailsDialog(BlackBoxCoinDetailsActivity.this, new ShareCoinDetailsCallBack() {
                        @Override
                        public void goWeixinFriend() {
                            WxShareAndLoginUtils.WxUrlShare(BlackBoxCoinDetailsActivity.this, "https://pocketeos.com", accountWithCoinBean.getCoinName() + "最新资讯详情", finalDesc,
                                    MyApplication.getInstance().getUserBean().getWallet_img(),
                                    WxShareAndLoginUtils.WECHAT_FRIEND);
                        }

                        @Override
                        public void goWeixinCircle() {
                            WxShareAndLoginUtils.WxUrlShare(BlackBoxCoinDetailsActivity.this, "https://pocketeos.com", accountWithCoinBean.getCoinName() + "最新资讯详情", finalDesc,
                                    MyApplication.getInstance().getUserBean().getWallet_img(),
                                    WxShareAndLoginUtils.WECHAT_MOMENT);
                        }

                        @Override
                        public void goQQFriend() {
                            params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
                            params.putString(QQShare.SHARE_TO_QQ_TITLE, accountWithCoinBean.getCoinName() + "最新资讯详情");// 标题
                            params.putString(QQShare.SHARE_TO_QQ_SUMMARY, finalDesc);// 摘要
                            params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, "https://pocketeos.com");// 内容地址
                            params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, "http://pocketeos.oss-cn-beijing.aliyuncs.com/yl/pocketeos.png?Expires=1839400748&OSSAccessKeyId=LTAIdWMZ4ikcYbmF&Signature=dAyqIz3DfCq4emFtdUu%2F%2Fq7kQYU%3D");
                            params.putString(QQShare.SHARE_TO_QQ_EXT_INT, "其它附加功能");
                            MyApplication.getInstance().getTencent().shareToQQ(BlackBoxCoinDetailsActivity.this, params, new BaseUIListener(BlackBoxCoinDetailsActivity.this, true));
                        }

                        @Override
                        public void goQzone() {
                            params.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
                            params.putString(QzoneShare.SHARE_TO_QQ_TITLE, accountWithCoinBean.getCoinName() + "最新资讯详情");// 标题
                            params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, finalDesc);// 摘要
                            params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, "https://pocketeos.com");// 内容地址
                            ArrayList<String> imgUrlList = new ArrayList<>();
                            imgUrlList.add("http://pocketeos.oss-cn-beijing.aliyuncs.com/yl/pocketeos.png?Expires=1839400748&OSSAccessKeyId=LTAIdWMZ4ikcYbmF&Signature=dAyqIz3DfCq4emFtdUu%2F%2Fq7kQYU%3D");
                            params.putStringArrayList(QzonePublish.PUBLISH_TO_QZONE_IMAGE_URL,
                                    imgUrlList);// 图片地址ArrayList
                            MyApplication.getInstance().getTencent().shareToQzone(BlackBoxCoinDetailsActivity.this, params, new BaseUIListener(BlackBoxCoinDetailsActivity.this, true));
                        }
                    });
                    dialog.setContent(bundle);
                    dialog.setCancelable(true);
                    dialog.show();
                } else {
                    dialog.show();
                }
                break;
            case R.id.go_transfer_accounts:
                bundle.putString("account", getIntent().getStringExtra("account"));
                bundle.putString("coin", accountWithCoinBean.getCoinName());
                bundle.putString("from", "coin");
                ActivityUtils.next(BlackBoxCoinDetailsActivity.this, TransferAccountsActivity.class, bundle, 100);
                break;
            case R.id.go_make_collections:
                bundle.putString("account", getIntent().getStringExtra("account"));
                bundle.putString("coin", accountWithCoinBean.getCoinName());
                bundle.putString("from", "coin");
                ActivityUtils.next(BlackBoxCoinDetailsActivity.this, TransferAccountsActivity.class, bundle, 100);
                break;
        }
    }
}
