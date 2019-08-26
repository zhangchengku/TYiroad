package com.tyiroad.tyiroad.quality;

import java.util.List;

public class XmmcBean {

    /**
     * STATE : 1
     * DATA : [{"GUID_OBJ":"fff40eaa-9d8d-460f-b044-04704536fc03","XMMC":"通化市2019年度普通干线公路养护工程（S205省道石通公路白山通化界至冰湖沟段）19YH01标段"}]
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
         * GUID_OBJ : fff40eaa-9d8d-460f-b044-04704536fc03
         * XMMC : 通化市2019年度普通干线公路养护工程（S205省道石通公路白山通化界至冰湖沟段）19YH01标段
         */

        private String GUID_OBJ;
        private String XMMC;

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
    }
}
