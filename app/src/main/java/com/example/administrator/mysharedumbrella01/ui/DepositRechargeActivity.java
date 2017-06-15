package com.example.administrator.mysharedumbrella01.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.mysharedumbrella01.R;
import com.gyf.barlibrary.ImmersionBar;

/**
 * Created by Administrator on 2017/6/8 0008.
 * //充值押金的界面
 */

public class DepositRechargeActivity extends AppCompatActivity implements View.OnClickListener {
    //返回箭头
    private ImageView img_back;

    private LinearLayout ll_llay_weixin;
    private LinearLayout ll_llay_zhifubao;
    //勾选 （微信的）
    private ImageView image_weixin_gouxuan,image_weixin_weigouxuan;
    //未勾选（支付宝的）
    private ImageView image_zhifubao_weigouxuan,image_zhifubao_gouxuan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depositrecharge);
        //沉浸式
        ImmersionBar.with(this)
                .statusBarColor(R.color.zhutiyanse) //指定主题颜色 意思 是在这里可以修改 styles 里面的主题颜色
                .fitsSystemWindows(true) //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
                .init();
        initView();

    }

    private void initView() {
        img_back = (ImageView) findViewById(R.id.img_back);
        img_back.setOnClickListener(this);
        image_weixin_gouxuan = (ImageView) findViewById(R.id.image_weixin_gouxuan);
        image_weixin_gouxuan.setOnClickListener(this);
        image_weixin_weigouxuan = (ImageView) findViewById(R.id.image_weixin_weigouxuan);
        image_weixin_weigouxuan.setOnClickListener(this);
        ll_llay_weixin = (LinearLayout) findViewById(R.id.ll_llay_weixin);
        ll_llay_weixin.setOnClickListener(this);

        image_zhifubao_weigouxuan = (ImageView) findViewById(R.id.image_zhifubao_weigouxuan);
        image_zhifubao_weigouxuan.setOnClickListener(this);
        image_zhifubao_gouxuan = (ImageView) findViewById(R.id.image_zhifubao_gouxuan);
        image_zhifubao_gouxuan.setOnClickListener(this);
        ll_llay_zhifubao = (LinearLayout) findViewById(R.id.ll_llay_zhifubao);
        ll_llay_zhifubao.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            //点击支付宝勾选按钮
            case R.id.image_zhifubao_weigouxuan:
                image_zhifubao_weigouxuan.setImageDrawable(getDrawable(R.drawable.gouxuan));
                image_weixin_gouxuan.setImageDrawable(getDrawable(R.drawable.weigouxuan));
                break;
            //点击微信勾选按钮
            case R.id.image_weixin_gouxuan:
                image_weixin_gouxuan.setImageDrawable(getDrawable(R.drawable.gouxuan));
                image_zhifubao_weigouxuan.setImageDrawable(getDrawable(R.drawable.weigouxuan));

                break;
        }
    }


}
