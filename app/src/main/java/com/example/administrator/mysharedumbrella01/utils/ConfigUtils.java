package com.example.administrator.mysharedumbrella01.utils;

/**
 * Created by Administrator on 2017/6/1 0001.
 */

public class ConfigUtils {
    //主域名
    public static final String ZHU_YU_MING = "http://u.sunyie.com/";

    // =============获取雨伞分布图=================================
    public static final String FENBUTU_HOUZHUI = "Share/umbrellaindex.php";
    /*===================扫描二维码 获取雨伞借伞=======================================================*/
    public static final String SAOMIAOERWEIMA_HOUZHUI = "Unlock/openumbrellanew.php ";
    /*===================历史记录=================================================================*/
    public static final String HUOQULISHILI = "Share/umbrellahistory.php";
    /*===================登录接口 后缀==========================================================*/
    public static final String LOGIN_HOUZHUI = "/Member/Memberlogin.php";
    /*====================管理员上传 当前 位子=========================================================*/
    public static final String DANGQAINWEIZI = "Share/UpdateStandposition.php";
    /*========================注册接口============================================================*/
    public static final String ZHUCEJIEKOUHOUZHUO = "Member/MemberRegistration.php";
    /*========================金额明细接口=======================================================*/
    public static final String JINERMINGXI = "Share/userdetail.php";
    /*========================获取钱包金额=================================================*/
    public static final String QIANBAOJINE = "Afterlogin/getMoney.php";
    /*=======================修改密码的 接口==================================================*/
   // public static final String XIUGAIMIMA = "Afterlogin/changepassword.php";
    public static final String XIUGAIMIMA = "Member/changepassword.php";
    /*==============================支付宝充值金额 ==============================*/
    public static final String ZHIFUBAO_CHONGZHI_JINER = "Alipay/pay.php";
    /*============================上传头像 接口============================================*/
    public static final String SHANGCHUANTOUXIANG = "Afterlogin/ModifyAvatar.php";
    /*==================================三方微信登录*/
   // public static final String WECHAT_LOGING = "Feedback/thirdlogin.php";
    public static final String WECHAT_LOGING = "Member/thirdpartylogin.php";

    /*
    * 用户反馈 1
    * */
    public static final String YONGHUFANKUI_ONE = "Feedback/check_feedback.php";

    /*
    * 主页广告
    * */
    public static final String ZHUYEGUANGGAO = "/Feedback/check_adimg.php";
    /*
    * 开锁中的广告
    * */
    public static final String KAISUOZHONGGUANGGAO = "Feedback/advertisement_img.php";
    /*
    * APP版本更新
    * */
    public static final String UDUPDATAAPP = "Feedback/version.php";
    /*
    * 获取雨伞图标的 接口
    * */
   // public static final String HUOQUYUSHANTUBIAO = "Feedback/check_adimg.php";
    public static final String HUOQUYUSHANTUBIAO = "Update/geticon.php";
    /*
    * 第三方登录绑定手机号码
    * */
    public static final String BANGDINGSHOUJIHAOMA = "Member/bindingmyphone.php";
    /*
    *  微信支付
    * */
    public static final String WECHATPAYZHIFU = "Weixin/pay.php";

    /*
    * 客服反馈问题 接口
    * */
    public static final String KEFUFANKUI = "Feedback/getproblem.php";

    /*
    * 退款 接口
    * */
   // public static final String TUIKUANJIEKOU = "Alipaynew/refound.php";
    public static final String TUIKUANJIEKOU = "Refund/index.php";
    /*
    * 雨伞图标 还伞图标
    * */
    public static final String YUSANTUBIAO = "http://u.sunyie.com/public/uploads/";

    /*
    * 用户是否取走雨伞
    * */
    public static final String USERGETYUSANSTATUS = "Unlock/getumbrella.php";
    /*
    * 提交问题反馈
    * */
    public static final String TIJIAOWENTFANKUI = "Feedback/check_feedback.php";
    /*
    * 获取用户是否 正在使用雨伞
    * */
    public static final String GET_USER_CURRENTS= "/Member/getinsuerumbrella.php";
    /*
    * 商家版本注册
    * */
    public static final String SHOPPINGUSERREGISTER = "Merchant/MerchantRegistration.php";
    /*
    * 商家登录
    * */
    public static final String SHOPPING_LOGIN = "Merchant/Merchantlogin.php";
    /*
    * 商家认证接口
    * */
    public static final String SHOPPING_AUTIONER = "Merchant/Authentication.php";
    /*
    * 商务中心认证界面
    * */
    public static final String SHANGWUZHONGXINRENZHENGJIEMIAN = "Merchant/check_merchant.php";

    /*
    * 检测用户是否绑定过手机号码
    * */
    public static final String CHECK_BINGDING = "/Merchant/checkphone.php ";
    /*
    * 查询商家是否已经认证过
    * */
    public static final String QUERYSHOPPINGAUT = "/Merchant/check_merchant.php";
    /*
    * 更换图片的 接口
    * */
    public static final String UPDATA_IMAGE_CHANG = "/Merchant/updata_img.php";

    /*
    * 添加商户的收获地址
    * */
    public static final String ADD_SHOPPING_ADDRESS = "/Merchant/add_address.php";
    /*
    * 获取用户新增 地址
    * */
    public static final String GET_SHOPPING_ADDRESS = "/Merchant/getaddress.php";
    /*
    * 修改商家收获地址
    * */
    public static final String EDIT_SHOPPING_ADDRSSS = "Merchant/update_address.php";
    /*
    * 获取商户的默认地址以及伞架的个数和伞的数量
    * */
    public static final String GET_SHOPPING_SANZUOSAN = "/Merchant/getstandandum.php";
    /*
    * 删除收获地址
    * */
    public static final String DELTE_SHOPPING_ADDRESS = "/Merchant/delete_address.php";
}
