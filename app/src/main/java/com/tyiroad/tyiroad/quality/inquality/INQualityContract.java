package com.tyiroad.tyiroad.quality.inquality;

import android.content.Context;

import com.tyiroad.tyiroad.mvp.BasePresenter;
import com.tyiroad.tyiroad.mvp.BaseRequestView;
import com.tyiroad.tyiroad.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class INQualityContract {
    interface View extends BaseRequestView {
        void getData(INQualityBean.DATABean videoVos2);
    }

    interface  Presenter extends BasePresenter<View> {//网络
        void initData(String uid);
    }
}
