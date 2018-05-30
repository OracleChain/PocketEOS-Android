package com.oraclechain.pocketeos.modules.leftdrawer.appupdate;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.model.Response;
import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.base.BaseAcitvity;
import com.oraclechain.pocketeos.base.BaseUrl;
import com.oraclechain.pocketeos.bean.ResponseBean;
import com.oraclechain.pocketeos.bean.UpdateAppBean;
import com.oraclechain.pocketeos.modules.normalvp.NormalPresenter;
import com.oraclechain.pocketeos.modules.normalvp.NormalView;
import com.oraclechain.pocketeos.net.HttpUtils;
import com.oraclechain.pocketeos.net.callbck.JsonCallback;
import com.oraclechain.pocketeos.utils.AppUtil;
import com.oraclechain.pocketeos.utils.UpdateUtils;
import com.oraclechain.pocketeos.utils.Utils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

public class AppUpdateActivity extends BaseAcitvity<NormalView, NormalPresenter> implements NormalView {

    @BindView(R.id.app_version)
    TextView mAppVersion;
    @BindView(R.id.version_intro)
    RelativeLayout mVersionIntro;
    @BindView(R.id.is_new_version)
    TextView mIsNewVersion;
    @BindView(R.id.check_new_version)
    RelativeLayout mCheckNewVersion;

    @BindView(R.id.title)
    TextView mTitle;

    @OnClick({R.id.version_intro, R.id.check_new_version})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.version_intro:
                break;
            case R.id.check_new_version:
                UpdateUtils.updateApp(this, 0);
                break;
        }
    }
    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        if (Utils.getSpUtils().getString("loginmode").equals("phone")) {
            mImmersionBar.statusBarDarkFont(true, 0.2f).init();
        } else {
            mImmersionBar.statusBarDarkFont(false, 0.2f).init();
        }
        ImmersionBar.setTitleBar(AppUpdateActivity.this, mTitle);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_app_update;
    }

    @Override
    public NormalPresenter initPresenter() {
        return new NormalPresenter();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

        mAppVersion.setText(getString(R.string.appversion) + AppUtil.getAppVersionName(AppUpdateActivity.this));
    }

    @Override
    protected void initData() {

        HttpUtils.getRequets(BaseUrl.HTTP_get_app_info, AppUpdateActivity.this, new HashMap<String, String>(), new JsonCallback<ResponseBean<UpdateAppBean.DataBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<UpdateAppBean.DataBean>> response) {
                if (response.body().code == 0) {
                    if (Integer.parseInt(response.body().data.getVersionCode()) > AppUtil.getAppVersionCode(AppUpdateActivity.this)) {
                        mIsNewVersion.setText(R.string.find_new_app);
                        mIsNewVersion.setTextColor(getResources().getColor(R.color.red_packet_color));
                    } else {
                        mIsNewVersion.setText(R.string.no_new_app);
                        mIsNewVersion.setTextColor(getResources().getColor(R.color.txt_color));
                    }
                }
            }
        });
    }

    @Override
    public void initEvent() {
        mTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
