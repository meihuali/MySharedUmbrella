package com.example.administrator.mysharedumbrella01.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.mysharedumbrella01.Fragment.PropagandamapFragment;
import com.example.administrator.mysharedumbrella01.Fragment.SanZuoSanFragent;
import com.example.administrator.mysharedumbrella01.Fragment.ShoppingJaJinJiLuFragment;
import com.example.administrator.mysharedumbrella01.Fragment.StreetscapeFragment;
import com.example.administrator.mysharedumbrella01.R;
import com.gyf.barlibrary.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.ui
 * 文件名：ShoppingRecordActivity
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/11 0011 19:08
 * 描述：商家记录
 */
public class ShoppingRecordActivity extends AppCompatActivity implements View.OnClickListener {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<String> mTitle;
    private List<Fragment> mFragment;
    private ImageView image_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppingrecord);
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
        mTitle.add(getResources().getString(R.string.sanzuosan));
        mTitle.add(getResources().getString(R.string.jilu));
        //实例化Fragment
        mFragment = new ArrayList<>();
        mFragment.add(new SanZuoSanFragent());
        mFragment.add(new ShoppingJaJinJiLuFragment());
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
                finish();
                break;

        }
    }
}
