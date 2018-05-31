package com.oraclechain.pocketeos.view.countdowntimer;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.widget.TextView;

public class CountDownTimerUtils extends CountDownTimer {
    private TextView mTextView;
    String img_id;

    public CountDownTimerUtils(TextView textView, long millisInFuture, long countDownInterval, String img_id) {
        super(millisInFuture, countDownInterval);
        this.mTextView = textView;
        this.img_id=img_id;
    }
    @Override
    public void onTick(long millisUntilFinished) {
        mTextView.setClickable(false); //设置不可点击
        mTextView.setText((millisUntilFinished/1000)+"s后重新获取");  //设置倒计时时间
        mTextView.setTextColor(Color.parseColor("#999999"));
        /**
         * 超链接 URLSpan
         * 文字背景颜色 BackgroundColorSpan
         * 文字颜色 ForegroundColorSpan
         * 字体大小 AbsoluteSizeSpan
         * 粗体、斜体 StyleSpan
         * 删除线 StrikethroughSpan
         * 下划线 UnderlineSpan
         * 图片 ImageSpan
         * http://blog.csdn.net/ah200614435/article/details/7914459
         */
      //  SpannableString spannableString = new SpannableString(mTextView.getText().toString());  //获取按钮上的文字
       // ForegroundColorSpan span = new ForegroundColorSpan(Color.GRAY);//设置灰色
        /**
         * public void setSpan(Object what, int start, int end, int flags) {
         * 主要是start跟end，start是起始位置,无论中英文，都算一个。
         * 从0开始计算起。end是结束位置，所以处理的文字，包含开始位置，但不包含结束位置。
         */
     /*   if(millisUntilFinished / 1000 < 10)
            spannableString.setSpan(span, 0, 7, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);//将倒计时的时间设置为灰色
        else
            spannableString.setSpan(span, 0, 7, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);//将倒计时的时间设置为灰色*/
       // mTextView.setText(spannableString);
    }

    @Override
    public void onFinish() {
        mTextView.setText("获取验证码");
        mTextView.setClickable(true);//重新获得点击
       mTextView.setTextColor(Color.parseColor(img_id));
        cancel();
    }
    public void onMyStop() {
        mTextView.setText("获取验证码");
        mTextView.setClickable(true);//重新获得点击
        mTextView.setTextColor(Color.parseColor("#ff3d46"));
        cancel();
    }
}
