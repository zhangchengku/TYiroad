package com.tyiroad.tyiroad.documentation.list;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.tyiroad.tyiroad.Bean.Basebean;
import com.tyiroad.tyiroad.MyApplication;
import com.tyiroad.tyiroad.documentation.GcBean;
import com.tyiroad.tyiroad.mvp.BasePresenterImpl;
import com.tyiroad.tyiroad.quality.XmmcBean;
import com.tyiroad.tyiroad.quality.qualitylist.QualityListBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LIstPresenter extends BasePresenterImpl<LIstContract.View> implements LIstContract.Presenter{
    @Override
    public void getXmmcData() {
        OkHttpUtils.get()
                .tag(this)
                .addParams("dwid", MyApplication.spUtils.getString("dqgydwid"))
                .url(MyApplication.BASEURLJLTH+"InitZljyForGcxm")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        XmmcBean videoVos2 = JSON.parseObject(response, XmmcBean.class);
                        if (mView == null)
                            return;
                        if (videoVos2.getSTATE().equals("1")){
                            mView.getXmmcDatas(videoVos2.getDATA());
                        }else {
                            mView.onRequestError(videoVos2.getMSG());
                        }
                    }
                });

    }
    @Override
    public void getGcjdData() {
        OkHttpUtils.get()
                .tag(this)
                .url(MyApplication.BASEURLJLTH+"InitWDZL")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        GcBean videoVos2 = JSON.parseObject(response, GcBean.class);
                        if (mView == null)
                            return;
                        if (videoVos2.getSTATE().equals("1")){
                            mView.getGcjdDatas(videoVos2);
                        }else {
                            mView.onRequestError(videoVos2.getMSG());
                        }
                    }
                });

    }
    @Override
    public void getList( String XMID, String ZLLX,String DATAID, String ACTION) {
        OkHttpUtils.get()
                .tag(this)
                .addParams("dwid", MyApplication.spUtils.getString("dqgydwid"))
                .addParams("xmid", XMID)
                .addParams("zllx", ZLLX)
                .addParams("pageIndex", "0")
                .addParams("pageSize", "100")
                .url(MyApplication.BASEURLJLTH+"QueryWDZL")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        ListBean videoVos2 = JSON.parseObject(response, ListBean.class);
                        if (videoVos2.getSTATE().equals("1")){
                            mView.getData(videoVos2);
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
                .url(MyApplication.BASEURLJLTH+"RevokeWDZL")
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
