package com.android.imageshowlibrary;

import android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;

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

    public ImageVpShowHelp.saveImageListener getSaveImageListener() {
        return saveImageListener;
    }

    public void setSaveImageListener(ImageVpShowHelp.saveImageListener saveImageListener) {
        this.saveImageListener = saveImageListener;
    }

    private saveImageListener saveImageListener;
    public interface saveImageListener{
        void saveImage(Context context, String url,int position);
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
        }else{
            showToast(context,"请初始化图片加载接口");
        }
    }
    public void saveImage(Context context, String url,int position){
        if (saveImageListener!=null){
            saveImageListener.saveImage(context,url,position);
        }else{
            showToast(context,"请初始化图片下载接口");
        }
    }
    private  Toast toast;
    public  void showToast(Context context,String message){
        if(toast==null){
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        }
        toast.setText(message);
        toast.show();
    }
}
