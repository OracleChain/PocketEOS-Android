package com.oraclechain.pocketeos.modules.wallet.createwallet.login;

import com.oraclechain.pocketeos.base.BaseView;
import com.oraclechain.pocketeos.bean.CodeAuthBean;

/**
 * Created by pocketEos on 2018/1/18.
 */

public interface LoginView extends BaseView {

    void getCodeDataHttp(String message);

    void getCodeAuthDataHttp(CodeAuthBean.DataBean codeAuthBean);

    void getDataHttpFail(String msg);
}
