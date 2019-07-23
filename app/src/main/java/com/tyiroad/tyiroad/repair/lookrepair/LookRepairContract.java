package com.tyiroad.tyiroad.repair.lookrepair;

import com.tyiroad.tyiroad.Bean.Dingbean;
import com.tyiroad.tyiroad.Bean.Lookdiseasebean;
import com.tyiroad.tyiroad.Bean.repairRKbean;
import com.tyiroad.tyiroad.mvp.BasePresenter;
import com.tyiroad.tyiroad.mvp.BaseRequestView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LookRepairContract {
    interface View extends BaseRequestView {
        void getData(Lookdiseasebean videoVos2);
        void getData2(Dingbean videoVos2);
        void getData3(repairRKbean videoVos2);
    }

    interface  Presenter extends BasePresenter<View> {//网络
        void testinfo(String id);
        void getZH(String LXCODE, String jd, String wd, String gydwid);
        void paifaDisease(String jsons);

    }
}
