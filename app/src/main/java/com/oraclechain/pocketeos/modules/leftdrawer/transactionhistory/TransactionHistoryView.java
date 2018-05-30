package com.oraclechain.pocketeos.modules.leftdrawer.transactionhistory;

import com.oraclechain.pocketeos.base.BaseView;
import com.oraclechain.pocketeos.bean.TransferHistoryBean;

/**
 * Created by pocketEos on 2017/12/26.
 */
public interface TransactionHistoryView extends BaseView {


    void getTransferHistoryDataHttp(TransferHistoryBean.DataBeanX transferHistoryBean);


    void getDataHttpFail(String msg);
}
