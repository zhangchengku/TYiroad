package com.tyiroad.tyiroad.documentation.list;

import android.content.Context;

import com.tyiroad.tyiroad.Bean.Basebean;
import com.tyiroad.tyiroad.documentation.GcBean;
import com.tyiroad.tyiroad.mvp.BasePresenter;
import com.tyiroad.tyiroad.mvp.BaseRequestView;
import com.tyiroad.tyiroad.mvp.BaseView;
import com.tyiroad.tyiroad.quality.XmmcBean;
import com.tyiroad.tyiroad.quality.qualitylist.QualityListBean;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LIstContract {
    interface View extends BaseRequestView {
        void getXmmcDatas(List<XmmcBean.DATABean> DATA);
        void getGcjdDatas(GcBean GcBean);
        void getData(ListBean videoVos2);
        void Deletes( Basebean videoVos2 );
    }

    interface  Presenter extends BasePresenter<View> {
        void getXmmcData();
        void getGcjdData();
        void getList( String XMID, String ZLLX,String DATAID, String ACTION);
        void Delete( String ID);
    }
}
