package com.example.administrator.mysharedumbrella01.peresenet;

import com.example.administrator.mysharedumbrella01.Impl.ShangWuzhongxinRenZhengImpl;
import com.example.administrator.mysharedumbrella01.model.IsShoppingShangwuzhongxinModel;
import com.example.administrator.mysharedumbrella01.view.IsShangWuzhongxinRenzhengView;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.peresenet
 * 文件名：ShangWuzhongxinrenzhengPersert
 * 创建者 ：梅华黎
 * 创建时间： 2017/9/9 0009 14:19
 * 描述：商务中心所有数据包括认证
 */
public class ShangWuzhongxinrenzhengPersert {
    IsShoppingShangwuzhongxinModel shoppingShangwuzhongxinModel;
    IsShangWuzhongxinRenzhengView shangWuzhongxinRenzhengView;

    public ShangWuzhongxinrenzhengPersert(IsShangWuzhongxinRenzhengView shangWuzhongxinRenzhengView) {
        this.shangWuzhongxinRenzhengView = shangWuzhongxinRenzhengView;
        shoppingShangwuzhongxinModel = new ShangWuzhongxinRenZhengImpl();
    }

    public void shangwuzhongxinrenzhen(String id) {
        if (shoppingShangwuzhongxinModel != null) {
            shoppingShangwuzhongxinModel.getshangwuzhongxinData(new IsShoppingShangwuzhongxinModel.onShangwuzhongxindata() {
                @Override
                public void onComptle(Object object) {
                    shangWuzhongxinRenzhengView.showSocces(object);
                }

                @Override
                public void onErrorComptle() {
                    shangWuzhongxinRenzhengView.showError();
                }
            },id);
        }
    }
}
