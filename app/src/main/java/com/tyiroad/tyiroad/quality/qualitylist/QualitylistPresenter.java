package com.tyiroad.tyiroad.quality.qualitylist;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.tyiroad.tyiroad.Bean.Basebean;
import com.tyiroad.tyiroad.Bean.LogListbean;
import com.tyiroad.tyiroad.MyApplication;
import com.tyiroad.tyiroad.mvp.BasePresenterImpl;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */
public class QualitylistPresenter extends BasePresenterImpl<QualitylistContract.View> implements QualitylistContract.Presenter {
    @Override
    public void getList(  String TIME, String DATAID, String ACTION) {
        OkHttpUtils.get()
                .tag(this)
                .addParams("gydwid", MyApplication.spUtils.getString("dqgydwid"))
                .addParams("time", TIME)
                .addParams("dataid", DATAID)
                .addParams("action", ACTION)
                .addParams("pagesize", "20")
                .url(MyApplication.BASEURLJLTH+"QueryZljcListByTime")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        QualityListBean videoVos2 = JSON.parseObject(response, QualityListBean.class);
                        if (videoVos2.getSTATE().equals("1")){
                            mView.getData(videoVos2.getDATA());
                        }else {
                            mView.onRequestError(videoVos2.getMSG());
                        }

                    }
                });

    }
    @Override
    public void Delete( String ID) {
        OkHttpUtils.get()
                .tag(this)
                .addParams("guid_obj", ID)
                .url(MyApplication.BASEURLJLTH+"RevokeZLJY")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Basebean videoVos2 = JSON.parseObject(response,Basebean.class);
                        if (videoVos2.getSTATE().equals("1")){
                            mView.Deletes(videoVos2);
                        }else {
                            mView.onRequestError(videoVos2.getMSG());
                        }

                    }
                });

    }
}
