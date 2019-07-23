package com.tyiroad.tyiroad.yjbs;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;

import com.tyiroad.tyiroad.Bean.Basebean;
import com.tyiroad.tyiroad.Bean.YJbsBean;
import com.tyiroad.tyiroad.Bean.YJbsgpsBean;
import com.tyiroad.tyiroad.MyApplication;
import com.tyiroad.tyiroad.mvp.BasePresenterImpl;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class YjbsPresenter extends BasePresenterImpl<YjbsContract.View> implements YjbsContract.Presenter{
    @Override
    public void getdata() {
        OkHttpUtils.get()
                .tag(this)
                .url(MyApplication.BASEURL + "SjbsClick")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        YJbsBean videoVos2 = JSON.parseObject(response, YJbsBean.class);
                        mView.getData2(videoVos2);
                    }
                });

    }
    @Override
    public void getZH(String jd,String wd) {
        OkHttpUtils.get()
                .tag(this)
                .addParams("jd",jd )
                .addParams("wd",wd )
                .url(MyApplication.BASEURL + "QueryZHLXByJwd")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        YJbsgpsBean videoVos2 = JSON.parseObject(response, YJbsgpsBean.class);
                        mView.getData(videoVos2);
                    }
                });

    }
    public void addData(String json) {
        Log.e( "测试西: ",json );
        OkHttpUtils.post()
                .tag(this)
                .addParams("json",json )
                .url(MyApplication.BASEURL + "MobileSjbs")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Basebean videoVos2 = JSON.parseObject(response, Basebean.class);
                        mView.getData3(videoVos2);
                    }
                });

    }
}
