package com.example.administrator.mysharedumbrella01.peresenet;

import com.example.administrator.mysharedumbrella01.Impl.UserFeedBackModelImpl;
import com.example.administrator.mysharedumbrella01.model.IsUserFeedbackModel;
import com.example.administrator.mysharedumbrella01.view.IsUserFeedBackView;

/**
 * Created by Administrator on 2017/6/27 0027.
 */

public class UserFeedBackPerserent {
    IsUserFeedbackModel userFeedbackModel;
    IsUserFeedBackView userFeedBackView;

    public UserFeedBackPerserent(IsUserFeedBackView userFeedBackView) {
        this.userFeedBackView = userFeedBackView;
        userFeedbackModel = new UserFeedBackModelImpl();
    }

    public void fach(String appid, String machine_code, String umbrella_id, String type, String content) {
        if (userFeedbackModel != null) {
            userFeedbackModel.userfeedback(new IsUserFeedbackModel.OnUseFeedBackListerenr() {
                @Override
                public void onComplete(Object object) {
                    userFeedBackView.showUserFeedBack(object);
                }
            },appid,machine_code, umbrella_id, type, content);
        }
    }
}
