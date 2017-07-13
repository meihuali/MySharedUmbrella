package com.example.administrator.mysharedumbrella01.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.entivity.HistoryBean;
import com.example.administrator.mysharedumbrella01.utils.GetTime;

import java.util.List;

/**
 * Created by Administrator on 2017/6/2 0002.
 */

public class HistoricalAdapter extends BaseQuickAdapter<HistoryBean.DataBean,BaseViewHolder> {
   private Context context;
    public HistoricalAdapter(int layoutResId, List<HistoryBean.DataBean> data,Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, HistoryBean.DataBean item) {
        if (item != null) {
            //把开始时间 时间戳 转成时间
            String starttime = item.getStarttime();
            String startTimes = GetTime.stampToDate(starttime);
            //开始时间
            helper.setText(R.id.tv_startTime,startTimes);
            //雨伞编号
            helper.setText(R.id.tv_bianhao, item.getId());
            //借伞总时间
           double duration =  item.getDuration();
            if (duration < 0) {
                helper.setText(R.id.tv_time, "正在使用中···");
            } else {
                helper.setText(R.id.tv_time,item.getDuration()+"分钟");
            }

            //借伞话费的金额
            helper.setText(R.id.tv_money,item.getExpense()+"元");
        }
    }
}
