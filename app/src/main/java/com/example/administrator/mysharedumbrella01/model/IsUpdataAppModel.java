package com.example.administrator.mysharedumbrella01.model;

import com.example.administrator.mysharedumbrella01.entivity.UpdataBean;

/**
 * Created by Administrator on 2017/6/26 0026.
 *  更新APP 版本用的
 */

public interface IsUpdataAppModel {
    /*
    *  更新APP用的
    * */
    void updataApp(OnUpdataAppListener listener);

    /*
    * 接口回调
    * */
    interface OnUpdataAppListener{
        void onComplete(UpdataBean updataBean);
    }
}
