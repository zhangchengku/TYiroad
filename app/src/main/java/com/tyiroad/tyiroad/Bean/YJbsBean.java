package com.tyiroad.tyiroad.Bean;


import java.util.List;

/**
 * Created by 张成昆 on 2019-5-20.
 */

public class YJbsBean {

    /**
     * ZHLXDT : [{"VALUE":"1","TEXT":"公路水浸"},{"VALUE":"2","TEXT":"滑坡"},{"VALUE":"3","TEXT":"路树、标识牌、路灯杆折断或倒地"},{"VALUE":"4","TEXT":"路旁山体滑坡或落石"},{"VALUE":"5","TEXT":"车辆掉落杂物占道"}]
     * MSG : SUCCESS
     * STATE : 1
     * YJDJDT : [{"VALUE":"1","TEXT":"蓝色预警"},{"VALUE":"2","TEXT":"黄色预警"},{"VALUE":"3","TEXT":"橙色预警"},{"VALUE":"4","TEXT":"红色预警"}]
     * YJNRDT : [{"VALUE":"1","TEXT":"台风"},{"VALUE":"2","TEXT":"暴雨"}]
     * JTQKDT : [{"VALUE":"1","TEXT":"畅通"},{"VALUE":"2","TEXT":"缓慢通行"},{"VALUE":"3","TEXT":"半封闭"},{"VALUE":"4","TEXT":"中断交通"}]
     */

    private String MSG;
    private String STATE;
    private List<ZHLXDTBean> ZHLXDT;
    private List<YJDJDTBean> YJDJDT;
    private List<YJNRDTBean> YJNRDT;
    private List<JTQKDTBean> JTQKDT;

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

    public List<YJDJDTBean> getYJDJDT() {
        return YJDJDT;
    }

    public void setYJDJDT(List<YJDJDTBean> YJDJDT) {
        this.YJDJDT = YJDJDT;
    }

    public List<YJNRDTBean> getYJNRDT() {
        return YJNRDT;
    }

    public void setYJNRDT(List<YJNRDTBean> YJNRDT) {
        this.YJNRDT = YJNRDT;
    }

    public List<JTQKDTBean> getJTQKDT() {
        return JTQKDT;
    }

    public void setJTQKDT(List<JTQKDTBean> JTQKDT) {
        this.JTQKDT = JTQKDT;
    }

    public static class ZHLXDTBean {
        /**
         * VALUE : 1
         * TEXT : 公路水浸
         */

        private String VALUE;
        private String TEXT;

        public String getVALUE() {
            return VALUE;
        }

        public void setVALUE(String VALUE) {
            this.VALUE = VALUE;
        }

        public String getTEXT() {
            return TEXT;
        }

        public void setTEXT(String TEXT) {
            this.TEXT = TEXT;
        }
    }

    public static class YJDJDTBean {
        /**
         * VALUE : 1
         * TEXT : 蓝色预警
         */

        private String VALUE;
        private String TEXT;

        public String getVALUE() {
            return VALUE;
        }

        public void setVALUE(String VALUE) {
            this.VALUE = VALUE;
        }

        public String getTEXT() {
            return TEXT;
        }

        public void setTEXT(String TEXT) {
            this.TEXT = TEXT;
        }
    }

    public static class YJNRDTBean {
        /**
         * VALUE : 1
         * TEXT : 台风
         */

        private String VALUE;
        private String TEXT;

        public String getVALUE() {
            return VALUE;
        }

        public void setVALUE(String VALUE) {
            this.VALUE = VALUE;
        }

        public String getTEXT() {
            return TEXT;
        }

        public void setTEXT(String TEXT) {
            this.TEXT = TEXT;
        }
    }

    public static class JTQKDTBean {
        /**
         * VALUE : 1
         * TEXT : 畅通
         */

        private String VALUE;
        private String TEXT;

        public String getVALUE() {
            return VALUE;
        }

        public void setVALUE(String VALUE) {
            this.VALUE = VALUE;
        }

        public String getTEXT() {
            return TEXT;
        }

        public void setTEXT(String TEXT) {
            this.TEXT = TEXT;
        }
    }
}
