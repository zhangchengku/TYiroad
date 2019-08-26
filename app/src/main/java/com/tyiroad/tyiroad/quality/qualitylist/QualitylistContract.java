package com.tyiroad.tyiroad.quality.qualitylist;

import android.content.Context;

import com.tyiroad.tyiroad.Bean.Basebean;
import com.tyiroad.tyiroad.Bean.LogListbean;
import com.tyiroad.tyiroad.mvp.BasePresenter;
import com.tyiroad.tyiroad.mvp.BaseRequestView;
import com.tyiroad.tyiroad.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class QualitylistContract {
    interface View extends BaseRequestView {
        void getData(List<QualityListBean.DATABean> videoVos2);
        void Deletes(Basebean videoVos2);
    }

    interface  Presenter extends BasePresenter<View> {
        void getList(  String ETIME, String DATAID, String ACTION);
        void Delete( String ID);
    }
}
