package com.example.administrator.mysharedumbrella01.dialog;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.ui.DepositRechargeActivity;
import com.example.administrator.mysharedumbrella01.ui.RechargeActivity;
import com.example.administrator.mysharedumbrella01.utils.FlikerProgressBar;
import com.example.administrator.mysharedumbrella01.utils.L;

import razerdp.basepopup.BasePopupWindow;

/**
 * Created by Administrator on 2017/6/17 0017.
 */

public class PopupWindowGuanGao extends BasePopupWindow implements View.OnClickListener,Runnable{
    private View popupView;
    private Activity activity;
    private FlikerProgressBar round_flikerbar;
    Thread downLoadThread;
    private int status;
    Button btn_stop;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            round_flikerbar.setProgress(msg.arg1);
            if(msg.arg1 == 100){
                round_flikerbar.finishLoad(status);
            }
        }
    };

    public PopupWindowGuanGao(Activity activity) {
        super(activity);
        this.activity = activity;
        bindEvent();
    }



    @Override
    protected Animation initShowAnimation() {
        return getDefaultScaleAnimation();
    }


    @Override
    public View getClickToDismissView() {
        return popupView.findViewById(R.id.rl_layout);
    }

    @Override
    public View onCreatePopupView() {
        popupView= LayoutInflater.from(getContext()).inflate(R.layout.popup_guanggao,null);
        return popupView;
    }

    @Override
    public View initAnimaView() {
        return popupView.findViewById(R.id.popup_anima);
    }

    private void bindEvent() {
        if (popupView!=null){
            popupView.findViewById(R.id.tv_xiangxi).setOnClickListener(this);
            round_flikerbar = (FlikerProgressBar) popupView.findViewById(R.id.round_flikerbar);
            downLoad(); //加载进度条
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_xiangxi:
                Toast.makeText(activity,"没有详细",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

    }

    private void downLoad() {
        downLoadThread = new Thread(this);
        downLoadThread.start();
    }



    @Override
    public void run() {
        try {
            while(!downLoadThread.isInterrupted()){
                float progress = round_flikerbar.getProgress();
                progress  += 2;
                Thread.sleep(300);
                Message message = handler.obtainMessage();
                message.arg1 = (int) progress;
                handler.sendMessage(message);
                if(progress == 100){
                    break;
                }
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stopUpdata(int status) {
        round_flikerbar.finishLoad(status);
    }
}
