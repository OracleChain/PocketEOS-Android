package com.oraclechain.pocketeos.modules.news.newsdetails;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.base.BaseAcitvity;
import com.oraclechain.pocketeos.modules.normalvp.NormalPresenter;
import com.oraclechain.pocketeos.modules.normalvp.NormalView;
import com.oraclechain.pocketeos.utils.RegexUtil;
import com.oraclechain.pocketeos.view.webview.BaseWebChromeClient;
import com.oraclechain.pocketeos.view.webview.BaseWebSetting;
import com.oraclechain.pocketeos.view.webview.BaseWebView;
import com.oraclechain.pocketeos.view.webview.BaseWebViewClient;
import com.zzhoujay.richtext.ImageHolder;
import com.zzhoujay.richtext.RichText;

import butterknife.BindView;
import butterknife.OnClick;

public class WebNewsDetailsActivity extends BaseAcitvity<NormalView, NormalPresenter> implements NormalView {


    @BindView(R.id.web_news_details)
    BaseWebView mWebNewsDetails;
    String details = null;
    @BindView(R.id.rich_test)
    TextView mRichTest;
    @BindView(R.id.iv_back)
    ImageView mIvBack;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_news_details;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setCenterTitle(getString(R.string.wen_details));
        details = getIntent().getStringExtra("details");
        // 开启辅助功能崩溃
        mWebNewsDetails.disableAccessibility(this);
        new BaseWebSetting(mWebNewsDetails, WebNewsDetailsActivity.this);//设置webseeting
        mWebNewsDetails.setWebViewClient(new BaseWebViewClient(WebNewsDetailsActivity.this));
        mWebNewsDetails.setWebChromeClient(new BaseWebChromeClient(WebNewsDetailsActivity.this));

    }

    @Override
    protected void initData() {
        if (RegexUtil.checkURL(details)) {
            mWebNewsDetails.setVisibility(View.VISIBLE);
            mRichTest.setVisibility(View.GONE);
            mWebNewsDetails.loadUrl(details);
        } else {
            mWebNewsDetails.setVisibility(View.GONE);
            mRichTest.setVisibility(View.VISIBLE);
            RichText
                    .fromMarkdown(details) // 数据源
                    .autoFix(true) // 是否自动修复，默认true
                    .autoPlay(true) // gif图片是否自动播放
                    .scaleType(ImageHolder.ScaleType.CENTER_CROP) // 图片缩放方式
                    .size(ImageHolder.MATCH_PARENT, ImageHolder.WRAP_CONTENT) // 图片占位区域的宽高
                    .error(R.mipmap.ic_launcher) // 设置加载失败的错误图
                    .bind(WebNewsDetailsActivity.this) // 绑定richText对象到某个object上，方便后面的清理
                    .into(mRichTest); // 设置目标TextView
        }
    }


    @Override
    public void initEvent() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // activity onDestory时
        RichText.clear(activity);
    }

    @Override
    public NormalPresenter initPresenter() {
        return new NormalPresenter();
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        if(mWebNewsDetails.canGoBack()){
            mWebNewsDetails.goBack();
        }else {
            finish();
        }
    }
    /**
     * 返回键返回上一网页
     */
    @Override
    public void onBackPressed() {
        if(mWebNewsDetails.canGoBack()){
            mWebNewsDetails.goBack();
        }else {
            finish();
        }
    }
}