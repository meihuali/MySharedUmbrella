package com.example.administrator.mysharedumbrella01.dialog;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;


import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.ui.KeHuFuWuActivity;
import com.example.administrator.mysharedumbrella01.ui.QiTaWenTiAcitivity;

import razerdp.basepopup.BasePopupWindow;

/**
 * Created by Administrator on 2017/6/16 0016.
 *   用户反馈 广告
 */

public class PopupWindowBotoom extends BasePopupWindow implements View.OnClickListener{
    private View popupView;
    private Activity activity;
    private ImageView weixin;
    private ImageView zhifubao;

    public PopupWindowBotoom(Activity activity) {
        super(activity);
        this.activity = activity;
        bindEvent();
    }



    @Override
    protected Animation initShowAnimation() {
        return getDefaultScaleAnimation();
    }


    @Override
    public View getClickToDismissView() {
        return popupView.findViewById(R.id.click_to_dismiss);
    }

    @Override
    public View onCreatePopupView() {
        popupView= LayoutInflater.from(getContext()).inflate(R.layout.popup_norma,null);
        return popupView;
    }

    @Override
    public View initAnimaView() {
        return popupView.findViewById(R.id.popup_anima);
    }

    private void bindEvent() {
        if (popupView!=null){
            /*  这里初始化控件*/
            popupView.findViewById(R.id.ll_layout_kaibuqisuo).setOnClickListener(this);
            popupView.findViewById(R.id.ll_layout_guzhang).setOnClickListener(this);
            popupView.findViewById(R.id.ll_layout_jubao).setOnClickListener(this);
            popupView.findViewById(R.id.ll_layout_qitawenti).setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //开不了锁
            case R.id.ll_layout_kaibuqisuo:
                Intent i1 = new Intent(activity, KeHuFuWuActivity.class);
                i1.putExtra("fuwu","1");
                activity.startActivity(i1);
                dismiss();
                break;
            //故障
            case R.id.ll_layout_guzhang:
                Intent i2 = new Intent(activity, KeHuFuWuActivity.class);
                i2.putExtra("fuwu","2");
                activity.startActivity(i2);
                dismiss();
                break;
            //举报
            case R.id.ll_layout_jubao:
                Intent i3 = new Intent(activity, KeHuFuWuActivity.class);
                i3.putExtra("fuwu","3");
                activity.startActivity(i3);
                dismiss();
                break;
            //其他问题
            case R.id.ll_layout_qitawenti:
                Intent i4 = new Intent(activity, QiTaWenTiAcitivity.class);
                activity.startActivity(i4);
                dismiss();
                break;
            default:
                break;
        }

    }
}