package com.tyiroad.tyiroad.season.seasonxq;

import android.content.Context;

import com.tyiroad.tyiroad.Bean.Seasonxqbean;
import com.tyiroad.tyiroad.mvp.BasePresenter;
import com.tyiroad.tyiroad.mvp.BaseRequestView;
import com.tyiroad.tyiroad.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class SeasonxqContract {
    interface View extends BaseRequestView {
        void getData(Seasonxqbean videoVos2);


    }

    interface  Presenter extends BasePresenter<View> {//网络
        void getlist(String jjxyhid, String dataid, String action, String pagesize);
    }
}
