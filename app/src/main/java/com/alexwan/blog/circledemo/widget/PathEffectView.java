package com.alexwan.blog.circledemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 365 on 2015/7/27.
 */
public class PathEffectView extends View {
    private float mPhase; // 偏移值
    private Path mPath; // 路径
    private Paint mPaint; // 画笔
    private PathEffect[] mPathEffect;
    public PathEffectView(Context context) {
        this(context , null);
    }

    public PathEffectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 初始画笔
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(20);
        mPaint.setColor(Color.BLACK);
        // 实例化路径
        mPath = new Path();
        mPath.moveTo(0,0);
        for (int i = 0 ; i < 30; i ++){
            mPath.lineTo(i*35, (float) (Math.random()*100));
        }
        //
        mPathEffect = new PathEffect[7];
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 定个效果
        mPathEffect[0] = null;
        mPathEffect[1] = new CornerPathEffect(10);
        mPathEffect[2] = new DiscretePathEffect(3.0f , 5.0f);
        mPathEffect[3] = new DashPathEffect(new float[]{20,10,5,10},mPhase);
        Path path = new Path();
        path.addRect(0, 0, 8, 8, Path.Direction.CCW);
        mPathEffect[4] = new PathDashPathEffect(path, 12, mPhase, PathDashPathEffect.Style.ROTATE);
        mPathEffect[5] = new ComposePathEffect(mPathEffect[2], mPathEffect[4]);
        mPathEffect[6] = new SumPathEffect(mPathEffect[4], mPathEffect[3]);
        for (int i = 0 ; i < mPathEffect.length ; i++){
            mPaint.setPathEffect(mPathEffect[i]);
            canvas.drawPath(mPath,mPaint);

            // 向下移动250
            canvas.translate(0 , 120);
        }
        //
        mPhase += 1;
        invalidate();
    }
}
