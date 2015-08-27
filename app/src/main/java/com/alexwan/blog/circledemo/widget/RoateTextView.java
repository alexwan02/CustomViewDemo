package com.alexwan.blog.circledemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by 365 on 2015/8/27.
 */
public class RoateTextView extends TextView {
    private Matrix matrix;
    private Paint mPaint;
    private TextPaint mTexPaint;
    private Path mPath;

    public RoateTextView(Context context) {
        this(context, null);
    }

    public RoateTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        matrix = new Matrix();
        matrix.postRotate(45, getWidth() / 2, getHeight() / 2);
        mPaint = new Paint();
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.FILL);
        mPath = new Path();
        mTexPaint = super.getPaint();
        mTexPaint.getFontMetrics();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPath.reset();
        canvas.save();
        canvas.rotate(45, getWidth(), getHeight() * 5 / 8);
        mPath.moveTo(0, getHeight() * 5 / 8);
        mPath.lineTo(getWidth() / 2, getHeight() * 1 / 8);
        mPath.lineTo(getWidth(), getHeight() * 5 / 8);
        canvas.drawPath(mPath, mPaint);
        super.onDraw(canvas);
        canvas.restore();
    }
}
