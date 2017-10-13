package com.example.administrator.mysharedumbrella01.entivity;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.entivity
 * 文件名：ShoppingConfirmGoodsBean
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/20 0020 16:40
 * 描述：TODO
 */
public class ShoppingConfirmGoodsBean {

    /**
     * status : 1
     * data : {"stand":"0","umbrella":"0"}
     */

    private int status;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * stand : 0
         * umbrella : 0
         */

        private String stand;
        private String umbrella;

        public String getStand() {
            return stand;
        }

        public void setStand(String stand) {
            this.stand = stand;
        }

        public String getUmbrella() {
            return umbrella;
        }

        public void setUmbrella(String umbrella) {
            this.umbrella = umbrella;
        }
    }
}
