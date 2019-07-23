package com.tyiroad.tyiroad.repair.lookrepairrely;

import com.alibaba.fastjson.JSON;
import com.tyiroad.tyiroad.Bean.Lookdiseasebean;
import com.tyiroad.tyiroad.MyApplication;
import com.tyiroad.tyiroad.mvp.BasePresenterImpl;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LookRepairRelyPresenter extends BasePresenterImpl<LookRepairRelyContract.View> implements LookRepairRelyContract.Presenter{
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
}
