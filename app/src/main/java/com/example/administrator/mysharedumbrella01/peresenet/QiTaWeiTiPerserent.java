package com.example.administrator.mysharedumbrella01.peresenet;

import com.example.administrator.mysharedumbrella01.Impl.QiTaWenTiModelImpl;
import com.example.administrator.mysharedumbrella01.model.IsQiTaWenTiModel;
import com.example.administrator.mysharedumbrella01.view.IsQiTaWenTiView;

/**
 * Created by Administrator on 2017/6/29 0029.
 */

public class QiTaWeiTiPerserent {
    IsQiTaWenTiModel qiTaWenTiModel;
    IsQiTaWenTiView qiTaWenTiView;

    public QiTaWeiTiPerserent(IsQiTaWenTiView qiTaWenTiView) {
        this.qiTaWenTiView = qiTaWenTiView;
        qiTaWenTiModel = new QiTaWenTiModelImpl();
    }

    public void fach(String appid,
                     String machine_code,String umbrella_id,String type,String content) {
        if (qiTaWenTiModel != null) {
            qiTaWenTiModel.qitawent(new IsQiTaWenTiModel.OnqitawentListerensst() {
                @Override
                public void onCmplete(Object object) {
                    qiTaWenTiView.showqitawenti(object);
                }
            }, appid,machine_code, umbrella_id, type, content);

        }
    }
}
