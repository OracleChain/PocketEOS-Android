package com.oraclechain.pocketeos.view.dialog.makecollectiondialog;

import android.graphics.Bitmap;

/**
 * Created by pocketEos on 2017/12/5.
 */

public interface MakeCollectionCallBack {
    void goWeixinFriend(Bitmap bitmap);

    void goWeixinCircle(Bitmap bitmap);

    void goQQFriend(Bitmap bitmap);

    void goQzone(Bitmap bitmap);

}
