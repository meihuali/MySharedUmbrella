package com.example.administrator.mysharedumbrella01.Adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.entivity.DepositRechargeBean;

import java.util.List;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.Adapter
 * 文件名：DraAdapter
 * 创建者 ：梅华黎
 * 创建时间： 2017/9/4 0004 11:05
 * 描述：TODO
 */
public class DraAdapter extends BaseQuickAdapter<DepositRechargeBean,BaseViewHolder> {

    private List<DepositRechargeBean> list;
    private Context context;

    public DraAdapter(int layoutResId, List<DepositRechargeBean> list, Context context) {
        super(layoutResId);
        this.list = list;
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, DepositRechargeBean depositRechargeBean) {

        TextView btn_sets =  baseViewHolder.getView(R.id.button);
        //这句话是设置textview 的文字
        btn_sets.setText("充"+depositRechargeBean.getMoney()+"元");

        if (depositRechargeBean.isSeceket()) {
            btn_sets.setBackgroundResource(R.drawable.jianbian_x);
            btn_sets.setTextColor(context.getResources().getColor(R.color.white));
        } else {
            btn_sets.setBackgroundResource(R.drawable.button_yuanjiaokongxin);
            btn_sets.setTextColor(context.getResources().getColor(R.color.shenhuise_x));
        }
    }
}
