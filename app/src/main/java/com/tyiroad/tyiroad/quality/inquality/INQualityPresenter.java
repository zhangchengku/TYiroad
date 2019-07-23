package com.tyiroad.tyiroad.quality.inquality;

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

public class INQualityPresenter extends BasePresenterImpl<INQualityContract.View> implements INQualityContract.Presenter{
    @Override
    public void initData(String guid) {
        OkHttpUtils.get()
                .tag(this)
                .addParams("guid", guid)
                .url(MyApplication.BASEURLJLTH+"QueryZljcMx")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        INQualityBean videoVos2 = JSON.parseObject(response, INQualityBean.class);
                        if (mView == null)
                            return;
                        if (videoVos2.getSTATE().equals("1")){
                            mView.getData(videoVos2.getDATA());
                        }else {
                            mView.onRequestError(videoVos2.getMSG());
                        }
                    }
                });

    }
}
