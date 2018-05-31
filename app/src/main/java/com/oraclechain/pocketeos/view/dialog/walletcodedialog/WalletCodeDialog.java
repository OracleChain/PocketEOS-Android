package com.oraclechain.pocketeos.view.dialog.walletcodedialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.zxing.client.result.ParsedResultType;
import com.mylhyl.zxing.scanner.encode.QREncode;
import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.utils.ViewToImageUtils;

/**
 * Created by pocketEos on 2017/12/5.
 * 钱包二维码弹窗
 */

public class WalletCodeDialog extends Dialog implements View.OnClickListener {

    WalletCodeCallBack callback;
    private ImageView walletCode;
    private Context context;
    private TextView go_weixin_friend;
    private TextView go_weixin_circle;
    private TextView go_qq_friend;
    private TextView go_qzone;
    private LinearLayout ll;

    public WalletCodeDialog(Context context, WalletCodeCallBack callback) {
        super(context, R.style.CustomDialog);
        this.callback = callback;
        this.context = context;
        setCustomDialog();
    }

    private void setCustomDialog() {

        View mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_wallet_code, null);
        walletCode = mView.findViewById(R.id.wallet_code);
        go_weixin_friend = mView.findViewById(R.id.go_weixin_friend);
        go_weixin_circle = mView.findViewById(R.id.go_weixin_circle);
        go_qq_friend = mView.findViewById(R.id.go_qq_friend);
        go_qzone = mView.findViewById(R.id.go_qzone);
        ll = mView.findViewById(R.id.ll);


        go_weixin_friend.setOnClickListener(this);
        go_weixin_circle.setOnClickListener(this);
        go_qq_friend.setOnClickListener(this);
        go_qzone.setOnClickListener(this);
        super.setContentView(mView);
        WindowManager windowManager = getWindow().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.width = (int) (display.getWidth() * 0.78); //设置宽度
        this.getWindow().setAttributes(lp);
    }


    public WalletCodeDialog setContent(String walletcode) {
        //生成的账号二维码包含的信息
        Resources res = context.getResources();
        Bitmap bmp = BitmapFactory.decodeResource(res, R.mipmap.ic_launcher_round);
        Bitmap bitmap = new QREncode.Builder(context)
                .setColor(context.getResources().getColor(R.color.title_color))//二维码颜色
                .setParsedResultType(ParsedResultType.TEXT)//默认是TEXT类型
                .setContents(walletcode)//二维码内容
                .setLogoBitmap(bmp)
                .setMargin(1)
                .setSize(1000)
                .build().encodeAsBitmap();
        if (bitmap != null) {
            walletCode.setImageBitmap(bitmap);
        }
        return this;
    }


    @Override
    public void onClick(View v) {
        Bitmap bitmap = ViewToImageUtils.loadBitmapFromView(ll);
        switch (v.getId()) {
            case R.id.go_weixin_friend:
                callback.goWeixinFriend(bitmap);
                break;
            case R.id.go_weixin_circle:
                callback.goWeixinCircle(bitmap);
                break;
            case R.id.go_qq_friend:
                callback.goQQFriend(bitmap);
                break;
            case R.id.go_qzone:
                callback.goQzone(bitmap);
                break;
        }
    }

}
