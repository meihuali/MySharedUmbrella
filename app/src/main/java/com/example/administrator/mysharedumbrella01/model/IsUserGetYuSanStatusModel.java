package com.example.administrator.mysharedumbrella01.model;

/**
 * Created by Administrator on 2017/8/11.
 *  这个接口就是访问服务器 查看用户开锁成功以后
 *  是否 真的 有取走雨伞
 */

public interface IsUserGetYuSanStatusModel {
    /*
    * 用户取伞接口
    * */
    void getYuSanStatus(onUserGetYuSanSttusLenerist lenerist,String sealt);

    /*
    *  接口回调
    * */
    interface onUserGetYuSanSttusLenerist{
        void onComplte(Object object);
    }
}
