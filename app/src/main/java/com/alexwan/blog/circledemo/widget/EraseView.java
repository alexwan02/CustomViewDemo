package com.alexwan.blog.circledemo.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import com.alexwan.blog.circledemo.R;

/**
 * Created by 365 on 2015/7/24.
 */
public class EraseView extends View {
    // 前景，背景图
    private Bitmap mFgBitmap,mBgBitmap;
    // 画刷
    private Paint mPaint;
    // 路径
    private Path mPath;
    // 混合模式
    private PorterDuffXfermode mPorterDuffXfermode;
    //
    private Context mContext;
    // 屏幕宽度和高度
    private int mScreenWidth , mScreenHeight;
    // 上一个触摸事件的坐标
    private float mPreX,mPreY;
    // 画布
    private Canvas mCanvas;
    public EraseView(Context context) {
        this(context,null);
    }

    public EraseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        cal();
        init();
    }

    private void cal() {
        // 获取屏幕的参数
        if(mContext != null && mContext instanceof Activity){
            DisplayMetrics dm = new DisplayMetrics();

            ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(dm);
            // 屏幕宽度和高度
            mScreenWidth = dm.widthPixels;
            mScreenHeight = dm.heightPixels;
        }
    }

    private void init() {
        // 初始化
        // 画笔抗锯齿，抗抖动
        mPath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);

        // 设置画笔的透明度
        mPaint.setARGB(128, 255, 0, 0);
        // 设置过滤的模式
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        // 画笔宽度
        mPaint.setStrokeWidth(50);
        if(mScreenWidth > 0 && mScreenHeight>0){
            mFgBitmap = Bitmap.createBitmap(mScreenWidth,mScreenHeight, Bitmap.Config.ARGB_8888);
            // 注入画布
            mCanvas = new Canvas(mFgBitmap);
            // 设置画布颜色中性灰
            mCanvas.drawColor(0xFF808080);
            mBgBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher);
            //
            mBgBitmap = Bitmap.createScaledBitmap(mBgBitmap, mScreenWidth, mScreenHeight, true);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        try {
            // 绘制背景
            canvas.drawBitmap(mBgBitmap,0 , 0 , null);
            // 绘制前景
            canvas.drawBitmap(mFgBitmap,0 , 0 , null);
            //
            mCanvas.drawPath(mPath,mPaint);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private float MIN_MOVE_DIS = 5;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                mPath.moveTo(x, y);
                mPreX = x ;
                mPreY = y ;
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = Math.abs(x - mPreX);
                float dy = Math.abs(y - mPreY);
                if(dx > MIN_MOVE_DIS || dy > MIN_MOVE_DIS){
                    mPath.quadTo(mPreX , mPreY , (x + mPreX) / 2 , (x + mPreY)/2);
                    mPreX = x;
                    mPreY = y;
                }
                break;
            case MotionEvent.ACTION_UP:
                mPreX = x;
                mPreY = y;
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            default:
                break;
        }
        invalidate();
        return true;
    }
}
