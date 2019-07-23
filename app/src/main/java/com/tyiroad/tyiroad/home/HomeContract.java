package com.tyiroad.tyiroad.home;

import com.tyiroad.tyiroad.Bean.SgRInfo;
import com.tyiroad.tyiroad.mvp.BasePresenter;
import com.tyiroad.tyiroad.mvp.BaseRequestView;
import com.tyiroad.tyiroad.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class HomeContract {
    interface View extends BaseRequestView {
        void getData(SgRInfo videoVos2);
    }

    interface  Presenter extends BasePresenter<View> {//网络
        void getData();
    }
}
