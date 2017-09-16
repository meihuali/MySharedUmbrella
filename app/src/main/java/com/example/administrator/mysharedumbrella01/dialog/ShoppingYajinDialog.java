package com.example.administrator.mysharedumbrella01.dialog;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.CycleInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.mysharedumbrella01.R;

import razerdp.basepopup.BasePopupWindow;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.dialog
 * 文件名：ShoppingYajinDialog
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/11 0011 11:33
 * 描述：商家弹出popupwindow
 */
public class ShoppingYajinDialog extends BasePopupWindow implements View.OnClickListener{

    private final ImageView img_close;
    private Button btn_queren;
    private TextView cancel;
    private Activity activity;
    public ShoppingYajinDialog(Activity context) {
        super(context);
        this.activity = context;

        btn_queren= (Button) findViewById(R.id.btn_queren);
        //cancel= (TextView) findViewById(R.id.cancel);
        img_close = (ImageView) findViewById(R.id.img_close);

        setViewClickListener(this,btn_queren,cancel);
    }

    @Override
    protected Animation initShowAnimation() {
        AnimationSet set=new AnimationSet(false);
        Animation shakeAnima=new RotateAnimation(0,15,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        shakeAnima.setInterpolator(new CycleInterpolator(5));
        shakeAnima.setDuration(400);
        set.addAnimation(getDefaultAlphaAnimation());
        set.addAnimation(shakeAnima);
        return set;
    }

    @Override
    public View getClickToDismissView() {
       // return getPopupWindowView();
        return null;
    }

    @Override
    public View onCreatePopupView() {
        return createPopupById(R.layout.popup_dialog);
    }

    @Override
    public View initAnimaView() {
        return findViewById(R.id.popup_anima);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_queren:
                ShopingYajjinChongZhiDialog chongzhi = new ShopingYajjinChongZhiDialog(activity);
                chongzhi.setPopupWindowFullScreen(true);
                chongzhi.showPopupWindow();
                dismiss();
                break;
//            case R.id.cancel:
//                  Toast.makeText(getContext(),"click the cancel button",Toast.LENGTH_SHORT).show();
//                break;
            case R.id.img_close:
                dismiss();
                break;
            default:
                break;
        }

    }
}
