package com.alexwan.blog.circledemo.widget;

//import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
//import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by 365 on 2015/7/30.
 */
public class PathView extends View {
    // 路径
    private Path mPath , mCirclePath;
//    private float dx , dy;
//    private float currentX , currentY;
    // 画笔
    private Paint mPaint;
    // 圆的画笔
    private Paint mCirclePaint;
//    private Context mContext;
//    private static float HEIGHT = 100;
    public PathView(Context context) {
        this(context,null);
    }

    public PathView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        mContext = context;
//        cal();
        init();
    }
//    private void cal() {
//        if(mContext instanceof Activity){
//            //
//            DisplayMetrics dm = new DisplayMetrics();
//            ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(dm);
//            HEIGHT = dm.heightPixels;
//        }
//    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void init() {
        // 初始画笔和路径
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
        mPaint.setColor(Color.RED);
        // 空心
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        //TODO
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
        mCirclePaint.setColor(Color.GRAY);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(5);

        //TODO
        mPath = new Path();
        mCirclePath = new Path();

        oval = new RectF(0,0,getWidth(),getHeight());

    }
    private RectF oval;
    private float startAngle = 0;
    private float sweepAngle = 90;
//    private float increase = 5;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        mPath.reset();
//        mPath.moveTo(100,100);
//        mPath.quadTo(dx, dy, 500,100);
        mCirclePath.arcTo(oval, startAngle, 359, true);
        mPath.arcTo(oval, startAngle, sweepAngle, true);
//        canvas.save();
        canvas.drawPath(mCirclePath, mCirclePaint);
//        canvas.restore();
        canvas.drawPath(mPath, mPaint);
//        canvas.drawCircle(150,150,50,mCirclePaint);
        startAngle += 5;
//        increase += 5;
//        sweepAngle += 5;
        mCirclePath.reset();
        mPath.reset();
        invalidate();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                // 设置触摸点
//                dx = event.getX();
//                dy = event.getY();
//                break;
//            case MotionEvent.ACTION_MOVE:
////                event.getX();
//                currentY = event.getY();
//                if(currentY > dy && currentY > 10 && (currentY - dy) < HEIGHT){
//                    sweepAngle = (currentY - dy) / HEIGHT * 360;
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//                sweepAngle = 0;
//                break;
//            default:
//                break;
//        }
//        dx = event.getX();
//        dy = event.getY();
//        invalidate();
        return true;
    }
}
