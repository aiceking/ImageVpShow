package com.android.imageshowlibrary.fragment.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;

public abstract class BaseFragment extends BaseLazyFragment  {
    private AppCompatActivity activity;

    public AppCompatActivity getParentActivity(){
        return activity;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity=(AppCompatActivity)context;
    }
    public void showToast(String s) {
        Toast.makeText(activity, s, Toast.LENGTH_SHORT).show();
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutRes(), container, false);
        }
        initView(rootView);
        return rootView;
    }

}
