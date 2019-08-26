package com.tyiroad.tyiroad.documentation;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.tyiroad.tyiroad.Bean.Basebean;
import com.tyiroad.tyiroad.MyApplication;
import com.tyiroad.tyiroad.mvp.BasePresenterImpl;
import com.tyiroad.tyiroad.quality.LocationBean;
import com.tyiroad.tyiroad.quality.XmmcBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class DocumentationPresenter extends BasePresenterImpl<DocumentationContract.View> implements DocumentationContract.Presenter{
    @Override
    public void getXmmcData() {
        OkHttpUtils.get()
                .tag(this)
                .addParams("dwid", MyApplication.spUtils.getString("dqgydwid"))
                .url(MyApplication.BASEURLJLTH+"InitZljyForGcxm")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        XmmcBean videoVos2 = JSON.parseObject(response, XmmcBean.class);
                        if (mView == null)
                            return;
                        if (videoVos2.getSTATE().equals("1")){
                            mView.getXmmcDatas(videoVos2.getDATA());
                        }else {
                            mView.onRequestError(videoVos2.getMSG());
                        }
                    }
                });

    }
    @Override
    public void getGcjdData() {
        OkHttpUtils.get()
                .tag(this)
                .url(MyApplication.BASEURLJLTH+"InitWDZL")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        GcBean videoVos2 = JSON.parseObject(response, GcBean.class);
                        if (mView == null)
                            return;
                        if (videoVos2.getSTATE().equals("1")){
                            mView.getGcjdDatas(videoVos2);
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
                .url(MyApplication.BASEURLJLTH+"SaveWDZL")
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
}
