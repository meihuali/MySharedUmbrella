package com.example.administrator.mysharedumbrella01.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.mysharedumbrella01.R;
import com.gyf.barlibrary.ImmersionBar;

/**
 * Created by Administrator on 2017/6/5 0005.
 */

public class YuanXingTouxiangSettingsActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imge_backes;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingsyuanxingtouxiang);
        //沉浸式
        ImmersionBar.with(this)
                .statusBarColor(R.color.top_red) //指定主题颜色 意思 是在这里可以修改 styles 里面的主题颜色
                .fitsSystemWindows(true) //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
                .init();
        intiview();

    }
        /*初始化 */
    private void intiview() {
        imge_backes = (ImageView) findViewById(R.id.imge_backes);
        imge_backes.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imge_backes:
                finish();
                break;
        }
    }
}
