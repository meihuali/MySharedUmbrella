package com.example.administrator.mysharedumbrella01.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.entivity.FanKuiBean;
import com.example.administrator.mysharedumbrella01.entivity.KeFuFanKuiBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/12 0012.
 */

public class FanKuiAdapter extends BaseQuickAdapter<KeFuFanKuiBean.DataBean, BaseViewHolder> {
    private Context context;
    private List<KeFuFanKuiBean.DataBean> data;

    public FanKuiAdapter(@LayoutRes int layoutResId, @Nullable List<KeFuFanKuiBean.DataBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
        this.data = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, KeFuFanKuiBean.DataBean item) {
        //这里是选择item 的时候修改背景
        TextView button1 = helper.getView(R.id.button);
        //这句话是设置textview 的文字
        button1.setText(item.getCate_name());
        //下面的这个判断是 获取某个item 中的 Select属性 是否为true 或者为true
        if (item.isSelect()) {
            button1.setBackgroundResource(R.drawable.jianbian_x);
            button1.setTextColor(context.getResources().getColor(R.color.white));
        } else {
            button1.setBackgroundResource(R.drawable.button_yuanjiaokongxin_lanse);
            button1.setTextColor(context.getResources().getColor(R.color.black));
        }

    }
}
