package com.oraclechain.pocketeos.modules.transaction.redpacket.getredpacketdetails;

import com.oraclechain.pocketeos.base.BaseView;
import com.oraclechain.pocketeos.bean.RedPacketDetailsBean;

/**
 * Created by pocketEos on 2017/12/26.
 */
public interface GetRedPacketDetailsView extends BaseView {
    void getRedPacketDetailsDataHttp(RedPacketDetailsBean.DataBean dataBean);

    void getDataHttpFail(String msg);
}
