package com.tyiroad.tyiroad.Bean;

import java.util.List;

/**
 * Created by 张成昆 on 2019-5-20.
 */

public class YJlistBean {

    /**
     * STATE : 1
     * YJYJLIST : [{"GUID_OBJ":"83dfbe50-bc66-46e7-838b-755d7676467d","YJNAME":"zz","YJJB":"省Ⅱ级","FBSJ":"2017-09-15T10:12:00"},{"GUID_OBJ":"1f28da13-1e92-4d34-8141-c98a8196fe4d","YJNAME":"ZRZHYA-001","YJJB":"省Ⅰ级","FBSJ":"2017-07-18T10:03:33"},{"GUID_OBJ":"e4a7e837-20b1-426e-af6b-24e0c7224731","YJNAME":"CXYA-001","YJJB":"省Ⅱ级","FBSJ":"2017-07-18T10:10:44"},{"GUID_OBJ":"87fb9712-8d0e-4abd-be70-6b39520e4e05","YJNAME":"SHSJYA-001","YJJB":"省Ⅲ级","FBSJ":"2017-07-18T10:21:02"}]
     */

    private String STATE;
    private List<YJYJLISTBean> YJYJLIST;

    public String getSTATE() {
        return STATE;
    }

    public void setSTATE(String STATE) {
        this.STATE = STATE;
    }

    public List<YJYJLISTBean> getYJYJLIST() {
        return YJYJLIST;
    }

    public void setYJYJLIST(List<YJYJLISTBean> YJYJLIST) {
        this.YJYJLIST = YJYJLIST;
    }

    public static class YJYJLISTBean {
        /**
         * GUID_OBJ : 83dfbe50-bc66-46e7-838b-755d7676467d
         * YJNAME : zz
         * YJJB : 省Ⅱ级
         * FBSJ : 2017-09-15T10:12:00
         */

        private String GUID_OBJ;
        private String YJNAME;
        private String YJJB;
        private String FBSJ;

        public String getGUID_OBJ() {
            return GUID_OBJ;
        }

        public void setGUID_OBJ(String GUID_OBJ) {
            this.GUID_OBJ = GUID_OBJ;
        }

        public String getYJNAME() {
            return YJNAME;
        }

        public void setYJNAME(String YJNAME) {
            this.YJNAME = YJNAME;
        }

        public String getYJJB() {
            return YJJB;
        }

        public void setYJJB(String YJJB) {
            this.YJJB = YJJB;
        }

        public String getFBSJ() {
            return FBSJ;
        }

        public void setFBSJ(String FBSJ) {
            this.FBSJ = FBSJ;
        }
    }
}
