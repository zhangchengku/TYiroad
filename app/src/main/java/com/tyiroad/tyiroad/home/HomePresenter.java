package com.tyiroad.tyiroad.home;

import com.alibaba.fastjson.JSON;
import com.tyiroad.tyiroad.Bean.SgRInfo;
import com.tyiroad.tyiroad.MyApplication;
import com.tyiroad.tyiroad.mvp.BasePresenterImpl;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class HomePresenter extends BasePresenterImpl<HomeContract.View> implements HomeContract.Presenter{
    @Override
    public void getData() {
        OkHttpUtils.get()
                .tag(this)
                .addParams("lxid","" )
                .addParams("bhid","")
                .addParams("gydwid", MyApplication.app.spUtils.getString("dqgydwid", "") )
                .url(MyApplication.BASEURL+"QueryYhwx")
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(okhttp3.Call call, Exception e, int id) {
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        SgRInfo videoVos = JSON.parseObject(response, SgRInfo.class);
                        if (mView == null)
                            return;
                        if (videoVos.getSTATE().equals("1")){
                            mView.getData(videoVos);
                        }else {
                            mView.onRequestError(videoVos.getMSG());
                        }

                    }
                });
    }
}
