package com.android.imageshowlibrary.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.imageshowlibrary.R;
import com.android.imageshowlibrary.model.ImageVpModel;
import com.android.imageshowlibrary.model.ImageVpType;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;

import java.io.File;
import java.util.List;

/**
 * Created by radio on 2017/9/20.
 */

public class ImageViewPagerAdapter extends PagerAdapter {
    private Context context;
    private List<ImageVpModel> list;
    private SparseArray<View> cacheView;
    private String type;

    public ImageViewPagerAdapter(List<ImageVpModel> list, Context context, String type) {
        this.context = context;
        this.list = list;
        this.type=type;
        cacheView = new SparseArray<>(list.size());
    }

    @Override
    public Object instantiateItem(final ViewGroup container, int position) {
        View view = cacheView.get(position);
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.vp_item_image,container,false);
            view.setTag(position);
            final PhotoView image = (PhotoView) view.findViewById(R.id.image);
            if (list.get(position).getImageVpType()== ImageVpType.Local){
                Glide.with(context).load(new File(list.get(position).getPath())).asBitmap().into(image);
            }else if (list.get(position).getImageVpType()== ImageVpType.Net){
                Glide.with(context).load(list.get(position).getPath()).asBitmap().into(image);
            }
            cacheView.put(position,view);
        }
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }

}
