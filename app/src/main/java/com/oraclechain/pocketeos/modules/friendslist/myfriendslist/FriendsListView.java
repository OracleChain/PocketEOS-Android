package com.oraclechain.pocketeos.modules.friendslist.myfriendslist;

import com.oraclechain.pocketeos.base.BaseView;
import com.oraclechain.pocketeos.bean.User;

import java.util.ArrayList;

/**
 * Created by pocketEos on 2017/12/26.
 * 获取friendslist
 */

public interface FriendsListView extends BaseView {

    void getDataHttp(ArrayList<User> mDataBeanArrayList);


    void getDataHttpFail(String msg);
}
