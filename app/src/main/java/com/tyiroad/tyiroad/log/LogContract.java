package com.tyiroad.tyiroad.log;


import com.tyiroad.tyiroad.Bean.LogListbean;
import com.tyiroad.tyiroad.mvp.BasePresenter;
import com.tyiroad.tyiroad.mvp.BaseRequestView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LogContract {
    interface View extends BaseRequestView {
        void getData(LogListbean videoVos2);

    }

    interface  Presenter extends BasePresenter<View> {//网络
        void testinfo(String GYDWID, String STIME, String ETIME, String DATAID, String ACTION, String PAGESIZE);

    }
}
