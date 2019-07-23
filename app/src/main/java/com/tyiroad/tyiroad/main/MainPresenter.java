package com.tyiroad.tyiroad.main;

import com.alibaba.fastjson.JSON;
import com.tyiroad.tyiroad.Bean.LiXianDatabean;
import com.tyiroad.tyiroad.MyApplication;
import com.tyiroad.tyiroad.mvp.BasePresenterImpl;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MainPresenter extends BasePresenterImpl<MainContract.View> implements MainContract.Presenter{
    @Override
    public void updatedb(String userid,String gydwid) {
        String url = MyApplication.BASEURL+"QueryLxbData";
        OkHttpUtils
                .get()
                .addParams("userid", userid)
                .addParams("gydwid", gydwid)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LiXianDatabean liXianDatabean = JSON.parseObject(response, LiXianDatabean.class);
                        if (mView == null)
                            return;
                        mView.getupdatedb(liXianDatabean);
                    }
                });
    }
}
