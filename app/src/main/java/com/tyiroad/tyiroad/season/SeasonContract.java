package com.tyiroad.tyiroad.season;

import com.tyiroad.tyiroad.Bean.Seasonlistbean;
import com.tyiroad.tyiroad.Bean.seasonbean;
import com.tyiroad.tyiroad.mvp.BasePresenter;
import com.tyiroad.tyiroad.mvp.BaseRequestView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class SeasonContract {
    interface View extends BaseRequestView {
        void getData(seasonbean videoVos2);
        void getData2(Seasonlistbean videoVos2);
    }

    interface  Presenter extends BasePresenter<View> {//网络
        void getbhlxdate();
        void getlist(String Lxid, String Dwid, String sglx, String dataid, String action);
    }
}
