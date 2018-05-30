package com.oraclechain.pocketeos.modules.coindetails;

import com.oraclechain.pocketeos.base.BaseView;
import com.oraclechain.pocketeos.bean.SparkLinesBean;
import com.oraclechain.pocketeos.bean.TransferHistoryBean;

/**
 * Created by pocketEos on 2017/12/26.
 */
public interface CoinDetailsView extends BaseView {


    void getTransferHistoryDataHttp(TransferHistoryBean.DataBeanX transferHistoryBean);

    void getSparklinesData(SparkLinesBean.DataBean dataBean);


    void getDataHttpFail(String msg);
}
