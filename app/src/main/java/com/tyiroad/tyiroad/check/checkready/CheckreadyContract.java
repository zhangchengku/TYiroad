package com.tyiroad.tyiroad.check.checkready;

import com.tyiroad.tyiroad.Bean.checkwaibean;
import com.tyiroad.tyiroad.mvp.BasePresenter;
import com.tyiroad.tyiroad.mvp.BaseRequestView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class CheckreadyContract {
    interface View extends BaseRequestView {
        void getData(checkwaibean videoVos2);


    }

    interface  Presenter extends BasePresenter<View> {//网络
        void getlist(String LXID, String GYDWID, String BHID, String STIME, String ETIME, String DATAID, String ACTION, String PAGESIZE);



    }
}
