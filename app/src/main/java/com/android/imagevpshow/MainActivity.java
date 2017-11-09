package com.android.imagevpshow;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.imageshowlibrary.ImageVpShowActivity;
import com.android.imageshowlibrary.model.ImageVpModel;
import com.android.imageshowlibrary.model.ImageVpType;

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
        });
    }

}
