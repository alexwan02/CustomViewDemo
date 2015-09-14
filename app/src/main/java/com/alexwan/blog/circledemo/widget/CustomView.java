package com.alexwan.blog.circledemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by alexwan on 15/9/14.
 */
public class CustomView extends View {
    private Paint piant;

    public CustomView(Context context) {
        this(context , null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }
    private void initPaint() {
        // 开启抗锯齿
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // or Paint paint = new Paint();
        // paint.setAntiAlias(true); 同样解决抗锯齿的问题
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
