package com.oraclechain.pocketeos.modules.nodevote;

import com.oraclechain.pocketeos.base.BaseView;
import com.oraclechain.pocketeos.bean.AccountDetailsBean;
import com.oraclechain.pocketeos.bean.ResultTableRowBean;

/**
 * Created by pocketEos on 2017/12/26.
 */
public interface NodeVoteView extends BaseView {

    void getAccountVoteDataHttp(ResultTableRowBean resultTableRowBean);

    void getAccountDetailsDataHttp(AccountDetailsBean accountDetailsBean);

    void getDataHttpFail(String msg);
}
