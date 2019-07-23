package com.tyiroad.tyiroad.handle.readyhandle;

import com.tyiroad.tyiroad.Bean.ReadyListbean;
import com.tyiroad.tyiroad.mvp.BasePresenter;
import com.tyiroad.tyiroad.mvp.BaseRequestView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ReadyhandleContract {
    interface View extends BaseRequestView {
        void getData(ReadyListbean videoVos2);

    }

    interface  Presenter extends BasePresenter<View> {//网络
        void getlist(String LXID, String GYDWID, String BHMCID, String DATAID, String ACTION, String PAGESIZE);

    }
}
