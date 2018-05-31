package com.oraclechain.pocketeos.wxapi;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.utils.OkLogger;
import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.app.ActivityUtils;
import com.oraclechain.pocketeos.app.AppManager;
import com.oraclechain.pocketeos.app.MyApplication;
import com.oraclechain.pocketeos.base.BaseUrl;
import com.oraclechain.pocketeos.base.Constants;
import com.oraclechain.pocketeos.bean.BaseBean;
import com.oraclechain.pocketeos.bean.UserBean;
import com.oraclechain.pocketeos.bean.WXAccessTokenBean;
import com.oraclechain.pocketeos.gen.UserBeanDao;
import com.oraclechain.pocketeos.modules.leftdrawer.usercenter.otherlogintype.OtherLoginTypeActivity;
import com.oraclechain.pocketeos.modules.main.MainActivity;
import com.oraclechain.pocketeos.modules.otherloginorshare.WechatInfoBean;
import com.oraclechain.pocketeos.modules.wallet.createwallet.bindphone.BindPhoneActivity;
import com.oraclechain.pocketeos.modules.wallet.createwallet.login.LoginActivity;
import com.oraclechain.pocketeos.utils.JsonUtil;
import com.oraclechain.pocketeos.utils.ShowDialog;
import com.oraclechain.pocketeos.utils.ToastUtils;
import com.oraclechain.pocketeos.utils.Utils;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    public int WX_LOGIN = 1;

    private IWXAPI iwxapi;

    private SendAuth.Resp resp;

    private String AppID = Constants.WX_AppID;
    private String AppSecret = Constants.WX_AppSecret;
    private WechatInfoBean wechatInfoBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        iwxapi = WXAPIFactory.createWXAPI(this, AppID, true);
        //接收到分享以及登录的intent传递handleIntent方法，处理结果
        iwxapi.handleIntent(getIntent(), this);

    }


    @Override
    public void onReq(BaseReq baseReq) {
    }


    //请求回调结果处理
    @Override
    public void onResp(BaseResp baseResp) {
        //微信登录为getType为1，分享为0
        if (baseResp.getType() == WX_LOGIN) {
            //登录回调
            resp = (SendAuth.Resp) baseResp;
            switch (resp.errCode) {
                case BaseResp.ErrCode.ERR_OK:
                    String code = String.valueOf(resp.code);
                    //获取用户信息
                    getAccessToken(code);
                    break;
                case BaseResp.ErrCode.ERR_AUTH_DENIED://用户拒绝授权
                    Toast.makeText(WXEntryActivity.this, R.string.accredit_fail, Toast.LENGTH_LONG).show();
                    break;
                case BaseResp.ErrCode.ERR_USER_CANCEL://用户取消
                    Toast.makeText(WXEntryActivity.this, R.string.cancel_login, Toast.LENGTH_LONG).show();
                    break;
                default:
                    break;
            }
        } else {
            //分享成功回调
            switch (baseResp.errCode) {
                case BaseResp.ErrCode.ERR_OK:
                    //分享成功
                    Toast.makeText(WXEntryActivity.this, R.string.share_success, Toast.LENGTH_LONG).show();
                    break;
                case BaseResp.ErrCode.ERR_USER_CANCEL:
                    //分享取消
                    Toast.makeText(WXEntryActivity.this,R.string.cancel_share, Toast.LENGTH_LONG).show();
                    break;
                case BaseResp.ErrCode.ERR_AUTH_DENIED:
                    //分享拒绝
                    Toast.makeText(WXEntryActivity.this, R.string.share_arr_auth_denied, Toast.LENGTH_LONG).show();
                    break;
            }
        }
        finish();
    }

    private void getAccessToken(String code) {
        //获取授权
        String http = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + AppID + "&secret=" + AppSecret + "&code=" + code + "&grant_type=authorization_code";
        OkGo.<String>get(http)
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        OkLogger.i(response.body().toString());
                        WXAccessTokenBean wxAccessTokenBean = (WXAccessTokenBean) JsonUtil.parseStringToBean(response.body(), WXAccessTokenBean.class);
                        //获取个人信息
                        String getUserInfo = "https://api.weixin.qq.com/sns/userinfo?access_token=" + wxAccessTokenBean.getAccess_token() + "&openid=" + wxAccessTokenBean.getOpenid();
                        OkGo.<String>get(getUserInfo)
                                .tag(this)
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(Response<String> response) {
                                        OkLogger.i(response.body().toString());
                                        wechatInfoBean = (WechatInfoBean) JsonUtil.parseStringToBean(response.body(), WechatInfoBean.class);
                                        if (wechatInfoBean != null) {
                                            UserBean userBean = null;
                                            if (AppManager.getAppManager().currentActivity().getClass().equals(LoginActivity.class)) {
                                                userBean = MyApplication.getDaoInstant().getUserBeanDao().queryBuilder().where(UserBeanDao.Properties.Wallet_weixin.eq(wechatInfoBean.getOpenid())).build().unique();
                                                if (userBean != null) {
                                                    ToastUtils.showLongToast(R.string.bind_weixin_wallet_exist);
                                                    Utils.getSpUtils().put("firstUser", userBean.getWallet_phone());//保存上次登陆钱包
                                                    ActivityUtils.next(WXEntryActivity.this, MainActivity.class, true);
                                                } else {
                                                    ToastUtils.showLongToast(R.string.weixin_login_success);
                                                    Bundle bundle = new Bundle();
                                                    bundle.putString("openid", wechatInfoBean.getOpenid());
                                                    bundle.putString("type", "2");//1 代表 qq 2 代表微信
                                                    bundle.putParcelable("wechatInfoBean", wechatInfoBean);
                                                    ActivityUtils.next(WXEntryActivity.this, BindPhoneActivity.class, bundle);
                                                }
                                            } else if (AppManager.getAppManager().currentActivity().getClass().equals(OtherLoginTypeActivity.class)) {
                                                bindWeixin(wechatInfoBean.getOpenid(), wechatInfoBean.getNickname(), wechatInfoBean.getHeadimgurl());
                                            }
                                        }
                                    }

                                    @Override
                                    public void onError(Response<String> response) {
                                        super.onError(response);
                                    }
                                });
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });
    }

    public void bindWeixin(String openid, String name, String avatar) {
        OkGo.<String>post(BaseUrl.HTTP_bindwechat)
                .params("uid", MyApplication.getInstance().getUserBean().getWallet_uid())
                .params("openid", openid)
                .params("name", name)
                .params("avatar", avatar)
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        OkLogger.i(response.body().toString());
                        BaseBean baseBean = (BaseBean) JsonUtil.parseStringToBean(response.body(), BaseBean.class);
                        if (baseBean != null && baseBean.getCode().equals("0")) {
                            if (AppManager.getAppManager().currentActivity().getClass().equals(OtherLoginTypeActivity.class)) {
                                UserBean userBean = MyApplication.getDaoInstant().getUserBeanDao().queryBuilder().where(UserBeanDao.Properties.Wallet_phone.eq(MyApplication.getInstance().getUserBean().getWallet_phone())).build().unique();
                                userBean.setWallet_weixin(wechatInfoBean.getOpenid());
                                userBean.setWallet_name(wechatInfoBean.getNickname());
                                userBean.setWallet_img(wechatInfoBean.getHeadimgurl());
                                MyApplication.getDaoInstant().getUserBeanDao().update(userBean);
                                MyApplication.getInstance().getUserBean().setWallet_weixin(wechatInfoBean.getOpenid());
                                MyApplication.getInstance().getUserBean().setWallet_name(wechatInfoBean.getNickname());
                                MyApplication.getInstance().getUserBean().setWallet_img(wechatInfoBean.getHeadimgurl());
                                ToastUtils.showLongToast(R.string.weixin_bind_success);
                                AppManager.getAppManager().finishActivity(OtherLoginTypeActivity.class);
                            }
                        } else {
                            ShowDialog.dissmiss();
                            ToastUtils.showShortToast(baseBean.getMessage());
                        }
                        finish();
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });
    }

}
