package com.example.administrator.mysharedumbrella01.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.mysharedumbrella01.Adapter.HistoricalAdapter;
import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.entivity.HistoryBean;
import com.example.administrator.mysharedumbrella01.peresenet.HisyoricalPeresenet;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.example.administrator.mysharedumbrella01.view.HisyoricalRecordView;
import com.gyf.barlibrary.ImmersionBar;

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
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usagelog);
        //沉浸式
        ImmersionBar.with(this)
                .statusBarColor(R.color.juse) //指定主题颜色 意思 是在这里可以修改 styles 里面的主题颜色
                .fitsSystemWindows(true) //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
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

    }

    private void initData() {
        int isroot = ShareUtils.getInt(getApplicationContext(),"isroots",0);
        String zhanghao  =  ShareUtils.getString(getApplicationContext(),"zhanghao","");
        HisyoricalPeresenet hp = new HisyoricalPeresenet(this);
        hp.fach(this,isroot,zhanghao,zhanghao);

    }

    @Override
    public void showHisyor(List<HistoryBean.DataBean> list,int isroot,String zhanghao) {
        int sizes = list.size();
        L.e("sizes"+sizes );
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
