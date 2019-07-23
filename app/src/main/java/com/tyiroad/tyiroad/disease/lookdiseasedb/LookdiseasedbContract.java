package com.tyiroad.tyiroad.disease.lookdiseasedb;

import com.tyiroad.tyiroad.Bean.Dingbean;
import com.tyiroad.tyiroad.Bean.adddiseasebean;
import com.tyiroad.tyiroad.mvp.BasePresenter;
import com.tyiroad.tyiroad.mvp.BaseRequestView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LookdiseasedbContract {
    interface View extends BaseRequestView {
        void getData(adddiseasebean videoVos2);
        void getData2(Dingbean videoVos2);
    }

    interface  Presenter extends BasePresenter<View> {//网络
        void addDisease(String json);
        void getZH(String LXCODE, String jd, String wd, String gydwid);
    }
}
