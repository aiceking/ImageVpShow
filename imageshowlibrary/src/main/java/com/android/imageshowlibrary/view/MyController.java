package com.android.imageshowlibrary.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.dueeeke.videocontroller.StandardVideoController;
import com.dueeeke.videoplayer.util.L;

public class MyController extends StandardVideoController {
    public MyController(@NonNull Context context) {
        super(context);
    }

    public MyController(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyController(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initView() {
        super.initView();
        fullScreenButton.setVisibility(View.INVISIBLE);
    }

}
