package com.mansoul.hot.http;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mansoul.hot.R;
import com.mansoul.hot.base.BaseFragment;

/**
 * Created by Mansoul on 16/8/11.
 */
public class LoadImage {
    private LoadImage() {
    }

    private static class Holder {
        private static final LoadImage INSTANCE = new LoadImage();
    }

    public static LoadImage getInstance() {
        return Holder.INSTANCE;
    }

    public void display(Context context, String url, ImageView target) {
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .fitCenter()
                .into(target);
    }

    public void display(Activity activity, String url, ImageView target) {
        Glide.with(activity)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .fitCenter()
                .into(target);
    }
    public void display(Fragment fragment, String url, ImageView target) {
        Glide.with(fragment)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .fitCenter()
                .into(target);
    }
}
