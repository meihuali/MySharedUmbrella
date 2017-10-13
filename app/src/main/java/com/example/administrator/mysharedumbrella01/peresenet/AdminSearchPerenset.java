package com.example.administrator.mysharedumbrella01.peresenet;

import com.example.administrator.mysharedumbrella01.Impl.AdminSearchImpl;
import com.example.administrator.mysharedumbrella01.model.IsAdminSearchModel;
import com.example.administrator.mysharedumbrella01.view.IsAdminSearchView;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.peresenet
 * 文件名：AdminSearchPerenset
 * 创建者 ：梅华黎
 * 创建时间： 2017/10/13 0013 15:49
 * 描述：TODO
 */
public class AdminSearchPerenset {
    IsAdminSearchModel adminSearchModel;
    IsAdminSearchView  adminSearchView;

    public AdminSearchPerenset(IsAdminSearchView adminSearchView) {
        this.adminSearchView = adminSearchView;
        adminSearchModel = new AdminSearchImpl();
    }

    public void adminsearch(String umbrellaid) {
        if (adminSearchModel != null) {
            adminSearchModel.adminsearch(new IsAdminSearchModel.onAdminSearchLinerest() {
                @Override
                public void onComplte(Object object) {
                    adminSearchView.showRelust(object);
                }
            },umbrellaid);
        }

    }
}
