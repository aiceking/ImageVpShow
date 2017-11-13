package com.android.imagevpshow;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.imageshowlibrary.ImageVpShowActivity;
import com.android.imageshowlibrary.ImageVpShowHelp;
import com.android.imageshowlibrary.model.ImageVpModel;
import com.android.imageshowlibrary.model.ImageVpType;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=(Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置图片加载器
                ImageVpShowHelp.getInstance().setShowImageListener(new ImageVpShowHelp.showImageListener() {
                    @Override
                    public void showImage(Context context, ImageVpType imageVpType, String path, ImageView imageView) {
                        if (imageVpType== ImageVpType.Local){
                            Glide.with(context).load(new File(path)).crossFade().into(imageView);
                        }else if (imageVpType== ImageVpType.Net){
                            Glide.with(context).load(path).crossFade().into(imageView);
                        }
                    }
                });
                //配置图片保存方法
                ImageVpShowHelp.getInstance().setSaveImageListener(new ImageVpShowHelp.saveImageListener() {
                    @Override
                    public void saveImage(Context context, String url, int position) {
                        savePicture(context,"myTestImages","image"+position,url);
                    }
                });
                //模拟网络图片展示
                startNewIntent();

            }
        });
    }

    private void startNewIntent() {
        Intent intent=new Intent(MainActivity.this, ImageVpShowActivity.class);
        ArrayList<ImageVpModel> list=new ArrayList<>();
        list.add(new ImageVpModel(ImageVpType.Net,
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510212565&di=6a65804488c5146bdde006f13bb983c5&imgtype=jpg&er=1&src=http%3A%2F%2Fff.topitme.com%2Ff%2F1a%2Ff4%2F1137312418ec4f41afm.jpg"
        ));
        list.add(new ImageVpModel(ImageVpType.Net,
                "http://old.bz55.com/uploads/allimg/140922/138-140922145121.jpg"));
        list.add(new ImageVpModel(ImageVpType.Net,
                "http://old.bz55.com/uploads/allimg/140922/138-140922145121-50.jpg"));

        list.add(new ImageVpModel(ImageVpType.Net,
                "http://old.bz55.com/uploads/allimg/140922/138-140922145122.jpg"));
        list.add(new ImageVpModel(ImageVpType.Net,
                "http://old.bz55.com/uploads/allimg/140922/138-140922145119.jpg"));
        list.add(new ImageVpModel(ImageVpType.Net,
                "http://old.bz55.com/uploads/allimg/140922/138-140922145120-50.jpg"));
        list.add(new ImageVpModel(ImageVpType.Net,
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510212811&di=a4aebf282dcec8b70921b0bce08847ce&imgtype=jpg&er=1&src=http%3A%2F%2Ff2.topitme.com%2F2%2F2b%2Fcc%2F113731243120ccc2b2m.jpg"));
        list.add(new ImageVpModel(ImageVpType.Net,
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509618093965&di=8e9d62f51db174daee4b8446db44cea3&imgtype=0&src=http%3A%2F%2Fgb.cri.cn%2Fmmsource%2Fimages%2F2013%2F06%2F25%2F90%2F13492636535114937994.jpg"));
        intent.putParcelableArrayListExtra("imageList",list);
        intent.putExtra("currentPosition",0);
        startActivity(intent);
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
