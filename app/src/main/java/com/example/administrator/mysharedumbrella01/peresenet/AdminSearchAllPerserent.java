package com.example.administrator.mysharedumbrella01.peresenet;

import com.example.administrator.mysharedumbrella01.Impl.AdminSearchAllImpl;
import com.example.administrator.mysharedumbrella01.model.IsAdminSearchAllModel;
import com.example.administrator.mysharedumbrella01.view.IsAdminSearchAllView;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.peresenet
 * 文件名：AdminSearchAllPerserent
 * 创建者 ：梅华黎
 * 创建时间： 2017/10/13 0013 15:21
 * 描述：TODO
 */
public class AdminSearchAllPerserent {
    IsAdminSearchAllModel adminSearchModel;
    IsAdminSearchAllView adminSearchView;

    public AdminSearchAllPerserent(IsAdminSearchAllView adminSearchView) {
        this.adminSearchView = adminSearchView;
        adminSearchModel = new AdminSearchAllImpl();
    }

    public void adminSearchAll(String phone,String machine_id) {
        if (adminSearchModel != null) {
            adminSearchModel.onAdminSeach(new IsAdminSearchAllModel.OnAdminSeachLister() {
                @Override
                public void onComptle(Object object) {
                    adminSearchView.onAdminSearch(object);
                }
            },phone,machine_id);
        }
    }
}
