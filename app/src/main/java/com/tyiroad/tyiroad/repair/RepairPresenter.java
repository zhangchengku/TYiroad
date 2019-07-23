package com.tyiroad.tyiroad.repair;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.tyiroad.tyiroad.Bean.Basebean;
import com.tyiroad.tyiroad.Bean.repairRKbean;
import com.tyiroad.tyiroad.MyApplication;
import com.tyiroad.tyiroad.mvp.BasePresenterImpl;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class RepairPresenter extends BasePresenterImpl<RepairContract.View> implements RepairContract.Presenter{
    @Override
    public void  addDisease(String gydwid) {

        OkHttpUtils.get()
                .tag(this)
                .addParams("gydwid",gydwid )
                .url(MyApplication.BASEURL+"QueryRwpf")
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        repairRKbean videoVos2 = JSON.parseObject(response, repairRKbean.class);
                        mView.getData(videoVos2);
                    }
                });

    }
    @Override//
    public void  paifaDisease(String JSONs) {
        Log.e("paifaDisease: ",JSONs);
        OkHttpUtils.post()
                .tag(this)
                .addParams("json",JSONs )
                .url(MyApplication.BASEURL+"MobileRwpf")
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        repairRKbean videoVos2 = JSON.parseObject(response, repairRKbean.class);
                        mView.getData2(videoVos2);
                    }
                });

    }


}
