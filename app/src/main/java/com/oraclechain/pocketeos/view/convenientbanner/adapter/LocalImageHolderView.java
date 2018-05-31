package com.oraclechain.pocketeos.view.convenientbanner.adapter;

/**
 * Created by pocketEos on 2018/1/3.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.view.convenientbanner.holder.Holder;

/**
 * 设置加载图片类型
 */
public class LocalImageHolderView implements Holder<String> {
    private View view;

    @Override
    public View createView(Context context) {
        view = LayoutInflater.from(context).inflate(R.layout.banner_item, null, false);
        return view;
    }

    @Override
    public void UpdateUI(Context context, int i, String data) {
        Glide.with(context).load(data).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher)
                .skipMemoryCache(true).thumbnail(0.1f)
                .into(new SimpleTarget<GlideDrawable>() { // 加上这段代码 可以解决
                    @Override
                    public void onResourceReady(GlideDrawable resource,
                                                GlideAnimation<? super GlideDrawable> glideAnimation) {
                       ImageView imageView =  (ImageView) view.findViewById(R.id.img);
                        imageView.setImageDrawable(resource); //显示图片
                    }
                });

    }
}

