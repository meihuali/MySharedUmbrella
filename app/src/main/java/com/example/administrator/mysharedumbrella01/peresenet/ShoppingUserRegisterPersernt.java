package com.example.administrator.mysharedumbrella01.peresenet;

import com.example.administrator.mysharedumbrella01.Impl.ShoppingUserRegisterImpl;
import com.example.administrator.mysharedumbrella01.model.IsShoppingUserRegisterModel;
import com.example.administrator.mysharedumbrella01.view.IsShoppingUserRegisterView;

import java.io.File;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.peresenet
 * 文件名：ShoppingUserRegisterPersernt
 * 创建者 ：梅华黎
 * 创建时间： 2017/9/7 0007 17:55
 * 描述：商家注册
 */
public class ShoppingUserRegisterPersernt {
    IsShoppingUserRegisterModel shoppingUserRegisterModel;
    IsShoppingUserRegisterView shoppingUserRegisterView;

    public ShoppingUserRegisterPersernt(IsShoppingUserRegisterView shoppingUserRegisterView) {
        this.shoppingUserRegisterView = shoppingUserRegisterView;
        shoppingUserRegisterModel = new ShoppingUserRegisterImpl();
    }

    public void shoppingRegsiert(String dianming,String shoujihaoma,File file,String address)
                                 {
        if (shoppingUserRegisterModel != null) {
            shoppingUserRegisterModel.ShoppingRegister(new IsShoppingUserRegisterModel.onShoppingLisenerts() {
                @Override
                public void onComplte(Object object) {
                    shoppingUserRegisterView.showShoppingRrelst(object);
                }
            },dianming, shoujihaoma,file,address
                     );
        }
    }
}
