package com.example.administrator.mysharedumbrella01.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.dialog.PopupWindowCenter;
import com.example.administrator.mysharedumbrella01.entivity.TuikuanBean;
import com.example.administrator.mysharedumbrella01.peresenet.TuikuanPerserent;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.example.administrator.mysharedumbrella01.view.IsTuikuanView;
import com.gyf.barlibrary.ImmersionBar;

import me.leefeng.promptlibrary.PromptButton;
import me.leefeng.promptlibrary.PromptButtonListener;
import me.leefeng.promptlibrary.PromptDialog;

/**
 * Created by Administrator on 2017/6/8 0008.
 *  我的钱包
 */

public class MyWalletActivity extends AppCompatActivity implements View.OnClickListener, IsTuikuanView {
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
    private TextView tv_tuikuan;
    private PromptDialog promptDialog;
    private String datas;
    private String yuer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mywallet);
        promptDialog = new PromptDialog(this);
        //沉浸式
        ImmersionBar.with(this)
                .statusBarColor(R.color.zhutiyanse) //指定主题颜色 意思 是在这里可以修改 styles 里面的主题颜色
                .fitsSystemWindows(true) //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
                .init();
        //初始化控件
        initView();

    }

    private void initView() {
        tv_tuikuan = (TextView)findViewById(R.id.tv_tuikuan);
        tv_tuikuan.setOnClickListener(this);
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
         yuer = intent.getStringExtra("yuer");
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
            case R.id.tv_tuikuan:
                //退款弹窗
                tuikuantanchuang();
                break;
        }
    }

    private void tuikuantanchuang() {
        //按钮的定义
        PromptButton confirm = new PromptButton("确定", new PromptButtonListener() {
            @Override
            public void onClick(PromptButton button) {
               double yuers = Double.parseDouble(yuer);
                if (yuers <= 0) {
                    Toast.makeText(getApplicationContext(),"您的余额小于零不可以退款",Toast.LENGTH_SHORT).show();
                } else {
                    String zh = ShareUtils.getString(getApplicationContext(),"zhanghao","");
                    TuikuanPerserent tuikuan = new TuikuanPerserent(MyWalletActivity.this);
                    tuikuan.tuikuan(zh);
                }

            }
        });
        confirm.setFocusBacColor(getResources().getColor(R.color.top_red));
//Alert的调用
        promptDialog.showWarnAlert("你确定要退款登录？", new PromptButton("取消", new PromptButtonListener() {
            @Override
            public void onClick(PromptButton button) {
                Toast.makeText(MyWalletActivity.this, button.getText(), Toast.LENGTH_SHORT).show();
            }
        }), confirm);
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
            Toast.makeText(getApplicationContext(), datas, Toast.LENGTH_SHORT).show();
        } else {

            Toast.makeText(getApplicationContext(), "退款失败，"+datas, Toast.LENGTH_SHORT).show();
        }
    }
}
