package com.tyiroad.tyiroad.monitoring.inmonitoring;

import android.content.Context;

import com.tyiroad.tyiroad.mvp.BasePresenter;
import com.tyiroad.tyiroad.mvp.BaseRequestView;
import com.tyiroad.tyiroad.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class INMonitoringContract {
    interface View extends BaseRequestView {
        void getData(INMmnitoringBean videoVos2);
    }

    interface  Presenter extends BasePresenter<View> {//网络
        void initData(String uid);
    }
}
