package com.oraclechain.pocketeos.modules.dapp.paidanswer.paidanswerhome.fragment;

import android.content.Context;

import com.google.gson.Gson;
import com.lzy.okgo.model.Response;
import com.oraclechain.pocketeos.base.BasePresent;
import com.oraclechain.pocketeos.base.BaseUrl;
import com.oraclechain.pocketeos.bean.GetAnswerListBean;
import com.oraclechain.pocketeos.bean.PaidAnswerBean;
import com.oraclechain.pocketeos.bean.ResponseBean;
import com.oraclechain.pocketeos.net.HttpUtils;
import com.oraclechain.pocketeos.net.callbck.JsonCallback;

/**
 * Created by pocketEos on 2017/12/26.
 */

public class PaidAnswerPresenter extends BasePresent<PaidAnswerView> {
    private Context mContext;

    public PaidAnswerPresenter(Context context) {
        this.mContext = context;
    }
    public void getData(int page , String releasedLable ,String askid ) {
        HttpUtils.postRequest(BaseUrl.HTTP_GetAsks, mContext, new Gson().toJson(new GetAnswerListBean(askid,new GetAnswerListBean.PageBean(page,10),releasedLable)), new JsonCallback<ResponseBean<PaidAnswerBean.DataBeanX>>() {
            @Override
            public void onSuccess(Response<ResponseBean<PaidAnswerBean.DataBeanX>> response) {
                if (response.body().code == 0) {
                    view.getQuestionListDataHttp(response.body().data);
                } else {
                    view.getDataHttpFail(response.body().message);
                }
            }
        });
    }

}
