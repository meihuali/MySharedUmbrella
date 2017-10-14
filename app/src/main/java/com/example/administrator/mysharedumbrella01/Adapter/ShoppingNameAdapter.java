package com.example.administrator.mysharedumbrella01.Adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.entivity.AdminAuthenticationBean;

import java.util.List;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.Adapter
 * 文件名：ShoppingNameAdapter
 * 创建者 ：梅华黎
 * 创建时间： 2017/10/14 0014 10:07
 * 描述：TODO
 */
public class ShoppingNameAdapter extends BaseQuickAdapter<AdminAuthenticationBean.DataBean,BaseViewHolder> {

    public ShoppingNameAdapter(int layoutResId, @Nullable List<AdminAuthenticationBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AdminAuthenticationBean.DataBean item) {
        //商家名字
        helper.setText(R.id.tv_shopping_name,item.getMerchantname());
    }
}
