package com.tyiroad.tyiroad.check;

import com.tyiroad.tyiroad.Bean.SgRInfo;
import com.tyiroad.tyiroad.mvp.BasePresenter;
import com.tyiroad.tyiroad.mvp.BaseRequestView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class CheckContract {
    interface View extends BaseRequestView {
        void getData(SgRInfo videoVos);
    }

    interface  Presenter extends BasePresenter<View> {//网络
        void getsx(String GYDWID);
    }
}
