package com.example.administrator.mysharedumbrella01.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.dialog.ShoppingYajinDialog;
import com.gyf.barlibrary.ImmersionBar;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.ui
 * 文件名：ShanZuoSanActivity
 * 创建者 ：梅华黎
 * 创建时间： 2017/9/8 0008 11:47
 * 描述： 伞座，跟伞的界面
 */
public class ShanZuoSanActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_confirm;
    private ImageView imageView;
    private LinearLayout ll_layout_addres;
    private ImageView image_back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanzuosan);
        //沉浸式
        ImmersionBar.with(this)
                .statusBarColor(R.color.lanse_x_x) //指定主题颜色 意思 是在这里可以修改 styles 里面的主题颜色
                // .statusBarDarkFont(true,0.2f)    如果是白色或者透明状态的时候就加上他
                .fitsSystemWindows(true) //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
                .init();
        initView();
    }
    /*
    * 初始化
    * */
    private void initView() {
        image_back = (ImageView) findViewById(R.id.image_back);
        image_back.setOnClickListener(this);
//        btn_confirm = (Button) findViewById(R.id.btn_confirm);
//        btn_confirm.setOnClickListener(this);
        ll_layout_addres = (LinearLayout) findViewById(R.id.ll_layout_addres);
        ll_layout_addres.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_back:
                finish();
                break;
            case R.id.btn_confirm:
                ShoppingYajinDialog shangjiayajin = new ShoppingYajinDialog(this);
                //设置pop 为全屏
                shangjiayajin.setPopupWindowFullScreen(true);
                shangjiayajin.showPopupWindow();
                shangjiayajin.setDismissWhenTouchOuside(true);
                break;
            //选择地址
            case R.id.ll_layout_addres:
                startActivity(new Intent(getApplicationContext(),ShoppingHarvestAddress.class));
                break;
        }
    }
}
