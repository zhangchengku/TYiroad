package com.tyiroad.tyiroad.quality.qualitylist;

import java.util.List;

/**
 * Created by 张成昆 on 2019-6-29.
 */

public class QualityListBean {


    /**
     * MSG : SUCCESS
     * DATA : [{"RNINDEX":1,"RN":1,"GUID_OBJ":"625ec891-2d74-4b5d-879e-ac9937efb5df","XMID":"0aafd34d-00b2-4005-ab54-4a738be2aa89","F03":"测试项目","BHGSM":"mmmmm","XMZT":"已上传","DLWZ":null,"JCSJ":"2019-06-28","FBGCMC":"路基土石方工程","FXGCMC":"石方工程","GCBW":null,"TPDZ":"http://106.37.229.146:4141"}]
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
         * GUID_OBJ : 625ec891-2d74-4b5d-879e-ac9937efb5df
         * XMID : 0aafd34d-00b2-4005-ab54-4a738be2aa89
         * F03 : 测试项目
         * BHGSM : mmmmm
         * XMZT : 已上传
         * DLWZ : null
         * JCSJ : 2019-06-28
         * FBGCMC : 路基土石方工程
         * FXGCMC : 石方工程
         * GCBW : null
         * TPDZ : http://106.37.229.146:4141
         */

        private int RNINDEX;
        private int RN;
        private String GUID_OBJ;
        private String XMID;
        private String F03;
        private String BHGSM;
        private String XMZT;
        private String DLWZ;
        private String JCSJ;
        private String FBGCMC;
        private String FXGCMC;
        private String GCBW;
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

        public String getXMID() {
            return XMID;
        }

        public void setXMID(String XMID) {
            this.XMID = XMID;
        }

        public String getF03() {
            return F03;
        }

        public void setF03(String F03) {
            this.F03 = F03;
        }

        public String getBHGSM() {
            return BHGSM;
        }

        public void setBHGSM(String BHGSM) {
            this.BHGSM = BHGSM;
        }

        public String getXMZT() {
            return XMZT;
        }

        public void setXMZT(String XMZT) {
            this.XMZT = XMZT;
        }

        public String getDLWZ() {
            return DLWZ;
        }

        public void setDLWZ(String DLWZ) {
            this.DLWZ = DLWZ;
        }

        public String getJCSJ() {
            return JCSJ;
        }

        public void setJCSJ(String JCSJ) {
            this.JCSJ = JCSJ;
        }

        public String getFBGCMC() {
            return FBGCMC;
        }

        public void setFBGCMC(String FBGCMC) {
            this.FBGCMC = FBGCMC;
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

        public String getTPDZ() {
            return TPDZ;
        }

        public void setTPDZ(String TPDZ) {
            this.TPDZ = TPDZ;
        }
    }
}
