package com.oraclechain.pocketeos.view.dialog.changepassworddialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.utils.ToastUtils;
import com.oraclechain.pocketeos.view.ClearEditText;


/**
 * 修改密码
 */

public class ChangePasswordDialog extends Dialog implements View.OnClickListener {

    PasswordCallback callback;
    private ClearEditText mClearEditText;
    private ClearEditText mClearEditText1;
    private ClearEditText mClearEditText2;
    private TextView sureBtn;
    private TextView cancleBtn;
    private Context context;

    public ChangePasswordDialog(Context context, PasswordCallback callback) {
        super(context, R.style.CustomDialog);
        this.callback = callback;
        this.context = context;
        setCustomDialog();
    }

    private void setCustomDialog() {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_change_password, null);
        sureBtn = (TextView) mView.findViewById(R.id.dialog_confirm_sure);
        cancleBtn = (TextView) mView.findViewById(R.id.dialog_confirm_cancle);
        mClearEditText = (ClearEditText) mView.findViewById(R.id.dialog_old_password);
        mClearEditText1 = (ClearEditText) mView.findViewById(R.id.dialog_old_two_password);
        mClearEditText2 = (ClearEditText) mView.findViewById(R.id.dialog_new_password);
        sureBtn.setOnClickListener(this);
        cancleBtn.setOnClickListener(this);
        super.setContentView(mView);
        WindowManager windowManager = getWindow().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.width = (int) (display.getWidth() * 0.78); //设置宽度
        this.getWindow().setAttributes(lp);
    }


    public ChangePasswordDialog setContent() {
        return this;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_confirm_cancle:
                this.cancel();
                break;

            case R.id.dialog_confirm_sure:
                if (mClearEditText.getText().toString().trim().length() != 0 && mClearEditText1.getText().toString().trim().length() != 0
                        && mClearEditText2.getText().toString().trim().length() != 0 && mClearEditText1.getText().toString().equals(mClearEditText2.getText().toString()) &&
                        !mClearEditText.getText().toString().equals(mClearEditText2.getText().toString())) {
                    InputMethodManager inputMgr = (InputMethodManager) context
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMgr.toggleSoftInput(InputMethodManager.HIDE_NOT_ALWAYS, 0);
                    callback.sure(mClearEditText.getText().toString().trim(), mClearEditText2.getText().toString().trim());
                    this.cancel();
                } else if (!mClearEditText1.getText().toString().equals(mClearEditText2.getText().toString())) {
                    ToastUtils.showShortToast(R.string.two_pwd);
                } else if (mClearEditText.getText().toString().equals(mClearEditText2.getText().toString())) {
                    ToastUtils.showShortToast(R.string.old_new_diffent);
                } else {
                    ToastUtils.showShortToast(R.string.input_all_pwd);
                }
                break;
        }
    }
}
