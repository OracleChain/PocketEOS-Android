package com.oraclechain.pocketeos.view.dialog.importprivatekeydialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.view.ScrollText;

/**
 * Created by pocketEos on 2017/12/5.
 * 导出私钥弹窗
 */

public class ImportPrivateKeyDialog extends Dialog implements View.OnClickListener {

    ImportrivateKeyCallBack callback;
    private ScrollText ownerPrivateKey;
    private ScrollText activePrivatePey;
    private TextView goWeixin;
    private TextView goQQ;
    private TextView goCopy;
    private TextView goCode;
    private ImageView privateKeyCode;
    private Context context;

    public ImportPrivateKeyDialog(Context context, ImportrivateKeyCallBack callback) {
        super(context, R.style.CustomDialog);
        this.callback = callback;
        this.context = context;
        setCustomDialog();
    }

    private void setCustomDialog() {

        View mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_import_private_key, null);
        ownerPrivateKey = (ScrollText) mView.findViewById(R.id.owner_private_key);
        activePrivatePey = (ScrollText) mView.findViewById(R.id.active_private_key);

        goWeixin = (TextView) mView.findViewById(R.id.go_weixin);
        goQQ = (TextView) mView.findViewById(R.id.go_qq);
        goCopy = (TextView) mView.findViewById(R.id.go_copy);
        goCode = (TextView) mView.findViewById(R.id.go_code);
        privateKeyCode = (ImageView) mView.findViewById(R.id.private_key_code);
        goWeixin.setOnClickListener(this);
        goQQ.setOnClickListener(this);
        goCopy.setOnClickListener(this);
        goCode.setOnClickListener(this);
        super.setContentView(mView);
        WindowManager windowManager = getWindow().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.width = (int) (display.getWidth() * 0.78); //设置宽度
        this.getWindow().setAttributes(lp);
    }


    public ImportPrivateKeyDialog setContent(String owner_private_key, String active_private_key) {
        ownerPrivateKey.setText("OWNKEY:" + owner_private_key);
        activePrivatePey.setText("ACTIVEKEY:" + active_private_key);
        return this;
    }

    public ImportPrivateKeyDialog setCode(Bitmap bitmaap) {
        privateKeyCode.setImageBitmap(bitmaap);
        if (privateKeyCode.getVisibility() == View.VISIBLE) {
            privateKeyCode.setVisibility(View.GONE);
            ownerPrivateKey.setVisibility(View.VISIBLE);
            activePrivatePey.setVisibility(View.VISIBLE);
            privateKeyCode.setImageBitmap(bitmaap);
            Drawable drawable = context.getResources().getDrawable(R.mipmap.make_scode);
            // 这一步必须要做,否则不会显示.
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            goCode.setCompoundDrawables(null, drawable, null, null);
            goCode.setText(R.string.look_code);
        } else {
            privateKeyCode.setVisibility(View.VISIBLE);
            ownerPrivateKey.setVisibility(View.GONE);
            activePrivatePey.setVisibility(View.GONE);
            Drawable drawable = context.getResources().getDrawable(R.mipmap.show_text);
            // 这一步必须要做,否则不会显示.
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            goCode.setCompoundDrawables(null, drawable, null, null);
            goCode.setText(R.string.look_text);
        }
        return this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.go_weixin:
                callback.goWeixin();
                this.cancel();
                break;
            case R.id.go_qq:
                callback.goQQ();
                this.cancel();
                break;
            case R.id.go_copy:
                callback.copy();
                this.cancel();
                break;
            case R.id.go_code:
                callback.goCode();
                break;
        }
    }

}
