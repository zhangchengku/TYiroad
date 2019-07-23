package com.tyiroad.tyiroad.other.reported;

import com.tyiroad.tyiroad.Bean.Basebean;
import com.tyiroad.tyiroad.Bean.OtherDetailsBean;
import com.tyiroad.tyiroad.mvp.BasePresenter;
import com.tyiroad.tyiroad.mvp.BaseRequestView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ReportedContract {
    interface View extends BaseRequestView {
        void getData(OtherDetailsBean videoVos);
        void getResult(Basebean basebean);
    }

    interface  Presenter extends BasePresenter<View> {//网络
        void  getDetails(String dataid );
        void  addData(String json );
    }
}
