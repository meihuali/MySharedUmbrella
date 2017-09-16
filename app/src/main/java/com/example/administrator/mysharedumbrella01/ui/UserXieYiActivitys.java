package com.example.administrator.mysharedumbrella01.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.administrator.mysharedumbrella01.R;
import com.gyf.barlibrary.ImmersionBar;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.ui
 * 文件名：UserXieYiActivitys
 * 创建者 ：梅华黎
 * 创建时间： 2017/9/4 0004 16:57
 * 描述：用户协议
 */
public class UserXieYiActivitys extends AppCompatActivity implements View.OnClickListener {
    private ImageView image_back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitys_userxieyi);
        //沉浸式
        ImmersionBar.with(this)
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
                finish();
                break;
        }
    }
}
