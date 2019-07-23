package com.tyiroad.tyiroad.season.seasonhandle;

import com.tyiroad.tyiroad.Bean.SUCCESBEAN;
import com.tyiroad.tyiroad.Bean.Seasonhandlebean;
import com.tyiroad.tyiroad.mvp.BasePresenter;
import com.tyiroad.tyiroad.mvp.BaseRequestView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class SeasonhandleContract {
    interface View extends BaseRequestView {
        void getData(Seasonhandlebean videoVos2);
    }

    interface  Presenter extends BasePresenter<View> {//网络
        void getdate(String jjxyhguid);
    }
}
