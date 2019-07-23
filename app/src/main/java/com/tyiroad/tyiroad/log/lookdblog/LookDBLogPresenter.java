package com.tyiroad.tyiroad.log.lookdblog;

import android.content.Context;

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

public class LookDBLogPresenter extends BasePresenterImpl<LookDBLogContract.View> implements LookDBLogContract.Presenter{
    @Override
    public void add(String JSONS) {
        OkHttpUtils.post()
                .tag(this)
                .addParams("JSON", JSONS)
                .url(MyApplication.BASEURL+"MobileXcrz")
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
