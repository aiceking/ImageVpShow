package com.android.imagevpshow;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.imageshowlibrary.ImageVpShowActivity;
import com.android.imageshowlibrary.model.ImageVpModel;
import com.android.imageshowlibrary.model.ImageVpType;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private Button btn_net,btn_local;
    private int IMAGE_OK=10;
    private ArrayList<ImageVpModel> list_local;
    private Handler ImageHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==IMAGE_OK){
                startLocalIntent();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_net=(Button) findViewById(R.id.btn_net);
        btn_local=(Button) findViewById(R.id.btn_local);
        btn_net.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //模拟网络图片展示
                startNetIntent();
            }
        });
        btn_local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //模拟本地图片展示
                getImage();
            }
        });
    }

    private void startLocalIntent() {
        Intent intent=new Intent(MainActivity.this, ImageVpShowActivity.class);
        intent.putParcelableArrayListExtra("imageList",list_local);
        intent.putExtra("currentPosition",0);
        startActivity(intent);
    }
    public void getImage(){
        if (list_local==null){
            list_local=new ArrayList<>();
        }else{
            list_local.clear();
        }
        new Runnable(){
            @Override
            public void run() {
                Uri imageuri= MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver imageResolver=MainActivity.this.getContentResolver();
                Cursor imageCursor = imageResolver.query(imageuri, null,
                        MediaStore.MediaColumns.MIME_TYPE + "=? or "
                                + MediaStore.MediaColumns.MIME_TYPE + "=?", new String[]{
                                "image/jpeg", "image/png"},
                        MediaStore.MediaColumns.DATE_MODIFIED);
                while (imageCursor.moveToNext()){
                    String path = imageCursor.getString(imageCursor
                            .getColumnIndex(MediaStore.MediaColumns.DATA)).trim();
                    list_local.add(new ImageVpModel(ImageVpType.Local,path));
                }
                //扫描完释放
                imageCursor.close();
                ImageHandler.sendEmptyMessage(IMAGE_OK);
            }
        }.run();
    }
    private void startNetIntent() {
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
        ArrayList<ImageVpModel> list_images=new ArrayList<>();
        for (int i=0;i<100;i++){
            list_images.addAll(list);
        }
        intent.putParcelableArrayListExtra("imageList",list_images);
        intent.putExtra("currentPosition",0);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
