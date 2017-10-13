package com.example.administrator.mysharedumbrella01.peresenet;

import com.example.administrator.mysharedumbrella01.Impl.GetShoppingYajinImpl;
import com.example.administrator.mysharedumbrella01.model.IsGetShoppingYajinModel;
import com.example.administrator.mysharedumbrella01.ui.InvitingFriendsAcitivity;
import com.example.administrator.mysharedumbrella01.view.IsGetShoppingYajinView;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.peresenet
 * 文件名：GetShoppingYajinPerserent
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/21 0021 16:36
 * 描述：TODO
 */
public class GetShoppingYajinPerserent {
    IsGetShoppingYajinModel getShoppingYajinModel;
    IsGetShoppingYajinView getShoppingYajinView;

    public GetShoppingYajinPerserent(IsGetShoppingYajinView getShoppingYajinView) {
        this.getShoppingYajinView = getShoppingYajinView;
        getShoppingYajinModel = new GetShoppingYajinImpl();
    }

    public void getyajin(String phone) {
        if (getShoppingYajinModel != null) {
            getShoppingYajinModel.getyajin(new IsGetShoppingYajinModel.onGetShoppingYaJinLinerset() {
                @Override
                public void onComplte(Object object) {
                    getShoppingYajinView.showGetYaJin(object);
                }
            },phone);
        }
    }
}
