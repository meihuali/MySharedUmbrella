package com.example.administrator.mysharedumbrella01.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 项目名：
 * 包名：
 * 文件名:   RegularUtil
 * 创建者:   梅华黎
 * 创建时间:  2017/6/9 23:06
 * 描述：    正则工具类
 */
public class RegularUtil {
    /**
     * 手机号
     *
     * @param str
     * @return
     */
    public static boolean isMobile(final String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    /**
     * 判断密码
     *
     * @param str
     * @return
     */
    public static boolean isPassword(final String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("[a-zA-Z0-9]{6,20}+$"); // 验证密码
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    /*
    * 判断验证码
    * */
    public static final boolean isPhoneValidateCode(String value){
        String pattern = "^[0-9]{4,4}+$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(value);
        return m.matches();
    }
    /*
    * 判断邮编
    * */
    public static final boolean isPostalCode(String value){
        String pattern = "^[0-9]{6,6}+$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(value);
        return m.matches();
    }
    /*
    * 判断用户昵称
    * */
    public static final boolean isUserNick(String value){

        String pattern = "[A-Za-z0-9_\\-\\u4e00-\\u9fa5]{2,4}";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(value);
        return m.matches();
    }


}
