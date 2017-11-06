package com.android.imageshowlibrary.model;

import java.io.Serializable;

/**
 * Created by radio on 2017/11/6.
 */

public class ImageVpModel implements Serializable{
    private ImageVpType imageVpType;
    private String path;
    public ImageVpModel(){}
    public ImageVpModel(ImageVpType imageVpType, String path) {
        this.imageVpType = imageVpType;
        this.path = path;
    }
    public ImageVpType getImageVpType() {
        return imageVpType;
    }

    public void setImageVpType(ImageVpType imageVpType) {
        this.imageVpType = imageVpType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
