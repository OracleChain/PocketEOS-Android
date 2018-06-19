package com.oraclechain.pocketeos.view.dialog.advertisingdialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.view.OvalImageView;


/**
 * 首页广告位弹窗
 */

public class AdvertisingDialog extends Dialog implements View.OnClickListener {

    AdvertisingCallback mAdvertisingCallback;
    private Button goLookBtn;
    private OvalImageView advertisingImg;
    private ImageView finishImg;
    private Context context;

    public AdvertisingDialog(Context context, AdvertisingCallback advertisingCallback) {
        super(context, R.style.CustomDialog);
        this.mAdvertisingCallback = advertisingCallback;
        this.context = context;
        setCustomDialog();
    }

    private void setCustomDialog() {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_advertising, null);
        goLookBtn = (Button) mView.findViewById(R.id.go_look);
        advertisingImg = (OvalImageView) mView.findViewById(R.id.advertising_img);
        finishImg = (ImageView) mView.findViewById(R.id.finish);
        goLookBtn.setOnClickListener(this);
        finishImg.setOnClickListener(this);
        super.setContentView(mView);
        WindowManager windowManager = getWindow().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.width = (int) (display.getWidth() * 0.78); //设置宽度
        lp.height = (int) (display.getHeight() * 1);
        this.getWindow().setAttributes(lp);
    }

    /**
     * 设置广告位图片
     *
     * @param url
     * @return
     */
    public AdvertisingDialog setImg(String url) {
//        MyApplication.getInstance().showImage(url,advertisingImg);
        advertisingImg.setImageResource(R.mipmap.go_vote_node_img);
        return this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.go_look:
                mAdvertisingCallback.callback();
                this.cancel();
                break;
            case R.id.finish:
                this.cancel();
                break;
        }
    }

}
