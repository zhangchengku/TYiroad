package com.tyiroad.tyiroad.monitoring.dbmonitoring;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.tyiroad.tyiroad.Bean.Basebean;
import com.tyiroad.tyiroad.MyApplication;
import com.tyiroad.tyiroad.monitoring.monitoringxq.MonitoringInitData;
import com.tyiroad.tyiroad.mvp.BasePresenterImpl;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class DBMonitoringPresenter extends BasePresenterImpl<DBMonitoringContract.View> implements DBMonitoringContract.Presenter{
    @Override
    public void initData(String gydwid) {
        OkHttpUtils.get()
                .tag(this)
                .addParams("gydwid", gydwid)
                .url(MyApplication.BASEURLJLTH+"QueryGcjkRk")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        MonitoringInitData videoVos2 = JSON.parseObject(response, MonitoringInitData.class);
                        if (mView == null)
                            return;
                        if (videoVos2.getSTATE().equals("1")){
                            mView.getData(videoVos2.getXMDATA());
                        }else {
                            mView.onRequestError(videoVos2.getMSG());
                        }
                    }
                });

    }
    @Override
    public void addData(String json) {
        OkHttpUtils.post()
                .tag(this)
                .addParams("json", json)
                .url(MyApplication.BASEURLJLTH+"SaveGCJK")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Basebean videoVos2 = JSON.parseObject(response, Basebean.class);
                        if (mView == null)
                            return;
                        if (videoVos2.getSTATE().equals("1")){
                            mView.getData2(videoVos2.getSTATE());
                        }else {
                            mView.onRequestError(videoVos2.getMSG());
                        }
                    }
                });

    }
}
