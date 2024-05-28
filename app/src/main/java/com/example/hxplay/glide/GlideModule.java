package com.example.hxplay.glide;

import android.content.Context;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;
import com.example.hxplay.R;

@com.bumptech.glide.annotation.GlideModule
public class GlideModule extends AppGlideModule {

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {

    // 设置默认的加载选项，包括缓存策略为 DiskCacheStrategy.RESOURCE
        // 设置默认的加载选项，如占位符、错误占位符等
        builder.setDefaultRequestOptions(new RequestOptions()
                        .placeholder(R.drawable.loading_anim)
                        .error(R.drawable.error)
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE))
                .setMemoryCache(new LruResourceCache(50 * 1024 * 1024));  // 设置内存缓存大小

        // 设置磁盘缓存大小和位置
//        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, 50 * 1024 * 1024)); // 50MB
    }

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        super.registerComponents(context, glide, registry);
    }

//    @Override
//    public boolean isManifestParsingEnabled() {
//        return false;
//    }

}

