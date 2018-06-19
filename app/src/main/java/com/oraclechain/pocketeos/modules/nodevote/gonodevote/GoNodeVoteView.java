package com.oraclechain.pocketeos.modules.nodevote.gonodevote;

import com.oraclechain.pocketeos.base.BaseView;
import com.oraclechain.pocketeos.bean.ResultNodeListBean;

/**
 * Created by pocketEos on 2017/12/26.
 */
public interface GoNodeVoteView extends BaseView {

    void getNodeListDataHttp(ResultNodeListBean resultNodeListBean);

    void getDataHttpFail(String msg);
}
