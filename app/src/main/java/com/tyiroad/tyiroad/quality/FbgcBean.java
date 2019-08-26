package com.tyiroad.tyiroad.quality;

import java.util.List;

public class FbgcBean {

    /**
     * STATE : 1
     * DATA : [{"GUID_OBJ":"bcc11c44-304b-4279-95b8-0ba1d0c860a2","XMMC":"路基土石方工程","GCXMID":"fff40eaa-9d8d-460f-b044-04704536fc03"}]
     */

    private String STATE;
    private List<DATABean> DATA;
    private String MSG;

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

    public static class DATABean {
        /**
         * GUID_OBJ : bcc11c44-304b-4279-95b8-0ba1d0c860a2
         * XMMC : 路基土石方工程
         * GCXMID : fff40eaa-9d8d-460f-b044-04704536fc03
         */

        private String GUID_OBJ;
        private String XMMC;
        private String GCXMID;

        public String getGUID_OBJ() {
            return GUID_OBJ;
        }

        public void setGUID_OBJ(String GUID_OBJ) {
            this.GUID_OBJ = GUID_OBJ;
        }

        public String getXMMC() {
            return XMMC;
        }

        public void setXMMC(String XMMC) {
            this.XMMC = XMMC;
        }

        public String getGCXMID() {
            return GCXMID;
        }

        public void setGCXMID(String GCXMID) {
            this.GCXMID = GCXMID;
        }
    }
}
