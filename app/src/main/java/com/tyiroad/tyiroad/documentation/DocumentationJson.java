package com.tyiroad.tyiroad.documentation;

import java.util.List;

public class DocumentationJson {

    /**
     * GCID : 1e804c8d-2691-427c-8470-7b5c7fed33ca
     * LOCATION : 地理位置
     * GCJD : 工程阶段
     * ZLLX : 资料类型
     * ZLMC : 资料名称
     * SCSJ : 2019-08-16
     * SCR : 李怀安
     * SCDW : 00000000-0000-0000-0000-000000000000
     * PIC : [{"PicFileName":"1491897074640.jpg","PicFileType":"jpg","PicDataBlob":""}]
     */

    private String GCID;
    private String LOCATION;
    private String GCJD;
    private String ZLLX;
    private String ZLMC;
    private String SCSJ;
    private String SCR;
    private String SCDW;
    private List<PICBean> PIC;
    private String USERID;

    public String getUSERID() {
        return USERID;
    }

    public void setUSERID(String USERID) {
        this.USERID = USERID;
    }

    public String getGCID() {
        return GCID;
    }

    public void setGCID(String GCID) {
        this.GCID = GCID;
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

    public List<PICBean> getPIC() {
        return PIC;
    }

    public void setPIC(List<PICBean> PIC) {
        this.PIC = PIC;
    }

    public static class PICBean {
        /**
         * PicFileName : 1491897074640.jpg
         * PicFileType : jpg
         * PicDataBlob :
         */

        private String PicFileName;
        private String PicFileType;
        private String PicDataBlob;

        public String getPicFileName() {
            return PicFileName;
        }

        public void setPicFileName(String PicFileName) {
            this.PicFileName = PicFileName;
        }

        public String getPicFileType() {
            return PicFileType;
        }

        public void setPicFileType(String PicFileType) {
            this.PicFileType = PicFileType;
        }

        public String getPicDataBlob() {
            return PicDataBlob;
        }

        public void setPicDataBlob(String PicDataBlob) {
            this.PicDataBlob = PicDataBlob;
        }
    }
}
