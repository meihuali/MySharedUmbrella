package com.example.administrator.mysharedumbrella01.peresenet;

import com.example.administrator.mysharedumbrella01.Impl.ShoppingJiluImpl;
import com.example.administrator.mysharedumbrella01.model.IsShoppingJiluModel;
import com.example.administrator.mysharedumbrella01.view.IsShoppingJiluView;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.peresenet
 * 文件名：ShoppingJiLuPerserent
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/21 0021 17:09
 * 描述：商家充值 记录
 */
public class ShoppingJiLuPerserent {
    IsShoppingJiluModel shoppingJiluModel;
    IsShoppingJiluView shoppingJiluView ;

    public ShoppingJiLuPerserent(IsShoppingJiluView shoppingJiluView) {
        this.shoppingJiluView = shoppingJiluView;
        shoppingJiluModel  = new ShoppingJiluImpl();
    }

    public void chongzhijilu(String phone) {
        if (shoppingJiluModel != null) {
            shoppingJiluModel.shoppingJilu(new IsShoppingJiluModel.onShoppingJiLuLinserset() {
                @Override
                public void onComplte(Object object) {
                    shoppingJiluView.showJiLu(object);
                }
            },phone);
        }
    }
}
