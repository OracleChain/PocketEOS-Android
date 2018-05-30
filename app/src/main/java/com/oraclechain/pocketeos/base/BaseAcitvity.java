package com.oraclechain.pocketeos.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.gyf.barlibrary.KeyboardPatch;
import com.lzy.okgo.OkGo;
import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.app.ActivityUtils;
import com.oraclechain.pocketeos.app.AppManager;
import com.oraclechain.pocketeos.utils.KeyBoardUtil;
import com.oraclechain.pocketeos.utils.LocalManageUtil;
import com.oraclechain.pocketeos.utils.ShowDialog;
import com.oraclechain.pocketeos.utils.ThemeUtil;
import com.oraclechain.pocketeos.utils.ToastUtils;
import com.oraclechain.pocketeos.utils.Utils;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.ButterKnife;

/**
 * Created by pocketEos on 2017/11/23.
 */

public abstract class BaseAcitvity<V, P extends BasePresent<V>> extends AutoLayoutActivity implements BaseView {

    protected P presenter;
    protected Activity activity;
    protected ImmersionBar mImmersionBar;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocalManageUtil.setLocal(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ThemeUtil.setTheme(this);
        super.onCreate(savedInstanceState);
        activity = this;
        // 添加Activity到堆栈
        AppManager.getAppManager().addActivity(activity);
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
            presenter = initPresenter();
            initBind();
            initViews(savedInstanceState);
            initData();
            initEvent();
        }

        //初始化沉浸式
        if (isImmersionBarEnabled()) {
            initImmersionBar();
        }
    }

    protected abstract int getLayoutId();

    public abstract P initPresenter();

    protected void initBind() {
        ButterKnife.bind(activity);
    }

    protected abstract void initViews(Bundle savedInstanceState);

    protected abstract void initData();

    public abstract void initEvent();

    /**
     * 是否可以使用沉浸式
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @SuppressLint("ResourceAsColor")
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        TextView textView = getId(R.id.tv_title);
        if (textView != null) {
            if (textView.getCurrentTextColor() == -1) {
                mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color.black_box_color);
                mImmersionBar.statusBarDarkFont(false, 0.2f);
            } else {
                mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color.white);
                mImmersionBar.statusBarDarkFont(true, 0.2f);
            }
        }

        mImmersionBar.keyboardEnable(true); //解决软键盘与底部输入框冲突问题;
        KeyboardPatch.patch(this).enable();
        mImmersionBar.init();
    }

    protected <T extends View> T getId(int id) {
        return (T) findViewById(id);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 关闭堆栈中的Activity
        AppManager.getAppManager().finishActivity(activity);
        //在onDestroy()生命周期中释放P中引用的V。
        presenter.detach();
        //在onDestroy()生命周期中取消所有子线程里面的网络连接。

        OkGo.getInstance().cancelTag(activity);
        if (mImmersionBar != null) {
            mImmersionBar.destroy();  //在BaseActivity里销毁
        }

    }

    @Override
    public void showProgress() {
        ShowDialog.showDialog(this, "", true, null);
    }

    @Override
    public void hideProgress() {
        ShowDialog.dissmiss();
    }

    @Override
    public void toast(CharSequence s) {
        ToastUtils.showShortToast(s);
    }

    @Override
    public void showNullLayout() {

    }

    @Override
    public void hideNullLayout() {

    }

    @Override
    public void showErrorLayout() {
      /*  getId(R.id.error_text).setVisibility(View.VISIBLE);
        getId(R.id.error_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initData();
            }
        });*/
    }
    /* *
     * 设置title
     *
     * @param title 文本*/

    @Override
    public void hideErrorLayout() {
        // getId(R.id.error_text).setVisibility(View.GONE);
    }

    /**
     * 设置左侧返回按钮
     */
    protected void setLeftImg() {
        getId(R.id.iv_back).setVisibility(View.GONE);
    }

    protected void setCenterTitle(String title) {
        if (title == null) {
            return;
        }
        TextView tvTitle = getId(R.id.tv_title);
        ImageView tvTitle1 = getId(R.id.iv_back);
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setText(title);
        goBack();
        if( Utils.getSpUtils().getString("loginmode","").equals("blackbox")){
            tvTitle1.setImageResource(R.mipmap.white_back);
            setRightImg(false);
        }else {
            tvTitle1.setImageResource(R.mipmap.back);
        }

    }
    /* *
     * 设置返回按钮事件*/

    protected void goBack() {

        getId(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KeyBoardUtil.getInstance(activity).hide();
                ActivityUtils.goBack(activity);
            }
        });
    }

    /**
     * 设置右侧文本
     *
     * @param title        文字
     * @param isVisibility 是否显示
     */

    protected void setRightTitle(String title, Boolean isVisibility) {
        if (title == null) {
            return;
        }
        TextView tvTitle = getId(R.id.tv_right_text);
        tvTitle.setText(title);
        if (isVisibility == true) {
            tvTitle.setVisibility(View.VISIBLE);
        } else {
            tvTitle.setVisibility(View.GONE);
        }
    }

    /**
     * 设置右侧
     *
     * @param isVisibility 是否显示
     */

    protected void setRightImg(Boolean isVisibility) {
        ImageView rightImg = getId(R.id.img_right);
        if (isVisibility == true) {
            rightImg.setVisibility(View.VISIBLE);
        } else {
            rightImg.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 在Activity中初始化P，并且连接V
        presenter.attach((V) activity);

    }
}

