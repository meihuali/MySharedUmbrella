package com.example.administrator.mysharedumbrella01.model;

import java.io.File;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.model
 * 文件名：IsImageChangeModel
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/13 0013 15:13
 * 描述：街景 图片更换 接口
 */
public interface IsImageChangeModel {
    void imageChange(onImageChangLinserts linserts, String type, String phone, File file);

    interface onImageChangLinserts{
        void onComplte(Object object);
    }
}
