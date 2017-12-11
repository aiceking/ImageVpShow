package com.android.imageshowlibrary;


import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.imageshowlibrary.model.ImageVpModel;
import com.android.imageshowlibrary.model.ImageVpType;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.util.ArrayList;
import java.util.List;

public class ImageVpShowFragmentActivity extends AppCompatActivity {
    private LinearLayout linear_back;
    private ViewPager vp_imageshow;
    private TextView tv_image_number,tv_image_save;
    private ArrayList<ImageVpModel> imageList;
    private int currentPosition;
    private List<ImageShowFragment> list_fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_vp_show_fragment_imageshowlibrary);
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
        list_fragments=new ArrayList<>();
        for (ImageVpModel imageVpModel:imageList){
            ImageShowFragment imageShowFragment=ImageShowFragment.newInstance(imageVpModel);
            list_fragments.add(imageShowFragment);
        }
        vp_imageshow.setAdapter(new MyAdapter(getSupportFragmentManager()));
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
        vp_imageshow=(ViewPager)findViewById(R.id.vp_imageshow);
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
                getFilePerMission();

            }
        });

    }

    private void getFilePerMission() {
        AndPermission.with(this)
                .requestCode(100)
                .permission(Permission.STORAGE)
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                        rationale.resume();
                    }
                }).callback(new PermissionListener() {
            @Override
            public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                if(AndPermission.hasPermission(ImageVpShowFragmentActivity.this,Permission.STORAGE)) {
                    saveImage();
                } else {
                    Toast.makeText(ImageVpShowFragmentActivity.this, "存储权限授予失败，请自行在设置中授权", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                if (AndPermission.hasAlwaysDeniedPermission(ImageVpShowFragmentActivity.this, deniedPermissions)) {
                    Toast.makeText(ImageVpShowFragmentActivity.this, "您已手动拒绝存储权限，请自行在设置中授权", Toast.LENGTH_SHORT).show();
                }else if (!AndPermission.hasPermission(ImageVpShowFragmentActivity.this,Permission.STORAGE)){
                    Toast.makeText(ImageVpShowFragmentActivity.this, "请开启存储权限", Toast.LENGTH_SHORT).show();

                }
            }
        }).start();
    }

    public void saveImage(){
        if (imageList.get(currentPosition).getImageVpType()== ImageVpType.Local){
            ImageVpShowManager.getInstance().showToast(ImageVpShowFragmentActivity.this,"该图为本地图片，无需下载");
        }else{
            ImageVpShowManager.getInstance().saveImage(ImageVpShowFragmentActivity.this,imageList.get(currentPosition).getPath(),currentPosition);
        }
    }
    public class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "";
        }

        @Override
        public Fragment getItem(int position) {
            return list_fragments.get(position);
        }

        @Override
        public int getCount() {
            return list_fragments.size();
        }
    }
}