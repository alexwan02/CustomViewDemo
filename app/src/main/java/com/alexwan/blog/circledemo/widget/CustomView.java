package com.alexwan.blog.circledemo.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

/**
 * Created by alexwan on 15/9/14.
 */
public class CustomView extends View {
    private Paint paint;
    private int width , height;
    private int radius = 10;
    private static String TAG = CustomView.class.getName();
    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        initSize();
    }

    private void initSize() {
        DisplayMetrics dm = new DisplayMetrics();
        if(getContext() instanceof Activity){
            ((Activity)getContext()).getWindowManager().getDefaultDisplay().getMetrics(dm);
        }
        width = dm.widthPixels;
        height = dm.heightPixels;
        Log.d(TAG , "width = " + width + " , height = "+ height);
    }

    private void initPaint() {
        // 开启抗锯齿
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // or Paint paint = new Paint();
        // paint.setAntiAlias(true); 同样解决抗锯齿的问题
        // 画笔颜色
        paint.setColor(Color.GRAY);
        /**
         * 画笔三种Style
         * STROKE ：描边
         * FILL_AND_STROKE : 描边并填充
         * FILL ：填充
         */
        paint.setStyle(Paint.Style.STROKE);
        // 画笔宽度
        paint.setStrokeWidth(10);
        /***--动态圆--***/
//        handler.sendEmptyMessageDelayed(1, 60);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /***--动态圆--***/
        canvas.drawCircle(width / 2, height / 2, radius, paint);
    }
    /***--动态圆--***/
//    private Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            if(radius <= 200){
//                radius += 10;
//            }else{
//                radius = 10;
//            }
//            // 每500毫秒发送一个消息
//            Log.d(TAG , "radius : " + radius);
//            // 半径改变后 调用invalidate(); 重新绘制
//            invalidate();
//            handler.sendEmptyMessageDelayed(1 , 60);
//            super.handleMessage(msg);
//        }
//    };
}

