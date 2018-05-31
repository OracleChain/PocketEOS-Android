package com.oraclechain.pocketeos.modules.leftdrawer.usercenter;

import com.oraclechain.pocketeos.base.BaseView;
import com.oraclechain.pocketeos.bean.UpdataPhotoBean;

/**
 * Created by pocketEos on 2018/1/18.
 */

public interface UserCenterView extends BaseView {

    void headImgUploadaDataHttp(UpdataPhotoBean updataPhotoBean);


    void getDataHttpFail(String msg);
}
