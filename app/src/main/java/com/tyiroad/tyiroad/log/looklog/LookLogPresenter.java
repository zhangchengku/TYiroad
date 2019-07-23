package com.tyiroad.tyiroad.log.looklog;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.tyiroad.tyiroad.Bean.LookIntbean;
import com.tyiroad.tyiroad.MyApplication;
import com.tyiroad.tyiroad.mvp.BasePresenterImpl;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LookLogPresenter extends BasePresenterImpl<LookLogContract.View> implements LookLogContract.Presenter{
    @Override
    public void testinfo(String RZID) {

        OkHttpUtils.get()
                .tag(this)
                .addParams("rzid", RZID)
                .url(MyApplication.BASEURL+"QueryRzMX")
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        LookIntbean videoVos2 = JSON.parseObject(response, LookIntbean.class);
                        mView.getData(videoVos2);
                    }
                });

    }
}
