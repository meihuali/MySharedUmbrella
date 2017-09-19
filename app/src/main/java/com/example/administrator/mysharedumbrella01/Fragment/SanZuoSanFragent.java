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
import com.example.administrator.mysharedumbrella01.entivity.ShoppingRecorBean;
import com.example.administrator.mysharedumbrella01.peresenet.ShoppingRecorPerserent;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.example.administrator.mysharedumbrella01.utils.ToastUtil;
import com.example.administrator.mysharedumbrella01.view.IsShoppingRecorView;

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
public class SanZuoSanFragent extends Fragment implements IsShoppingRecorView {

    private RecyclerView mRecyclerView;
    private SanZuoSanRecordAdapter adapter;
    private List<ShoppingRecorBean.DataBean> mlist = new ArrayList<>();
    private  SanZuoSanRecordBean  sanzuoData;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       View view = inflater.inflate(R.layout.activity_saozuosao,null);
        initView(view);
         initData(view);
        return view;
    }
    /*
    * 数据
    * */
    private void initData(View view) {
        //获取手机号码
        String zh =   ShareUtils.getString(getActivity(),"zhanghao","");
        ShoppingRecorPerserent shprecor = new ShoppingRecorPerserent(this);
        shprecor.sprecor(zh);
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

    @Override
    public void showRelst(Object object) {
        ShoppingRecorBean srb = (ShoppingRecorBean) object;
        int status = srb.getStatus();
        if (status == 1) {
            List<ShoppingRecorBean.DataBean> list =  srb.getData();
            adapter.addData(list);
            adapter.notifyDataSetChanged();
        } else {
            ToastUtil.showShortToast(getActivity(),"获取商家记录失败");
        }
    }
}
