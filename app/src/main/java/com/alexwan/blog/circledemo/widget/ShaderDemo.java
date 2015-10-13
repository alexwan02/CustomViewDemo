package com.alexwan.blog.circledemo.widget;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.alexwan.blog.circledemo.R;
import com.alexwan.blog.circledemo.help.AppProfile;
import com.alexwan.blog.circledemo.util.ScreenUtil;

import java.lang.ref.SoftReference;


/**
 * BitmapShaderDemo
 */
public class ShaderDemo extends View {
    private int[] screenSize;
    private Paint mBitmapPaint, mSweepPaint, mLinearPaint, mRadialPaint, mShaderPaint;
    private Bitmap mBitmap;
    private static final int RECT_SIZE = 400;
    private int width, height, divider;
    private int screenWidth, screenHeight;
    private Matrix mMatrix;
    private static final String TAG = ShaderDemo.class.getSimpleName();
    private BitmapShader mBitmapShader;
    private Bitmap mRadailBitmap, mRefBitmap;
    private Paint mXfermodePaint;
    private PorterDuffXfermode mPorterDuffXfermode, mXfermode;

    public ShaderDemo(Context context) throws Exception { this(context, null); }

    public ShaderDemo(Context context, AttributeSet attrs) throws Exception {
        super(context, attrs);
        if(! (context instanceof  Activity)){
            throw  new Exception("context not activity exception");
        }
        init(context);
    }

    private void init(Context context) {
        mHander = new MyHandler((Activity)context);
        screenSize = ScreenUtil.getScreenSize(context);
        mBitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        // 线性
        mLinearPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        // 梯度
        mSweepPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        // 径向
        mRadialPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inSampleSize = 4;
        options.inJustDecodeBounds = false;
        // 原图
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sakura, options);
        // 根据原图生成径向阴影层
        mRadailBitmap = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Matrix refMatrix = new Matrix();
        refMatrix.setScale(1f, -1f);
        // 根据原图生成倒影图
        mRefBitmap = Bitmap.createBitmap(mBitmap, 0, 0, mBitmap.getWidth(), mBitmap.getHeight(), refMatrix, true);

        if (AppProfile.DEBUG) {
            /* Log.d(TAG , "Bitmap width : "+mBitmap.getWidth());*/
        }
        screenWidth = screenSize[0];
        screenHeight = screenSize[1];
        width = height = screenWidth * 3 / 8;
        divider = screenWidth * 1 / 16;
        // BitmapShader：Bitmap着色器
        Matrix mMatrix = new Matrix();
        mMatrix.setTranslate(divider, divider * 2 + height);
        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mBitmapShader.setLocalMatrix(mMatrix);
        mBitmapPaint.setShader(mBitmapShader);
        // LinearGradient：线性渐变
        mLinearPaint.setShader(new LinearGradient(divider, divider, divider + width, divider + height, Color.RED, Color
                .YELLOW, Shader.TileMode.REPEAT));

        // 混合模式Paint
        mXfermodePaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mXfermodePaint.setShader(new LinearGradient(divider, (divider + height) * 2 + divider + mBitmap.getHeight(),
                divider + mBitmap.getWidth(), (divider + height) * 2 + divider + mBitmap.getHeight() * 2, 0xAAff0000,
                Color.TRANSPARENT, Shader.TileMode.CLAMP));
        /* SweepGradient：梯度渐变*/
        mSweepPaint.setShader(
                new SweepGradient(screenSize[0] - width / 2 - divider, divider + height / 2, Color.RED, Color
                        .YELLOW));
        // 径向渐变
        RadialGradient rg = new RadialGradient(divider + mBitmap.getWidth() / 2, (divider + height) * 2 + divider
                + mBitmap.getHeight() / 2, mBitmap.getWidth() / 2, new int[]{0, 0, 0x2F000000}, new float[]{0F,
                0.7F, 1.0F}, Shader.TileMode.CLAMP);
        mRadialPaint.setShader(rg);
        mHander.sendEmptyMessageDelayed(0, 500);
        mPorterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SCREEN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // BitmapShader

        // 线性
        canvas.drawRect(divider, divider, divider + width, divider + height, mLinearPaint);
        // 梯度
        canvas.drawRect(divider + screenWidth / 2, divider, divider + screenWidth / 2 + width, divider + height,
                mSweepPaint);
        // 径向
        canvas.save();
        canvas.drawRect(divider, divider * 2 + height, screenWidth - divider, divider * 2 + height * 2,
                mBitmapPaint);
        canvas.restore();
        // 原图
        canvas.drawBitmap(mBitmap, divider, (divider + height) * 2 + divider, mBitmapPaint);
        //
        canvas.drawRect(divider, (divider + height) * 2 + divider, divider + mBitmap.getWidth(), (divider +
                height) * 2 + divider + mBitmap.getHeight(), mRadialPaint);
        // 新建图层
        int src = canvas.saveLayer(divider, (divider + height) * 2 + divider + mBitmap.getHeight(), divider +
                mBitmap.getWidth(), (divider + height) * 3 + mBitmap.getHeight(), null, Canvas.ALL_SAVE_FLAG);
        //画倒影图
        canvas.drawBitmap(mRefBitmap, divider, (divider + height) * 2 + mBitmap.getHeight() + divider, null);
        // 混合模式
        mXfermodePaint.setXfermode(mPorterDuffXfermode);
        canvas.drawRect(divider, (divider + height) * 2 + divider + mBitmap.getHeight(), divider +
                mBitmap.getWidth(), (divider + height) * 3 + mBitmap.getHeight(), mXfermodePaint);
        mXfermodePaint.setXfermode(null);
        canvas.restoreToCount(src);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        MeasureSpec.getMode(widthMeasureSpec);
        MeasureSpec.getSize(widthMeasureSpec);
        MeasureSpec.getMode(heightMeasureSpec);
        MeasureSpec.getSize(heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private Handler mHander;
    private class MyHandler extends Handler{
        SoftReference<Activity> mActivityReference;
        MyHandler(Activity activity){
            mActivityReference = new SoftReference<Activity>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            mBitmapPaint.clearShadowLayer();
            switch (what) {
                case 0:
                    mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
                    mMatrix = new Matrix();
                    mMatrix.setTranslate(divider, divider * 2 + height);
                    mBitmapShader.setLocalMatrix(mMatrix);
                    if (AppProfile.DEBUG) {
                        Log.d(TAG, "Matrix : " + mMatrix.toString());
                    }
                    mBitmapPaint.setShader(mBitmapShader);
                    break;
                case 1:
                    mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
                    mMatrix = new Matrix();
                    mMatrix.setTranslate(divider, divider * 2 + height);
                    mBitmapShader.setLocalMatrix(mMatrix);
                    if (AppProfile.DEBUG) {
                        Log.d(TAG, "Matrix : " + mMatrix.toString());
                    }
                    mBitmapPaint.setShader(mBitmapShader);
                    break;
                case 2:
                    mMatrix = new Matrix();
                    mMatrix.setTranslate(divider, divider * 2 + height);
                    if (AppProfile.DEBUG) {
                        Log.d(TAG, "Matrix : " + mMatrix.toString());
                    }
                    mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
                    mBitmapShader.setLocalMatrix(mMatrix);
                    mBitmapPaint.setShader(mBitmapShader);
                    break;
                case 3:
                    mMatrix = new Matrix();
                    mMatrix.setTranslate(divider, divider * 2 + height);
                    if (AppProfile.DEBUG) {
                        Log.d(TAG, "Matrix : " + mMatrix.toString());
                    }
                    mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.MIRROR, Shader.TileMode.CLAMP);
                    mBitmapShader.setLocalMatrix(mMatrix);
                    mBitmapPaint.setShader(mBitmapShader);
                    break;

            }
            if (what == 3) {
                what = -1;
            }
            what++;
            invalidate();
            sendEmptyMessageDelayed(what, 2000);
        }
    }


}
