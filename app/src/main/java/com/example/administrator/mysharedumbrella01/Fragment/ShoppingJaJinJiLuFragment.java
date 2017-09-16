package com.example.administrator.mysharedumbrella01.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.mysharedumbrella01.Adapter.SanZuoSanRecordAdapter;
import com.example.administrator.mysharedumbrella01.Adapter.ShoppingYaJinJluAdapter;
import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.entivity.SanZuoSanRecordBean;
import com.example.administrator.mysharedumbrella01.entivity.ShoppingJiLubean;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.Fragment
 * 文件名：ShoppingJaJinJiLuFragment
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/11 0011 19:17
 * 描述：商家押金记录
 */
public class ShoppingJaJinJiLuFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private ShoppingYaJinJluAdapter adapter;
    private List<ShoppingJiLubean> mlist = new ArrayList<>();
    private  ShoppingJiLubean  sanzuoData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_shangjiayajinjilu,null);
        initView(view);
       // initData(view);
        return view;
    }

    /*
* 数据
* */
    private void initData(View view) {
        for (int i = 0; i <100; i++) {
            sanzuoData = new ShoppingJiLubean();
            sanzuoData.setTime("2017-09-14 11: 32");
            sanzuoData.setDanhao("13145201314520");
            sanzuoData.setYajin("￥"+300);
            sanzuoData.setStyle("支付宝支付");
            mlist.add(sanzuoData);
        }
        adapter.addData(mlist);
        adapter.notifyDataSetChanged();
    }

    /*
    * 初始化数据
    * */
    private void initView(View view) {
        //初始化recycleView
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        //设置 mRecyclerView 的管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ShoppingYaJinJluAdapter(R.layout.shopingyajinjilu_item,mlist,getActivity());
        mRecyclerView.setAdapter(adapter);
        //        这一句是开启 item 动画
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
    }

}
