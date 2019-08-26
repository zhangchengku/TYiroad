package com.tyiroad.tyiroad.documentation.lookdoumentation;

import android.content.Context;

import com.tyiroad.tyiroad.mvp.BasePresenter;
import com.tyiroad.tyiroad.mvp.BaseRequestView;
import com.tyiroad.tyiroad.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LookDoumentationContract {
    interface View extends BaseRequestView {
        void getDatas(LookBean.DATABean DATABean);
    }

    interface  Presenter extends BasePresenter<View> {
        void getData(String id);
    }
}
