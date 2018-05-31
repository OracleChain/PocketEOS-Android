package com.oraclechain.pocketeos.view.webview;

import android.content.Context;
import android.os.Build;
import android.webkit.WebSettings;

/**
 * Created by pocketEos on 2018/4/11.
 */

public class BaseWebSetting {
    BaseWebView mBaseWebView;
    Context mContext;

    public BaseWebSetting(BaseWebView baseWebView, Context context) {
        mBaseWebView = baseWebView;
        mContext = context;
        initWebSettings();
    }

    private void initWebSettings() {
        WebSettings webSettings = mBaseWebView.getSettings();
        if (webSettings == null) return;
        //设置字体缩放倍数，默认100
        webSettings.setTextZoom(100);
        // 支持 Js 使用
        webSettings.setJavaScriptEnabled(true);
        // 开启DOM缓存
        webSettings.setDomStorageEnabled(true);
        // 开启数据库缓存
        webSettings.setDatabaseEnabled(true);
        // 支持自动加载图片
        webSettings.setLoadsImagesAutomatically(hasKitkat());
        // 设置 WebView 的缓存模式
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        // 支持启用缓存模式
        webSettings.setAppCacheEnabled(true);
        // 设置 AppCache 最大缓存值(现在官方已经不提倡使用，已废弃)
        webSettings.setAppCacheMaxSize(8 * 1024 * 1024);
        // Android 私有缓存存储，如果你不调用setAppCachePath方法，WebView将不会产生这个目录
        webSettings.setAppCachePath(mContext.getCacheDir().getAbsolutePath());
        // 数据库路径
        if (!hasKitkat()) {
            webSettings.setDatabasePath(mContext.getDatabasePath("html").getPath());
        }
        // 关闭密码保存提醒功能
        webSettings.setSavePassword(false);
        // 支持缩放
        webSettings.setSupportZoom(true);
        // 设置 UserAgent 属性
        webSettings.setUserAgentString("");
        // 允许加载本地 html 文件/false
        webSettings.setAllowFileAccess(true);
        // 允许通过 file url 加载的 Javascript 读取其他的本地文件,Android 4.1 之前默认是true，在 Android 4.1 及以后默认是false,也就是禁止
        webSettings.setAllowFileAccessFromFileURLs(false);
        // 允许通过 file url 加载的 Javascript 可以访问其他的源，包括其他的文件和 http，https 等其他的源，
        // Android 4.1 之前默认是true，在 Android 4.1 及以后默认是false,也就是禁止
        // 如果此设置是允许，则 setAllowFileAccessFromFileURLs 不起做用
        webSettings.setAllowUniversalAccessFromFileURLs(false);

    }

    private static boolean hasKitkat() {
        return Build.VERSION.SDK_INT >= 19;
    }
}
