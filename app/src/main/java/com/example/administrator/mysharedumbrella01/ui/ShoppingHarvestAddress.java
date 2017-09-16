package com.example.administrator.mysharedumbrella01.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.mysharedumbrella01.Adapter.DetailoFamountAdapter;
import com.example.administrator.mysharedumbrella01.Adapter.ShoppingHarvestAdapter;
import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.entivity.ShoppingCityAddressBean;
import com.gyf.barlibrary.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.ui
 * 文件名：ShoppingHarvestAddress
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/13 0013 16:23
 * 描述：商家收获地址 列表
 */
public class ShoppingHarvestAddress extends AppCompatActivity implements View.OnClickListener {
    private ImageView image_back;
    private RecyclerView mRecyclerView;
    private List<ShoppingCityAddressBean> mlist = new ArrayList<>();
    private ShoppingHarvestAdapter adapter;
    private Button btn_add_address;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppingharvestaddress);
        //沉浸式
        ImmersionBar.with(this)
                .statusBarColor(R.color.lanse_x_x) //指定主题颜色 意思 是在这里可以修改 styles 里面的主题颜色
                // .statusBarDarkFont(true,0.2f)    如果是白色或者透明状态的时候就加上他
                .fitsSystemWindows(true) //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
                .init();
        initView();
        initDataRecyView();
    }

    private void initDataRecyView() {
        //初始化recycleView
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        //设置 mRecyclerView 的管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ShoppingHarvestAdapter(R.layout.detailofamount_item,mlist,getApplicationContext());
        mRecyclerView.setAdapter(adapter);
        //        这一句是开启 item 动画
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
    }

    private void initView() {
        image_back = (ImageView) findViewById(R.id.image_back);
        image_back.setOnClickListener(this);
        btn_add_address = (Button) findViewById(R.id.btn_add_address);
        btn_add_address.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_back:
                finish();
                break;
            //跳转到仿淘宝地址选择器
            case R.id.btn_add_address:
                startActivity(new Intent(getApplicationContext(),AddressSelectorActivity.class));
                break;
        }
    }
}
