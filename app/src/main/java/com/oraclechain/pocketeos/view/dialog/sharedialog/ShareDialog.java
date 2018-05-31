package com.oraclechain.pocketeos.view.dialog.sharedialog;

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
 * Created by pocketEos on 2017/12/5.
 * 分享弹窗
 */

public class ShareDialog extends Dialog implements View.OnClickListener {

    ShareCallBack callback;

    private Context context;

    private Button cancel;
    private LinearLayout go_weixin_friend;
    private LinearLayout go_weixin_circle;
    private LinearLayout go_qq_friend;
    private LinearLayout go_qzone;
    private TextView title;

    public ShareDialog(Context context, ShareCallBack callback) {
        super(context, R.style.PhotoDialog);
        this.callback = callback;
        this.context = context;
        setCustomDialog();
    }

    private void setCustomDialog() {

        View mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_share, null);

        cancel = mView.findViewById(R.id.cancel);
        go_weixin_friend = mView.findViewById(R.id.go_weixin_friend);
        go_weixin_circle = mView.findViewById(R.id.go_weixin_circle);
        go_qq_friend = mView.findViewById(R.id.go_qq_friend);
        go_qzone = mView.findViewById(R.id.go_qzone);
        title = mView.findViewById(R.id.title);


        cancel.setOnClickListener(this);
        go_weixin_friend.setOnClickListener(this);
        go_weixin_circle.setOnClickListener(this);
        go_qq_friend.setOnClickListener(this);
        go_qzone.setOnClickListener(this);
        super.setContentView(mView);

        WindowManager windowManager = getWindow().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.width = (int) (display.getWidth()); //设置宽度
        lp.height = (int) (display.getHeight()); //设置宽度
        this.getWindow().setAttributes(lp);
    }


    public ShareDialog setContent(String str) {
        title.setText(str);
        return this;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.go_weixin_friend:
                callback.goWeixinFriend();
                this.cancel();
                break;
            case R.id.go_weixin_circle:
                callback.goWeixinCircle();
                this.cancel();
                break;
            case R.id.go_qq_friend:
                callback.goQQFriend();
                this.cancel();
                break;
            case R.id.go_qzone:
                callback.goQzone();
                this.cancel();
                break;
            case R.id.ll:
                this.cancel();

            case R.id.cancel:
                this.cancel();
                break;
        }
    }

}
