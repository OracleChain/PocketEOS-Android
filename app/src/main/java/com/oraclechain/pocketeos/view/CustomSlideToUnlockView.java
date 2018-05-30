package com.oraclechain.pocketeos.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;
import com.nineoldandroids.view.ViewHelper;
import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.utils.DensityUtil;

/**
 * Created by pocketEos on 2017/12/14.'
 * 滑动销毁
 */

public class CustomSlideToUnlockView extends RelativeLayout {

    private static final long DEAFULT_DURATIN_LONG = 200;//左弹回,动画时长
    private static final long DEAFULT_DURATIN_SHORT = 100;//右弹,动画时长
    private static int DISTANCE_LIMIT = 600;//滑动阈值
    private static float THRESHOLD = 0.5F;//滑动阈值比例:默认是0.5,即滑动超过父容器宽度的一半再松手就会触发
    protected Context mContext;
    private ImageView iv_slide;//滑块
    private TextView tv_hint;//提示文本
    private RelativeLayout rl_slide;//滑动view
    private RelativeLayout rl_root;//父容器
    private boolean mIsUnLocked;//已经滑到最右边,将不再响应touch事件
    private CallBack mCallBack;//回调


    private int slideImageViewWidth;//滑块宽度
    private int slideImageViewResId;//滑块资源
    private int slideImageViewResIdAfter;//滑动到右边时,滑块资源id
    private int viewBackgroundResId;//root 背景
    private String textHint;//文本
//    private int textSize = 50;//单位是px,只拿数值
    private int textColorResId;//颜色,@color


    public CustomSlideToUnlockView(Context mContext) {
        super(mContext);
        this.mContext = mContext;
        initView();
    }

    public CustomSlideToUnlockView(Context mContext, AttributeSet attrs) {
        super(mContext, attrs);
        this.mContext = mContext;
        TypedArray mTypedArray = mContext.obtainStyledAttributes(attrs,
                R.styleable.SlideToUnlockView);
        init(mTypedArray);
        initView();
    }

    public CustomSlideToUnlockView(Context mContext, AttributeSet attrs, int defStyleAttr) {
        super(mContext, attrs, defStyleAttr);
        this.mContext = mContext;

        TypedArray mTypedArray = mContext.obtainStyledAttributes(attrs,
                R.styleable.SlideToUnlockView);
        init(mTypedArray);

        initView();

    }

    /**
     * @param :[mTypedArray]
     * @return type:void
     * @method name:init
     * @des:获取自定义属性
     **/
    private void init(TypedArray mTypedArray) {

        slideImageViewWidth = (int) mTypedArray.getDimension(R.styleable.SlideToUnlockView_slideImageViewWidth, DensityUtil.dip2px(getContext(), 50));
        slideImageViewResId = mTypedArray.getResourceId(R.styleable.SlideToUnlockView_slideImageViewResId, -1);
        slideImageViewResIdAfter = mTypedArray.getResourceId(R.styleable.SlideToUnlockView_slideImageViewResIdAfter, -1);
        viewBackgroundResId = mTypedArray.getResourceId(R.styleable.SlideToUnlockView_viewBackgroundResId, -1);
        textHint = mTypedArray.getString(R.styleable.SlideToUnlockView_textHint);
        textColorResId = mTypedArray.getColor(R.styleable.SlideToUnlockView_textColorResId, getResources().getColor(android.R.color.white));
        THRESHOLD = mTypedArray.getFloat(R.styleable.SlideToUnlockView_slideThreshold, 0.5f);

        mTypedArray.recycle();
    }


    private int mActionDownX, mLastX, mSlidedDistance;


    /**
     * 初始化界面布局
     */
    protected void initView() {

        LayoutInflater.from(mContext).inflate(R.layout.layout_view_slide_to_unlock,
                this, true);

        rl_root = (RelativeLayout) findViewById(R.id.rl_root);
        rl_slide = (RelativeLayout) findViewById(R.id.rl_slide);
        iv_slide = (ImageView) findViewById(R.id.iv_slide);
        tv_hint = (TextView) findViewById(R.id.tv_hint);


        LayoutParams params = (LayoutParams) iv_slide.getLayoutParams();//获取当前控件的布局对象
        params.width = slideImageViewWidth;//设置当前控件布局的宽度
        iv_slide.setLayoutParams(params);//将设置好的布局参数应用到控件中


        setImageDefault();
        if (viewBackgroundResId > 0) {
            rl_root.setBackgroundResource(viewBackgroundResId);//rootView设置背景
        }

        MarginLayoutParams tvParams = (MarginLayoutParams) tv_hint.getLayoutParams();
        tvParams.setMargins(slideImageViewWidth + DensityUtil.dip2px(mContext, 10), 0, 0, 0);//textview的marginLeft设置为和滑块的宽度一致
        tv_hint.setLayoutParams(tvParams);
//        tv_hint.setTextSize(DensityUtil.px2dip(getContext(), textSize));
        tv_hint.setTextColor(textColorResId);
        tv_hint.setText(TextUtils.isEmpty(textHint) ? mContext.getString(R.string.hint) : textHint);

        //添加滑动监听
        rl_slide.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                DISTANCE_LIMIT = (int) (CustomSlideToUnlockView.this.getWidth() * THRESHOLD);//默认阈值是控件宽度的一半

                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN://按下时记录纵坐标
                        if (mIsUnLocked) {//滑块已经在最右边则不处理touch
                            return false;
                        }

                        mLastX = (int) event.getRawX();//最后一个action时x值
                        mActionDownX = (int) event.getRawX();//按下的瞬间x
                        break;

                    case MotionEvent.ACTION_MOVE://上滑才处理,如果用户一开始就下滑,则过掉不处理
                        int dX = (int) event.getRawX() - mLastX;
                        mSlidedDistance = (int) event.getRawX() - mActionDownX;

                        final MarginLayoutParams params = (MarginLayoutParams) v.getLayoutParams();
                        int left = params.leftMargin;
                        int top = params.topMargin;
                        int right = params.rightMargin;
                        int bottom = params.bottomMargin;

                        int leftNew = left + dX;
                        int rightNew = right - dX;
                        if (mSlidedDistance > 0) {//直接通过margin实现滑动
                            params.setMargins(leftNew, top, rightNew, bottom);
                            v.setLayoutParams(params);
                            resetTextViewAlpha(mSlidedDistance);
                            //回调
                            if (mCallBack != null) {
                                mCallBack.onSlide(mSlidedDistance, slideImageViewWidth, (DISTANCE_LIMIT * 2));
                            }
                            mLastX = (int) event.getRawX();
                        } else {
                            return true;
                        }

                        break;


                    case MotionEvent.ACTION_UP:
                        scrollToLeft(v);//左边
                        if (mCallBack != null) {
                            mCallBack.onlocked();//通知页面进行随机数的重新换算
                        }
                        break;


                    case MotionEvent.ACTION_CANCEL:

                        break;

                }


                return true;
            }
        });


    }


    /**
     * @param :[mSlidedDistance]
     * @return type:void
     * @method name:resetTextViewAlpha
     * @des: 重置提示文本的透明度
     **/
    private void resetTextViewAlpha(int distance) {

        if (Math.abs(distance) >= Math.abs(DISTANCE_LIMIT)) {
            tv_hint.setAlpha(0.0f);
        } else {
            tv_hint.setAlpha(1.0f - Math.abs(distance) * 1.0f / Math.abs(DISTANCE_LIMIT));
        }
    }


    /**
     * @param :[v]
     * @return type:void
     * @method name:scrollToLeft
     * @des: 滑动未到阈值时松开手指, 弹回到最左边
     **/
    private void scrollToLeft(final View v) {


        final MarginLayoutParams params1 = (MarginLayoutParams) v.getLayoutParams();


        ViewAnimator.animate(rl_slide)
                .translationX(ViewHelper.getTranslationX(v), -params1.leftMargin)
                .interpolator(new AccelerateInterpolator())
                .duration(DEAFULT_DURATIN_LONG)
                .onStop(new AnimationListener.Stop() {
                    @Override
                    public void onStop() {
                        mSlidedDistance = 0;
                        tv_hint.setAlpha(1.0f);
                        mIsUnLocked = false;
                        if (mCallBack != null) {
                            mCallBack.onSlide(mSlidedDistance, slideImageViewWidth, (DISTANCE_LIMIT * 2));
                        }
                        setImageDefault();
                    }
                })
                .start();
    }


    /**
     * @param :[v]
     * @return type:void
     * @method name:scrollToRight
     * @des:滑动到右边,并触发回调
     **/
    private void scrollToRight(final View v) {


        final MarginLayoutParams params1 = (MarginLayoutParams) v.getLayoutParams();

        //移动到最右端  移动的距离是 父容器宽度-leftMargin
        ViewAnimator
                .animate(rl_slide)
                .translationX(ViewHelper.getTranslationX(v), (rl_slide.getWidth() - params1.leftMargin - slideImageViewWidth))
                .interpolator(new AccelerateInterpolator())
                .duration(DEAFULT_DURATIN_SHORT)
                .onStop(new AnimationListener.Stop() {
                    @Override
                    public void onStop() {

                        mSlidedDistance = 0;
                        tv_hint.setAlpha(0.0f);
                        mIsUnLocked = true;

                        if (slideImageViewResIdAfter > 0) {
                            iv_slide.setImageResource(slideImageViewResIdAfter);//滑块imagview设置资源
                        }

                        //回调
                        if (mCallBack != null) {
                            mCallBack.onUnlocked();
                        }
                    }
                })
                .start();


    }


    public void resetView() {
        mIsUnLocked = false;
        setImageDefault();
        scrollToLeft(rl_slide);
    }

    private void setImageDefault() {
        /**
         * @method name:setImageDefault
         * @des: 设置默认图片
         * @param :[]
         * @return type:void
         * @date 创建时间:2017/5/25
         * @author Chuck
         **/

        if (slideImageViewResId > 0) {
            iv_slide.setImageResource(slideImageViewResId);//滑块imagview设置资源
        }
    }

    public interface CallBack {
        void onSlide(int distance, int imgwidth, int rootwidth);//右滑距离回调

        void onUnlocked();//滑动到了边,事件回调

        void onlocked();//滑动到了左边,事件回调
    }


    public CallBack getmCallBack() {
        return mCallBack;
    }

    public void setmCallBack(CallBack mCallBack) {
        this.mCallBack = mCallBack;
    }
}
