package com.oraclechain.pocketeos.modules.dapp.dappdetails;

import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.app.ActivityUtils;
import com.oraclechain.pocketeos.base.BaseAcitvity;
import com.oraclechain.pocketeos.modules.dapp.paidanswer.chooseaccountwithcoin.ChooseAccountWithCoinActivity;
import com.oraclechain.pocketeos.modules.normalvp.NormalPresenter;
import com.oraclechain.pocketeos.modules.normalvp.NormalView;
import com.oraclechain.pocketeos.view.webview.BaseWebSetting;
import com.oraclechain.pocketeos.view.webview.BaseWebView;

import butterknife.BindView;

public class DappDetailsActivity extends BaseAcitvity<NormalView, NormalPresenter> implements NormalView {


    @BindView(R.id.web_dapp_details)
    BaseWebView mWebDappDetails;
    String account = null;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.close)
    TextView mClose;

    String url = null;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 200) {
            account = data.getStringExtra("account");
            init(data.getStringExtra("account"));
        }
    }

    public void init(final String account) {

        // 开启辅助功能崩溃
        mWebDappDetails.disableAccessibility(this);
        new BaseWebSetting(mWebDappDetails, DappDetailsActivity.this, false);//设置webseeting
        mWebDappDetails.getSettings().setUserAgentString("PocketEosAndroid");
        mWebDappDetails.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                if (error.getPrimaryError() == SslError.SSL_INVALID) {
                    handler.proceed();
                } else {
                    handler.cancel();
                }
            }
        });
        mWebDappDetails.getSettings().setJavaScriptEnabled(true);
        mWebDappDetails.addJavascriptInterface(new DappInterface(mWebDappDetails, this), "DappJsBridge");
        mWebDappDetails.loadUrl(url);
        mWebDappDetails.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int progress) {
                if (progress == 100) {
                    mProgressBar.setVisibility(View.GONE);//加载完网页进度条消失
                    mWebDappDetails.loadUrl("javascript:getEosAccount('" + account + "')");
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    mProgressBar.setProgress(progress);//设置进度值
                }
            }
        });

    }

    //设置返回键动作（防止按返回键直接退出程序)
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebDappDetails.canGoBack()) {//当webview不是处于第一页面时，返回上一个页面
                mWebDappDetails.goBack();
                return true;
            } else {//当webview处于第一页面时,直接退出程序
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }



    @Override
    protected int getLayoutId() {
        return R.layout.activity_dapp_details;
    }

    @Override
    public NormalPresenter initPresenter() {
        return new NormalPresenter();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setCenterTitle(getIntent().getStringExtra("title"));
        mClose.setVisibility(View.VISIBLE);
        url = getIntent().getStringExtra("url");
        ActivityUtils.next(this, ChooseAccountWithCoinActivity.class, 100);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void initEvent() {
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mWebDappDetails.canGoBack()) {//当webview不是处于第一页面时，返回上一个页面
                    mWebDappDetails.goBack();
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
