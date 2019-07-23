package com.tyiroad.tyiroad.repair.lookrepair;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.tyiroad.tyiroad.Bean.Dingbean;
import com.tyiroad.tyiroad.Bean.Lookdiseasebean;
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

public class LookRepairPresenter extends BasePresenterImpl<LookRepairContract.View> implements LookRepairContract.Presenter{
    @Override
    public void testinfo(String ID ) {

        OkHttpUtils.get()
                .tag(this)
                .addParams("BHID",ID )
                .url(MyApplication.BASEURL+"QueryBhmx")
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        Lookdiseasebean videoVos2 = JSON.parseObject(response, Lookdiseasebean.class);
                        mView.getData(videoVos2);
                    }
                });

    }
    @Override
    public void getZH(String LXCODE,String jd ,String wd ,String gydwid ) {

        OkHttpUtils.get()
                .tag(this)
                .addParams("lxcode",LXCODE )
                .addParams("jd",jd )
                .addParams("wd",wd )
                .addParams("gydwid",gydwid )
                .url(MyApplication.BASEURL+"QueryZHByJwd")
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        Log.i("定位结果",response);
                        Dingbean videoVos2 = JSON.parseObject(response, Dingbean.class);
                        mView.getData2(videoVos2);
                    }
                });

    }
    @Override//creator,GZZLBH,gydwid,startTime,endTime,bhStr
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
                        mView.getData3(videoVos2);
                    }
                });

    }
}
