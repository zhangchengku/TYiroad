package com.tyiroad.tyiroad.handle.bdwaihandle;

import com.tyiroad.tyiroad.Bean.waiHandleItembean;
import com.tyiroad.tyiroad.mvp.BasePresenter;
import com.tyiroad.tyiroad.mvp.BaseRequestView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class BDwaihandleContract {
    interface View extends BaseRequestView {
        void getData(waiHandleItembean videoVos2);

    }

    interface  Presenter extends BasePresenter<View> {//网络
        void add(String JSONS);

    }
}
