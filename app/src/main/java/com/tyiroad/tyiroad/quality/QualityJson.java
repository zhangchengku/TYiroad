package com.tyiroad.tyiroad.quality;

import java.util.List;

/**
 * Created by 张成昆 on 2019-6-28.
 */

public class QualityJson {


    /**
     * Xmmc : 项目名称guid
     * Fbgc : 分部工程guid
     * Fxgc : 分项工程guid
     * Gcbw : 工程部位
     * Jcr : 登录名
     * Jcdw : 登录用户单位
     * JcxmList : [{"Guid":"id1,id2","lsHg":"1,0"}]
     * Bhgsm : 不合格说明
     * PIC : []
     * Dlwz :  地理位置
     */

    private String Xmmc;
    private String Fbgc;
    private String Fxgc;
    private String Gcbw;
    private String Jcr;
    private String Jcdw;
    private String Bhgsm;
    private String Dlwz;
    private List<JcxmListBean> JcxmList;
    private List<PICBean> PIC;

    public List<PICBean> getPIC() {
        return PIC;
    }

    public void setPIC(List<PICBean> PIC) {
        this.PIC = PIC;
    }

    public String getXmmc() {
        return Xmmc;
    }

    public void setXmmc(String Xmmc) {
        this.Xmmc = Xmmc;
    }

    public String getFbgc() {
        return Fbgc;
    }

    public void setFbgc(String Fbgc) {
        this.Fbgc = Fbgc;
    }

    public String getFxgc() {
        return Fxgc;
    }

    public void setFxgc(String Fxgc) {
        this.Fxgc = Fxgc;
    }

    public String getGcbw() {
        return Gcbw;
    }

    public void setGcbw(String Gcbw) {
        this.Gcbw = Gcbw;
    }

    public String getJcr() {
        return Jcr;
    }

    public void setJcr(String Jcr) {
        this.Jcr = Jcr;
    }

    public String getJcdw() {
        return Jcdw;
    }

    public void setJcdw(String Jcdw) {
        this.Jcdw = Jcdw;
    }

    public String getBhgsm() {
        return Bhgsm;
    }

    public void setBhgsm(String Bhgsm) {
        this.Bhgsm = Bhgsm;
    }

    public String getDlwz() {
        return Dlwz;
    }

    public void setDlwz(String Dlwz) {
        this.Dlwz = Dlwz;
    }

    public List<JcxmListBean> getJcxmList() {
        return JcxmList;
    }

    public void setJcxmList(List<JcxmListBean> JcxmList) {
        this.JcxmList = JcxmList;
    }



    public static class JcxmListBean {
        /**
         * Guid : id1,id2
         * lsHg : 1,0
         */

        private String Guid;
        private String lsHg;

        public String getGuid() {
            return Guid;
        }

        public void setGuid(String Guid) {
            this.Guid = Guid;
        }

        public String getLsHg() {
            return lsHg;
        }

        public void setLsHg(String lsHg) {
            this.lsHg = lsHg;
        }
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
