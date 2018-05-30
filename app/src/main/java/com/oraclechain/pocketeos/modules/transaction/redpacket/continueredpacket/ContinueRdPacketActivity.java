package com.oraclechain.pocketeos.modules.transaction.redpacket.continueredpacket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.adapter.AdapterManger;
import com.oraclechain.pocketeos.adapter.baseadapter.wrapper.EmptyWrapper;
import com.oraclechain.pocketeos.app.MyApplication;
import com.oraclechain.pocketeos.base.BaseAcitvity;
import com.oraclechain.pocketeos.bean.CoinRateBean;
import com.oraclechain.pocketeos.bean.RedPacketDetailsBean;
import com.oraclechain.pocketeos.bean.RedPacketHistoryBean;
import com.oraclechain.pocketeos.modules.otherloginorshare.BaseUIListener;
import com.oraclechain.pocketeos.modules.otherloginorshare.WxShareAndLoginUtils;
import com.oraclechain.pocketeos.utils.BigDecimalUtil;
import com.oraclechain.pocketeos.utils.RegexUtil;
import com.oraclechain.pocketeos.utils.StringUtils;
import com.oraclechain.pocketeos.view.CircleImageView;
import com.oraclechain.pocketeos.view.dialog.sharedialog.ShareCallBack;
import com.oraclechain.pocketeos.view.dialog.sharedialog.ShareDialog;
import com.tencent.connect.common.Constants;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzonePublish;
import com.tencent.connect.share.QzoneShare;
import com.tencent.tauth.Tencent;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ContinueRdPacketActivity extends BaseAcitvity<ContinueRedPacketView, ContinueRedpacketPresenter> implements ContinueRedPacketView {


    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.title)
    RelativeLayout mTitle;
    @BindView(R.id.user_img)
    CircleImageView mUserImg;
    @BindView(R.id.user_number)
    TextView mUserNumber;
    @BindView(R.id.user_leave_message)
    TextView mUserLeaveMessage;
    @BindView(R.id.take_rmb_property)
    TextView mTakeRmbProperty;
    @BindView(R.id.go_red_pocket)
    Button mGoRedPocket;
    @BindView(R.id.red_packet_status)
    TextView mRedPacketStatus;
    @BindView(R.id.recycle_redpacket_history)
    RecyclerView mRecycleRedpacketHistory;
    ShareDialog dialog = null;

    private RedPacketHistoryBean.DataBean mDataBean = new RedPacketHistoryBean.DataBean();

    private List<RedPacketDetailsBean.DataBean.RedPacketOrderRedisDtosBean> mRedPacketOrderRedisDtosBeanList = new ArrayList<>();
    private EmptyWrapper mHistoryAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_continue_rd_packet;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        MyApplication.getInstance().showCirImage(MyApplication.getInstance().getUserBean().getWallet_img(), mUserImg);
        mUserNumber.setText(getIntent().getStringExtra("account"));
        mDataBean = getIntent().getParcelableExtra("data");
        if (mDataBean.getResidueCount() == 0) {
            mGoRedPocket.setBackgroundColor(getResources().getColor(R.color.gray_color));
            mGoRedPocket.setClickable(false);
            mGoRedPocket.setText(R.string.red_packet_null);
        } else {
            mGoRedPocket.setBackgroundColor(getResources().getColor(R.color.red_packet_buutton));
            mGoRedPocket.setClickable(true);
            mGoRedPocket.setText(R.string.continue_redpacket);
        }
        mRedPacketStatus.setText(getString(R.string.already_received) + (mDataBean.getPacketCount() - mDataBean.getResidueCount()) + "/" + mDataBean.getPacketCount() + "个,剩余" + (RegexUtil.subZeroAndDot(mDataBean.getResidueAmount()) + " " + mDataBean.getType()));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        layoutManager.setSmoothScrollbarEnabled(true);
        mRecycleRedpacketHistory.setLayoutManager(layoutManager);
    }

    @Override
    protected void initData() {
        showProgress();
        if (mDataBean.getType().equals("EOS")) {
            presenter.getCoinRateData("eos");
        } else {
            presenter.getCoinRateData("oraclechain");
        }

        presenter.getRedPacketDetailsData(mDataBean.getId());

        mHistoryAdapter = new EmptyWrapper(AdapterManger.getRedPacketDetailsAdapter(this, mRedPacketOrderRedisDtosBeanList));
        mHistoryAdapter.setEmptyView(R.layout.empty_project);
        mRecycleRedpacketHistory.setAdapter(mHistoryAdapter);
    }

    @Override
    public void initEvent() {

    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.fitsSystemWindows(true).statusBarDarkFont(false, 0f).statusBarColor(R.color.red_packet_color).init();
    }

    @Override
    public ContinueRedpacketPresenter initPresenter() {
        return new ContinueRedpacketPresenter(ContinueRdPacketActivity.this);
    }

    @Override
    public void getCoinRateDataHttp(CoinRateBean.DataBean coinRateBean) {
        hideProgress();
        mTakeRmbProperty.setText("≈" + StringUtils.addComma(BigDecimalUtil.multiply(new BigDecimal(mDataBean.getAmount()), coinRateBean.getPrice_cny(), 4) + "") + "CNY");
    }

    @Override
    public void getRedPacketDetailsDataHttp(RedPacketDetailsBean.DataBean dataBean) {
        for (RedPacketDetailsBean.DataBean.RedPacketOrderRedisDtosBean redPacketOrderRedisDtosBean : dataBean.getRedPacketOrderRedisDtos()) {
            mRedPacketOrderRedisDtosBeanList.add(redPacketOrderRedisDtosBean);
        }
        mHistoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void getDataHttpFail(String msg) {
        hideProgress();
        toast(msg);
    }

    @OnClick({R.id.iv_back, R.id.go_red_pocket})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.go_red_pocket:
                final Bundle params = new Bundle();
                if (dialog == null) {
                    dialog = new ShareDialog(ContinueRdPacketActivity.this, new ShareCallBack() {
                        @Override
                        public void goWeixinFriend() {
                            WxShareAndLoginUtils.WxUrlShare(ContinueRdPacketActivity.this,
                                    "https://pocketeos.com",
                                    getString(R.string.share_redpacket_title),
                                    mRedPacketStatus.getText().toString(),
                                    MyApplication.getInstance().getUserBean().getWallet_img(),
                                    WxShareAndLoginUtils.WECHAT_FRIEND);
                        }

                        @Override
                        public void goWeixinCircle() {
                            WxShareAndLoginUtils.WxUrlShare(ContinueRdPacketActivity.this, "https://pocketeos.com",
                                    getString(R.string.share_redpacket_title),
                                    mRedPacketStatus.getText().toString(),
                                    MyApplication.getInstance().getUserBean().getWallet_img(),
                                    WxShareAndLoginUtils.WECHAT_MOMENT);
                        }

                        @Override
                        public void goQQFriend() {
                            params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
                            params.putString(QQShare.SHARE_TO_QQ_TITLE, getString(R.string.share_redpacket_title));// 标题
                            params.putString(QQShare.SHARE_TO_QQ_SUMMARY, mRedPacketStatus.getText().toString());// 摘要
                            params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, "http://10.0.0.48:8081?id=" + mDataBean.getId() + "&verifystring=" + mDataBean.getVerifyString());// 内容地址
                            params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, "http://pocketeos.oss-cn-beijing.aliyuncs.com/yl/pocketeos.png?Expires=1839400748&OSSAccessKeyId=LTAIdWMZ4ikcYbmF&Signature=dAyqIz3DfCq4emFtdUu%2F%2Fq7kQYU%3D");// 网络图片地址
                            params.putString(QQShare.SHARE_TO_QQ_EXT_INT, "其它附加功能");
                            MyApplication.getInstance().getTencent().shareToQQ(ContinueRdPacketActivity.this, params, new BaseUIListener(ContinueRdPacketActivity.this, true));
                        }

                        @Override
                        public void goQzone() {
                            params.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
                            params.putString(QzoneShare.SHARE_TO_QQ_TITLE, getString(R.string.share_redpacket_title));// 标题
                            params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, mRedPacketStatus.getText().toString());// 摘要
                            params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, "https://pocketeos.com");// 内容地址
                            ArrayList<String> imgUrlList = new ArrayList<>();
                            imgUrlList.add("http://pocketeos.oss-cn-beijing.aliyuncs.com/yl/pocketeos.png?Expires=1839400748&OSSAccessKeyId=LTAIdWMZ4ikcYbmF&Signature=dAyqIz3DfCq4emFtdUu%2F%2Fq7kQYU%3D");
                            params.putStringArrayList(QzonePublish.PUBLISH_TO_QZONE_IMAGE_URL,
                                    imgUrlList);// 图片地址ArrayList
                            MyApplication.getInstance().getTencent().shareToQzone(ContinueRdPacketActivity.this, params, new BaseUIListener(ContinueRdPacketActivity.this, true));
                        }
                    });
                    dialog.setContent(getString(R.string.red_packet_send_to));
                    dialog.setCancelable(true);
                    dialog.show();
                } else {
                    dialog.show();
                }

                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        hideProgress();
        if (requestCode == Constants.REQUEST_QQ_SHARE || requestCode == Constants.REQUEST_QZONE_SHARE || requestCode == Constants.REQUEST_OLD_SHARE) {
            Tencent.handleResultData(data, new BaseUIListener(ContinueRdPacketActivity.this, true));
        }
    }
}
