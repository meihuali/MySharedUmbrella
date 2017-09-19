package com.example.administrator.mysharedumbrella01.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.entivity.SanZuoSanRecordBean;
import com.example.administrator.mysharedumbrella01.entivity.ShoppingRecorBean;
import com.example.administrator.mysharedumbrella01.utils.DateUtil;

import java.util.List;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.Adapter
 * 文件名：SanZuoSanRecordAdapter
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/14 0014 9:26
 * 描述：伞座/伞 的记录
 */
public class SanZuoSanRecordAdapter extends BaseQuickAdapter<ShoppingRecorBean.DataBean ,BaseViewHolder>{
    private Context context;


    public SanZuoSanRecordAdapter(@LayoutRes int layoutResId, List<ShoppingRecorBean.DataBean> data, Context context) {
        super(layoutResId,data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ShoppingRecorBean.DataBean item) {
        //设置时间
        helper.setText(R.id.tv_year, DateUtil.stampToDate(item.getC_time()));
        //设置编号
        helper.setText(R.id.tv_bianhao, item.getOrder());
        //设置派送状态
        String status = item.getDetermine(); //0为未收到正在派送中，1为以及收到了
        if (status.equals("0")) {
            helper.setText(R.id.tv_status,"正在派送中···");
        }
        if (status.equals("1")) {
            helper.setText(R.id.tv_status,"已经收货");
        }
        //设置伞座
        helper.setText(R.id.tv_sanzuoCount,item.getStand()+"个");
        //设置伞的个数
        helper.setText(R.id.tv_yusanCount,item.getUmbrella()+"个");

    }
}
