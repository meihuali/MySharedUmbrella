package com.example.administrator.mysharedumbrella01.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.mysharedumbrella01.entivity.ShoppingCityAddressBean;

import java.util.List;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.Adapter
 * 文件名：ShoppingHarvestAdapter
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/13 0013 16:39
 * 描述：商家收获地址 列表 adapter
 */
public class ShoppingHarvestAdapter extends BaseQuickAdapter<List<ShoppingCityAddressBean>,BaseViewHolder> {

    private Context context;
    public ShoppingHarvestAdapter(@LayoutRes int layoutResId, List<ShoppingCityAddressBean> data, Context context) {
        super(layoutResId);
        this.context  = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, List<ShoppingCityAddressBean> item) {

    }
}
