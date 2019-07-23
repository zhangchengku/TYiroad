package com.tyiroad.tyiroad.yjmx;

import android.content.Context;

import com.tyiroad.tyiroad.mvp.BasePresenter;
import com.tyiroad.tyiroad.mvp.BaseRequestView;
import com.tyiroad.tyiroad.Bean.yjmxbean;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class YjmxContract {
    interface View extends BaseRequestView {
        void getData2(yjmxbean videoVos2);
    }

    interface  Presenter extends BasePresenter<View> {//网络
        void getMX(String yjguid);
    }
}
