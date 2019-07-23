package com.tyiroad.tyiroad.quality;

import android.content.Context;

import com.tyiroad.tyiroad.Bean.Basebean;
import com.tyiroad.tyiroad.mvp.BasePresenter;
import com.tyiroad.tyiroad.mvp.BaseRequestView;
import com.tyiroad.tyiroad.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class QualityContract {
    interface View extends BaseRequestView {
        void getData(List<QualityBean.DATABean> videoVos2);
        void getResult(QualityBean basebean );
        void getLocationResult(int basebean );
        void getListData(List<ListDataBean.DATABean> ss);
    }

    interface  Presenter extends BasePresenter<View> {//网络
        void getData(String uid);
        void upData(String json);
        void getLocation(String xmid,String lon, String lat);
        void getListData(String uid,String gcid, String fxid);
    }
}
