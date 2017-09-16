package com.example.administrator.mysharedumbrella01.peresenet;

import com.example.administrator.mysharedumbrella01.Impl.IsUserCurrentImpl;
import com.example.administrator.mysharedumbrella01.model.IsUserCurrentMonde;
import com.example.administrator.mysharedumbrella01.view.IsUserCurrentView;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.peresenet
 * 文件名：IsUserCurrentPerserent
 * 创建者 ：梅华黎
 * 创建时间： 2017/9/6 0006 10:48
 * 描述：TODO
 */
public class IsUserCurrentPerserent {
    IsUserCurrentMonde userCurrentMonde;
    IsUserCurrentView currentView;

    public IsUserCurrentPerserent(IsUserCurrentView currentView) {
        this.currentView = currentView;
        userCurrentMonde = new IsUserCurrentImpl();
    }

    public void currents(String r_is) {
        if (userCurrentMonde != null) {
            userCurrentMonde.userCurrent(new IsUserCurrentMonde.onUserCurrent() {
                @Override
                public void onComplte(Object object) {

                    currentView.showCurrent(object);
                }

                @Override
                public void onErres() {
                    currentView.showErres();
                }
            },r_is);
        }
    }
}
