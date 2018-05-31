package com.oraclechain.pocketeos.view.dialog.sharecoindetailsdialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.bean.AccountWithCoinBean;
import com.oraclechain.pocketeos.utils.StringUtils;

/**
 * Created by pocketEos on 2017/12/5.
 * 分享token弹窗
 */

public class ShareCoinDetailsDialog extends Dialog implements View.OnClickListener {

    ShareCoinDetailsCallBack callback;

    private Context context;
    private LinearLayout ll;
    private Button cancel;
    private TextView go_weixin_friend;
    private TextView go_weixin_circle;
    private TextView go_qq_friend;
    private TextView go_qzone;
    private TextView coin_one_price;
    private TextView coin_ups_and_downs;
    private TextView coin_maket_total_price;

    public ShareCoinDetailsDialog(Context context, ShareCoinDetailsCallBack callback) {
        super(context, R.style.PhotoDialog);
        this.callback = callback;
        this.context = context;
        setCustomDialog();
    }

    private void setCustomDialog() {

        View mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_share_coin_details, null);
        ll = mView.findViewById(R.id.ll);
        cancel = mView.findViewById(R.id.cancel);
        go_weixin_friend = mView.findViewById(R.id.go_weixin_friend);
        go_weixin_circle = mView.findViewById(R.id.go_weixin_circle);
        go_qq_friend = mView.findViewById(R.id.go_qq_friend);
        go_qzone = mView.findViewById(R.id.go_qzone);
        coin_one_price = mView.findViewById(R.id.coin_one_price);
        coin_ups_and_downs = mView.findViewById(R.id.coin_ups_and_downs);
        coin_maket_total_price = mView.findViewById(R.id.coin_maket_total_price);


        ll.setOnClickListener(this);
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


    public ShareCoinDetailsDialog setContent(Bundle bundle) {
        AccountWithCoinBean accountWithCoinBean = bundle.getParcelable("coin");
        coin_ups_and_downs.setText(accountWithCoinBean.getCoinUpsAndDowns());
        if (accountWithCoinBean.getCoinName().equals("OCT")) {
            coin_one_price.setText("￥" + StringUtils.addComma(accountWithCoinBean.getOct_price_cny()));
            coin_maket_total_price.setText("￥" + StringUtils.addComma(accountWithCoinBean.getOct_market_cap_cny()));
        } else {
            coin_one_price.setText("￥" + StringUtils.addComma(accountWithCoinBean.getEos_price_cny()));
            coin_maket_total_price.setText("￥" + StringUtils.addComma(accountWithCoinBean.getEos_market_cap_cny()));
        }
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
