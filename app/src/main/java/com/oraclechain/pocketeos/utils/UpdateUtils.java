package com.oraclechain.pocketeos.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.oraclechain.pocketeos.base.BaseUrl;
import com.oraclechain.pocketeos.bean.UpdateAppBean;
import com.oraclechain.pocketeos.view.dialog.updatadialog.UpdataCallback;
import com.oraclechain.pocketeos.view.dialog.updatadialog.UpdataDialog;

/**
 * Created by pocketEos on 2018/2/10.
 */

public class UpdateUtils {
    /**
     * 获取apk的版本号 currentVersionCode
     *
     * @param ctx
     * @return
     */
    public static int getAPPLocalVersion(Context ctx) {
        int currentVersionCode = 0;
        PackageManager manager = ctx.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(ctx.getPackageName(), 0);
            String appVersionName = info.versionName; // 版本名
            currentVersionCode = info.versionCode; // 版本号
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return currentVersionCode;
    }

    /**
     * 获取服务器的版本号
     *
     * @param context  上下文
     * @param callBack 为了控制线程，需要获取服务器版本号成功的回掉
     */
    public static void getAPPServerVersion(final Context context, final VersionCallBack callBack) {
        //获取服务器最新版本号
        OkGo.<String>get(BaseUrl.HTTP_get_app_info)
                .tag(context)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        UpdateAppBean updateAppBean = (UpdateAppBean) JsonUtil.parseStringToBean(response.body(), UpdateAppBean.class);
                        if (updateAppBean.getCode() == 0) {
                            callBack.callBack(Integer.parseInt(updateAppBean.getData().getVersionCode()), updateAppBean.getData().getUploadUrl(),
                                    updateAppBean.getData().getVersionName(), updateAppBean.getData().getVersionDetail());//数字110代表1.1.0
                        }
                    }
                });
    }

    /**
     * 更新APP
     *
     * @param context
     */
    public static void updateApp(final Context context, final int status) {
        getAPPServerVersion(context, new VersionCallBack() {
            @Override
            public void callBack(final int versionCode, final String apkurl, final String versionName, final String versionDetail) {
                if (versionCode > 0) {
                    if (versionCode > getAPPLocalVersion(context)) {

                        UpdataDialog dialog = new UpdataDialog(context, new UpdataCallback() {
                            @Override
                            public void goData() {
                                Intent intent = new Intent(context, DownLoadServerice.class);
                                intent.putExtra("url", apkurl);
                                intent.putExtra("versionName", versionName);
                                context.startService(intent);
                            }

                        });
                        dialog.setContent(versionDetail);
                        dialog.setCancelable(false);
                        dialog.show();
                    } else {
                        if (status == 1) {
                            Toast.makeText(context, "您已是最新版本", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });


    }

    public interface VersionCallBack {
        void callBack(int versionCode, String apkurl, String versionName, String versionDetail);
    }
}
