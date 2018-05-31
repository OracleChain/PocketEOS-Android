package com.oraclechain.pocketeos.modules.account.accountdetails;

import com.oraclechain.pocketeos.base.BaseView;

/**
 * Created by pocketEos on 2018/1/18.
 */

public interface AccountDetailsView extends BaseView {

    void setMainAccountHttp(int type);


    void getDataHttpFail(String msg);


}
