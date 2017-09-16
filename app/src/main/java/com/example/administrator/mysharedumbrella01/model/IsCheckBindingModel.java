package com.example.administrator.mysharedumbrella01.model;

import java.util.Objects;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.model
 * 文件名：IsCheckBindingModel
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/12 0012 14:21
 * 描述：检测用户是否有绑定过第三方账号
 */
public interface IsCheckBindingModel {

    void checkbangding(onCheckBindingLinsestes linsestes,String phone);
    /*
    * 接口回调
    * */
    interface onCheckBindingLinsestes{
        void onComplte(Object objects);
    }
}
