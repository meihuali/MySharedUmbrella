package com.example.administrator.mysharedumbrella01.dialog;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;

import com.example.administrator.mysharedumbrella01.R;

import razerdp.basepopup.BasePopupWindow;

/**
 * Created by Administrator on 2017/6/22 0022.
 */

public class KaiSuohoudeGuanggao  extends BasePopupWindow implements View.OnClickListener{
    private View popupView;
    private Activity activity;
    public KaiSuohoudeGuanggao(Activity activity) {
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

        return null;
//        return popupView.findViewById(R.id.click_to_dismiss); //这句话·是返回的时候点击屏幕的地方隐藏pop
    }

    @Override
    public View onCreatePopupView() {
        popupView= LayoutInflater.from(getContext()).inflate(R.layout.zhuye_guanggaol,null);
        return popupView;
    }

    @Override
    public View initAnimaView() {
        return popupView.findViewById(R.id.popup_anima);
    }

    private void bindEvent() {
        if (popupView!=null){
            popupView.findViewById(R.id.image_colse).setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_colse:
                dismiss();
                break;

            default:
                break;
        }

    }
}
