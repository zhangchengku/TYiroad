package com.tyiroad.tyiroad.repair;

import com.tyiroad.tyiroad.Bean.Basebean;
import com.tyiroad.tyiroad.Bean.repairRKbean;
import com.tyiroad.tyiroad.mvp.BasePresenter;
import com.tyiroad.tyiroad.mvp.BaseRequestView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class RepairContract {
    interface View extends BaseRequestView {
        void getData(repairRKbean videoVos2);
        void getData2(repairRKbean videoVos2);

    }

    interface  Presenter extends BasePresenter<View> {//网络
        void addDisease(String gydwid);
        void paifaDisease(String jsons);

    }
}
