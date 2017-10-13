package com.example.administrator.mysharedumbrella01.entivity;

import java.util.List;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.entivity
 * 文件名：GetShoppingJiluBean
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/21 0021 17:19
 * 描述：TODO
 */
public class GetShoppingJiluBean {

    /**
     * status : 1
     * data : [{"p_id":"1186","appid":"2017061607503315","body":"1","subject":"2","pay_way":"2","umbrella_user":"17620193389","buyer_id":"176****3389","buyer_name":"buyer_name","money":0.01,"pay_number":"2017092121001004940507096621","out_trade_no":"2017092148588748","create_time":"1505958500","transaction_time":"2017-09-21 09:48:29","payment_state":"TRADE_SUCCESS","out_request_no":"","refound_status":"","refund_fee":"0.00","refund_id":"","is_merchant":"1","is_add":"1"},{"p_id":"1187","appid":"2017061607503315","body":"1","subject":"2","pay_way":"2","umbrella_user":"17620193389","buyer_id":"176****3389","buyer_name":"buyer_name","money":0.01,"pay_number":"2017092121001004940507117524","out_trade_no":"2017092149493760","create_time":"1505958587","transaction_time":"2017-09-21 09:49:54","payment_state":"TRADE_SUCCESS","out_request_no":"","refound_status":"","refund_fee":"0.00","refund_id":"","is_merchant":"1","is_add":"1"},{"p_id":"1188","appid":"2017061607503315","body":"1","subject":"2","pay_way":"2","umbrella_user":"17620193389","buyer_id":"176****3389","buyer_name":"buyer_name","money":0.01,"pay_number":"2017092121001004940507110035","out_trade_no":"2017092151172291","create_time":"1505958710","transaction_time":"2017-09-21 09:51:58","payment_state":"TRADE_SUCCESS","out_request_no":"","refound_status":"","refund_fee":"0.00","refund_id":"","is_merchant":"1","is_add":"1"},{"p_id":"1189","appid":"2017061607503315","body":"1","subject":"2","pay_way":"2","umbrella_user":"17620193389","buyer_id":"176****3389","buyer_name":"buyer_name","money":0.01,"pay_number":"2017092121001004940507115855","out_trade_no":"2017092152402088","create_time":"1505958754","transaction_time":"2017-09-21 09:52:41","payment_state":"TRADE_SUCCESS","out_request_no":"","refound_status":"","refund_fee":"0.00","refund_id":"","is_merchant":"1","is_add":"1"},{"p_id":"1190","appid":"2017061607503315","body":"1","subject":"2","pay_way":"2","umbrella_user":"17620193389","buyer_id":"176****3389","buyer_name":"buyer_name","money":0.01,"pay_number":"2017092121001004940507096985","out_trade_no":"2017092157161685","create_time":"1505959035","transaction_time":"2017-09-21 09:57:21","payment_state":"TRADE_SUCCESS","out_request_no":"","refound_status":"","refund_fee":"0.00","refund_id":"","is_merchant":"1","is_add":"1"},{"p_id":"1191","appid":"2017061607503315","body":"1","subject":"2","pay_way":"2","umbrella_user":"17620193389","buyer_id":"176****3389","buyer_name":"buyer_name","money":0.01,"pay_number":"2017092121001004940507097023","out_trade_no":"201709215839179","create_time":"1505959108","transaction_time":"2017-09-21 09:58:35","payment_state":"TRADE_SUCCESS","out_request_no":"","refound_status":"","refund_fee":"0.00","refund_id":"","is_merchant":"1","is_add":"1"},{"p_id":"1192","appid":"2017061607503315","body":"1","subject":"2","pay_way":"2","umbrella_user":"17620193389","buyer_id":"176****3389","buyer_name":"buyer_name","money":0.01,"pay_number":"2017092121001004940507140112","out_trade_no":"2017092159136378","create_time":"1505959157","transaction_time":"2017-09-21 09:59:24","payment_state":"TRADE_SUCCESS","out_request_no":"","refound_status":"","refund_fee":"0.00","refund_id":"","is_merchant":"1","is_add":"1"},{"p_id":"1193","appid":"2017061607503315","body":"1","subject":"2","pay_way":"2","umbrella_user":"17620193389","buyer_id":"176****3389","buyer_name":"buyer_name","money":0.01,"pay_number":"2017092121001004940507157193","out_trade_no":"2017092100683156","create_time":"1505959222","transaction_time":"2017-09-21 10:00:29","payment_state":"TRADE_SUCCESS","out_request_no":"","refound_status":"","refund_fee":"0.00","refund_id":"","is_merchant":"1","is_add":"1"},{"p_id":"1194","appid":"2017061607503315","body":"1","subject":"2","pay_way":"2","umbrella_user":"17620193389","buyer_id":"176****3389","buyer_name":"buyer_name","money":0.01,"pay_number":"2017092121001004940507116340","out_trade_no":"2017092102850647","create_time":"1505959365","transaction_time":"2017-09-21 10:02:52","payment_state":"TRADE_SUCCESS","out_request_no":"","refound_status":"","refund_fee":"0.00","refund_id":"","is_merchant":"1","is_add":"1"},{"p_id":"1195","appid":"2017061607503315","body":"1","subject":"2","pay_way":"2","umbrella_user":"17620193389","buyer_id":"176****3389","buyer_name":"buyer_name","money":0.01,"pay_number":"2017092121001004940507186324","out_trade_no":"2017092104416055","create_time":"1505959446","transaction_time":"2017-09-21 10:04:13","payment_state":"TRADE_SUCCESS","out_request_no":"","refound_status":"","refund_fee":"0.00","refund_id":"","is_merchant":"1","is_add":"1"},{"p_id":"1197","appid":"2017061607503315","body":"1","subject":"2","pay_way":"2","umbrella_user":"17620193389","buyer_id":"176****3389","buyer_name":"buyer_name","money":0.01,"pay_number":"2017092121001004940507176499","out_trade_no":"2017092106737427","create_time":"1505959570","transaction_time":"2017-09-21 10:06:17","payment_state":"TRADE_SUCCESS","out_request_no":"","refound_status":"","refund_fee":"0.00","refund_id":"","is_merchant":"1","is_add":"1"},{"p_id":"1198","appid":"2017061607503315","body":"1","subject":"2","pay_way":"2","umbrella_user":"17620193389","buyer_id":"176****3389","buyer_name":"buyer_name","money":0.01,"pay_number":"2017092121001004940507212458","out_trade_no":"201709211132384","create_time":"1505959860","transaction_time":"2017-09-21 10:11:14","payment_state":"TRADE_SUCCESS","out_request_no":"","refound_status":"","refund_fee":"0.00","refund_id":"","is_merchant":"1","is_add":"1"},{"p_id":"1200","appid":"2017061607503315","body":"1","subject":"2","pay_way":"2","umbrella_user":"17620193389","buyer_id":"176****3389","buyer_name":"buyer_name","money":0.01,"pay_number":"2017092121001004940507228044","out_trade_no":"2017092130958132","create_time":"1505961024","transaction_time":"2017-09-21 10:30:31","payment_state":"TRADE_SUCCESS","out_request_no":"","refound_status":"","refund_fee":"0.00","refund_id":"","is_merchant":"1","is_add":"1"},{"p_id":"1202","appid":"2017061607503315","body":"1","subject":"2","pay_way":"2","umbrella_user":"17620193389","buyer_id":"176****3389","buyer_name":"buyer_name","money":0.01,"pay_number":"2017092121001004940507275264","out_trade_no":"2017092133969469","create_time":"1505961234","transaction_time":"2017-09-21 10:34:00","payment_state":"TRADE_SUCCESS","out_request_no":"","refound_status":"","refund_fee":"0.00","refund_id":"","is_merchant":"1","is_add":"1"},{"p_id":"1203","appid":"2017061607503315","body":"1","subject":"2","pay_way":"2","umbrella_user":"17620193389","buyer_id":"176****3389","buyer_name":"buyer_name","money":0.01,"pay_number":"2017092121001004940507231007","out_trade_no":"2017092134945162","create_time":"1505961277","transaction_time":"2017-09-21 10:34:53","payment_state":"TRADE_SUCCESS","out_request_no":"","refound_status":"","refund_fee":"0.00","refund_id":"","is_merchant":"1","is_add":"1"},{"p_id":"1204","appid":"2017061607503315","body":"1","subject":"2","pay_way":"2","umbrella_user":"17620193389","buyer_id":"176****3389","buyer_name":"buyer_name","money":0.01,"pay_number":"2017092121001004940507229878","out_trade_no":"2017092137838543","create_time":"1505961421","transaction_time":"2017-09-21 10:37:08","payment_state":"TRADE_SUCCESS","out_request_no":"","refound_status":"","refund_fee":"0.00","refund_id":"","is_merchant":"1","is_add":"1"},{"p_id":"1205","appid":"2017061607503315","body":"1","subject":"2","pay_way":"2","umbrella_user":"17620193389","buyer_id":"176****3389","buyer_name":"buyer_name","money":0.01,"pay_number":"2017092121001004940507228464","out_trade_no":"2017092139877451","create_time":"1505961586","transaction_time":"2017-09-21 10:39:52","payment_state":"TRADE_SUCCESS","out_request_no":"","refound_status":"","refund_fee":"0.00","refund_id":"","is_merchant":"1","is_add":"1"},{"p_id":"1206","appid":"2017061607503315","body":"1","subject":"2","pay_way":"2","umbrella_user":"17620193389","buyer_id":"176****3389","buyer_name":"buyer_name","money":0.01,"pay_number":"2017092121001004940507237798","out_trade_no":"2017092142932763","create_time":"1505961725","transaction_time":"2017-09-21 10:42:11","payment_state":"TRADE_SUCCESS","out_request_no":"","refound_status":"","refund_fee":"0.00","refund_id":"","is_merchant":"1","is_add":"1"},{"p_id":"1207","appid":"wxac2d038a3a418057","body":"1","subject":"2","pay_way":"1","umbrella_user":"17620193389","buyer_id":"o5MzfwF9OUoKlT_UI-KPnl5EocfU","buyer_name":null,"money":"0.01","pay_number":"4200000002201709213337573571","out_trade_no":"2017092142652270","create_time":"1505961763","transaction_time":"20170921104251","payment_state":"TRADE_SUCCESS","out_request_no":"","refound_status":"","refund_fee":"0.00","refund_id":"","is_merchant":"1","is_add":"1"},{"p_id":"1208","appid":"wxac2d038a3a418057","body":"1","subject":"2","pay_way":"1","umbrella_user":"17620193389","buyer_id":"o5MzfwF9OUoKlT_UI-KPnl5EocfU","buyer_name":null,"money":"0.01","pay_number":"4200000027201709213340420486","out_trade_no":"2017092104361979","create_time":"1505963083","transaction_time":"20170921110459","payment_state":"TRADE_SUCCESS","out_request_no":"","refound_status":"","refund_fee":"0.00","refund_id":"","is_merchant":"1","is_add":"1"},{"p_id":"1209","appid":"wxac2d038a3a418057","body":"1","subject":"2","pay_way":"1","umbrella_user":"17620193389","buyer_id":"o5MzfwF9OUoKlT_UI-KPnl5EocfU","buyer_name":null,"money":"0.01","pay_number":"4200000027201709213343016829","out_trade_no":"2017092107419592","create_time":"1505963228","transaction_time":"20170921110716","payment_state":"TRADE_SUCCESS","out_request_no":"","refound_status":"","refund_fee":"0.00","refund_id":"","is_merchant":"1","is_add":"1"},{"p_id":"1210","appid":"wxac2d038a3a418057","body":"1","subject":"2","pay_way":"1","umbrella_user":"17620193389","buyer_id":"o5MzfwF9OUoKlT_UI-KPnl5EocfU","buyer_name":null,"money":"0.01","pay_number":"4200000029201709213343098607","out_trade_no":"2017092108707407","create_time":"1505963334","transaction_time":"20170921110901","payment_state":"TRADE_SUCCESS","out_request_no":"","refound_status":"","refund_fee":"0.00","refund_id":"","is_merchant":"1","is_add":"1"},{"p_id":"1211","appid":"wxac2d038a3a418057","body":"1","subject":"2","pay_way":"1","umbrella_user":"17620193389","buyer_id":"o5MzfwF9OUoKlT_UI-KPnl5EocfU","buyer_name":null,"money":"0.01","pay_number":"4200000006201709213341150821","out_trade_no":"2017092109523880","create_time":"1505963382","transaction_time":"20170921110950","payment_state":"TRADE_SUCCESS","out_request_no":"","refound_status":"","refund_fee":"0.00","refund_id":"","is_merchant":"1","is_add":"1"},{"p_id":"1212","appid":"wxac2d038a3a418057","body":"1","subject":"2","pay_way":"1","umbrella_user":"17620193389","buyer_id":"o5MzfwF9OUoKlT_UI-KPnl5EocfU","buyer_name":null,"money":"0.01","pay_number":"4200000020201709213339304158","out_trade_no":"2017092111901332","create_time":"1505963489","transaction_time":"20170921111137","payment_state":"TRADE_SUCCESS","out_request_no":"","refound_status":"","refund_fee":"0.00","refund_id":"","is_merchant":"1","is_add":"1"},{"p_id":"1213","appid":"wxac2d038a3a418057","body":"1","subject":"2","pay_way":"1","umbrella_user":"17620193389","buyer_id":"o5MzfwF9OUoKlT_UI-KPnl5EocfU","buyer_name":null,"money":"0.01","pay_number":"4200000002201709213344960860","out_trade_no":"2017092114272002","create_time":"1505963657","transaction_time":"20170921111431","payment_state":"TRADE_SUCCESS","out_request_no":"","refound_status":"","refund_fee":"0.00","refund_id":"","is_merchant":"1","is_add":"1"},{"p_id":"1214","appid":"wxac2d038a3a418057","body":"1","subject":"2","pay_way":"1","umbrella_user":"17620193389","buyer_id":"o5MzfwF9OUoKlT_UI-KPnl5EocfU","buyer_name":null,"money":"0.01","pay_number":"4200000023201709213339876835","out_trade_no":"201709212224611","create_time":"1505964170","transaction_time":"20170921112257","payment_state":"TRADE_SUCCESS","out_request_no":"","refound_status":"","refund_fee":"0.00","refund_id":"","is_merchant":"1","is_add":"1"},{"p_id":"1215","appid":"wxac2d038a3a418057","body":"1","subject":"2","pay_way":"1","umbrella_user":"17620193389","buyer_id":"o5MzfwF9OUoKlT_UI-KPnl5EocfU","buyer_name":null,"money":"0.01","pay_number":"4200000020201709213350959721","out_trade_no":"2017092146997497","create_time":"1505965598","transaction_time":"20170921114646","payment_state":"TRADE_SUCCESS","out_request_no":"","refound_status":"","refund_fee":"0.00","refund_id":"","is_merchant":"1","is_add":"1"},{"p_id":"1216","appid":"wxac2d038a3a418057","body":"1","subject":"2","pay_way":"1","umbrella_user":"17620193389","buyer_id":"o5MzfwF9OUoKlT_UI-KPnl5EocfU","buyer_name":null,"money":"0.01","pay_number":"4200000020201709213351032861","out_trade_no":"2017092148822412","create_time":"1505965704","transaction_time":"20170921114832","payment_state":"TRADE_SUCCESS","out_request_no":"","refound_status":"","refund_fee":"0.00","refund_id":"","is_merchant":"1","is_add":"1"},{"p_id":"1217","appid":"wxac2d038a3a418057","body":"1","subject":"2","pay_way":"1","umbrella_user":"17620193389","buyer_id":"o5MzfwF9OUoKlT_UI-KPnl5EocfU","buyer_name":null,"money":"0.01","pay_number":"4200000007201709213352616200","out_trade_no":"2017092150877645","create_time":"1505965840","transaction_time":"20170921115048","payment_state":"TRADE_SUCCESS","out_request_no":"","refound_status":"","refund_fee":"0.00","refund_id":"","is_merchant":"1","is_add":"1"},{"p_id":"1218","appid":"wxac2d038a3a418057","body":"1","subject":"2","pay_way":"1","umbrella_user":"17620193389","buyer_id":"o5MzfwF9OUoKlT_UI-KPnl5EocfU","buyer_name":null,"money":"0.01","pay_number":"4200000026201709213352778725","out_trade_no":"2017092154537149","create_time":"1505966088","transaction_time":"20170921115455","payment_state":"TRADE_SUCCESS","out_request_no":"","refound_status":"","refund_fee":"0.00","refund_id":"","is_merchant":"1","is_add":"1"},{"p_id":"1219","appid":"wxac2d038a3a418057","body":"1","subject":"2","pay_way":"1","umbrella_user":"17620193389","buyer_id":"o5MzfwF9OUoKlT_UI-KPnl5EocfU","buyer_name":null,"money":"0.01","pay_number":"4200000013201709213351473914","out_trade_no":"2017092155121517","create_time":"1505966145","transaction_time":"20170921115552","payment_state":"TRADE_SUCCESS","out_request_no":"","refound_status":"","refund_fee":"0.00","refund_id":"","is_merchant":"1","is_add":"1"},{"p_id":"1220","appid":"wxac2d038a3a418057","body":"1","subject":"2","pay_way":"1","umbrella_user":"17620193389","buyer_id":"o5MzfwF9OUoKlT_UI-KPnl5EocfU","buyer_name":null,"money":"0.01","pay_number":"4200000019201709213352170096","out_trade_no":"20170921082782","create_time":"1505966901","transaction_time":"20170921120828","payment_state":"TRADE_SUCCESS","out_request_no":"","refound_status":"","refund_fee":"0.00","refund_id":"","is_merchant":"1","is_add":"1"},{"p_id":"1221","appid":"2017061607503315","body":"1","subject":"2","pay_way":"2","umbrella_user":"17620193389","buyer_id":"176****3389","buyer_name":"buyer_name","money":0.01,"pay_number":"2017092121001004940507541207","out_trade_no":"201709210861242","create_time":"1505966928","transaction_time":"2017-09-21 12:09:10","payment_state":"TRADE_SUCCESS","out_request_no":"","refound_status":"","refund_fee":"0.00","refund_id":"","is_merchant":"1","is_add":"1"},{"p_id":"1223","appid":"2017061607503315","body":"1","subject":"2","pay_way":"2","umbrella_user":"17620193389","buyer_id":"176****3389","buyer_name":"buyer_name","money":0.01,"pay_number":"2017092121001004940507545124","out_trade_no":"2017092109807359","create_time":"1505966975","transaction_time":"2017-09-21 12:09:42","payment_state":"TRADE_SUCCESS","out_request_no":"","refound_status":"","refund_fee":"0.00","refund_id":"","is_merchant":"1","is_add":"1"}]
     */

    private int status;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * p_id : 1186
         * appid : 2017061607503315
         * body : 1
         * subject : 2
         * pay_way : 2
         * umbrella_user : 17620193389
         * buyer_id : 176****3389
         * buyer_name : buyer_name
         * money : 0.01
         * pay_number : 2017092121001004940507096621
         * out_trade_no : 2017092148588748
         * create_time : 1505958500
         * transaction_time : 2017-09-21 09:48:29
         * payment_state : TRADE_SUCCESS
         * out_request_no :
         * refound_status :
         * refund_fee : 0.00
         * refund_id :
         * is_merchant : 1
         * is_add : 1
         */

        private String p_id;
        private String appid;
        private String body;
        private String subject;
        private String pay_way;
        private String umbrella_user;
        private String buyer_id;
        private String buyer_name;
        private double money;
        private String pay_number;
        private String out_trade_no;
        private String create_time;
        private String transaction_time;
        private String payment_state;
        private String out_request_no;
        private String refound_status;
        private String refund_fee;
        private String refund_id;
        private String is_merchant;
        private String is_add;

        public String getP_id() {
            return p_id;
        }

        public void setP_id(String p_id) {
            this.p_id = p_id;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getPay_way() {
            return pay_way;
        }

        public void setPay_way(String pay_way) {
            this.pay_way = pay_way;
        }

        public String getUmbrella_user() {
            return umbrella_user;
        }

        public void setUmbrella_user(String umbrella_user) {
            this.umbrella_user = umbrella_user;
        }

        public String getBuyer_id() {
            return buyer_id;
        }

        public void setBuyer_id(String buyer_id) {
            this.buyer_id = buyer_id;
        }

        public String getBuyer_name() {
            return buyer_name;
        }

        public void setBuyer_name(String buyer_name) {
            this.buyer_name = buyer_name;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public String getPay_number() {
            return pay_number;
        }

        public void setPay_number(String pay_number) {
            this.pay_number = pay_number;
        }

        public String getOut_trade_no() {
            return out_trade_no;
        }

        public void setOut_trade_no(String out_trade_no) {
            this.out_trade_no = out_trade_no;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getTransaction_time() {
            return transaction_time;
        }

        public void setTransaction_time(String transaction_time) {
            this.transaction_time = transaction_time;
        }

        public String getPayment_state() {
            return payment_state;
        }

        public void setPayment_state(String payment_state) {
            this.payment_state = payment_state;
        }

        public String getOut_request_no() {
            return out_request_no;
        }

        public void setOut_request_no(String out_request_no) {
            this.out_request_no = out_request_no;
        }

        public String getRefound_status() {
            return refound_status;
        }

        public void setRefound_status(String refound_status) {
            this.refound_status = refound_status;
        }

        public String getRefund_fee() {
            return refund_fee;
        }

        public void setRefund_fee(String refund_fee) {
            this.refund_fee = refund_fee;
        }

        public String getRefund_id() {
            return refund_id;
        }

        public void setRefund_id(String refund_id) {
            this.refund_id = refund_id;
        }

        public String getIs_merchant() {
            return is_merchant;
        }

        public void setIs_merchant(String is_merchant) {
            this.is_merchant = is_merchant;
        }

        public String getIs_add() {
            return is_add;
        }

        public void setIs_add(String is_add) {
            this.is_add = is_add;
        }
    }
}
