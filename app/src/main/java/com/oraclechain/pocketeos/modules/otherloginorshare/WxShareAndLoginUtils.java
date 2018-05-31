package com.oraclechain.pocketeos.modules.otherloginorshare;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.base.Constants;
import com.oraclechain.pocketeos.utils.FilesUtils;
import com.oraclechain.pocketeos.utils.ShowDialog;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by pocketEos on 2018/3/23.
 */

public class WxShareAndLoginUtils {

    public static int WECHAT_FRIEND = 0;  //分享好友
    public static int WECHAT_MOMENT = 1;  //分享朋友圈
    private static IWXAPI iwxapi;

    /**
     * 微信登录
     */
    public static void WxLogin(Context context) {
        if (!judgeCanGo(context)) {
            ShowDialog.dissmiss();
            return;
        }
        SendAuth.Req req = new SendAuth.Req();
        //授权域 获取用户个人信息则填写snsapi_userinfo
        req.scope = "snsapi_userinfo";
        //用于保持请求和回调的状态 可以任意填写
        req.state = "login";
        iwxapi.sendReq(req);
    }

    private static boolean judgeCanGo(Context context) {
        getWXAPI(context);
        if (!iwxapi.isWXAppInstalled()) {
            Toast.makeText(context, R.string.install_weixin, Toast.LENGTH_SHORT).show();
            return false;
        } else if (!iwxapi.isWXAppSupportAPI()) {
            Toast.makeText(context, R.string.updata_weixin, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public static IWXAPI getWXAPI(Context context) {
        if (iwxapi == null) {
            //通过WXAPIFactory创建IWAPI实例
            iwxapi = WXAPIFactory.createWXAPI(context,  Constants.WX_AppID, false);
            //将应用的appid注册到微信
            iwxapi.registerApp( Constants.WX_AppID);
        }
        return iwxapi;
    }

    /**
     * 分享文本至朋友圈
     *
     * @param text  文本内容
     * @param judge 类型选择 好友-WECHAT_FRIEND 朋友圈-WECHAT_MOMENT
     */
    public static void WxTextShare(Context context, String text, int judge) {
        if (!judgeCanGo(context)) {
            return;
        }
        //初始化WXTextObject对象，填写对应分享的文本内容
        WXTextObject textObject = new WXTextObject();
        textObject.text = text;
        //初始化WXMediaMessage消息对象，
        WXMediaMessage message = new WXMediaMessage();
        message.mediaObject = textObject;
        message.description = text;
        //构建一个Req请求对象
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());   //transaction用于标识请求
        req.message = message;
        req.scene = judge;      //分享类型 好友==0 朋友圈==1
        //发送请求
        iwxapi.sendReq(req);
    }

    /**
     * 分享图片
     *
     * @param bitmap 图片bitmap,建议别超过32k
     * @param judge  类型选择 好友-WECHAT_FRIEND 朋友圈-WECHAT_MOMENT
     */
    public static void WxBitmapShare(Context context, Bitmap bitmap, int judge) {
        if (!judgeCanGo(context)) {
            return;
        }
        WXImageObject wxImageObject = new WXImageObject(bitmap);
        WXMediaMessage message = new WXMediaMessage();
        message.mediaObject = wxImageObject;

        Bitmap thunmpBmp = Bitmap.createScaledBitmap(bitmap, 50, 50, true);
        bitmap.recycle();
        message.thumbData = FilesUtils.bmpToByteArray(thunmpBmp, true);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = message;
        req.scene = judge;

        iwxapi.sendReq(req);

    }

    /**
     * 网页分享
     *
     * @param url         地址
     * @param title       标题
     * @param description 描述
     * @param judge       类型选择 好友-WECHAT_FRIEND 朋友圈-WECHAT_MOMENT
     */
    public static void WxUrlShare(Context context, String url, String title, String description, String imgUrl, int judge) {
        if (!judgeCanGo(context)) {
            return;
        }
        //第一版暂为applogo
//        Bitmap bitmap = getBitmap(imgUrl);
        Bitmap bitmap;
        if (title.equals(context.getString(R.string.eos_info_detail))) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.eos);
        } else if (title.equals(context.getString(R.string.oct_info_detail))) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.oct);
        } else {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher_round);
        }
        WXWebpageObject wxWebpageObject = new WXWebpageObject();
        wxWebpageObject.webpageUrl = url;

        WXMediaMessage wxMediaMessage = new WXMediaMessage(wxWebpageObject);
        wxMediaMessage.title = title;
        wxMediaMessage.description = description;
        Bitmap thunmpBmp = Bitmap.createScaledBitmap(bitmap, 50, 50, true);
        bitmap.recycle();
        wxMediaMessage.thumbData = FilesUtils.bmpToByteArray(thunmpBmp, true);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = wxMediaMessage;
        req.scene = judge;

        iwxapi.sendReq(req);
    }

    /**
     * 图片url转bitmap
     */

    public static Bitmap getBitmap(String url) {
        Bitmap bm = null;
        try {
            URL iconUrl = new URL(url);
            URLConnection conn = iconUrl.openConnection();
            HttpURLConnection http = (HttpURLConnection) conn;

            int length = http.getContentLength();

            conn.connect();
            // 获得图像的字符流
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is, length);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();// 关闭流
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bm;
    }
}
