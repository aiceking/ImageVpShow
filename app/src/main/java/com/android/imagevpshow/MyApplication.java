package com.android.imagevpshow;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.android.imageshowlibrary.ImageVpShowManager;
import com.android.imageshowlibrary.model.ImageVpType;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.github.chrisbanes.photoview.PhotoView;
import com.jaywei.mdprogress.CircularProgressBar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by static on 2017/12/11/011.
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        ImageVpShowManager.getInstance().setShowImageListener(new ImageVpShowManager.showImageListener() {
            @Override
            public void showImage(Context context, ImageVpType imageVpType, String path, PhotoView imageView, final CircularProgressBar progressBar) {
                if (imageVpType== ImageVpType.Local){
                    Glide.with(context).load(new File(path)).asBitmap().listener(new RequestListener<File, Bitmap>() {
                        @Override
                        public boolean onException(Exception e, File model, Target<Bitmap> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Bitmap resource, File model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            progressBar.setVisibility(View.GONE);
                            return false;
                        }
                    }).into(imageView);

                }else if (imageVpType== ImageVpType.Net){
                    Glide.with(context).load(path).asBitmap().listener(new RequestListener<String, Bitmap>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            progressBar.setVisibility(View.GONE);
                            return false;
                        }
                    }).into(imageView);
                }
            }
        });
        //配置图片保存方法
        ImageVpShowManager.getInstance().setSaveImageListener(new ImageVpShowManager.saveImageListener() {
            @Override
            public void saveImage(Context context, String url, int position) {
                savePicture(context,"myTestImages","image"+position,url);
            }
        });
    }
    public void savePicture(final Context context,final String imageFilesName,final String imageName, String url){
        Glide.with(context).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                saveImageToSD(context,resource,imageFilesName,imageName);
            }
        });
    }
    public void saveImageToSD(Context context, Bitmap bmp,String imageFilesName,String imageName) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            FileOutputStream fos = null;
            try {
                // 首先保存图片
                String rootFilePath = Environment.getExternalStorageDirectory().getCanonicalPath();
                File file = new File(rootFilePath);
                File appDir = new File(file ,imageFilesName);
                if (!appDir.exists()) {
                    appDir.mkdirs();
                }
                imageName = imageName + ".jpg";
                File currentFile = new File(appDir, imageName);
                fos = new FileOutputStream(currentFile);
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.flush();
                Toast.makeText(context, "图片已成功保存至"+currentFile.getPath(), Toast.LENGTH_SHORT).show();
                // 通知图库更新
                context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                        Uri.fromFile(new File(currentFile.getPath()))));
            } catch (Exception e) {
                Toast.makeText(context, "SD卡不存在或者不可读写", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            } finally {
                try {
                    if (fos != null) {
                        fos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else{
            Toast.makeText(context, "SD卡不存在或者不可读写", Toast.LENGTH_SHORT).show();
        }
    }
}
