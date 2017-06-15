package com.example.administrator.mysharedumbrella01.peresenet;

import android.app.Activity;

import com.example.administrator.mysharedumbrella01.Impl.WalletManeyImpl;
import com.example.administrator.mysharedumbrella01.entivity.ManeyBean;
import com.example.administrator.mysharedumbrella01.model.IsWalletManeyModel;
import com.example.administrator.mysharedumbrella01.view.IsWalletManeyView;

import java.util.List;

/**
 * Created by Administrator on 2017/6/15 0015.
 */

public class WalletManeyPerserent {
    IsWalletManeyModel walletManeyModel;
    IsWalletManeyView walletManeyView;

    public WalletManeyPerserent(IsWalletManeyView walletManeyView) {
        this.walletManeyView = walletManeyView;
        walletManeyModel = new WalletManeyImpl();
    }

    //绑定方法
    public void fach(Activity activity) {
        if (walletManeyModel != null) {
            walletManeyModel.walletMeney(new IsWalletManeyModel.OnWalletmyListener() {
                @Override
                public void onCompelte(ManeyBean.DataBean list) {
                    walletManeyView.showManey(list);
                }
            },activity);
        }
    }
}

