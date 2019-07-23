package com.tyiroad.tyiroad.Bean;

import java.util.List;

/**
 * Created by 张成昆 on 2019-5-22.
 */

public class AddBSbean {
//    SJLCFL	事件流程分类（0：简易流程，1：完整流程）
//    CREATOR	当前登录人
//    GYDWID	管养单位
//    LXCODE	路线
//    QDZH	起点桩号
//    ZDZH	终点桩号
//    WZMS	路段位置描述（上下行）
//    YJNR	预警内容
//    YJDJ	预警等级
//    FSSJ	发生时间
//    ZHLX	灾害类型
//    JTQK	交通情况
//    SSZQ	所属镇区
//    SJQK	事件情况
//    BZ	备注
//    PIC	图片信息（PicFileName：图片名称，PicFileType：图片类型，PicDataBlob：图片流，WJLX:图片类型
    private String SJLCFL;
    private String CREATOR;
    private String GYDWID;
    private String LXCODE;
    private String QDZH;
    private String ZDZH;
    private String WZMS; private String YJNR;
    private String YJDJ;
    private String FSSJ;
    private String ZHLX;
    private String JTQK;
    private String SSZQ;
    private String SJQK;
    private String BZ;
    private List<PICBean> PIC;

    public String getSJLCFL() {
        return SJLCFL;
    }

    public void setSJLCFL(String SJLCFL) {
        this.SJLCFL = SJLCFL;
    }

    public String getCREATOR() {
        return CREATOR;
    }

    public void setCREATOR(String CREATOR) {
        this.CREATOR = CREATOR;
    }

    public String getGYDWID() {
        return GYDWID;
    }

    public void setGYDWID(String GYDWID) {
        this.GYDWID = GYDWID;
    }

    public String getLXCODE() {
        return LXCODE;
    }

    public void setLXCODE(String LXCODE) {
        this.LXCODE = LXCODE;
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

    public String getWZMS() {
        return WZMS;
    }

    public void setWZMS(String WZMS) {
        this.WZMS = WZMS;
    }

    public String getYJNR() {
        return YJNR;
    }

    public void setYJNR(String YJNR) {
        this.YJNR = YJNR;
    }

    public String getYJDJ() {
        return YJDJ;
    }

    public void setYJDJ(String YJDJ) {
        this.YJDJ = YJDJ;
    }

    public String getFSSJ() {
        return FSSJ;
    }

    public void setFSSJ(String FSSJ) {
        this.FSSJ = FSSJ;
    }

    public String getZHLX() {
        return ZHLX;
    }

    public void setZHLX(String ZHLX) {
        this.ZHLX = ZHLX;
    }

    public String getJTQK() {
        return JTQK;
    }

    public void setJTQK(String JTQK) {
        this.JTQK = JTQK;
    }

    public String getSSZQ() {
        return SSZQ;
    }

    public void setSSZQ(String SSZQ) {
        this.SSZQ = SSZQ;
    }

    public String getSJQK() {
        return SJQK;
    }

    public void setSJQK(String SJQK) {
        this.SJQK = SJQK;
    }

    public String getBZ() {
        return BZ;
    }

    public void setBZ(String BZ) {
        this.BZ = BZ;
    }

    public List<PICBean> getPIC() {
        return PIC;
    }

    public void setPIC(List<PICBean> PIC) {
        this.PIC = PIC;
    }

    public static class PICBean {
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
