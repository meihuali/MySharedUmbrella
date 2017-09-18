package com.example.administrator.mysharedumbrella01.peresenet;

import android.view.View;

import com.example.administrator.mysharedumbrella01.Impl.EditShoppingAddressImpl;
import com.example.administrator.mysharedumbrella01.model.IsEditShoppingAddressModel;
import com.example.administrator.mysharedumbrella01.view.IsEditShoppingAddressView;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.peresenet
 * 文件名：EditShoppingAddressPerserent
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/18 0018 16:01
 * 描述：TODO
 */
public class EditShoppingAddressPerserent {
    IsEditShoppingAddressModel editShoppingAddressModel;
    IsEditShoppingAddressView editShoppingAddressView;

    public EditShoppingAddressPerserent(IsEditShoppingAddressView editShoppingAddressView) {
        this.editShoppingAddressView = editShoppingAddressView;
        editShoppingAddressModel = new EditShoppingAddressImpl();
    }

    public void editAddress(String name,String phone,String zipcode,String region,String address,String merchant_id,String is_inuser,String address_id) {
        if (editShoppingAddressModel != null) {
            editShoppingAddressModel.editAddress(new IsEditShoppingAddressModel.onEditAddressLinserst() {
                @Override
                public void onComplte(Object object) {
                    editShoppingAddressView.showRlrouts(object);
                }
            }, name, phone, zipcode, region, address, merchant_id, is_inuser, address_id);
        }
    }
}
