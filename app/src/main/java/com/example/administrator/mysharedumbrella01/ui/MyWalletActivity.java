package com.example.administrator.mysharedumbrella01.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.dialog.PopupWindowCenter;
import com.gyf.barlibrary.ImmersionBar;

/**
 * Created by Administrator on 2017/6/8 0008.
 *  我的钱包
 */

public class MyWalletActivity extends AppCompatActivity implements View.OnClickListener {
    //返回键
    private ImageView image_back;
    //点击按钮充值
    private Button btn_Recharge;
    //充值明细
    private TextView tv_mingxi;
    //押金 jine
    private TextView tv_yajin,tv_yuer;
    private Button btn_chongzhiYaJin;
    private String yajin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mywallet);
        //沉浸式
        ImmersionBar.with(this)
                .statusBarColor(R.color.zhutiyanse) //指定主题颜色 意思 是在这里可以修改 styles 里面的主题颜色
                .fitsSystemWindows(true) //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
                .init();
        //初始化控件
        initView();

    }

    private void initView() {
        btn_chongzhiYaJin = (Button)findViewById(R.id.btn_chongzhiYaJin);
        btn_chongzhiYaJin.setOnClickListener(this);
        tv_yajin = (TextView) findViewById(R.id.tv_yajin);
        tv_yuer = (TextView) findViewById(R.id.tv_yuer);
        tv_mingxi = (TextView) findViewById(R.id.tv_mingxi);
        tv_mingxi.setOnClickListener(this);
        image_back = (ImageView) findViewById(R.id.image_back);
        image_back.setOnClickListener(this);
        btn_Recharge = (Button) findViewById(R.id.btn_Recharge);
        btn_Recharge.setOnClickListener(this);
        //获取从上一个界面传过来的押金
        initDatas();
    }

    private void initDatas() {

        Intent intent =   getIntent();
        yajin = intent.getStringExtra("moneysss");
        String yuer = intent.getStringExtra("yuer");
        tv_yuer.setText(yuer+"元");
        tv_yajin.setText("账户押金"+yajin+"元");

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                finish();
                break;
            case R.id.btn_Recharge:
                double yajinmoney =  Double.parseDouble(yajin);
                if (yajinmoney < 20.00) {
                    PopupWindowCenter pwc = new PopupWindowCenter(this);
                    pwc.showPopupWindow();
                } else {
                    //充值余额
                    startActivity(new Intent(getApplicationContext(),RechargeActivity.class));
                }

                break;
            //明细
            case R.id.tv_mingxi:
                startActivity(new Intent(this,DetailofamountActivity.class));
                break;
            //充值押金
            case R.id.btn_chongzhiYaJin:
                startActivity(new Intent(getApplicationContext(), DepositRechargeActivity.class));
                break;
        }
    }
}
