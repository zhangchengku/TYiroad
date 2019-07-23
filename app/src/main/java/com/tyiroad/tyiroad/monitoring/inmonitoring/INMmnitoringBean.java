package com.tyiroad.tyiroad.monitoring.inmonitoring;

import java.util.List;

/**
 * Created by 张成昆 on 2019-7-1.
 */

public class INMmnitoringBean {

    /**
     * MSG : SUCCESS
     * DATA : [{"GUID_OBJ":"a7fbb808-029d-45f8-9d9f-2a7d42fb6f4c","XMID":"0aafd34d-00b2-4005-ab54-4a738be2aa89","XMMC":"测试项目","XMMS":"kkkk","SCSJ":"2019-07-01 09:01","SCDWID":"5c16c3d8-13c5-40ac-95b7-1c9496ff208a","SCDWMC":"金厂道班","LOCATION":null}]
     * STATE : 1
     * PICDATA : [{"FILEPATH":"http://106.37.229.146:5001/Files/YHXC/TEMP/PIC/1561943008543891_a7fbb808-029d-45f8-9d9f-2a7d42fb6f4c.jpg"}]
     */

    private String MSG;
    private String STATE;
    private List<DATABean> DATA;
    private List<PICDATABean> PICDATA;

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

    public List<PICDATABean> getPICDATA() {
        return PICDATA;
    }

    public void setPICDATA(List<PICDATABean> PICDATA) {
        this.PICDATA = PICDATA;
    }

    public static class DATABean {
        /**
         * GUID_OBJ : a7fbb808-029d-45f8-9d9f-2a7d42fb6f4c
         * XMID : 0aafd34d-00b2-4005-ab54-4a738be2aa89
         * XMMC : 测试项目
         * XMMS : kkkk
         * SCSJ : 2019-07-01 09:01
         * SCDWID : 5c16c3d8-13c5-40ac-95b7-1c9496ff208a
         * SCDWMC : 金厂道班
         * LOCATION : null
         */

        private String GUID_OBJ;
        private String XMID;
        private String XMMC;
        private String XMMS;
        private String SCSJ;
        private String SCDWID;
        private String SCDWMC;
        private Object LOCATION;

        public String getGUID_OBJ() {
            return GUID_OBJ;
        }

        public void setGUID_OBJ(String GUID_OBJ) {
            this.GUID_OBJ = GUID_OBJ;
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

        public Object getLOCATION() {
            return LOCATION;
        }

        public void setLOCATION(Object LOCATION) {
            this.LOCATION = LOCATION;
        }
    }

    public static class PICDATABean {
        /**
         * FILEPATH : http://106.37.229.146:5001/Files/YHXC/TEMP/PIC/1561943008543891_a7fbb808-029d-45f8-9d9f-2a7d42fb6f4c.jpg
         */

        private String FILEPATH;

        public String getFILEPATH() {
            return FILEPATH;
        }

        public void setFILEPATH(String FILEPATH) {
            this.FILEPATH = FILEPATH;
        }
    }
}
