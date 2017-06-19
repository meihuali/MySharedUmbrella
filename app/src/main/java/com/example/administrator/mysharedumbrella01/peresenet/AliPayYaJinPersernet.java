package com.example.administrator.mysharedumbrella01.peresenet;

import com.example.administrator.mysharedumbrella01.Impl.AliPayYaJinModelImpl;
import com.example.administrator.mysharedumbrella01.entivity.ZhiFuBaoYaJinBean;
import com.example.administrator.mysharedumbrella01.entivity.ZhifubaoBean;
import com.example.administrator.mysharedumbrella01.model.IsAipayYaJinModel;
import com.example.administrator.mysharedumbrella01.model.IsAlipayModel;
import com.example.administrator.mysharedumbrella01.view.IsAliPayYaJinView;

/**
 * Created by Administrator on 2017/6/19 0019.
 */

public class AliPayYaJinPersernet {
    IsAipayYaJinModel aipayYaJinModel;
    IsAliPayYaJinView aliPayYaJinView;

    public AliPayYaJinPersernet(IsAliPayYaJinView aliPayYaJinView) {
        this.aliPayYaJinView = aliPayYaJinView;
        aipayYaJinModel = new AliPayYaJinModelImpl();
    }

    public void fach(String zhifujixing,String money,String user, String zhifubiaoti) {
        if (aipayYaJinModel != null) {
            aipayYaJinModel.ZhiFuBaoYaJin(new IsAipayYaJinModel.OnZhifubaoYajinLinenere() {
                @Override
                public void OnCompelte(ZhifubaoBean zfb) {
                    aliPayYaJinView.showRestuelYaJin(zfb);
                }
            },zhifujixing,money,user,zhifubiaoti);
        }
    }

}
