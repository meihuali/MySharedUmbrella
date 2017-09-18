package com.example.administrator.mysharedumbrella01.peresenet;

import com.example.administrator.mysharedumbrella01.Impl.AddAddressImpl;
import com.example.administrator.mysharedumbrella01.model.IsAddAdressModel;
import com.example.administrator.mysharedumbrella01.view.IsAddAddressView;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.peresenet
 * 文件名：AddAddressPerserent
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/18 0018 10:12
 * 描述：商家添加地址
 */
public class AddAddressPerserent {
    IsAddAdressModel addAdressModel;
    IsAddAddressView addAddressView;

    public AddAddressPerserent(IsAddAddressView addAddressView) {
        this.addAddressView = addAddressView;
        addAdressModel = new AddAddressImpl();
    }

    public void addAddress(String name,String phone,String zipcode,String region,String address,String merchant_id,int is_inuser) {
        if (addAdressModel != null) {
            addAdressModel.addAddress(new IsAddAdressModel.onAddressLinenerss() {
                @Override
                public void onComptle(Object object) {
                    addAddressView.showReluest(object);
                }
            }, name, phone, zipcode, region, address, merchant_id, is_inuser);
        }
    }
}
