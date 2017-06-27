package com.example.administrator.mysharedumbrella01.peresenet;

import com.example.administrator.mysharedumbrella01.Impl.UpdataAppModelImpl;
import com.example.administrator.mysharedumbrella01.entivity.UpdataBean;
import com.example.administrator.mysharedumbrella01.model.IsUpdataAppModel;
import com.example.administrator.mysharedumbrella01.view.IsUpdataAPPView;

/**
 * Created by Administrator on 2017/6/26 0026.
 * 更新 APP 版本
 */

public class UpdataAppPerserent {
    IsUpdataAppModel updataAppModel;
    IsUpdataAPPView updataAPPView;

    public UpdataAppPerserent(IsUpdataAPPView updataAPPView) {
        this.updataAPPView = updataAPPView;
        updataAppModel = new UpdataAppModelImpl();
    }

    /*
    * 绑定方法
    * */
    public void fach() {
        if (updataAppModel != null) {
            updataAppModel.updataApp(new IsUpdataAppModel.OnUpdataAppListener() {
                @Override
                public void onComplete(UpdataBean updataBean) {
                    updataAPPView.showUpdataResltout(updataBean);
                }
            });
        }
    }
}
