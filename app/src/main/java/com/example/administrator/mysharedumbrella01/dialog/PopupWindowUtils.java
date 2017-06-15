package com.example.administrator.mysharedumbrella01.dialog;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.administrator.mysharedumbrella01.R;
import com.gyf.barlibrary.ImmersionBar;

import razerdp.basepopup.BasePopupWindow;


public class PopupWindowUtils extends BasePopupWindow implements View.OnClickListener {
    private View popupView; // 声明view

    public PopupWindowUtils(Activity activity) {
        super(activity);

        //初始化控件
        bindEvent();
    }
    /*初始化*/
    private void bindEvent() {
        if (popupView != null) {
            //这里初始化
        }
    }

    @Override
    protected Animation initShowAnimation() {
        return getTranslateAnimation(250 * 7, 0, 750);
    }

    @Override
    public View getClickToDismissView() {
        return popupView.findViewById(R.id.click_to_dismiss);
    }

    @Override
    public View onCreatePopupView() {
        popupView = LayoutInflater.from(getContext()).inflate(R.layout.btoom_dialog, null);


        return popupView;
    }

    @Override
    public View initAnimaView() {
        return popupView.findViewById(R.id.popup_anima);
    }

    @Override
    public void onClick(View view) {

    }

}
