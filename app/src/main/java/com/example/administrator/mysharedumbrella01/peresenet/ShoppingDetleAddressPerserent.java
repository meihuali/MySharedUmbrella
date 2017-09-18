package com.example.administrator.mysharedumbrella01.peresenet;

import com.example.administrator.mysharedumbrella01.Impl.ShoppingDetleAddressImpl;
import com.example.administrator.mysharedumbrella01.model.IsShoppingDetleAddressModle;
import com.example.administrator.mysharedumbrella01.view.IsShoppingDetleAddressView;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.peresenet
 * 文件名：ShoppingDetleAddressPerserent
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/18 0018 19:28
 * 描述：TODO
 */
public class ShoppingDetleAddressPerserent {
    IsShoppingDetleAddressModle shoppingDetleAddressModle;
    IsShoppingDetleAddressView shoppingDetleAddressView;

    public ShoppingDetleAddressPerserent(IsShoppingDetleAddressView shoppingDetleAddressView) {
        this.shoppingDetleAddressView = shoppingDetleAddressView;
        shoppingDetleAddressModle = new ShoppingDetleAddressImpl();
    }

    public void detleAddress(String address_id) {
        if (shoppingDetleAddressModle != null) {
            shoppingDetleAddressModle.detelAddress(new IsShoppingDetleAddressModle.onDelteAddressLinerset() {
                @Override
                public void onComplte(Object object) {
                    shoppingDetleAddressView.ShowComplte(object);
                }
            },address_id);
        }

    }
}
