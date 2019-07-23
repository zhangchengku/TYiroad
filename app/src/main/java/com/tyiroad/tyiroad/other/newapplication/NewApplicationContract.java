package com.tyiroad.tyiroad.other.newapplication;

import android.content.Context;

import com.tyiroad.tyiroad.Bean.Basebean;
import com.tyiroad.tyiroad.Bean.OtherDetailsBean;
import com.tyiroad.tyiroad.mvp.BasePresenter;
import com.tyiroad.tyiroad.mvp.BaseRequestView;
import com.tyiroad.tyiroad.mvp.BaseView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class NewApplicationContract {
    interface View extends BaseRequestView {
        void getData(OtherDetailsBean videoVos);

        void getAddData(Basebean basebean);
    }

    interface Presenter extends BasePresenter<View> {//网络

        void getDetails(String dataid);

        void AddData(String guid, String state);
    }
}
