package com.example.administrator.mysharedumbrella01.entivity;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.entivity
 * 文件名：SanZuoSanRecordBean
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/14 0014 9:25
 * 描述：伞座/伞的记录
 */
public class SanZuoSanRecordBean {
    private String yearMothdata; //年月日、
    private String time; //时间
    private String sanzuoCount; //伞座个数
    private String yusanCount; //雨伞个数
    private String bianhao; //编号

    public String getBianhao() {
        return bianhao;
    }

    public void setBianhao(String bianhao) {
        this.bianhao = bianhao;
    }

    public String getYearMothdata() {
        return yearMothdata;
    }

    public void setYearMothdata(String yearMothdata) {
        this.yearMothdata = yearMothdata;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSanzuoCount() {
        return sanzuoCount;
    }

    public void setSanzuoCount(String sanzuoCount) {
        this.sanzuoCount = sanzuoCount;
    }

    public String getYusanCount() {
        return yusanCount;
    }

    public void setYusanCount(String yusanCount) {
        this.yusanCount = yusanCount;
    }
}
