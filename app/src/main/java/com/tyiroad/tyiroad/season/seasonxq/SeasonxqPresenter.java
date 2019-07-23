package com.tyiroad.tyiroad.season.seasonxq;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.tyiroad.tyiroad.Bean.Seasonxqbean;
import com.tyiroad.tyiroad.Bean.checkwaibean;
import com.tyiroad.tyiroad.MyApplication;
import com.tyiroad.tyiroad.mvp.BasePresenterImpl;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;

import okhttp3.Call;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class SeasonxqPresenter extends BasePresenterImpl<SeasonxqContract.View> implements SeasonxqContract.Presenter{
    @Override
    public void getlist(String jjxyhid, String dataid, String action, String pagesize) {
        OkHttpUtils.get()
                .tag(this)
                .addParams("jjxyhid", jjxyhid)
                .addParams("dataid",dataid)
                .addParams("action",action)
                .addParams("pagesize",pagesize)
                .url(MyApplication.BASEURL+"QueryJjxyhMxData")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Seasonxqbean videoVos2 = JSON.parseObject(response, Seasonxqbean.class);
                        if(videoVos2!=null&&mView!=null){
                            mView.getData(videoVos2);
                        }
                    }
                });

    }
}
