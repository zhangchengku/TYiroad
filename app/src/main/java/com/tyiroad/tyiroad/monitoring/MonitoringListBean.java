package com.tyiroad.tyiroad.monitoring;

import java.util.List;

/**
 * Created by 张成昆 on 2019-7-1.
 */

public class MonitoringListBean {

    /**
     * MSG : SUCCESS
     * DATA : [{"RNINDEX":1,"RN":1,"GUID_OBJ":"a7fbb808-029d-45f8-9d9f-2a7d42fb6f4c","CREATOR":"马睿","CREATETIME":"2019-07-01T09:03:38","MODIFYTIME":"2019-07-01T09:03:38","XMID":"0aafd34d-00b2-4005-ab54-4a738be2aa89","XMMC":"测试项目","XMMS":"kkkk","XMZT":"已上传","SCSJ":"2019-07-01 09:03","SCDWID":"5c16c3d8-13c5-40ac-95b7-1c9496ff208a","SCDWMC":"金厂道班","LOCATION":null,"TPDZ":"http://106.37.229.146:5001/Files/YHXC/TEMP/PIC/1561943008543891_a7fbb808-029d-45f8-9d9f-2a7d42fb6f4c.jpg"}]
     * STATE : 1
     * ISALSO : 0
     */

    private String MSG;
    private String STATE;
    private String ISALSO;
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

    public String getISALSO() {
        return ISALSO;
    }

    public void setISALSO(String ISALSO) {
        this.ISALSO = ISALSO;
    }

    public List<DATABean> getDATA() {
        return DATA;
    }

    public void setDATA(List<DATABean> DATA) {
        this.DATA = DATA;
    }

    public static class DATABean {
        /**
         * RNINDEX : 1
         * RN : 1
         * GUID_OBJ : a7fbb808-029d-45f8-9d9f-2a7d42fb6f4c
         * CREATOR : 马睿
         * CREATETIME : 2019-07-01T09:03:38
         * MODIFYTIME : 2019-07-01T09:03:38
         * XMID : 0aafd34d-00b2-4005-ab54-4a738be2aa89
         * XMMC : 测试项目
         * XMMS : kkkk
         * XMZT : 已上传
         * SCSJ : 2019-07-01 09:03
         * SCDWID : 5c16c3d8-13c5-40ac-95b7-1c9496ff208a
         * SCDWMC : 金厂道班
         * LOCATION : null
         * TPDZ : http://106.37.229.146:5001/Files/YHXC/TEMP/PIC/1561943008543891_a7fbb808-029d-45f8-9d9f-2a7d42fb6f4c.jpg
         */

        private int RNINDEX;
        private int RN;
        private String GUID_OBJ;
        private String CREATOR;
        private String CREATETIME;
        private String MODIFYTIME;
        private String XMID;
        private String XMMC;
        private String XMMS;
        private String XMZT;
        private String SCSJ;
        private String SCDWID;
        private String SCDWMC;
        private String LOCATION;
        private String TPDZ;

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

        public String getCREATOR() {
            return CREATOR;
        }

        public void setCREATOR(String CREATOR) {
            this.CREATOR = CREATOR;
        }

        public String getCREATETIME() {
            return CREATETIME;
        }

        public void setCREATETIME(String CREATETIME) {
            this.CREATETIME = CREATETIME;
        }

        public String getMODIFYTIME() {
            return MODIFYTIME;
        }

        public void setMODIFYTIME(String MODIFYTIME) {
            this.MODIFYTIME = MODIFYTIME;
        }

        public String getXMID() {
            return XMID;
        }

        public void setXMID(String XMID) {
            this.XMID = XMID;
        }

        public String getXMMC() {
            return XMMC;
        }

        public void setXMMC(String XMMC) {
            this.XMMC = XMMC;
        }

        public String getXMMS() {
            return XMMS;
        }

        public void setXMMS(String XMMS) {
            this.XMMS = XMMS;
        }

        public String getXMZT() {
            return XMZT;
        }

        public void setXMZT(String XMZT) {
            this.XMZT = XMZT;
        }

        public String getSCSJ() {
            return SCSJ;
        }

        public void setSCSJ(String SCSJ) {
            this.SCSJ = SCSJ;
        }

        public String getSCDWID() {
            return SCDWID;
        }

        public void setSCDWID(String SCDWID) {
            this.SCDWID = SCDWID;
        }

        public String getSCDWMC() {
            return SCDWMC;
        }

        public void setSCDWMC(String SCDWMC) {
            this.SCDWMC = SCDWMC;
        }

        public String getLOCATION() {
            return LOCATION;
        }

        public void setLOCATION(String LOCATION) {
            this.LOCATION = LOCATION;
        }

        public String getTPDZ() {
            return TPDZ;
        }

        public void setTPDZ(String TPDZ) {
            this.TPDZ = TPDZ;
        }
    }
}
