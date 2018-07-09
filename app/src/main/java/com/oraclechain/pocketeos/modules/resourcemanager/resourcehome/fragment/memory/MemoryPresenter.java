package com.oraclechain.pocketeos.modules.resourcemanager.resourcehome.fragment.memory;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.oraclechain.pocketeos.base.BasePresent;
import com.oraclechain.pocketeos.base.BaseUrl;
import com.oraclechain.pocketeos.bean.BlockChainAccountInfoBean;
import com.oraclechain.pocketeos.bean.ResponseBean;
import com.oraclechain.pocketeos.net.HttpUtils;
import com.oraclechain.pocketeos.net.callbck.JsonCallback;

import java.util.HashMap;

/**
 * Created by pocketEos on 2017/12/26.
 */

public class MemoryPresenter extends BasePresent<MemoryView> {
    private Context mContext;

    public MemoryPresenter(Context context) {
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


}
