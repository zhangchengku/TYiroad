package com.tyiroad.tyiroad.documentation;

import android.content.Context;

import com.tyiroad.tyiroad.Bean.Basebean;
import com.tyiroad.tyiroad.mvp.BasePresenter;
import com.tyiroad.tyiroad.mvp.BaseRequestView;
import com.tyiroad.tyiroad.mvp.BaseView;
import com.tyiroad.tyiroad.quality.XmmcBean;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class DocumentationContract {
    interface View extends BaseRequestView {
        void getResult(Basebean videoVos2);
        void getXmmcDatas(List<XmmcBean.DATABean> DATA);
        void getGcjdDatas(GcBean GcBean);
        void getLocationResult(int basebean );
    }

    interface  Presenter extends BasePresenter<View> {
        void upData(String json);
        void getXmmcData();
        void getGcjdData();
        void getLocation(String xmid,String lon, String lat);
    }
}
