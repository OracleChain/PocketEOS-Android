package com.oraclechain.pocketeos.modules.transaction.redpacket.anticipationredpacket;

import com.oraclechain.pocketeos.base.BaseView;
import com.oraclechain.pocketeos.bean.AuthRedPacketBean;

/**
 * Created by pocketEos on 2017/12/26.
 */
public interface AnticipationRedPacketView extends BaseView {
    void getAuthRedPacketDataHttp(AuthRedPacketBean.DataBean dataBean);

    void getDataHttpFail(String msg);
}
