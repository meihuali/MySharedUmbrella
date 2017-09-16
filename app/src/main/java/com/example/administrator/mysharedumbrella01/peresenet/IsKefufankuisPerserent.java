package com.example.administrator.mysharedumbrella01.peresenet;

import com.example.administrator.mysharedumbrella01.Impl.IsKefufankuisImpl;
import com.example.administrator.mysharedumbrella01.model.IsKeFuFanKuisModel;
import com.example.administrator.mysharedumbrella01.view.IsKefufankuisView;

import java.io.File;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.peresenet
 * 文件名：IsKefufankuisPerserent
 * 创建者 ：梅华黎
 * 创建时间： 2017/9/5 0005 18:51
 * 描述：TODO
 */
public class IsKefufankuisPerserent {
    IsKeFuFanKuisModel keFuFanKuisModel;
    IsKefufankuisView kefufankuisView;

    public IsKefufankuisPerserent(IsKefufankuisView kefufankuisView) {
        this.kefufankuisView = kefufankuisView;
        keFuFanKuisModel = new IsKefufankuisImpl();
    }

    public void kefufankui(String appid, String type, File file,String bodys) {
        keFuFanKuisModel.bugFanKui(new IsKeFuFanKuisModel.onBugFanKuiLisener() {
            @Override
            public void onComplte(Object object) {
                kefufankuisView.ShowBugFankui(object);
            }
        },appid,type,file,bodys);
    }
}
