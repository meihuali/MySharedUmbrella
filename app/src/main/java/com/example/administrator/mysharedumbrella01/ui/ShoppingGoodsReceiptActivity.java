package com.example.administrator.mysharedumbrella01.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.mysharedumbrella01.Adapter.DetailoFamountAdapter;
import com.example.administrator.mysharedumbrella01.Adapter.ShoppingGoodsReceiptAdapter;
import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.entivity.ShoppingGoodsReceiptBean;
import com.gyf.barlibrary.ImmersionBar;
import com.whyalwaysmea.circular.AnimUtils;

import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.ui
 * 文件名：ShoppingGoodsReceiptActivity
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/14 0014 14:10
 * 描述：商家 货物签收
 */
public class ShoppingGoodsReceiptActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private List<ShoppingGoodsReceiptBean> mList = new ArrayList<>();
    private ShoppingGoodsReceiptAdapter adapter;
    private ShoppingGoodsReceiptBean spgoods;
    private ImageView image_back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppinggoodsreceiptactivity);
        AnimUtils.animhpel(this,R.id.ll_layout);

        //沉浸式
        ImmersionBar.with(this)
                .statusBarColor(R.color.lanse_x_x) //指定主题颜色 意思 是在这里可以修改 styles 里面的主题颜色
                // .statusBarDarkFont(true,0.2f)    如果是白色或者透明状态的时候就加上他
                .fitsSystemWindows(true) //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
                .init();
        initView();
        initData();
    }
    /*
    * 获取数据源
    * */
    private void initData() {
        for (int i = 0; i < 100; i++) {
            spgoods = new ShoppingGoodsReceiptBean();
            spgoods.setDanhao("1531351321231");
            spgoods.setAddress("广州市天河区，中山大道中路1192号，盈丰商务大厦，A栋515");
            spgoods.setNewSanZuoCount("3个");
            spgoods.setNewYuSanCuont("20把");
            spgoods.setSum("￥300.00");
            spgoods.setSanzuo("2个");
            spgoods.setYusan("15把");
            spgoods.setWqyusan("5把");
            spgoods.setWqsanzuo("1个");
            mList.add(spgoods);
        }
        adapter.addData(mList);
        adapter.notifyDataSetChanged();
    }

    /*
    * 初始化
    * */
    private void initView() {
        image_back = (ImageView) findViewById(R.id.image_back);
        image_back.setOnClickListener(this);
        //初始化recycleView
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        //设置 mRecyclerView 的管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ShoppingGoodsReceiptAdapter(R.layout.activity_shoppinggoods,mList);
        mRecyclerView.setAdapter(adapter);
        //        这一句是开启 item 动画
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_back:
             //   finish();
                AnimUtils.finishAmins((Activity) this,R.id.ll_xxxx,v,R.id.ll_layout);
                break;
        }
    }
}
