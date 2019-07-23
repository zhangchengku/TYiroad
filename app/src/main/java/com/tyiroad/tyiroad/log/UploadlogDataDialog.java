package com.tyiroad.tyiroad.log;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.tooklkit.Tooklkit;
import com.tyiroad.tyiroad.Bean.AddLogjson;
import com.tyiroad.tyiroad.Bean.Basebean;
import com.tyiroad.tyiroad.MyApplication;
import com.tyiroad.tyiroad.R;
import com.tyiroad.tyiroad.db.dbhelper.CuringDao;
import com.tyiroad.tyiroad.db.dbhelper.CuringDaoImpl;
import com.tyiroad.tyiroad.db.dbhelper.dbInfo.LogRecordInfo;
import com.tyiroad.tyiroad.thread.NetAPIExecutor;
import com.tyiroad.tyiroad.utils.Utils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 10270 on 2018/11/19.
 */

public class UploadlogDataDialog {

    private Dialog dialog;// 对话框
    private TextView titleTxt;//标题
    private TextView contentTxt;//提示内容
    private TextView vAnimTxt;
    private ProgressBar progressBar;//进度条
    private Activity activity;
    private List<LogRecordInfo> listDataLocal;

    private LogRecordInfo baseInfo;//当前正在上传的条目

    private int pro = 0;
    private final int MSG = 1;
    private final int UPLOAD_SUCCESS = 2;
    private Handler mHandler;
    private int total;//总条数
    private Gson gson=new Gson();
    private final int SAVE_DISEASE_TAG = 3;//上传病害数据调用网络接口标识
    private NetAPIExecutor netAPIExecutor;
    private int currentUploadItem=0;//当前正在上传的条目下标
    private CuringDaoImpl curingDao;
    private int currentUploadItemCurrentCount=0;//当前正在上传的条目包含的病害信息的个数正在上传的病害信息的下标
    private ValueAnimator valueAnimator;
    private String loadingStr;
    private String[] dotText = {""," . ", " . . ", " . . ."};
    private String nrstring;


    public Dialog getDialog() {
        return dialog;
    }

    /**
     * 构造方法
     *
     * @param context 使用该对话框的类
     */
    public UploadlogDataDialog(Activity context, List<LogRecordInfo> listDataLocal) {
        this.activity = context;
        this.listDataLocal = listDataLocal;
        curingDao = new CuringDaoImpl(activity);
        dialog = new Dialog(context, R.style.CustomDialogStyle);
        dialog.setCanceledOnTouchOutside(false);
        View viewDialog = LayoutInflater.from(context).inflate(
                R.layout.load_offline_data_dialog, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(Tooklkit.dip2px(context, 300),
                ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.addContentView(viewDialog, params);
        progressBar = (ProgressBar) viewDialog
                .findViewById(R.id.load_offline_data_progressbar);
        titleTxt = (TextView) viewDialog
                .findViewById(R.id.load_offline_data_title_txt);
        contentTxt = (TextView) viewDialog
                .findViewById(R.id.load_offline_data_value_txt);
        vAnimTxt=(TextView)viewDialog.findViewById(R.id.load_offline_data_title_anim_txt);

        titleTxt.setText("数据上传中");
        if (valueAnimator == null) {
            valueAnimator = ValueAnimator.ofInt(0, 4).setDuration(1000);
            valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int i = (int) animation.getAnimatedValue();
                    vAnimTxt.setText(dotText[i % dotText.length]);
                }
            });
        }
        valueAnimator.start();

        total = listDataLocal.size();
        String totalNumber = String.valueOf(total);
        String currentLoadNumber = "1";
        setWarmContent(totalNumber, currentLoadNumber);

        netAPIExecutor=NetAPIExecutor.newInstance(context);

        progressBar.setMax(total);
        //创建一个Handler
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                //处理消息
                switch (msg.what) {
                    case MSG:
                        //设置滚动条和text的值
                        progressBar.setProgress(pro);
                        String totalNumber = String.valueOf(total);
                        if(pro<total){
                            String currentLoadNumber = String.valueOf(pro + 1);
                            setWarmContent(totalNumber, currentLoadNumber);
                        }
                        break;
                    case UPLOAD_SUCCESS:
                        if(activity instanceof LogActivity){
                            ((LogActivity) activity).refreshDataMethod();
                        }
                        MyApplication.app.customToast("上传成功");
                        close();
                        break;
                    default:
                        break;
                }
            }
        };

        start();
    }

    private void saveDiseaseNetwork(LogRecordInfo baseInfo) {
        if (baseInfo != null ) {
            getnr(baseInfo);
            AddLogjson addLogjsons = new AddLogjson();
            List<AddLogjson.XCRZBean> addLogjsonlist = new ArrayList<AddLogjson.XCRZBean>();
            AddLogjson.XCRZBean addLogjson = new AddLogjson.XCRZBean();
            addLogjson.setGYDW(MyApplication.spUtils.getString("dqgydwid"));
            addLogjson.setXCLD(baseInfo.getXCLD());
            addLogjson.setLDMC(baseInfo.getLDMC());
            addLogjson.setXCSJ(baseInfo.getJLSJ());
            addLogjson.setTQ(baseInfo.getTQ());
            addLogjson.setXCR(MyApplication.app.spUtils.getString("dlr"));
            addLogjson.setCREATOR(MyApplication.app.spUtils.getString("dlr"));//空
            addLogjson.setXCXZ(curingDao.queryInvestigationByMc(baseInfo.getXCXZMC()).getDCID());
            addLogjson.setCLJL(baseInfo.getCLJL());
            addLogjson.setXCNR(nrstring);
            baseInfo.getTPDZ();
            if (!Utils.isNull(baseInfo.getTPDZ())) {
                String[] strArr = baseInfo.getTPDZ().split(",");
                if (strArr != null && strArr.length > 0) {
                    ArrayList<AddLogjson.PICBean> listPic = new ArrayList<>();
                    for (int i = 0; i < strArr.length; i++) {
                        AddLogjson.PICBean picInfo = new AddLogjson.PICBean();
                        String imgUrl = strArr[i];
                        String nameStr = imgUrl.substring(imgUrl.lastIndexOf("/") + 1, imgUrl.length());
                        String typeStr = imgUrl.substring(imgUrl.lastIndexOf(".") + 1, imgUrl.length());
                        picInfo.setPicFileName(nameStr);
                        picInfo.setPicFileType(typeStr);
                        String strBlob = Utils.bmpToBase64String(imgUrl);
                        Log.i("图片提交的二进制流", "=====" + strBlob);
                        picInfo.setPicDataBlob(strBlob);
                        listPic.add(picInfo);
                    }
                    addLogjson.setPICRZ(listPic);
                }
            }
            addLogjsonlist.add(addLogjson);
            addLogjsons.setXCRZ(addLogjsonlist);
            String tijiaodates = gson.toJson(addLogjsons);
            String url = MyApplication.BASEURL+"MobileXcrz";
            OkHttpUtils
                    .post()
                    .addParams("JSON", tijiaodates)
                    .url(url)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(okhttp3.Call call, Exception e, int id) {
                        }
                        @Override
                        public void onResponse(String response, int id) {
                            Basebean baseInfo = JSON.parseObject(response, Basebean.class);
                            if (baseInfo != null) {
                                if ("1".equals(baseInfo.getSTATE())) {
                                    currentUploadItemCurrentCount++;
                                    successNext();
                                } else if (!Utils.isNull(baseInfo.getMSG())) {
                                    MyApplication.app.customToast(baseInfo.getMSG());
                                }
                            }else{
                                if(dialog!=null&&dialog.isShowing()){
                                    dialog.cancel();
                                }
                                MyApplication.app.customToast("当前没有网络");
                            }
                        }
                    });
        }
    }

    private void getnr(LogRecordInfo baseInfo) {
        nrstring = replaceNull2(baseInfo.getCLJL()) + "," +
                replaceNull2(baseInfo.getQCSS()) + "," +
                replaceNull2(baseInfo.getQCZC()) + "," +
                replaceNull2(baseInfo.getWJJ()) + "," +
                replaceNull2(baseInfo.getWG()) + "," +
                replaceNull2(baseInfo.getLMZHYH()) + "," +
                replaceNull2(baseInfo.getGFJ()) + "," +
                replaceNull2(baseInfo.getYSC());
    }
    public static String replaceNull2(String str) {
        if (str.equals("")) {
            return "0";
        }
        return str;
    }
    /**
     * 上传一条成功之后继续上传下一条
     */
    public void successNext() {
        pro += 1;
        Message msg = new Message();
        msg.what = MSG;
        mHandler.sendMessage(msg);
        if (pro >= total) {
            //如果是编辑页面的立即上传 上传成功后需要删除数据库里的记录\
            CuringDao curingDao=new CuringDaoImpl(activity);
            for (int i = 0; i < listDataLocal.size(); i++) {
                LogRecordInfo diseaseBaseInfo = listDataLocal.get(i);
                if (diseaseBaseInfo != null) {
                    curingDao.deleteRzjlById(diseaseBaseInfo.getRZID());
                }
            }
            Message msgLoad = new Message();
            msgLoad.what = UPLOAD_SUCCESS;
            mHandler.sendMessageDelayed(msgLoad,500);
        } else {
            currentUploadItem+=1;
            start();
        }
    }

    private void start(){
        baseInfo=listDataLocal.get(currentUploadItem);
        currentUploadItemCurrentCount=0;
        saveDiseaseNetwork(baseInfo);
    }



    /**
     * 设置对话框的提示内容
     *
     * @param totalNumber       总共需要上传的病害的个数
     * @param currentLoadNumber 当前正在上传第几个病害
     */
    public void setWarmContent(String totalNumber, String currentLoadNumber) {
        String content = String.format(activity.getString(R.string.disease_data_uploading_content), totalNumber, currentLoadNumber);
        contentTxt.setText(content);
    }

    /**
     * 显示
     */
    public void show() {
        dialog.show();
    }

    /**
     * 关闭
     */
    public void close() {
        if(dialog!=null&&dialog.isShowing()){
            dialog.cancel();
        }
    }


    public static String replaceNull(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }
}

