package com.oraclechain.pocketeos.modules.nodevote.surevote;

import com.oraclechain.pocketeos.base.BaseView;
import com.oraclechain.pocketeos.bean.ResponseBean;
import com.oraclechain.pocketeos.bean.ResultTableRowBean;
import com.oraclechain.pocketeos.bean.ResultVoteWeightBean;

/**
 * Created by pocketEos on 2017/12/26.
 */
public interface SureNodeVoteView extends BaseView {

    void getNowVoteWeightDataHttp(ResultVoteWeightBean resultVoteWeightBean);

    void getAccountVoteDataHttp(ResultTableRowBean resultTableRowBean);

    void postVoteTask(ResponseBean<String> data);

    void getDataHttpFail(String msg);
}
