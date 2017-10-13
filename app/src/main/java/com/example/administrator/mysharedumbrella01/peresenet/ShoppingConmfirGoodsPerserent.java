package com.example.administrator.mysharedumbrella01.peresenet;

import com.example.administrator.mysharedumbrella01.Impl.ShoppingConmfirGoodsImpl;
import com.example.administrator.mysharedumbrella01.model.IsShoppingConmfirGoodsModel;
import com.example.administrator.mysharedumbrella01.view.IsShoppingConmfirGoodsView;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.peresenet
 * 文件名：ShoppingConmfirGoodsPerserent
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/20 0020 17:08
 * 描述：TODO
 */
public class ShoppingConmfirGoodsPerserent {
    IsShoppingConmfirGoodsModel shoppingConmfirGoodsModel;
    IsShoppingConmfirGoodsView shoppingConmfirGoodsView;

    public ShoppingConmfirGoodsPerserent(IsShoppingConmfirGoodsView shoppingConmfirGoodsView) {
        this.shoppingConmfirGoodsView = shoppingConmfirGoodsView;
        shoppingConmfirGoodsModel = new ShoppingConmfirGoodsImpl();
    }

    public void confirGoods(String phone, String stand, String umbrella) {
        if (shoppingConmfirGoodsModel != null) {
            shoppingConmfirGoodsModel.conmfirGoodsService(new IsShoppingConmfirGoodsModel.onConmfirGoodsSeviceLinerset() {
                @Override
                public void onComplte(Object object) {
                    shoppingConmfirGoodsView.showConfri(object);
                }
            }, phone,  stand,  umbrella);
        }
    }
}
