package com.oraclechain.pocketeos.modules.resourcemanager.changememory;

import com.oraclechain.pocketeos.base.BaseView;
import com.oraclechain.pocketeos.bean.AccountDetailsBean;
import com.oraclechain.pocketeos.bean.TableResultBean;

/**
 * Created by pocketEos on 2017/12/26.
 */
public interface ChangeMemoryView extends BaseView {


    void getAccountDetailsDataHttp(AccountDetailsBean accountDetailsBean);

    void getTableDataHttp(TableResultBean.DataBean dataBean);

    void getDataHttpFail(String msg);
}
