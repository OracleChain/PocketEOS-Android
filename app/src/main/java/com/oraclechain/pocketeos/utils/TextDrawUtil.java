package com.oraclechain.pocketeos.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

/**
 * Created by pocketEos on 2018/6/1.
 */

public class TextDrawUtil {
    /**
     * 设置textview上侧图片
     * @param context
     * @param attention
     * @param drawableId
     */
    public static void setDrawableTop(Context context, TextView attention, int drawableId) {
        Drawable drawable = context.getResources().getDrawable(drawableId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        attention.setCompoundDrawables(null, drawable, null, null);
    }
    /**
     * 设置textview下侧图片
     * @param context
     * @param attention
     * @param drawableId
     */
    public static void setDrawableBottom(Context context ,TextView attention, int drawableId) {
        Drawable drawable = context.getResources().getDrawable(drawableId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        attention.setCompoundDrawables(null, null, null, drawable);
    }
    /**
     * 设置textview左侧图片
     * @param context
     * @param attention
     * @param drawableId
     */
    public static void setDrawableLeft(Context context ,TextView attention, int drawableId) {
        Drawable drawable = context.getResources().getDrawable(drawableId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        attention.setCompoundDrawables(drawable, null, null, null);
    }
    /**
     * 设置textview右侧图片
     * @param context
     * @param attention
     * @param drawableId
     */
    public static void setDrawableRight(Context context ,TextView attention, int drawableId) {
        Drawable drawable = context.getResources().getDrawable(drawableId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        attention.setCompoundDrawables(null, null, drawable, null);
    }
}
