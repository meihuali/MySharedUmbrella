package com.example.administrator.mysharedumbrella01.model;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.model
 * 文件名：IsShoppingQueryAuthenticationModel
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/13 0013 14:03
 * 描述：查询商户是否已经认证过了
 */
public interface IsShoppingQueryAuthenticationModel {

    void QueryAutent(onQueryAutentLinserts linserts,String phone);
    /*
    * 接口回调
    * */

    interface onQueryAutentLinserts{

        void onComplte(Object object);

    }
}
