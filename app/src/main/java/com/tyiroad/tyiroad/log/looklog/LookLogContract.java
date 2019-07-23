package com.tyiroad.tyiroad.log.looklog;

import android.content.Context;

import com.tyiroad.tyiroad.Bean.LookIntbean;
import com.tyiroad.tyiroad.mvp.BasePresenter;
import com.tyiroad.tyiroad.mvp.BaseRequestView;
import com.tyiroad.tyiroad.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LookLogContract {
    interface View extends BaseRequestView {
        void getData(LookIntbean videoVos2);

    }

    interface  Presenter extends BasePresenter<View> {//网络
        void testinfo(String RZID);

    }
}
