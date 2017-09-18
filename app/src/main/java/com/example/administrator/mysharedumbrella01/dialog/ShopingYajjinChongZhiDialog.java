package com.example.administrator.mysharedumbrella01.dialog;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.CycleInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.utils.ToastUtil;

import razerdp.basepopup.BasePopupWindow;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.dialog
 * 文件名：ShopingYajjinChongZhiDialog
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/11 0011 14:28
 * 描述：商家充值押金
 */
public class ShopingYajjinChongZhiDialog  extends BasePopupWindow implements View.OnClickListener{

    private final ImageView img_close;
    private Button btn_queren;
    private TextView cancel;
    private LinearLayout ll_layout_close;
    private TextView tv_shoppingYJ;
    private ImageView img_jian,imge_add;
    private int jiner = 900;
    private int cuont = 6 ;
    private TextView tv_sums;
    private int sum;
    private TextView tv_yusans;
    private int yusanCuont = 18;
    private ImageView img_yusan_add;
    private ImageView img_yusan_jian;
    public ShopingYajjinChongZhiDialog(Activity context) {
        super(context);

        btn_queren= (Button) findViewById(R.id.btn_queren);
        //cancel= (TextView) findViewById(R.id.cancel);
        img_close = (ImageView) findViewById(R.id.img_close);
        ll_layout_close = (LinearLayout) findViewById(R.id.ll_layout_close);
        tv_shoppingYJ = (TextView) findViewById(R.id.tv_shoppingYJ);
        imge_add = (ImageView) findViewById(R.id.imge_add);
        img_jian = (ImageView) findViewById(R.id.img_jian);
        tv_sums = (TextView) findViewById(R.id.tv_sums);
        tv_sums.setText(cuont+"");

        tv_yusans = (TextView) findViewById(R.id.tv_yusans);
        tv_yusans.setText(yusanCuont+"");
        img_yusan_add = (ImageView) findViewById(R.id.img_yusan_add);
        img_yusan_jian = (ImageView) findViewById(R.id.img_yusan_jian);
        setViewClickListener(this,btn_queren,cancel,ll_layout_close,tv_shoppingYJ,tv_sums,imge_add,img_jian,img_yusan_add,img_yusan_jian);
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
        return createPopupById(R.layout.popup_chongzhi_dialog);
    }

    @Override
    public View initAnimaView() {
        return findViewById(R.id.popup_anima);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_queren:

                break;
//            case R.id.cancel:
//                  Toast.makeText(getContext(),"click the cancel button",Toast.LENGTH_SHORT).show();
//                break;
            case R.id.img_close:
                dismiss();
                break;
            case R.id.ll_layout_close:
                dismiss();
                break;
            //申请伞座 增加
            case R.id.imge_add:
                sum =  (cuont+=6) * 50;
                // 设置点击个数
                tv_sums.setText(cuont+"");
                //设置总金额
                tv_shoppingYJ.setText(sum+"");

                break;
            //申请伞座 减少
            case R.id.img_jian:

                if (cuont > 6) {
                    sum = (cuont-=6) * 50;
                    //这里设置金额
                    tv_shoppingYJ.setText(sum + "");
                    //设置个数
                    tv_sums.setText(cuont+"");
                } else {
                    ToastUtil.showShortToast(getContext(),"默认6个伞座，300元！");
                }

                break;
            case R.id.img_yusan_add:
                yusanCuont+=10;
                //设置申请的雨伞个数
                tv_yusans.setText(yusanCuont+"");
                break;
            case R.id.img_yusan_jian:
                if (yusanCuont > 18) {
                    yusanCuont -= 10;
                    tv_yusans.setText(yusanCuont + "");
                } else {
                    ToastUtil.showShortToast(getContext(),"默认就是18把雨伞哦！");
                }
                break;
            default:
                break;
        }

    }

}
