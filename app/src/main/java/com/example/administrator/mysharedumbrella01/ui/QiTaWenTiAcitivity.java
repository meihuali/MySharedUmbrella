package com.example.administrator.mysharedumbrella01.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.administrator.mysharedumbrella01.R;
import com.gyf.barlibrary.ImmersionBar;

/**
 * Created by Administrator on 2017/6/22 0022.
 */

public class QiTaWenTiAcitivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout ll_layout_lishijilu;
    private ImageView image_back1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qitawenti);
        //沉浸式
        ImmersionBar.with(this)
                .statusBarColor(R.color.zhutiyanse) //指定主题颜色 意思 是在这里可以修改 styles 里面的主题颜色
                // .statusBarDarkFont(true,0.2f)    如果是白色或者透明状态的时候就加上他
                .fitsSystemWindows(true) //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
                .init();
        initView();
    }

    private void initView() {
        ll_layout_lishijilu = (RelativeLayout) findViewById(R.id.ll_layout_lishijilu);
        ll_layout_lishijilu.setOnClickListener(this);
        image_back1 = (ImageView) findViewById(R.id.image_back1);
        image_back1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //历史记录
            case R.id.ll_layout_lishijilu:
                startActivity(new Intent(this, UsagelogActivity.class));
                break;
            case R.id.image_back1:
                finish();
                break;
        }
    }
}
