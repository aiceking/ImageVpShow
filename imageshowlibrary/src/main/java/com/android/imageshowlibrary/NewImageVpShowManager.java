package com.android.imageshowlibrary;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.imageshowlibrary.model.GlideApp;
import com.android.imageshowlibrary.model.ImageVpType;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dinuscxj.progressbar.CircleProgressBar;

import java.io.File;

import me.jessyan.progressmanager.ProgressListener;
import me.jessyan.progressmanager.ProgressManager;
import me.jessyan.progressmanager.body.ProgressInfo;
import okhttp3.OkHttpClient;

/**
 * Created by static on 2017/12/9/009.
 */

public class NewImageVpShowManager {
    private static NewImageVpShowManager newImageVpShowManager;
    private String saveFilesName;
    public  OkHttpClient okHttpClient;
    private NewImageVpShowManager(){
        okHttpClient= ProgressManager.getInstance().with(new OkHttpClient.Builder())
                .build();

    }
    public static NewImageVpShowManager getInstance(){
        if (newImageVpShowManager==null){
            synchronized (NewImageVpShowManager.class){
                if (newImageVpShowManager==null){
                    newImageVpShowManager=new NewImageVpShowManager();
                }
            }
        }
        return newImageVpShowManager;
    }
    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }
    public String getSaveFilesName() {
        return saveFilesName;
    }

    public void setSaveFilesName(String saveFilesName) {
        this.saveFilesName = saveFilesName;
    }
    public void showImage(Context context, ImageVpType imageVpType, final String path, ImageView imageView,final CircleProgressBar circleProgressBar){
        if (imageVpType==ImageVpType.Local){
            GlideApp.with(context).asBitmap().diskCacheStrategy(DiskCacheStrategy.NONE).load(new File(path)).into(imageView);
        }else {
            GlideApp.with(context).asBitmap().diskCacheStrategy(DiskCacheStrategy.NONE).load(path).into(imageView);
        }
        ProgressManager.getInstance().addResponseListener(path, new ProgressListener() {
            @Override
            public void onProgress(ProgressInfo progressInfo) {
                if (circleProgressBar.getVisibility()==View.GONE){
                circleProgressBar.setVisibility(View.VISIBLE);}
                Log.v("ProgressInfo="+path,progressInfo.getPercent()+"");
                circleProgressBar.setProgress(progressInfo.getPercent());
                if (progressInfo.isFinish()){
                    circleProgressBar.setVisibility(View.GONE);
                }
            }


            
            @Override
            public void onError(long id, Exception e) {

            }
        });
    }
}
