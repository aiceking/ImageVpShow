package com.android.imageshowlibrary;

import android.content.Context;
import android.widget.ImageView;

import com.android.imageshowlibrary.model.ImageVpType;

/**
 * Created by radio on 2017/11/10.
 */

public class ImageVpShowHelp {
    public void setShowImageListener(ImageVpShowHelp.showImageListener showImageListener) {
        this.showImageListener = showImageListener;
    }

    private showImageListener showImageListener;
    public interface showImageListener{
        void showImage(Context context,ImageVpType imageVpType, String path, ImageView imageView);
    }
    private static ImageVpShowHelp imageVpShowHelp;
    private ImageVpShowHelp(){}
    public static ImageVpShowHelp getInstance(){
        if (imageVpShowHelp==null){
            synchronized (ImageVpShowHelp.class){
                if (imageVpShowHelp==null){
                    imageVpShowHelp=new ImageVpShowHelp();
                }
            }
        }
        return imageVpShowHelp;
    }
    public void showImage(Context context,ImageVpType imageVpType,String path,ImageView imageView){
        if (showImageListener!=null){
            showImageListener.showImage( context,imageVpType,path,imageView);
        }
    }
}
