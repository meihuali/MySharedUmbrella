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

import java.util.List;

/**
 * Created by Administrator on 2017/7/12 0012.
 */

public class FanKuiAdapter extends BaseQuickAdapter<FanKuiBean, BaseViewHolder> {
    private Context context;
    private List<FanKuiBean> data;

    public FanKuiAdapter(@LayoutRes int layoutResId, @Nullable List<FanKuiBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
        this.data = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, FanKuiBean item) {
        //这里是选择item 的时候修改背景
         TextView button1 = helper.getView(R.id.button);
        if (item.isSelect()) {
            button1.setBackgroundResource(R.drawable.button_xiaoyuanjiao_juse);
        } else {
            button1.setBackgroundResource(R.drawable.button_yuanjiaokongxin);
        }

    }
}
