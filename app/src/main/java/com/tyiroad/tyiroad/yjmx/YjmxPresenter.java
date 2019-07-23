package com.tyiroad.tyiroad.yjmx;

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
import com.tyiroad.tyiroad.Bean.yjmxbean;
public class YjmxPresenter extends BasePresenterImpl<YjmxContract.View> implements YjmxContract.Presenter{
    @Override
    public void getMX(String yjguid) {
        OkHttpUtils.get()
                .tag(this)
                .addParams("yjguid", yjguid)
                .url(MyApplication.BASEURL + "QueryYjyjmx")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        yjmxbean videoVos2 = JSON.parseObject(response, yjmxbean.class);
                        mView.getData2(videoVos2);
                    }
                });

    }
}
