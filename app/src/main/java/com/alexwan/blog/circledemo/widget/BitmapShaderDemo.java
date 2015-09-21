package com.alexwan.blog.circledemo.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.alexwan.blog.circledemo.R;
import com.alexwan.blog.circledemo.help.AppProfile;
import com.alexwan.blog.circledemo.util.ScreenUtil;

/**
 * BitmapShaderDemo
 */
public class BitmapShaderDemo extends View {
    private int[] screenSize;
    private Paint mPaint;
    private Bitmap mBitmap;
    private static final int RECT_SIZE = 400;
    private int left, top, right, bottom;
    private static final String TAG  = BitmapShaderDemo.class.getSimpleName();
    public BitmapShaderDemo(Context context) {
        this(context, null);
    }

    public BitmapShaderDemo(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        screenSize = ScreenUtil.getScreenSize(context);
        // 抗锯齿
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), R.drawable.saber , options);
        options.inSampleSize = 4;
        options.inJustDecodeBounds = false;
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.saber , options);
        //
        if(AppProfile.DEBUG){
//            Log.d(TAG , "Bitmap width : "+mBitmap.getWidth());
        }
        left = screenSize[0] / 2 - RECT_SIZE;
        top = screenSize[1]  / 2 - RECT_SIZE;
        right = screenSize[0] / 2 + RECT_SIZE;
        bottom = screenSize[1] / 2 + RECT_SIZE;
        mPaint.setShader(new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.MIRROR));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //
        canvas.drawRect(0,0 ,screenSize[0] , screenSize[1], mPaint);
    }
}
