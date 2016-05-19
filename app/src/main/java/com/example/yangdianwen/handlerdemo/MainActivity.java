package com.example.yangdianwen.handlerdemo;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private int index;
    //图片资源
    private int[] images = {R.mipmap.ic_launcher, R.mipmap.and, R.mipmap.logo_5};
    //动画资源
    private int[]animation={R.anim.alpha,R.anim.scale,R.anim.translate};
    private ImageView imageView;
    MyRunnable myRunnable = new MyRunnable();
    private Handler mhandler = new Handler();

   //自定义类实现Runnable接口重写run方法
   class MyRunnable implements Runnable {
        @Override
        public void run() {
            //变量index进行初始化
            index++;
            //index对images数组的长度取余实现了图片之间不停切换效果（类似循环）。
            index = index % 3;
            imageView.setImageResource(images[index]);
            Animation anim= AnimationUtils.loadAnimation(MainActivity.this,animation[index]);
            imageView.setAnimation(anim);
            //使用Handler对象调用postDelayed方法，实现了延时效果。
            mhandler.postDelayed(myRunnable, 1500);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.image);
        //在UI线程中使用Handler对象调用postDelayed方法实现图片的切换。
        mhandler.postDelayed(myRunnable, 1000);
    }
}
