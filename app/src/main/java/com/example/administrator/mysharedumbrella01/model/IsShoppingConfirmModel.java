package com.example.administrator.mysharedumbrella01.model;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.model
 * 文件名：IsShoppingConfirmModel
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/20 0020 16:15
 * 描述：获取确认收获列表中的雨伞跟伞座的个数
 */
public interface IsShoppingConfirmModel {
    void shoppingConfirm(onShopingConfirmLinsert linsert,String phone);

    interface onShopingConfirmLinsert{
        void onComplte(Object object);

    }
}
