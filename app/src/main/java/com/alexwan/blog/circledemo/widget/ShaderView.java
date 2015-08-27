package com.alexwan.blog.circledemo.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.alexwan.blog.circledemo.R;

/**
 * Created by 365 on 2015/7/27.
 */
public class ShaderView extends ImageView {
    private BitmapShader mBitmapShader;
    private Paint mFillPaint;
    private Paint mPaint;
    private int mBitmapWidth,mBitmapHeight;
    private float mDrawableRadius;
    public ShaderView(Context context) {
        this(context,null);
    }
    private Bitmap mBitmap;
    public ShaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 抗锯齿
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        // 空心
        mPaint.setStyle(Paint.Style.STROKE);
        // 透明
        mPaint.setColor(0xFF000000);
        mPaint.setStrokeWidth(5);
        mFillPaint = new Paint();
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.test);
        mBitmapWidth = mBitmap.getWidth();
        mBitmapHeight = mBitmap.getHeight();
//        if(mBitmapWidth < getWidth()){
//
//        }else if(mBitmapHeight < getHeight()){
//
//        }
        mDrawableRadius = mBitmapWidth > mBitmapHeight?mBitmapHeight: mBitmapHeight;
        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP , Shader.TileMode.CLAMP);
        mFillPaint.setShader(mBitmapShader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        canvas.drawColor(0x00000000);
        canvas.drawCircle(canvas.getWidth(), canvas.getHeight(), mDrawableRadius / 2, mFillPaint);
        canvas.drawCircle(canvas.getWidth(), canvas.getHeight(), mDrawableRadius / 2, mPaint);
        invalidate();
    }
}
