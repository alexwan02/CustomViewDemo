package com.alexwan.blog.circledemo.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.alexwan.blog.circledemo.R;

/**
 * Created by 365 on 2015/8/12.
 */
public class CircleDemoView extends View {
    private static final String TAG = CircleDemoView.class.getSimpleName();
    private Bitmap mDrawable;
    private Paint mPaint;
    private int width , height;
    public CircleDemoView(Context context) {
        this(context, null);
    }

    public CircleDemoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initSize(context);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setColor(Color.BLUE);
        mPaint.setColorFilter(new LightingColorFilter(0xFFFF00FF, 0x000000000));
        mDrawable = BitmapFactory.decodeResource(context.getResources() , R.drawable.test);

    }

    private void initSize(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        if(context instanceof Activity){
            ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        }
        width = dm.widthPixels;
        height = dm.heightPixels;
        Log.d(TAG, "width = " + width + " , height = " + height);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.setMeasuredDimension(widthMeasureSpec , heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mDrawable , width / 2 - mDrawable.getWidth() / 2, height / 2 - mDrawable.getHeight() / 2 ,
                mPaint);
    }
}
