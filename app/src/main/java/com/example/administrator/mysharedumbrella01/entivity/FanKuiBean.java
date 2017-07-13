package com.example.administrator.mysharedumbrella01.entivity;

/**
 * Created by Administrator on 2017/7/12 0012.
 *   假数据 用户反馈实体类
 */

public class FanKuiBean {
    String types;
    boolean isSelect =false ;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }
}
