package com.example.administrator.mysharedumbrella01.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.mysharedumbrella01.Fragment.PropagandamapFragment;
import com.example.administrator.mysharedumbrella01.Fragment.StreetscapeFragment;
import com.example.administrator.mysharedumbrella01.R;
import com.gyf.barlibrary.ImmersionBar;
import com.whyalwaysmea.circular.AnimUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.ui
 * 文件名：UdataImageActivity
 * 创建者 ：梅华黎
 * 创建时间： 2017/9/8 0008 17:11
 * 描述：更换图片的tablayout
 */
public class UdataImageActivity extends AppCompatActivity implements View.OnClickListener {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<String> mTitle;
    private List<Fragment> mFragment;
    private ImageView image_back;
    private View ll_layout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updataimage);
        AnimUtils.animhpel(this,R.id.ll_layout);

        //沉浸式
        ImmersionBar.with(this)
                .init();
        initData();
        initView();
    }

    /*
    * 初始化数据
    * */
    private void initData() {
        mTitle = new ArrayList<>();
        mTitle.add(getResources().getString(R.string.jiejing));
        mTitle.add(getResources().getString(R.string.xuanchuantu));
        //实例化Fragment
        mFragment = new ArrayList<>();
        mFragment.add(new StreetscapeFragment());
        mFragment.add(new PropagandamapFragment());
    }

    /*
    * 初始化数据
    * */
    private void initView() {
        image_back = (ImageView) findViewById(R.id.image_back);
        image_back.setOnClickListener(this);
        mTabLayout = (TabLayout) findViewById(R.id.mTabLayout);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        //预加载
        mViewPager.setOffscreenPageLimit(mFragment.size());

        // 设置适配器
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            //选中的item
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }

            //返回item的个数
            @Override
            public int getCount() {
                return mFragment.size();
            }
            //设置标题
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitle.get(position);
            }
        });
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.image_back:
             //   finish();
                AnimUtils.finishAmins((Activity)this,R.id.rl_layoutssss,v,R.id.ll_layout);
                break;
        }
    }
}
