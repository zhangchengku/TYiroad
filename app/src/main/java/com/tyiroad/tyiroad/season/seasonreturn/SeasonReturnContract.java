package com.tyiroad.tyiroad.season.seasonreturn;

import android.content.Context;

import com.tyiroad.tyiroad.Bean.Seasonhandlebean;
import com.tyiroad.tyiroad.mvp.BasePresenter;
import com.tyiroad.tyiroad.mvp.BaseRequestView;
import com.tyiroad.tyiroad.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class SeasonReturnContract {
    interface View extends BaseRequestView {
        void getData(Seasonhandlebean videoVos2);
    }

    interface  Presenter extends BasePresenter<View> {//网络
        void getdate(String jjxyhguid);
    }
}
