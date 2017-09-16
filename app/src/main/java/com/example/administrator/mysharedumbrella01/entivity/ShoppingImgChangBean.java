package com.example.administrator.mysharedumbrella01.entivity;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.entivity
 * 文件名：ShoppingImgChangBean
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/13 0013 15:37
 * 描述：街景 实体类
 */
public class ShoppingImgChangBean {

    /**
     * status : 1
     * data : {"adimg":"adimg/20170913/8c6b247a844be5343647dd323dc401de.png"}
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
         * adimg : adimg/20170913/8c6b247a844be5343647dd323dc401de.png
         */

        private String adimg;
        private String proimg;


        public String getProimg() {
            return proimg;
        }

        public void setProimg(String proimg) {
            this.proimg = proimg;
        }

        public String getAdimg() {
            return adimg;
        }

        public void setAdimg(String adimg) {
            this.adimg = adimg;
        }
    }
}
