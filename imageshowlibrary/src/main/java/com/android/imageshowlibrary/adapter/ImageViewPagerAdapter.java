package com.android.imageshowlibrary.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.imageshowlibrary.ImageVpShowHelp;
import com.android.imageshowlibrary.R;
import com.android.imageshowlibrary.model.ImageVpModel;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.List;

/**
 * Created by radio on 2017/9/20.
 */

public class ImageViewPagerAdapter extends PagerAdapter {
    private Context context;
    private List<ImageVpModel> list;
    private SparseArray<View> cacheView;

    public ImageViewPagerAdapter(List<ImageVpModel> list, Context context) {
        this.context = context;
        this.list = list;
        cacheView = new SparseArray<>(list.size());
    }

    @Override
    public Object instantiateItem(final ViewGroup container, int position) {
        View view = cacheView.get(position);
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.vp_item_image,container,false);
            view.setTag(position);
            final PhotoView image = (PhotoView) view.findViewById(R.id.image);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((Activity)context).finish();
                }
            });
            ImageVpShowHelp.getInstance().showImage(context,list.get(position).getImageVpType(),list.get(position).getPath(),image);
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
