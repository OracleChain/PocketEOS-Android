package com.oraclechain.pocketeos.base;

/**
 * Created by pocketEos on 2017/7/14.
 */

public interface BaseView {

    /**
     * 显示loading框
     */
    void showProgress();

    /**
     * 隐藏loading框
     */
    void hideProgress();

    void toast(CharSequence s);


    /**
     * 显示空数据布局
     */
    void showNullLayout();

    /**
     * 隐藏空数据布局
     */
    void hideNullLayout();

    /**
     * 显示异常布局
     */
    void showErrorLayout();

    void hideErrorLayout();

}

