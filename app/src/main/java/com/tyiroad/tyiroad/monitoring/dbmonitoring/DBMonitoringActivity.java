package com.tyiroad.tyiroad.monitoring.dbmonitoring;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.tooklkit.Tooklkit;
import com.tyiroad.tyiroad.MyApplication;
import com.tyiroad.tyiroad.R;
import com.tyiroad.tyiroad.db.dbhelper.CuringDaoImpl;
import com.tyiroad.tyiroad.db.dbhelper.dbInfo.MonitoringInfo;
import com.tyiroad.tyiroad.handle.LoadDataDialog;
import com.tyiroad.tyiroad.kdxf.DictationResult;
import com.tyiroad.tyiroad.monitoring.monitoringxq.MonitoringInitData;
import com.tyiroad.tyiroad.monitoring.monitoringxq.MonitoringXQAddJson;
import com.tyiroad.tyiroad.mvp.MVPBaseActivity;
import com.tyiroad.tyiroad.utils.DBTimePopupWindow;
import com.tyiroad.tyiroad.utils.DiseaseNewSelectObjectListener;
import com.tyiroad.tyiroad.utils.NoScroolGridView;
import com.tyiroad.tyiroad.utils.PopSelectListener;
import com.tyiroad.tyiroad.utils.Utils;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class DBMonitoringActivity extends MVPBaseActivity<DBMonitoringContract.View, DBMonitoringPresenter> implements DBMonitoringContract.View {

    @Bind(R.id.go_back)
    LinearLayout goBack;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.right)
    TextView right;
    @Bind(R.id.hea)
    RelativeLayout hea;
    @Bind(R.id.progress_te)
    TextView progressTe;
    @Bind(R.id.ed_progress)
    TextView edProgress;
    @Bind(R.id.rel_progress)
    RelativeLayout relProgress;
    @Bind(R.id.road_edit)
    EditText roadEdit;
    @Bind(R.id.yuyin)
    ImageView yuyin;
    @Bind(R.id.rrrrr)
    RelativeLayout rrrrr;
    @Bind(R.id.cai_ji_picture_add_grid)
    NoScroolGridView caiJiPictureAddGrid;
    @Bind(R.id.disease_new_bh_content_layou)
    LinearLayout diseaseNewBhContentLayou;
    @Bind(R.id.header)
    RelativeLayout header;
    @Bind(R.id.rq_te)
    TextView rqTe;
    @Bind(R.id.time_layout)
    LinearLayout timeLayout;
    @Bind(R.id.sgdw_te)
    TextView sgdwTe;
    @Bind(R.id.is_bridge_fw)
    TextView isBridgeFw;
    @Bind(R.id.save)
    TextView save;
    @Bind(R.id.add_log)
    TextView addLog;
    @Bind(R.id.lay_foot)
    LinearLayout layFoot;
    @Bind(R.id.foord)
    LinearLayout foord;
    @Bind(R.id.activity_new_disease_zhe_zhao_layout)
    View activityNewDiseaseZheZhaoLayout;
    @Bind(R.id.bg_layout)
    RelativeLayout bgLayout;
    private DBMonitoringGreAdapter dBMonitoringGreAdapter;
    private CuringDaoImpl curingDao;
    private MonitoringInfo DBMonitoringData;
    private DBTimePopupWindow Timpopup;
    private DBMonitoringPopupWindow XmmcPop;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private List<MonitoringInitData.XMDATABean> XmmcInfos = new ArrayList<>();
    private LoadDataDialog loadDataDialog;
    private MonitoringInitData.XMDATABean XmmcInfo;
    private static String APPID = "5bf211f5";
    private String dictationResultStr;
    private List<String> xf = new ArrayList<>();
    private List<String> zong = new ArrayList<>();
    public void setCameraPath(String cameraPath) {
        this.cameraPath = cameraPath;
    }
    private ArrayList<String> XmmcResult = new ArrayList<>();
    public void setChildViewPosition(int childViewPosition) {
        this.childViewPosition = childViewPosition;
    }
    private String cameraPath;//拍照获取图片的地址
    private int childViewPosition;//拍照获取图片的地址
    private Map<Integer, DBMonitoringGreAdapter> mapAdapter = new HashMap<>();
    private final int CHOOSE_PICTURE_CODE = 1;
    private final int CAMERA_CODE = 2;
    private Gson gson = new Gson();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        Utils.setStatusBarColor(this, R.color.theme_color);
        setContentView(R.layout.act_monitoringxq);
        ButterKnife.bind(this);
        curingDao = new CuringDaoImpl(this);
        showLoadingDialogMethod("加载中...");
        initview();
        initdata();
        initpopu();
        initlistener();
    }

    private void initlistener() {
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNull(edProgress.getText().toString())) {
                    MyApplication.app.customToast("请输入项目名称");
                    return;
                }
                save.setClickable(false);
                String str = "正在加载...";
                showLoadingDialogMethod(str);
                MonitoringInfo monitoringInfo = new MonitoringInfo();
                monitoringInfo.setCREATOR(MyApplication.spUtils.getString("dlr"));
                monitoringInfo.setXMMC(XmmcInfo.getF03());
                monitoringInfo.setXMID(XmmcInfo.getGUID_OBJ());
                monitoringInfo.setXMMS(roadEdit.getText().toString());
                monitoringInfo.setSCSJ(rqTe.getText().toString().replace("-", "/"));
                monitoringInfo.setSCDWID(MyApplication.spUtils.getString("dqgydwid"));
                monitoringInfo.setLOCATION("");
                String imgStr = "";
                ArrayList<String> listImgUrl = dBMonitoringGreAdapter.getListImgUrl();
                if (listImgUrl != null && listImgUrl.size() > 0) {
                    for (int k = 0; k < listImgUrl.size(); k++) {
                        if (k == 0) {
                            imgStr += listImgUrl.get(k);
                        } else {
                            imgStr += "," + listImgUrl.get(k);
                        }
                    }
                }
                monitoringInfo.setPIC(imgStr);
                int reture = curingDao.updateMonitoring(monitoringInfo);
                if (reture != 0) {
                    MyApplication.app.customToast("保存本地成功");
                    Intent intent = new Intent();
                    setResult(2, intent);
                    finish();
                }
                save.setClickable(true);
                if (loadDataDialog != null && loadDataDialog.isShowing()) {
                    loadDataDialog.cancel();
                }
            }
        });
        addLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNull(edProgress.getText().toString())) {
                    MyApplication.app.customToast("请输入项目名称");
                    return;
                }
                addLog.setClickable(false);
                String str = "正在加载...";
                showLoadingDialogMethod(str);
                MonitoringXQAddJson monitoringXQAddJson = new MonitoringXQAddJson();
                monitoringXQAddJson.setCREATOR(MyApplication.spUtils.getString("dlr"));
                monitoringXQAddJson.setXMID(XmmcInfo.getGUID_OBJ());
                monitoringXQAddJson.setXMMS(roadEdit.getText().toString());
                monitoringXQAddJson.setSCSJ(rqTe.getText().toString().replace("-", "/"));
                monitoringXQAddJson.setSCDWID(MyApplication.spUtils.getString("dqgydwid"));
                monitoringXQAddJson.setLOCATION("");
                String imgStr = "";
                ArrayList<String> listImgUrl = dBMonitoringGreAdapter.getListImgUrl();
                if (listImgUrl != null && listImgUrl.size() > 0) {
                    for (int k = 0; k < listImgUrl.size(); k++) {
                        if (k == 0) {
                            imgStr += listImgUrl.get(k);
                        } else {
                            imgStr += "," + listImgUrl.get(k);
                        }
                    }
                }
                if (!Utils.isNull(imgStr)) {
                    String[] strArr = imgStr.split(",");
                    if (strArr != null && strArr.length > 0) {
                        List<MonitoringXQAddJson.PICBean> PICBeanlist = new ArrayList<>();
                        for (int i = 0; i < strArr.length; i++) {
                            MonitoringXQAddJson.PICBean picBean = new MonitoringXQAddJson.PICBean();
                            String imgUrl = strArr[i];
                            String nameStr = imgUrl.substring(imgUrl.lastIndexOf("/") + 1, imgUrl.length());
                            String typeStr = imgUrl.substring(imgUrl.lastIndexOf(".") + 1, imgUrl.length());
                            picBean.setPicFileName(nameStr);
                            picBean.setPicFileType(typeStr);
                            String strBlob = Utils.bmpToBase64String(imgUrl);
                            picBean.setPicDataBlob(strBlob);
                            PICBeanlist.add(picBean);
                        }
                        monitoringXQAddJson.setPIC(PICBeanlist);
                    }
                }
                String json = gson.toJson(monitoringXQAddJson);
                Log.e("张成昆", json);
                mPresenter.addData(json);
            }
        });
        yuyin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getkdxf(roadEdit);
            }
        });
        timeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Timpopup.show(bgLayout);
            }
        });
        edProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XmmcPop.show(bgLayout);
            }
        });
    }

    private void initpopu() {
        XmmcPop = new DBMonitoringPopupWindow(this, "请选择项目名称", XmmcResult, new DiseaseNewSelectObjectListener() {
            @Override
            public void selectPosition(int position) {
                edProgress.setText(XmmcResult.get(position));
                XmmcInfo = XmmcInfos.get(position);
            }
        });
        Timpopup = new DBTimePopupWindow(this, new PopSelectListener() {
            @Override
            public void selectResult(Object... result) {
                String selDataStr = result[0].toString();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
                try {
                    Date dateResult = sdf.parse(selDataStr);
                    selDataStr = simpleDateFormat.format(dateResult);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                rqTe.setText(selDataStr);
            }
        });
    }

    private void initdata() {
        Integer sss = Integer.valueOf(getIntent().getStringExtra("MOID")).intValue();
        Log.e("initdata: ", getIntent().getStringExtra("MOID"));
        DBMonitoringData = curingDao.queryMonitoring(sss);
        edProgress.setText(DBMonitoringData.getXMMC());
        roadEdit.setText(DBMonitoringData.getXMMS());
        ArrayList<Drawable> listPicture = new ArrayList<>();
        ArrayList<String> listImgUrl = new ArrayList<>();
        if (!Utils.isNull(DBMonitoringData.getPIC())) {
            listImgUrl.addAll(Arrays.asList(DBMonitoringData.getPIC().split(",")));
            for (int i = 0; i < listImgUrl.size(); i++) {
                Bitmap bitmap = null;
                bitmap = Tooklkit.getImageThumbnail(listImgUrl.get(i), Tooklkit.dip2px(this, 480),
                        Tooklkit.dip2px(this, 480));
                if (bitmap != null) {
                    Drawable drawable = new BitmapDrawable(bitmap);
                    listPicture.add(drawable);
                }
            }
        }
        Drawable addPicture = getResources().getDrawable(R.drawable.sjq_add_tupian_sel);
        listPicture.add(addPicture);
        final int childPosition = diseaseNewBhContentLayou.getChildCount();
        dBMonitoringGreAdapter = new DBMonitoringGreAdapter(this, listPicture, listImgUrl, childPosition);
        caiJiPictureAddGrid.setAdapter(dBMonitoringGreAdapter);
        mapAdapter.put(childPosition, dBMonitoringGreAdapter);
        rqTe.setText(DBMonitoringData.getSCSJ());
        sgdwTe.setText(MyApplication.spUtils.getString("dqgydwmc"));
        mPresenter.initData(MyApplication.spUtils.getString("dqgydwid"));
        MonitoringInitData.XMDATABean XMDATABean = new MonitoringInitData.XMDATABean();
        XMDATABean.setF03(DBMonitoringData.getXMMC());
        XMDATABean.setGUID_OBJ(DBMonitoringData.getXMID());
        XmmcInfo = XMDATABean;
    }

    private void initview() {
        hea.setBackgroundColor(getResources().getColor(R.color.theme_color));
        title.setText("过程监控");
    }

    public void showZheZhaoView() {
        if (!activityNewDiseaseZheZhaoLayout.isShown()) {
            activityNewDiseaseZheZhaoLayout.setVisibility(View.VISIBLE);
        }
    }

    public void hideZheZhaoView() {
        if (activityNewDiseaseZheZhaoLayout.isShown()) {
            activityNewDiseaseZheZhaoLayout.setVisibility(View.GONE);
        }
    }

    private void showLoadingDialogMethod(String str) {
        if (loadDataDialog == null) {
            loadDataDialog = new LoadDataDialog(this);
        }
        loadDataDialog.setTitleStr(str);
        loadDataDialog.show();
    }

    @Override
    public void getData(List<MonitoringInitData.XMDATABean> videoVos2) {
        for (int i = 0; i < videoVos2.size(); i++) {
            XmmcResult.add(videoVos2.get(i).getF03());
        }
        XmmcInfos = videoVos2;
        if (loadDataDialog != null && loadDataDialog.isShowing()) {
            loadDataDialog.cancel();
        }
    }

    @Override
    public void getData2(String videoVos2) {
        curingDao.deleteMonitoring(Integer.valueOf(getIntent().getStringExtra("MOID")).intValue());
        MyApplication.app.customToast("上传成功");
        Intent intent = new Intent();
        setResult(2, intent);
        finish();
        addLog.setClickable(true);
        if (loadDataDialog != null && loadDataDialog.isShowing()) {
            loadDataDialog.cancel();
        }
    }

    @Override
    public void onRequestError(String msg) {

    }

    @Override
    public void onRequestEnd() {

    }

    private void getkdxf(final EditText kdaxfdate) {
        dictationResultStr = "[";
        // 语音配置对象初始化
        SpeechUtility.createUtility(DBMonitoringActivity.this, SpeechConstant.APPID + "=" + APPID);
        // 1.创建SpeechRecognizer对象，第2个参数：本地听写时传InitListener
        SpeechRecognizer mIat = SpeechRecognizer.createRecognizer(DBMonitoringActivity.this, null);
        // 交互动画
        final RecognizerDialog iatDialog = new RecognizerDialog(DBMonitoringActivity.this, null);
        // 2.设置听写参数，详见《科大讯飞MSC API手册(Android)》SpeechConstant类
        mIat.setParameter(SpeechConstant.DOMAIN, "iat");
        mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        mIat.setParameter(SpeechConstant.ACCENT, "mandarin");
        // 3.开始听写
        iatDialog.setListener(new RecognizerDialogListener() {
            @Override
            public void onResult(RecognizerResult results, boolean isLast) {

                if (!isLast) {
                    dictationResultStr += results.getResultString() + ",";
                } else {
                    dictationResultStr += results.getResultString() + "]";
                }
                if (isLast) {
                    Gson gson = new Gson();
                    List<DictationResult> dictationResultList = gson
                            .fromJson(dictationResultStr,
                                    new TypeToken<List<DictationResult>>() {
                                    }.getType());
                    String finalResult = "";
                    for (int i = 0; i < dictationResultList.size() - 1; i++) {
                        finalResult += dictationResultList.get(i)
                                .toString();
                    }
                    xf.clear();
                    zong.clear();
                    zong.add(kdaxfdate.getText().toString());
                    zong.add(finalResult);
                    kdaxfdate.setText(listToString2(zong));
                    kdaxfdate.requestFocus();
                    kdaxfdate.setSelection(listToString2(zong).length());
                }
            }

            @Override
            public void onError(SpeechError error) {
                error.getPlainDescription(true);
            }

        });
        iatDialog.show();

    }

    public static String listToString2(List<String> list) {
        StringBuilder sb = new StringBuilder();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (i < list.size() - 1) {
                    sb.append(list.get(i));
                } else {
                    sb.append(list.get(i));
                }
            }
        }
        return sb.toString();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == CHOOSE_PICTURE_CODE) {//从相册获取照片
            if (intent != null) {
                ArrayList<String> listSelectPic = intent.getStringArrayListExtra("filelist");
                int position = intent.getIntExtra("position", -1);
                if (position != -1 && listSelectPic != null && listSelectPic.size() > 0) {
                    final DBMonitoringGreAdapter addPictureAdapter = mapAdapter.get(position);
                    final ArrayList<Drawable> listPicture = addPictureAdapter.getListPicture();
                    final ArrayList<String> listImgUrl = addPictureAdapter.getListImgUrl();

                    String pathStr = listSelectPic.get(0);
                    final String savePathStr = pathStr.substring(0, pathStr.lastIndexOf("/"));
                    Luban.with(this)
                            .load(listSelectPic)                                  // 传人要压缩的图片列表
                            .ignoreBy(100)                                  // 忽略不压缩图片的大小
//                            .setTargetDir(savePathStr)                     // 设置压缩后文件存储位置
                            .setCompressListener(new OnCompressListener() { //设置回调
                                @Override
                                public void onStart() {
                                    // TODO 压缩开始前调用，可以在方法内启动 loading UI
                                }

                                @Override
                                public void onSuccess(File file) {
                                    // TODO 压缩成功后调用，返回压缩后的图片文件
                                    String imgPath = file.getPath();
                                    listPicture.remove(listPicture.size() - 1);
                                    Bitmap bitmap = Tooklkit.getImageThumbnail(imgPath, Tooklkit.dip2px(DBMonitoringActivity.this, 480),
                                            Tooklkit.dip2px(DBMonitoringActivity.this, 480));
                                    if (bitmap != null) {
                                        Log.i("图片压缩成功", savePathStr + "地址" + imgPath);
                                        Drawable drawable = new BitmapDrawable(bitmap);
                                        listPicture.add(drawable);
                                        listImgUrl.add(imgPath);
                                        Drawable addPicture = getResources().getDrawable(R.drawable.sjq_add_tupian_sel);
                                        listPicture.add(addPicture);
                                        addPictureAdapter.notifyDataSetChanged();
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    // TODO 当压缩过程出现问题时调用
                                    Log.i("图片压缩失败", "");
                                }
                            }).launch();    //启动压缩
                }
            }
        } else if (requestCode == CAMERA_CODE) {//拍照获取照片
            if (resultCode == -1) {
                if (!Utils.isNull(cameraPath) && childViewPosition != -1) {
                    Utils.dealBitmapRotate(cameraPath);
                    final DBMonitoringGreAdapter addPictureAdapter = mapAdapter.get(childViewPosition);
                    final ArrayList<Drawable> listPicture = addPictureAdapter.getListPicture();
                    final ArrayList<String> listImgUrl = addPictureAdapter.getListImgUrl();

                    Luban.with(this)
                            .load(cameraPath)                                  // 传人要压缩的图片列表
                            .ignoreBy(100)                                  // 忽略不压缩图片的大小
//                            .setTargetDir(cameraPath.substring(0, cameraPath.lastIndexOf("/")))                        // 设置压缩后文件存储位置
                            .setCompressListener(new OnCompressListener() { //设置回调
                                @Override
                                public void onStart() {
                                    // TODO 压缩开始前调用，可以在方法内启动 loading UI
                                }

                                @Override
                                public void onSuccess(File file) {
                                    // TODO 压缩成功后调用，返回压缩后的图片文件
                                    String imgPath = file.getPath();
                                    listPicture.remove(listPicture.size() - 1);
                                    Bitmap bitmap = Tooklkit.getImageThumbnail(imgPath, Tooklkit.dip2px(DBMonitoringActivity.this, 480),
                                            Tooklkit.dip2px(DBMonitoringActivity.this, 480));
                                    if (bitmap != null) {
                                        Log.i("图片压缩成功", cameraPath + "地址" + imgPath);
                                        Drawable drawable = new BitmapDrawable(bitmap);
                                        listPicture.add(drawable);
                                        listImgUrl.add(imgPath);
                                        Drawable addPicture = getResources().getDrawable(R.drawable.sjq_add_tupian_sel);
                                        listPicture.add(addPicture);
                                        addPictureAdapter.notifyDataSetChanged();
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    // TODO 当压缩过程出现问题时调用
                                    Log.i("图片压缩失败", "");
                                }
                            }).launch();    //启动压缩
                }
            }
        }
    }

}
