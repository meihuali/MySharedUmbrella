package com.example.administrator.mysharedumbrella01.model;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.model
 * 文件名：IsUserCurrentMonde
 * 创建者 ：梅华黎
 * 创建时间： 2017/9/6 0006 10:42
 * 描述：用户正在使用的雨伞
 */
public interface IsUserCurrentMonde {

    void userCurrent(onUserCurrent liners,String r_id);

    interface onUserCurrent{
        //请求成功
        void onComplte(Object object);
        //请求失败
        void onErres();
    }
}
