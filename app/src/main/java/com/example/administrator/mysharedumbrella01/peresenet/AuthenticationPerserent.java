package com.example.administrator.mysharedumbrella01.peresenet;

import com.example.administrator.mysharedumbrella01.Impl.AuthenticationImpl;
import com.example.administrator.mysharedumbrella01.model.IsAuthenticationModel;
import com.example.administrator.mysharedumbrella01.view.IsAuthenticationView;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.peresenet
 * 文件名：AuthenticationPerserent
 * 创建者 ：梅华黎
 * 创建时间： 2017/9/9 0009 10:54
 * 描述：商家认证
 */
public class AuthenticationPerserent {
    IsAuthenticationModel authenticationModel;
    IsAuthenticationView authenticationView;

    public AuthenticationPerserent(IsAuthenticationView authenticationView) {
        this.authenticationView = authenticationView;
        authenticationModel = new AuthenticationImpl();
    }

    public void Shangjiarenzheng(String phone, String address, String img){
        if (authenticationModel != null) {
            authenticationModel.Authent(new IsAuthenticationModel.onAuthenticationLinerset() {
                @Override
                public void onComplet(Object object) {
                    authenticationView.showAuthenRelust(object);
                }

                @Override
                public void onErrorComplte() {

                }
            },phone,address,img);
        }
    }
}
