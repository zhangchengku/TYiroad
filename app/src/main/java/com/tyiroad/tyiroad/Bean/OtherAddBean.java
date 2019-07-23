package com.tyiroad.tyiroad.Bean;

import java.util.List;

/**
 * Created by 张成昆 on 2019-6-6.
 */

public class OtherAddBean {
    private String GYDW;
    private String GYDWMC;
    private String LXCODE;
    private String LXMC;
    private String QDZH;
    private String ZDZH;
    private String GCXM;
    private String JLDW;
    private String SL;
    private String DJ;
    private String GZL;
    private String SBSJ;
    private String SBR;
    private String WZFX;
    private String CREATOR;
    private List<PICBean> PIC;
    private String STATE;
    private String GUID_OBJ;
    private String CREATETIME;
    private String SHYJ;

    public String getCREATETIME() {
        return CREATETIME;
    }

    public void setCREATETIME(String CREATETIME) {
        this.CREATETIME = CREATETIME;
    }

    public String getSHYJ() {
        return SHYJ;
    }

    public void setSHYJ(String SHYJ) {
        this.SHYJ = SHYJ;
    }

    public String getGUID_OBJ() {
        return GUID_OBJ;
    }

    public void setGUID_OBJ(String GUID_OBJ) {
        this.GUID_OBJ = GUID_OBJ;
    }

    public String getSTATE() {
        return STATE;
    }

    public void setSTATE(String STATE) {
        this.STATE = STATE;
    }

    public List<PICBean> getPIC() {
        return PIC;
    }

    public void setPIC(List<PICBean> PIC) {
        this.PIC = PIC;
    }

    public String getCREATOR() {
        return CREATOR;
    }

    public void setCREATOR(String CREATOR) {
        this.CREATOR = CREATOR;
    }

    public String getGYDW() {
        return GYDW;
    }

    public void setGYDW(String GYDW) {
        this.GYDW = GYDW;
    }

    public String getGYDWMC() {
        return GYDWMC;
    }

    public void setGYDWMC(String GYDWMC) {
        this.GYDWMC = GYDWMC;
    }

    public String getLXCODE() {
        return LXCODE;
    }

    public void setLXCODE(String LXCODE) {
        this.LXCODE = LXCODE;
    }

    public String getLXMC() {
        return LXMC;
    }

    public void setLXMC(String LXMC) {
        this.LXMC = LXMC;
    }

    public String getQDZH() {
        return QDZH;
    }

    public void setQDZH(String QDZH) {
        this.QDZH = QDZH;
    }

    public String getZDZH() {
        return ZDZH;
    }

    public void setZDZH(String ZDZH) {
        this.ZDZH = ZDZH;
    }

    public String getGCXM() {
        return GCXM;
    }

    public void setGCXM(String GCXM) {
        this.GCXM = GCXM;
    }

    public String getJLDW() {
        return JLDW;
    }

    public void setJLDW(String JLDW) {
        this.JLDW = JLDW;
    }

    public String getSL() {
        return SL;
    }

    public void setSL(String SL) {
        this.SL = SL;
    }

    public String getDJ() {
        return DJ;
    }

    public void setDJ(String DJ) {
        this.DJ = DJ;
    }

    public String getGZL() {
        return GZL;
    }

    public void setGZL(String GZL) {
        this.GZL = GZL;
    }

    public String getSBSJ() {
        return SBSJ;
    }

    public void setSBSJ(String SBSJ) {
        this.SBSJ = SBSJ;
    }

    public String getSBR() {
        return SBR;
    }

    public void setSBR(String SBR) {
        this.SBR = SBR;
    }

    public String getWZFX() {
        return WZFX;
    }

    public void setWZFX(String WZFX) {
        this.WZFX = WZFX;
    }
    public static class PICBean {
        /**
         * PicFileName : 1491897074640.jpg
         * PicFileType : jpg
         * PicDataBlob :
         * WJLX : 0
         */

        private String PicFileName;
        private String PicFileType;
        private String PicDataBlob;

        public String getPicFileName() {
            return PicFileName;
        }

        public void setPicFileName(String picFileName) {
            PicFileName = picFileName;
        }

        public String getPicFileType() {
            return PicFileType;
        }

        public void setPicFileType(String picFileType) {
            PicFileType = picFileType;
        }

        public String getPicDataBlob() {
            return PicDataBlob;
        }

        public void setPicDataBlob(String picDataBlob) {
            PicDataBlob = picDataBlob;
        }
    }
}
