package com.example.administrator.mysharedumbrella01.model;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.model
 * 文件名：IsShoppingConmfirGoodsModel
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/20 0020 17:03
 * 描述：点击 确认收获的列表的时候 确认按钮 接口
 */
public interface IsShoppingConmfirGoodsModel {
    void conmfirGoodsService(onConmfirGoodsSeviceLinerset linerset,String phone,String stand,String umbrella);

    interface onConmfirGoodsSeviceLinerset{
        void onComplte(Object object);
    }
}
