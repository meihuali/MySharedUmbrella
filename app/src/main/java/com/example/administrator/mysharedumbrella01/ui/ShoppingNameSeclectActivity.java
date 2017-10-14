package com.example.administrator.mysharedumbrella01.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.mysharedumbrella01.Adapter.DetailoFamountAdapter;
import com.example.administrator.mysharedumbrella01.Adapter.ShoppingNameAdapter;
import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.entivity.AdminAuthenticationBean;
import com.example.administrator.mysharedumbrella01.peresenet.AdminAuthenticationPerensert;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.example.administrator.mysharedumbrella01.utils.MyDialog;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.example.administrator.mysharedumbrella01.view.IsAdminAuthenticationView;
import com.gyf.barlibrary.ImmersionBar;
import com.hss01248.dialog.StyledDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.ui
 * 文件名：ShoppingNameSeclectActivity
 * 创建者 ：梅华黎
 * 创建时间： 2017/10/14 0014 10:01
 * 描述：TODO
 */
public class ShoppingNameSeclectActivity extends AppCompatActivity implements View.OnClickListener, IsAdminAuthenticationView {
    private ImageView image_back;
    private RecyclerView mRecyclerView;
     private ShoppingNameAdapter adapter;
    private List<AdminAuthenticationBean.DataBean> mlist = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppingnameseclect);
        //沉浸式
        ImmersionBar.with(this)
                .statusBarColor(R.color.lanse_x_x) //指定主题颜色 意思 是在这里可以修改 styles 里面的主题颜色
                // .statusBarDarkFont(true,0.2f)    如果是白色或者透明状态的时候就加上他
                .fitsSystemWindows(true) //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
                .init();

        initView();
        initData();
    }
    /*
    * 获取服务器返回的数据源
    * */
    private void initData() {
        StyledDialog.buildLoading("加载中···").show();
        String phone = ShareUtils.getString(getApplicationContext(),"zhanghao","");
        AdminAuthenticationPerensert authenticationPerensert = new AdminAuthenticationPerensert(this);
        authenticationPerensert.authentiact(phone);
    }

    /*
    * 初始化
    * */
    private void initView() {
        image_back = (ImageView) findViewById(R.id.image_back);
        image_back.setOnClickListener(this);
        //初始化recycleView
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        //设置 mRecyclerView 的管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ShoppingNameAdapter(R.layout.shopping_name_seclect_item,mlist);
        mRecyclerView.setAdapter(adapter);
        //        这一句是开启 item 动画
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        //设置 监听
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                AdminAuthenticationBean.DataBean okdata = (AdminAuthenticationBean.DataBean) adapter.getItem(position);
               String shoppingName =  okdata.getMerchantname();
               int merchant_id = okdata.getId();
                L.e("商家名称  "+shoppingName);
                Intent mIntent = new Intent();
                mIntent.putExtra("change02", shoppingName);
                mIntent.putExtra("merchant_id",merchant_id+"");
                // 设置结果，并进行传送
                setResult(102, mIntent);
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_back:
                finish();
                break;
        }
    }
    /*
    * 该回调是 获取所有商家数据 主要为了 获取商家名称
    * */
    @Override
    public void showRestultAuthenticon(Object object) {
        StyledDialog.dismissLoading();
        AdminAuthenticationBean bean = (AdminAuthenticationBean) object;
        int state = bean.getStatus();
        if (state == 1) {
            List<AdminAuthenticationBean.DataBean> beandata = bean.getData();
            adapter.setNewData(beandata);
            adapter.notifyDataSetChanged();
        } else {
            MyDialog.dialog("提示","服务器挂了","确定","");
        }
    }
}
