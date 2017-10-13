package com.example.administrator.mysharedumbrella01.peresenet;

import android.app.Activity;

import com.example.administrator.mysharedumbrella01.Impl.TuikuanImpl;
import com.example.administrator.mysharedumbrella01.model.IsTuiKuanModel;
import com.example.administrator.mysharedumbrella01.view.IsTuikuanView;

/**
 * Created by Administrator on 2017/7/27 0027.
 */

public class TuikuanPerserent {
    IsTuiKuanModel tuiKuanModel;
    IsTuikuanView tuikuanView;

    public TuikuanPerserent(IsTuikuanView tuikuanView) {
        this.tuikuanView = tuikuanView;
        tuiKuanModel = new TuikuanImpl();
    }



    public void tuikuan(String zhanghao,String num,String type) {
        if (tuiKuanModel != null) {
            tuiKuanModel.tuikuan(new IsTuiKuanModel.onTuikuanjiekouLinereset() {
                @Override
                public void complte(Object object) {
                    tuikuanView.showRrult(object);
                }
            },zhanghao,num,type);
        }
    }
}
