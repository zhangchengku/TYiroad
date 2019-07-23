package com.tyiroad.tyiroad.log.lookdblog;

import android.content.Context;

import com.tyiroad.tyiroad.Bean.Basebean;
import com.tyiroad.tyiroad.mvp.BasePresenter;
import com.tyiroad.tyiroad.mvp.BaseRequestView;
import com.tyiroad.tyiroad.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LookDBLogContract {
    interface View extends BaseRequestView {
        void getData(Basebean videoVos2);

    }

    interface  Presenter extends BasePresenter<View> {//网络
        void add(String JSONS);

    }
}
