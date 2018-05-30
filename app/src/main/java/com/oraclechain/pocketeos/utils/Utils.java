package com.oraclechain.pocketeos.utils;

import android.content.Context;
import android.content.Intent;

import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.service.KillSelfService;

import java.util.List;

import me.ljp.permission.HiPermission;
import me.ljp.permission.PermissionCallback;
import me.ljp.permission.PermissionItem;

/**
 * Created by pocketEos on 2017/11/23.
 * Utils初始化相关
 */
public final class Utils {

    private static Context context;
    private static SPUtils spUtils;


    private Utils() {
        throw new UnsupportedOperationException("...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        Utils.context = context.getApplicationContext();
        spUtils = new SPUtils("USER");
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext context
     */
    public static Context getContext() {
        if (context != null) {
            return context;
        }
        throw new NullPointerException("应该首先初始化");
    }

    /**
     * Gets sp utils.
     *
     * @return the sp utils
     */
    public static SPUtils getSpUtils() {
        return spUtils;
    }

    public static boolean getPermissions(List<PermissionItem> permissonItems, String msg) {
        final Boolean[] isGetPermissions = {false};
        HiPermission.create(context)
                .permissions(permissonItems)
                .title(context.getResources().getString(R.string.dear_user))
                .msg(msg)
                .animStyle(R.style.PermissionAnimScale)
                .style(R.style.PermissionThemeStyle)
                .checkMutiPermission(new PermissionCallback() {
                    @Override
                    public void onClose() {
                        isGetPermissions[0] = false;
                        ToastUtils.showShortToast(context.getResources().getString(R.string.close_permisson_toast));
                    }

                    @Override
                    public void onFinish() {
                        isGetPermissions[0] = true;
                    }

                    @Override
                    public void onDeny(String permisson, int position) {
                    }

                    @Override
                    public void onGuarantee(String permisson, int position) {
                    }
                });
        return isGetPermissions[0];
    }

    /**
     * 重启整个APP
     * @param Delayed 延迟多少毫秒
     */
    public static void restartAPP(long Delayed){

        /**开启一个新的服务，用来重启本APP*/
        Intent intent1=new Intent(context,KillSelfService.class);
        intent1.putExtra("PackageName",context.getPackageName());
        intent1.putExtra("Delayed",Delayed);
        context.startService(intent1);

        /**杀死整个进程**/
        android.os.Process.killProcess(android.os.Process.myPid());
    }



}
