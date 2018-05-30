package com.oraclechain.pocketeos.modules.otherloginorshare;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.app.MyApplication;
import com.oraclechain.pocketeos.utils.JsonUtil;
import com.oraclechain.pocketeos.utils.ToastUtils;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

//qq登陆管理类
public class BaseUIListener implements IUiListener {
    private Context mContext;
    private boolean mIsCaneled;
    private static final int ON_COMPLETE = 0;
    private static final int ON_ERROR = 1;
    private static final int ON_CANCEL = 2;
    private static final int ON_GETINFO = 3;
    OtherLoginCallBack mOtherLoginCallBack;
    String openid = null;
    Boolean isShare = false;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ON_COMPLETE:
                    JSONObject response = (JSONObject) msg.obj;
                    /**
                     * {
                     "ret": 0,
                     "openid": "19E8D43EB75ED256CAC70C02953F188A",
                     "access_token": "65A5A933F116085E051F39CAD65084EF",
                     "pay_token": "BA387A1679483A4C8585BE268C7C4128",
                     "expires_in": 7776000,
                     "pf": "desktop_m_qq-10000144-android-2002-",
                     "pfkey": "2c7171fb052154f89e9d439e17e18c11",
                     "msg": "",
                     "login_cost": 442,
                     "query_authority_cost": 322,
                     "authority_cost": 0
                     }
                     */
                    if (!isShare) {
                        //设置openid和token，否则获取不到下面的信息
                        initOpenidAndToken(response);
                    } else {
                        ToastUtils.showLongToast(R.string.share_success);
                    }
                    break;
                case ON_GETINFO:
                    if (openid != null) {
                        ToastUtils.showLongToast(R.string.qq_accredit_success);
                        mOtherLoginCallBack.userInfoData(openid, (QQUserInfoBean) msg.obj);
                    }
                    break;
                case ON_ERROR:
                    ToastUtils.showLongToast("onError");
                    break;
                case ON_CANCEL:
                    if (!isShare) {
                        ToastUtils.showLongToast(R.string.cancel_login);
                    } else {
                        ToastUtils.showLongToast(R.string.cancel_share);
                    }
                    break;
            }
        }
    };

    public BaseUIListener(Context mContext, OtherLoginCallBack otherLoginCallBack) {
        super();
        this.mContext = mContext;
        this.mOtherLoginCallBack = otherLoginCallBack;
    }

    public BaseUIListener(Context mContext, Boolean share) {
        super();
        this.mContext = mContext;
        this.isShare = share;
    }

    public BaseUIListener() {
        super();
    }


    public void cancel() {
        mIsCaneled = true;
    }


    @Override
    public void onComplete(Object response) {
        if (mIsCaneled) return;
        Message msg = mHandler.obtainMessage();
        msg.what = ON_COMPLETE;
        msg.obj = response;
        mHandler.sendMessage(msg);
    }

    @Override
    public void onError(UiError e) {
        if (mIsCaneled) return;
        Message msg = mHandler.obtainMessage();
        msg.what = ON_ERROR;
        msg.obj = e;
        mHandler.sendMessage(msg);
    }

    @Override
    public void onCancel() {
        if (mIsCaneled) return;
        Message msg = mHandler.obtainMessage();
        msg.what = ON_CANCEL;
        mHandler.sendMessage(msg);
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    private void initOpenidAndToken(JSONObject jsonObject) {
        try {
            openid = jsonObject.getString("openid");
            String token = jsonObject.getString("access_token");
            String expires = jsonObject.getString("expires_in");

            MyApplication.getInstance().getTencent().setAccessToken(token, expires);
            MyApplication.getInstance().getTencent().setOpenId(openid);

            //获取QQ用户的各信息
            getUserInfo();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getUserInfo() {
        /**
         * {
         "ret": 0,
         "msg": "",
         "is_lost": 0,
         "nickname": "",
         "gender": "",
         "province": "",
         "city": "",
         "figureurl": "",
         "figureurl_1": "",
         "figureurl_2": "",
         "figureurl_qq_1": "",
         "figureurl_qq_2": "",
         "is_yellow_vip": "0",
         "vip": "0",
         "yellow_vip_level": "0",
         "level": "0",
         "is_yellow_year_vip": "0"
         }
         */
        //sdk给我们提供了一个类UserInfo，这个类中封装了QQ用户的一些信息，我么可以通过这个类拿到这些信息
        QQToken mQQToken = MyApplication.getInstance().getTencent().getQQToken();
        UserInfo userInfo = new UserInfo(mContext, mQQToken);
        userInfo.getUserInfo(new IUiListener() {
                                 @Override
                                 public void onComplete(final Object o) {
                                     JSONObject userInfoJson = (JSONObject) o;
                                     Message msgNick = new Message();
                                     QQUserInfoBean qqUserInfoBean = (QQUserInfoBean) JsonUtil.parseStringToBean(userInfoJson.toString(), QQUserInfoBean.class);
                                     msgNick.what = ON_GETINFO;//昵称
                                     msgNick.obj = qqUserInfoBean;//直接传递一QQ信息内容过去
                                     mHandler.sendMessage(msgNick);
                                 }

                                 @Override
                                 public void onError(UiError uiError) {
                                     ToastUtils.showLongToast(R.string.get_qqinfo_error);
                                 }

                                 @Override
                                 public void onCancel() {
                                     ToastUtils.showLongToast(R.string.get_qqinfo_cancel);
                                 }
                             }
        );
    }
}
