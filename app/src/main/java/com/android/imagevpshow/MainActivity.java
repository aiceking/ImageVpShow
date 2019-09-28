package com.android.imagevpshow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.imageshowlibrary.ImageVideoShowActivity;
import com.android.imageshowlibrary.model.ImageVpModel;
import com.android.imageshowlibrary.model.ImageVpType;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btn=(Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, ImageVideoShowActivity.class);
                ArrayList<ImageVpModel> list=new ArrayList<>();
                list.add(new ImageVpModel(ImageVpType.NetImage,
                        "http://img4.imgtn.bdimg.com/it/u=1463932439,3051898217&fm=214&gp=0.jpg"
                ));
                list.add(new ImageVpModel(ImageVpType.NetImage,
                        "http://img1.cache.netease.com/catchpic/4/47/47E5E3CA5A91B67C4D76CF2F01970A3D.jpg"));
                list.add(new ImageVpModel(ImageVpType.NetImage,
                        "http://img4.imgtn.bdimg.com/it/u=1463932439,3051898217&fm=214&gp=0.jpg"
                ));
                list.add(new ImageVpModel(ImageVpType.NetImage,
                        "http://img1.cache.netease.com/catchpic/4/47/47E5E3CA5A91B67C4D76CF2F01970A3D.jpg"));list.add(new ImageVpModel(ImageVpType.NetImage,
                        "http://img4.imgtn.bdimg.com/it/u=1463932439,3051898217&fm=214&gp=0.jpg"
                ));
                list.add(new ImageVpModel(ImageVpType.NetImage,
                        "http://img1.cache.netease.com/catchpic/4/47/47E5E3CA5A91B67C4D76CF2F01970A3D.jpg"));list.add(new ImageVpModel(ImageVpType.NetImage,
                        "http://img4.imgtn.bdimg.com/it/u=1463932439,3051898217&fm=214&gp=0.jpg"
                ));
                list.add(new ImageVpModel(ImageVpType.NetImage,
                        "http://img1.cache.netease.com/catchpic/4/47/47E5E3CA5A91B67C4D76CF2F01970A3D.jpg"));
                list.add(new ImageVpModel(ImageVpType.NetVideo,
                        "http://jzvd.nathen.cn/342a5f7ef6124a4a8faf00e738b8bee4/cf6d9db0bd4d41f59d09ea0a81e918fd-5287d2089db37e62345123a1be272f8b.mp4"));
                list.add(new ImageVpModel(ImageVpType.NetVideo,
                        "http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4"));
                list.add(new ImageVpModel(ImageVpType.NetVideo,
                        "http://jzvd.nathen.cn/342a5f7ef6124a4a8faf00e738b8bee4/cf6d9db0bd4d41f59d09ea0a81e918fd-5287d2089db37e62345123a1be272f8b.mp4"));
                list.add(new ImageVpModel(ImageVpType.NetVideo,
                        "http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4"));
                list.add(new ImageVpModel(ImageVpType.NetVideo,
                        "http://jzvd.nathen.cn/342a5f7ef6124a4a8faf00e738b8bee4/cf6d9db0bd4d41f59d09ea0a81e918fd-5287d2089db37e62345123a1be272f8b.mp4"));
                list.add(new ImageVpModel(ImageVpType.NetVideo,
                        "http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4"));
                list.add(new ImageVpModel(ImageVpType.NetVideo,
                        "http://jzvd.nathen.cn/342a5f7ef6124a4a8faf00e738b8bee4/cf6d9db0bd4d41f59d09ea0a81e918fd-5287d2089db37e62345123a1be272f8b.mp4"));
                list.add(new ImageVpModel(ImageVpType.NetVideo,
                        "http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4"));

                intent.putParcelableArrayListExtra("imageList",list);
                intent.putExtra("currentPosition",0);
                startActivity(intent);
            }
        });


    }

}
