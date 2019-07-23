package com.tyiroad.tyiroad.yjbs;

import android.content.Context;

import com.tyiroad.tyiroad.Bean.Basebean;
import com.tyiroad.tyiroad.Bean.YJbsBean;
import com.tyiroad.tyiroad.Bean.YJbsgpsBean;
import com.tyiroad.tyiroad.mvp.BasePresenter;
import com.tyiroad.tyiroad.mvp.BaseRequestView;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class YjbsContract {
    interface View extends BaseRequestView {
        void getData2(YJbsBean videoVos2);
        void getData(YJbsgpsBean videoVos2);
        void getData3(Basebean videoVos2);
    }

    interface  Presenter extends BasePresenter<View> {//网络
        void getdata();
        void getZH(String jd,String wd);
        void addData(String json);
    }
}
