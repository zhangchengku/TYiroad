package com.tyiroad.tyiroad.Bean;

import java.util.List;

/**
 * Created by 张成昆 on 2019-5-24.
 */

public class Yjczlistbean {

    /**
     * MSG : SUCCESS
     * STATE : 1
     * YJLIST : [{"RNINDEX":1,"RN":1,"GUID_OBJ":"3557d53d-ebd7-4b3a-b352-6949cc5bbcdb","LXCODE":"S212","QDZH":1250.128,"ZDZH":1580.125,"TPDZ":"http://106.37.229.146:5001","FSSJ":"2019-05-22 16:33:00","STATE":"新事件","ZHLX":"公路水浸","YJDJ":"黄色预警"},{"RNINDEX":2,"RN":2,"GUID_OBJ":"f28e38e6-1485-49c9-b7cf-40a958426712","LXCODE":"S212","QDZH":218.913,"ZDZH":219.123,"TPDZ":"http://106.37.229.146:5001","FSSJ":"2019-05-24 14:17:00","STATE":"新事件","ZHLX":"公路水浸","YJDJ":"蓝色预警"}]
     * ISALSO : 0
     */

    private String MSG;
    private String STATE;
    private String ISALSO;
    private List<YJLISTBean> YJLIST;

    public String getMSG() {
        return MSG;
    }

    public void setMSG(String MSG) {
        this.MSG = MSG;
    }

    public String getSTATE() {
        return STATE;
    }

    public void setSTATE(String STATE) {
        this.STATE = STATE;
    }

    public String getISALSO() {
        return ISALSO;
    }

    public void setISALSO(String ISALSO) {
        this.ISALSO = ISALSO;
    }

    public List<YJLISTBean> getYJLIST() {
        return YJLIST;
    }

    public void setYJLIST(List<YJLISTBean> YJLIST) {
        this.YJLIST = YJLIST;
    }

    public static class YJLISTBean {
        /**
         * RNINDEX : 1
         * RN : 1
         * GUID_OBJ : 3557d53d-ebd7-4b3a-b352-6949cc5bbcdb
         * LXCODE : S212
         * QDZH : 1250.128
         * ZDZH : 1580.125
         * TPDZ : http://106.37.229.146:5001
         * FSSJ : 2019-05-22 16:33:00
         * STATE : 新事件
         * ZHLX : 公路水浸
         * YJDJ : 黄色预警
         */

        private int RNINDEX;
        private int RN;
        private String GUID_OBJ;
        private String LXCODE;
        private double QDZH;
        private double ZDZH;
        private String TPDZ;
        private String FSSJ;
        private String STATE;
        private String ZHLX;
        private String YJDJ;

        public int getRNINDEX() {
            return RNINDEX;
        }

        public void setRNINDEX(int RNINDEX) {
            this.RNINDEX = RNINDEX;
        }

        public int getRN() {
            return RN;
        }

        public void setRN(int RN) {
            this.RN = RN;
        }

        public String getGUID_OBJ() {
            return GUID_OBJ;
        }

        public void setGUID_OBJ(String GUID_OBJ) {
            this.GUID_OBJ = GUID_OBJ;
        }

        public String getLXCODE() {
            return LXCODE;
        }

        public void setLXCODE(String LXCODE) {
            this.LXCODE = LXCODE;
        }

        public double getQDZH() {
            return QDZH;
        }

        public void setQDZH(double QDZH) {
            this.QDZH = QDZH;
        }

        public double getZDZH() {
            return ZDZH;
        }

        public void setZDZH(double ZDZH) {
            this.ZDZH = ZDZH;
        }

        public String getTPDZ() {
            return TPDZ;
        }

        public void setTPDZ(String TPDZ) {
            this.TPDZ = TPDZ;
        }

        public String getFSSJ() {
            return FSSJ;
        }

        public void setFSSJ(String FSSJ) {
            this.FSSJ = FSSJ;
        }

        public String getSTATE() {
            return STATE;
        }

        public void setSTATE(String STATE) {
            this.STATE = STATE;
        }

        public String getZHLX() {
            return ZHLX;
        }

        public void setZHLX(String ZHLX) {
            this.ZHLX = ZHLX;
        }

        public String getYJDJ() {
            return YJDJ;
        }

        public void setYJDJ(String YJDJ) {
            this.YJDJ = YJDJ;
        }
    }
}
