package com.tyiroad.tyiroad.handle.inwaihandle;

import com.tyiroad.tyiroad.Bean.Dingbean;
import com.tyiroad.tyiroad.Bean.waiHandleItembean;
import com.tyiroad.tyiroad.mvp.BasePresenter;
import com.tyiroad.tyiroad.mvp.BaseRequestView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class INwaihandleContract {
    interface View extends BaseRequestView {
        void getData(waiHandleItembean videoVos2);
        void getData2(Dingbean videoVos2);
    }

    interface  Presenter extends BasePresenter<View> {//网络
        void add(String JSONS);
        void getZH(String LXCODE, String jd, String wd, String gydwid);
    }
}
