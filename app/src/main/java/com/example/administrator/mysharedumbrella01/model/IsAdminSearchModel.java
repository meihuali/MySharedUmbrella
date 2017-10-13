package com.example.administrator.mysharedumbrella01.model;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.model
 * 文件名：IsAdminSearchModel
 * 创建者 ：梅华黎
 * 创建时间： 2017/10/13 0013 15:44
 * 描述：TODO
 */
public interface IsAdminSearchModel {
    void adminsearch(onAdminSearchLinerest linerest,String umbrellaid);

    interface onAdminSearchLinerest{
        void onComplte(Object object);
    }
}
