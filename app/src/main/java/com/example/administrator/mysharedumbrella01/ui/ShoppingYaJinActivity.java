package com.example.administrator.mysharedumbrella01.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.dialog.ShoppingTuiKuanDialog;
import com.example.administrator.mysharedumbrella01.entivity.GetShoppingYajinBean;
import com.example.administrator.mysharedumbrella01.peresenet.GetShoppingYajinPerserent;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.example.administrator.mysharedumbrella01.view.IsGetShoppingYajinView;
import com.gyf.barlibrary.ImmersionBar;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.whyalwaysmea.circular.AnimUtils;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.ui
 * 文件名：ShoppingYaJinActivity
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/21 0021 12:28
 * 描述：商家 我的押金
 */
public class ShoppingYaJinActivity extends AppCompatActivity implements View.OnClickListener, IsGetShoppingYajinView {

    private ImageView image_back;
    private TextView tv_shangjia;
    private Button btn_tuikuan;
    private String shopping_moneys;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppingyajin);
        AnimUtils.animhpel(this,R.id.ll_layout);
        //沉浸式
        ImmersionBar.with(this)
                .init();
        initView();
        initData();
    }
    /*
    *  获取商家押金的网络请求
    * */
    private void initData() {
        StyledDialog.buildLoading("获取中···").show();
        String phone =  ShareUtils.getString(getApplicationContext(),"zhanghao","");
        GetShoppingYajinPerserent getyajin = new GetShoppingYajinPerserent(this);
        getyajin.getyajin(phone);
    }

    private void initView() {
        btn_tuikuan = (Button) findViewById(R.id.btn_tuikuan);
        btn_tuikuan.setOnClickListener(this);
        image_back = (ImageView) findViewById(R.id.image_back);
        image_back.setOnClickListener(this);
        tv_shangjia =(TextView) findViewById(R.id.tv_shangjia);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_back:
                AnimUtils.finishAmins(this,R.id.rl_layoutssss,v,R.id.ll_layout);
                break;
            case R.id.btn_tuikuan:
                if (Double.parseDouble(shopping_moneys) > 0) {
                    //这里退款弹出窗口
                    ShoppingTuiKuanDialog tuiKuanDialog = new ShoppingTuiKuanDialog(this,shopping_moneys);
                    tuiKuanDialog.showPopupWindow();
                } else {
                    StyledDialog.buildIosAlert("提示！", "您目前没有押金可以退款！谢谢", new MyDialogListener() {
                        @Override
                        public void onFirst() {
                        }
                        @Override
                        public void onSecond() {
                        }
                    }).setBtnText("确定","").show();
                }
                break;
        }
    }
    /*
    * 获取商家押金接口回调
    * */
    @Override
    public void showGetYaJin(Object object) {
        StyledDialog.dismissLoading();
        GetShoppingYajinBean getyajin = (GetShoppingYajinBean) object;
        int status = getyajin.getStatus();
        if (status == 1) {
            GetShoppingYajinBean.DataBean getyajinben = getyajin.getData();
            shopping_moneys = getyajinben.getSuccess();
            tv_shangjia.setText(shopping_moneys);
        } else {
            tv_shangjia.setText("0.00");
        }
    }
}
