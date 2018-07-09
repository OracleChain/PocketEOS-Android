package com.oraclechain.pocketeos.modules.news.newsdetails;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.base.BaseAcitvity;
import com.oraclechain.pocketeos.modules.normalvp.NormalPresenter;
import com.oraclechain.pocketeos.modules.normalvp.NormalView;
import com.oraclechain.pocketeos.view.webview.BaseWebChromeClient;
import com.oraclechain.pocketeos.view.webview.BaseWebSetting;
import com.oraclechain.pocketeos.view.webview.BaseWebView;
import com.oraclechain.pocketeos.view.webview.BaseWebViewClient;

import butterknife.BindView;

public class WebNewsDetailsActivity extends BaseAcitvity<NormalView, NormalPresenter> implements NormalView {


    @BindView(R.id.web_news_details)
    BaseWebView mWebNewsDetails;
    String details = null;
    @BindView(R.id.rich_test)
    TextView mRichTest;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.close)
    TextView mClose;


    //设置返回键动作（防止按返回键直接退出程序)
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebNewsDetails.canGoBack()) {//当webview不是处于第一页面时，返回上一个页面
                mWebNewsDetails.goBack();
                return true;
            } else {//当webview处于第一页面时,直接退出程序
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_news_details;
    }

    @Override
    public NormalPresenter initPresenter() {
        return new NormalPresenter();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setCenterTitle(getString(R.string.wen_details));
        mClose.setVisibility(View.VISIBLE);
        details = getIntent().getStringExtra("details");
        // 开启辅助功能崩溃
        mWebNewsDetails.disableAccessibility(this);
        new BaseWebSetting(mWebNewsDetails, WebNewsDetailsActivity.this, true);//设置webseeting
        mWebNewsDetails.setWebViewClient(new BaseWebViewClient(WebNewsDetailsActivity.this));
        mWebNewsDetails.setWebChromeClient(new BaseWebChromeClient(WebNewsDetailsActivity.this, mProgressBar));

    }

    @Override
    protected void initData() {
        mWebNewsDetails.setVisibility(View.VISIBLE);
        mRichTest.setVisibility(View.GONE);
        mWebNewsDetails.loadUrl(details);
     /*   if (RegexUtil.checkURL(details)) {
            mWebNewsDetails.setVisibility(View.VISIBLE);
            mRichTest.setVisibility(View.GONE);
            mWebNewsDetails.loadUrl(details);
        } else {
            mWebNewsDetails.setVisibility(View.GONE);
            mRichTest.setVisibility(View.VISIBLE);
            RichText
                    .fromHtml(details) // 数据源
                    .autoFix(true) // 是否自动修复，默认true
                    .autoPlay(true) // gif图片是否自动播放
                    .scaleType(ImageHolder.ScaleType.CENTER_CROP) // 图片缩放方式
                    .size(ImageHolder.MATCH_PARENT, ImageHolder.WRAP_CONTENT) // 图片占位区域的宽高
                    .error(R.mipmap.ic_launcher) // 设置加载失败的错误图
                    .bind(WebNewsDetailsActivity.this) // 绑定richText对象到某个object上，方便后面的清理
                    .into(mRichTest); // 设置目标TextView
        }*/
    }

    @Override
    public void initEvent() {
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mWebNewsDetails.canGoBack()) {//当webview不是处于第一页面时，返回上一个页面
                    mWebNewsDetails.goBack();
                } else {//当webview处于第一页面时,直接退出程序
                    finish();
                }
            }
        });
        mClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}