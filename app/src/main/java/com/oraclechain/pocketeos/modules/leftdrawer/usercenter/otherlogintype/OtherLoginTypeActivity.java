package com.oraclechain.pocketeos.modules.leftdrawer.usercenter.otherlogintype;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.app.MyApplication;
import com.oraclechain.pocketeos.base.BaseAcitvity;
import com.oraclechain.pocketeos.bean.UserBean;
import com.oraclechain.pocketeos.gen.UserBeanDao;
import com.oraclechain.pocketeos.modules.otherloginorshare.BaseUIListener;
import com.oraclechain.pocketeos.modules.otherloginorshare.OtherLoginCallBack;
import com.oraclechain.pocketeos.modules.otherloginorshare.QQUserInfoBean;
import com.oraclechain.pocketeos.modules.otherloginorshare.WxShareAndLoginUtils;
import com.tencent.tauth.Tencent;

import butterknife.BindView;
import butterknife.OnClick;

//三方平台登录管理页面 QQ 微信等绑定与解绑
public class OtherLoginTypeActivity extends BaseAcitvity<OtherLoginTypeView, OtherLoginTypePresenter> implements OtherLoginTypeView {

    @BindView(R.id.other_login_img)
    ImageView mOtherLoginImg;
    @BindView(R.id.unbind_one_text)
    TextView mUnbindOneText;
    @BindView(R.id.unbind_two_text)
    TextView mUnbindTwoText;
    @BindView(R.id.bind_text)
    TextView mBindText;
    @BindView(R.id.go_bind_unbind)
    Button mGoBindUnbind;
    int type = 0;
    QQUserInfoBean mqqUserInfoBean = null;
    String mOpenid = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_other_login_type;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        type = getIntent().getIntExtra("type", 0);
        mGoBindUnbind.setBackgroundColor(getResources().getColor(R.color.button_blue));
        // 1,解绑微信2：解绑QQ3：绑定微信4：绑定QQ
        if (type == 1) {
            setCenterTitle(getString(R.string.title_go_unbind_weixin));
            mOtherLoginImg.setImageDrawable(getResources().getDrawable(R.mipmap.bind_weixin));
            mUnbindOneText.setVisibility(View.GONE);
            mUnbindTwoText.setVisibility(View.GONE);
            mBindText.setVisibility(View.VISIBLE);
            mBindText.setText(getString(R.string.bind_weixin_number) + MyApplication.getInstance().getUserBean().getWallet_weixin());
            mGoBindUnbind.setText(getString(R.string.go_unbind));
            mGoBindUnbind.setBackgroundColor(getResources().getColor(R.color.gray_color));
        } else if (type == 2) {
            setCenterTitle(getString(R.string.title_go_unbind_qq));
            mOtherLoginImg.setImageDrawable(getResources().getDrawable(R.mipmap.bind_qq));
            mUnbindOneText.setVisibility(View.GONE);
            mUnbindTwoText.setVisibility(View.GONE);
            mBindText.setVisibility(View.VISIBLE);
            mBindText.setText(getString(R.string.bind_qq_number) + MyApplication.getInstance().getUserBean().getWallet_qq());
            mGoBindUnbind.setText(getString(R.string.go_unbind));
            mGoBindUnbind.setBackgroundColor(getResources().getColor(R.color.gray_color));
        } else if (type == 3) {
            setCenterTitle(getString(R.string.title_go_bind_weixin));
            mOtherLoginImg.setImageDrawable(getResources().getDrawable(R.mipmap.bind_weixin));
            mUnbindOneText.setVisibility(View.VISIBLE);
            mUnbindTwoText.setVisibility(View.VISIBLE);
            mBindText.setVisibility(View.GONE);
            mUnbindOneText.setText(R.string.unbind_one_weixin);
            mUnbindTwoText.setText(R.string.unbind_two_weixin);
            mGoBindUnbind.setText(R.string.go_bind_weixin);
        } else if (type == 4) {
            setCenterTitle(getString(R.string.title_go_bind_qq));
            mOtherLoginImg.setImageDrawable(getResources().getDrawable(R.mipmap.bind_qq));
            mUnbindOneText.setVisibility(View.VISIBLE);
            mUnbindTwoText.setVisibility(View.VISIBLE);
            mBindText.setVisibility(View.GONE);
            mUnbindOneText.setText(R.string.unbind_one_qq);
            mUnbindTwoText.setText(R.string.unbind_two_qq);
            mGoBindUnbind.setText(R.string.go_bind_qq);
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    public void initEvent() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideProgress();
    }

    @Override
    public OtherLoginTypePresenter initPresenter() {
        return new OtherLoginTypePresenter(OtherLoginTypeActivity.this);
    }

    @OnClick(R.id.go_bind_unbind)
    public void onViewClicked() {
        showProgress();
        switch (type) {// 1,解绑微信2：解绑QQ3：绑定微信4：绑定QQ
            case 1:
                presenter.unbindWechat();
                break;
            case 2:
                presenter.unbindQQ();
                break;
            case 3:
                WxShareAndLoginUtils.WxLogin(this);
                break;
            case 4:
                MyApplication.getInstance().getTencent().login(OtherLoginTypeActivity.this, "all", new BaseUIListener(OtherLoginTypeActivity.this, new OtherLoginCallBack() {
                    @Override
                    public void userInfoData(String openid, QQUserInfoBean qqUserInfoBean) {
                        if (openid != null && qqUserInfoBean != null) {
                            mqqUserInfoBean = qqUserInfoBean;
                            mOpenid = openid;
                            presenter.bindQQ(openid, qqUserInfoBean.getNickname(), qqUserInfoBean.getFigureurl_qq_2());//通知服务端

                        }
                    }
                }));
                break;
        }
    }

    @Override
    public void unBindOtherLoginDataHttp() {
        hideProgress();
        UserBean userBean = MyApplication.getDaoInstant().getUserBeanDao().queryBuilder().where(UserBeanDao.Properties.Wallet_phone.eq(MyApplication.getInstance().getUserBean().getWallet_phone())).build().unique();
        if (userBean != null) {
            if (type == 2) {
                userBean.setWallet_qq("");
                MyApplication.getInstance().getUserBean().setWallet_qq("");
                MyApplication.getDaoInstant().getUserBeanDao().update(userBean);
                finish();
            } else if (type == 1) {
                userBean.setWallet_weixin("");
                MyApplication.getInstance().getUserBean().setWallet_weixin("");
                MyApplication.getDaoInstant().getUserBeanDao().update(userBean);
                finish();
            }
        }
    }

    @Override
    public void bindOtherLoginDataHttp() {
        UserBean userBean = MyApplication.getDaoInstant().getUserBeanDao().queryBuilder().where(UserBeanDao.Properties.Wallet_phone.eq(MyApplication.getInstance().getUserBean().getWallet_phone())).build().unique();
        if (userBean != null) {
            userBean.setWallet_qq(mOpenid);
            userBean.setWallet_name(mqqUserInfoBean.getNickname());
            userBean.setWallet_img(mqqUserInfoBean.getFigureurl_qq_2());
            MyApplication.getDaoInstant().getUserBeanDao().update(userBean);
            MyApplication.getInstance().getUserBean().setWallet_qq(mOpenid);
            MyApplication.getInstance().getUserBean().setWallet_name(mqqUserInfoBean.getNickname());
            MyApplication.getInstance().getUserBean().setWallet_img(mqqUserInfoBean.getFigureurl_qq_2());
            hideProgress();
            finish();
        }
    }

    @Override
    public void getDataHttpFail(String msg) {
        hideProgress();
        toast(msg);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        hideProgress();
        Tencent.onActivityResultData(requestCode, resultCode, data, new BaseUIListener());
    }

    @Override
    protected void onStop() {
        super.onStop();
        hideProgress();
    }
}
