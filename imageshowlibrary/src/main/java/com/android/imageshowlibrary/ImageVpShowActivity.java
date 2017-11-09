package com.android.imageshowlibrary;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.imageshowlibrary.adapter.ImageViewPagerAdapter;
import com.android.imageshowlibrary.model.ImageVpModel;
import com.android.imageshowlibrary.view.ImageShowViewPager;
import java.util.ArrayList;

public class ImageVpShowActivity extends AppCompatActivity  {
private LinearLayout linear_back;
private ImageShowViewPager vp_imageshow;
private TextView tv_image_number,tv_image_save;
private ArrayList<ImageVpModel> imageList;
private int currentPosition;
private String imageSaveFileName;
private ImageViewPagerAdapter adapter;
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
        imageSaveFileName=getIntent().getStringExtra("imageSaveFileName");
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
                Toast.makeText(ImageVpShowActivity.this, "保存", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
