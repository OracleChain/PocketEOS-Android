package com.oraclechain.pocketeos.modules.nodevote;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.base.BaseAcitvity;
import com.oraclechain.pocketeos.modules.normalvp.NormalPresenter;
import com.oraclechain.pocketeos.modules.normalvp.NormalView;
import com.oraclechain.pocketeos.view.webview.BaseWebChromeClient;
import com.oraclechain.pocketeos.view.webview.BaseWebSetting;
import com.oraclechain.pocketeos.view.webview.BaseWebView;
import com.oraclechain.pocketeos.view.webview.BaseWebViewClient;

import butterknife.BindView;

public class NodeVoteDetailsActivity extends BaseAcitvity<NormalView, NormalPresenter> implements NormalView {


    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.web_details)
    BaseWebView mWebDetails;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    String url = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_node_vote_details;
    }

    @Override
    public NormalPresenter initPresenter() {
        return new NormalPresenter();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setCenterTitle(getIntent().getStringExtra("title"));
        url = getIntent().getStringExtra("url");
        // 开启辅助功能崩溃
        mWebDetails.disableAccessibility(this);
        new BaseWebSetting(mWebDetails, NodeVoteDetailsActivity.this, true);//设置webseeting
        mWebDetails.setWebViewClient(new BaseWebViewClient(NodeVoteDetailsActivity.this));
        mWebDetails.setWebChromeClient(new BaseWebChromeClient(NodeVoteDetailsActivity.this, mProgressBar));
    }

    @Override
    protected void initData() {
        mWebDetails.loadUrl(url);
    }

    @Override
    public void initEvent() {
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mWebDetails.canGoBack()) {//当webview不是处于第一页面时，返回上一个页面
                    mWebDetails.goBack();
                } else {//当webview处于第一页面时,直接退出程序
                    finish();
                }
            }
        });
    }

    //设置返回键动作（防止按返回键直接退出程序)
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebDetails.canGoBack()) {//当webview不是处于第一页面时，返回上一个页面
                mWebDetails.goBack();
                return true;
            } else {//当webview处于第一页面时,直接退出程序
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}
