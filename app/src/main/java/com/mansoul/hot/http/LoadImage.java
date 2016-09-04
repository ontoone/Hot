package com.mansoul.hot.http;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;
import com.mansoul.hot.R;
import com.mansoul.hot.base.BaseFragment;
import com.mansoul.hot.widget.ScaleImageView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

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

    public void displayWithWH(final Context context, final String url, final ScaleImageView target) {
        Observable.just(url)
                .subscribeOn(Schedulers.io())
                .map(new Func1<String, Bitmap>() {
                    @Override
                    public Bitmap call(String s) {
                        return LoadImage.load(context, url);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) {
                        int width = bitmap.getWidth();
                        int height = bitmap.getHeight();
                        target.setInitSize(width,height);
                        Glide.with(context)
                                .load(url)
                                .override(width, height)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(target);
                    }
                });

    }

    public static Bitmap load(Context context, String url) {
        try {
            return Glide.with(context)
                    .load(url)
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
