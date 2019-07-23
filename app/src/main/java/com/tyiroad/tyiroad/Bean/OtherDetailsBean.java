package com.tyiroad.tyiroad.Bean;

import java.util.List;

/**
 * Created by 张成昆 on 2019-6-10.
 */

public class OtherDetailsBean {
    /**
     * MSG : SUCCESS
     * STATE : 1
     * LISTDATA : [{"GUID_OBJ":"63eec956-7b0f-48d5-9468-36c7564af867","CREATOR":"成坤","CREATETIME":"2019-06-10T09:35:02","MODIFYTIME":"2019-06-10T09:35:02","GYDW":"d4078045-645d-4802-bc74-b0ce703ff9cc","GYDWMC":"通化市郊公路管理段","LXCODE":"92095fb4-7a27-4d86-a277-1d5b1aced9e9","LXMC":"G201 鹤大线","QDZH":1022.12,"ZDZH":1023.23,"GCXM":"2-1","JLDW":"m","SL":10,"DJ":12,"GZL":null,"SBSJ":"2019-06-10T09:34:00","SBR":"成坤","WZFX":"下行左","STATE":"1"}]
     */

    private String MSG;
    private String STATE;
    private List<LISTDATABean> LISTDATA;
    private List<FILEDATABean> FILEDATA;

    public List<FILEDATABean> getFILEDATA() {
        return FILEDATA;
    }

    public void setFILEDATA(List<FILEDATABean> FILEDATA) {
        this.FILEDATA = FILEDATA;
    }

    public static String replaceNull(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }

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

    public List<LISTDATABean> getLISTDATA() {
        return  LISTDATA;
    }

    public void setLISTDATA(List<LISTDATABean> LISTDATA) {
        this.LISTDATA = LISTDATA;
    }

    public static class LISTDATABean {
        /**
         * GUID_OBJ : 63eec956-7b0f-48d5-9468-36c7564af867
         * CREATOR : 成坤
         * CREATETIME : 2019-06-10T09:35:02
         * MODIFYTIME : 2019-06-10T09:35:02
         * GYDW : d4078045-645d-4802-bc74-b0ce703ff9cc
         * GYDWMC : 通化市郊公路管理段
         * LXCODE : 92095fb4-7a27-4d86-a277-1d5b1aced9e9
         * LXMC : G201 鹤大线
         * QDZH : 1022.12
         * ZDZH : 1023.23
         * GCXM : 2-1
         * JLDW : m
         * SL : 10
         * DJ : 12
         * GZL : null
         * SBSJ : 2019-06-10T09:34:00
         * SBR : 成坤
         * WZFX : 下行左
         * STATE : 1
         */

        private String GUID_OBJ;
        private String CREATOR;
        private String CREATETIME;
        private String MODIFYTIME;
        private String GYDW;
        private String GYDWMC;
        private String LXCODE;
        private String LXMC;
        private double QDZH;
        private double ZDZH;
        private String GCXM;
        private String JLDW;
        private String SL;
        private String DJ;
        private Object GZL;
        private String SBSJ;
        private String SBR;
        private String WZFX;
        private String STATE;
        private String SHYJ;

        public String getSHYJ() {
            return SHYJ;
        }

        public void setSHYJ(String SHYJ) {
            this.SHYJ = SHYJ;
        }

        public String getGUID_OBJ() {
            return replaceNull( GUID_OBJ);
        }

        public void setGUID_OBJ(String GUID_OBJ) {
            this.GUID_OBJ = GUID_OBJ;
        }

        public String getCREATOR() {
            return replaceNull( CREATOR);
        }

        public void setCREATOR(String CREATOR) {
            this.CREATOR = CREATOR;
        }

        public String getCREATETIME() {
            return replaceNull( CREATETIME);
        }

        public void setCREATETIME(String CREATETIME) {
            this.CREATETIME = CREATETIME;
        }

        public String getMODIFYTIME() {
            return replaceNull( MODIFYTIME);
        }

        public void setMODIFYTIME(String MODIFYTIME) {
            this.MODIFYTIME = MODIFYTIME;
        }

        public String getGYDW() {
            return replaceNull( GYDW);
        }

        public void setGYDW(String GYDW) {
            this.GYDW = GYDW;
        }

        public String getGYDWMC() {
            return replaceNull( GYDWMC);
        }

        public void setGYDWMC(String GYDWMC) {
            this.GYDWMC = GYDWMC;
        }

        public String getLXCODE() {
            return replaceNull( LXCODE);
        }

        public void setLXCODE(String LXCODE) {
            this.LXCODE = LXCODE;
        }

        public String getLXMC() {
            return replaceNull( LXMC);
        }

        public void setLXMC(String LXMC) {
            this.LXMC = LXMC;
        }

        public double getQDZH() {
            return  QDZH;
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

        public String getGCXM() {
            return replaceNull( GCXM);
        }

        public void setGCXM(String GCXM) {
            this.GCXM = GCXM;
        }

        public String getJLDW() {
            return replaceNull( JLDW);
        }

        public void setJLDW(String JLDW) {
            this.JLDW = JLDW;
        }

        public String getSL() {
            return  SL;
        }

        public void setSL(String SL) {
            this.SL = SL;
        }

        public String getDJ() {
            return  DJ;
        }

        public void setDJ(String DJ) {
            this.DJ = DJ;
        }

        public Object getGZL() {
            return  GZL;
        }

        public void setGZL(Object GZL) {
            this.GZL = GZL;
        }

        public String getSBSJ() {
            return replaceNull( SBSJ);
        }

        public void setSBSJ(String SBSJ) {
            this.SBSJ = SBSJ;
        }

        public String getSBR() {
            return replaceNull( SBR);
        }

        public void setSBR(String SBR) {
            this.SBR = SBR;
        }

        public String getWZFX() {
            return replaceNull( WZFX);
        }

        public void setWZFX(String WZFX) {
            this.WZFX = WZFX;
        }

        public String getSTATE() {
            return replaceNull( STATE);
        }

        public void setSTATE(String STATE) {
            this.STATE = STATE;
        }
    }
    public static class FILEDATABean {
        /**
         * FILEPATH : http://106.37.229.146:5001/Files/YHXC/TEMP/PIC/adsfadf_6c3c6af4-a6b6-458d-999c-f38e305005b9.jpg
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
