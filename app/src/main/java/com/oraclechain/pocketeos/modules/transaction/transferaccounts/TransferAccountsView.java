package com.oraclechain.pocketeos.modules.transaction.transferaccounts;

import com.oraclechain.pocketeos.base.BaseView;
import com.oraclechain.pocketeos.bean.AccountDetailsBean;
import com.oraclechain.pocketeos.bean.CoinRateBean;
import com.oraclechain.pocketeos.bean.TransferHistoryBean;

/**
 * Created by pocketEos on 2017/12/26.
 */
public interface TransferAccountsView extends BaseView {
    void getCoinRateDataHttp(CoinRateBean.DataBean coinRateBean);

    void getAccountDetailsDataHttp(AccountDetailsBean accountDetailsBean);


    void getTransferHistoryDataHttp(TransferHistoryBean.DataBeanX transferHistoryBean);

    void getDataHttpFail(String msg);
}
