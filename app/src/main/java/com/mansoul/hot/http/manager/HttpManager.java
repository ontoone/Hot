package com.mansoul.hot.http.manager;

import android.support.annotation.NonNull;

import com.mansoul.hot.app.MyApplication;
import com.mansoul.hot.http.service.NetService;
import com.mansoul.hot.util.NetUtil;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mansoul on 16/8/11.
 */
public class HttpManager {
    //设缓存有效期为两天
    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;
    //查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
    private static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    //查询网络的Cache-Control设置，头部Cache-Control设为max-age=0
    //(假如请求了服务器并在a时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回)时则不会使用缓存而请求服务器
    private static final String CACHE_CONTROL_NETWORK = "max-age=0";

    private OkHttpClient mOkHttpClient;

    // 云端响应头拦截器，用来配置缓存策略
    private Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetUtil.isConnected(MyApplication.getInstance())) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
                Logger.e("no network");
            }
            Response originalResponse = chain.proceed(request);

            if (NetUtil.isConnected(MyApplication.getInstance())) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder().header("Cache-Control", cacheControl)
                        .removeHeader("Pragma").build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached," + CACHE_STALE_SEC)
                        .removeHeader("Pragma").build();
            }
        }
    };

    private static class SingleHolder {
        private static final HttpManager INSTANCE = new HttpManager();
    }

    private HttpManager() {
        initOkHttp();
    }


    //单例模式获取网络管理
    public static HttpManager getInstance() {
        return SingleHolder.INSTANCE;
    }


    private void initOkHttp() {
        // OkHttpClient配置是一样的,静态创建一次即可
        // 指定缓存路径,缓存大小30Mb
        Cache cache = new Cache(new File(MyApplication.getInstance().getCacheDir(), "HttpCache"),
                1024 * 1024 * 100);

        mOkHttpClient = new OkHttpClient()
                .newBuilder()
                .cache(cache)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(mRewriteCacheControlInterceptor)
                .retryOnConnectionFailure(true)
                .build();

    }

    /**
     * 根据网络状况获取缓存的策略
     *
     * @return
     */
    @NonNull
    public String getCacheControl() {
        return NetUtil.isConnected(MyApplication.getInstance()) ? CACHE_CONTROL_NETWORK : CACHE_CONTROL_CACHE;
    }

    public NetService getRetrofit(String baseUrl) {

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(NetService.class);

    }


}
