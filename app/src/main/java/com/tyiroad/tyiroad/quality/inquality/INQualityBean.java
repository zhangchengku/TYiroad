package com.tyiroad.tyiroad.quality.inquality;

import java.util.List;

/**
 * Created by 张成昆 on 2019-7-2.
 */

public class INQualityBean {

    /**
     * MSG : SUCCESS
     * DATA : {"DATAMX":[{"GUID_OBJ":"7e91e564-fa11-49c5-9ff5-ad2f0300e592","XMID":"0aafd34d-00b2-4005-ab54-4a738be2aa89","XMMC":"测试项目","FBGCID":"bcc11c44-304b-4279-95b8-0ba1d0c860a2","FBGCMC":"路基土石方工程","FXGCID":"f215c34f-9e11-44ae-a32a-3a20d87afca0","FXGCMC":"石方工程","DLWZ":null,"GCBW":"gcbw","JCR":"成坤"}],"DATAJCXMMX":[{"JCXMID":"c797e8ac-26c1-419b-9c19-94f326ab453b","JCXMMC":"压实度","JCJG":"1","JCFWZ":"层厚和碾压遍数符合要求"},{"JCXMID":"aef22338-f575-4ee3-a072-883fc20c20ee","JCXMMC":"边坡(坡度)","JCJG":"1","JCFWZ":"尺量，1处/50米"},{"JCXMID":"a70ebfcf-d599-412b-9b74-37d9d1e6384a","JCXMMC":"横坡(%)","JCJG":"0","JCFWZ":"±0.3"},{"JCXMID":"8ef292cc-64b4-4ed5-8825-d0cb4952ea01","JCXMMC":"中线偏位(mm)","JCJG":"1","JCFWZ":"50"},{"JCXMID":"d8441a6e-c3ad-4e0c-aaed-e593ad0bbc31","JCXMMC":"宽度(mm)","JCJG":"1","JCFWZ":"符合设计要求"},{"JCXMID":"6aa2f902-4280-4bb6-af49-faf900a166b5","JCXMMC":"纵断高程(mm)","JCJG":"1","JCFWZ":"+10,-20"},{"JCXMID":"22857ba1-4b47-43ee-a331-d71017233b64","JCXMMC":"平整度(mm)","JCJG":"1","JCFWZ":"20"}],"DATAPICMX":[{"FILEPATH":"http://106.37.229.146:4141/Files/YHXC/TEMP/PIC/shopImage_7e91e564-fa11-49c5-9ff5-ad2f0300e592.png"}]}
     * STATE : 1
     */

    private String MSG;
    private DATABean DATA;
    private String STATE;

    public String getMSG() {
        return MSG;
    }

    public void setMSG(String MSG) {
        this.MSG = MSG;
    }

    public DATABean getDATA() {
        return DATA;
    }

    public void setDATA(DATABean DATA) {
        this.DATA = DATA;
    }

    public String getSTATE() {
        return STATE;
    }

    public void setSTATE(String STATE) {
        this.STATE = STATE;
    }

    public static class DATABean {
        private List<DATAMXBean> DATAMX;
        private List<DATAJCXMMXBean> DATAJCXMMX;
        private List<DATAPICMXBean> DATAPICMX;

        public List<DATAMXBean> getDATAMX() {
            return DATAMX;
        }

        public void setDATAMX(List<DATAMXBean> DATAMX) {
            this.DATAMX = DATAMX;
        }

        public List<DATAJCXMMXBean> getDATAJCXMMX() {
            return DATAJCXMMX;
        }

        public void setDATAJCXMMX(List<DATAJCXMMXBean> DATAJCXMMX) {
            this.DATAJCXMMX = DATAJCXMMX;
        }

        public List<DATAPICMXBean> getDATAPICMX() {
            return DATAPICMX;
        }

        public void setDATAPICMX(List<DATAPICMXBean> DATAPICMX) {
            this.DATAPICMX = DATAPICMX;
        }

        public static class DATAMXBean {
            /**
             * GUID_OBJ : 7e91e564-fa11-49c5-9ff5-ad2f0300e592
             * XMID : 0aafd34d-00b2-4005-ab54-4a738be2aa89
             * XMMC : 测试项目
             * FBGCID : bcc11c44-304b-4279-95b8-0ba1d0c860a2
             * FBGCMC : 路基土石方工程
             * FXGCID : f215c34f-9e11-44ae-a32a-3a20d87afca0
             * FXGCMC : 石方工程
             * DLWZ : null
             * GCBW : gcbw
             * JCR : 成坤
             */

            private String GUID_OBJ;
            private String XMID;
            private String XMMC;
            private String FBGCID;
            private String FBGCMC;
            private String FXGCID;
            private String FXGCMC;
            private String DLWZ;
            private String GCBW;
            private String JCR;
            private String BHGSM;

            public String getBHGSM() {
                return BHGSM;
            }

            public void setBHGSM(String BHGSM) {
                this.BHGSM = BHGSM;
            }

            public String getDLWZ() {
                return DLWZ;
            }

            public void setDLWZ(String DLWZ) {
                this.DLWZ = DLWZ;
            }

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

            public String getFBGCID() {
                return FBGCID;
            }

            public void setFBGCID(String FBGCID) {
                this.FBGCID = FBGCID;
            }

            public String getFBGCMC() {
                return FBGCMC;
            }

            public void setFBGCMC(String FBGCMC) {
                this.FBGCMC = FBGCMC;
            }

            public String getFXGCID() {
                return FXGCID;
            }

            public void setFXGCID(String FXGCID) {
                this.FXGCID = FXGCID;
            }

            public String getFXGCMC() {
                return FXGCMC;
            }

            public void setFXGCMC(String FXGCMC) {
                this.FXGCMC = FXGCMC;
            }



            public String getGCBW() {
                return GCBW;
            }

            public void setGCBW(String GCBW) {
                this.GCBW = GCBW;
            }

            public String getJCR() {
                return JCR;
            }

            public void setJCR(String JCR) {
                this.JCR = JCR;
            }
        }

        public static class DATAJCXMMXBean {
            /**
             * JCXMID : c797e8ac-26c1-419b-9c19-94f326ab453b
             * JCXMMC : 压实度
             * JCJG : 1
             * JCFWZ : 层厚和碾压遍数符合要求
             */

            private String JCXMID;
            private String JCXMMC;
            private String JCJG;
            private String JCFWZ;

            public String getJCXMID() {
                return JCXMID;
            }

            public void setJCXMID(String JCXMID) {
                this.JCXMID = JCXMID;
            }

            public String getJCXMMC() {
                return JCXMMC;
            }

            public void setJCXMMC(String JCXMMC) {
                this.JCXMMC = JCXMMC;
            }

            public String getJCJG() {
                return JCJG;
            }

            public void setJCJG(String JCJG) {
                this.JCJG = JCJG;
            }

            public String getJCFWZ() {
                return JCFWZ;
            }

            public void setJCFWZ(String JCFWZ) {
                this.JCFWZ = JCFWZ;
            }
        }

        public static class DATAPICMXBean {
            /**
             * FILEPATH : http://106.37.229.146:4141/Files/YHXC/TEMP/PIC/shopImage_7e91e564-fa11-49c5-9ff5-ad2f0300e592.png
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
}
