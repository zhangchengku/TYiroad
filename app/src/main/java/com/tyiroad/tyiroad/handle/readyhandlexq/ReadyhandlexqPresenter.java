package com.tyiroad.tyiroad.handle.readyhandlexq;

import com.alibaba.fastjson.JSON;
import com.tyiroad.tyiroad.Bean.ReadyhandxqleInfo;
import com.tyiroad.tyiroad.MyApplication;
import com.tyiroad.tyiroad.mvp.BasePresenterImpl;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ReadyhandlexqPresenter extends BasePresenterImpl<ReadyhandlexqContract.View> implements ReadyhandlexqContract.Presenter{
    @Override
    public void getxqdate(String BHID) {
        OkHttpUtils.get()
                .tag(this)
                .addParams("BHID", BHID)
                .url(MyApplication.BASEURL+"QueryBhmxByWx")
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        if (!"".equals(id+"")&&id==0){
                            mView.onRequestError(id+"");
                        }
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        ReadyhandxqleInfo videoVos2 = JSON.parseObject(response, ReadyhandxqleInfo.class);
                        mView.getData(videoVos2);
                    }
                });

    }
}
