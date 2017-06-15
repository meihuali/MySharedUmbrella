package com.example.administrator.mysharedumbrella01.model;

import com.example.administrator.mysharedumbrella01.entivity.RegisterBean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/6 0006.
 * // 注册 接口
 */

public interface IsRegisterModel {
    //注册接口
    void register(OnRegisterListener listener,String ed_phone,String ed_pwd,String ed_name);
    //注册接口回调
    interface OnRegisterListener{
        void onComplete(RegisterBean rb);

    }
}
