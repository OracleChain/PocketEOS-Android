package com.oraclechain.pocketeos.modules.dapp.paidanswer.questiondetails;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.gson.Gson;
import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.app.MyApplication;
import com.oraclechain.pocketeos.base.BaseAcitvity;
import com.oraclechain.pocketeos.bean.AnswerMessageBean;
import com.oraclechain.pocketeos.bean.QuestionListBean;
import com.oraclechain.pocketeos.blockchain.AskansDatamanger;
import com.oraclechain.pocketeos.modules.normalvp.NormalPresenter;
import com.oraclechain.pocketeos.modules.normalvp.NormalView;
import com.oraclechain.pocketeos.utils.PasswordToKeyUtils;
import com.oraclechain.pocketeos.utils.ShowDialog;
import com.oraclechain.pocketeos.view.dialog.passworddialog.PasswordCallback;
import com.oraclechain.pocketeos.view.dialog.passworddialog.PasswordDialog;

import butterknife.BindView;

public class QuestionDetailsActivity extends BaseAcitvity<NormalView, NormalPresenter> implements NormalView {


    @BindView(R.id.webview)
    WebView mWebview;
    String url = "http://59.110.162.106:8002/#/answer";
    private QuestionListBean mDataBean;

    private String userPassword = null;
    private String answerOption = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_question_details;
    }

    @Override
    public NormalPresenter initPresenter() {
        return new NormalPresenter();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setCenterTitle(getString(R.string.question_details));
        mDataBean = getIntent().getParcelableExtra("question");
    }

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    @Override
    protected void initData() {
        ShowDialog.showDialog(QuestionDetailsActivity.this, "", true, null).show();
        mWebview.setVerticalScrollbarOverlay(true);
        //设置WebView支持JavaScript
        mWebview.getSettings().setJavaScriptEnabled(true);

        mWebview.loadUrl(url);
        //在js中调用本地java方法
        mWebview.addJavascriptInterface(new JsInterface(QuestionDetailsActivity.this), "AndroidWebView");
        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        //添加客户端支持
        mWebview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int progress) {
                if (progress == 100) {
                    ShowDialog.dissmiss();
                    mWebview.loadUrl("javascript:getquestion('" + mDataBean.getId() + "','" + mDataBean.getReleasedLable() + "')");
                }
            }
        });


    }

    @Override
    public void initEvent() {

    }

    private class JsInterface {
        private Context mContext;

        public JsInterface(Context context) {
            this.mContext = context;
        }

        @JavascriptInterface
        public void javatojs(String mes) {
            answerOption = mes;
            PasswordDialog mPasswordDialog = new PasswordDialog(QuestionDetailsActivity.this, new PasswordCallback() {
                @Override
                public void sure(final String password) {
                    if (MyApplication.getInstance().getUserBean().getWallet_shapwd().equals(PasswordToKeyUtils.shaCheck(password))) {
                        userPassword = password;
                        showProgress();
                        new AskansDatamanger(QuestionDetailsActivity.this, userPassword, new AskansDatamanger.Callback() {
                            @Override
                            public void isApprove(String action) {
                                if (action.equals(AskansDatamanger.ACTIONANSWER)) {
                                    toast(getString(R.string.answer_question_success));
                                    mWebview.loadUrl(url);
                                }
                            }
                        }).pushAction(AskansDatamanger.OCTASKANSCONTRACT, AskansDatamanger.ACTIONANSWER,
                                new Gson().toJson(new AnswerMessageBean(getIntent().getStringExtra("account"), mDataBean.getId(), answerOption)),
                                getIntent().getStringExtra("account"));
                    } else {
                        toast(getResources().getString(R.string.password_error));
                    }
                }

                @Override
                public void cancle() {
                }
            });
            mPasswordDialog.setCancelable(true);
            mPasswordDialog.show();
        }
    }
}
