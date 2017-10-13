package com.example.administrator.mysharedumbrella01.model;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.model
 * 文件名：IsShenQingSanZuoModel
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/20 0020 19:09
 * 描述：申请雨伞个数
 */
public interface IsShenQingSanZuoModel {
    void shenqingsanzuo(onShenQinSanZUOLinserst linserst,String phone,String stand,String umbrella);

    interface onShenQinSanZUOLinserst{
        void onComplte(Object object);
    }
}
