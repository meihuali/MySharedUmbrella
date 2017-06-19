package com.example.administrator.mysharedumbrella01.peresenet;

import android.app.Activity;

import com.example.administrator.mysharedumbrella01.Impl.ShangChuanTouXiangModelImpl;
import com.example.administrator.mysharedumbrella01.entivity.ShangChuanTouXiangBean;
import com.example.administrator.mysharedumbrella01.model.IsShangchuanPhotoModel;
import com.example.administrator.mysharedumbrella01.view.IsShangChuanTouXiangView;

import java.io.File;

/**
 * Created by Administrator on 2017/6/19 0019.
 */

public class ShangChuanTouXiangPersernet {
    IsShangchuanPhotoModel shangchuanPhotoModel;
    IsShangChuanTouXiangView shangChuanTouXiangView;

    public ShangChuanTouXiangPersernet(IsShangChuanTouXiangView shangChuanTouXiangView) {
        this.shangChuanTouXiangView = shangChuanTouXiangView;
        shangchuanPhotoModel = new ShangChuanTouXiangModelImpl();
    }

    public void fach(File file, Activity activity) {
        if (shangchuanPhotoModel != null) {
            shangchuanPhotoModel.ShangChuanTouXiang(new IsShangchuanPhotoModel.OnShangChuanListener() {
                @Override
                public void onComlete(ShangChuanTouXiangBean sctxb) {
                    shangChuanTouXiangView.ShowRest(sctxb);
                }
            },file,activity);
        }
    }
}
