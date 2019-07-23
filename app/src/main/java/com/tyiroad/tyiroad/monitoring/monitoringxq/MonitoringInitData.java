package com.tyiroad.tyiroad.monitoring.monitoringxq;

import java.util.List;

/**
 * Created by 张成昆 on 2019-6-28.
 */

public class MonitoringInitData {

    /**
     * MSG : SUCCESS
     * STATE : 1
     * XMDATA : [{"GUID_OBJ":"0aafd34d-00b2-4005-ab54-4a738be2aa89","F03":"测试项目"},{"GUID_OBJ":"463977a8-107e-496e-9213-3db47a14443f","F03":"吉林通化养护工程测试20190628"}]
     */

    private String MSG;
    private String STATE;
    private List<XMDATABean> XMDATA;

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

    public List<XMDATABean> getXMDATA() {
        return XMDATA;
    }

    public void setXMDATA(List<XMDATABean> XMDATA) {
        this.XMDATA = XMDATA;
    }

    public static class XMDATABean {
        /**
         * GUID_OBJ : 0aafd34d-00b2-4005-ab54-4a738be2aa89
         * F03 : 测试项目
         */

        private String GUID_OBJ;
        private String F03;

        public String getGUID_OBJ() {
            return GUID_OBJ;
        }

        public void setGUID_OBJ(String GUID_OBJ) {
            this.GUID_OBJ = GUID_OBJ;
        }

        public String getF03() {
            return F03;
        }

        public void setF03(String F03) {
            this.F03 = F03;
        }
    }
}
