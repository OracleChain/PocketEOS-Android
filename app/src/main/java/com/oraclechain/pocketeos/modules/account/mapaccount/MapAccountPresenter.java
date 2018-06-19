package com.oraclechain.pocketeos.modules.account.mapaccount;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.oraclechain.pocketeos.app.MyApplication;
import com.oraclechain.pocketeos.base.BasePresent;
import com.oraclechain.pocketeos.base.BaseUrl;
import com.oraclechain.pocketeos.bean.GetAccountsBean;
import com.oraclechain.pocketeos.bean.ResponseBean;
import com.oraclechain.pocketeos.net.HttpUtils;
import com.oraclechain.pocketeos.net.callbck.JsonCallback;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by pocketEos on 2017/12/26.
 */

public class MapAccountPresenter extends BasePresent<MapAccountView> {
    private Context mContext;

    public MapAccountPresenter(Context context) {
        this.mContext = context;
    }

    public void getAccountInfoData(String public_key) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("public_key", public_key);
        HttpUtils.postRequest(BaseUrl.getHTTP_GetAccounts, mContext, new JSONObject(hashMap).toString(), new JsonCallback<GetAccountsBean>() {
            @Override
            public void onSuccess(Response<GetAccountsBean> response) {
                    view.getBlackAccountDataHttp(response.body());
            }
        });


    }

    public void postEosAccountData(String eosAccountName, String uid) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("uid", uid);
        hashMap.put("eosAccountName", eosAccountName);
        HttpUtils.postRequest(BaseUrl.HTTP_add_new_eos, mContext, hashMap, new JsonCallback<ResponseBean<String>>() {
            @Override
            public void onSuccess(Response<ResponseBean<String>> response) {
                if (response.body().code == 0) {
                    view.postEosAccountDataHttp();
                } else {
                    view.getDataHttpFail(response.body().message);
                }
            }
        });
    }

    public void setMianAccountData(String eosAccountName) {

        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("uid", MyApplication.getInstance().getUserBean().getWallet_uid());
        hashMap.put("eosAccountName", eosAccountName);
        HttpUtils.postRequest(BaseUrl.HTTP_set_mian_account, mContext, hashMap, new JsonCallback<ResponseBean<String>>() {
            @Override
            public void onSuccess(Response<ResponseBean<String>> response) {
                if (response.body().code == 0) {
                    view.setMainAccountHttp();
                } else {
                    view.getDataHttpFail(response.body().message);
                }
            }
        });
    }
}
