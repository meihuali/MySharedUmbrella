package com.example.administrator.mysharedumbrella01.model;

import android.app.Activity;

import com.example.administrator.mysharedumbrella01.entivity.ShangChuanTouXiangBean;

import java.io.File;

/**
 * Created by Administrator on 2017/6/19 0019.
 *  上传头像 用的 接口
 */

public interface IsShangchuanPhotoModel {
    //上传头像接口
    void ShangChuanTouXiang(OnShangChuanListener listener, File file, Activity activity);
    /*
    * 上传头像接口回调
    * */

    interface OnShangChuanListener{
        void onComlete(ShangChuanTouXiangBean sctxb);
    }
}
