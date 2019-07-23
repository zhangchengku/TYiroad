package com.tyiroad.tyiroad.monitoring.inmonitoring;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.tyiroad.tyiroad.MyApplication;
import com.tyiroad.tyiroad.mvp.BasePresenterImpl;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class INMonitoringPresenter extends BasePresenterImpl<INMonitoringContract.View> implements INMonitoringContract.Presenter{
    @Override
    public void initData(String guid) {
        OkHttpUtils.get()
                .tag(this)
                .addParams("guid", guid)
                .url(MyApplication.BASEURLJLTH+"QueryGcjkMx")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        INMmnitoringBean videoVos2 = JSON.parseObject(response, INMmnitoringBean.class);
                        if (mView == null)
                            return;
                        if (videoVos2.getSTATE().equals("1")){
                            mView.getData(videoVos2);
                        }else {
                            mView.onRequestError(videoVos2.getMSG());
                        }
                    }
                });

    }
}
