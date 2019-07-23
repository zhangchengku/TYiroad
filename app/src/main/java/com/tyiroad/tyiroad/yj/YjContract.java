package com.tyiroad.tyiroad.yj;

import android.content.Context;

import com.tyiroad.tyiroad.Bean.YJlistBean;
import com.tyiroad.tyiroad.mvp.BasePresenter;
import com.tyiroad.tyiroad.mvp.BaseRequestView;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class YjContract {
    interface View extends BaseRequestView {
        void getData2(YJlistBean videoVos2);
    }

    interface  Presenter extends BasePresenter<View> {//网络
        void getlist(String yjdj, String yjlx);
    }
}
