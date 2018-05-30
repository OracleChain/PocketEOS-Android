package com.oraclechain.pocketeos.modules.leftdrawer.usercenter.otherlogintype;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.oraclechain.pocketeos.app.MyApplication;
import com.oraclechain.pocketeos.base.BasePresent;
import com.oraclechain.pocketeos.base.BaseUrl;
import com.oraclechain.pocketeos.net.HttpUtils;
import com.oraclechain.pocketeos.bean.ResponseBean;
import com.oraclechain.pocketeos.net.callbck.JsonCallback;

import java.util.HashMap;

/**
 * Created by pocketEos on 2018/1/18.
 */

public class OtherLoginTypePresenter extends BasePresent<OtherLoginTypeView> {
    private Context mContext;

    public OtherLoginTypePresenter(Context context) {
        this.mContext = context;
    }

    public void bindQQ(String openid, String name, String avatar) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("uid", MyApplication.getInstance().getUserBean().getWallet_uid());
        hashMap.put("openid", openid);
        hashMap.put("name", name);
        hashMap.put("avatar", avatar);
        HttpUtils.postRequest(BaseUrl.HTTP_bindQQ, mContext, hashMap, new JsonCallback<ResponseBean<String>>() {
            @Override
            public void onSuccess(Response<ResponseBean<String>> response) {
                if (response.body().code == 0) {
                    view.bindOtherLoginDataHttp();
                } else {
                    view.getDataHttpFail(response.body().message);
                }
            }
        });
    }

    public void unbindQQ() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("uid", MyApplication.getInstance().getUserBean().getWallet_uid());
        HttpUtils.postRequest(BaseUrl.HTTP_unbindQQ, mContext, hashMap, new JsonCallback<ResponseBean<String>>() {
            @Override
            public void onSuccess(Response<ResponseBean<String>> response) {
                if (response.body().code == 0) {
                    view.unBindOtherLoginDataHttp();
                } else {
                    view.getDataHttpFail(response.body().message);
                }
            }
        });
    }

    public void unbindWechat() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("uid", MyApplication.getInstance().getUserBean().getWallet_uid());
        HttpUtils.postRequest(BaseUrl.HTTP_unbindWechat, mContext, hashMap, new JsonCallback<ResponseBean<String>>() {
            @Override
            public void onSuccess(Response<ResponseBean<String>> response) {
                if (response.body().code == 0) {
                    view.unBindOtherLoginDataHttp();
                } else {
                    view.getDataHttpFail(response.body().message);
                }
            }
        });
    }
}

