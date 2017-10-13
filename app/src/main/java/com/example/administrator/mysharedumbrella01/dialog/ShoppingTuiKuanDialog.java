package com.example.administrator.mysharedumbrella01.dialog;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.entivity.TuikuanBean;
import com.example.administrator.mysharedumbrella01.peresenet.TuikuanPerserent;
import com.example.administrator.mysharedumbrella01.ui.MyWalletActivity;
import com.example.administrator.mysharedumbrella01.ui.ShoppingYaJinActivity;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.example.administrator.mysharedumbrella01.utils.ToastUtil;
import com.example.administrator.mysharedumbrella01.view.IsTuikuanView;

import razerdp.basepopup.BasePopupWindow;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.dialog
 * 文件名：ShoppingTuiKuanDialog
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/21 0021 14:49
 * 描述：TODO
 */
public class ShoppingTuiKuanDialog  extends BasePopupWindow implements View.OnClickListener,IsTuikuanView{

    private final String yajin;
    private Activity activity;
    private View popupView;
    private int count = 1;
    private TextView tv_conunts;
    private double showMoney;
    private int money = 300;
    private String datas;

    public ShoppingTuiKuanDialog(Activity activity,String yajin) {
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
        popupView= LayoutInflater.from(getContext()).inflate(R.layout.shopping_tuikuan,null);
        return popupView;
    }

    @Override
    public View initAnimaView() {
        return popupView.findViewById(R.id.popup_anima);
    }

    private void bindEvent() {
        if (popupView!=null){
            popupView.findViewById(R.id.img_close).setOnClickListener(this);
            popupView.findViewById(R.id.img_add).setOnClickListener(this);
            TextView tv_sumMoney = (TextView) popupView.findViewById(R.id.tv_zongyajin);
            //总金额
            tv_sumMoney.setText("您共有押金："+yajin+"元");

            tv_conunts = (TextView) popupView.findViewById(R.id.tv_jiners);
            tv_conunts.setText(yajin+"元");

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
                //  ((ShoppingYaJinActivity) activity).getHttpMoney();
                break;
            case R.id.btn_tukuan:
                String zh = ShareUtils.getString(activity,"zhanghao","");
                TuikuanPerserent tuikuan = new TuikuanPerserent(this);
                tuikuan.tuikuan(zh,count+"","1"); //0为用户 1为商户
                break;
        }
    }

    private void addMoney(String yajins) {
        int yj = (int) Double.parseDouble(yajins);
        if (count + 1 <= yj / 300) {
            count++;
            showMoney = count * money;
            tv_conunts.setText(showMoney + "");
        } else {
            ToastUtil.showShortToast(activity,"您没有更多的押金可以退了");
        }
    }

    /*
* 计算减号的金额
* */
    private void MinusSignMoney(String yajins) {
        int yj = (int) Double.parseDouble(yajins);
        if (count - 1 >= 1) {
            count--;
            double jin = count * 300;
            tv_conunts.setText(jin + "");
        } else {
            ToastUtil.showShortToast(activity,"默认退款20元！");
        }

    }

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
