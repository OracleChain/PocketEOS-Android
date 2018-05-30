package com.oraclechain.pocketeos.modules.account.accountdetails;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.text.ClipboardManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.google.zxing.client.result.ParsedResultType;
import com.mylhyl.zxing.scanner.encode.QREncode;
import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.app.ActivityUtils;
import com.oraclechain.pocketeos.app.AppManager;
import com.oraclechain.pocketeos.app.MyApplication;
import com.oraclechain.pocketeos.base.BaseAcitvity;
import com.oraclechain.pocketeos.bean.AccountInfoBean;
import com.oraclechain.pocketeos.bean.QrCodeAccountBean;
import com.oraclechain.pocketeos.bean.QrCodeAccountPrivateKeyBean;
import com.oraclechain.pocketeos.bean.UserBean;
import com.oraclechain.pocketeos.gen.UserBeanDao;
import com.oraclechain.pocketeos.modules.otherloginorshare.BaseUIListener;
import com.oraclechain.pocketeos.modules.otherloginorshare.WxShareAndLoginUtils;
import com.oraclechain.pocketeos.modules.wallet.createwallet.login.LoginActivity;
import com.oraclechain.pocketeos.utils.DensityUtil;
import com.oraclechain.pocketeos.utils.EncryptUtil;
import com.oraclechain.pocketeos.utils.FilesUtils;
import com.oraclechain.pocketeos.utils.JsonUtil;
import com.oraclechain.pocketeos.utils.KeyBoardUtil;
import com.oraclechain.pocketeos.utils.PasswordToKeyUtils;
import com.oraclechain.pocketeos.utils.ShowDialog;
import com.oraclechain.pocketeos.utils.ToastUtils;
import com.oraclechain.pocketeos.utils.Utils;
import com.oraclechain.pocketeos.utils.ViewToImageUtils;
import com.oraclechain.pocketeos.view.CustomSlideToUnlockView;
import com.oraclechain.pocketeos.view.dialog.confimdialog.Callback;
import com.oraclechain.pocketeos.view.dialog.confimdialog.ConfirmDialog;
import com.oraclechain.pocketeos.view.dialog.importprivatekeydialog.ImportPrivateKeyDialog;
import com.oraclechain.pocketeos.view.dialog.importprivatekeydialog.ImportrivateKeyCallBack;
import com.oraclechain.pocketeos.view.dialog.passworddialog.PasswordCallback;
import com.oraclechain.pocketeos.view.dialog.passworddialog.PasswordDialog;
import com.oraclechain.pocketeos.view.dialog.sharedialog.ShareCallBack;
import com.oraclechain.pocketeos.view.dialog.sharedialog.ShareDialog;
import com.tencent.connect.common.Constants;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzonePublish;
import com.tencent.tauth.Tencent;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class AccountDetailsActivity extends BaseAcitvity<AccountDetailsView, AccountDetailsPresenter> implements AccountDetailsView {


    @BindView(R.id.img_right)
    ImageView mImgRight;
    @BindView(R.id.img_number_code)
    ImageView mImgNumberCode;
    @BindView(R.id.set_main_number)
    Button mSetMainNumber;
    @BindView(R.id.import_private_key)
    Button mImportPrivateKey;

    CustomSlideToUnlockView mSlideToUnlock;
    ImageView mIvSlideEnd;
    PasswordDialog mDialog;
    @BindView(R.id.rel)
    RelativeLayout mRel;
    ShareDialog dialog = null;
    private AccountInfoBean mAccountInfoBean = new AccountInfoBean();
    private ImportPrivateKeyDialog mImportPrivateKeyDialog;

    @Override
    public void setMainAccountHttp(int type) {
        hideProgress();
        toast(getString(R.string.set_main_account_success));

        UserBean userBean = MyApplication.getDaoInstant().getUserBeanDao().queryBuilder().where(UserBeanDao.Properties.Wallet_phone.eq(MyApplication.getInstance().getUserBean().getWallet_phone())).build().unique();
        if (userBean != null) {
            userBean.setWallet_main_account(mAccountInfoBean.getAccount_name());
            userBean.setWallet_main_account_img(mAccountInfoBean.getAccount_img());
            ArrayList<AccountInfoBean> accountInfoBeanArrayList = new ArrayList<>();
            if (MyApplication.getInstance().getUserBean().getAccount_info() != null) {
                accountInfoBeanArrayList = JsonUtil.parseJsonToArrayList(MyApplication.getInstance().getUserBean().getAccount_info(), AccountInfoBean.class);
                for (int i = 0; i < accountInfoBeanArrayList.size(); i++) {
                    if (accountInfoBeanArrayList.get(i).getAccount_name().equals(mAccountInfoBean.getAccount_name())) {
                        accountInfoBeanArrayList.get(i).setIs_main_account("1");
                    } else {
                        accountInfoBeanArrayList.get(i).setIs_main_account("0");
                    }
                }
                userBean.setAccount_info(new Gson().toJson(accountInfoBeanArrayList));
            }
            MyApplication.getDaoInstant().getUserBeanDao().update(userBean);
            MyApplication.getInstance().getUserBean().setWallet_main_account_img(mAccountInfoBean.getAccount_img());
            MyApplication.getInstance().getUserBean().setWallet_main_account(mAccountInfoBean.getAccount_name());
            MyApplication.getInstance().getUserBean().setAccount_info(new Gson().toJson(accountInfoBeanArrayList));
            if (type == 1) {
                finish();
            } else if (type == 0) {
                mSetMainNumber.setBackgroundColor(getResources().getColor(R.color.gray_color));
                mSetMainNumber.setClickable(false);
            }
        }

    }

    @Override
    public void getDataHttpFail(String msg) {
        toast(msg);
    }

    @OnClick({R.id.img_right, R.id.set_main_number, R.id.import_private_key})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_right:

                if (dialog == null) {
                    dialog = new ShareDialog(AccountDetailsActivity.this, new ShareCallBack() {
                        @Override
                        public void goWeixinFriend() {
                            WxShareAndLoginUtils.WxBitmapShare(AccountDetailsActivity.this, ViewToImageUtils.loadBitmapFromView(mRel), WxShareAndLoginUtils.WECHAT_FRIEND);
                        }

                        @Override
                        public void goWeixinCircle() {
                            WxShareAndLoginUtils.WxBitmapShare(AccountDetailsActivity.this, ViewToImageUtils.loadBitmapFromView(mRel), WxShareAndLoginUtils.WECHAT_MOMENT);
                        }

                        @Override
                        public void goQQFriend() {
                            Bundle params = new Bundle();
                            params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_IMAGE);
                            params.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, FilesUtils.savePhoto(ViewToImageUtils.loadBitmapFromView(mRel), Environment
                                    .getExternalStorageDirectory().getAbsolutePath() + "/pocketEos/accountCode", String
                                    .valueOf(System.currentTimeMillis())));
                            params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "pocketEos");
                            params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE);
                            MyApplication.getInstance().getTencent().shareToQQ(AccountDetailsActivity.this, params, new BaseUIListener(AccountDetailsActivity.this, true));
                        }

                        @Override
                        public void goQzone() {
                            Bundle params = new Bundle();
                            params.putInt(QzonePublish.PUBLISH_TO_QZONE_KEY_TYPE, QzonePublish.PUBLISH_TO_QZONE_TYPE_PUBLISHMOOD);
                            ArrayList<String> imgUrlList = new ArrayList<>();
                            imgUrlList.add(FilesUtils.savePhoto(ViewToImageUtils.loadBitmapFromView(mRel), Environment
                                    .getExternalStorageDirectory().getAbsolutePath() + "/pocketEos/accountCode", String
                                    .valueOf(System.currentTimeMillis())));// 图片地址
                            params.putStringArrayList(QzonePublish.PUBLISH_TO_QZONE_IMAGE_URL,
                                    imgUrlList);// 图片地址ArrayList
                            MyApplication.getInstance().getTencent().publishToQzone(AccountDetailsActivity.this, params, new BaseUIListener(AccountDetailsActivity.this, true));
                        }
                    });
                    dialog.setContent("将二维码分享到");
                    dialog.setCancelable(true);
                    dialog.show();
                } else {
                    dialog.show();
                }
                break;
            case R.id.set_main_number://设为主账号
                ShowDialog.showDialog(this, "", true, null).show();
                presenter.setMianAccountData(mAccountInfoBean.getAccount_name(), 0);
                break;
            case R.id.import_private_key://导出私钥
                PasswordDialog mPasswordDialog = new PasswordDialog(AccountDetailsActivity.this, new PasswordCallback() {
                    @Override
                    public void sure(final String password) {
                        if (MyApplication.getInstance().getUserBean().getWallet_shapwd().equals(PasswordToKeyUtils.shaCheck(password))) {
                            if (mImportPrivateKeyDialog == null) {
                                mImportPrivateKeyDialog = new ImportPrivateKeyDialog(AccountDetailsActivity.this, new ImportrivateKeyCallBack() {
                                    @Override
                                    public void goWeixin() {

                                    }

                                    @Override
                                    public void goQQ() {

                                    }

                                    @Override
                                    public void goCode() {
                                        QrCodeAccountPrivateKeyBean qrCodeAccountPrivateKeyBean = new QrCodeAccountPrivateKeyBean();
                                        qrCodeAccountPrivateKeyBean.setAccount_name(mAccountInfoBean.getAccount_name());
                                        try {
                                            qrCodeAccountPrivateKeyBean.setOwner_private_key( EncryptUtil.getDecryptString(mAccountInfoBean.getAccount_owner_private_key(),password));
                                            qrCodeAccountPrivateKeyBean.setActive_private_key( EncryptUtil.getDecryptString(mAccountInfoBean.getAccount_active_private_key(),password));
                                        } catch (NoSuchAlgorithmException e) {
                                            e.printStackTrace();
                                        } catch (InvalidKeySpecException e) {
                                            e.printStackTrace();
                                        }
                                        qrCodeAccountPrivateKeyBean.setType("account_priviate_key_QRCode");
                                        //生成的账号二维码包含的信息
                                        Resources res = AccountDetailsActivity.this.getResources();
                                        Bitmap bmp = BitmapFactory.decodeResource(res, R.mipmap.ic_launcher_round);
                                        Bitmap bitmap = new QREncode.Builder(AccountDetailsActivity.this)
                                                .setColor(AccountDetailsActivity.this.getResources().getColor(R.color.title_color))//二维码颜色
                                                .setParsedResultType(ParsedResultType.TEXT)//默认是TEXT类型
                                                .setLogoBitmap(bmp)
                                                .setSize(1000)
                                                .setContents(new Gson().toJson(qrCodeAccountPrivateKeyBean))//二维码内容
                                                .build().encodeAsBitmap();
                                        if (bitmap != null) {
                                            mImportPrivateKeyDialog.setCode(bitmap);
                                        }
                                    }

                                    @Override
                                    public void copy() {
                                        try {
                                            ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                                            // 将文本内容放到系统剪贴板里。
                                            cm.setText("OWNKEY:" + EncryptUtil.getDecryptString(mAccountInfoBean.getAccount_owner_private_key(),password)+ "\nACTIVEKEY:" + EncryptUtil.getDecryptString(mAccountInfoBean.getAccount_active_private_key(),password));
                                            ToastUtils.showShortToast(R.string.copy_success);
                                        } catch (NoSuchAlgorithmException e) {
                                            e.printStackTrace();
                                        } catch (InvalidKeySpecException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                });
                                try {
                                    mImportPrivateKeyDialog.setContent( EncryptUtil.getDecryptString(mAccountInfoBean.getAccount_owner_private_key(),password),  EncryptUtil.getDecryptString(mAccountInfoBean.getAccount_active_private_key(),password));
                                } catch (NoSuchAlgorithmException e) {
                                    e.printStackTrace();
                                } catch (InvalidKeySpecException e) {
                                    e.printStackTrace();
                                }
                                mImportPrivateKeyDialog.setCancelable(true);
                                mImportPrivateKeyDialog.show();

                            } else
                                mImportPrivateKeyDialog.show();
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
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_nuumber_details;
    }

    @Override
    public AccountDetailsPresenter initPresenter() {
        return new AccountDetailsPresenter(AccountDetailsActivity.this);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);//当前页面防截屏录屏
        mIvSlideEnd = getId(R.id.iv_slide_end);
        mSlideToUnlock = getId(R.id.slide_to_unlock);


        mAccountInfoBean = getIntent().getParcelableExtra("bean");
        setCenterTitle(mAccountInfoBean.getAccount_name());
        setRightImg(true);
        mImgRight.setImageDrawable(getResources().getDrawable(R.mipmap.makecollectionshare));
        if (mAccountInfoBean.getIs_main_account().equals("1")) {
            mSetMainNumber.setBackgroundColor(getResources().getColor(R.color.gray_color));
            mSetMainNumber.setClickable(false);
        } else {
            mSetMainNumber.setBackgroundColor(getResources().getColor(R.color.theme_color));
            mSetMainNumber.setClickable(true);
        }
        QrCodeAccountBean qrCodeAccountBean = new QrCodeAccountBean();
        qrCodeAccountBean.setAccount_name(mAccountInfoBean.getAccount_name());
        qrCodeAccountBean.setAccount_img(mAccountInfoBean.getAccount_img());
        qrCodeAccountBean.setType("account_QRCode");
        //生成的账号二维码包含的信息
        Resources res = AccountDetailsActivity.this.getResources();
        Bitmap bmp = BitmapFactory.decodeResource(res, R.mipmap.ic_launcher_round);
        Bitmap bitmap = new QREncode.Builder(this)
                .setColor(AccountDetailsActivity.this.getResources().getColor(R.color.title_color))//二维码颜色
                .setParsedResultType(ParsedResultType.TEXT)//默认是TEXT类型
                .setContents(new Gson().toJson(qrCodeAccountBean))//二维码内容
                .setLogoBitmap(bmp)
                .setSize(1000)
                .build().encodeAsBitmap();
        if (bitmap != null) {
            mImgNumberCode.setImageBitmap(bitmap);
        }

        if (Utils.getSpUtils().getString("loginmode").equals("blackbox")) {
            mSetMainNumber.setVisibility(View.GONE);
        } else {
            mSetMainNumber.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    public void initEvent() {
        CustomSlideToUnlockView.CallBack callBack = new CustomSlideToUnlockView.CallBack() {
            @Override
            public void onSlide(int distance, int width1, int rootwidth) {
                if (distance > 0) {//直接通过margin实现滑动
                    mIvSlideEnd.setVisibility(View.VISIBLE);
                    int virtualValue = distance - (rootwidth - width1 - (DensityUtil.dip2px(AccountDetailsActivity.this, 40))) + DensityUtil.dip2px(AccountDetailsActivity.this, 5);//触发滑块的有效值

                    if (virtualValue == 0) {
                        mIvSlideEnd.setVisibility(View.GONE);
                        if (mDialog == null) {
                            mDialog = new PasswordDialog(AccountDetailsActivity.this, new PasswordCallback() {
                                @Override
                                public void sure(String password) {
                                    if (MyApplication.getInstance().getUserBean().getWallet_shapwd().equals(PasswordToKeyUtils.shaCheck(password))) {//本地化删除，如果删除主账号，通知服务端更改主账号
                                        final UserBean userBean = MyApplication.getDaoInstant().getUserBeanDao().queryBuilder().where(UserBeanDao.Properties.Wallet_phone.eq(MyApplication.getInstance().getUserBean().getWallet_phone())).build().unique();
                                        if (userBean != null) {
                                            ArrayList<AccountInfoBean> accountInfoBeanArrayList = new ArrayList<>();
                                            if (MyApplication.getInstance().getUserBean().getAccount_info() != null) {
                                                accountInfoBeanArrayList = JsonUtil.parseJsonToArrayList(MyApplication.getInstance().getUserBean().getAccount_info(), AccountInfoBean.class);
                                                if (accountInfoBeanArrayList.size() == 1) {//如果钱包下面只有一个账号则提示用户执行注销操作
                                                    ConfirmDialog confirmDialog = new ConfirmDialog(AccountDetailsActivity.this, new Callback() {
                                                        @Override
                                                        public void callback() {
                                                            MyApplication.getDaoInstant().getUserBeanDao().delete(userBean);
                                                            AppManager.getAppManager().finishAllActivity();
                                                            ActivityUtils.next(AccountDetailsActivity.this, LoginActivity.class, true);
                                                        }
                                                    });
                                                    confirmDialog.setCancelable(true);
                                                    confirmDialog.setToast(getString(R.string.delet_account_toast));
                                                    confirmDialog.show();
                                                } else {
                                                    for (int i = accountInfoBeanArrayList.size() - 1; i >= 0; i--) {
                                                        if (accountInfoBeanArrayList.get(i).getAccount_name().equals(mAccountInfoBean.getAccount_name())) {
                                                            accountInfoBeanArrayList.remove(i);
                                                        }
                                                    }
                                                    if (mAccountInfoBean.getIs_main_account().equals("1")) {
                                                        if (accountInfoBeanArrayList.size() > 0) {
                                                            userBean.setWallet_main_account(accountInfoBeanArrayList.get(0).getAccount_name());
                                                            userBean.setWallet_main_account_img(accountInfoBeanArrayList.get(0).getAccount_img());
                                                            accountInfoBeanArrayList.get(0).setIs_main_account("1");
                                                            MyApplication.getInstance().getUserBean().setWallet_main_account(accountInfoBeanArrayList.get(0).getAccount_name());
                                                            MyApplication.getInstance().getUserBean().setWallet_main_account_img(accountInfoBeanArrayList.get(0).getAccount_img());
                                                            userBean.setAccount_info(new Gson().toJson(accountInfoBeanArrayList));
                                                            MyApplication.getDaoInstant().getUserBeanDao().update(userBean);
                                                            MyApplication.getInstance().getUserBean().setAccount_info(new Gson().toJson(accountInfoBeanArrayList));
                                                            mAccountInfoBean = accountInfoBeanArrayList.get(0);
                                                            showProgress();
                                                            presenter.setMianAccountData(accountInfoBeanArrayList.get(0).getAccount_name(), 1);
                                                        }
                                                    } else {
                                                        userBean.setAccount_info(new Gson().toJson(accountInfoBeanArrayList));
                                                        MyApplication.getDaoInstant().getUserBeanDao().update(userBean);
                                                        MyApplication.getInstance().getUserBean().setAccount_info(new Gson().toJson(accountInfoBeanArrayList));
                                                        finish();
                                                    }
                                                }
                                                if (KeyBoardUtil.isSoftInputShow(AccountDetailsActivity.this)) {
                                                    KeyBoardUtil.getInstance(AccountDetailsActivity.this).hide();
                                                }
                                            }
                                        }
                                    } else {
                                        toast(getResources().getString(R.string.password_error));
                                    }
                                }

                                @Override
                                public void cancle() {
                                }
                            });
                            mDialog.setCancelable(true);
                            mDialog.show();
                        } else
                            mDialog.show();
                    }
                } else {
                    mIvSlideEnd.setVisibility(View.GONE);
                }

            }

            @Override
            public void onUnlocked() {
                ToastUtils.showShortToast("onUnlocked");
            }

            @Override
            public void onlocked() {
            }
        };
        mSlideToUnlock.setmCallBack(callBack);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        hideProgress();
        if (requestCode == Constants.REQUEST_QQ_SHARE || requestCode == Constants.REQUEST_QZONE_SHARE || requestCode == Constants.REQUEST_OLD_SHARE) {
            Tencent.handleResultData(data, new BaseUIListener(AccountDetailsActivity.this, true));
        }
    }
}
