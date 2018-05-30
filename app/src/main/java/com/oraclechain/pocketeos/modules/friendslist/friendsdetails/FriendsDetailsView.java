package com.oraclechain.pocketeos.modules.friendslist.friendsdetails;

import com.oraclechain.pocketeos.base.BaseView;
import com.oraclechain.pocketeos.bean.AccountWithCoinBean;
import com.oraclechain.pocketeos.bean.WalletDetailsBean;

import java.util.List;

/**
 * Created by pocketEos on 2018/1/18.
 */

public interface FriendsDetailsView extends BaseView {

    void getWalletDetailsDataHttp(List<WalletDetailsBean.DataBean> walletDetailsBean);

    void getAccountDetailsDataHttp(List<AccountWithCoinBean> mAccountWithCoinBeen);

    void getAddFollowsDataHttp();

    void getCancelFollow();

    void isfollow(Boolean isFollows);

    void getDataHttpFail(String msg);
}
