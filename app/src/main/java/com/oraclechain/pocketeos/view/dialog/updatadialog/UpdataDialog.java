package com.oraclechain.pocketeos.view.dialog.updatadialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.oraclechain.pocketeos.R;


/**
 *版本更新弹窗
 */

public class UpdataDialog extends Dialog implements View.OnClickListener {

    UpdataCallback callback;
    private TextView content;
    private TextView sureBtn;
    private TextView cancleBtn;
    private Context context;

    public UpdataDialog(Context context, UpdataCallback callback) {
        super(context, R.style.CustomDialog);
        this.callback = callback;
        this.context = context;
        setCustomDialog();
    }

    private void setCustomDialog() {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_updata_app, null);
        sureBtn = (TextView) mView.findViewById(R.id.dialog_confirm_sure);
        cancleBtn = (TextView) mView.findViewById(R.id.dialog_confirm_cancle);
        content = (TextView) mView.findViewById(R.id.app_details);
        sureBtn.setOnClickListener(this);
        cancleBtn.setOnClickListener(this);
        super.setContentView(mView);
        WindowManager windowManager = getWindow().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.width = (int) (display.getWidth() * 0.78); //设置宽度
        this.getWindow().setAttributes(lp);
    }


    public UpdataDialog setContent(String cont) {
        content.setText(cont);
        return this;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_confirm_cancle:
                this.cancel();
                break;

            case R.id.dialog_confirm_sure:
                callback.goData();
                this.cancel();
                break;
        }
    }

}
