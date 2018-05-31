package com.oraclechain.pocketeos.view.countdowntimer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;

/**
 * Created by pocketEos on 2017/7/28.
 */

public class CountTimerBackgroundSpan extends ImageSpan {
    private Rect mTextBound;
    private int maxHeight = 0;
    private int maxWidth = 0;
    private int mPaddingLeft = 20;
    private int mPaddingRight = 20;
    private int mPaddingTop =  20;
    private int mPaddingBottom = 20;
    private int mTextColor = Color.GREEN;
    private int mTextSize = 50;
    public CountTimerBackgroundSpan(Drawable d, int verticalAlignment) {
        super(d, verticalAlignment);
        mTextBound = new Rect();
    }

    public CountTimerBackgroundSpan setTimerTextColor(int mTextColor) {
        this.mTextColor = mTextColor;
        return this;
    }
    public CountTimerBackgroundSpan setTimerTextSize(int textSize){
        this.mTextSize = textSize;
        return this;
    }
    public CountTimerBackgroundSpan setTimerPadding(int left,int top,int right,int bottom){
        this.mPaddingLeft = left;
        this.mPaddingRight = right;
        this.mPaddingBottom = bottom;
        this.mPaddingTop = top;
        return this;
    }
    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        //绘制文本的内容的背景
        paint.setTextSize(mTextSize);
        //测量文本的宽度和高度，通过mTextBound得到
        paint.getTextBounds(text.toString(), start, end, mTextBound);
        //设置文本背景的宽度和高度，传入的是left,top,right,bottom四个参数
        maxWidth = maxWidth < mTextBound.width() ? mTextBound.width() : maxWidth;
        maxHeight = maxHeight < mTextBound.height() ? mTextBound.height() : maxHeight;
        //设置最大宽度和最大高度是为了防止在倒计时在数字切换的过程中会重绘，会导致倒计时边框的宽度和高度会抖动，
        // 所以每次取得最大的高度和宽度而不是每次都去取测量的高度和宽度
        getDrawable().setBounds(0,0, maxWidth+mPaddingLeft+mPaddingRight,mPaddingTop+mPaddingBottom+maxHeight);
        //绘制文本背景
        super.draw(canvas, text, start, end, x, top, y, bottom, paint);
        //设置文本的颜色
        paint.setColor(mTextColor);
        //设置字体的大小
        paint.setTextSize(mTextSize);
        int mGapX = (getDrawable().getBounds().width() - maxWidth)/2;
        int mGapY= (getDrawable().getBounds().height() - maxHeight)/2;
        //绘制文本内容
        canvas.drawText(text.subSequence(start, end).toString(), x + mGapX , y - mGapY + maxHeight/3, paint);    }
}