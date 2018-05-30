package com.oraclechain.pocketeos.view.dialog.makecollectiondialog;

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
 * 收款二维码弹窗
 */

public class MakeCollectionsDialog extends Dialog implements View.OnClickListener {

    MakeCollectionCallBack callback;
    private ImageView mImageView;
    private TextView iconNUmber;
    private TextView iconType;
    private Context context;
    private TextView go_weixin_friend;
    private TextView go_weixin_circle;
    private TextView go_qq_friend;
    private TextView go_qzone;
    private LinearLayout share_view;

    public MakeCollectionsDialog(Context context, MakeCollectionCallBack callback) {
        super(context, R.style.CustomDialog);
        this.callback = callback;
        this.context = context;
        setCustomDialog();
    }

    private void setCustomDialog() {

        View mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_make_collections, null);
        mImageView = (ImageView) mView.findViewById(R.id.make_collections_code);

        go_weixin_friend = mView.findViewById(R.id.go_weixin_friend);
        go_weixin_circle = mView.findViewById(R.id.go_weixin_circle);
        go_qq_friend = mView.findViewById(R.id.go_qq_friend);
        go_qzone = mView.findViewById(R.id.go_qzone);
        iconNUmber = (TextView) mView.findViewById(R.id.icon_number);
        iconType = (TextView) mView.findViewById(R.id.icon_type);
        share_view = (LinearLayout) mView.findViewById(R.id.share_view);
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


    public MakeCollectionsDialog setContent(String content, String icon_number, String icon_type) {
        iconType.setText("/ " + icon_type);
        iconNUmber.setText(icon_number);
        Resources res = context.getResources();
        Bitmap bmp = BitmapFactory.decodeResource(res, R.mipmap.ic_launcher_round);
        //生成的账号二维码包含的信息
        Bitmap bitmap = new QREncode.Builder(context)
                .setColor(context.getResources().getColor(R.color.title_color))//二维码颜色
                .setParsedResultType(ParsedResultType.TEXT)//默认是TEXT类型
                .setContents(content)//二维码内容
                .setLogoBitmap(bmp)
                .setSize(1000)
                .build().encodeAsBitmap();
        if (bitmap != null) {
            mImageView.setImageBitmap(bitmap);
        }
        return this;
    }


    @Override
    public void onClick(View v) {
        Bitmap bitmap = ViewToImageUtils.loadBitmapFromView(share_view);
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
