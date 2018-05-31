package com.oraclechain.pocketeos.net;

import com.lzy.okgo.OkGo;
import com.oraclechain.pocketeos.net.callbck.JsonCallback;

import java.util.Map;

/**
 * Created by pocketEos on 2018/4/2.
 * app网络请求管理类
 */
public class HttpUtils {
    /**
     * Gets requets.
     *
     * @param <T>      the type parameter
     * @param url      the url
     * @param tag      the tag
     * @param map      the map
     * @param callback the callback
     */
    public static <T> void getRequets(String url, Object tag, Map<String, String> map, JsonCallback<T> callback) {
        OkGo.<T>get(url)
                .tag(tag)
                .params(map)
                .execute(callback);
    }

    /**
     * Post request.
     *
     * @param <T>      the type parameter
     * @param url      the url
     * @param tag      the tag
     * @param map      the map
     * @param callback the callback
     */
    public static <T> void postRequest(String url, Object tag, Map<String, String> map, JsonCallback<T> callback) {
        OkGo.<T>post(url)
                .tag(tag)
                .params(map)
                .execute(callback);
    }

    /**
     * Post request.
     *
     * @param <T>      the type parameter
     * @param url      the url
     * @param tag      the tag
     * @param parms    the parms
     * @param callback the callback
     */
    public static <T> void postRequest(String url, Object tag, String parms, JsonCallback<T> callback) {
        OkGo.<T>post(url)
                .tag(tag)
                .upJson(parms)
                .execute(callback);
    }
}
