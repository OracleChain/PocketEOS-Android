package com.oraclechain.pocketeos.modules.wallet.walletmanagement;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.adapter.baseadapter.CommonAdapter;
import com.oraclechain.pocketeos.adapter.baseadapter.base.ViewHolder;
import com.oraclechain.pocketeos.adapter.baseadapter.wrapper.HeaderAndFooterWrapper;
import com.oraclechain.pocketeos.app.ActivityUtils;
import com.oraclechain.pocketeos.app.MyApplication;
import com.oraclechain.pocketeos.base.BaseAcitvity;
import com.oraclechain.pocketeos.bean.AccountInfoBean;
import com.oraclechain.pocketeos.bean.BaseBean;
import com.oraclechain.pocketeos.bean.UserBean;
import com.oraclechain.pocketeos.modules.account.accountdetails.AccountDetailsActivity;
import com.oraclechain.pocketeos.modules.account.createaccount.CreateAccountActivity;
import com.oraclechain.pocketeos.modules.account.importaccount.ImportAccountActivity;
import com.oraclechain.pocketeos.utils.EncryptUtil;
import com.oraclechain.pocketeos.utils.FilesUtils;
import com.oraclechain.pocketeos.utils.JsonUtil;
import com.oraclechain.pocketeos.utils.PasswordToKeyUtils;
import com.oraclechain.pocketeos.utils.TextDrawUtil;
import com.oraclechain.pocketeos.utils.Utils;
import com.oraclechain.pocketeos.view.dialog.backupnumberdialog.BackUpNumberCallBack;
import com.oraclechain.pocketeos.view.dialog.backupnumberdialog.BackUpNumberDialog;
import com.oraclechain.pocketeos.view.dialog.changepassworddialog.ChangePasswordDialog;
import com.oraclechain.pocketeos.view.dialog.changepassworddialog.PasswordCallback;
import com.oraclechain.pocketeos.view.swiperecycle.SwipeItemLayout;
import com.oraclechain.pocketeos.view.swiperecycle.SwipeMenuRecyclerView;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.ljp.permission.PermissionItem;

//钱包管理页面
public class WalletManagementActivity extends BaseAcitvity<WalletManagementView, WalletManagementPresenter> implements WalletManagementView {


    @BindView(R.id.user_account_number)
    SwipeMenuRecyclerView mUserAccountNumber;

    TextView desc ,create_number, import_number, backup_number, reset_password, main_account;
    ImageView main_account_img;
    private CommonAdapter mCommonAdapter;
    private BackUpNumberDialog dialog;
    private String UserBackupPath = null;//用户加密备份文件路径
    private ArrayList<AccountInfoBean> mAccountInfoBeanList = new ArrayList<>();

    private UserBean userBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wallet_management;
    }

    @Override
    public WalletManagementPresenter initPresenter() {
        return new WalletManagementPresenter();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setCenterTitle(getString(R.string.wallet_manget_title));

    }

    @Override
    protected void initData() {
        userBean = MyApplication.getInstance().getUserBean();
        mAccountInfoBeanList = JsonUtil.parseJsonToArrayList(userBean.getAccount_info(), AccountInfoBean.class);
        for (int i = 0; i < mAccountInfoBeanList.size(); i++) {
            if (mAccountInfoBeanList.get(i).getIs_main_account().equals("1")) {//剔除主账号
                mAccountInfoBeanList.remove(i);
            }
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        layoutManager.setSmoothScrollbarEnabled(true);
        mUserAccountNumber.setLayoutManager(layoutManager);
        mCommonAdapter = new CommonAdapter<AccountInfoBean>(this, R.layout.item_comment_swip, mAccountInfoBeanList) {
            @Override
            protected void convert(final ViewHolder holder, final AccountInfoBean accountInfoBean, final int position) {
                final TextView oneMenu = holder.getView(R.id.right_one);
                final TextView account_number = holder.getView(R.id.account_number);
                final ImageView account_img = holder.getView(R.id.account_img);
                final ImageView account_lock = holder.getView(R.id.account_lock);
                final SwipeItemLayout swipeLayout = (SwipeItemLayout) holder.itemView;
                account_number.setText(accountInfoBean.getAccount_name());
                if (accountInfoBean.getIs_privacy_policy().equals("1")) {
                    account_lock.setVisibility(View.VISIBLE);
                } else {
                    account_lock.setVisibility(View.GONE);
                }
                MyApplication.getInstance().showImage(accountInfoBean.getAccount_img(), account_img);
                oneMenu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.setPolicyAccountData(accountInfoBean.getAccount_name(), (accountInfoBean.getIs_privacy_policy().equals("0") ? "1" : "0"), (position - 1));
                        swipeLayout.close();
                    }
                });
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("bean", accountInfoBean);
                        ActivityUtils.next(WalletManagementActivity.this, AccountDetailsActivity.class, bundle);
                    }
                });
            }
        };

        //顶部布局作为头添加进去
        HeaderAndFooterWrapper headerAndFooterWrapper = new HeaderAndFooterWrapper(mCommonAdapter);
        LayoutInflater mInflater = LayoutInflater.from(this);
        View contentView = mInflater.inflate(R.layout.wallet_management_header, null);
        LinearLayout linearLayout = (LinearLayout) contentView.findViewById(R.id.wallet_management_header_ll);
        desc = (TextView) contentView.findViewById(R.id.desc);
        create_number = (TextView) contentView.findViewById(R.id.create_number);
        import_number = (TextView) contentView.findViewById(R.id.import_number);
        backup_number = (TextView) contentView.findViewById(R.id.backup_number);
        reset_password = (TextView) contentView.findViewById(R.id.reset_password);
        main_account = (TextView) contentView.findViewById(R.id.main_account);
        main_account_img = (ImageView) contentView.findViewById(R.id.main_account_img);
        headerAndFooterWrapper.addHeaderView(linearLayout);

        main_account.setText(userBean.getWallet_main_account());
        MyApplication.getInstance().showImage(userBean.getWallet_main_account_img(), main_account_img);
//        mUserAccountNumber.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL, 1, getResources().getColor(R.color.line)));
        mUserAccountNumber.setAdapter(headerAndFooterWrapper);


        if (!Utils.getSpUtils().getString("loginmode", "").equals("phone")) {
            desc.setBackgroundColor(getResources().getColor(R.color.black_box_coin_backgroud));
            desc.setTextColor(getResources().getColor(R.color.blackbox_desc_text));
            TextDrawUtil.setDrawableTop(this,create_number, R.mipmap.black_box_creat_account);
            TextDrawUtil.setDrawableTop(this,import_number, R.mipmap.black_box_import_account);
            TextDrawUtil.setDrawableTop(this,backup_number, R.mipmap.black_box_back_up_wallet);
            TextDrawUtil.setDrawableTop(this,reset_password, R.mipmap.black_box_chang_pwd);
        }
    }

    @Override
    public void initEvent() {
        create_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("type", 2);
                ActivityUtils.next(WalletManagementActivity.this, CreateAccountActivity.class, bundle);
            }
        });
        import_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.next(WalletManagementActivity.this, ImportAccountActivity.class);
            }
        });
        backup_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建本地备份
                List<PermissionItem> permissonItems = new ArrayList<PermissionItem>();
                permissonItems.add(new PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, getString(R.string.WRITE_STORAGE), R.drawable.permission_card1));
                permissonItems.add(new PermissionItem(Manifest.permission.READ_EXTERNAL_STORAGE, getString(R.string.READ_STORAGE), R.drawable.permission_card1));
                if (Utils.getPermissions(permissonItems, getString(R.string.WRITE_EXTERNAL_STORAGE))) {
                    String jsonString = new Gson().toJson(MyApplication.getInstance().getUserBean());

                    if (dialog == null) {
                        //创建本地备份生成文件
                        UserBackupPath = FilesUtils.saveTxtFile(jsonString, Environment
                                .getExternalStorageDirectory().getAbsolutePath() + "/pocketEos/UserBackup", userBean + "的钱包");
                        dialog = new BackUpNumberDialog(WalletManagementActivity.this, new BackUpNumberCallBack() {
                        });
                        dialog.setContent(userBean.getWallet_name() + "的钱包", UserBackupPath);
                        dialog.setCancelable(true);
                        dialog.show();
                    } else {
                        dialog.show();
                    }
                }
            }
        });
        reset_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangePasswordDialog dialog = new ChangePasswordDialog(WalletManagementActivity.this, new PasswordCallback() {
                    @Override
                    public void sure(String oldPassword, String newPassword) {
                        if (userBean.getWallet_shapwd().equals(PasswordToKeyUtils.shaCheck(oldPassword))) {
                            if (userBean != null) {
                                String randomString = EncryptUtil.getRandomString(32);
                                userBean.setWallet_shapwd(PasswordToKeyUtils.shaEncrypt(randomString + newPassword));
                                ArrayList<AccountInfoBean> mAccountInfoBeanList1 = JsonUtil.parseJsonToArrayList(userBean.getAccount_info(), AccountInfoBean.class);
                                for (int i = 0; i < mAccountInfoBeanList1.size(); i++) {
                                    try {
                                        String owner_key = EncryptUtil.getDecryptString(mAccountInfoBeanList1.get(i).getAccount_owner_private_key(), oldPassword);
                                        mAccountInfoBeanList1.get(i).setAccount_owner_private_key(EncryptUtil.getEncryptString(owner_key, newPassword));
                                        String active_key = EncryptUtil.getDecryptString(mAccountInfoBeanList1.get(i).getAccount_active_private_key(), oldPassword);
                                        mAccountInfoBeanList1.get(i).setAccount_active_private_key(EncryptUtil.getEncryptString(active_key, newPassword));
                                    } catch (NoSuchAlgorithmException e) {
                                        e.printStackTrace();
                                    } catch (InvalidKeySpecException e) {
                                        e.printStackTrace();
                                    }
                                }
                                userBean.setAccount_info(new Gson().toJson(mAccountInfoBeanList1));

                                MyApplication.getDaoInstant().getUserBeanDao().update(userBean);
                                MyApplication.getInstance().getUserBean().setWallet_shapwd(PasswordToKeyUtils.shaEncrypt(randomString + newPassword));
                                MyApplication.getInstance().getUserBean().setAccount_info(new Gson().toJson(mAccountInfoBeanList1));
                                toast(getString(R.string.change_password_success));

                            }
                        } else {
                            toast(getResources().getString(R.string.password_error));
                        }
                    }
                });
                dialog.setCancelable(true);
                dialog.show();
            }
        });
        main_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<AccountInfoBean> mlist = JsonUtil.parseJsonToArrayList(MyApplication.getInstance().getUserBean().getAccount_info(), AccountInfoBean.class);
                for (int i = 0; i < mlist.size(); i++) {
                    if (mlist.get(i).getIs_main_account().equals("1")) {
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("bean", mlist.get(i));
                        ActivityUtils.next(WalletManagementActivity.this, AccountDetailsActivity.class, bundle);
                    }
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        mAccountInfoBeanList.clear();
        initData();
        initEvent();
    }



    @Override
    public void setPolicyAccountHttp(BaseBean baseBean, int position) {
        if (baseBean.getCode().equals("0")) {

            if (userBean != null) {
                ArrayList<AccountInfoBean> accountInfoBeanArrayList = new ArrayList<>();
                if (MyApplication.getInstance().getUserBean().getAccount_info() != null) {
                    accountInfoBeanArrayList = JsonUtil.parseJsonToArrayList(MyApplication.getInstance().getUserBean().getAccount_info(), AccountInfoBean.class);
                    for (int i = 0; i < accountInfoBeanArrayList.size(); i++) {
                        if (accountInfoBeanArrayList.get(i).getAccount_name().equals(mAccountInfoBeanList.get(position).getAccount_name())) {
                            accountInfoBeanArrayList.get(i).setIs_privacy_policy((mAccountInfoBeanList.get(position).getIs_privacy_policy().equals("0") ? "1" : "0"));
                        }
                    }
                    userBean.setAccount_info(new Gson().toJson(accountInfoBeanArrayList));
                }
                MyApplication.getDaoInstant().getUserBeanDao().update(userBean);
                MyApplication.getInstance().getUserBean().setAccount_info(new Gson().toJson(accountInfoBeanArrayList));
                mAccountInfoBeanList.get(position).setIs_privacy_policy((mAccountInfoBeanList.get(position).getIs_privacy_policy().equals("0") ? "1" : "0"));
                mAccountInfoBeanList.clear();
                initData();
                initEvent();
            }
        }
    }

    @Override
    public void getDataHttpFail(String msg) {
        toast(msg);
    }
}
