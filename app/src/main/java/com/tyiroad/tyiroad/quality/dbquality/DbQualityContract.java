package com.tyiroad.tyiroad.quality.dbquality;

import android.content.Context;

import com.tyiroad.tyiroad.Bean.Basebean;
import com.tyiroad.tyiroad.mvp.BasePresenter;
import com.tyiroad.tyiroad.mvp.BaseRequestView;
import com.tyiroad.tyiroad.mvp.BaseView;
import com.tyiroad.tyiroad.quality.ListDataBean;
import com.tyiroad.tyiroad.quality.QualityBean;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class DbQualityContract {
    interface View extends BaseRequestView {
        void getData(List<QualityBean.DATABean> videoVos2);
        void getResult(QualityBean basebean);
        void getListData(List<ListDataBean.DATABean> ss);
    }

    interface  Presenter extends BasePresenter<View> {//网络
        void getData(String uid);
        void upData(String json);
        void getListData(String uid,String gcid, String fxid);
    }
}

