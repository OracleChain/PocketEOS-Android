package com.oraclechain.pocketeos.modules.leftdrawer.usercenter.changename;

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

public class ChangeNamePresenter extends BasePresent<ChangeNameView> {

    private Context mContext;

    public ChangeNamePresenter(Context context) {
        this.mContext = context;
    }
    public void HTTP_updateNameData(String userName) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("uid",  MyApplication.getInstance().getUserBean().getWallet_uid());
        hashMap.put("userName", userName);
        HttpUtils.postRequest(BaseUrl.HTTP_updateName, mContext, hashMap, new JsonCallback<ResponseBean<String>>() {
            @Override
            public void onSuccess(Response<ResponseBean<String>> response) {
                if (response.body().code == 0) {
                    view.updateNameDataHttp();
                } else {
                    view.getDataHttpFail(response.body().message);
                }
            }
        });
    }

}

