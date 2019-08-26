package com.tyiroad.tyiroad.quality;

import java.util.List;

/**
 * Created by 张成昆 on 2019-7-6.
 */

public class ListDataBean {

    /**
     * STATE : 1
     * DATA : [{"GCXMID":"b9bd8749-2e5c-4be3-9def-8b14fa5b0de2","GUID_OBJ":"45168604-75ac-48b7-bb2a-13e7c86ceddf","SJXMGUID":"f215c34f-9e11-44ae-a32a-3a20d87afca0","XMMC":"平整度(mm)","XMCS":"20"},{"GCXMID":"b9bd8749-2e5c-4be3-9def-8b14fa5b0de2","GUID_OBJ":"6ab92f19-88db-413f-81c8-6de9005e2dd6","SJXMGUID":"f215c34f-9e11-44ae-a32a-3a20d87afca0","XMMC":"横坡(%)","XMCS":"±0.3"}]
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
         * GCXMID : b9bd8749-2e5c-4be3-9def-8b14fa5b0de2
         * GUID_OBJ : 45168604-75ac-48b7-bb2a-13e7c86ceddf
         * SJXMGUID : f215c34f-9e11-44ae-a32a-3a20d87afca0
         * XMMC : 平整度(mm)
         * XMCS : 20
         */

        private String GCXMID;
        private String GUID_OBJ;
        private String SJXMGUID;
        private String XMMC;
        private String XMCS;
        private String ISCHECK;
        private String HGDS;

        public String getHGDS() {
            return HGDS;
        }

        public void setHGDS(String HGDS) {
            this.HGDS = HGDS;
        }

        public String getISCHECK() {
            return ISCHECK;
        }

        public void setISCHECK(String ISCHECK) {
            this.ISCHECK = ISCHECK;
        }

        public String getGCXMID() {
            return GCXMID;
        }

        public void setGCXMID(String GCXMID) {
            this.GCXMID = GCXMID;
        }

        public String getGUID_OBJ() {
            return GUID_OBJ;
        }

        public void setGUID_OBJ(String GUID_OBJ) {
            this.GUID_OBJ = GUID_OBJ;
        }

        public String getSJXMGUID() {
            return SJXMGUID;
        }

        public void setSJXMGUID(String SJXMGUID) {
            this.SJXMGUID = SJXMGUID;
        }

        public String getXMMC() {
            return XMMC;
        }

        public void setXMMC(String XMMC) {
            this.XMMC = XMMC;
        }

        public String getXMCS() {
            return XMCS;
        }

        public void setXMCS(String XMCS) {
            this.XMCS = XMCS;
        }
    }
}
