package com.alexwan.blog.circledemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.alexwan.blog.circledemo.BuildConfig;

/**
 * Created by 365 on 2015/7/24.
 */
public class FontView extends View {
    private static final String TXT = "alexwan维护ξτβбпшㄎㄊěǔぬも┰┠№＠↓asdv;kiewtwsn, kdhqiwuehqn";
    private Paint mPaint;
    private TextPaint mTextPaint;
    private Paint mLinePaint;
    private Paint.FontMetrics mFontMetrics;
    private static final String TAG = "com.alexwan.blog.circledemo";
    private int baseX , baseY;
    private StaticLayout mStaticLayout;
    private Context mContext;
    public FontView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        cal();
        initPaint();

    }

    private void cal() {

    }

    private void initPaint() {
        Typeface typeface = Typeface.createFromAsset(mContext.getAssets(),"font/NotoSans-Italic.ttf");
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(50);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTypeface(typeface);
        mTextPaint.setStrikeThruText(true);
//        mTextPaint.setStyle(Paint.Style.FILL);
//        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        mPaint.setTextSize(50);
//        mPaint.setColor(Color.BLACK);
//        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        mLinePaint.setStrokeWidth(1);
//        mLinePaint.setColor(Color.RED);
//        mLinePaint.setStyle(Paint.Style.STROKE);
        mFontMetrics = mTextPaint.getFontMetrics();
        if(BuildConfig.DEBUG){
            Log.i(TAG,"FontView ascent :"+mFontMetrics.ascent);
            Log.i(TAG,"FontView top :"+mFontMetrics.top);
            Log.i(TAG,"FontView leading :"+mFontMetrics.leading);
            Log.i(TAG,"FontView descent :"+mFontMetrics.descent);
            Log.i(TAG,"FontView bottom :"+mFontMetrics.bottom);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        baseX = (int)(canvas.getWidth() /2 - mPaint.measureText(TXT) / 2);
//        baseY = (int)((canvas.getHeight() / 2) + (mPaint.descent() / 2));
        mStaticLayout = new StaticLayout(TXT, mTextPaint, canvas.getWidth(), Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, false);
        mStaticLayout.draw(canvas);
        canvas.restore();
//        baseY = (int)((canvas.getHeight() / 2) - ((mPaint.descent() + mPaint.ascent()) / 2));
//        canvas.drawColor(Color.GRAY);
//        canvas.drawText(TXT,baseX,baseY,mPaint);
//        canvas.drawLine(0,canvas.getHeight()/2,canvas.getWidth(),canvas.getHeight() /2 ,mLinePaint);
    }
}
