package com.oraclechain.pocketeos.modules.dapp.paidanswer.questiondetails;

import com.oraclechain.pocketeos.base.BaseView;
import com.oraclechain.pocketeos.bean.ChainInfoBean;
import com.oraclechain.pocketeos.bean.GetChainJsonBean;
import com.oraclechain.pocketeos.bean.GetRequiredKeysBean;

/**
 * Created by pocketEos on 2017/12/26.
 */
public interface QuestionDetailsView extends BaseView {


    void getChainInfoHttp(ChainInfoBean.DataBean chainInfoBean);

    void getChainJsonHttp(GetChainJsonBean.DataBean getChainJsonBean);

    void getRequiredKeysHttp(GetRequiredKeysBean.DataBean getRequiredKeysBean);

    void pushtransactionHttp();

    void getDataHttpFail(String msg);
}
