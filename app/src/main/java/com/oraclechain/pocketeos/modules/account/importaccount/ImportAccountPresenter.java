package com.oraclechain.pocketeos.modules.account.importaccount;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.oraclechain.pocketeos.app.MyApplication;
import com.oraclechain.pocketeos.base.BasePresent;
import com.oraclechain.pocketeos.base.BaseUrl;
import com.oraclechain.pocketeos.bean.BlockChainAccountInfoBean;
import com.oraclechain.pocketeos.net.HttpUtils;
import com.oraclechain.pocketeos.bean.ResponseBean;
import com.oraclechain.pocketeos.net.callbck.JsonCallback;

import java.util.HashMap;

/**
 * Created by pocketEos on 2017/12/26.
 */

public class ImportAccountPresenter extends BasePresent<ImportAccountView> {
    private Context mContext;

    public ImportAccountPresenter(Context context) {
        this.mContext = context;
    }

    public void getAccountInfoData(String accountname) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("name", accountname);
        HttpUtils.postRequest(BaseUrl.HTTP_get_chain_account_info, mContext, hashMap, new JsonCallback<ResponseBean<BlockChainAccountInfoBean.DataBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<BlockChainAccountInfoBean.DataBean>> response) {
                if (response.body().code == 0) {
                    view.getBlockchainAccountInfoDataHttp(response.body().data);
                } else {
                    view.getDataHttpFail(response.body().message);
                }
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

    public void setMianAccountData(String eosAccountName) {//0代表直接执行设置主账号操作，1代表先删除后设置主账号

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
