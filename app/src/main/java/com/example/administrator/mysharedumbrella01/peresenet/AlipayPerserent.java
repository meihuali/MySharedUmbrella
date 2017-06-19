package com.example.administrator.mysharedumbrella01.peresenet;

import com.example.administrator.mysharedumbrella01.Impl.AliPayModelImpl;
import com.example.administrator.mysharedumbrella01.entivity.ZhiFuBaoYaJinBean;
import com.example.administrator.mysharedumbrella01.entivity.ZhifubaoBean;
import com.example.administrator.mysharedumbrella01.model.IsAlipayModel;
import com.example.administrator.mysharedumbrella01.view.IsAliPayView;

/**
 * Created by Administrator on 2017/6/17 0017.
 */

public class AlipayPerserent {
    IsAlipayModel alipayModel;
    IsAliPayView aliPayView;


    public AlipayPerserent(IsAliPayView aliPayView) {
        this.aliPayView = aliPayView;
        alipayModel = new AliPayModelImpl();


    }

    //中间者 绑定方法
    public void fach(String zhifujixing, String money, String user, String zhifubiaoti) {
        if (alipayModel != null) {
            alipayModel.ZhiFuBao(new IsAlipayModel.OnZhiFuBaoLinset() {
                @Override
                public void onCompelte(ZhifubaoBean zfb) {
                    aliPayView.showRestuel(zfb);
                }
            },zhifujixing,money,user,zhifubiaoti);
        }

    }
}
