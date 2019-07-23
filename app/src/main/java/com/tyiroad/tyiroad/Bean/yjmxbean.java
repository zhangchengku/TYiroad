package com.tyiroad.tyiroad.Bean;
import java.util.List;

/**
 * Created by 张成昆 on 2019-5-20.
 */

public class yjmxbean {

    /**
     * STATE : 1
     * YJYJDATA : [{"GUID_OBJ":"e4a7e837-20b1-426e-af6b-24e0c7224731","YJNAME":"CXYA-001","YJJB":"2","FBSJ":"2017-07-18T10:10:44","YJNR":"工作要求。扫雪除冰应急处置要求：\n1．降雪时应边降边清扫，停雪后2小时内全部完成清扫冰雪任务。\n2．清除的冰雪要及时清运到指定的场所；暂时不能清运的，应当整齐堆放在不妨碍交通的地方。含有融雪剂的冰雪，一律不得堆放在行道树树穴和绿地内。\n3．城市道路红线宽度30米以上、城市风景区以及广场等公共场所，为清扫冰雪的重点地段。\n4．任何人不得向道路两侧和公共场所的积雪上倾倒垃圾。\n5．需将冰雪运出的区域，中雪应在1日内运完，大雪应在3日内运完，特大暴雪应在10日内运完，运出的冰雪要在指定地点倾倒。其它地区，在不影响行人通行的情况下，可将冰雪堆放在人行道靠近路边石处或绿化区域，做到整齐堆放。\n6．清除冰雪，以无积冰，无残雪，露出地面为标准。\n7．滨德高速公路德州管理处除做好高速公路引道、匝道出入口的扫雪除冰工作外，还应负责滞留在滨德高速公路上的车辆驾驶员的御寒及饮食供应。","FBJG":"通化市公路管理处"}]
     */

    private String STATE;
    private List<YJYJDATABean> YJYJDATA;

    public String getSTATE() {
        return STATE;
    }

    public void setSTATE(String STATE) {
        this.STATE = STATE;
    }

    public List<YJYJDATABean> getYJYJDATA() {
        return YJYJDATA;
    }

    public void setYJYJDATA(List<YJYJDATABean> YJYJDATA) {
        this.YJYJDATA = YJYJDATA;
    }

    public static class YJYJDATABean {
        /**
         * GUID_OBJ : e4a7e837-20b1-426e-af6b-24e0c7224731
         * YJNAME : CXYA-001
         * YJJB : 2
         * FBSJ : 2017-07-18T10:10:44
         * YJNR : 工作要求。扫雪除冰应急处置要求：
         1．降雪时应边降边清扫，停雪后2小时内全部完成清扫冰雪任务。
         2．清除的冰雪要及时清运到指定的场所；暂时不能清运的，应当整齐堆放在不妨碍交通的地方。含有融雪剂的冰雪，一律不得堆放在行道树树穴和绿地内。
         3．城市道路红线宽度30米以上、城市风景区以及广场等公共场所，为清扫冰雪的重点地段。
         4．任何人不得向道路两侧和公共场所的积雪上倾倒垃圾。
         5．需将冰雪运出的区域，中雪应在1日内运完，大雪应在3日内运完，特大暴雪应在10日内运完，运出的冰雪要在指定地点倾倒。其它地区，在不影响行人通行的情况下，可将冰雪堆放在人行道靠近路边石处或绿化区域，做到整齐堆放。
         6．清除冰雪，以无积冰，无残雪，露出地面为标准。
         7．滨德高速公路德州管理处除做好高速公路引道、匝道出入口的扫雪除冰工作外，还应负责滞留在滨德高速公路上的车辆驾驶员的御寒及饮食供应。
         * FBJG : 通化市公路管理处
         */

        private String GUID_OBJ;
        private String YJNAME;
        private String YJJB;
        private String FBSJ;
        private String YJNR;
        private String FBJG;

        public String getGUID_OBJ() {
            return GUID_OBJ;
        }

        public void setGUID_OBJ(String GUID_OBJ) {
            this.GUID_OBJ = GUID_OBJ;
        }

        public String getYJNAME() {
            return YJNAME;
        }

        public void setYJNAME(String YJNAME) {
            this.YJNAME = YJNAME;
        }

        public String getYJJB() {
            return YJJB;
        }

        public void setYJJB(String YJJB) {
            this.YJJB = YJJB;
        }

        public String getFBSJ() {
            return FBSJ;
        }

        public void setFBSJ(String FBSJ) {
            this.FBSJ = FBSJ;
        }

        public String getYJNR() {
            return YJNR;
        }

        public void setYJNR(String YJNR) {
            this.YJNR = YJNR;
        }

        public String getFBJG() {
            return FBJG;
        }

        public void setFBJG(String FBJG) {
            this.FBJG = FBJG;
        }
    }
}
