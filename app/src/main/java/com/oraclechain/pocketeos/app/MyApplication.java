package com.oraclechain.pocketeos.app;

import android.content.Context;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.support.multidex.MultiDexApplication;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.lzy.okgo.https.HttpsUtils;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.HttpHeaders;
import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.bean.UserBean;
import com.oraclechain.pocketeos.gen.DaoMaster;
import com.oraclechain.pocketeos.gen.DaoSession;
import com.oraclechain.pocketeos.utils.LocalManageUtil;
import com.oraclechain.pocketeos.utils.Utils;
import com.tencent.tauth.Tencent;
import com.zhy.autolayout.config.AutoLayoutConifg;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

/**
 * Created by pocketEos on 2017/11/23.
 */

public class MyApplication extends MultiDexApplication {
    private static MyApplication mInstance;
    private static DaoSession daoSession;
    Tencent mTencent;
    private UserBean mUserBean = new UserBean();

    public static MyApplication getInstance() {
        return mInstance;
    }

    public static DaoSession getDaoInstant() {
        return daoSession;
    }

    public UserBean getUserBean() {
        return mUserBean;
    }

    public void setUserBean(UserBean userBean) {
        mUserBean = userBean;
    }

    public Tencent getTencent() {
        return mTencent;
    }

    public void setTencent(Tencent tencent) {
        mTencent = tencent;
    }

    @Override
    protected void attachBaseContext(Context base) {
        //保存系统选择语言
        LocalManageUtil.saveSystemCurrentLanguage(base);
        super.attachBaseContext(LocalManageUtil.setLocal(base));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //保存系统选择语言
        LocalManageUtil.onConfigurationChanged(getApplicationContext());
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;

        Utils.init(getApplicationContext());


        try {
            initOkGo();
        } catch (IOException e) {
            e.printStackTrace();
        }


        AutoLayoutConifg.getInstance().useDeviceSize();

        //配置数据库
        setupDatabase();

        setTencent(Tencent.createInstance("1106779320", getApplicationContext()));//QQ第三方登录1106779320

        LocalManageUtil.setApplicationLanguage( getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (mInstance != null) {
            mInstance = null;
        }
    }

    public void initOkGo() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        if (new SPCookieStore(this).getAllCookie().size() != 0) {
            headers.put("cookie", String.valueOf(new SPCookieStore(this).getAllCookie().get(0)));
        }
        headers.put("version", "3.0");
        headers.put("uid", MyApplication.getInstance().getUserBean().getWallet_uid());

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //log相关
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkHttp");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setColorLevel(Level.INFO);                               //log颜色级别，决定了log在控制台显示的颜色
        builder.addInterceptor(loggingInterceptor);                                 //添加OkGo默认debug日志
        //超时时间设置
        builder.readTimeout(10000, TimeUnit.MILLISECONDS);      //全局的读取超时时间
        builder.writeTimeout(10000, TimeUnit.MILLISECONDS);     //全局的写入超时时间
        builder.connectTimeout(10000, TimeUnit.MILLISECONDS);   //全局的连接超时时间
        builder.cookieJar(new CookieJarImpl(new SPCookieStore(this)));            //使用sp保持cookie，如果cookie不过期，则一直有效


        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(getAssets().open("server.cer"));
        builder.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
//        //配置https的域名匹配规则，使用不当会导致https握手失败
        builder.hostnameVerifier(HttpsUtils.UnSafeHostnameVerifier);

        // 其他统一的配置
        OkGo.getInstance().init(this)                           //必须调用初始化
                .setOkHttpClient(builder.build())               //必须设置OkHttpClient
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(3)          //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
                .addCommonHeaders(headers) ;              //全局公共头
//                .addCommonParams(httpParams);                       //全局公共参数

    }

    /**
     * 配置数据库
     */
    private void setupDatabase() {
        //创建数据库
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "pocketEos.db", null);
        //获取可写数据库
        SQLiteDatabase db = helper.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        //获取Dao对象管理者
        daoSession = daoMaster.newSession();

    }


    public void showImage(String url, final ImageView image) {
        Glide.with(getApplicationContext()).load(url).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher)
                .into(new SimpleTarget<GlideDrawable>() { // 加上这段代码 可以解决
                    @Override
                    public void onResourceReady(GlideDrawable resource,
                                                GlideAnimation<? super GlideDrawable> glideAnimation) {
                        image.setImageDrawable(resource); //显示图片
                    }
                });
    }

    public void showCirImage(String url, final ImageView image) {
        if (url == null || url.isEmpty() || "".equals(url)) {
            image.setImageResource(R.mipmap.defeat_person_img);
            return;
        }
        Glide.with(getApplicationContext())
                .load(url)
                .error(R.mipmap.ic_launcher_round)
                .into(new SimpleTarget<GlideDrawable>() { // 加上这段代码 可以解决
                    @Override
                    public void onResourceReady(GlideDrawable resource,
                                                GlideAnimation<? super GlideDrawable> glideAnimation) {
                        image.setImageDrawable(resource); //显示图片
                    }
                });
    }


}

