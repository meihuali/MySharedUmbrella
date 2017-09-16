package com.example.administrator.mysharedumbrella01.peresenet;

import com.example.administrator.mysharedumbrella01.Impl.ShoppingQueryAuthentionImpl;
import com.example.administrator.mysharedumbrella01.model.IsShoppingQueryAuthenticationModel;
import com.example.administrator.mysharedumbrella01.view.IsShopingQueryAuthentionView;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.peresenet
 * 文件名：ShoppingQueryAuthentionPerserent
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/13 0013 14:09
 * 描述：TODO
 */
public class ShoppingQueryAuthentionPerserent {
    IsShoppingQueryAuthenticationModel shoppingQueryAuthenticationModel;
    IsShopingQueryAuthentionView shopingQueryAuthentionView;

    public ShoppingQueryAuthentionPerserent(IsShopingQueryAuthentionView shopingQueryAuthentionView) {
        this.shopingQueryAuthentionView = shopingQueryAuthentionView;
        shoppingQueryAuthenticationModel = new ShoppingQueryAuthentionImpl();
    }

    public void shoppingAut(String phone) {
        if (shoppingQueryAuthenticationModel != null) {
            shoppingQueryAuthenticationModel.QueryAutent(new IsShoppingQueryAuthenticationModel.onQueryAutentLinserts() {
                @Override
                public void onComplte(Object object) {
                    shopingQueryAuthentionView.showComplte(object);
                }
            },phone);
        }
    }
}
