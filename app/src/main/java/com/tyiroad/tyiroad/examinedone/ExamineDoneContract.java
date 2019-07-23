package com.tyiroad.tyiroad.examinedone;

import com.tyiroad.tyiroad.Bean.ToExamineDataInfo;
import com.tyiroad.tyiroad.mvp.BasePresenter;
import com.tyiroad.tyiroad.mvp.BaseRequestView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ExamineDoneContract {
    interface View extends BaseRequestView {
        void getData(ToExamineDataInfo videoVos2);

    }

    interface  Presenter extends BasePresenter<View> {//网络
        void addDisease(String startTime, String lxid, String bhlx, String gydwid, String listtype);

    }
}
