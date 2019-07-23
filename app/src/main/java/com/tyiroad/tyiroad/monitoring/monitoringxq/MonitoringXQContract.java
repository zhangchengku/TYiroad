package com.tyiroad.tyiroad.monitoring.monitoringxq;

import android.content.Context;

import com.tyiroad.tyiroad.mvp.BasePresenter;
import com.tyiroad.tyiroad.mvp.BaseRequestView;
import com.tyiroad.tyiroad.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MonitoringXQContract {
    interface View extends BaseRequestView {
        void getData(List<MonitoringInitData.XMDATABean> videoVos2);
        void getData2(String videoVos2);
        void getLocationResult(int basebean );
    }

    interface  Presenter extends BasePresenter<View> {//网络
        void initData(String uid);
        void addData(String json);
        void getLocation(String xmid,String lon, String lat);
    }
}
