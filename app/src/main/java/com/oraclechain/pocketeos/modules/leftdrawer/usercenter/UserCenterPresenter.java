package com.oraclechain.pocketeos.modules.leftdrawer.usercenter;

import android.content.Context;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.utils.OkLogger;
import com.oraclechain.pocketeos.app.MyApplication;
import com.oraclechain.pocketeos.base.BasePresent;
import com.oraclechain.pocketeos.base.BaseUrl;
import com.oraclechain.pocketeos.bean.UpdataPhotoBean;
import com.oraclechain.pocketeos.utils.JsonUtil;
import com.oraclechain.pocketeos.utils.ToastUtils;

import java.io.File;

/**
 * Created by pocketEos on 2018/1/18.
 */

public class UserCenterPresenter extends BasePresent<UserCenterView> {
    private Context mContext;

    public UserCenterPresenter(Context context) {
        this.mContext = context;
    }

    public void headImgUploadaData(String imagePath) {
        OkGo.<String>post(BaseUrl.HTTP_headImgUploada)
                .params("uid", MyApplication.getInstance().getUserBean().getWallet_uid())
                .params("file", new File(imagePath))
                .tag(mContext)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        OkLogger.i(response.body().toString());
                        UpdataPhotoBean updataPhotoBean = (UpdataPhotoBean) JsonUtil.parseStringToBean(response.body(), UpdataPhotoBean.class);
                        if (updataPhotoBean != null && updataPhotoBean.getCode().equals("0")) {
                            view.headImgUploadaDataHttp(updataPhotoBean);
                        } else {
                            ToastUtils.showShortToast(updataPhotoBean.getMessage());
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        view.getDataHttpFail(response.message());
                    }
                });
    }

}

