package com.tyiroad.tyiroad.handle.readyhandlexq;

import com.tyiroad.tyiroad.Bean.ReadyhandxqleInfo;
import com.tyiroad.tyiroad.mvp.BasePresenter;
import com.tyiroad.tyiroad.mvp.BaseRequestView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ReadyhandlexqContract {
    interface View extends BaseRequestView {
        void getData(ReadyhandxqleInfo videoVos2);

    }

    interface  Presenter extends BasePresenter<View> {//网络
        void getxqdate(String BHID);

    }
}
