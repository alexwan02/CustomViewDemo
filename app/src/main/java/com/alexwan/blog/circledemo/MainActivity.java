package com.alexwan.blog.circledemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.alexwan.blog.circledemo.widget.CustomView;


public class MainActivity extends AppCompatActivity {
//    private CustomView customView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        customView = (CustomView) findViewById(R.id.custom_view);
//        handler.sendEmptyMessage(1);

    }
//    private Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            // 每500毫秒发送一个消息
////            handler.sendEmptyMessageDelayed(1 , 500);
//        }
//    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // activity销毁的时候清除handler的引用
//        handler.removeCallbacksAndMessages(null);
    }
}
