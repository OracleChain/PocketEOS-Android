package com.oraclechain.pocketeos.modules.account.importaccount;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.app.ActivityUtils;
import com.oraclechain.pocketeos.app.AppManager;
import com.oraclechain.pocketeos.app.MyApplication;
import com.oraclechain.pocketeos.base.BaseAcitvity;
import com.oraclechain.pocketeos.bean.AccountInfoBean;
import com.oraclechain.pocketeos.bean.BlockChainAccountInfoBean;
import com.oraclechain.pocketeos.bean.UserBean;
import com.oraclechain.pocketeos.blockchain.cypto.ec.EosPrivateKey;
import com.oraclechain.pocketeos.gen.UserBeanDao;
import com.oraclechain.pocketeos.modules.blackbox.BlackBoxMainActivity;
import com.oraclechain.pocketeos.modules.main.MainActivity;
import com.oraclechain.pocketeos.modules.scancode.ScanCodeActivity;
import com.oraclechain.pocketeos.utils.EncryptUtil;
import com.oraclechain.pocketeos.utils.JsonUtil;
import com.oraclechain.pocketeos.utils.PasswordToKeyUtils;
import com.oraclechain.pocketeos.utils.Utils;
import com.oraclechain.pocketeos.view.ClearEditText;
import com.oraclechain.pocketeos.view.dialog.passworddialog.PasswordCallback;
import com.oraclechain.pocketeos.view.dialog.passworddialog.PasswordDialog;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.ljp.permission.PermissionItem;

//导入账号
public class ImportAccountActivity extends BaseAcitvity<ImportAccountView, ImportAccountPresenter> implements ImportAccountView {


    @BindView(R.id.go_scan_code)
    RelativeLayout mGoScanCode;
    @BindView(R.id.import_number)
    Button mImportNumber;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.account_name)
    ClearEditText mAccountName;
    @BindView(R.id.owner_private_key)
    ClearEditText mOwnerPrivateKey;
    @BindView(R.id.active_private_key)
    ClearEditText mActivePrivateKey;


    private String mAccount_owner_private_key, mAccount_active_private_key = null;
    private String mAccount_owner_public_key, mAccount_active_public_key = null;

    private EosPrivateKey mOwnerKey;
    private EosPrivateKey mActiveKey;

    private String userPassword = null;
    private UserBean userBean = new UserBean();
    private List<AccountInfoBean> mAccountInfoBeanList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_import_number;
    }

    @Override
    public ImportAccountPresenter initPresenter() {
        return new ImportAccountPresenter(ImportAccountActivity.this);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setCenterTitle(getString(R.string.import_account));
        if (getIntent().getStringExtra("account_name") != null) {
            mAccountName.setText(getIntent().getStringExtra("account_name"));
            mOwnerPrivateKey.setText(getIntent().getStringExtra("owner_private_key"));
            mActivePrivateKey.setText(getIntent().getStringExtra("active_private_key"));
        }
        if (!Utils.getSpUtils().getString("loginmode").equals("blackbox")) {
            userBean = MyApplication.getDaoInstant().getUserBeanDao().queryBuilder().where(UserBeanDao.Properties.Wallet_phone.eq(Utils.getSpUtils().getString("firstUser"))).build().unique();
        } else {
            userBean = MyApplication.getDaoInstant().getUserBeanDao().queryBuilder().where(UserBeanDao.Properties.Wallet_name.eq(Utils.getSpUtils().getString("firstUser"))).build().unique();
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    public void initEvent() {
    }

    @Override
    public void getBlockchainAccountInfoDataHttp(BlockChainAccountInfoBean.DataBean blockChainAccountInfoBean) {
        hideProgress();
        String chainAccountOwnerKey = null;
        String chainAccountActiveKey = null;
        for (int i = 0; i < blockChainAccountInfoBean.getPermissions().size(); i++) {
            if (blockChainAccountInfoBean.getPermissions().get(i).getPerm_name().equals("owner")) {
                chainAccountOwnerKey = blockChainAccountInfoBean.getPermissions().get(i).getRequired_auth().getKeys().get(0).getKey();
            } else {
                chainAccountActiveKey = blockChainAccountInfoBean.getPermissions().get(i).getRequired_auth().getKeys().get(0).getKey();
            }
        }
        if (mAccount_active_public_key.equals(chainAccountActiveKey) && mAccount_owner_public_key.equals(chainAccountOwnerKey)) {
            presenter.postEosAccountData(mAccountName.getText().toString().trim(), userBean.getWallet_uid());//只是通知，不以服务端返回结果作为查询依据
            toast(getString(R.string.import_success));
            ArrayList<AccountInfoBean> accountInfoBeanArrayList = new ArrayList<>();
            if (MyApplication.getInstance().getUserBean().getAccount_info() != null) {
                accountInfoBeanArrayList = JsonUtil.parseJsonToArrayList(MyApplication.getInstance().getUserBean().getAccount_info(), AccountInfoBean.class);
            }
            AccountInfoBean accountInfoBean = new AccountInfoBean();
            accountInfoBean.setAccount_name(mAccountName.getText().toString().trim());
            accountInfoBean.setAccount_img("");
            try {
                accountInfoBean.setAccount_active_private_key(EncryptUtil.getEncryptString(mAccount_active_private_key, userPassword));
                accountInfoBean.setAccount_owner_private_key(EncryptUtil.getEncryptString(mAccount_owner_private_key, userPassword));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            }
            accountInfoBean.setAccount_active_public_key(mAccount_active_public_key);
            accountInfoBean.setAccount_owner_public_key(mAccount_owner_public_key);
            accountInfoBean.setIs_privacy_policy("0");
            if (accountInfoBeanArrayList.size() == 0) {
                accountInfoBean.setIs_main_account("1");
                if (Utils.getSpUtils().getString("loginmode").equals("phone")) {
                    presenter.setMianAccountData(mAccountName.getText().toString().trim());//只是通知，不以服务端返回结果作为查询依据
                }
            } else {
                accountInfoBean.setIs_main_account("0");
            }
            if (userBean != null) {
                if (accountInfoBeanArrayList.size() == 0) {
                    userBean.setWallet_main_account(accountInfoBean.getAccount_name());
                    userBean.setWallet_main_account_img(accountInfoBean.getAccount_img());

                }
                accountInfoBeanArrayList.add(accountInfoBean);
                userBean.setAccount_info(new Gson().toJson(accountInfoBeanArrayList));
                MyApplication.getDaoInstant().getUserBeanDao().update(userBean);
            }

            hideProgress();
            AppManager.getAppManager().finishAllActivity();
            if (!Utils.getSpUtils().getString("loginmode").equals("blackbox")) {
                ActivityUtils.next(ImportAccountActivity.this, MainActivity.class, true);
            }else {
                ActivityUtils.next(ImportAccountActivity.this, BlackBoxMainActivity.class, true);
            }
        } else {
            toast(getString(R.string.account_key_error));
        }

    }

    @Override
    public void setMainAccountHttp() {

    }

    @Override
    public void getDataHttpFail(String msg) {
        hideProgress();
        toast(msg);
    }

    @Override
    public void postEosAccountDataHttp() {

    }

    @OnClick({R.id.go_scan_code, R.id.import_number})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.go_scan_code:
                List<PermissionItem> permissonItems = new ArrayList<PermissionItem>();
                permissonItems.add(new PermissionItem(Manifest.permission.CAMERA, getString(R.string.camer), R.drawable.permission_ic_camera));
                if (Utils.getPermissions(permissonItems, getString(R.string.open_camer_scan))) {
                    Bundle bundle = new Bundle();
                    bundle.putString("from", "import_account");
                    ActivityUtils.next(ImportAccountActivity.this, ScanCodeActivity.class, bundle, 100);
                }
                break;
            case R.id.import_number:
                if (mAccountName.getText().toString().length() != 0 && mActivePrivateKey.getText().toString().length() != 0 && mOwnerPrivateKey.getText().toString().length() != 0) {
                    mAccount_owner_private_key = mOwnerPrivateKey.getText().toString();
                    mAccount_active_private_key = mActivePrivateKey.getText().toString();
                    try {
                        mActiveKey = new EosPrivateKey(mAccount_active_private_key);
                        mOwnerKey = new EosPrivateKey(mAccount_owner_private_key);
                    } catch (Exception e) {
                        e.printStackTrace();
                        toast("私钥格式错误");
                        return;
                    }
                    if (userBean != null && MyApplication.getInstance().getUserBean().getAccount_info() != null && MyApplication.getInstance().getUserBean().getAccount_info().contains(mAccountName.getText().toString())) {
                        toast(getString(R.string.import_two_account));
                        return;
                    }
                    PasswordDialog mPasswordDialog = new PasswordDialog(ImportAccountActivity.this, new PasswordCallback() {
                        @Override
                        public void sure(final String password) {
                            if (MyApplication.getInstance().getUserBean().getWallet_shapwd().equals(PasswordToKeyUtils.shaCheck(password))) {
                                userPassword = password;

                                mAccount_owner_public_key = mOwnerKey.getPublicKey().toString();
                                mAccount_active_public_key = mActiveKey.getPublicKey().toString();
                                showProgress();
                                presenter.getAccountInfoData(mAccountName.getText().toString().trim());

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
                } else {
                    toast(getString(R.string.import_all_info));
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 200) {
            mAccountName.setText(data.getStringExtra("account_name"));
            mOwnerPrivateKey.setText(data.getStringExtra("owner_private_key"));
            mActivePrivateKey.setText(data.getStringExtra("active_private_key"));
            mAccountName.setSelection(mAccountName.getText().length());
        }
    }
}
