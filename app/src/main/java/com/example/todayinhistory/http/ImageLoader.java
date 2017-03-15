package com.example.todayinhistory.http;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.todayinhistory.R;

/**
 * Created by win7 on 2017/3/15.
 * 描述:
 * 作者:小智 win7
 */

public class ImageLoader {
    public static void load(Context context, String url, ImageView imageView) {
        if (!TextUtils.isEmpty(url)) {
            Glide.with(context)
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);

        }
    }

    public static void load(Context context, String url, SimpleTarget target) {
        if (!TextUtils.isEmpty(url)) {
            Glide.with(context)
                    .load(url)
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(target);

        }
    }

    public static void load(Context context, ImageView imageView) {
        Glide.with(context)
                .load(R.mipmap.default_img)
                .placeholder(R.mipmap.default_img)
                .into(imageView);
    }

}
