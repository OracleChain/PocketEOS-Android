package com.oraclechain.pocketeos.modules.transaction.makecollections;

import com.oraclechain.pocketeos.base.BaseView;
import com.oraclechain.pocketeos.bean.CoinRateBean;
import com.oraclechain.pocketeos.bean.TransferHistoryBean;

/**
 * Created by pocketEos on 2017/12/26.
 */
public interface MakeCollectionsView extends BaseView {
    void getCoinRateDataHttp(CoinRateBean.DataBean coinRateBean);

    void getTransferHistoryDataHttp(TransferHistoryBean.DataBeanX transferHistoryBean);


    void getDataHttpFail(String msg);
}
