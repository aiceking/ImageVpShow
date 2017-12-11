package com.android.imageshowlibrary;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.imageshowlibrary.model.ImageVpModel;
import com.github.chrisbanes.photoview.PhotoView;
import com.jaywei.mdprogress.CircularProgressBar;

/**
 * Created by static on 2017/12/9/009.
 */

public class ImageShowFragment extends Fragment implements View.OnClickListener{
    private PhotoView imageView_normal;
    private ImageVpModel imageVpModel;
    private CircularProgressBar progressBar;
    private AppCompatActivity appCompatActivity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        appCompatActivity=(AppCompatActivity)context;
    }

    public static ImageShowFragment newInstance(ImageVpModel imageVpModel){
        ImageShowFragment imageShowFragment=new ImageShowFragment();
        Bundle bundle=new Bundle();
        bundle.putParcelable("imageVpModel",imageVpModel);
        imageShowFragment.setArguments(bundle);
        return imageShowFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.vp_item_image, container, false);
        imageVpModel=getArguments().getParcelable("imageVpModel");
        imageView_normal = (PhotoView)contentView.findViewById(R.id.image);
        progressBar=(CircularProgressBar)contentView.findViewById(R.id.progressbar);
        imageView_normal.setOnClickListener(this);
       // ImageVpShowManager.getInstance().showImage(this,imageVpModel.getImageVpType(),imageVpModel.getPath(),imageView_normal,progressBar);
        return contentView;
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.image){
            appCompatActivity.finish();
        }
    }
}
