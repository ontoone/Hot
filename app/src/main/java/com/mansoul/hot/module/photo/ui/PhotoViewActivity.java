package com.mansoul.hot.module.photo.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mansoul.hot.R;
import com.mansoul.hot.app.MyApplication;
import com.mansoul.hot.http.LoadImage;
import com.mansoul.hot.util.BitmapUtil;
import com.mansoul.hot.util.ToastUtil;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.majiajie.swipeback.SwipeBackActivity;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import uk.co.senab.photoview.PhotoView;

public class PhotoViewActivity extends SwipeBackActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.photo_view)
    PhotoView mPhotoView;
    private String mImageName;
    private static final String PATH = Environment.getExternalStorageDirectory().getAbsolutePath()
            + File.separator + "HotImage";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
        ButterKnife.bind(this);

        init();
        initData();
    }

    private void init() {
        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_girl, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_share) {

            return true;
        } else if (id == R.id.action_save) {
            saveGirl();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initData() {
        Intent intent = getIntent();
        String mImageUrl = intent.getStringExtra("imageUrl");
        mImageName = mImageUrl.substring(mImageUrl.lastIndexOf("/") + 1, mImageUrl.length());
        LoadImage.getInstance().display(this, mImageUrl, mPhotoView);
    }

    private void saveGirl() {
        Drawable drawable = mPhotoView.getDrawable();
        Observable.just(drawable)
                .map(new Func1<Drawable, Bitmap>() {
                    @Override
                    public Bitmap call(Drawable drawable) {
                        return BitmapUtil.drawableToBitmap(drawable);
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Bitmap>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        showToast("保存失败");
                    }

                    @Override
                    public void onNext(Bitmap bitmap) {
                        boolean b = BitmapUtil.saveBitmap(bitmap, PATH, mImageName);
                        if (b) {
                            showToast("保存成功至" + PATH);
                        } else {
                            showToast("保存失败");
                        }
                    }
                });
    }

    private void showToast(final String msg) {
        MyApplication.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                ToastUtil.showShort(getApplicationContext(), msg);
            }
        });
    }

}
