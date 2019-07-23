package com.tyiroad.tyiroad.yjcz;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.tyiroad.tyiroad.Bean.Yjczbean;
import com.tyiroad.tyiroad.Bean.Yjczlistbean;
import com.tyiroad.tyiroad.MyApplication;
import com.tyiroad.tyiroad.mvp.BasePresenterImpl;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class YjczPresenter extends BasePresenterImpl<YjczContract.View> implements YjczContract.Presenter{
    @Override
    public void getHead(String gydwid) {
        OkHttpUtils.get()
                .tag(this)
                .addParams("gydwid", gydwid)
                .url(MyApplication.BASEURL + "SjclClick")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Yjczbean videoVos2 = JSON.parseObject(response, Yjczbean.class);
                        mView.getData(videoVos2);
                    }
                });

    }
    @Override
    public void getlist(String time,String gydwid,String zhlx,String state,String orderby,String dataid,String action,String pagesize) {
        OkHttpUtils.get()
                .tag(this)
                .addParams("time", time)
                .addParams("gydwid", gydwid)
                .addParams("zhlx", zhlx)
                .addParams("state", state)
                .addParams("orderby", orderby)
                .addParams("dataid", dataid)
                .addParams("action", action)
                .addParams("pagesize", pagesize)
                .url(MyApplication.BASEURL + "QueryYJList")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Yjczlistbean videoVos2 = JSON.parseObject(response, Yjczlistbean.class);
                        mView.getData2(videoVos2);
                    }
                });

    }
}
