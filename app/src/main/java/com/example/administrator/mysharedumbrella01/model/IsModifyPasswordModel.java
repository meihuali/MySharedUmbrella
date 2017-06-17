package com.example.administrator.mysharedumbrella01.model;

import com.example.administrator.mysharedumbrella01.entivity.ModifyPasswordBean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/16 0016.
 * //修改密码的接口
 */

public interface IsModifyPasswordModel {
    //修改密码的接口
    void password(OnPassWordLineserset lineserset,String phone,String newPassWord,String yanzhenma);
    //接口回调
    interface OnPassWordLineserset{
        void OnCompelte(ModifyPasswordBean list);
    }
}
