package com.example.administrator.mysharedumbrella01.peresenet;

import com.example.administrator.mysharedumbrella01.Impl.ShoppingSettingAddressImpl;
import com.example.administrator.mysharedumbrella01.model.IsShoppingSettingAddressModel;
import com.example.administrator.mysharedumbrella01.view.IsShoppingSettingAddressView;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.peresenet
 * 文件名：ShoppingSettingAddressPerserent
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/19 0019 11:57
 * 描述：TODO
 */
public class ShoppingSettingAddressPerserent {
    IsShoppingSettingAddressModel shoppingSettingAddressModel;
    IsShoppingSettingAddressView shoppingSettingAddressView;

    public ShoppingSettingAddressPerserent(IsShoppingSettingAddressView shoppingSettingAddressView) {
        this.shoppingSettingAddressView = shoppingSettingAddressView;
        shoppingSettingAddressModel = new ShoppingSettingAddressImpl();
    }

    public void setttingaddress(String merchant_id ,String address_id) {
        if (shoppingSettingAddressModel != null) {
            shoppingSettingAddressModel.settingaddress(new IsShoppingSettingAddressModel.onSettingAddressLinerset() {
                @Override
                public void onComplte(Object object) {
                    shoppingSettingAddressView.showSettingRelust(object);
                }
            }, merchant_id , address_id);
        }

    }
}
