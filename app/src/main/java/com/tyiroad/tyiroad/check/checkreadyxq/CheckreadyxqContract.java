package com.tyiroad.tyiroad.check.checkreadyxq;

import com.tyiroad.tyiroad.Bean.CheckxqBean;
import com.tyiroad.tyiroad.mvp.BasePresenter;
import com.tyiroad.tyiroad.mvp.BaseRequestView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class CheckreadyxqContract {
    interface View extends BaseRequestView {
        void getData(CheckxqBean videoVos);
    }

    interface  Presenter extends BasePresenter<View> {//网络
        void getxqdate(String BHID);

    }
}
