package com.example.administrator.mysharedumbrella01.dialog;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.entivity.ImgeBean;
import com.example.administrator.mysharedumbrella01.ui.MyWalletActivity;
import com.example.administrator.mysharedumbrella01.utils.ConfigUtils;
import com.example.administrator.mysharedumbrella01.utils.GlideUtils;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;
import razerdp.basepopup.BasePopupWindow;

/**
 * Created by Administrator on 2017/6/8 0008.
 */

public class YaJinGuangGao extends BasePopupWindow  implements View.OnClickListener{
    private View popupView;
    private Activity activity;
    private ImageView imageView;

    public YaJinGuangGao(Activity activity) {
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
        popupView= LayoutInflater.from(getContext()).inflate(R.layout.yajinbuzuguanggao,null);
        return popupView;
    }

    @Override
    public View initAnimaView() {
        return popupView.findViewById(R.id.popup_anima);
    }

    private void bindEvent() {
        if (popupView!=null){
            popupView.findViewById(R.id.image_colse).setOnClickListener(this);
            popupView.findViewById(R.id.btn_quxiao).setOnClickListener(this);
            popupView.findViewById(R.id.btn_chongzhi).setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_colse:
                dismiss();
                break;
            case R.id.btn_quxiao:
               dismiss();
                break;
            case R.id.btn_chongzhi:
                activity.startActivity(new Intent(activity, MyWalletActivity.class));
                dismiss();
                break;

            default:
                break;
        }

    }
}
