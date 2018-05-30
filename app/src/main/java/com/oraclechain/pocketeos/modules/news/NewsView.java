package com.oraclechain.pocketeos.modules.news;

import com.oraclechain.pocketeos.base.BaseView;
import com.oraclechain.pocketeos.bean.CoinBean;
import com.oraclechain.pocketeos.bean.NewsBean;

import java.util.List;

/**
 * Created by pocketEos on 2017/12/26.
 * 获取friendslist
 */

public interface NewsView extends BaseView {

    void getNewsDataHttp(List<NewsBean.DataBean> newsBean);

    void getBannerDataHttp(List<NewsBean.DataBean> newsBean);

    void getCoinTypeDataHttp(List<CoinBean.DataBean> coinBeen);

    void getDataHttpFail(String msg);
}
