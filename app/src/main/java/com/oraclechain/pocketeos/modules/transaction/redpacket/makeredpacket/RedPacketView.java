package com.oraclechain.pocketeos.modules.transaction.redpacket.makeredpacket;

import com.oraclechain.pocketeos.base.BaseView;
import com.oraclechain.pocketeos.bean.CoinRateBean;
import com.oraclechain.pocketeos.bean.RedPacketHistoryBean;
import com.oraclechain.pocketeos.bean.SendRedPacketBean;

import java.util.List;

/**
 * Created by pocketEos on 2017/12/26.
 */
public interface RedPacketView extends BaseView {
    void getCoinRateDataHttp(CoinRateBean.DataBean coinRateBean);

    void getRedPacketHistoryDataHttp(List<RedPacketHistoryBean.DataBean> dataBeanList);

    void sendRedPacketDataHttp(SendRedPacketBean.DataBean sendRedPacketBean);


    void getDataHttpFail(String msg);
}
