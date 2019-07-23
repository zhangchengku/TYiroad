package com.tyiroad.tyiroad.other;

import android.content.Context;

import com.tyiroad.tyiroad.Bean.Basebean;
import com.tyiroad.tyiroad.Bean.OtherListbean;
import com.tyiroad.tyiroad.Bean.Otherheaderbean;
import com.tyiroad.tyiroad.mvp.BasePresenter;
import com.tyiroad.tyiroad.mvp.BaseRequestView;
import com.tyiroad.tyiroad.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class OtherContract {
    interface View extends BaseRequestView {
        void getData(OtherListbean videoVos2);
        void getheaderData(Otherheaderbean videoVos2);
        void getAddData(Basebean basebean);
    }

    interface  Presenter extends BasePresenter<View> {//网络
        void getlist(String gydwid,String lxcode,String  dataid  ,String action,String zt);
        void getheader(String gydwid);
        void AddData(String guid,String state);
    }
}
