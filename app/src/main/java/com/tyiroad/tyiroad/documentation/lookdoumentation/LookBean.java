package com.tyiroad.tyiroad.documentation.lookdoumentation;

import java.util.List;

public class LookBean {

    /**
     * STATE : 1
     * DATA : {"GUID_OBJ":"f737b321-a1e5-4716-95f4-0253c5caff30","GCMC":"工程测试20190816","LOCATION":"不在范围内","GCJD":"项目前期","ZLLX":"质检资料","ZLMC":"测试","SCSJ":"2019-08-17","SCR":"成坤","SCDW":"通化市郊公路管理段","IMGLIST":[{"IMGURL":"http://106.37.229.146:4141/Files/YHXC/TEMP/PIC/1566010772596861_f737b321-a1e5-4716-95f4-0253c5caff30.jpg"},{"IMGURL":"http://106.37.229.146:4141/Files/YHXC/TEMP/PIC/1566010776722451_f737b321-a1e5-4716-95f4-0253c5caff30.jpg"}]}
     */

    private String STATE;
    private DATABean DATA;
    private String MSG;

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

    public DATABean getDATA() {
        return DATA;
    }

    public void setDATA(DATABean DATA) {
        this.DATA = DATA;
    }

    public static class DATABean {
        /**
         * GUID_OBJ : f737b321-a1e5-4716-95f4-0253c5caff30
         * GCMC : 工程测试20190816
         * LOCATION : 不在范围内
         * GCJD : 项目前期
         * ZLLX : 质检资料
         * ZLMC : 测试
         * SCSJ : 2019-08-17
         * SCR : 成坤
         * SCDW : 通化市郊公路管理段
         * IMGLIST : [{"IMGURL":"http://106.37.229.146:4141/Files/YHXC/TEMP/PIC/1566010772596861_f737b321-a1e5-4716-95f4-0253c5caff30.jpg"},{"IMGURL":"http://106.37.229.146:4141/Files/YHXC/TEMP/PIC/1566010776722451_f737b321-a1e5-4716-95f4-0253c5caff30.jpg"}]
         */

        private String GUID_OBJ;
        private String GCMC;
        private String LOCATION;
        private String GCJD;
        private String ZLLX;
        private String ZLMC;
        private String SCSJ;
        private String SCR;
        private String SCDW;
        private List<IMGLISTBean> IMGLIST;

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

        public String getLOCATION() {
            return LOCATION;
        }

        public void setLOCATION(String LOCATION) {
            this.LOCATION = LOCATION;
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

        public String getSCR() {
            return SCR;
        }

        public void setSCR(String SCR) {
            this.SCR = SCR;
        }

        public String getSCDW() {
            return SCDW;
        }

        public void setSCDW(String SCDW) {
            this.SCDW = SCDW;
        }

        public List<IMGLISTBean> getIMGLIST() {
            return IMGLIST;
        }

        public void setIMGLIST(List<IMGLISTBean> IMGLIST) {
            this.IMGLIST = IMGLIST;
        }

        public static class IMGLISTBean {
            /**
             * IMGURL : http://106.37.229.146:4141/Files/YHXC/TEMP/PIC/1566010772596861_f737b321-a1e5-4716-95f4-0253c5caff30.jpg
             */

            private String IMGURL;

            public String getIMGURL() {
                return IMGURL;
            }

            public void setIMGURL(String IMGURL) {
                this.IMGURL = IMGURL;
            }
        }
    }
}
