package com.oraclechain.pocketeos.modules.leftdrawer.usercenter.otherlogintype;

import com.oraclechain.pocketeos.base.BaseView;

/**
 * Created by pocketEos on 2018/1/18.
 */

public interface OtherLoginTypeView extends BaseView {

    void unBindOtherLoginDataHttp();

    void bindOtherLoginDataHttp();

    void getDataHttpFail(String msg);
}
