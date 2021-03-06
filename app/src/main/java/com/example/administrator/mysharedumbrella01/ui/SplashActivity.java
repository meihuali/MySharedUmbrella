package com.example.administrator.mysharedumbrella01.ui;

/*
 *  项目名：  SmartButler 
 *  包名：    com.imooc.smartbutler.ui
 *  文件名:   SplashActivity
 *  创建者:   LGL
 *  创建时间:  2016/10/28 22:51
 *  描述：    闪屏页
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.example.administrator.mysharedumbrella01.utils.StaticClass;
import com.example.administrator.mysharedumbrella01.utils.UtilTools;
import com.gyf.barlibrary.BarHide;
import com.gyf.barlibrary.ImmersionBar;


public class SplashActivity extends AppCompatActivity {

    /**
     * 1.延时2000ms
     * 2.判断程序是否第一次运行
     * 3.自定义字体
     * 4.Activity全屏主题
     */

    private TextView tv_splash;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case StaticClass.HANDLER_SPLASH:
                    //判断程序是否是第一次运行
                    if (isFirst()) {
                        startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    } else {
                        startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    }
                    finish();
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //沉浸式
        ImmersionBar.with(this).fullScreen(true).hideBar(BarHide.FLAG_HIDE_BAR)
                .init();
        initView();
    }
    //初始化View
    private void initView() {
        //延时2000ms
        handler.sendEmptyMessageDelayed(StaticClass.HANDLER_SPLASH, 3000);
    }

    //判断程序是否第一次运行
    private boolean isFirst() {
        //这里暂时写死保存为true
       // ShareUtils.putBoolean(SplashActivity.this, StaticClass.SHARE_IS_FIRST,true);
        boolean isFirst = ShareUtils.getBoolean(this,StaticClass.SHARE_IS_FIRSTS,true);
        if(isFirst){
            ShareUtils.putBoolean(this,StaticClass.SHARE_IS_FIRSTS,false);
            //是第一次运行
            return true;
        }else {
            return false;
        }

    }

}
