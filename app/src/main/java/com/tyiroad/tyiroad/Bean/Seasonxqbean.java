package com.tyiroad.tyiroad.Bean;

import java.util.List;

/**
 * Created by 张成昆 on 2019-6-4.
 */

public class Seasonxqbean {

    /**
     * MSG : SUCCESS
     * STATE : 1
     * JJXYHMX : [{"RNINDEX":1,"RN":1,"GUID_OBJ":"8e66b9ad-2a3e-4963-9e6d-3863fef25eef","LCZH":"12-13","YHXM":"标牌刷新","DW":"个","GCL":12,"QT":null,"BZ":null},{"RNINDEX":2,"RN":2,"GUID_OBJ":"af93b8de-a2d0-449a-ae4d-9b1c987f30c1","LCZH":"13-14","YHXM":"标牌刷新","DW":"个","GCL":12,"QT":null,"BZ":null},{"RNINDEX":3,"RN":3,"GUID_OBJ":"83dbe1fc-acc0-4dc8-ba7d-03b9cb984d65","LCZH":"14-15","YHXM":"标牌刷新","DW":"个","GCL":12,"QT":null,"BZ":null},{"RNINDEX":4,"RN":4,"GUID_OBJ":"13a365e7-9130-48ee-92ee-412c66710244","LCZH":"15-16","YHXM":"标牌刷新","DW":"个","GCL":12,"QT":null,"BZ":null},{"RNINDEX":5,"RN":5,"GUID_OBJ":"22fe6fdf-51c0-4d98-981e-45d931d3681c","LCZH":"16-17","YHXM":"标牌刷新","DW":"个","GCL":12,"QT":null,"BZ":null},{"RNINDEX":6,"RN":6,"GUID_OBJ":"c440f4fb-97f0-4db5-adb7-dcb843712aca","LCZH":"17-18","YHXM":"标牌刷新","DW":"个","GCL":12,"QT":null,"BZ":null},{"RNINDEX":7,"RN":7,"GUID_OBJ":"10511a85-26fc-4916-a345-074bb00af8c4","LCZH":"18-19","YHXM":"标牌刷新","DW":"个","GCL":12,"QT":null,"BZ":null},{"RNINDEX":8,"RN":8,"GUID_OBJ":"00720c67-d308-4d44-b046-03eacb8f6d74","LCZH":"19-20","YHXM":"标牌刷新","DW":"个","GCL":12,"QT":null,"BZ":null},{"RNINDEX":9,"RN":9,"GUID_OBJ":"606e0769-beed-4812-a633-97019bd91f77","LCZH":"20-21","YHXM":"标牌刷新","DW":"个","GCL":12,"QT":null,"BZ":null},{"RNINDEX":10,"RN":10,"GUID_OBJ":"b7dce33e-90fd-4d37-9602-2374608bfda6","LCZH":"21-22","YHXM":"标牌刷新","DW":"个","GCL":12,"QT":null,"BZ":null}]
     * ISALSO : 0
     */

    private String MSG;
    private String STATE;
    private String ISALSO;
    private List<JJXYHMXBean> JJXYHMX;

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

    public String getISALSO() {
        return ISALSO;
    }

    public void setISALSO(String ISALSO) {
        this.ISALSO = ISALSO;
    }

    public List<JJXYHMXBean> getJJXYHMX() {
        return JJXYHMX;
    }

    public void setJJXYHMX(List<JJXYHMXBean> JJXYHMX) {
        this.JJXYHMX = JJXYHMX;
    }

    public static class JJXYHMXBean {
        /**
         * RNINDEX : 1
         * RN : 1
         * GUID_OBJ : 8e66b9ad-2a3e-4963-9e6d-3863fef25eef
         * LCZH : 12-13
         * YHXM : 标牌刷新
         * DW : 个
         * GCL : 12
         * QT : null
         * BZ : null
         */

        private int RNINDEX;
        private int RN;
        private String GUID_OBJ;
        private String LCZH;
        private String YHXM;
        private String DW;
        private int GCL;
        private String QT;
        private String BZ;

        public int getRNINDEX() {
            return RNINDEX;
        }

        public void setRNINDEX(int RNINDEX) {
            this.RNINDEX = RNINDEX;
        }

        public int getRN() {
            return RN;
        }

        public void setRN(int RN) {
            this.RN = RN;
        }

        public String getGUID_OBJ() {
            return GUID_OBJ;
        }

        public void setGUID_OBJ(String GUID_OBJ) {
            this.GUID_OBJ = GUID_OBJ;
        }

        public String getLCZH() {
            return LCZH;
        }

        public void setLCZH(String LCZH) {
            this.LCZH = LCZH;
        }

        public String getYHXM() {
            return YHXM;
        }

        public void setYHXM(String YHXM) {
            this.YHXM = YHXM;
        }

        public String getDW() {
            return DW;
        }

        public void setDW(String DW) {
            this.DW = DW;
        }

        public int getGCL() {
            return GCL;
        }

        public void setGCL(int GCL) {
            this.GCL = GCL;
        }

        public String getQT() {
            return QT;
        }

        public void setQT(String QT) {
            this.QT = QT;
        }

        public String getBZ() {
            return BZ;
        }

        public void setBZ(String BZ) {
            this.BZ = BZ;
        }
    }
}
