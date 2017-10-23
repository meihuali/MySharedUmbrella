package com.example.administrator.mysharedumbrella01.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.mysharedumbrella01.Adapter.DetailoFamountAdapter;
import com.example.administrator.mysharedumbrella01.Adapter.ShoppingHarvestAdapter;
import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.entivity.GetShoppingAddressBean;
import com.example.administrator.mysharedumbrella01.entivity.ShoppingDetelAddressBean;
import com.example.administrator.mysharedumbrella01.entivity.ShoppingSettingAddressBean;
import com.example.administrator.mysharedumbrella01.peresenet.GetShoppingAddressPerserent;
import com.example.administrator.mysharedumbrella01.peresenet.ShoppingDetleAddressPerserent;
import com.example.administrator.mysharedumbrella01.peresenet.ShoppingSettingAddressPerserent;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.example.administrator.mysharedumbrella01.utils.ToastUtil;
import com.example.administrator.mysharedumbrella01.view.IsGetShoppingAddressView;
import com.example.administrator.mysharedumbrella01.view.IsShoppingDetleAddressView;
import com.example.administrator.mysharedumbrella01.view.IsShoppingSettingAddressView;
import com.gyf.barlibrary.ImmersionBar;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.whyalwaysmea.circular.AnimUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.ui
 * 文件名：ShoppingHarvestAddress
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/13 0013 16:23
 * 描述：商家收获地址 列表
 */
public class ShoppingHarvestAddress extends AppCompatActivity implements View.OnClickListener, IsGetShoppingAddressView, IsShoppingDetleAddressView, IsShoppingSettingAddressView {
    private ImageView image_back;
    private RecyclerView mRecyclerView;
    private List<GetShoppingAddressBean.DataBean> mlist = new ArrayList<>();
    private ShoppingHarvestAdapter mAdapter;
    private Button btn_add_address;
    private String ids;
    private View ll_layout;
    private List<GetShoppingAddressBean.DataBean> GetList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppingharvestaddress);

        AnimUtils.animhpel(this,R.id.ll_layout);
        //沉浸式
        ImmersionBar.with(this)
                .statusBarColor(R.color.lanse_x_x) //指定主题颜色 意思 是在这里可以修改 styles 里面的主题颜色
                // .statusBarDarkFont(true,0.2f)    如果是白色或者透明状态的时候就加上他
                .fitsSystemWindows(true) //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
                .init();
        initView();
        initDataRecyView();
        initData();
    }
    /*
    * 获取服务器返回的商家数据 新增地址
    * */
    private void initData() {
        String shoppingid  =  ShareUtils.getString(getApplicationContext(),"shoppingId","");
        GetShoppingAddressPerserent getaddres = new GetShoppingAddressPerserent(this);
        getaddres.getaddress(shoppingid);
    }

    private void initDataRecyView() {
        //初始化recycleView
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        //设置 mRecyclerView 的管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ShoppingHarvestAdapter(R.layout.activity_getaddress,mlist,getApplicationContext());

        mRecyclerView.setAdapter(mAdapter);
        // 这一句是开启 item 动画
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);

        //设置item，的子控件点击事件

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                GetShoppingAddressBean.DataBean dd  = (GetShoppingAddressBean.DataBean) adapter.getItem(position);
                switch (view.getId()) {
                    // 删除地址
                    case R.id.tv_detle:
                        if (GetList.size() != 1) {
                            //获取该对象的id字段
                            String id = dd.getId();
                            ShoppingDetleAddressPerserent detelAddress = new ShoppingDetleAddressPerserent(ShoppingHarvestAddress.this);
                            detelAddress.detleAddress(id);
                        } else {
                            StyledDialog.buildIosAlert("提示", "需要保留至少一个默认收货地址！", new MyDialogListener() {
                                @Override
                                public void onFirst() {
                                }
                                @Override
                                public void onSecond() {
                                }
                            }).setBtnText("确定","").show();
                        }

                        break;
                    //编辑按钮
                    case R.id.tv_edit:
                      String scltectStatus = dd.getIs_inuser();
                        edites(scltectStatus,position);
                        break;
                    //勾选状态
                    case R.id.ll_layoutSeclet:
                        //获取被点击的item的子控件
                        for (int i = 0; i < mlist.size(); i++) {
                            if (i == position) {
                                //mlist.get(i).setSelect(true);
                                mlist.get(i).setIs_inuser("1");


                                ShoppingSettingAddressPerserent settingaddress = new ShoppingSettingAddressPerserent(ShoppingHarvestAddress.this);
                                settingaddress.setttingaddress( mlist.get(i).getMerchant_id(), mlist.get(i).getId());
                            } else {
                                // mlist.get(i).setSelect(false);
                                mlist.get(i).setIs_inuser("0");
                            }
                        }
                        adapter.notifyDataSetChanged();
                        break;
                }



            }
        });

    }
    /*
    * 编辑收货地址
    * */
    private void edites(String seclectStatus,int position) {
        GetShoppingAddressBean.DataBean dd  = (GetShoppingAddressBean.DataBean) mAdapter.getItem(position);
        //获取该对象的id字段
        String id = dd.getId();
        //获取该对象的用户名
        String name = dd.getName();
        //获取用户手机号码
        String phone = dd.getPhone();
        //获取地区
        String diqu = dd.getRegion();
        //获取详细地址
        String xiangxidizhi =  dd.getAddress();
        //获取默认状态
        String status = dd.getIs_inuser();
        //获取邮编
        String youbian = dd.getZipcode();


        Intent intent = new Intent(getApplicationContext(), AddressSelectorActivity.class);
        intent.putExtra("type","1");
        intent.putExtra("id",id);
        intent.putExtra("name",name);
        intent.putExtra("phone",phone);
        intent.putExtra("diqu",diqu);
        intent.putExtra("xiangxidizhi",xiangxidizhi);
        intent.putExtra("status", status);
        intent.putExtra("youbian",youbian);
        intent.putExtra("seclectStatus",seclectStatus);
        startActivity(intent);
    }

    private void initView() {
        image_back = (ImageView) findViewById(R.id.image_back);
        image_back.setOnClickListener(this);
        btn_add_address = (Button) findViewById(R.id.btn_add_address);
        btn_add_address.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_back:
                // rl_layout_back  finish();
                AnimUtils.finishAmins((Activity) this,R.id.rl_layout_back,v,R.id.ll_layout);
                break;
            //跳转到仿淘宝地址选择器
            case R.id.btn_add_address:
                Intent intent = new Intent(getApplicationContext(),AddressSelectorActivity.class);
                intent.putExtra("type","2");
                startActivity(intent);
                break;
        }
    }

    /*
    * 程序从上一个界面进来 的时候执行
    * */
    /*
    * 获取新增地址 接口回调
    * */

    @Override
    protected void onRestart() {
        super.onRestart();
        initData();
    }

    @Override
    public void showRelust(Object object) {
        GetShoppingAddressBean getaddres = (GetShoppingAddressBean) object;
        int status = getaddres.getStatus();
        if (status == 1) {
            GetList = getaddres.getData();

            mlist.clear();
            mAdapter.addData(GetList);
            mAdapter.notifyDataSetChanged();


        } else {
            ToastUtil.showShortToast(getApplicationContext(),"获取接口商家收获地址挂了");
        }
    }
    /*
    * 删除收货地址的接口回调
    * */
    @Override
    public void ShowComplte(Object object) {
        ShoppingDetelAddressBean deteleBean = (ShoppingDetelAddressBean) object;
        int status = deteleBean.getStatus();
        if (status == 1) {
            StyledDialog.buildIosAlert("删除", deteleBean.getData().getSuccess(), new MyDialogListener() {
                @Override
                public void onFirst() {
                    initData();
                }

                @Override
                public void onSecond() {

                }
            }).setBtnText("确定", "").show();
        } else {
            StyledDialog.buildIosAlert("删除", deteleBean.getData().getError_reason(), new MyDialogListener() {
                @Override
                public void onFirst() {
                }

                @Override
                public void onSecond() {
                }
            }).setBtnText("确定", "").show();
        }
    }
    /*
    * 商家手动在列表点点击修改 默认地址
    * */
    @Override
    public void showSettingRelust(Object object) {
        ShoppingSettingAddressBean spaddress = (ShoppingSettingAddressBean) object;
        int status = spaddress.getStatus();
        if (status == 1) {
            ToastUtil.showShortToast(getApplicationContext(), spaddress.getData().getSuccess());
        } else {
            ToastUtil.showShortToast(getApplicationContext(), spaddress.getData().getError_reason());
        }
    }
}
