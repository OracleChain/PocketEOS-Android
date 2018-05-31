package com.oraclechain.pocketeos.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.lang.ref.WeakReference;

/**
 * Created by pocketEos on 2017/11/23.
 */
public class KeyBoardUtil {
    private static KeyBoardUtil instance;
    private static InputMethodManager mInputMethodManager;
    private static WeakReference<Activity> mActivity;

    private KeyBoardUtil() {

    }

    /**
     * Gets instance.
     *
     * @param activity the activity
     * @return the instance
     */
    public static KeyBoardUtil getInstance(Activity activity) {
        mActivity = new WeakReference<Activity>(activity);
        if (instance == null) {
            instance = new KeyBoardUtil();
            mInputMethodManager = (InputMethodManager) mActivity.get().getSystemService(Context.INPUT_METHOD_SERVICE);
        }
        return instance;
    }

    /**
     * 强制显示输入法
     */
    public void show() {
        Activity activity = mActivity.get();
        if (activity != null) {
            if (!isSoftInputShow(activity)) {
                show(activity.getWindow().getCurrentFocus());
            }
        }
    }

    /**
     * 判断当前软键盘是否打开
     *
     * @param activity
     * @return
     */
    public static boolean isSoftInputShow(Activity activity) {

        // 虚拟键盘隐藏 判断view是否为空
        View view = activity.getWindow().peekDecorView();
        if (view != null) {
            // 隐藏虚拟键盘
            InputMethodManager inputmanger = (InputMethodManager) activity
                    .getSystemService(Activity.INPUT_METHOD_SERVICE);
//       inputmanger.hideSoftInputFromWindow(view.getWindowToken(),0);

            return inputmanger.isActive() && activity.getWindow().getCurrentFocus() != null;
        }
        return false;
    }

    /**
     * Show.
     *
     * @param view the view
     */
    public void show(View view) {

        mInputMethodManager.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    /**
     * 强制关闭输入法
     */
    public void hide() {
        Activity activity = mActivity.get();
        if (activity != null) {
            if (isSoftInputShow(activity)) {
                hide(activity.getWindow().getCurrentFocus());
            }
        }
    }

    /**
     * Hide.
     *
     * @param view the view
     */
    public void hide(View view) {
        mInputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 如果输入法已经显示，那么就隐藏它；如果输入法现在没显示，那么就显示它
     */
    public void showOrHide() {
        mInputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

}
