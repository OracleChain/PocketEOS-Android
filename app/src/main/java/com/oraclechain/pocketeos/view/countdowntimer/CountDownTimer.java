package com.oraclechain.pocketeos.view.countdowntimer;

import android.content.Context;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import org.apache.commons.lang.time.DurationFormatUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pocketEos on 2017/7/28.
 * 倒计时
 */

public class CountDownTimer extends android.os.CountDownTimer {
    private Context mContext;//传入的上下文对象
    protected TextView mDateTv;//一个TextView实现倒计时
    private long  mGapTime;//传入设置的时间间隔即倒计时的总时长
    private long mCount = 1000;//倒计时的步长　一般为1000代表每隔1s跳一次
    private String mTimePattern = "HH:mm:ss";//timePattern 传入的时间的样式　如: HH:mm:ss HH时mm分ss秒　dd天HH时mm分ss秒
    private String mTimeStr;
    protected List<CountTimerBackgroundSpan> mBackSpanList;
    protected List<ForegroundColorSpan> mTextColorSpanList;
    private int mDrawableId;
    private boolean flag = false;//设置标记flag,用于控制使得初始化Span的数据一次

    protected  String[] numbers;//此数组用于保存每个倒计时字符拆分后的天,时,分,秒的数值
    protected char[]  nonNumbers;//保存了天,时,分,秒之间的间隔("天","时","分","秒"或者":")
    //用于倒计时样式的内间距,字体大小,字体颜色,倒计时间隔的颜色
    private int mSpanPaddingLeft,mSpanPaddingRight,mSpanPaddingTop,mSpanPaddingBottom;
    private int mSpanTextSize;
    private int mSpanTextColor;
    protected int mGapSpanColor;
    public CountDownTimer(Context mContext, long mGapTime, String mTimePattern, int mDrawableId) {
        this(mContext,mGapTime,1000,mTimePattern,mDrawableId);
    }
    public CountDownTimer(Context mContext, long mGapTime, int mCount, String mTimePattern, int mDrawableId) {
        super(mGapTime,mCount);
        this.mContext = mContext;
        this.mGapTime = mGapTime;//倒计时总时长
        this.mCount = mCount;//每次倒计时的步长，默认是1000
        this.mDrawableId= mDrawableId;//用于设置背景的drawable的id
        this.mTimePattern = mTimePattern;//时间的格式:如HH:mm:ss或者dd天HH时mm分ss秒等
        mBackSpanList = new ArrayList<>();
        mTextColorSpanList = new ArrayList<>();
        mDateTv = new TextView(mContext,null);
    }
    //公布这些设置倒计时样式的方法，供外部调用，从而灵活定制倒计时的样式
    public CountDownTimer setTimerTextSize(int textSize){
        this.mSpanTextSize = textSize;
        return this;
    }
    public CountDownTimer setTimerPadding(int left, int top, int right, int bottom){
        this.mSpanPaddingLeft = left;
        this.mSpanPaddingBottom = bottom;
        this.mSpanPaddingRight = right;
        this.mSpanPaddingTop = top;
        return this;
    }
    public CountDownTimer setTimerTextColor(int color){
        this.mSpanTextColor = color;
        return this;
    }
    public CountDownTimer setTimerGapColor(int color){
        this.mGapSpanColor = color;
        return this;
    }
    //设置倒计时的Span的样式,公布出给各个子类实现
    public void setBackgroundSpan(String timeStr) {
        if (!flag){
            initSpanData(timeStr);
            flag = true;
        }
        mDateTv.setText(timeStr);
    }
    //设置倒计时的Span的数据,公布出给各个子类实现
    public void initSpanData(String timeStr) {
        numbers = TimerUtils.getNumInTimerStr(timeStr);
        nonNumbers = TimerUtils.getNonNumInTimerStr(timeStr);
    }

    protected void initBackSpanStyle(CountTimerBackgroundSpan mBackSpan) {
        mBackSpan.setTimerPadding(mSpanPaddingLeft,mSpanPaddingTop,mSpanPaddingRight,mSpanPaddingBottom);
        mBackSpan.setTimerTextColor(mSpanTextColor);
        mBackSpan.setTimerTextSize(mSpanTextSize);
    }

    @Override
    public void onTick(long l) {
        if (l > 0) {
            mTimeStr = DurationFormatUtils.formatDuration(l, mTimePattern);
            //这是apache中的common的lang包中DurationFormatUtils类中的formatDuration，通过传入
            //一个时间格式就会自动将倒计时转换成相应的mTimePattern的样式(HH:mm:ss或dd天HH时mm分ss秒)
            setBackgroundSpan(mTimeStr);
        }
    }

    @Override
    public void onFinish() {
        mDateTv.setText("已结束");
    }
    //用于返回显示倒计时的TextView的对象
    public TextView getmDateTv() {
        startTimer();
        return mDateTv;
    }
    public void cancelTimer(){
        this.cancel();
    }
    public void startTimer(){
        this.start();
    }

    public String getmTimeStr() {
        return mTimeStr;
    }
}