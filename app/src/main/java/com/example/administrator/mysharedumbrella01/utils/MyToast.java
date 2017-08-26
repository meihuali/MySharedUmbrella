package com.example.administrator.mysharedumbrella01.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.JinupUtils
 * 文件名：Toast
 * 作者 ：梅华黎
 * 创建时间： 2017/7/31 0031  下午 6:09
 * 描述：
 */
public class MyToast {
    public static void toast(Context activity, String text) {
        Toast.makeText(activity,text,Toast.LENGTH_SHORT).show();
    }
}
