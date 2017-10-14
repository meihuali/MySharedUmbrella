package com.example.administrator.mysharedumbrella01.peresenet;

import com.example.administrator.mysharedumbrella01.Impl.ComiteBindingImpl;
import com.example.administrator.mysharedumbrella01.model.IsComiteBindingModel;
import com.example.administrator.mysharedumbrella01.view.IsComiteBindingView;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.peresenet
 * 文件名：ComiteBindingPerenset
 * 创建者 ：梅华黎
 * 创建时间： 2017/10/14 0014 11:26
 * 描述：TODO
 */
public class ComiteBindingPerenset {
    IsComiteBindingModel comiteBindingModel;
    IsComiteBindingView comiteBindingView;

    public ComiteBindingPerenset(IsComiteBindingView comiteBindingView) {
        this.comiteBindingView = comiteBindingView;
        comiteBindingModel = new ComiteBindingImpl();
    }

    public void bangding(String phone,String machine_id,String merchant_id,String longitude,String latitude) {
        if (comiteBindingModel != null) {
            comiteBindingModel.comiteBangding(new IsComiteBindingModel.onComiteBangdingLinesiet() {
                @Override
                public void onComplte(Object object) {
                    comiteBindingView.showResulets(object);
                }
            }, phone, machine_id, merchant_id, longitude, latitude);
        }
    }
}
