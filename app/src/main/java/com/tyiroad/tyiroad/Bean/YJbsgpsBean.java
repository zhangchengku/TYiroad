package com.tyiroad.tyiroad.Bean;

import java.util.List;

/**
 * Created by 张成昆 on 2019-5-22.
 */

public class YJbsgpsBean {

    /**
     * MSG : SUCCESS
     * GPS : [{"QDZH":"218.913","LXID":"d00d5b88-626f-4f84-ab77-3bf45c090455"}]
     * STATE : 1
     */

    private String MSG;
    private String STATE;
    private List<GPSBean> GPS;

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

    public List<GPSBean> getGPS() {
        return GPS;
    }

    public void setGPS(List<GPSBean> GPS) {
        this.GPS = GPS;
    }

    public static class GPSBean {
        /**
         * QDZH : 218.913
         * LXID : d00d5b88-626f-4f84-ab77-3bf45c090455
         */

        private String QDZH;
        private String LXID;

        public String getQDZH() {
            return QDZH;
        }

        public void setQDZH(String QDZH) {
            this.QDZH = QDZH;
        }

        public String getLXID() {
            return LXID;
        }

        public void setLXID(String LXID) {
            this.LXID = LXID;
        }
    }
}
