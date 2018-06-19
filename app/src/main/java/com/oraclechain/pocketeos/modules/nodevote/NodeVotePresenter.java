package com.oraclechain.pocketeos.modules.nodevote;

import android.content.Context;

import com.google.gson.Gson;
import com.lzy.okgo.model.Response;
import com.oraclechain.pocketeos.base.BasePresent;
import com.oraclechain.pocketeos.base.BaseUrl;
import com.oraclechain.pocketeos.bean.AccountDetailsBean;
import com.oraclechain.pocketeos.bean.RequestTableRowsBean;
import com.oraclechain.pocketeos.bean.ResponseBean;
import com.oraclechain.pocketeos.bean.ResultTableRowBean;
import com.oraclechain.pocketeos.net.HttpUtils;
import com.oraclechain.pocketeos.net.callbck.JsonCallback;

import java.util.HashMap;

/**
 * Created by pocketEos on 2017/12/26.
 */

public class NodeVotePresenter extends BasePresent<NodeVoteView> {
    private Context mContext;

    public NodeVotePresenter(Context context) {
        this.mContext = context;
    }

    public void getAccountVoteData(String account) {
        RequestTableRowsBean requestTableRowsBean = new RequestTableRowsBean();
        requestTableRowsBean.setAccountNameStr(account);
        HttpUtils.postRequest(BaseUrl.getHTTP_GetMyVoteInfo, mContext, new Gson().toJson(requestTableRowsBean), new JsonCallback<ResultTableRowBean>() {
            @Override
            public void onSuccess(Response<ResultTableRowBean> response) {
                view.getAccountVoteDataHttp(response.body());
            }
        });


        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("name", account);
        HttpUtils.postRequest(BaseUrl.HTTP_eos_get_account, mContext, hashMap, new JsonCallback<ResponseBean<AccountDetailsBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<AccountDetailsBean>> response) {
                if (response.body().code == 0) {
                    view.getAccountDetailsDataHttp(response.body().data);
                } else {
                    view.getDataHttpFail(response.body().message);
                }
            }
        });
    }

}
