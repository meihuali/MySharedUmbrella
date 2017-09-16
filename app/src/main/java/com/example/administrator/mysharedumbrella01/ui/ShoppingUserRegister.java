package com.example.administrator.mysharedumbrella01.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.appliction.BaseAppliction;
import com.example.administrator.mysharedumbrella01.dialog.PopupShopping;
import com.example.administrator.mysharedumbrella01.entivity.ShoppingUserRegisterBean;
import com.example.administrator.mysharedumbrella01.peresenet.ShoppingUserRegisterPersernt;
import com.example.administrator.mysharedumbrella01.utils.EditTextWithDelete;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.example.administrator.mysharedumbrella01.utils.ToastUtil;
import com.example.administrator.mysharedumbrella01.view.IsShoppingUserRegisterView;
import com.gyf.barlibrary.ImmersionBar;
import com.jkb.vcedittext.VerificationCodeEditText;

import cn.smssdk.SMSSDK;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.ui
 * 文件名：ShoppingUserRegister
 * 创建者 ：梅华黎
 * 创建时间： 2017/9/7 0007 12:01
 * 描述：商家用户版本
 */
public class ShoppingUserRegister extends AppCompatActivity implements View.OnClickListener, IsShoppingUserRegisterView {
    private VerificationCodeEditText yzm;
    private Button btn_next;
    //店名
    private EditTextWithDelete edit_dianName;
    //获取电话号码
    private EditTextWithDelete ed_phones;
    //获取地址
    private EditTextWithDelete ed_addres;
    //获取手机号码
    private EditTextWithDelete ed_shouhaoma;
    //获取验证码的按钮
    private Button btn_getYzm;
    private EditTextWithDelete ed_passWord;
    private String ed_dianName;
    private String shoujihaoma;
    private String et_pasword;
    private String yanzm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppingregister);
        //沉浸式
        ImmersionBar.with(this)
                .init();

   //     initView();
        //添加到栈中
        BaseAppliction.addDestoryActivity(this,"ShoppingUserRegister");
    }
    /*
    * 初始化
    * */
    private void initView() {
        btn_next = (Button) findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);
        yzm = (VerificationCodeEditText) findViewById(R.id.am_et);
        initYzmjiaodian();
        edit_dianName = (EditTextWithDelete) findViewById(R.id.edit_phone);
      //  ed_phones = (EditTextWithDelete) findViewById(R.id.ed_phones);
      //  ed_addres = (EditTextWithDelete) findViewById(R.id.ed_addres);
        ed_shouhaoma = (EditTextWithDelete) findViewById(R.id.ed_shouhaoma);
        btn_getYzm = (Button) findViewById(R.id.btn_getYzm);
        btn_getYzm.setOnClickListener(this);
        ed_passWord = (EditTextWithDelete) findViewById(R.id.ed_passWord);
    }
    /*
    * 验证码焦点获取
    * */
    private void initYzmjiaodian() {

        yzm.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                L.e("获取焦点状态 "+hasFocus);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:
                //获取商家店名
                 ed_dianName = edit_dianName.getText().toString().trim();
                //获取电话号码
               // String ed_phone = ed_phones.getText().toString().trim();
                //获取地址
              //  String ed_shoppingAddres = ed_addres.getText().toString().trim();
                //获取商家手机号码
                 shoujihaoma = ed_shouhaoma.getText().toString().trim();
                //获取密码
                 et_pasword = ed_passWord.getText().toString().trim();
                //获取验证码
                 yanzm = yzm.getText().toString().trim();
                L.e("输入框数据 "+yanzm);
                confimfirService();
                break;
            //点击这里获取验证码
            case R.id.btn_getYzm:
                getSMS();
                break;

        }
    }

    /*
* 请求服务器注册 商家账号
* */
    private void confimfirService() {
//        ShoppingUserRegisterPersernt shangjiazhuce = new ShoppingUserRegisterPersernt(this);
//        //2 代表是安卓 1表示 是ios
//        shangjiazhuce.shoppingRegsiert(ed_dianName,shoujihaoma,et_pasword,yanzm,"2");
    }

    /*
    * sharSDK 获取短信验证
    * */
    private void getSMS() {
        String ed_phone = ed_shouhaoma.getText().toString().trim();
        if (TextUtils.isEmpty(ed_phone)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (ed_phone.length() != 11) {
            Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "验证码已发送，请注意查看！", Toast.LENGTH_SHORT).show();
        SMSSDK.getVerificationCode("86", ed_phone);
    }

    @Override
    public void showShoppingRrelst(Object object) {
/*        ShoppingUserRegisterBean shangjiaBean = (ShoppingUserRegisterBean) object;
        int status = shangjiaBean.getStatus();
        if (status == 1) {
            PopupShopping zhuchetanchuang = new PopupShopping(this);
            zhuchetanchuang.showPopupWindow();

            ShoppingUserRegisterBean.DataBean shangjiadatat =  shangjiaBean.getData();
*//*            //获取手机号码
            String shangjiaPhone = shangjiadatat.getPhone();
            //获取密码
            String shangjiamima = shangjiadatat.getPwd();*//*
            //这里保存手机号码跟密码
            ShareUtils.putString(getApplicationContext(),"shangjiashouji",shangjiaPhone);
            ShareUtils.putString(getApplicationContext(),"shangjiamima",shangjiamima);
            //保存昵称

            //获取ID

        } else {
            ToastUtil.showShortToast(getApplicationContext(),"注册失败请仔细检查资料！");
        }*/
    }
}
