package com.example.administrator.mysharedumbrella01.model;

/**
 * Created by Administrator on 2017/7/27 0027.
 *  退款 接口
 */

public interface IsTuiKuanModel {
    /*
    退款接口
    * */
    void tuikuan(onTuikuanjiekouLinereset linereset,String zhanghao,String num,String type);
    /*
    * 退款接口回调
    * */
    interface onTuikuanjiekouLinereset{
        void complte(Object object);
    }
}
