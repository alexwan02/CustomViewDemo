package com.alexwan.blog.circledemo;

import android.animation.ObjectAnimator;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.TextView;

import com.alexwan.blog.circledemo.widget.CircleView;

public class MainActivity extends AppCompatActivity {
    private CircleView circleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        circleView = (CircleView) findViewById(R.id.circle);

//        ObjectAnimator animator = ObjectAnimator.ofFloat(circleView, "rotation", 0f, 360f);
//        animator.setInterpolator(new LinearInterpolator());
//        animator.setDuration(1000);
//        animator.setRepeatCount(-1);
//        animator.start();
//        new Thread(circleView).start();
//        TextView tv = (TextView) findViewById(R.id.text_view);
//        Typeface typeface = Typeface.createFromAsset(this.getAssets(),"font/NotoSans-Italic.ttf");
//        TextPaint paint = tv.getPaint();
//        paint.setStrikeThruText(true);
//        paint.setTypeface(typeface);
//        handler.sendEmptyMessage(1);
//        handler.post(new Thread(circleView){
//            @Override
//            public void run() {
//                super.run();
//
//            }
//        });
    }
//    private Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
////            new Thread(circleView).start();
//        }
//    };

}
