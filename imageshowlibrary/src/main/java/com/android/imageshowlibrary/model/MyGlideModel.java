package com.android.imageshowlibrary.model;

import android.content.Context;

import com.android.imageshowlibrary.ImageVpShowActivity;
import com.android.imageshowlibrary.NewImageVpShowManager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;

import java.io.InputStream;

/**
 * Created by static on 2017/12/8/008.
 */
@GlideModule
public class MyGlideModel extends AppGlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {

    }

    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {
        //Glide 底层默认使用 HttpConnection 进行网络请求,这里替换为 Okhttp 后才能使用本框架,进行 Glide 的加载进度监听
        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader
                .Factory(NewImageVpShowManager.getInstance().getOkHttpClient()));
    }

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}

