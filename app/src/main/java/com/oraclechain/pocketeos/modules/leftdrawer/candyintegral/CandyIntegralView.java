package com.oraclechain.pocketeos.modules.leftdrawer.candyintegral;

import com.oraclechain.pocketeos.base.BaseView;
import com.oraclechain.pocketeos.bean.CandyScoreBean;
import com.oraclechain.pocketeos.bean.CandyUserTaskBean;
import com.oraclechain.pocketeos.bean.HotEquitiesBean;

import java.util.List;

/**
 * Created by pocketEos on 2017/12/26.
 * 获取friendslist
 */
public interface CandyIntegralView extends BaseView {

    void getCandyScoreDataHttp(CandyScoreBean.DataBean messageBean);

    void getHotEquitiesDataHttp(List<HotEquitiesBean.DataBean> mDataBeans);

    void getCandyTaskDataHttp(List<CandyUserTaskBean.DataBean> mDataBeans);

    void getDataHttpFail(String msg);
}
