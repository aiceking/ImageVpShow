package com.android.imageshowlibrary.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.android.imageshowlibrary.fragment.ImageFragment;
import com.android.imageshowlibrary.fragment.VideoFragment;
import com.android.imageshowlibrary.model.ImageVpModel;
import com.android.imageshowlibrary.model.ImageVpType;

import java.util.List;
public class ImageVideoPagerAdapter extends FragmentStatePagerAdapter {
    private List<ImageVpModel> imageVpModelList;
    public ImageVideoPagerAdapter(FragmentManager fm,List<ImageVpModel> imageVpModelList) {
        super(fm);
        this.imageVpModelList=imageVpModelList;
    }
    @Override
    public Fragment getItem(int position) {
        if (imageVpModelList.get(position).getImageVpType()== ImageVpType.LocalImage
                ||imageVpModelList.get(position).getImageVpType()== ImageVpType.NetImage){
            Log.v("image",position+"");
            return ImageFragment.newInstance(imageVpModelList.get(position));
        }else {
            Log.v("video",position+"");
            return VideoFragment.newInstance(imageVpModelList.get(position));
        }
    }
    @Override
    public int getCount() {
        return imageVpModelList.size();
    }
}
