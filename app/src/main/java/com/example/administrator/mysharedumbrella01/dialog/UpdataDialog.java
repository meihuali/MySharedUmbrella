package com.example.administrator.mysharedumbrella01.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.text.method.MovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.utils.UpdataProgressBar;
import com.google.zxing.common.detector.WhiteRectangleDetector;

/**
 * Created by Administrator on 2017/6/26 0026.
 * 主界面弹出广告 提示让用户更新APP
 */

public class UpdataDialog extends Dialog {
    public static UpdataDialog dialog;
    public static Button btn_cancel;
    public static Button btn_confirm;
    public static UpdataProgressBar round_flikerbar;

    public UpdataDialog(Context context) {
        super(context);
    }

    public UpdataDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    /*
    * cancelable 是否按返回键取消
    * cancelListener 按返回键监听
    * */
    public static UpdataDialog show(Context context, CharSequence message, String title,
                                    int shifougengxin, boolean cancelanle, OnCancelListener cancelListener) {

        dialog = new UpdataDialog(context, R.style.updatadialog);
        dialog.setTitle("");
        dialog.setContentView(R.layout.activity_updatadialog);
        if (message == null || message.length() == 0) {
            dialog.findViewById(R.id.tv_content).setVisibility(View.GONE);
        } else {//否则就设置更新了哪些内容
            TextView tx = (TextView) dialog.findViewById(R.id.tv_content);
            tx.setMovementMethod(new ScrollingMovementMethod()); //new 这个对象可以让text文字 在里面滚动
            tx.setGravity(Gravity.CENTER);
            tx.setText(message);
        }
        TextView tv_update_title = (TextView) dialog.findViewById(R.id.tv_update_title);
        if (title.equals("") || title.length() == 0) {
            tv_update_title.setText(R.string.gongxiangyushangengxinneirong);
        } else {
            tv_update_title.setText(title);
        }
        btn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);
        btn_confirm = (Button) dialog.findViewById(R.id.btn_confirm);
        //初始化一个进度条·
        round_flikerbar = (UpdataProgressBar)dialog.findViewById(R.id.round_flikerbar);
        if (shifougengxin == 0) {
            btn_cancel.setVisibility(View.VISIBLE);
        } else {
            btn_cancel.setVisibility(View.GONE);
        }
        //按返回键是否取消
        dialog.setCancelable(cancelanle);
        //监听返回键处理
        dialog.setOnCancelListener(cancelListener);
        //设置居中
        dialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        //回去屏幕宽高用
        Display display = wm.getDefaultDisplay();
        //获取对话框当前的参数值
        WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
        //宽度设置为 整个屏幕宽度
        wmlp.width = (int) (display.getWidth() * 0.8);
        //高度设置为 屏幕的 0.7
        wmlp.height = (int) (display.getHeight() * 0.6);
        //设置对话框的 透明度
        wmlp.alpha = 1f;
        dialog.getWindow().setAttributes(wmlp);
        dialog.show();

        return dialog;
    }

    /*
    * 隐藏dialog
    * */
    public static void dialogcancle() {
        dialog.cancel();
    }

    /*
    * 设置对话框 外部点击事件是否为关闭 true 为关闭，false为不关闭
    * */
    public static void setOutside(boolean flag) {
        dialog.setCanceledOnTouchOutside(flag);
    }
}
