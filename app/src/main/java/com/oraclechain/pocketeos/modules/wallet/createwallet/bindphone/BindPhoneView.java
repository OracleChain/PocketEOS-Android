package com.oraclechain.pocketeos.modules.wallet.createwallet.bindphone;

import com.oraclechain.pocketeos.base.BaseView;
import com.oraclechain.pocketeos.bean.CodeAuthBean;

/**
 * Created by pocketEos on 2018/1/18.
 */

public interface BindPhoneView extends BaseView {

    void getCodeDataHttp(String msg);

    void getCodeAuthDataHttp(CodeAuthBean.DataBean codeAuthBean);

    void getDataHttpFail(String msg);
}
