package com.alexwan.blog.circledemo.widget;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.os.Debug;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.alexwan.blog.circledemo.R;
import com.alexwan.blog.circledemo.help.AppProfile;
import com.alexwan.blog.circledemo.util.ScreenUtil;


/**
 * BitmapShaderDemo
 */
public class ShaderDemo extends View {
    private int[] screenSize;
    private Paint mBitmapPaint, mSweepPaint, mLinearPaint;
    private Bitmap mBitmap;
    private static final int RECT_SIZE = 400;
    private int width, height, divider;
    private int screenWidth, screenHeight;
    private Matrix mMatrix;
    private static final String TAG = ShaderDemo.class.getSimpleName();
    private BitmapShader mBitmapShader;

    public ShaderDemo(Context context) { this(context, null); }

    public ShaderDemo(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        screenSize = ScreenUtil.getScreenSize(context);
        mBitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        // 线性
        mLinearPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        // 梯度
        mSweepPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        //
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), R.drawable.saber, options);
        options.inSampleSize = 8;
        options.inJustDecodeBounds = false;
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.saber, options); /**/
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
                .YELLOW,
                Shader.TileMode.REPEAT));
        /* SweepGradient：梯度渐变*/
        mSweepPaint.setShader(
                new SweepGradient(screenSize[0] - width / 2 - divider, divider + height / 2, Color.RED, Color
                        .YELLOW)); /**/
        mHander.sendEmptyMessageDelayed(0, 500);
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
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        MeasureSpec.getMode(widthMeasureSpec);
        MeasureSpec.getSize(widthMeasureSpec);
        MeasureSpec.getMode(heightMeasureSpec);
        MeasureSpec.getSize(heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private Handler mHander = new Handler() {
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
                    if(AppProfile.DEBUG){
                        Log.d(TAG , "Matrix : "+ mMatrix.toString() );
                    }
                    mBitmapPaint.setShader(mBitmapShader);
                    break;
                case 1:
                    mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
                    mMatrix = new Matrix();
                    mMatrix.setTranslate(divider, divider * 2 + height);
                    mBitmapShader.setLocalMatrix(mMatrix);
                    if(AppProfile.DEBUG){
                        Log.d(TAG , "Matrix : "+ mMatrix.toString() );
                    }
                    mBitmapPaint.setShader(mBitmapShader);
                    break;
                case 2:
                    mMatrix = new Matrix();
                    mMatrix.setTranslate(divider, divider * 2 + height);
                    if(AppProfile.DEBUG){
                        Log.d(TAG , "Matrix : "+ mMatrix.toString() );
                    }
                    mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
                    mBitmapShader.setLocalMatrix(mMatrix);
                    mBitmapPaint.setShader(mBitmapShader);
                    break;
                case 3:
                    mMatrix = new Matrix();
                    mMatrix.setTranslate(divider, divider * 2 + height);
                    if(AppProfile.DEBUG){
                        Log.d(TAG , "Matrix : "+ mMatrix.toString() );
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
    };
}
