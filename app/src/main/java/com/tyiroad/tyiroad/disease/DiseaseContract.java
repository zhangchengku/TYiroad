package com.tyiroad.tyiroad.disease;

import com.tyiroad.tyiroad.Bean.DiseaseListbean;
import com.tyiroad.tyiroad.mvp.BasePresenter;
import com.tyiroad.tyiroad.mvp.BaseRequestView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class DiseaseContract {
    interface View extends BaseRequestView {
        void getData(DiseaseListbean videoVos2);

    }

    interface  Presenter extends BasePresenter<View> {//网络
        void testinfo(String GYDWID, String STIME, String ETIME, String DATAID, String ACTION, String PAGESIZE);

    }
}

