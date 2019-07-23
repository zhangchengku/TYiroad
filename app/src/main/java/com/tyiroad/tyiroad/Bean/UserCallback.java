package com.tyiroad.tyiroad.Bean;

import android.util.Log;

import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by 张成昆 on 2019-3-8.
 */

public abstract class UserCallback extends Callback<String>
{
    @Override
    public String parseNetworkResponse(Response response, int id) throws Exception {
        String string = response.body().string();
        Log.e("OkHttpUtils", string );
        return string;
    }
}
