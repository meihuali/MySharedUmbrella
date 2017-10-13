package com.example.administrator.mysharedumbrella01.utils;

import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.utils
 * 文件名：MyDialog
 * 创建者 ：梅华黎
 * 创建时间： 2017/10/13 0013 15:02
 * 描述：TODO
 */
public class MyDialog {
    public static void dialog(String title,String body,String confirm,String cancel) {
        StyledDialog.buildIosAlert(title, body, new MyDialogListener() {
            @Override
            public void onFirst() {
            }
            @Override
            public void onSecond() {
            }
        }).setBtnText(confirm,cancel).show();
    }
}
