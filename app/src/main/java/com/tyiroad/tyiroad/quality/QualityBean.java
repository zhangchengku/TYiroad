package com.tyiroad.tyiroad.quality;

import java.util.List;

/**
 * Created by 张成昆 on 2019-6-26.
 */

public class QualityBean {


    /**
     * STATE : 1
     * DATA : [{"Xmmc":[{"GUID_OBJ":"fcfe9c69-e393-437a-92ca-ce187ac45c1d","XMMC":"G201改建工程"},{"GUID_OBJ":"b9bd8749-2e5c-4be3-9def-8b14fa5b0de2","XMMC":"通化市郊工程管理"}],"Fbgc":[{"GUID_OBJ":"bcc11c44-304b-4279-95b8-0ba1d0c860a2","XMMC":"路基土石方工程","GCXMID":"b9bd8749-2e5c-4be3-9def-8b14fa5b0de2"},{"GUID_OBJ":"bcc11c44-304b-4279-95b8-0ba1d0c860a2","XMMC":"路基土石方工程","GCXMID":"fcfe9c69-e393-437a-92ca-ce187ac45c1d"}],"Fxgc":[{"GUID_OBJ":"f714484a-c7b6-4059-82a5-75863f51c244","SJXMGUID":"bcc11c44-304b-4279-95b8-0ba1d0c860a2","XMMC":"软土地基处理(沙垫层)","GCXMID":"fcfe9c69-e393-437a-92ca-ce187ac45c1d"},{"GUID_OBJ":"f215c34f-9e11-44ae-a32a-3a20d87afca0","SJXMGUID":"bcc11c44-304b-4279-95b8-0ba1d0c860a2","XMMC":"石方工程","GCXMID":"b9bd8749-2e5c-4be3-9def-8b14fa5b0de2"},{"GUID_OBJ":"f215c34f-9e11-44ae-a32a-3a20d87afca0","SJXMGUID":"bcc11c44-304b-4279-95b8-0ba1d0c860a2","XMMC":"石方工程","GCXMID":"fcfe9c69-e393-437a-92ca-ce187ac45c1d"}]}]
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
        private List<XmmcBean> Xmmc;
        private List<FbgcBean> Fbgc;
        private List<FxgcBean> Fxgc;

        public List<XmmcBean> getXmmc() {
            return Xmmc;
        }

        public void setXmmc(List<XmmcBean> Xmmc) {
            this.Xmmc = Xmmc;
        }

        public List<FbgcBean> getFbgc() {
            return Fbgc;
        }

        public void setFbgc(List<FbgcBean> Fbgc) {
            this.Fbgc = Fbgc;
        }

        public List<FxgcBean> getFxgc() {
            return Fxgc;
        }

        public void setFxgc(List<FxgcBean> Fxgc) {
            this.Fxgc = Fxgc;
        }

        public static class XmmcBean {
            /**
             * GUID_OBJ : fcfe9c69-e393-437a-92ca-ce187ac45c1d
             * XMMC : G201改建工程
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

        public static class FbgcBean {
            /**
             * GUID_OBJ : bcc11c44-304b-4279-95b8-0ba1d0c860a2
             * XMMC : 路基土石方工程
             * GCXMID : b9bd8749-2e5c-4be3-9def-8b14fa5b0de2
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

        public static class FxgcBean {
            /**
             * GUID_OBJ : f714484a-c7b6-4059-82a5-75863f51c244
             * SJXMGUID : bcc11c44-304b-4279-95b8-0ba1d0c860a2
             * XMMC : 软土地基处理(沙垫层)
             * GCXMID : fcfe9c69-e393-437a-92ca-ce187ac45c1d
             */

            private String GUID_OBJ;
            private String SJXMGUID;
            private String XMMC;
            private String GCXMID;

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

            public String getGCXMID() {
                return GCXMID;
            }

            public void setGCXMID(String GCXMID) {
                this.GCXMID = GCXMID;
            }
        }
    }
}
