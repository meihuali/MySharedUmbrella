package com.example.administrator.mysharedumbrella01.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.entivity.DetailoFamounBean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/9 0009.
 */

public class DetailoFamountAdapter extends BaseQuickAdapter<DetailoFamounBean.DataBean,BaseViewHolder> {
    private Context context;
    private List<DetailoFamounBean.DataBean> list;
    public DetailoFamountAdapter(int layoutResId, Context context, List<DetailoFamounBean.DataBean> list) {
        super(layoutResId);
        this.context = context;
        this.list = list;
    }

    @Override
    protected void convert(BaseViewHolder helper, DetailoFamounBean.DataBean item) {

            //支付金额
            String money =  item.getMoney();
            helper.setText(R.id.tv_money,"￥"+money);
            //支付是否成功
            String status = item.getStatus();
            helper.setText(R.id.tv_status,status);
            //支付时间
            String time = item.getPay_time();
            helper.setText(R.id.tv_pay_time,time);
            //支付类型(有微信支付跟 支付宝两种)
            String  way = item.getWay();
            helper.setText(R.id.tv_pay_way, way);

    }
}
