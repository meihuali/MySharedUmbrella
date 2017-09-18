package com.example.administrator.mysharedumbrella01.peresenet;

import com.example.administrator.mysharedumbrella01.Impl.GetShoppingAddressImpl;
import com.example.administrator.mysharedumbrella01.model.IsGetShoppingAddressModel;
import com.example.administrator.mysharedumbrella01.view.IsGetShoppingAddressView;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.peresenet
 * 文件名：GetShoppingAddressPerserent
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/18 0018 12:11
 * 描述：获取商家新增收获地址
 */
public class GetShoppingAddressPerserent {
    IsGetShoppingAddressModel getShoppingAddressModel;
    IsGetShoppingAddressView getShoppingAddressView;

    public GetShoppingAddressPerserent(IsGetShoppingAddressView getShoppingAddressView) {
        this.getShoppingAddressView = getShoppingAddressView;
        getShoppingAddressModel = new GetShoppingAddressImpl();
    }

    public void getaddress(String merchant_id) {
        if (getShoppingAddressModel != null) {
            getShoppingAddressModel.getAddress(new IsGetShoppingAddressModel.onGetAddressLinerster() {
                @Override
                public void onComplte(Object object) {
                    getShoppingAddressView.showRelust(object);
                }
            }, merchant_id);
        }
    }
}
