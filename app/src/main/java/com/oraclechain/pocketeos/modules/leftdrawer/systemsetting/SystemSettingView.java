package com.oraclechain.pocketeos.modules.leftdrawer.systemsetting;

import com.oraclechain.pocketeos.base.BaseView;
import com.oraclechain.pocketeos.bean.SystemInfoBean;

/**
 * Created by pocketEos on 2018/1/18.
 */

public interface SystemSettingView extends BaseView {

    void getSystemInfoHttp(SystemInfoBean.DataBean systemInfoBean, String id);


    void getDataHttpFail(String msg);
}
