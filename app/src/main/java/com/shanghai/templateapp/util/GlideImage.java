package com.shanghai.templateapp.util;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * 首页轮播图要用到
 */
public class GlideImage extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context).load(path).into(imageView);
        Uri uri = Uri.parse((String) path);
        imageView.setImageURI(uri);

    }
}
