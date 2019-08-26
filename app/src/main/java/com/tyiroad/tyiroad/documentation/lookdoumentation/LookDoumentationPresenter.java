package com.tyiroad.tyiroad.documentation.lookdoumentation;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.tyiroad.tyiroad.MyApplication;
import com.tyiroad.tyiroad.mvp.BasePresenterImpl;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LookDoumentationPresenter extends BasePresenterImpl<LookDoumentationContract.View> implements LookDoumentationContract.Presenter{
    @Override
    public void getData(String id) {
        OkHttpUtils.get()
                .tag(this)
                .addParams("guid_obj", id)
                .url(MyApplication.BASEURLJLTH+"LoadWDZL")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LookBean videoVos2 = JSON.parseObject(response, LookBean.class);
                        if (mView == null)
                            return;
                        if (videoVos2.getSTATE().equals("1")){
                            mView.getDatas(videoVos2.getDATA());
                        }else {
                            mView.onRequestError(videoVos2.getMSG());
                        }
                    }
                });

    }
}
