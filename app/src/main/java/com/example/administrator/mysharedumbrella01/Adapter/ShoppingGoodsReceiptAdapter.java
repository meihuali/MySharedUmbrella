package com.example.administrator.mysharedumbrella01.Adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.entivity.ShoppingConfirmGoodsBean;
import com.example.administrator.mysharedumbrella01.entivity.ShoppingGoodsReceiptBean;

import java.util.List;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.Adapter
 * 文件名：ShoppingGoodsReceiptAdapter
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/14 0014 15:25
 * 描述：商家 货物签收
 */
public class ShoppingGoodsReceiptAdapter extends BaseQuickAdapter<ShoppingConfirmGoodsBean.DataBean,BaseViewHolder>{

    public ShoppingGoodsReceiptAdapter(@LayoutRes int layoutResId, @Nullable List<ShoppingConfirmGoodsBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShoppingConfirmGoodsBean.DataBean item) {
        //获取新增雨伞个数
        helper.setText(R.id.tv_newSanZuo,item.getStand()+"个");
        //获取雨伞
        helper.setText(R.id.tv_newYusan,item.getUmbrella()+"个");

        //注册子控件的点击事件
        helper.addOnClickListener(R.id.btn_confirm);
    }
}
