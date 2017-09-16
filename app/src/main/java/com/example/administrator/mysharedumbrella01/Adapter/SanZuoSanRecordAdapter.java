package com.example.administrator.mysharedumbrella01.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.entivity.SanZuoSanRecordBean;

import java.util.List;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.Adapter
 * 文件名：SanZuoSanRecordAdapter
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/14 0014 9:26
 * 描述：伞座/伞 的记录
 */
public class SanZuoSanRecordAdapter extends BaseQuickAdapter<SanZuoSanRecordBean ,BaseViewHolder>{
    private Context context;


    public SanZuoSanRecordAdapter(@LayoutRes int layoutResId, List<SanZuoSanRecordBean> data, Context context) {
        super(layoutResId,data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, SanZuoSanRecordBean item) {

        helper.setText(R.id.tv_year,item.getYearMothdata());
        helper.setText(R.id.tv_time,item.getTime());
        helper.setText(R.id.tv_sanzuoCount,item.getSanzuoCount());
        helper.setText(R.id.tv_yusanCount,item.getYusanCount());
        helper.setText(R.id.tv_bianhao,item.getBianhao());
    }
}
