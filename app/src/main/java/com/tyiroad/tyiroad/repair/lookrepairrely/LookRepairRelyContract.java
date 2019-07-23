package com.tyiroad.tyiroad.repair.lookrepairrely;

import com.tyiroad.tyiroad.Bean.Lookdiseasebean;
import com.tyiroad.tyiroad.mvp.BasePresenter;
import com.tyiroad.tyiroad.mvp.BaseRequestView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LookRepairRelyContract {
    interface View extends BaseRequestView {
        void getData(Lookdiseasebean videoVos2);

    }

    interface  Presenter extends BasePresenter<View> {//网络
        void testinfo(String id);


    }
}
