package com.oraclechain.pocketeos.modules.wallet.walletmanagement;

import com.oraclechain.pocketeos.base.BaseView;
import com.oraclechain.pocketeos.bean.BaseBean;

/**
 * Created by pocketEos on 2018/1/18.
 */

public interface WalletManagementView extends BaseView {

    void setPolicyAccountHttp(BaseBean baseBean, int position);


    void getDataHttpFail(String msg);
}
