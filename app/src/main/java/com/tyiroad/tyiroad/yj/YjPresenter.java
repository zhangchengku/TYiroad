package com.tyiroad.tyiroad.yj;

import android.content.Context;

import com.alibaba.fastjson.JSON;

import com.tyiroad.tyiroad.Bean.YJlistBean;
import com.tyiroad.tyiroad.MyApplication;
import com.tyiroad.tyiroad.mvp.BasePresenterImpl;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class YjPresenter extends BasePresenterImpl<YjContract.View> implements YjContract.Presenter{
    @Override
    public void getlist(String yjdj,String yjlx) {
        OkHttpUtils.get()
                .tag(this)
                .addParams("yjdj", yjdj)
                .addParams("yjlx", yjlx)
                .url(MyApplication.BASEURL + "QueryYjyjList")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        YJlistBean videoVos2 = JSON.parseObject(response, YJlistBean.class);
                        mView.getData2(videoVos2);
                    }
                });

    }
}
