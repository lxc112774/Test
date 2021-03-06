package com.example.lxc.android_video.help.download_img;

/**
 * Created by lxc on 18-11-23.
 */

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.example.lxc.android_video.R;

/**
 * 自定义的BitmapUtils,实现图片三级缓存
 */
public class BitmapUtils {

    private NetCacheUtils mNetCacheUtils;
    private LocalCacheUtils mLocalCacheUtils;
    private MemoryCacheUtils mMemoryCacheUtils;

    public BitmapUtils(){
        mMemoryCacheUtils=new MemoryCacheUtils();
        mLocalCacheUtils=new LocalCacheUtils();
        mNetCacheUtils=new NetCacheUtils(mLocalCacheUtils,mMemoryCacheUtils);
    }

    public void disPlay(ImageView ivPic, String url) {
        ivPic.setImageResource(R.drawable.lxc);
        Bitmap bitmap;
        //内存缓存
        bitmap=mMemoryCacheUtils.getBitmapFromMemory(url);
        if (bitmap!=null){
            ivPic.setImageBitmap(bitmap);
            System.out.println("从内存获取图片啦.....");
            return;
        }

        //本地缓存
        bitmap = mLocalCacheUtils.getBitmapFromLocal(url);
        if(bitmap !=null){
            ivPic.setImageBitmap(bitmap);
            System.out.println("从本地获取图片啦.....");
            //从本地获取图片后,保存至内存中
            mMemoryCacheUtils.setBitmapToMemory(url,bitmap);
            return;
        }
        //网络缓存
        mNetCacheUtils.getBitmapFromNet(ivPic,url);
    }
}