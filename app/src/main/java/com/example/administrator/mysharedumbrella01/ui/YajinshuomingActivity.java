package com.example.administrator.mysharedumbrella01.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.mysharedumbrella01.R;
import com.gyf.barlibrary.ImmersionBar;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.ui
 * 文件名：YajinshuomingActivity
 * 创建者 ：梅华黎
 * 创建时间： 2017/9/4 0004 16:47
 * 描述：押金说明
 */
public class YajinshuomingActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView image_backs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yajinshuoming);
        //沉浸式
        ImmersionBar.with(this)
                .init();
        initView();
    }

    private void initView() {
        image_backs = (ImageView) findViewById(R.id.image_backs);
        image_backs.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_backs:
                finish();
                break;
        }
    }
}
