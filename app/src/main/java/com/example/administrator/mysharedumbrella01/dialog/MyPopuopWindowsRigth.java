package com.example.administrator.mysharedumbrella01.dialog;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.ui.MainActivity;
import com.example.administrator.mysharedumbrella01.ui.ShangChuanLocation;
import com.example.administrator.mysharedumbrella01.ui.UsagelogActivity;

import razerdp.basepopup.BasePopupWindow;

/**
 * Created by Administrator on 2017/5/24 0024.
 */

public class MyPopuopWindowsRigth extends BasePopupWindow implements View.OnClickListener {
    private TextView tx1;
    private TextView tx2;
    private TextView tx3,tx4;
    private  AMap aMap;
    private MainActivity activity;
    private boolean isFrest;
    public MyPopuopWindowsRigth(MainActivity activity,  AMap aMap) {
        super(activity, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.aMap = aMap;
        this.activity = activity;
 /*       findViewById(R.id.tx_1).setOnClickListener(this); */
        findViewById(R.id.tx_2).setOnClickListener(this);
        tx3 = (TextView) findViewById(R.id.tx_3);
        tx3.setOnClickListener(this);
        tx4 = (TextView) findViewById(R.id.tx_4);
        tx4.setOnClickListener(this);
    }

    @Override
    protected Animation initShowAnimation(){
        AnimationSet set = new AnimationSet(true);
        set.setInterpolator(new DecelerateInterpolator());
        set.addAnimation(getScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 1, Animation.RELATIVE_TO_SELF, 0));
        set.addAnimation(getDefaultAlphaAnimation());
        return set;
    }

    @Override
    public View getClickToDismissView() {
        return null;
    }

    @Override
    public View onCreatePopupView() {
        return createPopupById(R.layout.popup_menu);
    }

    @Override
    public View initAnimaView() {
        return null;
    }

    /*这个方法是 popupwindow 显示在具体 什么位子 ·这里是显示右上角*/
    @Override
    public void showPopupWindow(View v) {

        setOffsetX(-(getPopupViewWidth() - v.getWidth() / 2));
        int h = v.getHeight();
        int w = v.getWidth();
        setOffsetY(v.getHeight()+18);
        super.showPopupWindow(v);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tx_2:
                activity.startActivity(new Intent(activity,UsagelogActivity.class));
                break;

            case R.id.tx_4:
                activity.startActivity(new Intent(activity, ShangChuanLocation.class));
                break;
        }
    }
}
