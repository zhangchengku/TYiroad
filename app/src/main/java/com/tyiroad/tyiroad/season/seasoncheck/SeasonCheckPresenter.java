package com.tyiroad.tyiroad.season.seasoncheck;

import com.alibaba.fastjson.JSON;
import com.tyiroad.tyiroad.Bean.SUCCESBEAN;
import com.tyiroad.tyiroad.Bean.Seasonhandlebean;
import com.tyiroad.tyiroad.MyApplication;
import com.tyiroad.tyiroad.mvp.BasePresenterImpl;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class SeasonCheckPresenter extends BasePresenterImpl<SeasonCheckContract.View> implements SeasonCheckContract.Presenter{
    @Override
    public void getdate(String jjxyhguid) {
        OkHttpUtils.get()
                .tag(this)
                .addParams("jjxyhguid", jjxyhguid)
                .url(MyApplication.BASEURL + "JjxyhLCMX")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Seasonhandlebean videoVos2 = JSON.parseObject(response, Seasonhandlebean.class);
                        mView.getData(videoVos2);
                    }
                });
    }
    @Override
    public void  addTask(String guid,String state ,String ysyj) {

        OkHttpUtils.get()
                .tag(this)
                .addParams("guid",guid )
                .addParams("state",state )
                .addParams("ysyj",ysyj )
                .url(MyApplication.BASEURL+"UpdateJjxyhYsLC")
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        SUCCESBEAN videoVos2 = JSON.parseObject(response, SUCCESBEAN.class);
                        mView.getData2(videoVos2);
                    }
                });

    }
}
