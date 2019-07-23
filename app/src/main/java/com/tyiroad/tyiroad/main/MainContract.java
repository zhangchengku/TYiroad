package com.tyiroad.tyiroad.main;

import com.tyiroad.tyiroad.Bean.LiXianDatabean;
import com.tyiroad.tyiroad.mvp.BasePresenter;
import com.tyiroad.tyiroad.mvp.BaseRequestView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MainContract {
    interface View extends BaseRequestView {

        void getupdatedb(LiXianDatabean liXianDatabean);


    }

    interface Presenter extends BasePresenter<View> {
        void updatedb(String userid, String gydwid);
    }
}

