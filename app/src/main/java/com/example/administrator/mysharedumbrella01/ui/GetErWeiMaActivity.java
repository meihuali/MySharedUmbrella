package com.example.administrator.mysharedumbrella01.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.administrator.mysharedumbrella01.R;
import com.gyf.barlibrary.ImmersionBar;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.ui
 * 文件名：GetErWeiMaActivity
 * 创建者 ：梅华黎
 * 创建时间： 2017/9/25 0025 12:14
 * 描述：TODO
 */
public class GetErWeiMaActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView image_back;
    private Button btn_confmir;
    private EditText ed_body;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erweimahuoqu);
        //沉浸式
        ImmersionBar.with(this)
                .statusBarColor(R.color.white) //指定主题颜色 意思 是在这里可以修改 styles 里面的主题颜色
                // .statusBarDarkFont(true,0.2f)    如果是白色或者透明状态的时候就加上他
                .fitsSystemWindows(true) //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
                .init();

        initView();
    }

    private void initView() {
        ed_body = (EditText) findViewById(R.id.ed_body);
        ed_body.setOnClickListener(this);
        btn_confmir = (Button) findViewById(R.id.btn_confmir);
        btn_confmir.setOnClickListener(this);
        image_back = (ImageView) findViewById(R.id.image_back);
        image_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_back:
                finish();
                break;
            case R.id.btn_confmir:
                String ed_content  = ed_body.getText().toString().trim();
                StyledDialog.buildIosAlert("提示", "该功能暂未开放", new MyDialogListener() {
                    @Override
                    public void onFirst() {

                    }

                    @Override
                    public void onSecond() {

                    }
                }).setBtnText("确定","").show();
                break;
        }
    }
}
