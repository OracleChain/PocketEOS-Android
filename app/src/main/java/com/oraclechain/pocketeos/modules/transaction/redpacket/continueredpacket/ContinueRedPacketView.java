package com.oraclechain.pocketeos.modules.transaction.redpacket.continueredpacket;

import com.oraclechain.pocketeos.base.BaseView;
import com.oraclechain.pocketeos.bean.CoinRateBean;
import com.oraclechain.pocketeos.bean.RedPacketDetailsBean;

/**
 * Created by pocketEos on 2017/12/26.
 */
public interface ContinueRedPacketView extends BaseView {
    void getCoinRateDataHttp(CoinRateBean.DataBean coinRateBean);

    void getRedPacketDetailsDataHttp(RedPacketDetailsBean.DataBean dataBean);

    void getDataHttpFail(String msg);
}
