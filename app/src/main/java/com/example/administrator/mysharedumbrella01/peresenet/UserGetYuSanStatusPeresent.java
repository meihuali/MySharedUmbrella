package com.example.administrator.mysharedumbrella01.peresenet;

import com.example.administrator.mysharedumbrella01.Impl.UserGetYuSanStatusImpl;
import com.example.administrator.mysharedumbrella01.model.IsUserGetYuSanStatusModel;
import com.example.administrator.mysharedumbrella01.view.IsUserGetYuSanStatusView;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.peresenet
 * 文件名：UserGetYuSanStatusPeresent
 * 创建者 ：$梅华黎
 * 创建时间： 2017/8/11 17:26
 * 描述：用户取走雨伞的中间者类
 */
public class UserGetYuSanStatusPeresent {
    IsUserGetYuSanStatusModel isUserGetYuSanStatusModel;
    IsUserGetYuSanStatusView isUserGetYuSanStatusView;

    public UserGetYuSanStatusPeresent(IsUserGetYuSanStatusView isUserGetYuSanStatusView) {
        this.isUserGetYuSanStatusView = isUserGetYuSanStatusView;
        isUserGetYuSanStatusModel = new UserGetYuSanStatusImpl();
    }

    /*
    * 用户取走雨伞 需要调用的方法
    * */

    public void userGetYuSan(String sedit) {
        if (isUserGetYuSanStatusModel != null) {
            isUserGetYuSanStatusModel.getYuSanStatus(new IsUserGetYuSanStatusModel.onUserGetYuSanSttusLenerist() {
                @Override
                public void onComplte(Object object) {
                    isUserGetYuSanStatusView.showGetyunsanStatus(object);

                }
            },sedit);
        }
    }
}
