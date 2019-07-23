package com.tyiroad.tyiroad.monitoring;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.tyiroad.tyiroad.Bean.LogListbean;
import com.tyiroad.tyiroad.MyApplication;
import com.tyiroad.tyiroad.mvp.BasePresenterImpl;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MonitoringPresenter extends BasePresenterImpl<MonitoringContract.View> implements MonitoringContract.Presenter{
    @Override
    public void getList(  String TIME, String DATAID, String ACTION) {
        OkHttpUtils.get()
                .tag(this)
                .addParams("time", TIME)
                .addParams("gydwid", MyApplication.spUtils.getString("dqgydwid"))
                .addParams("dataid", DATAID)
                .addParams("action", ACTION)
                .addParams("pagesize", "20")
                .url(MyApplication.BASEURLJLTH+"QueryGcjkListByTime")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        MonitoringListBean videoVos2 = JSON.parseObject(response, MonitoringListBean.class);
                        if (videoVos2.getSTATE().equals("1")){
                            mView.getData(videoVos2.getDATA());
                        }else {
                            mView.onRequestError(videoVos2.getMSG());
                        }
                    }
                });

    }
}
