package com.oraclechain.pocketeos.modules.transaction.redpacket.anticipationredpacket;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.app.MyApplication;
import com.oraclechain.pocketeos.base.BaseAcitvity;
import com.oraclechain.pocketeos.bean.AuthRedPacketBean;
import com.oraclechain.pocketeos.bean.SendRedPacketBean;
import com.oraclechain.pocketeos.modules.otherloginorshare.BaseUIListener;
import com.oraclechain.pocketeos.modules.otherloginorshare.WxShareAndLoginUtils;
import com.oraclechain.pocketeos.view.CircleImageView;
import com.tencent.connect.common.Constants;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzonePublish;
import com.tencent.connect.share.QzoneShare;
import com.tencent.tauth.Tencent;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * The type Anticipation red packet activity.
 * 红包已封好页面
 */
public class AnticipationRedPacketActivity extends BaseAcitvity<AnticipationRedPacketView, AnticipationRedPacketPresenter> implements AnticipationRedPacketView {


    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.user_img)
    CircleImageView mUserImg;
    @BindView(R.id.packet_info)
    TextView mPacketInfo;
    @BindView(R.id.user_leave_message)
    TextView mUserLeaveMessage;
    @BindView(R.id.go_weixin_friend)
    LinearLayout mGoWeixinFriend;
    @BindView(R.id.go_weixin_circle)
    LinearLayout mGoWeixinCircle;
    @BindView(R.id.go_qq_friend)
    LinearLayout mGoQqFriend;
    @BindView(R.id.go_qzone)
    LinearLayout mGoQzone;
    @BindView(R.id.pay_account)
    TextView mPayAccount;
    @BindView(R.id.send_back_time)
    TextView mSendBackTime;
    SendRedPacketBean.DataBean mDataBean;

    String message = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_anticipation_red_packet;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        MyApplication.getInstance().showCirImage(MyApplication.getInstance().getUserBean().getWallet_img(), mUserImg);
        mDataBean = getIntent().getParcelableExtra("info");
        mPacketInfo.setText(getIntent().getStringExtra("redpacketNumber") + "个红包,共" + getIntent().getStringExtra("amount") + getIntent().getStringExtra("transferCode"));
        mSendBackTime.setText(getString(R.string.send_back_time) + mDataBean.getEndTime());
        mPayAccount.setText(getString(R.string.pay_account) + getIntent().getStringExtra("account"));
        mUserLeaveMessage.setText(getIntent().getStringExtra("memo"));
    }

    @Override
    protected void initData() {
        presenter.getAuthRedPacketData(mDataBean.getId(), getIntent().getStringExtra("txtid"));
    }

    @Override
    public void initEvent() {
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.fitsSystemWindows(true).statusBarDarkFont(false, 0f).statusBarColor(R.color.red_packet_color).init();
    }

    @Override
    public AnticipationRedPacketPresenter initPresenter() {
        return new AnticipationRedPacketPresenter(this);
    }

    @Override
    public void getAuthRedPacketDataHttp(AuthRedPacketBean.DataBean dataBean) {
        message = null;
    }

    @Override
    public void getDataHttpFail(String msg) {
        message = msg;
        toast(message);
    }

    @OnClick({R.id.go_weixin_friend, R.id.go_weixin_circle, R.id.go_qq_friend, R.id.go_qzone})
    public void onViewClicked(View view) {
        Bundle params = new Bundle();
        if (!TextUtils.isEmpty(message)) {
            presenter.getAuthRedPacketData(mDataBean.getId(), getIntent().getStringExtra("txtid"));
        } else {
            switch (view.getId()) {
                case R.id.go_weixin_friend:
                    WxShareAndLoginUtils.WxUrlShare(AnticipationRedPacketActivity.this, "https://pocketeos.com", getString(R.string.share_redpacket_title), getString(R.string.share_redpacket_message),
                            MyApplication.getInstance().getUserBean().getWallet_img(),
                            WxShareAndLoginUtils.WECHAT_FRIEND);
                    break;
                case R.id.go_weixin_circle:
                    WxShareAndLoginUtils.WxUrlShare(AnticipationRedPacketActivity.this, "https://pocketeos.com", getString(R.string.share_redpacket_title),
                            getString(R.string.share_redpacket_message),
                            MyApplication.getInstance().getUserBean().getWallet_img(),
                            WxShareAndLoginUtils.WECHAT_MOMENT);
                    break;
                case R.id.go_qq_friend:
                    params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
                    params.putString(QQShare.SHARE_TO_QQ_TITLE, getString(R.string.share_redpacket_title));// 标题
                    params.putString(QQShare.SHARE_TO_QQ_SUMMARY, getString(R.string.share_redpacket_message));// 摘要
                    params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, "http://192.168.33.17:8081?id=" + mDataBean.getId() + "&verifystring=" + mDataBean.getVerifyString());// 内容地址
                    params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, "http://pocketeos.oss-cn-beijing.aliyuncs.com/yl/pocketeos.png?Expires=1839400748&OSSAccessKeyId=LTAIdWMZ4ikcYbmF&Signature=dAyqIz3DfCq4emFtdUu%2F%2Fq7kQYU%3D");// 网络图片地址
                    params.putString(QQShare.SHARE_TO_QQ_EXT_INT, "其它附加功能");
                    MyApplication.getInstance().getTencent().shareToQQ(AnticipationRedPacketActivity.this, params, new BaseUIListener(AnticipationRedPacketActivity.this, true));
                    break;
                case R.id.go_qzone:
                    params.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
                    params.putString(QzoneShare.SHARE_TO_QQ_TITLE, getString(R.string.share_redpacket_title));// 标题
                    params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, getString(R.string.share_redpacket_message));// 摘要
                    params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, "https://pocketeos.com");// 内容地址
                    ArrayList<String> imgUrlList = new ArrayList<>();
                    imgUrlList.add("http://pocketeos.oss-cn-beijing.aliyuncs.com/yl/pocketeos.png?Expires=1839400748&OSSAccessKeyId=LTAIdWMZ4ikcYbmF&Signature=dAyqIz3DfCq4emFtdUu%2F%2Fq7kQYU%3D");
                    params.putStringArrayList(QzonePublish.PUBLISH_TO_QZONE_IMAGE_URL,
                            imgUrlList);// 图片地址ArrayList
                    MyApplication.getInstance().getTencent().shareToQzone(AnticipationRedPacketActivity.this, params, new BaseUIListener(AnticipationRedPacketActivity.this, true));
                    break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        hideProgress();
//        Tencent.onActivityResultData(requestCode, resultCode, data, new BaseUIListener(AnticipationRedPacketActivity.this, true));
        if (requestCode == Constants.REQUEST_API) {
            if (resultCode == Constants.REQUEST_QQ_SHARE || resultCode == Constants.REQUEST_QZONE_SHARE || resultCode == Constants.REQUEST_OLD_SHARE) {
                Tencent.handleResultData(data, new BaseUIListener(AnticipationRedPacketActivity.this, true));
            }
        }
    }

}
