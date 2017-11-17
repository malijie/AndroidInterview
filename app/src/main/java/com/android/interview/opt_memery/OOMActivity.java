package com.android.interview.opt_memery;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;

import com.android.interview.R;

/**
 * 使用优化手段解决OOM图片问题
 * Created by malijie on 2017/11/16.
 */
public class OOMActivity extends Activity{
    private ImageView iv = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oom_layout);
        iv = findViewById(R.id.id_oom);
//        noOpt(mImageView);
//        optSimpleSize(iv);
        rgb565(iv);
    }

    /**
     * 没有优化
     */
    private void noOpt(ImageView iv){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.qin);
        iv.setImageBitmap(bitmap);
        Log.d("MLJ","bitmap count=" + bitmap.getByteCount());
    }

    /**
     * 使用scale缩放比例来加载图片
     * 原图片与屏幕尺寸进行比较，如果原图片宽度（或高度）比屏幕长，则缩放2倍比例，知道缩放后的尺寸比手机屏幕尺寸小为止
     * @param iv
     */
    private void optSimpleSize(ImageView iv){
        DisplayMetrics dm = new DisplayMetrics();
        dm = this.getResources().getDisplayMetrics();
        int SCREEN_WIDTH = dm.widthPixels;
        int SCREEN_HEIGHT = dm.heightPixels;

        int scale = 2;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.mipmap.qin,options);//不直接加载，获取bitmap图片宽高

        int withTemp = options.outWidth;
        int heightTemp = options.outHeight;

        while(true){
            if(withTemp/scale < SCREEN_WIDTH){
                break;
            }
            scale = scale * 2;
        }

        scale/= 2;
        BitmapFactory.Options options2 = new BitmapFactory.Options();
        options2.inSampleSize = scale;
        Bitmap bitmap1=BitmapFactory.decodeResource(getResources(),R.mipmap.qin,options2);//scale越大，图片越模糊，所占内存越小
        iv.setImageBitmap(bitmap1);
        Log.d("MLJ","bitmap count=" + bitmap1.getByteCount());
    }

    public void rgb565(ImageView iv){
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inPreferredConfig= Bitmap.Config.RGB_565;
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(),R.mipmap.qin,options);
        iv.setImageBitmap(bitmap1);
        Log.d("MLJ","bitmap count=" + bitmap1.getByteCount());
    }


    public void choosePart2Show(){
//        DisplayMetrics dm = new DisplayMetrics();
//        dm = this.getResources().getDisplayMetrics();
//        int SCREEN_WIDTH = dm.widthPixels;
//        int SCREEN_HEIGHT = dm.heightPixels;
//
//        BitmapRegionDecoder decoder= BitmapRegionDecoder.newInstance(,false);
//        BitmapFactory.Options options2 = new BitmapFactory.Options();
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.qin,options2);
//        bitmap=decoder.decodeRegion(new Rect(width/2-WID/2+shiftpx,
//                height/2-SCREEN_HEIGHT/2,width/2+SCREEN_WIDTH/2+shiftpx,
//                height/2+SCREEN_HEIGHT/2),options2);
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }
}
