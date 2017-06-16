package com.example.administrator.mysharedumbrella01.peresenet;

import com.example.administrator.mysharedumbrella01.Impl.RegisterModeImpl;
import com.example.administrator.mysharedumbrella01.entivity.RegisterBean;
import com.example.administrator.mysharedumbrella01.model.IsRegisterModel;
import com.example.administrator.mysharedumbrella01.view.IsRegisterView;

import java.util.List;

/**
 * Created by Administrator on 2017/6/6 0006.
 * //注册 接口的 中间者
 */

public class RegisterPrestenet {
    IsRegisterModel registerModel;
    IsRegisterView registerView;

    public RegisterPrestenet(IsRegisterView registerView) {
        this.registerView = registerView;
        registerModel = new RegisterModeImpl();
    }

    //绑定的 方法
    public void fact(String ed_phone, String ed_pwd, String ed_name,String yanzhengma) {
        if (registerModel != null) {
            registerModel.register(new IsRegisterModel.OnRegisterListener() {
                @Override
                public void onComplete(RegisterBean rb) {
                    registerView.showUmbrella(rb);
                }
            },ed_phone,ed_pwd,ed_name,yanzhengma);
        }
    }
}
