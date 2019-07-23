package com.tyiroad.tyiroad.monitoring.dbmonitoring;

import android.content.Context;

import com.tyiroad.tyiroad.monitoring.monitoringxq.MonitoringInitData;
import com.tyiroad.tyiroad.mvp.BasePresenter;
import com.tyiroad.tyiroad.mvp.BaseRequestView;
import com.tyiroad.tyiroad.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class DBMonitoringContract {
    interface View extends BaseRequestView {
        void getData(List<MonitoringInitData.XMDATABean> videoVos2);
        void getData2(String videoVos2);
    }

    interface  Presenter extends BasePresenter<View> {//网络
        void initData(String uid);
        void addData(String json);

    }
}
