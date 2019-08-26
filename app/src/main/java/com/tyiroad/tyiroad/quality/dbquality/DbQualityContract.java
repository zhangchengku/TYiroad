package com.tyiroad.tyiroad.quality.dbquality;

import android.content.Context;

import com.tyiroad.tyiroad.Bean.Basebean;
import com.tyiroad.tyiroad.mvp.BasePresenter;
import com.tyiroad.tyiroad.mvp.BaseRequestView;
import com.tyiroad.tyiroad.mvp.BaseView;
import com.tyiroad.tyiroad.quality.FbgcBean;
import com.tyiroad.tyiroad.quality.FxgcBean;
import com.tyiroad.tyiroad.quality.ListDataBean;
import com.tyiroad.tyiroad.quality.QualityBean;
import com.tyiroad.tyiroad.quality.XmmcBean;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class DbQualityContract {
    interface View extends BaseRequestView {
        void getResult(QualityBean basebean);
        void getListData(List<ListDataBean.DATABean> ss);
        void getXmmcDatas(List<XmmcBean.DATABean>   videoVos2);
        void getFbgcDatas(List<FbgcBean.DATABean> videoVos2);
        void getFbgcDatas2(List<FbgcBean.DATABean> videoVos2);
        void getFxgcDatas(List<FxgcBean.DATABean> videoVos2);
        void getFxgcDatas2(List<FxgcBean.DATABean> videoVos2);
    }

    interface  Presenter extends BasePresenter<View> {//网络
        void upData(String json);
        void getListData(String uid,String gcid, String fxid);
        void getXmmcData();
        void getFbgcData(String uid);
        void getFbgcData2(String uid);
        void getFxgcData(String uid,String fbid);
        void getFxgcData2(String uid,String fbid);
    }
}

