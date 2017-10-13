package com.example.administrator.mysharedumbrella01.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.entivity.GetShoppingJiluBean;
import com.example.administrator.mysharedumbrella01.entivity.ShoppingJiLubean;
import com.example.administrator.mysharedumbrella01.utils.DateUtil;

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
public class ShoppingYaJinJluAdapter extends BaseQuickAdapter<GetShoppingJiluBean.DataBean,BaseViewHolder>{
    private Context context;
    public ShoppingYaJinJluAdapter(@LayoutRes int layoutResId, @Nullable List<GetShoppingJiluBean.DataBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, GetShoppingJiluBean.DataBean item) {
        //设置时间
        helper.setText(R.id.tv_year, item.getTransaction_time());
        //单号
        helper.setText(R.id.tv_danhao,item.getOut_trade_no());
        //设置充值的金额是多少
        helper.setText(R.id.tv_yajin,item.getMoney()+"￥");
        //设置支付方式
        String style = item.getPay_way();
        if (style.equals("1")) {
            helper.setText(R.id.tv_zhifuStyle, " 微信支付");
        } else if (style.equals("2")){
            helper.setText(R.id.tv_zhifuStyle, " 支付宝支付");
        }

    }
}
