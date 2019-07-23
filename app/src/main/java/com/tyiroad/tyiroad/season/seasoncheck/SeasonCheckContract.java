package com.tyiroad.tyiroad.season.seasoncheck;

import com.tyiroad.tyiroad.Bean.SUCCESBEAN;
import com.tyiroad.tyiroad.Bean.Seasonhandlebean;
import com.tyiroad.tyiroad.mvp.BasePresenter;
import com.tyiroad.tyiroad.mvp.BaseRequestView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class SeasonCheckContract {
    interface View extends BaseRequestView {
        void getData(Seasonhandlebean videoVos2);
        void getData2(SUCCESBEAN videoVos2);
    }

    interface  Presenter extends BasePresenter<View> {//网络
        void getdate(String jjxyhguid);
        void addTask(String guid, String state ,String ysyj);
    }
}
