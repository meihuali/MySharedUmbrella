package com.example.administrator.mysharedumbrella01.peresenet;

import android.app.Activity;

import com.example.administrator.mysharedumbrella01.Impl.LoginModelImpl;
import com.example.administrator.mysharedumbrella01.entivity.LoginBean;
import com.example.administrator.mysharedumbrella01.model.IsLognModel;
import com.example.administrator.mysharedumbrella01.view.IsLoginView;

/**
 * Created by Administrator on 2017/6/2 0002.
 * //登录用的 中间者
 */

public class LoginPeresenet {
    IsLoginView isLoginView;
    IsLognModel isLognModel;

    public LoginPeresenet(IsLoginView isLoginView) {
        this.isLoginView = isLoginView;
        isLognModel = new LoginModelImpl();
    }

    //绑定下
    public void fach(final String phone, final String password, Activity activity) {
        if (isLognModel != null) {
            isLognModel.loginmode(new IsLognModel.onLoginmodeLinistener() {
                @Override
                public void onComplete(LoginBean logindata) {
                    isLoginView.showLogin(phone,password,logindata);
                }
            },phone,password,activity);
        }
    }
}
