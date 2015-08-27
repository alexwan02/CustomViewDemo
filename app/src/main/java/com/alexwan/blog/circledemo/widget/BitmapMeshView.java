package com.alexwan.blog.circledemo.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.alexwan.blog.circledemo.R;

/**
 * Created by 365 on 2015/7/29.
 */
public class BitmapMeshView extends ImageView {
    private static final int WIDTH = 9, HEIGHT = 9;
    private static final int COUNT = (WIDTH + 1) * (HEIGHT + 1);
    private float[] matrixOrginal = new float[COUNT * 2];
    private float[] matrixMoved = new float[COUNT * 2];
    private Paint origPaint, movePaint, linePaint;
    private float clickX, clickY;
    private Bitmap mBitmap;

    public BitmapMeshView(Context context) {
        this(context, null);
        //
//        setClickable(true);

        //
        //
    }

    public BitmapMeshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        setFocusable(true);
        // 初始画笔
        origPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        origPaint.setColor(0x660000FF);
        movePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        movePaint.setColor(0x99FF0000);
        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        linePaint.setColor(0xFFFFFB00);
        // 获取位图资源
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test);
        int index = 0;
        for (int y = 0; y <= HEIGHT; y++) {
            float dy = mBitmap.getHeight() / HEIGHT * y;
            for (int x = 0; x <= WIDTH; x++) {
                float dx = mBitmap.getWidth() / HEIGHT * x;
                setXy(matrixOrginal, index, dx, dy);
                setXy(matrixMoved, index, dx, dy);
                index += 1;
            }

        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmapMesh(mBitmap, WIDTH, HEIGHT, matrixMoved, 0, null, 0, null);
        //
        drawGuide(canvas);
    }

    private void drawGuide(Canvas canvas) {
        for (int i = 0; i < COUNT * 2; i += 2) {
            // 以原点绘制半径为4的○
            float x = matrixOrginal[i + 0];
            float y = matrixOrginal[i + 1];
//            canvas.drawCircle(x, y, 3, origPaint);
            // 绘制偏移路线
            float x1 = matrixOrginal[i + 0];
            float y1 = matrixOrginal[i + 1];
            float x2 = matrixMoved[i + 0];
            float y2 = matrixMoved[i + 1];
//            canvas.drawLine(x1, y1, x2, y2, linePaint);
        }
        for (int i = 0; i < COUNT * 2; i += 2) {
            // 绘制偏移后的圆
            float x = matrixMoved[i + 0];
            float y = matrixMoved[i + 1];
//            canvas.drawCircle(x, y, 4, movePaint);
        }
        // 绘制触点
//        canvas.drawCircle(clickX, clickY, 6, linePaint);
    }

    private void setXy(float[] array, int index, float x, float y) {
        array[index * 2 + 0] = x;
        array[index * 2 + 1] = y;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 获取当前触点
        clickX = event.getX();
        clickY = event.getY();
        // 计算偏移
        smudge();
        invalidate();
        return true;
    }

    private void smudge() {
        for (int i = 0; i < COUNT * 2; i += 2) {
            // 获取原始点的坐标
            float origX = matrixOrginal[i + 0];
            float origY = matrixOrginal[i + 1];
            // 获取坐标差值
            float dist_click_to_origin_x = clickX - origX;
            float dist_click_to_origin_y = clickY - origY;
            float kv_kat = dist_click_to_origin_x * dist_click_to_origin_x + dist_click_to_origin_y * dist_click_to_origin_y;
            float pull = (float)(1000000 / kv_kat / Math.sqrt(kv_kat));
            if(pull >= 1){
                matrixMoved[i + 0] = clickX;
                matrixMoved[i + 1] = clickY;
            }else {
                matrixMoved[i + 0] = pull * dist_click_to_origin_x + origX;
                matrixMoved[i + 1] = pull * dist_click_to_origin_y + origY;

            }
        }
    }

}
