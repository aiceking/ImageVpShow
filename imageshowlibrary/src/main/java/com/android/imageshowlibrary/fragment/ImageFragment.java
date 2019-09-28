package com.android.imageshowlibrary.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.android.imageshowlibrary.ImageVpShowManager;
import com.android.imageshowlibrary.R;
import com.android.imageshowlibrary.fragment.base.BaseFragment;
import com.android.imageshowlibrary.model.ImageVpModel;
import com.github.chrisbanes.photoview.PhotoView;
import com.jaywei.mdprogress.CircularProgressBar;

public class ImageFragment extends BaseFragment {
    private ImageVpModel imageVpModel;
    private  PhotoView image;
    private CircularProgressBar circleProgressBar;
    public static ImageFragment newInstance(ImageVpModel imageVpModel){
        ImageFragment imageFragment=new ImageFragment();
        Bundle bundle=new Bundle();
        bundle.putParcelable("ImageVpModel",imageVpModel);
        imageFragment.setArguments(bundle);
        return imageFragment;
    }
    @Override
    protected int getLayoutRes() {
        return R.layout.vp_item_image;
    }

    @Override
    public void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        ImageVpShowManager.getInstance().showImage(getParentActivity(),imageVpModel.getImageVpType(),imageVpModel.getPath(),image,circleProgressBar);
    }
    @Override
    protected void initView(View rootView) {
        imageVpModel=(ImageVpModel)(getArguments().getParcelable("ImageVpModel"));
         image = (PhotoView) rootView.findViewById(R.id.image);
         circleProgressBar=(CircularProgressBar)rootView.findViewById(R.id.progressbar);
        circleProgressBar.setVisibility(View.VISIBLE);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentActivity().finish();
            }
        });

    }
    private void unbindDrawables(View view)
    {
        if (view.getBackground() != null)
        {
            view.getBackground().setCallback(null);
        }
        if (view instanceof ViewGroup && !(view instanceof AdapterView))
        {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++)
            {
                unbindDrawables(((ViewGroup) view).getChildAt(i));
            }
            ((ViewGroup) view).removeAllViews();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbindDrawables(rootView);
    }
}
