package com.oraclechain.pocketeos.view.dialog.gophotodialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.oraclechain.pocketeos.R;

/**
 * Created by pocketEos on 2017/12/11.
 * 更换头像弹窗
 */

public class GoPhotoDialog extends Dialog implements View.OnClickListener {

    GoPhotoCallBack callback;
    private TextView goSystemPhoto;
    private TextView goTakePhoto;
    private LinearLayout ll;
    private Button cancle;
    private Context context;

    public GoPhotoDialog(Context context, GoPhotoCallBack callback) {
        super(context, R.style.PhotoDialog);
        this.callback = callback;
        this.context = context;
        setCustomDialog();
    }

    private void setCustomDialog() {

        View mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_go_photo, null);

        goSystemPhoto = (TextView) mView.findViewById(R.id.go_system_photo);
        ll = (LinearLayout) mView.findViewById(R.id.ll);
        goTakePhoto = (TextView) mView.findViewById(R.id.go_take_photo);
        cancle = (Button) mView.findViewById(R.id.cancel);
        goSystemPhoto.setOnClickListener(this);
        goTakePhoto.setOnClickListener(this);
        cancle.setOnClickListener(this);
        ll.setOnClickListener(this);
        super.setContentView(mView);

        WindowManager windowManager = getWindow().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.width = (int) (display.getWidth()); //设置宽度
        lp.height = (int) (display.getHeight() - 20); //设置宽度
        this.getWindow().setAttributes(lp);
    }


    public GoPhotoDialog setContent() {
        return this;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.go_system_photo:
                callback.goSystemPhoto();
                this.cancel();
                break;
            case R.id.go_take_photo:
                callback.goTakePhoto();
                this.cancel();
                break;
            case R.id.cancel:
                this.cancel();
                break;
            case R.id.ll:
                this.cancel();
                break;
        }
    }

}
