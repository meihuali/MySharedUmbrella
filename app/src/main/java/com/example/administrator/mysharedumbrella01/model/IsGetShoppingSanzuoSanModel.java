package com.example.administrator.mysharedumbrella01.model;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.model
 * 文件名：IsGetShoppingSanzuoSanModel
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/18 0018 18:32
 * 描述：获取伞座界面的伞跟 伞座
 */
public interface IsGetShoppingSanzuoSanModel {
    void getSanZuoSan(onGetSanZuoSanLinerst linerst,String merchant_id);
    interface onGetSanZuoSanLinerst{
        void onComplte(Object object);
    }
}
