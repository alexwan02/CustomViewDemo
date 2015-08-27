package com.alexwan.blog.circledemo.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by 365 on 2015/7/24.
 */
public class EmbossMaskFilterView extends View {
    private Paint mPaint;
    private Context mContext;
    private int width, height;
    private PointF[] mPointFs;
    private float coorY;
    private int H_COUNT = 5;
    private int V_COUNT = 5;

    public EmbossMaskFilterView(Context context) {
        this(context,null);
    }

    public EmbossMaskFilterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initPaint();
        cal();
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(0xFF603811);
        // 不设置硬件加速
        this.setLayerType(LAYER_TYPE_SOFTWARE, null);
        mPaint.setMaskFilter(new EmbossMaskFilter(new float[]{1, 1, 1f}, 0.1f, 10f, 20f));
    }

    private void cal() {
        if (mContext != null && mContext instanceof Activity) {
            DisplayMetrics dm = new DisplayMetrics();
            ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(dm);
            width = dm.widthPixels / H_COUNT;
            height = dm.heightPixels / V_COUNT;
        }
        int size = H_COUNT * V_COUNT;
        mPointFs = new PointF[size];
        for (int i = 0; i < size; i++) {
            if (i % 5 == 0) {
                coorY = (i/5f) * height;
                mPointFs[i] = new PointF(0, coorY);
            } else if (i % 5 == 1) {
                mPointFs[i] = new PointF(width, coorY);
            } else if (i % 5 == 2) {
                mPointFs[i] = new PointF(width * 2, coorY);
            } else if (i % 5 == 3) {
                mPointFs[i] = new PointF(width * 3, coorY);
            } else{
                mPointFs[i] = new PointF(width * 4, coorY);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.RED);
        int size = H_COUNT * V_COUNT;
        for (int i = 0; i < size; i++) {
            canvas.drawRect(mPointFs[i].x, mPointFs[i].y, mPointFs[i].x + width, mPointFs[i].y + height, mPaint);
        }
    }
}
