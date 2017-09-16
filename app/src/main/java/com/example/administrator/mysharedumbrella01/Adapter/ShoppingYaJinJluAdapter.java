package com.example.administrator.mysharedumbrella01.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.entivity.ShoppingJiLubean;

import java.util.List;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.Adapter
 * 文件名：ShoppingYaJinJluAdapter
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/14 0014 11:12
 * 描述：商家押金记录 适配器
 */
public class ShoppingYaJinJluAdapter extends BaseQuickAdapter<ShoppingJiLubean,BaseViewHolder>{
    private Context context;
    public ShoppingYaJinJluAdapter(@LayoutRes int layoutResId, @Nullable List<ShoppingJiLubean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ShoppingJiLubean item) {
        helper.setText(R.id.tv_year,item.getTime());
        helper.setText(R.id.tv_danhao,item.getDanhao());
        helper.setText(R.id.tv_yajin,item.getYajin());
        helper.setText(R.id.tv_zhifuStyle,item.getStyle());
    }
}
