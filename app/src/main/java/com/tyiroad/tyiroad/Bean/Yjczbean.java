package com.tyiroad.tyiroad.Bean;

import java.util.List;

/**
 * Created by 张成昆 on 2019-5-24.
 */

public class Yjczbean {


    /**
     * ZHLXDT : [{"TEXT":"公路水浸","VALUE":"1"}]
     * MSG : SUCCESS
     * CDRYDT : [{"NAME":"张成坤","ZW":"员工","GYDW":"通化市郊公路管理段","LXDH":"18888888888","VALUE":"a527e581-b0d6-4440-afa7-54ad2709c901"}]
     * STATE : 1
     * STATEDT : [{"TEXT":"新事件","VALUE":"1"}]
     * CDCLDT : []
     */

    private String MSG;
    private String STATE;
    private List<ZHLXDTBean> ZHLXDT;
    private List<CDRYDTBean> CDRYDT;
    private List<STATEDTBean> STATEDT;
    private List<?> CDCLDT;

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

    public List<ZHLXDTBean> getZHLXDT() {
        return ZHLXDT;
    }

    public void setZHLXDT(List<ZHLXDTBean> ZHLXDT) {
        this.ZHLXDT = ZHLXDT;
    }

    public List<CDRYDTBean> getCDRYDT() {
        return CDRYDT;
    }

    public void setCDRYDT(List<CDRYDTBean> CDRYDT) {
        this.CDRYDT = CDRYDT;
    }

    public List<STATEDTBean> getSTATEDT() {
        return STATEDT;
    }

    public void setSTATEDT(List<STATEDTBean> STATEDT) {
        this.STATEDT = STATEDT;
    }

    public List<?> getCDCLDT() {
        return CDCLDT;
    }

    public void setCDCLDT(List<?> CDCLDT) {
        this.CDCLDT = CDCLDT;
    }

    public static class ZHLXDTBean {
        /**
         * TEXT : 公路水浸
         * VALUE : 1
         */

        private String TEXT;
        private String VALUE;

        public String getTEXT() {
            return TEXT;
        }

        public void setTEXT(String TEXT) {
            this.TEXT = TEXT;
        }

        public String getVALUE() {
            return VALUE;
        }

        public void setVALUE(String VALUE) {
            this.VALUE = VALUE;
        }
    }

    public static class CDRYDTBean {
        /**
         * NAME : 张成坤
         * ZW : 员工
         * GYDW : 通化市郊公路管理段
         * LXDH : 18888888888
         * VALUE : a527e581-b0d6-4440-afa7-54ad2709c901
         */

        private String NAME;
        private String ZW;
        private String GYDW;
        private String LXDH;
        private String VALUE;

        public String getNAME() {
            return NAME;
        }

        public void setNAME(String NAME) {
            this.NAME = NAME;
        }

        public String getZW() {
            return ZW;
        }

        public void setZW(String ZW) {
            this.ZW = ZW;
        }

        public String getGYDW() {
            return GYDW;
        }

        public void setGYDW(String GYDW) {
            this.GYDW = GYDW;
        }

        public String getLXDH() {
            return LXDH;
        }

        public void setLXDH(String LXDH) {
            this.LXDH = LXDH;
        }

        public String getVALUE() {
            return VALUE;
        }

        public void setVALUE(String VALUE) {
            this.VALUE = VALUE;
        }
    }

    public static class STATEDTBean {
        /**
         * TEXT : 新事件
         * VALUE : 1
         */

        private String TEXT;
        private String VALUE;

        public String getTEXT() {
            return TEXT;
        }

        public void setTEXT(String TEXT) {
            this.TEXT = TEXT;
        }

        public String getVALUE() {
            return VALUE;
        }

        public void setVALUE(String VALUE) {
            this.VALUE = VALUE;
        }
    }
}
