package com.example.administrator.mysharedumbrella01.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.mysharedumbrella01.Adapter.DetailoFamountAdapter;
import com.example.administrator.mysharedumbrella01.Adapter.ShoppingGoodsReceiptAdapter;
import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.entivity.ShoppingConfirmGoodsBean;
import com.example.administrator.mysharedumbrella01.entivity.ShoppingGoodsReceiptBean;
import com.example.administrator.mysharedumbrella01.entivity.ShoppingQianshouBean;
import com.example.administrator.mysharedumbrella01.peresenet.ShoppingConfirmPerserent;
import com.example.administrator.mysharedumbrella01.peresenet.ShoppingConmfirGoodsPerserent;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.example.administrator.mysharedumbrella01.utils.ToastUtil;
import com.example.administrator.mysharedumbrella01.view.IsShoppingConfirmView;
import com.example.administrator.mysharedumbrella01.view.IsShoppingConmfirGoodsView;
import com.gyf.barlibrary.ImmersionBar;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.whyalwaysmea.circular.AnimUtils;

import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.ui
 * 文件名：ShoppingGoodsReceiptActivity
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/14 0014 14:10
 * 描述：商家 货物签收
 */
public class ShoppingGoodsReceiptActivity extends AppCompatActivity implements View.OnClickListener, IsShoppingConfirmView, IsShoppingConmfirGoodsView {
    private RecyclerView mRecyclerView;
    private List<ShoppingConfirmGoodsBean.DataBean> mList = new ArrayList<>();
    private ShoppingGoodsReceiptAdapter adapter;
    private ShoppingGoodsReceiptBean spgoods;
    private ImageView image_back;
    private String phone;
    private int stands,umbrellas;
    String yusan_a;
    String sanzuo_a;
    private ShoppingConfirmPerserent confirmGoods;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppinggoodsreceiptactivity);
        AnimUtils.animhpel(this,R.id.ll_layout);

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
    * 获取数据源
    * */
    private void initData() {
        phone = ShareUtils.getString(getApplicationContext(),"zhanghao","");
        confirmGoods = new ShoppingConfirmPerserent(this);
        confirmGoods.confirmGoods(phone);
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
        adapter = new ShoppingGoodsReceiptAdapter(R.layout.activity_shoppinggoods,mList);
        mRecyclerView.setAdapter(adapter);

        // 这一句是开启 item 动画
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.btn_confirm:
                        ShoppingConfirmGoodsBean.DataBean goodsReceiptBean = (ShoppingConfirmGoodsBean.DataBean) adapter.getItem(position);
                        EditText tv_shijisanzuo = (EditText) adapter.getViewByPosition(mRecyclerView,position,R.id.tv_shijisanzuo);
                        EditText tv_shijiyusan = (EditText) adapter.getViewByPosition(mRecyclerView,position,R.id.tv_shijiyusan);
                        String editSanZuo = tv_shijisanzuo.getText().toString().trim();
                        String editYuSan = tv_shijiyusan.getText().toString().trim();

                        if (TextUtils.isEmpty(editSanZuo)) {
                            editSanZuo = 0 + "";
                        } else {
                            if ( Integer.parseInt(editSanZuo)> stands) {
                                tv_shijisanzuo.setText(stands + "");
                                sanzuo_a = String.valueOf(stands);
                            } else {
                                //这里获取输入框的内容
                                sanzuo_a = editSanZuo;
                            }
                        }
                        if (TextUtils.isEmpty(editYuSan)) {
                            editYuSan = 0+"";
                        }else {
                            if (Integer.parseInt(editYuSan) > umbrellas) {
                                tv_shijiyusan.setText(umbrellas + "");
                                yusan_a = String.valueOf(umbrellas);
                            } else {
                                //获取输入框的内容
                                yusan_a = editYuSan;
                            }
                        }

                        if (Integer.parseInt(editSanZuo) > 0) {
                            StyledDialog.buildLoading("提交中···").show();
                            ShoppingConmfirGoodsPerserent perserent = new ShoppingConmfirGoodsPerserent(ShoppingGoodsReceiptActivity.this);
                            perserent.confirGoods(phone, sanzuo_a + "", yusan_a + "");
                        } else if (Integer.parseInt(editYuSan) > 0) {
                            StyledDialog.buildLoading("提交中···").show();
                            ShoppingConmfirGoodsPerserent perserent = new ShoppingConmfirGoodsPerserent(ShoppingGoodsReceiptActivity.this);
                            perserent.confirGoods(phone, sanzuo_a + "", yusan_a + "");
                        } else {
                            StyledDialog.buildIosAlert("警告！", "您没有可签收的伞座或雨伞", new MyDialogListener() {
                                @Override
                                public void onFirst() {

                                }

                                @Override
                                public void onSecond() {

                                }
                            }).setBtnText("确定", "").show();
                        }

                        break;
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_back:
                //   finish();
                AnimUtils.finishAmins((Activity) this,R.id.ll_xxxx,v,R.id.ll_layout);
                break;
        }
    }


    /*
    *
    * 确认收获的 的item 上的数据·接口回调
    * */
    @Override
    public void showRelust(Object object) {
        StyledDialog.dismissLoading();
        ShoppingConfirmGoodsBean goodsBean = (ShoppingConfirmGoodsBean) object;
        if (goodsBean.getStatus() == 1) {
            ShoppingConfirmGoodsBean.DataBean ddd =   goodsBean.getData();
            mList.clear();
         //   if (!goodsBean.getData().getStand().equals("0") && !goodsBean.getData().getUmbrella().equals("0")) {
                adapter.addData(ddd);
                adapter.notifyDataSetChanged();
                String stand =   ddd.getStand();
                String umbrella =  ddd.getUmbrella();
                stands = Integer.parseInt(stand);
                umbrellas = Integer.parseInt(umbrella);
          //  }




        } else {
            StyledDialog.buildIosAlert("错误", "获取数据失败", new MyDialogListener() {
                @Override
                public void onFirst() {

                }

                @Override
                public void onSecond() {
                }
            }).setBtnText("确认","").show();
        }


    }
    /*
    * 点击item 上的确定按钮 后
    * 也就是收获按钮
    * */
    @Override
    public void showConfri(Object object) {
        StyledDialog.dismissLoading();
        ShoppingQianshouBean qianshou = (ShoppingQianshouBean) object;
        int status = qianshou.getStatus();
        if (status == 1) {
            StyledDialog.buildIosAlert("签收", qianshou.getData().getSuccess(), new MyDialogListener() {
                @Override
                public void onFirst() {
                    //下面三句话相当于刷新的意思·从新调用

                    confirmGoods.confirmGoods(phone);
                }

                @Override
                public void onSecond() {
                }
            }).setBtnText("确定", "").show();
        }
    }














}
