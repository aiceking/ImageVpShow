package com.android.imageshowlibrary.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.imageshowlibrary.R;
import com.android.imageshowlibrary.fragment.base.BaseFragment;
import com.android.imageshowlibrary.model.ImageVpModel;
import com.android.imageshowlibrary.model.ImageVpType;
import com.android.imageshowlibrary.view.MyController;
import com.dueeeke.videoplayer.player.IjkVideoView;
import com.dueeeke.videoplayer.player.PlayerConfig;

public class VideoFragment extends BaseFragment {
    private IjkVideoView videoplayer;
    private ImageVpModel imageVpModel;
    public static VideoFragment newInstance(ImageVpModel imageVpModel){
        VideoFragment videoFragment=new VideoFragment();
        Bundle bundle=new Bundle();
        bundle.putParcelable("ImageVpModel",imageVpModel);
        videoFragment.setArguments(bundle);
        return videoFragment;
    }
    @Override
    protected int getLayoutRes() {
        return R.layout.vp_item_video;
    }

    @Override
    protected void initView(View rootView) {
         imageVpModel=(ImageVpModel)(getArguments().getParcelable("ImageVpModel"));
        videoplayer=(IjkVideoView)rootView.findViewById(R.id.videoplayer);
    }

    @Override
    public void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        if (imageVpModel.getImageVpType()== ImageVpType.LocalVideo){
            videoplayer.setUrl("file://"+imageVpModel.getPath());
        }else {
            videoplayer.setUrl(imageVpModel.getPath());
        }
        videoplayer.setPlayerConfig(new PlayerConfig.Builder().disableAudioFocus().setLooping().build());
        MyController controller1 = new MyController(getParentActivity());
        videoplayer.setVideoController(controller1);
    }

    @Override
    public void onFragmentPause() {
        super.onFragmentPause();
        videoplayer.pause();
    }

    @Override
    public void onFragmentResume() {
        super.onFragmentResume();
        videoplayer.resume();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        videoplayer.release();
    }
}
