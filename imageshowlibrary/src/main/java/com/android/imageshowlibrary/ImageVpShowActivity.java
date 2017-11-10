package com.android.imageshowlibrary;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.imageshowlibrary.adapter.ImageViewPagerAdapter;
import com.android.imageshowlibrary.model.ImageVpModel;
import com.android.imageshowlibrary.model.ImageVpType;
import com.android.imageshowlibrary.view.ImageShowViewPager;
import java.util.ArrayList;

public class ImageVpShowActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {
private LinearLayout linear_back;
private ImageShowViewPager vp_imageshow;
private TextView tv_image_number,tv_image_save;
private ArrayList<ImageVpModel> imageList;
private int currentPosition;
private ImageViewPagerAdapter adapter;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_vp_show);
        getData();
        initView();
        initViewPager();
    }

    @Override
    protected void onPause() {
        this.overridePendingTransition(0, 0);
        super.onPause();
    }

    private void getData() {
        imageList=getIntent().getParcelableArrayListExtra("imageList");
        currentPosition=getIntent().getIntExtra("currentPosition",0);
    }

    private void initViewPager() {
        if (imageList==null){
            Toast.makeText(this, "数据为空", Toast.LENGTH_SHORT).show();
            return;
        }
        adapter = new ImageViewPagerAdapter(imageList, this);
        vp_imageshow.setAdapter(adapter);
        vp_imageshow.setCurrentItem(currentPosition, false);
        tv_image_number.setText(currentPosition+1 + "/" + imageList.size());
        vp_imageshow.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                currentPosition = position;
                tv_image_number.setText(currentPosition + 1 + "/" + imageList.size());
            }
        });
    }

    private void initView() {
        linear_back=(LinearLayout)findViewById(R.id.linear_back);
        vp_imageshow=(ImageShowViewPager)findViewById(R.id.vp_imageshow);
        tv_image_number=(TextView)findViewById(R.id.tv_image_number);
        tv_image_save=(TextView)findViewById(R.id.tv_image_save);
        linear_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
            tv_image_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!getStoragePermissions(ImageVpShowActivity.this))return;
                    saveImage();
                }
            });

    }
public void saveImage(){
    if (imageList.get(currentPosition).getImageVpType()== ImageVpType.Local){
        ImageVpShowHelp.getInstance().showToast(ImageVpShowActivity.this,"该图为本地图片，无需下载");
    }else{
        ImageVpShowHelp.getInstance().saveImage(ImageVpShowActivity.this,imageList.get(currentPosition).getPath(),currentPosition);
    }
}
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==REQUEST_EXTERNAL_STORAGE){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                saveImage();
            }
        }
    }

    public  boolean getStoragePermissions(Activity activity) {
         boolean flag=false;
        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);
            }else{
                flag=true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
