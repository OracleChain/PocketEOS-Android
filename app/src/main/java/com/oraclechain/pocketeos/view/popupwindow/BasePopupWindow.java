package com.oraclechain.pocketeos.view.popupwindow;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.adapter.AdapterManger;
import com.oraclechain.pocketeos.adapter.baseadapter.CommonAdapter;
import com.oraclechain.pocketeos.bean.AccountInfoBean;
import com.oraclechain.pocketeos.bean.CoinBean;
import com.oraclechain.pocketeos.utils.RotateUtils;

import java.util.List;

/**
 * Created by pocketEos on 2017/12/4.
 */

public class BasePopupWindow extends PopupWindow {
    final PopupController controller;
    RecyclerView mRecyclerView;
    boolean baseIsShow = false;

    @Override
    public int getWidth() {
        return controller.mPopupView.getMeasuredWidth();
    }

    @Override
    public int getHeight() {
        return controller.mPopupView.getMeasuredHeight();
    }


    private BasePopupWindow(Context context) {
        controller = new PopupController(context, this);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        controller.setBackGroundLevel(1.0f);
    }

    public static class Builder {
        private final PopupController.PopupParams params;

        public Builder(Context context) {
            params = new PopupController.PopupParams(context);
        }

        /**
         * @param layoutResId 设置PopupWindow 布局ID
         * @return Builder
         */
        public Builder setView(int layoutResId) {
            params.mView = null;
            params.layoutResId = layoutResId;
            return this;
        }

        /**
         * @param view 设置PopupWindow布局
         * @return Builder
         */
        public Builder setView(View view) {
            params.mView = view;
            params.layoutResId = 0;
            return this;
        }

        /**
         * 设置宽度和高度 如果不设置 默认是wrap_content
         *
         * @param width 宽
         * @return Builder
         */
        public Builder setWidthAndHeight(int width, int height) {
            params.mWidth = width;
            params.mHeight = height;
            return this;
        }

        /**
         * 设置背景灰色程度
         *
         * @param level 0.0f-1.0f
         * @return Builder
         */
        public Builder setBackGroundLevel(float level) {
            params.isShowBg = true;
            params.bg_level = level;
            return this;
        }

        /**
         * 是否可点击Outside消失
         *
         * @param touchable 是否可点击
         * @return Builder
         */
        public Builder setOutsideTouchable(boolean touchable) {
            params.isTouchable = touchable;
            return this;
        }

        /**
         * 设置动画
         *
         * @return Builder
         */
        public Builder setAnimationStyle(int animationStyle) {
            params.isShowAnim = true;
            params.animationStyle = animationStyle;
            return this;
        }


        public BasePopupWindow create() {
            final BasePopupWindow popupWindow = new BasePopupWindow(params.mContext);
            params.apply(popupWindow.controller);
            //测量View的宽高
            int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            popupWindow.controller.mPopupView.measure(widthMeasureSpec, heightMeasureSpec);

            return popupWindow;
        }
    }

    @Override
    public void showAsDropDown(View anchor) {
        if(Build.VERSION.SDK_INT >= 24){
            Rect visibleFrame = new Rect();
            anchor.getGlobalVisibleRect(visibleFrame);
            int height = anchor.getResources().getDisplayMetrics().heightPixels - visibleFrame.bottom;
            setHeight(height);
        }

        super.showAsDropDown(anchor);
    }

    public boolean setAccountData(final Context context, final List<AccountInfoBean> mAccountInfoBeanList, final String account, final ImageView lookMore, final boolean isSHow) {
        mRecyclerView = getContentView().findViewById(R.id.exchange_two_type);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        layoutManager.setSmoothScrollbarEnabled(true);
        mRecyclerView.setLayoutManager(layoutManager);
        CommonAdapter commonAdapter = AdapterManger.getAccountAdapter(context, mAccountInfoBeanList, account);
        mRecyclerView.setAdapter(commonAdapter);
        baseIsShow = isSHow;
        getContentView().findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                baseIsShow = !isSHow;
                RotateUtils.rotateArrow(lookMore, baseIsShow);
            }
        });
        return baseIsShow;
    }

    public boolean setCoinData(final Context context, List<String> mCoinList, final String coin, final ImageView lookMore, final boolean isSHow) {
        mRecyclerView = getContentView().findViewById(R.id.exchange_two_type);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        layoutManager.setSmoothScrollbarEnabled(true);
        mRecyclerView.setLayoutManager(layoutManager);
        CommonAdapter commonAdapter =  AdapterManger.getCoinTypeAdapter(context, mCoinList, coin);
        mRecyclerView.setAdapter(commonAdapter);
        baseIsShow = isSHow;
        getContentView().findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                baseIsShow = !isSHow;
                RotateUtils.rotateArrow(lookMore, baseIsShow);
            }
        });
        return baseIsShow;
    }

    public boolean setNewsCoinData(final Context context, List<CoinBean.DataBean> mCoinBeen, final String coinid, final ImageView lookMore, final boolean isSHow) {
        mRecyclerView = getContentView().findViewById(R.id.exchange_two_type);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        layoutManager.setSmoothScrollbarEnabled(true);
        mRecyclerView.setLayoutManager(layoutManager);
        CommonAdapter commonAdapter =  AdapterManger.getNewsCoinTypeAdapter(context, mCoinBeen, coinid);
        mRecyclerView.setAdapter(commonAdapter);
        baseIsShow = isSHow;
        getContentView().findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                baseIsShow = !isSHow;
                RotateUtils.rotateArrow(lookMore, baseIsShow);
            }
        });
        return baseIsShow;
    }

}



