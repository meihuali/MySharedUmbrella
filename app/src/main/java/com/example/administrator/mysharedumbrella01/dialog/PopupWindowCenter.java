package com.example.administrator.mysharedumbrella01.dialog;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;

import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.ui.DepositRechargeActivity;
import com.example.administrator.mysharedumbrella01.ui.RechargeActivity;

import razerdp.basepopup.BasePopupWindow;

/**
 * Created by Administrator on 2017/6/8 0008.
 */

public class PopupWindowCenter extends BasePopupWindow  implements View.OnClickListener{
    private View popupView;
    private Activity activity;
    public PopupWindowCenter(Activity activity) {
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
        popupView= LayoutInflater.from(getContext()).inflate(R.layout.popup_normal,null);
        return popupView;
    }

    @Override
    public View initAnimaView() {
        return popupView.findViewById(R.id.popup_anima);
    }

    private void bindEvent() {
        if (popupView!=null){
            popupView.findViewById(R.id.tx_1).setOnClickListener(this);
            popupView.findViewById(R.id.tx_2).setOnClickListener(this);
            popupView.findViewById(R.id.tx_3).setOnClickListener(this);
            popupView.findViewById(R.id.tx_4).setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tx_1:
                break;
            case R.id.tx_2:
                break;
            case R.id.tx_3:
                //充押金
                activity.startActivity(new Intent(activity, DepositRechargeActivity.class));
                dismiss();
                break;
            //充值
            case R.id.tx_4:
                activity.startActivity(new Intent(activity,RechargeActivity.class));
                dismiss();
                break;
            default:
                break;
        }

    }
}
