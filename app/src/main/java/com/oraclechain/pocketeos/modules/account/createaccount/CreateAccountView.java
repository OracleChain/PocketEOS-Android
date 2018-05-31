package com.oraclechain.pocketeos.modules.account.createaccount;

import com.oraclechain.pocketeos.base.BaseView;
import com.oraclechain.pocketeos.bean.EosRegisterBean;

/**
 * Created by pocketEos on 2018/1/18.
 */

public interface CreateAccountView extends BaseView {

    void getEosRegisterhDataHttp(EosRegisterBean.DataBeanX eosRegisterBean);
    void postEosAccountDataHttp();
    void getDataHttpFail(String msg);
    void setMainAccountHttp();
}
