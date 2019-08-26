package com.tyiroad.tyiroad.quality;

import java.util.List;

public class FxgcBean {

    /**
     * STATE : 1
     * DATA : [{"GUID_OBJ":"f215c34f-9e11-44ae-a32a-3a20d87afca0","SJXMGUID":"bcc11c44-304b-4279-95b8-0ba1d0c860a2","XMMC":"石方工程","GCXMID":"fff40eaa-9d8d-460f-b044-04704536fc03"},{"GUID_OBJ":"bcc11c44-304b-4279-95b8-0ba1d0c860a2","SJXMGUID":"00000000-0000-0000-0000-000000000000","XMMC":"路基土石方工程","GCXMID":"fff40eaa-9d8d-460f-b044-04704536fc03"},{"GUID_OBJ":"865d3d46-9f1b-471f-af9f-83159df324a3","SJXMGUID":"bcc11c44-304b-4279-95b8-0ba1d0c860a2","XMMC":"加筋工程土工合成材料","GCXMID":"fff40eaa-9d8d-460f-b044-04704536fc03"},{"GUID_OBJ":"0e27f838-6c86-49d4-ae7a-4979da653634","SJXMGUID":"bcc11c44-304b-4279-95b8-0ba1d0c860a2","XMMC":"隔离工程土工合成材料","GCXMID":"fff40eaa-9d8d-460f-b044-04704536fc03"},{"GUID_OBJ":"0df96823-8340-48b4-b199-db2ba8e01ad2","SJXMGUID":"bcc11c44-304b-4279-95b8-0ba1d0c860a2","XMMC":"防裂工程土工合成材料","GCXMID":"fff40eaa-9d8d-460f-b044-04704536fc03"},{"GUID_OBJ":"6c28ff15-c5ae-4aa7-aca1-b63b8309d81e","SJXMGUID":"bcc11c44-304b-4279-95b8-0ba1d0c860a2","XMMC":"过滤排水工程土工合成材料","GCXMID":"fff40eaa-9d8d-460f-b044-04704536fc03"}]
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
         * GUID_OBJ : f215c34f-9e11-44ae-a32a-3a20d87afca0
         * SJXMGUID : bcc11c44-304b-4279-95b8-0ba1d0c860a2
         * XMMC : 石方工程
         * GCXMID : fff40eaa-9d8d-460f-b044-04704536fc03
         */

        private String GUID_OBJ;
        private String SJXMGUID;
        private String XMMC;
//        private String GCXMID;

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

//        public String getGCXMID() {
//            return GCXMID;
//        }
//
//        public void setGCXMID(String GCXMID) {
//            this.GCXMID = GCXMID;
//        }
    }
}
