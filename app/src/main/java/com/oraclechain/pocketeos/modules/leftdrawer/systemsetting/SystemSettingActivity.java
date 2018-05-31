package com.oraclechain.pocketeos.modules.leftdrawer.systemsetting;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.app.ActivityUtils;
import com.oraclechain.pocketeos.base.BaseAcitvity;
import com.oraclechain.pocketeos.bean.SystemInfoBean;
import com.oraclechain.pocketeos.utils.CacheDataManager;
import com.oraclechain.pocketeos.utils.FilesUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SystemSettingActivity extends BaseAcitvity<SystemSettingView, SystemSettingPresenter> implements SystemSettingView {


    @BindView(R.id.system_settings_one)
    RelativeLayout mSystemSettingsOne;
    @BindView(R.id.system_settings_two)
    RelativeLayout mSystemSettingsTwo;
    @BindView(R.id.system_settings_three)
    RelativeLayout mSystemSettingsThree;
    @BindView(R.id.cache_size)
    TextView mCacheSize;
    @BindView(R.id.select_language)
    RelativeLayout mSelectLanguage;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_system_setting;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setCenterTitle(getString(R.string.system_settings));
        mCacheSize.setText(CacheDataManager.getCacheSize(new File("/data/data/" + this.getPackageName() + "/app_webview")));
    }

    @Override
    protected void initData() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    public SystemSettingPresenter initPresenter() {
        return new SystemSettingPresenter(SystemSettingActivity.this);
    }

    @Override
    public void getSystemInfoHttp(SystemInfoBean.DataBean systemInfoBean, String id) {
        Bundle bundle = new Bundle();
        bundle.putString("details", systemInfoBean.getContent());
        if (id.equals(1)) {
            bundle.putString("title", getString(R.string.setting_one));
        } else {
            bundle.putString("title", getString(R.string.setting_two));
        }
        ActivityUtils.next(SystemSettingActivity.this, RichTextActivity.class, bundle);
    }

    @Override
    public void getDataHttpFail(String msg) {
        toast(msg);
    }

    @OnClick({R.id.select_language,R.id.system_settings_one, R.id.system_settings_two, R.id.system_settings_three})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.select_language:
                ActivityUtils.next(SystemSettingActivity.this, LanguageActivity.class);
                break;
            case R.id.system_settings_one:
//                presenter.getSystemInfo("1");
                bundle.putString("details",FilesUtils.readAssetsTxt(this,"policy_pocketeos"));
                bundle.putString("title", getString(R.string.setting_one));
                ActivityUtils.next(SystemSettingActivity.this, RichTextActivity.class, bundle);
                break;
            case R.id.system_settings_two:
//                presenter.getSystemInfo("2");
                bundle.putString("details", FilesUtils.readAssetsTxt(this,"about_pocketeos"));
                bundle.putString("title", getString(R.string.setting_two));
                ActivityUtils.next(SystemSettingActivity.this, RichTextActivity.class, bundle);
                break;
            case R.id.system_settings_three:
                if (mCacheSize.getText().toString().equals("0.00KB")) {
                    toast(getString(R.string.toast_no_cache));
                } else {
                    CacheDataManager.cleanWebview(SystemSettingActivity.this);
                    CacheDataManager.cleanInternalCache(SystemSettingActivity.this);
                    mCacheSize.setText("0.00KB");
                    toast(getString(R.string.toast_clean_cache));
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
