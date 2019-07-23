package com.tyiroad.tyiroad.other.noexamine;

import android.content.Context;

import com.tyiroad.tyiroad.Bean.Basebean;
import com.tyiroad.tyiroad.Bean.OtherDetailsBean;
import com.tyiroad.tyiroad.mvp.BasePresenter;
import com.tyiroad.tyiroad.mvp.BaseRequestView;
import com.tyiroad.tyiroad.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class NoExamineContract {
    interface View extends BaseRequestView {
        void getData(OtherDetailsBean videoVos);
        void getData2(Basebean videoVos2 );
    }

    interface  Presenter extends BasePresenter<View> {//网络
        void  getDetails(String dataid );
        void  addOtherXq(String json );
    }
}
