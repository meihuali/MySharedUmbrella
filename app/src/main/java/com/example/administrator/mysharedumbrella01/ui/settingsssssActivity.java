package com.example.administrator.mysharedumbrella01.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.gyf.barlibrary.ImmersionBar;

/**
 * Created by Administrator on 2017/6/3 0003.
 * //最后的那个 设置界面 ·包括 退出 功能
 */

public class settingsssssActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView image_back;
    private Button btn_exits;
    private RelativeLayout rl_guanyuwomen;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sttingsssss);
        //沉浸式
        ImmersionBar.with(this)
                .statusBarColor(R.color.colorAccent) //指定主题颜色 意思 是在这里可以修改 styles 里面的主题颜色
                .fitsSystemWindows(true) //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
                .init();

        initView();
    }

    private void initView() {
        rl_guanyuwomen = (RelativeLayout) findViewById(R.id.rl_guanyuwomen);
        rl_guanyuwomen.setOnClickListener(this);
        image_back = (ImageView) findViewById(R.id.image_back);
        image_back.setOnClickListener(this);
        btn_exits = (Button) findViewById(R.id.btn_exits);
        btn_exits.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                finish();
                break;
            case R.id.btn_exits:
                ShareUtils.deleShare(getApplicationContext(),"mima");
                finish();
                break;
            case R.id.rl_guanyuwomen:
                startActivity(new Intent(this,AboutusActivity.class));
                break;
        }
    }
}