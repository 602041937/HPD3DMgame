package com.hpdxay.hpd3dmgame;

import android.app.Application;
import android.graphics.Bitmap;
import android.os.StrictMode;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.internal.Supplier;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.SimpleProgressiveJpegConfig;
import com.hpdxay.hpd3dmgame.utils.DBUtil;

/**
 * Created by hpd on 2016/1/27.
 */
public class MyApplication extends Application {

    public static RequestQueue mRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        DBUtil.initialize(getApplicationContext());
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setBitmapMemoryCacheParamsSupplier(new Supplier<MemoryCacheParams>() {
                    @Override
                    public MemoryCacheParams get() {
//设置缓存的参数1.最多的图片的大小 2.图片的数量 

                        return new MemoryCacheParams(20 << 20,
                                100,
                                Integer.MAX_VALUE,
                                Integer.MAX_VALUE,
                                Integer.MAX_VALUE
                        );
                    }
                })
//最大缓存大小，缓存的路径，和缓存的文件名， 和图片的配置565可以减少大小，默认大小是4个8

                .setMainDiskCacheConfig(DiskCacheConfig.newBuilder(getApplicationContext())
                        .setMaxCacheSize(50 << 20)
                        .setBaseDirectoryPath(getCacheDir())
                        .setBaseDirectoryName("fresco")
                        .build())
                .setBitmapsConfig(Bitmap.Config.RGB_565)
                .setProgressiveJpegConfig(new SimpleProgressiveJpegConfig())
                .build();
        Fresco.initialize(this, config);


        //
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());

//        //开启严格检查模式
//        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
//                        .detectAll()
//                        .penaltyLog()
//                        .penaltyDeath()
//                        .build()
//        );
//
//        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
//                        .detectAll()
//                        .penaltyLog()
//                        .penaltyDeath()
//                        .build()
//        );
    }

    public static RequestQueue getHttpQueues() {
        return mRequestQueue;
    }
}
