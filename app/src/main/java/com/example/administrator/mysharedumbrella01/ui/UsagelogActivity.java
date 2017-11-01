package com.example.administrator.mysharedumbrella01.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.mysharedumbrella01.Adapter.HistoricalAdapter;
import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.entivity.HistoryBean;
import com.example.administrator.mysharedumbrella01.peresenet.HisyoricalPeresenet;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.example.administrator.mysharedumbrella01.view.HisyoricalRecordView;
import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.whyalwaysmea.circular.AnimUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/2 0002.
 * // 历史记录 activity
 */

public class UsagelogActivity extends AppCompatActivity implements HisyoricalRecordView, View.OnClickListener {
    private List<HistoryBean.DataBean> list1 = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private HistoricalAdapter historicalAdapter;
    private ImageView image_back;
    private int curPage = 0;
    private String zhanghao;
    private  int isroot;
    private View rl_waicheng;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usagelog);

        AnimUtils.animhpel(this,R.id.rl_waicheng);
        //沉浸式
/*        ImmersionBar.with(this)
                .statusBarColor(R.color.zhutiyanse) //指定主题颜色 意思 是在这里可以修改 styles 里面的主题颜色
                .fitsSystemWindows(true) //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
                .init();*/
        ImmersionBar.with(this)
                .init();

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
        historicalAdapter = new HistoricalAdapter(R.layout.hisyoriccal_item,list1,getApplicationContext());
        mRecyclerView.setAdapter(historicalAdapter);
//        这一句是开启 item 动画
        historicalAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);

        //初始化刷新的 smartlayout
        final RefreshLayout refreshLayout = (RefreshLayout) findViewById(R.id.smartLayout);
        //开启自动加载功能（非必须，也就是说可以不加这句话·）
//        refreshLayout.setEnableAutoLoadmore(true);
        //关闭 上拉加载功能
        refreshLayout.setEnableAutoLoadmore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {

                ((View)refreshlayout).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list1.clear();
                        //这里做业务处理
                        curPage+=10;
                        HisyoricalPeresenet hp = new HisyoricalPeresenet(UsagelogActivity.this);
                        hp.fach(UsagelogActivity.this,isroot,zhanghao,zhanghao,"10",curPage+"");
                        refreshlayout.finishRefresh();
                    }
                },2000);
            }
        });
    }

    private void initData() {
        isroot = ShareUtils.getInt(getApplicationContext(),"isroots",0);
        zhanghao  =  ShareUtils.getString(getApplicationContext(),"zhanghao","");
        HisyoricalPeresenet hp = new HisyoricalPeresenet(this);
        hp.fach(this,isroot,zhanghao,zhanghao,"10","10");

    }

    @Override
    public void showHisyor(List<HistoryBean.DataBean> list,int isroot,String zhanghao) {
        int sizes = list.size();
        L.e("sizes "+sizes );
        list1.addAll(list);
        historicalAdapter.notifyDataSetChanged();


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                finish();
                break;
        }
    }
}
