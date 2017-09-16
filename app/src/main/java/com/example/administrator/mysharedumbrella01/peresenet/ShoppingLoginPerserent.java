package com.example.administrator.mysharedumbrella01.peresenet;

import com.autonavi.amap.mapcore.interfaces.IMapFragmentDelegate;
import com.example.administrator.mysharedumbrella01.Impl.ShoppingLoginImpl;
import com.example.administrator.mysharedumbrella01.model.IsShoppingLoginModel;
import com.example.administrator.mysharedumbrella01.view.IsShoppingLoginView;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.peresenet
 * 文件名：ShoppingLoginPerserent
 * 创建者 ：梅华黎
 * 创建时间： 2017/9/8 0008 10:48
 * 描述：商家登录
 */
public class ShoppingLoginPerserent {
    IsShoppingLoginModel shoppingLoginModel;
    IsShoppingLoginView shoppingLoginView;

    public ShoppingLoginPerserent(IsShoppingLoginView shoppingLoginView) {
        this.shoppingLoginView = shoppingLoginView;
        shoppingLoginModel = new ShoppingLoginImpl();
    }

    public void shoppinglogin(String phone,String pwd) {
        if (shoppingLoginModel != null) {
            shoppingLoginModel.shoppingLogin(new IsShoppingLoginModel.onShoppingLoginLinseres() {
                @Override
                public void onComplte(Object object) {
                    shoppingLoginView.ShowLoginRelust(object);
                }
            },phone,pwd);
        }
    }
}
