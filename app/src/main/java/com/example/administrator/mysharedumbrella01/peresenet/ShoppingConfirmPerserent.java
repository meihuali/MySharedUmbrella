package com.example.administrator.mysharedumbrella01.peresenet;

import com.example.administrator.mysharedumbrella01.Impl.ShoppingConfirmImpl;
import com.example.administrator.mysharedumbrella01.model.IsShoppingConfirmModel;
import com.example.administrator.mysharedumbrella01.view.IsShoppingConfirmView;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.peresenet
 * 文件名：ShoppingConfirmPerserent
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/20 0020 16:26
 * 描述：TODO
 */
public class ShoppingConfirmPerserent {
    IsShoppingConfirmModel shoppingConfirmModel;
    IsShoppingConfirmView shoppingConfirmView;

    public ShoppingConfirmPerserent(IsShoppingConfirmView shoppingConfirmView) {
        this.shoppingConfirmView = shoppingConfirmView;
        shoppingConfirmModel = new ShoppingConfirmImpl();
    }

    public void confirmGoods(String phone) {
        if (shoppingConfirmModel != null) {
            shoppingConfirmModel.shoppingConfirm(new IsShoppingConfirmModel.onShopingConfirmLinsert() {
                @Override
                public void onComplte(Object object) {
                    shoppingConfirmView.showRelust(object);
                }
            },phone);
        }
    }
}
