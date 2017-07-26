package com.example.administrator.mysharedumbrella01.peresenet;

import com.example.administrator.mysharedumbrella01.Impl.KeFuFanKuiImpl;
import com.example.administrator.mysharedumbrella01.model.IsKeFuFanKuiModel;
import com.example.administrator.mysharedumbrella01.view.IsKefufankuiView;

/**
 * Created by Administrator on 2017/7/26 0026.
 */

public class KefufankuiPerserent {
    IsKeFuFanKuiModel keFuFanKuiModel;
    IsKefufankuiView kefufankuiView;

    public KefufankuiPerserent(IsKefufankuiView kefufankuiView) {
        this.kefufankuiView = kefufankuiView;
        keFuFanKuiModel = new KeFuFanKuiImpl();
    }

    public void kehufankui() {
        if (keFuFanKuiModel != null) {
            keFuFanKuiModel.kefufankui(new IsKeFuFanKuiModel.onKefufankuiLinerenst() {
                @Override
                public void onComplte(Object object) {
                    kefufankuiView.showRelout(object);
                }
            });
        }
    }
}
