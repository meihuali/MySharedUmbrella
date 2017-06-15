package com.example.administrator.mysharedumbrella01.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;

/**
 * Created by Administrator on 2017/6/2 0002.
 */

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_exits_login;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initViews();
    }

    private void initViews() {
        tv_exits_login = (TextView) findViewById(R.id.tv_exits_login);
        tv_exits_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_exits_login:
                ShareUtils.deleShare(getApplicationContext(),"mima");
                finish();
            break;
        }
    }
}
