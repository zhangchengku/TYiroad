package com.tyiroad.tyiroad.monitoring;

import android.content.Context;

import com.tyiroad.tyiroad.Bean.LogListbean;
import com.tyiroad.tyiroad.mvp.BasePresenter;
import com.tyiroad.tyiroad.mvp.BaseRequestView;
import com.tyiroad.tyiroad.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MonitoringContract {
    interface View extends BaseRequestView {
        void getData(List<MonitoringListBean.DATABean> videoVos2);

    }

    interface  Presenter extends BasePresenter<View> {
        void getList(  String ETIME, String DATAID, String ACTION);
    }
}
