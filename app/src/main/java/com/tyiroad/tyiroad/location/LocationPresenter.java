package com.tyiroad.tyiroad.location;

import com.alibaba.fastjson.JSON;
import com.tyiroad.tyiroad.Bean.Basebean;
import com.tyiroad.tyiroad.MyApplication;
import com.tyiroad.tyiroad.mvp.BasePresenterImpl;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LocationPresenter extends BasePresenterImpl<LocationContract.View> implements LocationContract.Presenter{
    @Override
    public void testinfo(String code,String lon,String lat) {

        OkHttpUtils.get()
                .tag(this)
                .addParams("code", code)
                .addParams("lon", lon)
                .addParams("lat", lat)
                .url(MyApplication.BASEURL+"UpdateQLGPS")
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        Basebean videoVos2 = JSON.parseObject(response, Basebean.class);
                        mView.getData(videoVos2);
                    }
                });

    }
}
