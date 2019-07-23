package com.tyiroad.tyiroad.monitoring.monitoringxq;

import java.util.List;

/**
 * Created by 张成昆 on 2019-6-28.
 */

public class MonitoringXQAddJson {

    /**
     * CREATOR : 李怀安
     * XMID : 00000000-0000-0000-0000-000000000000
     * XMMS : 描述信息
     * SCSJ : 2019/06/28 10:48:40
     * SCDWID : 7cc801f8-23d7-4943-8e15-d112cdd29b3f
     * LOCATION : 地理位置
     * PIC : [{"PicFileName":"1491897074640.jpg","PicFileType":"jpg","PicDataBlob":""}]
     */

    private String CREATOR;
    private String XMID;
    private String XMMS;
    private String SCSJ;
    private String SCDWID;
    private String LOCATION;
    private List<PICBean> PIC;

    public String getCREATOR() {
        return CREATOR;
    }

    public void setCREATOR(String CREATOR) {
        this.CREATOR = CREATOR;
    }

    public String getXMID() {
        return XMID;
    }

    public void setXMID(String XMID) {
        this.XMID = XMID;
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

    public String getLOCATION() {
        return LOCATION;
    }

    public void setLOCATION(String LOCATION) {
        this.LOCATION = LOCATION;
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
