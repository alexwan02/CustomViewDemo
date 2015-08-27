package com.alexwan.blog.circledemo.widget;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.alexwan.blog.circledemo.BuildConfig;
import com.alexwan.blog.circledemo.R;


/**
 * Created by 365 on 2015/7/23.
 */
public class CircleView extends Drawable {
    private Paint mPaint;
    //    private Paint mPaint1;
    private Context mContext;
//    private float mRadius = 100;
//    private int mPhase;

//    public CircleView(Context context) {
//        this(context, null);
//    }

    public CircleView(Context context, View parent) {
//        super(context, attrs);
        mContext = context;
        initPaint();
    }

    private void initPaint() {
        //
//        this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//        Path path = new Path();
//        path.addRect(0, 0, 8, 8, Path.Direction.CCW);
        mPaint = new Paint();
        // 抗锯齿
        mPaint.setAntiAlias(true);
        // 空心
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setShader(new SweepGradient(350, 350, Color.BLUE, Color.RED));
//        // 蓝色
//        mPaint.setColor(Color.argb(255, 255, 128, 103));
//        // 描边宽度
//        // 设置扫描测量
        mPaint.setStrokeWidth(30);
//        mPaint1 = new Paint();
//        // 抗锯齿
//        mPaint1.setAntiAlias(true);
//        // 空心
//        mPaint1.setStyle(Paint.Style.STROKE);
//        // 蓝色
//        mPaint1.setColor(Color.argb(0, 255, 128, 103));
//        // 描边宽度
//        // 设置扫描测量
//        mPaint1.setStrokeWidth(10);
//        animator = ObjectAnimator.ofFloat(this, "rotation", 0f, 360f);
//        animator.setInterpolator(new LinearInterpolator());
//        animator.setDuration(1000);
//        animator.setRepeatCount(-1);
//        animator.getPropertyName()
        if(mContext instanceof  Activity){
            WIDTH = mContext.getResources().getDisplayMetrics().widthPixels;
            HEGHT = mContext.getResources().getDisplayMetrics().heightPixels;
        }
        mBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.test);
        mBitmap = Bitmap.createScaledBitmap(mBitmap, getWidth(), getHeight(), true);
        mMatirx = new Matrix();
        sx = getWidth() / 2;
        sy = getHeight() / 2;
    }

    //    private ObjectAnimator animator;
//    private int startAngle = 0;
////    private int sweepAngle = 30;
    private Bitmap mBitmap;
    private Matrix mMatirx;
    private int WIDTH,HEGHT;
    private int sx;
    private int sy;
    @Override
    public void draw(Canvas canvas) {
//        super.onDraw(canvas);
        final int saveCount = canvas.save();
//        canvas.translate(0, 0);
        Matrix matrix = mMatirx;
        matrix.reset();
        float offsetX = getWidth();
        float offsetY = getHeight();
//        float sx = offsetX / 2;
//        float sy = offsetY / 2;
        matrix.preTranslate(offsetX / 4, offsetY / 4);
        matrix.preScale(0.5f, 0.5f);
        matrix.preTranslate(-offsetX / 2, -offsetY/2);
        matrix.postTranslate(100,100);
//        Display display = ((Activity) mContext).getWindowManager().getDefaultDisplay();
//        mPaint.setPathEffect(new DashPathEffect(new float[]{20, 10, 5, 10}, mPhase));
        //
//        Bitmap.Config config = Bitmap.Config.ARGB_4444;
//
//        Bitmap bitmap = Bitmap.createBitmap(canvas.getWidth(),canvas.getHeight(),config);
//        canvas.drawBitmap(bitmap,0,0,new Paint(Paint.ANTI_ALIAS_FLAG));
//        Canvas canvas2 = new Canvas(bitmap);
//        canvas2.drawColor(Color.WHITE);
        canvas.drawBitmap(mBitmap, matrix, null);
        canvas.restoreToCount(saveCount);
//        canvas.save();
//        RectF oval2 = new RectF(100, 100,
//                500, 500);
//        canvas.drawArc(oval2, 0, 359, false, mPaint);
//        canvas.restore();

//        int x = 0;
//        int y = 0 ;
//        if(mBitmap != null){
//             x = mBitmap.getWidth() / 2;
//             y = mBitmap.getHeight() / 2;
//        }
//
//        float radius = Math.min(x,y);
//        canvas.drawCircle(getWidth() / 2,getHeight() / 2,radius,mPaint);
//        canvas.drawArc(oval2, startAngle - 30, startAngle, false, mPaint1);
//            canvas.drawOval(oval2,30,180,false, mPaint);
//            canvas.drawCircle(display.getWidth() / 2, display.getHeight() / 2, mRadius, mPaint);
//        if(startAngle >= 60){
//            startAngle = 0;
//        }else {
//            startAngle += 30;
//        }
//        sweepAngle += 30;
//        mPhase += 1;
//        if(mHandler.getLooper() == null){

//        }
//        invalidate();
//        canvas.restore();
//        mPaint.cle
    }

    @Override
    public void setAlpha(int alpha) {

    }
    public int getHeight(){
        return mBitmap.getHeight();
    }
    public int getWidth(){
        return mBitmap.getWidth();
    }
    @Override
    public void setColorFilter(ColorFilter cf) {

    }

    @Override
    public int getOpacity() {
        return 0;
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        float dx = event.getX();
//        float dy = event.getY();
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//
//                if (dx > 0 && dx < mBitmap.getWidth() && dy > 0 && dy < mBitmap.getHeight()) {
//                    int index = mBitmap.getPixel((int) dx, (int) dy);
//                    if (BuildConfig.DEBUG) {
//                        String RGB = "red = " + Color.red(index) + "green = " + Color.green(index) + "blue = " + Color.blue(index);
//                        Log.i("CircleView", RGB);
//                    }
//                }
//                break;
//            default:
//                break;
//        }
//        return true;
//    }
    //    private Handler mHandler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            startAngle = (startAngle +30) % 360;
////            sweepAngle = (sweepAngle + 30) % 360;
//            mHandler.sendEmptyMessageDelayed(1,50);
//            super.handleMessage(msg);
//        }
//    };
//    @Override
//    public void run() {
////        postInvalidate();
//        animator.start();
//    }

}
