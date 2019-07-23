package com.tyiroad.tyiroad.handle.waihandle;

import com.tyiroad.tyiroad.Bean.waiHandleItembean;
import com.tyiroad.tyiroad.mvp.BasePresenter;
import com.tyiroad.tyiroad.mvp.BaseRequestView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class WaihandleContract {
    interface View extends BaseRequestView {
        void getData(waiHandleItembean videoVos2);

    }

    interface  Presenter extends BasePresenter<View> {//网络
        void getlist(String LXID, String GYDWID, String BHMCID, String DATAID, String ACTION, String PAGESIZE);

    }
}
