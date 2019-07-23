package com.tyiroad.tyiroad.season.addtask;

import com.tyiroad.tyiroad.Bean.SUCCESBEAN;
import com.tyiroad.tyiroad.mvp.BasePresenter;
import com.tyiroad.tyiroad.mvp.BaseRequestView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class AddTaskContract {
    interface View extends BaseRequestView {
        void getData(SUCCESBEAN videoVos2);

    }

    interface  Presenter extends BasePresenter<View> {//网络
        void addTask(String json);

    }
}
