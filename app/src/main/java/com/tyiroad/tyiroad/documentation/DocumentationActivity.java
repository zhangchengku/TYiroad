package com.tyiroad.tyiroad.documentation;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.google.gson.Gson;
import com.tooklkit.Tooklkit;
import com.tyiroad.tyiroad.Bean.Basebean;
import com.tyiroad.tyiroad.MyApplication;
import com.tyiroad.tyiroad.R;
import com.tyiroad.tyiroad.handle.LoadDataDialog;
import com.tyiroad.tyiroad.mvp.MVPBaseActivity;
import com.tyiroad.tyiroad.quality.XmmcBean;
import com.tyiroad.tyiroad.utils.DiseaseNewSelectObjectListener;
import com.tyiroad.tyiroad.utils.DocumentTimePop;
import com.tyiroad.tyiroad.utils.NoScroolGridView;
import com.tyiroad.tyiroad.utils.PopSelectListener;
import com.tyiroad.tyiroad.utils.Utils;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class DocumentationActivity extends MVPBaseActivity<DocumentationContract.View, DocumentationPresenter> implements DocumentationContract.View {

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
    @Bind(R.id.check_te)
    TextView checkTe;
    @Bind(R.id.ed_check)
    TextView edCheck;
    @Bind(R.id.rel_check)
    RelativeLayout relCheck;
    @Bind(R.id.evaluate_te)
    TextView evaluateTe;
    @Bind(R.id.ed_evaluate)
    TextView edEvaluate;
    @Bind(R.id.rel_evaluate)
    RelativeLayout relEvaluate;
    @Bind(R.id.completedbridge_te)
    TextView completedbridgeTe;
    @Bind(R.id.ed_completedbridge)
    EditText edCompletedbridge;
    @Bind(R.id.rel_completedbridge)
    RelativeLayout relCompletedbridge;
    @Bind(R.id.wzxx_te)
    TextView wzxxTe;
    @Bind(R.id.ed_wzxx)
    TextView edWzxx;
    @Bind(R.id.rel_wzxx)
    RelativeLayout relWzxx;
    @Bind(R.id.dw_te)
    TextView dwTe;
    @Bind(R.id.ed_dw)
    TextView edDw;
    @Bind(R.id.rel_dw)
    RelativeLayout relDw;
    @Bind(R.id.r_te)
    TextView rTe;
    @Bind(R.id.ed_r)
    TextView edR;
    @Bind(R.id.rel_r)
    RelativeLayout relR;
    @Bind(R.id.cai_ji_picture_add_grid)
    NoScroolGridView caiJiPictureAddGrid;
    @Bind(R.id.disease_custom_edit_item_cjtp_layout)
    LinearLayout diseaseCustomEditItemCjtpLayout;
    @Bind(R.id.disease_new_bh_content_layout)
    LinearLayout diseaseNewBhContentLayout;
    @Bind(R.id.is_bridge_fw)
    TextView isBridgeFw;
    @Bind(R.id.activity_disease_new_scrollview)
    ScrollView activityDiseaseNewScrollview;
    @Bind(R.id.add_log)
    TextView addLog;
    @Bind(R.id.activity_new_disease_zhe_zhao_layout)
    View activityNewDiseaseZheZhaoLayout;
    @Bind(R.id.lay_rel)
    RelativeLayout layRel;
    private DocumentaPopupWindow GcmcPop;
    private ArrayList<String> GcmcResult = new ArrayList<>();
    private DocumentaPopupWindow GcjdPop;
    private ArrayList<String> GcjdResult = new ArrayList<>();
    private DocumentaPopupWindow ZllxPop;
    private ArrayList<String> ZllxResult = new ArrayList<>();
    private SimpleDateFormat simpleDateFormat;
    private DocumentTimePop documentTimePop;
    private DocumentGredAdapter documentGredAdapter;
    private final int CHOOSE_PICTURE_CODE = 1;
    private final int CAMERA_CODE = 2;
    private List<XmmcBean.DATABean> GcmcInfos;
    private XmmcBean.DATABean GcmcInfo;
    private List<GcBean.DATABean.GcjdBean> GcjdInfos = new ArrayList<>();
    private GcBean.DATABean.GcjdBean GcjdInfo;
    private List<GcBean.DATABean.ZllxBean> ZllxInfos = new ArrayList<>();
    private GcBean.DATABean.ZllxBean ZllxInfo;
    private LoadDataDialog loadDataDialog;

    public void setCameraPath(String cameraPath) {
        this.cameraPath = cameraPath;
    }
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    private Gson gson = new Gson();
    private Map<Integer, DocumentGredAdapter> mapAdapter = new HashMap<>();
    private String cameraPath;//拍照获取图片的地址
    private int childViewPosition;//拍照获取图片的地址

    public void setChildViewPosition(int childViewPosition) {
        this.childViewPosition = childViewPosition;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setStatusBarColor(this, R.color.theme_color);
        setContentView(R.layout.act_documentation);
        ButterKnife.bind(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        initdata();
        initpop();
    }

    private void initdata() {
        title.setText("资料上传");
        edDw.setText(MyApplication.spUtils.getString("dqgydwmc"));
        edR.setText(MyApplication.spUtils.getString("dlr"));
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        String currentDate = simpleDateFormat.format(calendar.getTime());
        edWzxx.setText(currentDate);
        ArrayList<Drawable> listPicture = new ArrayList<>();
        ArrayList<String> listImgUrl = new ArrayList<>();
        Drawable addPicture = getResources().getDrawable(R.drawable.sjq_add_tupian_sel);
        listPicture.add(addPicture);
        final int childPosition = layRel.getChildCount();
        documentGredAdapter = new DocumentGredAdapter(this, listPicture, listImgUrl, childPosition);
        caiJiPictureAddGrid.setAdapter(documentGredAdapter);
        mapAdapter.put(childPosition, documentGredAdapter);
        String str = "正在加载...";
        showLoadingDialogMethod(str);
        mPresenter.getXmmcData();
    }

    private void initpop() {
        GcmcPop = new DocumentaPopupWindow(this, "请选择工程名称", GcmcResult, new DiseaseNewSelectObjectListener() {
            @Override
            public void selectPosition(int position) {
                edProgress.setText(GcmcResult.get(position));
                GcmcInfo = GcmcInfos.get(position);
                if (locationClient == null) {
                    initLocation();
                }
                startLocation();
            }
        });
        GcjdPop = new DocumentaPopupWindow(this, "请选择工程阶段", GcjdResult, new DiseaseNewSelectObjectListener() {
            @Override
            public void selectPosition(int position) {
                edCheck.setText(GcjdResult.get(position));
                GcjdInfo = GcjdInfos.get(position);
            }
        });
        ZllxPop = new DocumentaPopupWindow(this, "请选择资料类型", ZllxResult, new DiseaseNewSelectObjectListener() {
            @Override
            public void selectPosition(int position) {
                edEvaluate.setText(ZllxResult.get(position));
                ZllxInfo = ZllxInfos.get(position);
            }
        });
        documentTimePop = new DocumentTimePop(this, new PopSelectListener() {
            @Override
            public void selectResult(Object... result) {
                String selDataStr = result[0].toString();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
                try {
                    Date dateResult = sdf.parse(selDataStr);
                    selDataStr = simpleDateFormat.format(dateResult);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                edWzxx.setText(selDataStr);
            }
        });
    }



    @OnClick({R.id.rel_progress, R.id.rel_check,R.id.rel_evaluate, R.id.rel_wzxx, R.id.add_log,R.id.go_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.go_back:
               finish();
                break;
            case R.id.rel_progress:
                GcmcPop.show(layRel);
                break;
            case R.id.rel_check:
                GcjdPop.show(layRel);
                break;
            case R.id.rel_evaluate:
                ZllxPop.show(layRel);
                break;
            case R.id.rel_wzxx:
                if (documentTimePop != null ) {
                    documentTimePop.show(layRel);
                }
                break;
            case R.id.add_log:
                String str = "正在加载...";
                showLoadingDialogMethod(str);
                addLog.setClickable(false);
                DocumentationJson documentationJson = new DocumentationJson();
                documentationJson.setGCID(GcmcInfo.getGUID_OBJ());
                documentationJson.setLOCATION(isBridgeFw.getText().toString());
                documentationJson.setGCJD(GcjdInfo.getID());
                documentationJson.setZLLX(ZllxInfo.getID());
                documentationJson.setZLMC(edCompletedbridge.getText().toString());
                documentationJson.setSCSJ(edWzxx.getText().toString());
                documentationJson.setUSERID(MyApplication.spUtils.getString("username"));
                documentationJson.setSCR(MyApplication.spUtils.getString("dlr"));
                documentationJson.setSCDW(MyApplication.spUtils.getString("dqgydwid"));
                String imgStr = "";
                ArrayList<String> listImgUrl = documentGredAdapter.getListImgUrl();
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
                        List<DocumentationJson.PICBean> PICBeanlist = new ArrayList<>();
                        for (int i = 0; i < strArr.length; i++) {
                            DocumentationJson.PICBean picBean = new DocumentationJson.PICBean();
                            String imgUrl = strArr[i];
                            String nameStr = imgUrl.substring(imgUrl.lastIndexOf("/") + 1, imgUrl.length());
                            String typeStr = imgUrl.substring(imgUrl.lastIndexOf(".") + 1, imgUrl.length());
                            picBean.setPicFileName(nameStr);
                            picBean.setPicFileType(typeStr);
                            String strBlob = Utils.bmpToBase64String(imgUrl);
                            Log.i("图片提交的二进制流", "=====" + strBlob);
                            picBean.setPicDataBlob(strBlob);
                            PICBeanlist.add(picBean);
                        }
                        documentationJson.setPIC(PICBeanlist);
                    }
                }
                String json = gson.toJson(documentationJson);
                Log.e("张成昆 ", json);
                mPresenter.upData(json);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == CHOOSE_PICTURE_CODE) {//从相册获取照片
            if (intent != null) {
                ArrayList<String> listSelectPic = intent.getStringArrayListExtra("filelist");
                int position = intent.getIntExtra("position", -1);
                if (position != -1 && listSelectPic != null && listSelectPic.size() > 0) {
                    final DocumentGredAdapter addPictureAdapter = mapAdapter.get(position);
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
                                    Bitmap bitmap = Tooklkit.getImageThumbnail(imgPath, Tooklkit.dip2px(DocumentationActivity.this, 480),
                                            Tooklkit.dip2px(DocumentationActivity.this, 480));
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
                    final DocumentGredAdapter addPictureAdapter = mapAdapter.get(childViewPosition);
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
                                    Bitmap bitmap = Tooklkit.getImageThumbnail(imgPath, Tooklkit.dip2px(DocumentationActivity.this, 480),
                                            Tooklkit.dip2px(DocumentationActivity.this, 480));
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

    public void showZheZhaoView() {
        if (!activityNewDiseaseZheZhaoLayout.isShown()) {
            activityNewDiseaseZheZhaoLayout.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 隐藏遮罩view
     */
    public void hideZheZhaoView() {
        if (activityNewDiseaseZheZhaoLayout.isShown()) {
            activityNewDiseaseZheZhaoLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onRequestError(String msg) {

    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void getResult(Basebean videoVos2) {
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
    public void getXmmcDatas(List<XmmcBean.DATABean> DATA) {
        if (DATA.size()>0){
            GcmcInfos = DATA;
            for (int i = 0; i <DATA.size() ; i++) {
                GcmcResult.add(DATA.get(i).getXMMC());
            }
        }
        mPresenter.getGcjdData();
    }

    @Override
    public void getGcjdDatas(GcBean GcBean) {
        if (GcBean.getDATA().size()>0){
            if (GcBean.getDATA().get(0).getGcjd().size()>0){
                GcjdInfos =GcBean.getDATA().get(0).getGcjd();
                for (int i = 0; i <GcBean.getDATA().get(0).getGcjd().size() ; i++) {
                    GcjdResult.add(GcBean.getDATA().get(0).getGcjd().get(i).getTEXT());
                }
            }
            if (GcBean.getDATA().get(0).getZllx().size()>0){
                ZllxInfos =GcBean.getDATA().get(0).getZllx();
                for (int i = 0; i <GcBean.getDATA().get(0).getZllx().size() ; i++) {
                    ZllxResult.add(GcBean.getDATA().get(0).getZllx().get(i).getTEXT());
                }
            }

        }
        if (loadDataDialog != null && loadDataDialog.isShowing()) {
            loadDataDialog.cancel();
        }
    }

    @Override
    public void getLocationResult(int basebean) {
        if (basebean == 1) {
            isBridgeFw.setText("在范围内");
        }
    }
    /********定位功能开始**********/
    /**
     * 初始化定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void initLocation() {
        //初始化client
        locationClient = new AMapLocationClient(this.getApplicationContext());
        locationOption = getDefaultOption();
        //设置定位参数
        locationClient.setLocationOption(locationOption);
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
    }

    /**
     * 默认的定位参数
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(true);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        mOption.setGeoLanguage(AMapLocationClientOption.GeoLanguage.DEFAULT);//可选，设置逆地理信息的语言，默认值为默认语言（根据所在地区选择语言）
        return mOption;
    }

    /**
     * 定位监听
     */
    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {//定位成功
                Double longitude = aMapLocation.getLongitude();//经度
                Double latitude = aMapLocation.getLatitude();//纬度
                Log.i("定位结果", "经度" + String.valueOf(longitude) + " 纬度" + String.valueOf(latitude));
                mPresenter.getLocation(GcmcInfo.getGUID_OBJ(), String.valueOf(longitude), String.valueOf(latitude));
            } else {//定位失败
                Log.i("定位失败", "定位失败");
            }
            stopLocation();
        }
    };

    /**
     * 开始定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void startLocation() {
        // 设置定位参数
//        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
    }

    /**
     * 停止定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void stopLocation() {
        // 停止定位
        locationClient.stopLocation();
    }

    /**
     * 销毁定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void destroyLocation() {
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyLocation();
    }

    private void showLoadingDialogMethod(String str) {
        if (loadDataDialog == null) {
            loadDataDialog = new LoadDataDialog(this);
        }
        loadDataDialog.setTitleStr(str);
        loadDataDialog.show();
    }
}
