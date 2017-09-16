package com.example.administrator.mysharedumbrella01.Adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.mysharedumbrella01.R;
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
public class ShoppingGoodsReceiptAdapter extends BaseQuickAdapter<ShoppingGoodsReceiptBean,BaseViewHolder>{

    public ShoppingGoodsReceiptAdapter(@LayoutRes int layoutResId, @Nullable List<ShoppingGoodsReceiptBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShoppingGoodsReceiptBean item) {
        //单号
        helper.setText(R.id.tv_danhao,item.getDanhao());
        //地址
        helper.setText(R.id.tv_address,item.getAddress());
        //新增伞座
        helper.setText(R.id.tv_newSanZuo, item.getNewSanZuoCount());
        //新增雨伞
        helper.setText(R.id.tv_newYusan,item.getNewYuSanCuont());
        //合计
        helper.setText(R.id.tv_sums, item.getSum());
        //实际雨伞
        helper.setText(R.id.tv_shijiyusan,item.getYusan());
        //实际伞座
        helper.setText(R.id.tv_shijisanzuo,item.getSanzuo());
        //未签收雨伞
        helper.setText(R.id.tv_sj_yusan,item.getYusan());
        //未签收伞座
        helper.setText(R.id.tv_sjsanzuo,item.getSanzuo());
    }
}
