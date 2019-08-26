package com.tyiroad.tyiroad.documentation.list;

import java.util.List;

public class ListBean {


    /**
     * MSG : 操作成功
     * DATA : [{"GUID_OBJ":"b50d5ec0-6075-42f5-bf7a-8ac05d93d8ee","GCMC":"工程测试20190816","CJWZ":"不在范围内","GCJD":"项目前期","ZLLX":"质检资料","ZLMC":"测试","SCSJ":"2019-08-17","CJR":"成坤","SCDW":"通化市郊公路管理段","IMAGEURL":"http://106.37.229.146:4141/Files/YHXC/TEMP/PIC/1558409835151425_b50d5ec0-6075-42f5-bf7a-8ac05d93d8ee.jpg","RN":1},{"GUID_OBJ":"f737b321-a1e5-4716-95f4-0253c5caff30","GCMC":"工程测试20190816","CJWZ":"不在范围内","GCJD":"项目前期","ZLLX":"质检资料","ZLMC":"测试","SCSJ":"2019-08-17","CJR":"成坤","SCDW":"通化市郊公路管理段","IMAGEURL":"http://106.37.229.146:4141/Files/YHXC/TEMP/PIC/1566010772596861_f737b321-a1e5-4716-95f4-0253c5caff30.jpg","RN":2},{"GUID_OBJ":"8176c9ae-bd33-44cc-a3e6-eddf2ba3d0e8","GCMC":"工程测试20190816","CJWZ":"不在范围内","GCJD":"项目前期","ZLLX":"计量资料","ZLMC":"测试","SCSJ":"2019-08-17","CJR":"成坤","SCDW":"通化市郊公路管理段","IMAGEURL":"http://106.37.229.146:4141","RN":3}]
     * STATE : 1
     */

    private String MSG;
    private String STATE;
    private List<DATABean> DATA;

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

    public List<DATABean> getDATA() {
        return DATA;
    }

    public void setDATA(List<DATABean> DATA) {
        this.DATA = DATA;
    }

    public static class DATABean {
        /**
         * GUID_OBJ : b50d5ec0-6075-42f5-bf7a-8ac05d93d8ee
         * GCMC : 工程测试20190816
         * CJWZ : 不在范围内
         * GCJD : 项目前期
         * ZLLX : 质检资料
         * ZLMC : 测试
         * SCSJ : 2019-08-17
         * CJR : 成坤
         * SCDW : 通化市郊公路管理段
         * IMAGEURL : http://106.37.229.146:4141/Files/YHXC/TEMP/PIC/1558409835151425_b50d5ec0-6075-42f5-bf7a-8ac05d93d8ee.jpg
         * RN : 1
         */

        private String GUID_OBJ;
        private String GCMC;
        private String CJWZ;
        private String GCJD;
        private String ZLLX;
        private String ZLMC;
        private String SCSJ;
        private String CJR;
        private String SCDW;
        private String IMAGEURL;
        private int RN;

        public String getGUID_OBJ() {
            return GUID_OBJ;
        }

        public void setGUID_OBJ(String GUID_OBJ) {
            this.GUID_OBJ = GUID_OBJ;
        }

        public String getGCMC() {
            return GCMC;
        }

        public void setGCMC(String GCMC) {
            this.GCMC = GCMC;
        }

        public String getCJWZ() {
            return CJWZ;
        }

        public void setCJWZ(String CJWZ) {
            this.CJWZ = CJWZ;
        }

        public String getGCJD() {
            return GCJD;
        }

        public void setGCJD(String GCJD) {
            this.GCJD = GCJD;
        }

        public String getZLLX() {
            return ZLLX;
        }

        public void setZLLX(String ZLLX) {
            this.ZLLX = ZLLX;
        }

        public String getZLMC() {
            return ZLMC;
        }

        public void setZLMC(String ZLMC) {
            this.ZLMC = ZLMC;
        }

        public String getSCSJ() {
            return SCSJ;
        }

        public void setSCSJ(String SCSJ) {
            this.SCSJ = SCSJ;
        }

        public String getCJR() {
            return CJR;
        }

        public void setCJR(String CJR) {
            this.CJR = CJR;
        }

        public String getSCDW() {
            return SCDW;
        }

        public void setSCDW(String SCDW) {
            this.SCDW = SCDW;
        }

        public String getIMAGEURL() {
            return IMAGEURL;
        }

        public void setIMAGEURL(String IMAGEURL) {
            this.IMAGEURL = IMAGEURL;
        }

        public int getRN() {
            return RN;
        }

        public void setRN(int RN) {
            this.RN = RN;
        }
    }
}
