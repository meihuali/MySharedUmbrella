package com.example.administrator.mysharedumbrella01.dialog;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;

import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.appliction.BaseAppliction;
import com.example.administrator.mysharedumbrella01.ui.DepositRechargeActivity;
import com.example.administrator.mysharedumbrella01.ui.RechargeActivity;
import com.example.administrator.mysharedumbrella01.ui.ShoppingShangjiaxinxiActivity;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.example.administrator.mysharedumbrella01.utils.StaticClass;

import razerdp.basepopup.BasePopupWindow;

/**
 * Created by Administrator on 2017/6/8 0008.
 */

public class PopupShopping extends BasePopupWindow  implements View.OnClickListener{
    private View popupView;
    private Activity activity;
    public PopupShopping(Activity activity) {
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
        popupView= LayoutInflater.from(getContext()).inflate(R.layout.popupshopping,null);
        return popupView;
    }

    @Override
    public View initAnimaView() {
        return popupView.findViewById(R.id.popup_anima);
    }

    private void bindEvent() {
        if (popupView!=null){
            popupView.findViewById(R.id.btn_queding).setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_queding:
//                BaseAppliction.destoryActivity("ShangJiaRenZhengActivity");
//                BaseAppliction.destoryActivity("ShoppingUserRegister");
                BaseAppliction.destoryActivity("LoginActivity");
                BaseAppliction.destoryActivity("MainActivity");

                //保存状态为true 下次从启动页面取出来直接进入商家
            //    ShareUtils.putBoolean(activity, StaticClass.SHARE_IS_FIRSTS,true);
                activity.startActivity(new Intent(activity, ShoppingShangjiaxinxiActivity.class));
                dismiss();
                break;

        }

    }
}
