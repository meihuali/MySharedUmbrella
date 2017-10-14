package com.example.administrator.mysharedumbrella01.peresenet;

import com.example.administrator.mysharedumbrella01.Impl.AdminAuthenticationImpl;
import com.example.administrator.mysharedumbrella01.model.IsAdminAuthenticationModel;
import com.example.administrator.mysharedumbrella01.view.IsAdminAuthenticationView;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.peresenet
 * 文件名：AdminAuthenticationPerensert
 * 创建者 ：梅华黎
 * 创建时间： 2017/10/13 0013 16:47
 * 描述：TODO
 */
public class AdminAuthenticationPerensert {
    IsAdminAuthenticationModel adminAuthenticationModel;
    IsAdminAuthenticationView adminAuthenticationView;

    public AdminAuthenticationPerensert(IsAdminAuthenticationView adminAuthenticationView) {
        this.adminAuthenticationView = adminAuthenticationView;
        adminAuthenticationModel = new AdminAuthenticationImpl();
    }

    public void authentiact(String phone) {
        if (adminAuthenticationModel != null) {
            adminAuthenticationModel.getAdminAllAuthentication(new IsAdminAuthenticationModel.onAdminAllAuthenticonLisenert() {
                @Override
                public void onComplte(Object object) {
                    adminAuthenticationView.showRestultAuthenticon(object);
                }
            },phone);
        }
    }
}
