package com.tyiroad.tyiroad.location;


import com.tyiroad.tyiroad.Bean.Basebean;
import com.tyiroad.tyiroad.mvp.BasePresenter;
import com.tyiroad.tyiroad.mvp.BaseRequestView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LocationContract {
    interface View extends BaseRequestView {
        void getData(Basebean videoVos2);
    }

    interface  Presenter extends BasePresenter<View> {//网络
        void testinfo(String code, String lon, String lat);

    }
}
