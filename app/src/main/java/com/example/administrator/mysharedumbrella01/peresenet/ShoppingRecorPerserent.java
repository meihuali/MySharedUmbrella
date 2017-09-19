package com.example.administrator.mysharedumbrella01.peresenet;

import com.example.administrator.mysharedumbrella01.Impl.ShoppingRecorImpl;
import com.example.administrator.mysharedumbrella01.model.IsShoppingRecordModel;
import com.example.administrator.mysharedumbrella01.view.IsShoppingRecorView;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.peresenet
 * 文件名：ShoppingRecorPerserent
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/19 0019 9:41
 * 描述：TODO
 */
public class ShoppingRecorPerserent {
    IsShoppingRecordModel shoppingRecordModel;
    IsShoppingRecorView shoppingRecorView;

    public ShoppingRecorPerserent(IsShoppingRecorView shoppingRecorView) {
        this.shoppingRecorView = shoppingRecorView;
        shoppingRecordModel = new ShoppingRecorImpl();
    }

    public void sprecor(String phone) {
        if (shoppingRecordModel != null) {
            shoppingRecordModel.record(new IsShoppingRecordModel.onRecordLinserent() {
                @Override
                public void onComplte(Object object) {
                    shoppingRecorView.showRelst(object);
                }
            },phone);
        }
    }
}
