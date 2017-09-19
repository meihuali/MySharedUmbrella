package com.example.administrator.mysharedumbrella01.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.entivity.GetShoppingAddressBean;
import com.example.administrator.mysharedumbrella01.utils.ToastUtil;

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
public class ShoppingHarvestAdapter extends BaseQuickAdapter<GetShoppingAddressBean.DataBean,BaseViewHolder> {

    private Context context;
    public ShoppingHarvestAdapter(@LayoutRes int layoutResId, List<GetShoppingAddressBean.DataBean> data, Context context) {
        super(layoutResId,data);
        this.context  = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, GetShoppingAddressBean.DataBean item) {
        //收货人姓名
        helper.setText(R.id.tv_name,item.getName());
        //收货人手机号码
        helper.setText(R.id.tv_hone, item.getPhone());
        //收货人地址
        helper.setText(R.id.tv_address, item.getRegion()+item.getAddress());
        //获取用户默认选择的状态
       String is_inuser =  item.getIs_inuser();
        if (is_inuser.equals("1")) {
            helper.getView(R.id.img_budagou).setVisibility(View.GONE);
            helper.getView(R.id.img_dagou).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.img_budagou).setVisibility(View.VISIBLE);
            helper.getView(R.id.img_dagou).setVisibility(View.GONE);
        }
        // 点击编辑按钮
        helper.addOnClickListener(R.id.tv_edit);
        //点击删除收货地址
        helper.addOnClickListener(R.id.tv_detle);
        //点击勾选状态
        helper.addOnClickListener(R.id.ll_layoutSeclet);
        if (item.isSelect()) {
            helper.getView(R.id.img_budagou).setVisibility(View.GONE);
            helper.getView(R.id.img_dagou).setVisibility(View.VISIBLE);
        } else {
            ToastUtil.showShortToast(context,"否则");
        }

    }
}
