package com.oraclechain.pocketeos.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

/**
 * 杀死进程自我重启
 */
public class KillSelfService extends Service {
    /**关闭应用后多久重新启动*/
    private static  long stopDelayed=2000;
    private Handler handler;
    private String PackageName;
    public KillSelfService() {
        handler=new Handler();
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        stopDelayed=intent.getLongExtra("Delayed",10);
        PackageName=intent.getStringExtra("PackageName");
        handler.postDelayed(()->{
            Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(PackageName);
            startActivity(LaunchIntent);
            KillSelfService.this.stopSelf();
        },stopDelayed);
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
