package com.tyiroad.tyiroad.quality;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.google.gson.Gson;
import com.tooklkit.Tooklkit;
import com.tyiroad.tyiroad.MyApplication;
import com.tyiroad.tyiroad.R;
import com.tyiroad.tyiroad.db.dbhelper.CuringDaoImpl;
import com.tyiroad.tyiroad.db.dbhelper.dbInfo.QualityInfo;
import com.tyiroad.tyiroad.handle.LoadDataDialog;
import com.tyiroad.tyiroad.mvp.MVPBaseActivity;
import com.tyiroad.tyiroad.utils.DiseaseNewSelectObjectListener;
import com.tyiroad.tyiroad.utils.Utils;
import com.tyiroad.tyiroad.utils.adapter.CommonAdapter;
import com.tyiroad.tyiroad.utils.adapter.ViewHolder;

import java.io.File;
import java.util.ArrayList;
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

public class QualityActivity extends MVPBaseActivity<QualityContract.View, QualityPresenter> implements QualityContract.View {

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
    @Bind(R.id.incompletebridge_te)
    TextView incompletebridgeTe;
    @Bind(R.id.ed_incompletebridge)
    TextView edIncompletebridge;
    @Bind(R.id.rel_incompletebridge)
    RelativeLayout relIncompletebridge;
    @Bind(R.id.lay_xm)
    LinearLayout layXm;
    @Bind(R.id.listview)
    NoScroolGridView listview;
    @Bind(R.id.explain_et)
    EditText explainEt;
    @Bind(R.id.INQuality)
    RelativeLayout INQuality;
    @Bind(R.id.cai_ji_picture_add_grid)
    com.tyiroad.tyiroad.utils.NoScroolGridView caiJiPictureAddGrid;
    @Bind(R.id.disease_custom_edit_item_cjtp_layout)
    LinearLayout diseaseCustomEditItemCjtpLayout;
    @Bind(R.id.disease_new_bh_content_layout)
    LinearLayout diseaseNewBhContentLayout;
    @Bind(R.id.is_bridge_fw)
    TextView isBridgeFw;
    @Bind(R.id.activity_disease_new_scrollview)
    ScrollView activityDiseaseNewScrollview;
    @Bind(R.id.save)
    TextView save;
    @Bind(R.id.add_log)
    TextView addLog;
    @Bind(R.id.lay_foot)
    LinearLayout layFoot;
    @Bind(R.id.activity_new_disease_zhe_zhao_layout)
    View activityNewDiseaseZheZhaoLayout;
    @Bind(R.id.lay_rel)
    RelativeLayout layRel;
    @Bind(R.id.jcfw_te)
    TextView jcfwTe;
    @Bind(R.id.ed_jcfw)
    EditText edJcfw;
    @Bind(R.id.rel_jcfw)
    RelativeLayout relJcfw;
    private QualityGredAdapter qualityGredAdapter;
    private ArrayList<String> XmmcResult = new ArrayList<>();
    private ArrayList<String> FxgcResult = new ArrayList<>();
    private ArrayList<String> FbgcResult = new ArrayList<>();
    private QualityPopupWindow XmmcPop, FbgcPop, FxgcPop;

    private List<FbgcBean.DATABean> FbgcInfos = new ArrayList<>();
    private FbgcBean.DATABean FbgcInfo;
    private List<ListDataBean.DATABean> listData = new ArrayList<>();
    private FxgcBean.DATABean FxgcInfo;
    private List<FxgcBean.DATABean> FxgcInfos = new ArrayList<>();
    private Gson gson = new Gson();
    private CuringDaoImpl curingDao;
    private CommonAdapter<ListDataBean.DATABean> adapter;
    private XmmcBean.DATABean XmmcInfo;
    private List<XmmcBean.DATABean> XmmcInfos = new ArrayList<>();

    public void setCameraPath(String cameraPath) {
        this.cameraPath = cameraPath;
    }

    private Map<Integer, QualityGredAdapter> mapAdapter = new HashMap<>();
    private String cameraPath;//拍照获取图片的地址
    private int childViewPosition;//拍照获取图片的地址

    public void setChildViewPosition(int childViewPosition) {
        this.childViewPosition = childViewPosition;
    }

    private LoadDataDialog loadDataDialog;
    private final int CHOOSE_PICTURE_CODE = 1;
    private final int CAMERA_CODE = 2;
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setStatusBarColor(this, R.color.theme_color);
        setContentView(R.layout.act_quality);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        ButterKnife.bind(this);
        curingDao = new CuringDaoImpl(this);
        String str = "正在加载...";
        showLoadingDialogMethod(str);
        initdata();
        initpop();
        initlisener();

    }

    private void showLoadingDialogMethod(String str) {
        if (loadDataDialog == null) {
            loadDataDialog = new LoadDataDialog(this);
        }
        loadDataDialog.setTitleStr(str);
        loadDataDialog.show();
    }

    private void initlisener() {
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNull(XmmcInfo.getGUID_OBJ())) {
                    MyApplication.app.customToast("请选择项目名称");
                    return;
                }
                if (Utils.isNull(FbgcInfo.getGUID_OBJ())) {
                    MyApplication.app.customToast("请选择分部工程");
                    return;
                }
                if (Utils.isNull(FxgcInfo.getGUID_OBJ())) {
                    MyApplication.app.customToast("请选择分项工程");
                    return;
                }
                save.setClickable(false);
                String str = "正在加载...";
                showLoadingDialogMethod(str);
                QualityInfo qualityInfo = new QualityInfo();
                qualityInfo.setXmmc(XmmcInfo.getXMMC());
                qualityInfo.setXmmcID(XmmcInfo.getGUID_OBJ());
                qualityInfo.setFbgc(FbgcInfo.getXMMC());
                qualityInfo.setFbgcID(FbgcInfo.getGUID_OBJ());
                qualityInfo.setFxgc(FxgcInfo.getXMMC());
                qualityInfo.setFxgcID(FxgcInfo.getGUID_OBJ());
                qualityInfo.setJLSJ(Utils.thisMonth());
                qualityInfo.setGcbw(edCompletedbridge.getText().toString());
                qualityInfo.setJcr(edIncompletebridge.getText().toString());
                qualityInfo.setJcdw(MyApplication.spUtils.getString("dqgydwid"));
                qualityInfo.setBhgsm(explainEt.getText().toString());
                qualityInfo.setDlwz(isBridgeFw.getText().toString());//地理位置
                qualityInfo.setJCFW(edJcfw.getText().toString());
                String imgStr = "";
                ArrayList<String> listImgUrl = qualityGredAdapter.getListImgUrl();
                if (listImgUrl != null && listImgUrl.size() > 0) {
                    for (int k = 0; k < listImgUrl.size(); k++) {
                        if (k == 0) {
                            imgStr += listImgUrl.get(k);
                        } else {
                            imgStr += "," + listImgUrl.get(k);
                        }
                    }
                }
                String JCXM = "";
                String JCFWZ = "";
                String CSXMID = "";
                String LsHg = "";
                String HGDS = "";
                for (int i = 0; i < listData.size(); i++) {
                    if (i == 0) {
                        JCXM = listData.get(i).getXMMC();
                        JCFWZ = listData.get(i).getXMCS();
                        CSXMID = listData.get(i).getGUID_OBJ();
                        LsHg = listData.get(i).getISCHECK();
                        HGDS = listData.get(i).getHGDS();
                    } else {
                        JCXM += "," + listData.get(i).getXMMC();
                        JCFWZ += "。" + listData.get(i).getXMCS();
                        CSXMID += "," + listData.get(i).getGUID_OBJ();
                        LsHg += "," + listData.get(i).getISCHECK();
                        HGDS += "," + listData.get(i).getHGDS();
                    }
                }
                qualityInfo.setJCXM(JCXM);
                qualityInfo.setJCFWZ(JCFWZ);
                qualityInfo.setCSXMID(CSXMID);
                qualityInfo.setLsHg(LsHg);
                qualityInfo.setHGDS(HGDS);
                qualityInfo.setPIC(imgStr);
                int reture = curingDao.addQuality(qualityInfo);
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
                if (Utils.isNull(XmmcInfo.getGUID_OBJ())) {
                    MyApplication.app.customToast("请选择项目名称");
                    return;
                }
                if (Utils.isNull(FbgcInfo.getGUID_OBJ())) {
                    MyApplication.app.customToast("请选择分部工程");
                    return;
                }
                if (Utils.isNull(FxgcInfo.getGUID_OBJ())) {
                    MyApplication.app.customToast("请选择分项工程");
                    return;
                }
                String str = "正在加载...";
                showLoadingDialogMethod(str);
                addLog.setClickable(false);
                QualityJson qualityJson = new QualityJson();
                qualityJson.setJCFW(edJcfw.getText().toString());
                qualityJson.setXmmc(XmmcInfo.getGUID_OBJ());
                qualityJson.setFbgc(FbgcInfo.getGUID_OBJ());
                qualityJson.setFxgc(FxgcInfo.getGUID_OBJ());
                qualityJson.setGcbw(edCompletedbridge.getText().toString());
                qualityJson.setJcr(edIncompletebridge.getText().toString());
                qualityJson.setJcdw(MyApplication.spUtils.getString("dqgydwid"));
                qualityJson.setBhgsm(explainEt.getText().toString());
                qualityJson.setDlwz(isBridgeFw.getText().toString());
                QualityJson.JcxmListBean jcxmListBean = new QualityJson.JcxmListBean();
                String Guid = "";
                String JcxmList = "";
                String HgdsList = "";
                for (int i = 0; i < listData.size(); i++) {
                    if (i == 0) {
                        Guid = listData.get(i).getGUID_OBJ();
                        JcxmList = listData.get(i).getISCHECK();
                        HgdsList = listData.get(i).getHGDS();
                    } else {
                        Guid += "," + listData.get(i).getGUID_OBJ();
                        JcxmList += "," + listData.get(i).getISCHECK();
                        HgdsList += "," + listData.get(i).getHGDS();
                    }
                }
                jcxmListBean.setGuid(Guid);
                jcxmListBean.setLsHg(JcxmList);
                jcxmListBean.setHGDS(HgdsList);
                List<QualityJson.JcxmListBean> sssss = new ArrayList<>();
                sssss.add(jcxmListBean);
                qualityJson.setJcxmList(sssss);
                String imgStr = "";
                ArrayList<String> listImgUrl = qualityGredAdapter.getListImgUrl();
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
                        List<QualityJson.PICBean> PICBeanlist = new ArrayList<>();
                        for (int i = 0; i < strArr.length; i++) {
                            QualityJson.PICBean picBean = new QualityJson.PICBean();
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
                        qualityJson.setPIC(PICBeanlist);
                    }
                }
                String json = gson.toJson(qualityJson);
                Log.e("张成昆 ", json);
                mPresenter.upData(json);
            }
        });
        edProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XmmcPop.show(layRel);
            }
        });
        edCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNull(edProgress.getText().toString())) {
                    MyApplication.app.customToast("项目名称不能为空");
                } else {
                    FbgcPop.show(layRel);
                }
            }
        });
        edEvaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNull(edCheck.getText().toString()) || Utils.isNull(edProgress.getText().toString())) {
                    MyApplication.app.customToast("项目名称或分部工程不能为空");
                } else {
                    FxgcPop.show(layRel);
                }
            }
        });
    }

    private void initpop() {
        XmmcPop = new QualityPopupWindow(this, "请选择项目名称", XmmcResult, new DiseaseNewSelectObjectListener() {
            @Override
            public void selectPosition(int position) {
                edProgress.setText(XmmcResult.get(position));
                XmmcInfo = XmmcInfos.get(position);
                mPresenter.getFbgcData(XmmcInfo.getGUID_OBJ());
                if (locationClient == null) {
                    initLocation();
                }
                startLocation();
            }
        });
        FbgcPop = new QualityPopupWindow(this, "请选择分部工程", FbgcResult, new DiseaseNewSelectObjectListener() {
            @Override
            public void selectPosition(int position) {
                edCheck.setText(FbgcResult.get(position));
                FbgcInfo = FbgcInfos.get(position);
                mPresenter.getFxgcData(XmmcInfo.getGUID_OBJ(), FbgcInfo.getGUID_OBJ());
            }
        });
        FxgcPop = new QualityPopupWindow(this, "请选择分项工程", FxgcResult, new DiseaseNewSelectObjectListener() {
            @Override
            public void selectPosition(int position) {
                edEvaluate.setText(FxgcResult.get(position));
                FxgcInfo = FxgcInfos.get(position);
                getJcxm();
            }
        });
    }

    private void getJcxm() {
        mPresenter.getListData(MyApplication.spUtils.getString("dqgydwid"), XmmcInfo.getGUID_OBJ(), FxgcInfo.getGUID_OBJ());
    }

    private void initdata() {
        title.setText("现场采集");
        edIncompletebridge.setText(MyApplication.spUtils.getString("dlr"));
        mPresenter.getXmmcData();
        ArrayList<Drawable> listPicture = new ArrayList<>();
        ArrayList<String> listImgUrl = new ArrayList<>();
        Drawable addPicture = getResources().getDrawable(R.drawable.sjq_add_tupian_sel);
        listPicture.add(addPicture);
        final int childPosition = layRel.getChildCount();
        qualityGredAdapter = new QualityGredAdapter(this, listPicture, listImgUrl, childPosition);
        caiJiPictureAddGrid.setAdapter(qualityGredAdapter);
        mapAdapter.put(childPosition, qualityGredAdapter);
        layXm.setVisibility(View.GONE);
        listview.setVisibility(View.GONE);
        adapter = new CommonAdapter<ListDataBean.DATABean>(this,
                R.layout.lay, listData) {
            @Override
            protected void convert(final ViewHolder holder, ListDataBean.DATABean tubiaoVo, final int position) {
                holder.setText(R.id.one, tubiaoVo.getXMMC());
                holder.setText(R.id.two, tubiaoVo.getXMCS());
                RadioGroup rgsex = (RadioGroup) holder.getView(R.id.rg_sex);
                final RadioButton rbMale = (RadioButton) holder.getView(R.id.rb_Male);
                final RadioButton rbFeMale = (RadioButton) holder.getView(R.id.rb_FeMale);
                rgsex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if (rbMale.getId() == checkedId) {
                            listData.get(position).setISCHECK("1");
                        }
                        if (rbFeMale.getId() == checkedId) {
                            listData.get(position).setISCHECK("0");
                        }
                    }
                });
                final EditText et = (EditText) holder.getView(R.id.three);
                et.setTag(position);
                et.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        int positions = (int) et.getTag();//取tag值
                        listData.get(positions).setHGDS(s.toString());
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });
            }
        };
        listview.setAdapter(adapter);
    }

    @Override
    public void onRequestError(String msg) {

    }

    @Override
    public void onRequestEnd() {

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
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == CHOOSE_PICTURE_CODE) {//从相册获取照片
            if (intent != null) {
                ArrayList<String> listSelectPic = intent.getStringArrayListExtra("filelist");
                int position = intent.getIntExtra("position", -1);
                if (position != -1 && listSelectPic != null && listSelectPic.size() > 0) {
                    final QualityGredAdapter addPictureAdapter = mapAdapter.get(position);
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
                                    Bitmap bitmap = Tooklkit.getImageThumbnail(imgPath, Tooklkit.dip2px(QualityActivity.this, 480),
                                            Tooklkit.dip2px(QualityActivity.this, 480));
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
                    final QualityGredAdapter addPictureAdapter = mapAdapter.get(childViewPosition);
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
                                    Bitmap bitmap = Tooklkit.getImageThumbnail(imgPath, Tooklkit.dip2px(QualityActivity.this, 480),
                                            Tooklkit.dip2px(QualityActivity.this, 480));
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

    @Override
    public void getResult(QualityBean basebean) {
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
    public void getLocationResult(int basebean) {
        if (basebean == 1) {
            isBridgeFw.setText("在范围内");
        }
    }

    @Override
    public void getListData(List<ListDataBean.DATABean> ss) {
        listData.clear();
        for (int i = 0; i < ss.size(); i++) {
            ss.get(i).setISCHECK("1");
        }
        listData.addAll(ss);
        layXm.setVisibility(View.VISIBLE);
        listview.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getXmmcDatas(List<XmmcBean.DATABean> videoVos2) {
        if (videoVos2.size() > 0) {
            XmmcInfos = videoVos2;
            for (int i = 0; i < videoVos2.size(); i++) {
                XmmcResult.add(videoVos2.get(i).getXMMC());
            }
        }
        if (loadDataDialog != null && loadDataDialog.isShowing()) {
            loadDataDialog.cancel();
        }
    }

    @Override
    public void getFbgcDatas(List<FbgcBean.DATABean> videoVos2) {
        FbgcResult.clear();
        FbgcInfos.clear();
        layXm.setVisibility(View.GONE);
        listview.setVisibility(View.GONE);
        if (!Utils.isNull(edCheck.getText().toString())) {
            edCheck.setText("");
            edCheck.setHint("请选择分部工程");
            FbgcInfo = null;
        }
        if (!Utils.isNull(edEvaluate.getText().toString())) {
            edEvaluate.setText("");
            edEvaluate.setHint("请选择分项工程");
            FxgcInfo = null;
            FxgcResult.clear();
        }
        for (int i = 0; i < videoVos2.size(); i++) {
            FbgcResult.add(videoVos2.get(i).getXMMC());
            FbgcInfos.add(videoVos2.get(i));
        }
    }


    @Override
    public void getFxgcDatas(List<FxgcBean.DATABean> videoVos2) {
        FxgcResult.clear();
        FxgcInfos.clear();
        layXm.setVisibility(View.GONE);
        listview.setVisibility(View.GONE);
        if (!Utils.isNull(edEvaluate.getText().toString())) {
            edEvaluate.setText("");
            edEvaluate.setHint("请选择分项工程");
            FxgcInfo = null;
        }
        for (int i = 0; i < videoVos2.size(); i++) {
            FxgcResult.add(videoVos2.get(i).getXMMC());
            FxgcInfos.add(videoVos2.get(i));
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
                mPresenter.getLocation(XmmcInfo.getGUID_OBJ(), String.valueOf(longitude), String.valueOf(latitude));
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
        curingDao.release();
    }
}
