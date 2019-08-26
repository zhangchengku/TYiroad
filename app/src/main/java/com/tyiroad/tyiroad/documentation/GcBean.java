package com.tyiroad.tyiroad.documentation;

import java.util.List;

public class GcBean {

    /**
     * STATE : 1
     * DATA : [{"Gcjd":[{"ID":"1","TEXT":"项目前期"},{"ID":"2","TEXT":"项目实施"},{"ID":"3","TEXT":"交竣工验收"}],"Zllx":[{"ID":"1","TEXT":"质检资料"},{"ID":"2","TEXT":"计量资料"},{"ID":"3","TEXT":"结算资料"},{"ID":"4","TEXT":"招投标资料"},{"ID":"5","TEXT":"施工资料"},{"ID":"6","TEXT":"验收资料"},{"ID":"7","TEXT":"审计资料"},{"ID":"8","TEXT":"设计资料"},{"ID":"9","TEXT":"其他资料"}]}]
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
        private List<GcjdBean> Gcjd;
        private List<ZllxBean> Zllx;

        public List<GcjdBean> getGcjd() {
            return Gcjd;
        }

        public void setGcjd(List<GcjdBean> Gcjd) {
            this.Gcjd = Gcjd;
        }

        public List<ZllxBean> getZllx() {
            return Zllx;
        }

        public void setZllx(List<ZllxBean> Zllx) {
            this.Zllx = Zllx;
        }

        public static class GcjdBean {
            /**
             * ID : 1
             * TEXT : 项目前期
             */

            private String ID;
            private String TEXT;

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
            }

            public String getTEXT() {
                return TEXT;
            }

            public void setTEXT(String TEXT) {
                this.TEXT = TEXT;
            }
        }

        public static class ZllxBean {
            /**
             * ID : 1
             * TEXT : 质检资料
             */

            private String ID;
            private String TEXT;

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
            }

            public String getTEXT() {
                return TEXT;
            }

            public void setTEXT(String TEXT) {
                this.TEXT = TEXT;
            }
        }
    }
}
