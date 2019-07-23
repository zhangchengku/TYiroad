package com.tyiroad.tyiroad;

import android.app.Application;
import android.view.Gravity;
import android.widget.Toast;

import com.bumptech.glide.request.RequestOptions;
import com.tyiroad.tyiroad.db.dbhelper.CuringDao;
import com.tyiroad.tyiroad.db.dbhelper.CuringDaoImpl;
import com.tyiroad.tyiroad.utils.SPUtils;
import com.tyiroad.tyiroad.utils.Utils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by 张成昆 on 2019-3-1.
 */

public class MyApplication extends Application {
    public static SPUtils spUtils;
    public static String BASEURL="http://106.37.229.146:4148/CurrencyRCYH/";
    public static String BASEURLJLTH="http://106.37.229.146:4148/JLTH/";
    public static String BASEURLCODE="http://106.37.229.146:4149/BridgeSweepCode/BridgeSweepCode.html?";
    public static String TPDZ= "com.tyiroad.tyiroad.fileprovider";
    public final String APP_FILE_SAVE_PATH = "/mnt/sdcard/tyiroad/";
    public static MyApplication app;// 应用实例
    public static final int SHOW_TOAST_TIMES = 500;// 土司通知显示时长
    public CuringDao curingDao;
    public RequestOptions options;
    public static String GYDWID = "NO";
    public static String GZZLBH = "NO";
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        Utils.init(this);
        curingDao=new CuringDaoImpl(this);
        spUtils = new SPUtils(LocalConfiguration.SP_NAME);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(100*1000L, TimeUnit.MILLISECONDS)
                .readTimeout(100*1000L,TimeUnit.MILLISECONDS)
                .addInterceptor(new LoggerInterceptor("OkHttpUtils",true))
                .build();
        OkHttpUtils.initClient(okHttpClient);
        options=new RequestOptions()
                .placeholder(R.drawable.morentu)
                .error(R.drawable.morentu)
                .fallback(R.drawable.morentu);
        ZXingLibrary.initDisplayOpinion(this);
    }
    public void customToast(String textId) {
        if (textId == null)
            textId = "";
        customToast(Gravity.CENTER, SHOW_TOAST_TIMES, textId);
    }
    public void customToast(int location, int duration, String showTxt) {
        Toast.makeText(this,showTxt,duration).show();
    }
}
