package com.oraclechain.pocketeos.modules.account.createaccount;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.oraclechain.pocketeos.app.MyApplication;
import com.oraclechain.pocketeos.base.BasePresent;
import com.oraclechain.pocketeos.base.BaseUrl;
import com.oraclechain.pocketeos.bean.EosRegisterBean;
import com.oraclechain.pocketeos.net.HttpUtils;
import com.oraclechain.pocketeos.bean.ResponseBean;
import com.oraclechain.pocketeos.net.callbck.JsonCallback;

import java.util.HashMap;

/**
 * Created by pocketEos on 2018/1/18.
 */

public class CreateAccountPresenter extends BasePresent<CreateAccountView> {

    private Context mContext;

    public CreateAccountPresenter(Context context) {
        this.mContext = context;
    }


    public void getRegisterData(String username, String owner_key, String active_key) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("account_name", username);
        hashMap.put("owner_key", owner_key);
        hashMap.put("active_key", active_key);
        HttpUtils.postRequest(BaseUrl.HTTP_eos_register, mContext, hashMap, new JsonCallback<ResponseBean<EosRegisterBean.DataBeanX>>() {
            @Override
            public void onSuccess(Response<ResponseBean<EosRegisterBean.DataBeanX>> response) {
                if (response.body().code == 0) {
                    view.getEosRegisterhDataHttp(response.body().data);
                } else {
                    view.getDataHttpFail(response.body().message);
                }
            }
        });
    }

    public void postEosAccountData(String eosAccountName) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("uid", MyApplication.getInstance().getUserBean().getWallet_uid());
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

