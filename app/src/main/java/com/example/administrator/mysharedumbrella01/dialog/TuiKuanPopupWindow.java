package com.example.administrator.mysharedumbrella01.dialog;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.entivity.TuikuanBean;
import com.example.administrator.mysharedumbrella01.peresenet.TuikuanPerserent;
import com.example.administrator.mysharedumbrella01.ui.DepositRechargeActivity;
import com.example.administrator.mysharedumbrella01.ui.MyWalletActivity;
import com.example.administrator.mysharedumbrella01.ui.RechargeActivity;
import com.example.administrator.mysharedumbrella01.ui.ShoppingYaJinActivity;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.example.administrator.mysharedumbrella01.utils.MyToast;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.example.administrator.mysharedumbrella01.utils.ToastUtil;
import com.example.administrator.mysharedumbrella01.view.IsTuikuanView;

import razerdp.basepopup.BasePopupWindow;

/**
 * Created by Administrator on 2017/6/8 0008.
 */

public class TuiKuanPopupWindow extends BasePopupWindow  implements View.OnClickListener,IsTuikuanView{
    private View popupView;
    private Activity activity;
    public String yajin;
    private int count =1;
    //定义金额变量
    private int money = 20;
    private TextView tv_conunts;
    private double showMoney;
    private String datas;

    public TuiKuanPopupWindow(Activity activity,String yajin) {
        super(activity);
        this.activity = activity;
        this.yajin = yajin;
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
        popupView= LayoutInflater.from(getContext()).inflate(R.layout.tuikuan_popup_normal,null);
        return popupView;
    }

    @Override
    public View initAnimaView() {
        return popupView.findViewById(R.id.popup_anima);
    }

    private void bindEvent() {
        if (popupView!=null){
            popupView.findViewById(R.id.img_close).setOnClickListener(this);

            TextView tv_sumMoney = (TextView) popupView.findViewById(R.id.tv_zongyajin);
            //总金额
            tv_sumMoney.setText("您共有押金："+yajin+"元");

            //默认设置显示一个20.00的 金额
            tv_conunts = (TextView) popupView.findViewById(R.id.tv_jiners);
            tv_conunts.setText(money+"");

            //初始化增加的按钮
            popupView.findViewById(R.id.img_add).setOnClickListener(this);
            /*==================== 下面是减号按钮逻辑==================================================*/
            popupView.findViewById(R.id.img_jianhao).setOnClickListener(this);

            popupView.findViewById(R.id.btn_tukuan).setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_add:
                //计算增加金额
                addMoney(yajin);
                break;
            case R.id.img_jianhao:
                //计算减号的金额
                MinusSignMoney(yajin);
                break;
            case R.id.img_close:
                dismiss();
                //这里是关闭popwu窗口 然后调用上一个界面的 gethttMoney 方法继续 请求服务器·再次回去当前账号下的金额
                ((MyWalletActivity) activity).getHttpMoney();
                break;
            case R.id.btn_tukuan:
                //这里是用户点击了确定按钮 退款请求
                tuikuanQyest();
                break;
        }

    }
    /*
    * 退款请求
    * */
    private void tuikuanQyest() {
            String zh = ShareUtils.getString(activity,"zhanghao","");
            TuikuanPerserent tuikuan = new TuikuanPerserent((IsTuikuanView) activity);
            tuikuan.tuikuan(zh,count+"","0"); //0为用户 1为商户
    }

    /*
    * 计算减号的金额
    * */
    private void MinusSignMoney(String yajins) {
        int yj = (int) Double.parseDouble(yajins);
        if (count - 1 >= 1) {
            count--;
            double jin = count * 20.00;
            tv_conunts.setText(jin + "");
        } else {
            ToastUtil.showShortToast(activity,"默认退款20元！");
        }

    }

    /*
    *
    * */
    private void addMoney(String yajins) {
        int yj = (int) Double.parseDouble(yajins);
        if (count + 1 <= yj / 20) {
            count++;
            showMoney = count * money;
            tv_conunts.setText(showMoney + "");
        } else {
            ToastUtil.showShortToast(activity,"您没有更多的押金可以退了");
        }
    }

    /*
    * 退款接口回调结果
    * */
    @Override
    public void showRrult(Object object) {
        TuikuanBean tuikuanBean = (TuikuanBean) object;
        int status = tuikuanBean.getStatus();
        datas = tuikuanBean.getData();
        if (status == 1) {
            Toast.makeText(activity, datas, Toast.LENGTH_SHORT).show();
            ToastUtil.showShortToast(activity,datas);
        } else {
            ToastUtil.showShortToast(activity,"退款失败"+datas);
        }
    }


}
