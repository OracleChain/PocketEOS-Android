package com.oraclechain.pocketeos.modules.dapp;

import com.oraclechain.pocketeos.base.BaseView;
import com.oraclechain.pocketeos.bean.DappBean;
import com.oraclechain.pocketeos.bean.DappCommpanyBean;

import java.util.List;

/**
 * Created by pocketEos on 2017/12/26.
 * 获取friendslist
 */

public interface DappView extends BaseView {
    void getDappCommpanyDataHttp(List<DappCommpanyBean.DataBean> dappCommpanyBean);

    void getDappDataHttp(List<DappBean.DataBean> dappBean);

    void getDataHttpFail(String msg);
}
