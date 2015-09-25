package com.sanmubai.mobilesafe.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * 设置页面的基类
 * Created by bb on 2015/9/25.
 */
public abstract class SettingBaseActivity extends Activity {

    private GestureDetector gestureDetector;
    public SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sp=getSharedPreferences("settingConfig",MODE_PRIVATE);
        //设置触摸手势监听
        gestureDetector = new GestureDetector(this, new GestureDetector
                .SimpleOnGestureListener() {
            /**
             *
             * @param e1 滑动起点
             * @param e2 滑动终点
             * @param velocityX 滑动水平速度
             * @param velocityY 滑动垂直速度
             * @return
             */
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float
                    velocityY) {

                //左滑原理 终点的x坐标 小于 起点的x 坐标，距离设为100. 如果在y方向滑动距离大于100.则不允许。
                if(Math.abs(e1.getRawY()-e2.getRawY())>150){
                    System.out.println("y方向滑动太过分，不做处理。");
                    return true;
                }

                //如果滑动太慢，提示。

                if (Math.abs(velocityX)<100){
                    System.out.println("滑动太慢");
                    return true;
                }

                //下一页
                if ((e1.getRawX()-e2.getRawX())>100){
                    System.out.println("下一页");
                    showNext();
                    return true;
                }

                //上一页
                if ((e2.getRawX()-e1.getRawX())>100){
                    System.out.println("上一页");
                    showPrevious();
                    return true;
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });
    }

    public void next(View view) {
        showNext();
    }

    public void previous(View view) {
        showPrevious();
    }

    public abstract void showNext();
    public abstract void showPrevious();

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //委托手势识别器来处理触摸事件
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
