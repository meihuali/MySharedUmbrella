package com.example.administrator.mysharedumbrella01.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.administrator.mysharedumbrella01.R;

/**
 * Created by Administrator on 2017/6/3 0003.
 */

public class ShangChuanLocation extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changhuanlocation);
        initView();
    }

    /*启动扫描二维码*/
    private void initView() {
        //打开扫描界面扫描条形码或二维码

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //扫描后 回调结果
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");

            Toast.makeText(getApplicationContext(),"扫描成功"+scanResult,Toast.LENGTH_SHORT).show();
            finish();
        }
    }
    //返回键的方法


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();

    }
}
