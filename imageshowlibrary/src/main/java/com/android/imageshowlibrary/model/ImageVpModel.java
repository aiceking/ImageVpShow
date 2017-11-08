package com.android.imageshowlibrary.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by radio on 2017/11/6.
 */

public class ImageVpModel implements Parcelable {
    private ImageVpType imageVpType;
    private String path;
    private boolean isLoad;

    public boolean isLoad() {
        return isLoad;
    }

    public void setLoad(boolean load) {
        isLoad = load;
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.imageVpType == null ? -1 : this.imageVpType.ordinal());
        dest.writeString(this.path);
    }

    protected ImageVpModel(Parcel in) {
        int tmpImageVpType = in.readInt();
        this.imageVpType = tmpImageVpType == -1 ? null : ImageVpType.values()[tmpImageVpType];
        this.path = in.readString();
    }

    public static final Parcelable.Creator<ImageVpModel> CREATOR = new Parcelable.Creator<ImageVpModel>() {
        @Override
        public ImageVpModel createFromParcel(Parcel source) {
            return new ImageVpModel(source);
        }

        @Override
        public ImageVpModel[] newArray(int size) {
            return new ImageVpModel[size];
        }
    };
}
