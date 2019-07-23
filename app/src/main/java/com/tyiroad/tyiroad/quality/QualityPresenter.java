package com.tyiroad.tyiroad.quality;

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

public class QualityPresenter extends BasePresenterImpl<QualityContract.View> implements QualityContract.Presenter{
    @Override
    public void getData(String uid) {
        OkHttpUtils.get()
                .tag(this)
                .addParams("uid", uid)
                .url(MyApplication.BASEURLJLTH+"InitZljy")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        QualityBean videoVos2 = JSON.parseObject(response, QualityBean.class);
                        if (mView == null)
                            return;
                        if (videoVos2.getSTATE().equals("1")){
                            mView.getData(videoVos2.getDATA());
                        }else {
                            mView.onRequestError(videoVos2.getMSG());
                        }
                    }
                });

    }
    @Override
    public void upData(String json) {
        OkHttpUtils.post()
                .tag(this)
                .addParams("json", json)
                .url(MyApplication.BASEURLJLTH+"SaveZljy")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        QualityBean videoVos2 = JSON.parseObject(response, QualityBean.class);
                        if (mView == null)
                            return;
                        if (videoVos2.getSTATE().equals("1")){
                            mView.getResult(videoVos2);
                        }else {
                            mView.onRequestError(videoVos2.getMSG());
                        }
                    }
                });

    }
    @Override
    public void getLocation(String xmid,String lon, String lat) {
        OkHttpUtils.get()
                .tag(this)
                .addParams("xmid", xmid)
                .addParams("lon", lon)
                .addParams("lat", lat)
                .url(MyApplication.BASEURLJLTH+"QueryLocation")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LocationBean videoVos2 = JSON.parseObject(response, LocationBean.class);
                        if (mView == null)
                            return;
                        if (videoVos2.getSTATE().equals("1")){
                            mView.getLocationResult(videoVos2.getDATA());
                        }else {
                            mView.onRequestError(videoVos2.getMESSAGE());
                        }
                    }
                });

    }
    @Override
    public void getListData(String uid,String gcid, String fxid) {
        OkHttpUtils.get()
                .tag(this)
                .addParams("uid", uid)
                .addParams("gcid", gcid)
                .addParams("fxid", fxid)
                .url(MyApplication.BASEURLJLTH+"InitJcxm")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        ListDataBean videoVos2 = JSON.parseObject(response, ListDataBean.class);
                        if (mView == null)
                            return;
                        if (videoVos2.getSTATE().equals("1")){
                            mView.getListData(videoVos2.getDATA());
                        }else {
                            mView.onRequestError(videoVos2.getMSG());
                        }
                    }
                });

    }
}
