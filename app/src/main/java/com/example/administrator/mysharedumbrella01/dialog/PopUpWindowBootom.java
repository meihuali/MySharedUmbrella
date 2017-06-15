package com.example.administrator.mysharedumbrella01.dialog;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Toast;

import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.wxapi.WxPayUtils;

import razerdp.basepopup.BasePopupWindow;

/**
 * Created by Administrator on 2017/6/7 0007.
 */

public class PopUpWindowBootom extends BasePopupWindow implements View.OnClickListener {
    private View popupView;
    private Activity activity;
    public PopUpWindowBootom(Activity context) {
        super(context);
        activity = context;

        bindEvent();
    }

    @Override
    protected Animation initShowAnimation() {
        return getTranslateAnimation(250 * 2, 0, 300);
    }

    @Override
    public View getClickToDismissView() {
        return popupView.findViewById(R.id.click_to_dismiss);
    }

    @Override
    public View onCreatePopupView() {
        popupView = LayoutInflater.from(getContext()).inflate(R.layout.popup_slide_from_bottom, null);
        return popupView;
    }

    @Override
    public View initAnimaView() {
        return popupView.findViewById(R.id.popup_anima);
    }

    private void bindEvent() {
        if (popupView != null) {
            popupView.findViewById(R.id.tx_1).setOnClickListener(this);
            popupView.findViewById(R.id.tx_2).setOnClickListener(this);
            popupView.findViewById(R.id.tx_3).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tx_1:
                Toast.makeText(activity,"等待服务器接口···",Toast.LENGTH_SHORT).show();
                //点击按钮 实例化掉起支付接口的 类
                WxPayUtils wpu = new WxPayUtils(activity);
                //  wpu.pay_wechat();
                break;
            case R.id.tx_2:
                Toast.makeText(activity,"待开发中···",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tx_3:
                Toast.makeText(activity,"待开发中···",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

    }

}
