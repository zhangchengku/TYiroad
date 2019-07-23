package com.tyiroad.tyiroad.other;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.tyiroad.tyiroad.Bean.Basebean;
import com.tyiroad.tyiroad.Bean.OtherListbean;
import com.tyiroad.tyiroad.Bean.Otherheaderbean;
import com.tyiroad.tyiroad.Bean.Seasonlistbean;
import com.tyiroad.tyiroad.MyApplication;
import com.tyiroad.tyiroad.mvp.BasePresenterImpl;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class OtherPresenter extends BasePresenterImpl<OtherContract.View> implements OtherContract.Presenter{
    @Override
    public void getlist(String gydwid,String lxcode,String  dataid  ,String action,String zt) {
        OkHttpUtils.get()
                .tag(this)
                .addParams("gydwid", gydwid)
                .addParams("lxcode", lxcode)
                .addParams("zt", zt)
                .addParams("qdzh", "")
                .addParams("zdzh", "")
                .addParams("qssj", "")
                .addParams("jssj", "")
                .addParams("dataid", dataid)
                .addParams("action", action)
                .addParams("pagesize", "20")
                .url(MyApplication.BASEURL + "QueryThQtList")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        OtherListbean videoVos2 = JSON.parseObject(response, OtherListbean.class);
                        mView.getData(videoVos2);
                    }
                });

    }
    @Override
    public void getheader(String gydwid) {
        OkHttpUtils.get()
                .tag(this)
                .addParams("gydwid", gydwid)
                .url(MyApplication.BASEURL + "MobileQt")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Otherheaderbean videoVos2 = JSON.parseObject(response, Otherheaderbean.class);
                        mView.getheaderData(videoVos2);
                    }
                });

    }
    @Override
    public void AddData(String guid,String state) {
        OkHttpUtils.get()
                .tag(this)
                .addParams("guid", guid)
                .addParams("state", state)
                .url(MyApplication.BASEURL + "UpdateYwQt")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Basebean videoVos2 = JSON.parseObject(response, Basebean.class);
                        mView.getAddData(videoVos2);
                    }
                });

    }
}
