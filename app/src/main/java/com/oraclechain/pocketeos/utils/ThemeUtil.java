package com.oraclechain.pocketeos.utils;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.oraclechain.pocketeos.R;

/**
 * Created by pocketEos on 2018/5/16.
 */

public class ThemeUtil {
    public ThemeUtil() {
    }

    public static void setTheme(@NonNull Activity activity) {
        if (Utils.getSpUtils().getString("loginmode") != null) {
            boolean isLight = Utils.getSpUtils().getString("loginmode").equals("phone");
            activity.setTheme(isLight ? R.style.ThemeLight : R.style.ThemeDark);
        }
    }

    public static void reCreate(@NonNull final Activity activity) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activity.recreate();
            }
        });

    }
}

