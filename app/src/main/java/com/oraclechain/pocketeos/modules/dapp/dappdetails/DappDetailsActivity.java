package com.oraclechain.pocketeos.modules.dapp.dappdetails;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;

import com.lzy.okgo.utils.OkLogger;
import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.app.ActivityUtils;
import com.oraclechain.pocketeos.base.BaseAcitvity;
import com.oraclechain.pocketeos.modules.dapp.paidanswer.chooseaccountwithcoin.ChooseAccountWithCoinActivity;
import com.oraclechain.pocketeos.modules.normalvp.NormalPresenter;
import com.oraclechain.pocketeos.modules.normalvp.NormalView;
import com.oraclechain.pocketeos.view.webview.BaseWebView;

import butterknife.BindView;
import butterknife.OnClick;

public class DappDetailsActivity extends BaseAcitvity<NormalView, NormalPresenter> implements NormalView {


    @BindView(R.id.web_dapp_details)
    BaseWebView mWebDappDetails;
    String account = null;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    String url = null;

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
        url = getIntent().getStringExtra("url");
        ActivityUtils.next(this, ChooseAccountWithCoinActivity.class, 100);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 200) {
            account = data.getStringExtra("account");
            init(data.getStringExtra("account"));
        }
    }

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    public void init(final String account) {
        showProgress();
        // 开启辅助功能崩溃
        mWebDappDetails.disableAccessibility(this);
        mWebDappDetails.getSettings().setJavaScriptEnabled(true);
        mWebDappDetails.getSettings().setUserAgentString("PocketEosAndroid");
//        mWebDappDetails.setWebViewClient(new BaseWebViewClient(this));
        mWebDappDetails.addJavascriptInterface(new DappInterface(mWebDappDetails, this), "DappJsBridge");
        mWebDappDetails.loadUrl(url);
        mWebDappDetails.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int progress) {
                if (progress == 100) {
                    hideProgress();
                    OkLogger.i("==============>"+account);
                    mWebDappDetails.loadUrl("javascript:getEosAccount('" + account + "')");
                }
            }
        });

    }

    /**
     * 返回键返回上一网页
     */
    @Override
    public void onBackPressed() {
        if (mWebDappDetails.canGoBack()) {
            mWebDappDetails.goBack();
        } else {
            finish();
        }
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        if (mWebDappDetails.canGoBack()) {
            mWebDappDetails.goBack();
        } else {
            finish();
        }
    }
}
