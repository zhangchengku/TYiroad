package com.tyiroad.tyiroad.check.checkxq;

import com.tyiroad.tyiroad.Bean.CheckxqBean;
import com.tyiroad.tyiroad.mvp.BasePresenter;
import com.tyiroad.tyiroad.mvp.BaseRequestView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class CheckxqContract {
    interface View extends BaseRequestView {
        void getData(CheckxqBean videoVos);
        void getData2(CheckxqBean videoVos);
    }

    interface  Presenter extends BasePresenter<View> {//网络
        void getxqdate(String BHID);
        void add(String json, String type);
    }
}
