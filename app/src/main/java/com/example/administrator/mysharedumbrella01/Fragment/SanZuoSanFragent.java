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
import com.example.administrator.mysharedumbrella01.Adapter.ShoppingHarvestAdapter;
import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.entivity.SanZuoSanRecordBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.Fragment
 * 文件名：SanZuoSanFragent
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/11 0011 19:15
 * 描述：伞座 / 伞记录
 */
public class SanZuoSanFragent extends Fragment {

    private RecyclerView mRecyclerView;
    private SanZuoSanRecordAdapter adapter;
    private List<SanZuoSanRecordBean> mlist = new ArrayList<>();
    private  SanZuoSanRecordBean  sanzuoData;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       View view = inflater.inflate(R.layout.activity_saozuosao,null);
        initView(view);
      //  initData(view);
        return view;
    }
    /*
    * 数据
    * */
    private void initData(View view) {
        for (int i = 0; i <100; i++) {
            sanzuoData = new SanZuoSanRecordBean();
            sanzuoData.setYearMothdata("2017-09-14");
            sanzuoData.setTime("08: 36");
            sanzuoData.setSanzuoCount("88");
            sanzuoData.setYusanCount("88");
            sanzuoData.setBianhao("13145201314520");
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
        adapter = new SanZuoSanRecordAdapter(R.layout.suozuosanrecord_item,mlist,getActivity());
        mRecyclerView.setAdapter(adapter);
        //        这一句是开启 item 动画
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
    }

}
