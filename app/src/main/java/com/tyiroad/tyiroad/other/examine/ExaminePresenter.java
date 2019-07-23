package com.tyiroad.tyiroad.other.examine;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.tyiroad.tyiroad.Bean.OtherDetailsBean;
import com.tyiroad.tyiroad.MyApplication;
import com.tyiroad.tyiroad.mvp.BasePresenterImpl;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ExaminePresenter extends BasePresenterImpl<ExamineContract.View> implements ExamineContract.Presenter{
    @Override
    public void  getDetails(String dataid ) {

        OkHttpUtils.get()
                .tag(this)
                .addParams("dataid",dataid )
                .url(MyApplication.BASEURL+"QueryThQtListMx")
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        OtherDetailsBean videoVo= JSON.parseObject(response, OtherDetailsBean.class);
                        mView.getData(videoVo);
                    }
                });

    }
}
