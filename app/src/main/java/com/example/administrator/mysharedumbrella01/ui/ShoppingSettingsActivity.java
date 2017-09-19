package com.example.administrator.mysharedumbrella01.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.appliction.BaseAppliction;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.example.administrator.mysharedumbrella01.utils.StaticClass;
import com.gyf.barlibrary.ImmersionBar;
import com.whyalwaysmea.circular.AnimUtils;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.ui
 * 文件名：ShoppingSettingsActivity
 * 创建者 ：梅华黎
 * 创建时间： 2017/9/8 0008 10:04
 * 描述：商家版本的设置
 */
public class ShoppingSettingsActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_exits;
    private ImageView image_backs;
    private View rl_back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppingsettings);

        AnimUtils.animhpel((Activity) this,R.id.ll_layout);
        //沉浸式
        ImmersionBar.with(this)
                //  .transparentBar()
                .init();
        initView();
    }
    /*
    * 初始化数据
    * */
    private void initView() {
        image_backs = (ImageView) findViewById(R.id.image_backs);
        image_backs.setOnClickListener(this);
        btn_exits = (Button) findViewById(R.id.btn_exits);
        btn_exits.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_exits:
                ShareUtils.putBoolean(ShoppingSettingsActivity.this, StaticClass.SHARE_IS_FIRST,false);
                BaseAppliction.destoryActivity("ShoppingShangjiaxinxiActivity");
                finish();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                break;
            case R.id.image_backs:
             //   finish();
                AnimUtils.finishAmins((Activity)this,R.id.rl_back,v,R.id.ll_layout);
                break;

        }
    }
}
