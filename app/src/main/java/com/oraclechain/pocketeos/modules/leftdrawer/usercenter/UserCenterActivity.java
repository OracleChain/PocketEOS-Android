package com.oraclechain.pocketeos.modules.leftdrawer.usercenter;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.app.ActivityUtils;
import com.oraclechain.pocketeos.app.AppManager;
import com.oraclechain.pocketeos.app.MyApplication;
import com.oraclechain.pocketeos.base.BaseAcitvity;
import com.oraclechain.pocketeos.bean.UpdataPhotoBean;
import com.oraclechain.pocketeos.bean.UserBean;
import com.oraclechain.pocketeos.gen.UserBeanDao;
import com.oraclechain.pocketeos.modules.leftdrawer.usercenter.changename.ChangeNameActivity;
import com.oraclechain.pocketeos.modules.leftdrawer.usercenter.otherlogintype.OtherLoginTypeActivity;
import com.oraclechain.pocketeos.modules.wallet.createwallet.login.LoginActivity;
import com.oraclechain.pocketeos.utils.DensityUtil;
import com.oraclechain.pocketeos.utils.FilesUtils;
import com.oraclechain.pocketeos.utils.PasswordToKeyUtils;
import com.oraclechain.pocketeos.utils.ShowDialog;
import com.oraclechain.pocketeos.utils.ToastUtils;
import com.oraclechain.pocketeos.utils.Utils;
import com.oraclechain.pocketeos.view.CircleImageView;
import com.oraclechain.pocketeos.view.CustomSlideToUnlockView;
import com.oraclechain.pocketeos.view.dialog.gophotodialog.GoPhotoCallBack;
import com.oraclechain.pocketeos.view.dialog.gophotodialog.GoPhotoDialog;
import com.oraclechain.pocketeos.view.dialog.passworddialog.PasswordCallback;
import com.oraclechain.pocketeos.view.dialog.passworddialog.PasswordDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;
import me.ljp.permission.PermissionItem;

//用户中心
public class UserCenterActivity extends BaseAcitvity<UserCenterView, UserCenterPresenter> implements UserCenterView {

    private static final int CHOOSE_PICTURE = 0;
    private static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;
    private static final int CHANGE_NAME = 3;
    private static Uri tempUri;
    @BindView(R.id.user_img)
    CircleImageView mUserImg;
    @BindView(R.id.go_photo)
    RelativeLayout mGoPhoto;
    @BindView(R.id.user_name)
    TextView mUserName;
    @BindView(R.id.go_name)
    RelativeLayout mGoName;
    @BindView(R.id.user_weixin)
    TextView mUserWeixin;
    @BindView(R.id.go_weixin)
    RelativeLayout mGoWeixin;
    @BindView(R.id.user_qq)
    TextView mUserQq;
    @BindView(R.id.go_qq)
    RelativeLayout mGoQq;
    @BindView(R.id.create_a_wallet)
    Button mCreateAWallet;
    CustomSlideToUnlockView mSlideToUnlock;
    @BindView(R.id.iv_slide_end)
    ImageView mIvSlideEnd;
    PasswordDialog dialog;
    GoPhotoDialog goPhotoDialog;
    private File fileUri;
    private String imagePath;
    private Bitmap mBitmap;
    private FrameLayout.LayoutParams params;
    private int windowWidth, randNum;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_center;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setCenterTitle(getString(R.string.title_user_center));
        mIvSlideEnd = getId(R.id.iv_slide_end);
        mSlideToUnlock = getId(R.id.slide_to_unlock);
        windowWidth = getWindow().getWindowManager().getDefaultDisplay().getWidth();
        if (MyApplication.getInstance().getUserBean() != null) {
            mUserName.setText(MyApplication.getInstance().getUserBean().getWallet_name() + getString(R.string.wallet));
            if (!TextUtils.isEmpty(MyApplication.getInstance().getUserBean().getWallet_weixin())) {
                mUserWeixin.setText(R.string.bind);
                mUserWeixin.setTextColor(getResources().getColor(R.color.red_packet_buutton));
            } else {
                mUserWeixin.setText(R.string.unbind_one_weixin);
                mUserWeixin.setTextColor(getResources().getColor(R.color.gray_color));
            }
            if (!TextUtils.isEmpty(MyApplication.getInstance().getUserBean().getWallet_qq())) {
                mUserQq.setText(R.string.bind);
                mUserQq.setTextColor(getResources().getColor(R.color.red_packet_buutton));
            } else {
                mUserQq.setText(R.string.unbind_one_qq);
                mUserQq.setTextColor(getResources().getColor(R.color.gray_color));
            }
            MyApplication.getInstance().showCirImage(MyApplication.getInstance().getUserBean().getWallet_img(), mUserImg);
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    public void initEvent() {
        final Random random = new Random();
        randNum = random.nextInt(50) % (windowWidth / 2 - 50 + 1) + (50);//生成50-一半控件宽度的随机数
        CustomSlideToUnlockView.CallBack callBack = new CustomSlideToUnlockView.CallBack() {
            @Override
            public void onSlide(int distance, int width1, int rootwidth) {
                if (distance > 0) {//直接通过margin实现滑动
                    mIvSlideEnd.setVisibility(View.VISIBLE);
                    params = (FrameLayout.LayoutParams) mIvSlideEnd.getLayoutParams();
                    params.width = width1;
                    params.setMargins(0, DensityUtil.dip2px(UserCenterActivity.this, 5), (rootwidth / 2 - width1 - randNum), DensityUtil.dip2px(UserCenterActivity.this, 5));
                    mIvSlideEnd.setLayoutParams(params);
                    int virtualValue = distance - (rootwidth - width1 - ((rootwidth / 2 - width1 - randNum))) + DensityUtil.dip2px(UserCenterActivity.this, 5);//触发滑块的有效值

                    if (virtualValue == 0) {
                        mIvSlideEnd.setVisibility(View.GONE);
                        if (dialog == null) {
                            dialog = new PasswordDialog(UserCenterActivity.this, new PasswordCallback() {
                                @Override
                                public void sure(String password) {
                                    if (MyApplication.getInstance().getUserBean().getWallet_shapwd().equals(PasswordToKeyUtils.shaCheck(password))) {
                                        UserBean userBean = MyApplication.getDaoInstant().getUserBeanDao().queryBuilder().where(UserBeanDao.Properties.Wallet_phone.eq(MyApplication.getInstance().getUserBean().getWallet_phone())).build().unique();
                                        if (userBean != null) {
                                            MyApplication.getDaoInstant().getUserBeanDao().delete(userBean);
                                            AppManager.getAppManager().finishAllActivity();
                                            ActivityUtils.next(UserCenterActivity.this, LoginActivity.class, true);
                                        }
                                    } else {
                                        toast(getResources().getString(R.string.password_error));
                                    }
                                }

                                @Override
                                public void cancle() {
                                }
                            });
                            dialog.setCancelable(true);
                            dialog.show();
                        } else
                            dialog.show();
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
                randNum = random.nextInt(50) % (windowWidth / 2 - 50 + 1) + (50);//生成50-一半控件宽度的随机数
            }
        };
        mSlideToUnlock.setmCallBack(callBack);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mUserName.setText(MyApplication.getInstance().getUserBean().getWallet_name() + getString(R.string.wallet));
        if (!TextUtils.isEmpty(MyApplication.getInstance().getUserBean().getWallet_weixin())) {
            mUserWeixin.setText(R.string.bind);
            mUserWeixin.setTextColor(getResources().getColor(R.color.red_packet_buutton));
        } else {
            mUserWeixin.setText(R.string.unbind_one_weixin);
            mUserWeixin.setTextColor(getResources().getColor(R.color.gray_color));
        }
        if (!TextUtils.isEmpty(MyApplication.getInstance().getUserBean().getWallet_qq())) {
            mUserQq.setText(R.string.bind);
            mUserQq.setTextColor(getResources().getColor(R.color.red_packet_buutton));
        } else {
            mUserQq.setText(R.string.unbind_one_qq);
            mUserQq.setTextColor(getResources().getColor(R.color.gray_color));
        }
        MyApplication.getInstance().showCirImage(MyApplication.getInstance().getUserBean().getWallet_img(), mUserImg);
    }

    @Override
    public UserCenterPresenter initPresenter() {
        return new UserCenterPresenter(UserCenterActivity.this);
    }

    @Override
    public void headImgUploadaDataHttp(UpdataPhotoBean updataPhotoBean) {
        ShowDialog.dissmiss();
        toast(updataPhotoBean.getMessage());
        mUserImg.setImageBitmap(mBitmap);
        UserBean userBean = MyApplication.getDaoInstant().getUserBeanDao().queryBuilder().where(UserBeanDao.Properties.Wallet_phone.eq(MyApplication.getInstance().getUserBean().getWallet_phone())).build().unique();
        if (userBean != null) {
            userBean.setWallet_img(updataPhotoBean.getData());
            MyApplication.getDaoInstant().getUserBeanDao().update(userBean);
            MyApplication.getInstance().getUserBean().setWallet_img(updataPhotoBean.getData());
        }
    }

    @Override
    public void getDataHttpFail(String msg) {

    }

    @OnClick({R.id.go_photo, R.id.go_name, R.id.go_weixin, R.id.go_qq, R.id.create_a_wallet})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.go_photo:
                List<PermissionItem> permissonItems = new ArrayList<PermissionItem>();
                permissonItems.add(new PermissionItem(Manifest.permission.CAMERA, getString(R.string.camer), R.drawable.permission_ic_camera));
                permissonItems.add(new PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, getString(R.string.WRITE_STORAGE), R.drawable.permission_card1));
                if (Utils.getPermissions(permissonItems, getString(R.string.change_photo))) {
                    if (goPhotoDialog == null) {
                        goPhotoDialog = new GoPhotoDialog(UserCenterActivity.this, new GoPhotoCallBack() {
                            @Override
                            public void goSystemPhoto() {
                                Intent intent = new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(intent, CHOOSE_PICTURE);
                            }

                            @Override
                            public void goTakePhoto() {
                                String pathUri = Environment
                                        .getExternalStorageDirectory().getAbsolutePath() + "/pocketEos/Picture";
                                if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                                    File f = new File(pathUri);// 目录
                                    if (!f.exists()) {
                                        f.mkdirs();
                                    }
                                }
                                fileUri = new File(pathUri, System.currentTimeMillis() + "原图.png");
                                Intent openCameraIntent = new Intent(
                                        MediaStore.ACTION_IMAGE_CAPTURE);
                                tempUri = Uri.fromFile(fileUri);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                    tempUri = FileProvider.getUriForFile(UserCenterActivity.this, "com.oraclechain.pocketeos.fileprovider", fileUri);//通过FileProvider创建一个content类型的Uri
                                    openCameraIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //添加这一句表示对目标应用临时授权该Uri所代表的文件
                                }
                                //将拍照结果保存至photo_file的Uri中，不保留在相册中
                                openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
                                startActivityForResult(openCameraIntent, TAKE_PICTURE);

                            }

                        });

                        goPhotoDialog.setCancelable(true);
                        goPhotoDialog.show();
                    } else {
                        goPhotoDialog.show();
                    }
                }
                break;
            case R.id.go_name:
                ActivityUtils.next(this, ChangeNameActivity.class);
                break;
            case R.id.go_weixin://1,解绑微信2：解绑QQ3：绑定微信4：绑定QQ
                if (!TextUtils.isEmpty(MyApplication.getInstance().getUserBean().getWallet_weixin())) {
                    bundle.putInt("type", 1);
                } else {
                    bundle.putInt("type", 3);
                }
                ActivityUtils.next(this, OtherLoginTypeActivity.class, bundle);
                break;
            case R.id.go_qq:
                if (!TextUtils.isEmpty(MyApplication.getInstance().getUserBean().getWallet_qq())) {
                    bundle.putInt("type", 2);
                } else {
                    bundle.putInt("type", 4);
                }
                ActivityUtils.next(this, OtherLoginTypeActivity.class, bundle);
                break;
            case R.id.create_a_wallet:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == UserCenterActivity.RESULT_OK) {
            switch (requestCode) {
                case TAKE_PICTURE:
                    cutImage(tempUri);
                    break;
                case CHOOSE_PICTURE:
                    cutImage(data.getData());
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        setImageToView(data);
                    }
                    break;
            }
        }
    }

    //裁剪图片
    public void cutImage(Uri uri) {
        if (uri == null) {
           toast(getString(R.string.uri_no_exist));
        }
        Intent intent = new Intent("com.android.camera.action.CROP");
        tempUri = uri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    //保存裁剪之后的图片数据
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            mBitmap = extras.getParcelable("data");
            mBitmap = FilesUtils.toRoundBitmap(mBitmap, tempUri);
            imagePath = FilesUtils.savePhoto(mBitmap, Environment
                    .getExternalStorageDirectory().getAbsolutePath() + "/pocketEos/ClipPhoto", String
                    .valueOf(System.currentTimeMillis()));
            if (imagePath != null) {
                showProgress();
                presenter.headImgUploadaData(imagePath);
                Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri uri = Uri.fromFile(new File(imagePath));
                intent.setData(uri);
                this.sendBroadcast(intent);//这个广播的目的就是更新图库，发了这个广播进入相册就可以找到你保存的图片了！
            }
        }
    }
}
