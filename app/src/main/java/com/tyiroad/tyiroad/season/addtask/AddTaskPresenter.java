package com.tyiroad.tyiroad.season.addtask;

import com.alibaba.fastjson.JSON;
import com.tyiroad.tyiroad.Bean.SUCCESBEAN;
import com.tyiroad.tyiroad.MyApplication;
import com.tyiroad.tyiroad.mvp.BasePresenterImpl;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class AddTaskPresenter extends BasePresenterImpl<AddTaskContract.View> implements AddTaskContract.Presenter{

    @Override
    public void  addTask(String json ) {

        OkHttpUtils.post()
                .tag(this)
                .addParams("json",json )
                .url(MyApplication.BASEURL+"SaveJjxyhLC")
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        SUCCESBEAN videoVos2 = JSON.parseObject(response, SUCCESBEAN.class);
                        mView.getData(videoVos2);
                    }
                });

    }
}
