package com.example.administrator.mysharedumbrella01.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.mysharedumbrella01.R;
import com.gyf.barlibrary.ImmersionBar;
import com.whyalwaysmea.circular.AnimUtils;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.ui
 * 文件名：LianXiKeFuActivity
 * 创建者 ：梅华黎
 * 创建时间： 2017/9/4 0004 16:14
 * 描述：TODO
 */
public class LianXiKeFuActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView image_back;
    private View ll_layout;
    private View ll_xxxx;
    private View ll_layout_sss;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lianxikefu);

        AnimUtils.animhpel((Activity) this,R.id.ll_layout);
        //沉浸式
        ImmersionBar.with(this)
                .transparentBar()
                .init();

        initView();
    }

    private void initView() {
        image_back = (ImageView) findViewById(R.id.image_back);
        image_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_back:
               // finish();
                AnimUtils.finishAmins((Activity) LianXiKeFuActivity.this,R.id.ll_xxxx,v,R.id.ll_layout_sss);
                break;

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
