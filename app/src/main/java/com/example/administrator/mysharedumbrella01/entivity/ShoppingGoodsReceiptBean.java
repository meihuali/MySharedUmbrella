package com.example.administrator.mysharedumbrella01.entivity;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.entivity
 * 文件名：ShoppingGoodsReceiptBean
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/14 0014 14:15
 * 描述：
 */
public class ShoppingGoodsReceiptBean {
    private String danhao; // 单号
    private String address;
    //新增
    private String newYuSanCuont;
    private String newSanZuoCount;
    //合计
    private String sum;
    //实际签收
    private String sanzuo;
    private String yusan;
    //未签收
    private String wqsanzuo;
    private String wqyusan;


    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getDanhao() {
        return danhao;
    }

    public void setDanhao(String danhao) {
        this.danhao = danhao;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNewYuSanCuont() {
        return newYuSanCuont;
    }

    public void setNewYuSanCuont(String newYuSanCuont) {
        this.newYuSanCuont = newYuSanCuont;
    }

    public String getNewSanZuoCount() {
        return newSanZuoCount;
    }

    public void setNewSanZuoCount(String newSanZuoCount) {
        this.newSanZuoCount = newSanZuoCount;
    }

    public String getSanzuo() {
        return sanzuo;
    }

    public void setSanzuo(String sanzuo) {
        this.sanzuo = sanzuo;
    }

    public String getYusan() {
        return yusan;
    }

    public void setYusan(String yusan) {
        this.yusan = yusan;
    }

    public String getWqsanzuo() {
        return wqsanzuo;
    }

    public void setWqsanzuo(String wqsanzuo) {
        this.wqsanzuo = wqsanzuo;
    }

    public String getWqyusan() {
        return wqyusan;
    }

    public void setWqyusan(String wqyusan) {
        this.wqyusan = wqyusan;
    }
}
