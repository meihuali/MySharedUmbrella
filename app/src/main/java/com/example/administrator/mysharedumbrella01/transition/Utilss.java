package com.example.administrator.mysharedumbrella01.transition;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewAnimationUtils;

import com.example.administrator.mysharedumbrella01.R;


/**
 * 项目名：CustomAndroidActivityTransition-master
 * 包名：com.imczy.customactivitytransition.transition
 * 文件名：Utilss
 * 创建者 ：$梅华黎
 * 创建时间： 2017/8/18 15:53
 * 描述：TODO
 */
public class Utilss {

   public static void transitionTo(Intent i, Activity activity, View view) {
        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, new Pair<>(view, "comment"));
        activity.startActivity(i, transitionActivityOptions.toBundle());
    }

    /**
     * 分享 元素 进入动画
     * @return
     */
    public static TransitionSet buildShareElemEnterSet(View view, int ids) {
        TransitionSet transitionSet = new TransitionSet();
        Transition changePos = new ChangePosition();
        changePos.setDuration(500);
        changePos.addTarget(ids);
        transitionSet.addTransition(changePos);

        Transition revealTransition = new ShareElemEnterRevealTransition(view);
        transitionSet.addTransition(revealTransition);
        revealTransition.addTarget(ids);
        revealTransition.setInterpolator(new FastOutSlowInInterpolator());
        revealTransition.setDuration(500);

        ChangeColor changeColor = new ChangeColor(view.getResources().getColor(R.color.toumingses), view.getResources().getColor(R.color.white));
        changeColor.addTarget(ids);
        changeColor.setDuration(500);

        transitionSet.addTransition(changeColor);

        transitionSet.setDuration(500);

        return transitionSet;
    }


    /**
     * 分享元素返回动画
     * @return
     */
    public static TransitionSet buildShareElemReturnSet(View view, int ids) {
        TransitionSet transitionSet = new TransitionSet();
        Transition changePos = new ShareElemReturnChangePosition();
        changePos.addTarget(ids);
        transitionSet.addTransition(changePos);

        ChangeColor changeColor = new ChangeColor(view.getResources().getColor(R.color.white), view.getResources().getColor(R.color.toumingses));
        changeColor.addTarget(ids);
        transitionSet.addTransition(changeColor);


        Transition revealTransition = new ShareElemReturnRevealTransition(view);
        revealTransition.addTarget(ids);
        transitionSet.addTransition(revealTransition);

        transitionSet.setDuration(500);

        return transitionSet;
    }


}
