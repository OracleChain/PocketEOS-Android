package com.oraclechain.pocketeos.view.dialog.backupnumberdialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.oraclechain.pocketeos.R;

/**
 * Created by pocketEos on 2017/12/5.
 * 钱包备份弹窗
 */

public class BackUpNumberDialog extends Dialog implements View.OnClickListener {

    BackUpNumberCallBack callback;
    private TextView private_key;
    private TextView goWeixin;
    private TextView goQQ;
    private TextView goemail;
    private TextView user_name;
    private TextView path_backup;
    private Context context;

    public BackUpNumberDialog(Context context, BackUpNumberCallBack callback) {
        super(context, R.style.CustomDialog);
        this.callback = callback;
        this.context = context;
        setCustomDialog();
    }

    private void setCustomDialog() {

        View mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_backup_number, null);
        private_key = (TextView) mView.findViewById(R.id.private_key);
        path_backup = (TextView) mView.findViewById(R.id.path_backup);

        goWeixin = (TextView) mView.findViewById(R.id.go_weixin);
        goQQ = (TextView) mView.findViewById(R.id.go_qq);
        goemail = (TextView) mView.findViewById(R.id.go_email);
        user_name = (TextView) mView.findViewById(R.id.user_name);
        goWeixin.setOnClickListener(this);
        goQQ.setOnClickListener(this);
        goemail.setOnClickListener(this);
        super.setContentView(mView);
        WindowManager windowManager = getWindow().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.width = (int) (display.getWidth() * 0.78); //设置宽度
        this.getWindow().setAttributes(lp);
    }


    public BackUpNumberDialog setContent(String username, String strPath) {
        user_name.setText(username);
        path_backup.setText("文件地址:" + strPath);
        return this;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.go_weixin:
                this.cancel();
                break;
            case R.id.go_qq:
                this.cancel();
                break;
            case R.id.go_email:
                this.cancel();
                break;
        }
    }

}
