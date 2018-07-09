package com.oraclechain.pocketeos.modules.account.createaccount;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.utils.OkLogger;
import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.app.ActivityUtils;
import com.oraclechain.pocketeos.app.MyApplication;
import com.oraclechain.pocketeos.base.BaseAcitvity;
import com.oraclechain.pocketeos.bean.AccountInfoBean;
import com.oraclechain.pocketeos.bean.UserBean;
import com.oraclechain.pocketeos.blockchain.cypto.ec.EosPrivateKey;
import com.oraclechain.pocketeos.gen.UserBeanDao;
import com.oraclechain.pocketeos.modules.account.backupaccount.BackUpKeyActivity;
import com.oraclechain.pocketeos.modules.account.importaccount.ImportAccountActivity;
import com.oraclechain.pocketeos.modules.account.mapaccount.MapAccountActivity;
import com.oraclechain.pocketeos.modules.leftdrawer.systemsetting.RichTextActivity;
import com.oraclechain.pocketeos.utils.EncryptUtil;
import com.oraclechain.pocketeos.utils.FilesUtils;
import com.oraclechain.pocketeos.utils.JsonUtil;
import com.oraclechain.pocketeos.utils.PasswordToKeyUtils;
import com.oraclechain.pocketeos.utils.PublicAndPrivateKeyUtils;
import com.oraclechain.pocketeos.utils.RegexUtil;
import com.oraclechain.pocketeos.utils.Utils;
import com.oraclechain.pocketeos.view.ClearEditText;
import com.oraclechain.pocketeos.view.dialog.passworddialog.PasswordCallback;
import com.oraclechain.pocketeos.view.dialog.passworddialog.PasswordDialog;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

//创建账号
public class CreateAccountActivity extends BaseAcitvity<CreateAccountView, CreateAccountPresenter> implements CreateAccountView {

    @BindView(R.id.account_name)
    ClearEditText mAccountName;
    @BindView(R.id.check_box)
    CheckBox mCheckBox;
    @BindView(R.id.create_account)
    Button mCreateAccount;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.go_import_account)
    TextView mGoImportAccount;
    @BindView(R.id.go_pocketeos_user)
    TextView mGoPocketeosUser;
    @BindView(R.id.go_map_account)
    TextView mGoMapAccount;

    private String mAccount_owner_private_key, mAccount_active_private_key = null;
    private String mAccount_owner_public_key, mAccount_active_public_key = null;
    private String userPassword = null;


    private EosPrivateKey mOwnerKey;
    private EosPrivateKey mActiveKey;

    @OnClick(R.id.create_account)
    public void onViewClicked() {
        if (!Utils.getSpUtils().getString("loginmode", "").equals("phone")) {
            toast(getString(R.string.black_box_creat_account_toast));
            return;
        }
        if (RegexUtil.isEosName(mAccountName.getText().toString())) {
            PasswordDialog dialog = new PasswordDialog(CreateAccountActivity.this, new PasswordCallback() {
                @Override
                public void sure(String password) {
                    if (MyApplication.getInstance().getUserBean().getWallet_shapwd().equals(PasswordToKeyUtils.shaCheck(password))) {
                        userPassword = password;
                        showProgress();
                        mOwnerKey = PublicAndPrivateKeyUtils.getPrivateKey(2)[0];
                        mActiveKey = PublicAndPrivateKeyUtils.getPrivateKey(2)[1];
                        mAccount_owner_public_key = mOwnerKey.getPublicKey().toString();
                        mAccount_active_public_key = mActiveKey.getPublicKey().toString();
                        mAccount_active_private_key = mActiveKey.toString();
                        mAccount_owner_private_key = mOwnerKey.toString();
                        OkLogger.i("===============>mAccount_active_private_key"+mAccount_active_private_key);
                        OkLogger.i("===============>mAccount_owner_private_key"+mAccount_owner_private_key);
                        presenter.postEosAccountData(mAccountName.getText().toString().trim(), mAccount_owner_public_key, mAccount_active_public_key);
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
        } else {
            toast(getString(R.string.input_account_name));
        }
    }


    @Override
    public void postEosAccountDataHttp() {
        hideProgress();
        toast(getString(R.string.eos_register_success));
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
        UserBean userBean = null;
        if (Utils.getSpUtils().getString("loginmode").equals("phone")) {
            userBean = MyApplication.getDaoInstant().getUserBeanDao().queryBuilder().where(UserBeanDao.Properties.Wallet_phone.eq(Utils.getSpUtils().getString("firstUser"))).build().unique();
        } else {
            userBean = MyApplication.getDaoInstant().getUserBeanDao().queryBuilder().where(UserBeanDao.Properties.Wallet_name.eq(Utils.getSpUtils().getString("firstUser"))).build().unique();
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

        Bundle bundle = new Bundle();
        bundle.putParcelable("account", accountInfoBean);
        ActivityUtils.next(this, BackUpKeyActivity.class, bundle);
    }

    @Override
    public void getDataHttpFail(String msg) {
        hideProgress();
        toast(msg);
    }

    @Override
    public void setMainAccountHttp() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_create_number;
    }

    @Override
    public CreateAccountPresenter initPresenter() {
        return new CreateAccountPresenter(CreateAccountActivity.this);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setCenterTitle(getString(R.string.creat_account));
        if (getIntent().getIntExtra("type", 1) == 1) {
            mGoImportAccount.setVisibility(View.VISIBLE);
        } else if (getIntent().getIntExtra("type", 1) == 2) {
            mGoImportAccount.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    public void initEvent() {
        Bundle bundle = new Bundle();
        mGoImportAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("from", "creat");
                ActivityUtils.next(CreateAccountActivity.this, ImportAccountActivity.class, bundle);
            }
        });
        mGoPocketeosUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("details", FilesUtils.readAssetsTxt(CreateAccountActivity.this, "pocketeos_user"));
                bundle.putString("title", getString(R.string.setting_one));
                ActivityUtils.next(CreateAccountActivity.this, RichTextActivity.class, bundle);
            }
        });
        mGoMapAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.next(CreateAccountActivity.this, MapAccountActivity.class);
            }
        });
    }
}
