package com.alexwan.blog.circledemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 365 on 2015/8/12.
 */
public class CircleDemoView extends View {
    private CircleView mDrawable;
    public CircleDemoView(Context context) {
        this(context,null);
    }
    public CircleDemoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mDrawable = new CircleView(getContext(),this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int height = mDrawable. * 5 / 4;
        mDrawable.getHeight() ;
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(mDrawable.getHeight() + getPaddingTop() + getPaddingBottom(), MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int pl = getPaddingLeft();
        int pt = getPaddingTop();
        mDrawable.setBounds(pl, pt, pl + right - left, pt + bottom - top);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mDrawable.draw(canvas);
    }
}
