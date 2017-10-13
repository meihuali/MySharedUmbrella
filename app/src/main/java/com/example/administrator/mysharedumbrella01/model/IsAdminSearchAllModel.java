package com.example.administrator.mysharedumbrella01.model;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.model
 * 文件名：IsAdminSearchAllModel
 * 创建者 ：梅华黎
 * 创建时间： 2017/10/13 0013 15:16
 * 描述：TODO
 */
public interface IsAdminSearchAllModel {

    void onAdminSeach(OnAdminSeachLister lister,String phone,String machine_id);

    interface OnAdminSeachLister{
        void onComptle(Object object);
    }
}
