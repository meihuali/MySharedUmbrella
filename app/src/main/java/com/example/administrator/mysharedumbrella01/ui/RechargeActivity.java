package com.example.administrator.mysharedumbrella01.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.gyf.barlibrary.ImmersionBar;

/**
 * Created by Administrator on 2017/6/9 0009.
 */

public class RechargeActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView image_back;
    private ImageView image_weixin_gouxuan;
    private ImageView image_zhifubao_weigouxuan;
    //金额选择的四个button
    private Button btn_yibaiyuan,btn_wushiyuan,btn_ershiyuan,btn_shiyuan;
    //声明选择的金额
    private int moneys = 100;
    //选择支付方式的变量
    private int types = 1;
    private Button btn_Recharge;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        //沉浸式
        ImmersionBar.with(this)
                .statusBarColor(R.color.top_red) //指定主题颜色 意思 是在这里可以修改 styles 里面的主题颜色
                // .statusBarDarkFont(true,0.2f)    如果是白色或者透明状态的时候就加上他
                .fitsSystemWindows(true) //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
                .init();
        initView();
    }

    private void initView() {
        btn_Recharge = (Button) findViewById(R.id.btn_Recharge);
        btn_Recharge.setOnClickListener(this);
        image_back = (ImageView) findViewById(R.id.image_back);
        image_back.setOnClickListener(this);
        image_weixin_gouxuan = (ImageView) findViewById(R.id.image_weixin_gouxuan);
        image_weixin_gouxuan.setOnClickListener(this);
        image_zhifubao_weigouxuan = (ImageView) findViewById(R.id.image_zhifubao_weigouxuan);
        image_zhifubao_weigouxuan.setOnClickListener(this);

        btn_yibaiyuan = (Button) findViewById(R.id.btn_yibaiyuan);
        btn_yibaiyuan.setOnClickListener(this);
        btn_wushiyuan = (Button) findViewById(R.id.btn_wushiyuan);
        btn_wushiyuan.setOnClickListener(this);
        btn_ershiyuan = (Button) findViewById(R.id.btn_ershiyuan);
        btn_ershiyuan.setOnClickListener(this);
        btn_shiyuan = (Button) findViewById(R.id.btn_shiyuan);
        btn_shiyuan.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                finish();
                break;
            case R.id.btn_yibaiyuan:
                btn_yibaiyuan.setTextColor(getResources().getColor(R.color.white));
                btn_wushiyuan.setTextColor(getResources().getColor(R.color.txt_balck));
                btn_shiyuan.setTextColor(getResources().getColor(R.color.txt_balck));
                btn_ershiyuan.setTextColor(getResources().getColor(R.color.txt_balck));
                btn_yibaiyuan.setBackgroundColor(getResources().getColor(R.color.juse));
                btn_wushiyuan.setBackgroundColor(getResources().getColor(R.color.huise));
                btn_ershiyuan.setBackgroundColor(getResources().getColor(R.color.huise));
                btn_shiyuan.setBackgroundColor(getResources().getColor(R.color.huise));
                //点击一百元 赋值为100
                moneys = 100;
                break;
            case R.id.btn_wushiyuan:
                btn_wushiyuan.setTextColor(getResources().getColor(R.color.white));
                btn_yibaiyuan.setTextColor(getResources().getColor(R.color.txt_balck));
                btn_ershiyuan.setTextColor(getResources().getColor(R.color.black));
                btn_shiyuan.setTextColor(getResources().getColor(R.color.txt_balck));
                btn_wushiyuan.setBackgroundColor(getResources().getColor(R.color.juse));
                btn_yibaiyuan.setBackgroundColor(getResources().getColor(R.color.huise));
                btn_ershiyuan.setBackgroundColor(getResources().getColor(R.color.huise));
                btn_shiyuan.setBackgroundColor(getResources().getColor(R.color.huise));
                // 点击五十元 赋值为50；
                moneys = 50;
                break;
            case R.id.btn_ershiyuan:
                btn_ershiyuan.setTextColor(getResources().getColor(R.color.white));
                btn_yibaiyuan.setTextColor(getResources().getColor(R.color.txt_balck));
                btn_wushiyuan.setTextColor(getResources().getColor(R.color.txt_balck));
                btn_shiyuan.setTextColor(getResources().getColor(R.color.txt_balck));
                btn_ershiyuan.setBackgroundColor(getResources().getColor(R.color.juse));
                btn_yibaiyuan.setBackgroundColor(getResources().getColor(R.color.huise));
                btn_wushiyuan.setBackgroundColor(getResources().getColor(R.color.huise));
                btn_shiyuan.setBackgroundColor(getResources().getColor(R.color.huise));
                moneys = 20;
                break;
            case R.id.btn_shiyuan:
                btn_shiyuan.setTextColor(getResources().getColor(R.color.white));
                btn_yibaiyuan.setTextColor(getResources().getColor(R.color.txt_balck));
                btn_wushiyuan.setTextColor(getResources().getColor(R.color.txt_balck));
                btn_ershiyuan.setTextColor(getResources().getColor(R.color.txt_balck));
                btn_shiyuan.setBackgroundColor(getResources().getColor(R.color.juse));
                btn_yibaiyuan.setBackgroundColor(getResources().getColor(R.color.huise));
                btn_wushiyuan.setBackgroundColor(getResources().getColor(R.color.huise));
                btn_ershiyuan.setBackgroundColor(getResources().getColor(R.color.huise));
                moneys = 10;
                break;
            //点击支付宝勾选按钮
            case R.id.image_zhifubao_weigouxuan:
                image_zhifubao_weigouxuan.setImageDrawable(getDrawable(R.drawable.gouxuan));
                image_weixin_gouxuan.setImageDrawable(getDrawable(R.drawable.weigouxuan));
                types = 2; //代表用户选择的是支付宝
                break;
            //点击微信勾选按钮
            case R.id.image_weixin_gouxuan:
                image_weixin_gouxuan.setImageDrawable(getDrawable(R.drawable.gouxuan));
                image_zhifubao_weigouxuan.setImageDrawable(getDrawable(R.drawable.weigouxuan));
                types = 1; //代表用户选择的是微信
                break;
            case R.id.btn_Recharge:

                if (types == 1) {
                    //等于1 代表选择选择的是 微信支付 这里掉微信的支付接口 然后在把moneys 金额带过去
                    Toast.makeText(getApplicationContext(),"微信支付暂未开通 "+moneys+" 元",Toast.LENGTH_SHORT).show();
                } else if (types == 2) {
                    //等于2 代表选择选择的是 支付宝支付 这里掉微信的支付接口 然后在把moneys 金额带过去
                    Toast.makeText(getApplicationContext(),"支付宝支付暂未开通 "+moneys+" 元",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
