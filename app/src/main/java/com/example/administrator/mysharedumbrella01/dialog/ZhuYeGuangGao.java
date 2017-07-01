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
import com.example.administrator.mysharedumbrella01.ui.DepositRechargeActivity;
import com.example.administrator.mysharedumbrella01.ui.RechargeActivity;
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

public class ZhuYeGuangGao extends BasePopupWindow  implements View.OnClickListener{
    private View popupView;
    private Activity activity;
    private ImageView imageView;

    public ZhuYeGuangGao(Activity activity) {
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
            imageView = (ImageView) popupView.findViewById(R.id.img_guanggao);
            // 直接请求 网络接口获取图片路径
            String url = ConfigUtils.ZHU_YU_MING+ConfigUtils.ZHUYEGUANGGAO;
            OkGo.post(url)
                    .params("imgid","6")
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            L.e("主页广告 "+s);
                            Gson gson = new Gson();
                            ImgeBean ib = gson.fromJson(s, ImgeBean.class);
                           String url =  ib.getData();
                            if (!TextUtils.isEmpty(url)) {
                             GlideUtils.loadImageViewCache(activity,url,imageView);
                            }
                        }

                        @Override
                        public void onError(Call call, Response response, Exception e) {
                            super.onError(call, response, e);
                            L.e("主页广告 "+call);
                        }
                    });
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
