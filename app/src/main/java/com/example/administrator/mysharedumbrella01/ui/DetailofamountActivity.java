package com.example.administrator.mysharedumbrella01.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.mysharedumbrella01.Adapter.DetailoFamountAdapter;
import com.example.administrator.mysharedumbrella01.Adapter.HistoricalAdapter;
import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.entivity.DetailoFamounBean;
import com.example.administrator.mysharedumbrella01.peresenet.DetailoFamountPerserent;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.example.administrator.mysharedumbrella01.view.IsDetailoFamounView;
import com.gyf.barlibrary.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/9 0009.
 * //  金额 明细
 */

public class DetailofamountActivity extends AppCompatActivity implements View.OnClickListener, IsDetailoFamounView,BaseQuickAdapter.RequestLoadMoreListener{
    private ImageView image_back;
    private RecyclerView mRecyclerView;
    private DetailoFamountAdapter adapter;
    //假数据集合
    private List<DetailoFamounBean.DataBean> lists = new ArrayList<>();
    //上拉加载各种变量
    int curPage = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailofamount_activity);
        //沉浸式
        ImmersionBar.with(this)
                .statusBarColor(R.color.zhutiyanse) //指定主题颜色 意思 是在这里可以修改 styles 里面的主题颜色
                // .statusBarDarkFont(true,0.2f)    如果是白色或者透明状态的时候就加上他
                .fitsSystemWindows(true) //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
                .init();
                        /*==============================================================================================*/
        initView();
        initData();
    }


    private void initView() {
        image_back = (ImageView) findViewById(R.id.image_back);
        image_back.setOnClickListener(this);
        //初始化recycleView
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        //设置 mRecyclerView 的管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DetailoFamountAdapter(R.layout.detailofamount_item,getApplicationContext(),lists);
        adapter.setOnLoadMoreListener(this, mRecyclerView);
        adapter.disableLoadMoreIfNotFullPage();
        mRecyclerView.setAdapter(adapter);
        //        这一句是开启 item 动画
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
    }
    private void initData() {
        DetailoFamountPerserent dfp = new DetailoFamountPerserent(this);
        dfp.fact(this,"10",curPage+"",adapter,1);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                finish();
                break;
        }
    }


    @Override
    public void showData(String limt, List<DetailoFamounBean.DataBean> list,int type) {
        if(type ==1){
           adapter.setNewData(list); //这里我 没看懂·
        }else{
            adapter.addData(list); // 这里 是如果·type不等于1 的时候 往adapter 里面添加数据源
        }

    }

    @Override
    public void onLoadMoreRequested() {
        curPage+=10;
        DetailoFamountPerserent dfp = new DetailoFamountPerserent(this);
        dfp.fact(this,"10",curPage+"",adapter,2);
    }
}
