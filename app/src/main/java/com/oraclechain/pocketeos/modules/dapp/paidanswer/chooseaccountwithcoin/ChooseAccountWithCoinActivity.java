package com.oraclechain.pocketeos.modules.dapp.paidanswer.chooseaccountwithcoin;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.app.ActivityUtils;
import com.oraclechain.pocketeos.app.MyApplication;
import com.oraclechain.pocketeos.bean.AccountInfoBean;
import com.oraclechain.pocketeos.utils.JsonUtil;
import com.oraclechain.pocketeos.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//进入Dapp选择coin
public class ChooseAccountWithCoinActivity extends Activity {

    @BindView(R.id.choose_account)
    TextView mChooseAccount;
    @BindView(R.id.go_on)
    TextView mGoOn;
    private OptionsPickerView pvCustomOptions;
    private List<AccountInfoBean> mAccountInfoBeanList = new ArrayList<>();
    private List<String> mStr = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_coin);
        ButterKnife.bind(this);
        WindowManager manager = getWindowManager();
        Display d = manager.getDefaultDisplay();//获取屏幕的宽高
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = (int) d.getWidth() * 1;
        getWindow().setAttributes(lp);//减少边距，宽度全屏
        mAccountInfoBeanList = JsonUtil.parseJsonToArrayList(MyApplication.getInstance().getUserBean().getAccount_info(), AccountInfoBean.class);
        for (int i = 0; i < mAccountInfoBeanList.size(); i++) {
            mStr.add(mAccountInfoBeanList.get(i).getAccount_name());
        }
    }

    @OnClick({R.id.choose_account, R.id.go_on})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.choose_account:
                if (pvCustomOptions == null) {
                    pvCustomOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                        @Override
                        public void onOptionsSelect(int options1, int option2, int options3, View v) {
                            String tx = mStr.get(options1);
                            mChooseAccount.setText(tx);
                        }
                    })
                            .setSubmitText(getString(R.string.sure))//确定按钮文字
                            .setCancelText(getString(R.string.seach_cancel))//取消按钮文字
                            .setSubmitColor(R.color.theme_color)//确定按钮文字颜色
                            .setCancelColor(R.color.theme_color)//取消按钮文字颜色
                            .setTitleColor(R.color.theme_color)
                            .build();
                    pvCustomOptions.setPicker(mStr);//添加数据
                    pvCustomOptions.show();
                } else {
                    pvCustomOptions.show();
                }
                break;
            case R.id.go_on:
                if (!mChooseAccount.getText().toString().contains(getString(R.string.choose_account))) {
                    if (pvCustomOptions != null) {
                        pvCustomOptions.dismiss();
                    }
                    Bundle bundle = new Bundle();
                    bundle.putString("account", mChooseAccount.getText().toString());
                    ActivityUtils.goBackfadeWithResult(ChooseAccountWithCoinActivity.this, 200, bundle);
                } else {
                    ToastUtils.showShortToast(R.string.choose_account);
                }
                break;
        }
    }
}
