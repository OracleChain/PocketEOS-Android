package com.oraclechain.pocketeos.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.oraclechain.pocketeos.R;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by pocketEos on 2017/11/23.
 */
public class JsonUtil {
    /**
     * The constant jsonUtils.
     */
    public static JsonUtil jsonUtils;

    /**
     * Instantiates a new Json util.
     */
    public JsonUtil() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static JsonUtil getInstance() {
        if (jsonUtils == null) {
            synchronized (JsonUtil.class) {
                if (jsonUtils == null) {
                    jsonUtils = new JsonUtil();
                }
            }
        }
        return jsonUtils;
    }

    /**
     * Parse string to bean object.
     *
     * @param str   the str
     * @param clazz the clazz
     * @return the object
     */
    public static Object parseStringToBean(String str, Class clazz) {
        Object object = null;
        try {
            Gson gson = new Gson();
            object = gson.fromJson(str, clazz);
        } catch (JsonSyntaxException e) {
            ToastUtils.showShortToast(R.string.error_parse);
        }
        return object;
    }

    /**
     * Parse json to array list array list.
     *
     * @param <T>   the type parameter
     * @param json  the json
     * @param clazz the clazz
     * @return the array list
     */
    public static <T> ArrayList<T> parseJsonToArrayList(String json, Class<T> clazz) {
        Type type = new TypeToken<ArrayList<JsonObject>>() {
        }.getType();
        ArrayList<JsonObject> jsonObjects = new Gson().fromJson(json, type);
        ArrayList<T> arrayList = new ArrayList<>();
        for (JsonObject jsonObject : jsonObjects) {
            arrayList.add(new Gson().fromJson(jsonObject, clazz));
        }
        return arrayList;
    }

}

