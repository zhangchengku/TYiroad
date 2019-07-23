package com.tyiroad.tyiroad.yjcz;

import android.content.Context;

import com.tyiroad.tyiroad.Bean.Yjczbean;
import com.tyiroad.tyiroad.Bean.Yjczlistbean;
import com.tyiroad.tyiroad.mvp.BasePresenter;
import com.tyiroad.tyiroad.mvp.BaseRequestView;
import com.tyiroad.tyiroad.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class YjczContract {
    interface View extends BaseRequestView {
        void getData(Yjczbean videoVos2);
        void getData2(Yjczlistbean videoVos2);
    }

    interface  Presenter extends BasePresenter<View> {//网络
        void getHead(String gydwid);
        void getlist(String time,String gydwid,String zhlx,String state,String orderby,String dataid,String action,String pagesize);
    }
}
