package com.tyiroad.tyiroad.quality.dbquality;


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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tooklkit.Tooklkit;
import com.tyiroad.tyiroad.MyApplication;
import com.tyiroad.tyiroad.R;
import com.tyiroad.tyiroad.db.dbhelper.CuringDaoImpl;
import com.tyiroad.tyiroad.db.dbhelper.dbInfo.QualityInfo;
import com.tyiroad.tyiroad.handle.LoadDataDialog;
import com.tyiroad.tyiroad.mvp.MVPBaseActivity;
import com.tyiroad.tyiroad.quality.ListDataBean;
import com.tyiroad.tyiroad.quality.NoScroolGridView;
import com.tyiroad.tyiroad.quality.QualityBean;
import com.tyiroad.tyiroad.quality.QualityJson;
import com.tyiroad.tyiroad.utils.DiseaseNewSelectObjectListener;
import com.tyiroad.tyiroad.utils.Utils;
import com.tyiroad.tyiroad.utils.adapter.CommonAdapter;
import com.tyiroad.tyiroad.utils.adapter.ViewHolder;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
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

public class DbQualityActivity extends MVPBaseActivity<DbQualityContract.View, DbQualityPresenter> implements DbQualityContract.View {


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
    private CuringDaoImpl curingDao;
    private QualityInfo DBQualityData;
    private CommonAdapter<ListDataBean.DATABean> adapter;
    private List<ListDataBean.DATABean> listData = new ArrayList<>();
    private ArrayList<String> listCSXMID = new ArrayList<>();
    private ArrayList<String> listJCXM = new ArrayList<>();
    private ArrayList<String> listJCFWZ = new ArrayList<>();
    private ArrayList<String> listLsHg = new ArrayList<>();
    private int childViewPosition;//拍照获取图片的地址
    private String cameraPath;//拍照获取图片的地址
    private LoadDataDialog loadDataDialog;

    public void setChildViewPosition(int childViewPosition) {
        this.childViewPosition = childViewPosition;
    }

    public void setCameraPath(String cameraPath) {
        this.cameraPath = cameraPath;
    }

    private ArrayList<String> XmmcResult = new ArrayList<>();
    private ArrayList<String> FxgcResult = new ArrayList<>();
    private ArrayList<String> FbgcResult = new ArrayList<>();
    private DbQualityPopupWindow XmmcPop, FbgcPop, FxgcPop;
    private List<QualityBean.DATABean.XmmcBean> XmmcInfos = new ArrayList<>();
    private QualityBean.DATABean.XmmcBean XmmcInfo;
    private List<QualityBean.DATABean.FbgcBean> FbgcInfos = new ArrayList<>();
    private QualityBean.DATABean.FbgcBean FbgcInfo;
    private List<QualityBean.DATABean.FxgcBean> FxgcInfos = new ArrayList<>();
    private QualityBean.DATABean.FxgcBean FxgcInfo;
    private QualityBean.DATABean Data;
    private final int CHOOSE_PICTURE_CODE = 1;
    private final int CAMERA_CODE = 2;
    private Gson gson = new Gson();
    private Map<Integer, DbQualityGredAdapter> mapAdapter = new HashMap<>();
    private DbQualityGredAdapter qualityGredAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setStatusBarColor(this, R.color.theme_color);
        setContentView(R.layout.act_quality);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        ButterKnife.bind(this);
        curingDao = new CuringDaoImpl(this);
        setadapter();
        initdata();
        initlisener();

    }

    private void setadapter() {
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
                if (!Utils.isNull(tubiaoVo.getISCHECK())) {
                    if (tubiaoVo.getISCHECK().equals("1")) {
                        rbMale.setChecked(true);
                        rbFeMale.setChecked(false);
                    } else if (tubiaoVo.getISCHECK().equals("0")) {
                        rbMale.setChecked(false);
                        rbFeMale.setChecked(true);
                    }
                }
            }
        };
        listview.setAdapter(adapter);
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
                for (int i = 0; i < listData.size(); i++) {
                    if (i == 0) {
                        JCXM = listData.get(i).getXMMC();
                        JCFWZ = listData.get(i).getXMCS();
                        CSXMID = listData.get(i).getGUID_OBJ();
                        LsHg = listData.get(i).getISCHECK();
                    } else {
                        JCXM += "," + listData.get(i).getXMMC();
                        JCFWZ += "。" + listData.get(i).getXMCS();
                        CSXMID += "," + listData.get(i).getGUID_OBJ();
                        LsHg += "," + listData.get(i).getISCHECK();
                    }
                }
                qualityInfo.setJCXM(JCXM);
                qualityInfo.setJCFWZ(JCFWZ);
                qualityInfo.setCSXMID(CSXMID);
                qualityInfo.setLsHg(LsHg);
                qualityInfo.setPIC(imgStr);
                qualityInfo.setQUID(Integer.valueOf(getIntent().getStringExtra("MOID")).intValue());
                int reture = curingDao.updateQuality(qualityInfo);
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
                qualityJson.setXmmc(XmmcInfo.getGUID_OBJ());
                qualityJson.setFbgc(FbgcInfo.getGUID_OBJ());
                qualityJson.setFxgc(FxgcInfo.getGUID_OBJ());
                qualityJson.setGcbw(edCompletedbridge.getText().toString());
                qualityJson.setJcr(edIncompletebridge.getText().toString());
                qualityJson.setJcdw(MyApplication.spUtils.getString("dqgydwid"));
                qualityJson.setBhgsm(explainEt.getText().toString());
                qualityJson.setDlwz(isBridgeFw.getText().toString());//地理位置
                QualityJson.JcxmListBean jcxmListBean = new QualityJson.JcxmListBean();
                String Guid = "";
                String JcxmList = "";
                for (int i = 0; i < listData.size(); i++) {
                    if (i == 0) {
                        Guid = listData.get(i).getGUID_OBJ();
                        JcxmList = listData.get(i).getISCHECK();
                    } else {
                        Guid += "," + listData.get(i).getGUID_OBJ();
                        JcxmList += "," + listData.get(i).getISCHECK();
                    }
                }
                jcxmListBean.setGuid(Guid);
                jcxmListBean.setLsHg(JcxmList);
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
                            picBean.setPicDataBlob(strBlob);
                            PICBeanlist.add(picBean);
                        }
                        qualityJson.setPIC(PICBeanlist);
                    }
                }
                String json = gson.toJson(qualityJson);
                Log.e("张成昆",json );
                if (loadDataDialog != null && loadDataDialog.isShowing()) {
                    loadDataDialog.cancel();
                }
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
                } else if (FbgcInfos.size()==0){
                    MyApplication.app.customToast("请重新选择项目名称");
                }else {
                    FbgcPop.show(layRel);
                }
            }
        });
        edEvaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNull(edCheck.getText().toString()) || Utils.isNull(edProgress.getText().toString())) {
                    MyApplication.app.customToast("项目名称或分部工程不能为空");
                }  else if (FbgcInfos.size()==0||FxgcInfos.size()==0){
                    MyApplication.app.customToast("请重新选择项目名称");
                }else {
                    FxgcPop.show(layRel);
                }
            }
        });
    }

    private void showLoadingDialogMethod(String str) {
        if (loadDataDialog == null) {
            loadDataDialog = new LoadDataDialog(this);
        }
        loadDataDialog.setTitleStr(str);
        loadDataDialog.show();
    }


    private void initdata() {
        title.setText("质量检验");
        edIncompletebridge.setText(MyApplication.spUtils.getString("dlr"));
        mPresenter.getData(MyApplication.spUtils.getString("dqgydwid"));
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

    @Override
    public void onRequestError(String msg) {

    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void getData(List<QualityBean.DATABean> videoVos2) {
        if (videoVos2.size() > 0) {
            Data = videoVos2.get(0);
            if (Data.getXmmc().size() > 0) {
                XmmcInfos = Data.getXmmc();
            }
            for (int i = 0; i < Data.getXmmc().size(); i++) {
                XmmcResult.add(Data.getXmmc().get(i).getXMMC());
            }
        }
        Integer sss = Integer.valueOf(getIntent().getStringExtra("MOID")).intValue();
        DBQualityData = curingDao.queryQuality(sss);
        edProgress.setText(DBQualityData.getXmmc());
        edCheck.setText(DBQualityData.getFbgc());
        edEvaluate.setText(DBQualityData.getFxgc());
        edCompletedbridge.setText(DBQualityData.getGcbw());
        edIncompletebridge.setText(DBQualityData.getJcr());
        explainEt.setText(DBQualityData.getBhgsm());
        listJCXM.addAll(Arrays.asList(DBQualityData.getJCXM().split(",")));
        listJCFWZ.addAll(Arrays.asList(DBQualityData.getJCFWZ().split("。")));
        listCSXMID.addAll(Arrays.asList(DBQualityData.getCSXMID().split(",")));
        listLsHg.addAll(Arrays.asList(DBQualityData.getLsHg().split(",")));
        for (int i = 0; i < listCSXMID.size(); i++) {
            ListDataBean.DATABean jcxmBean = new ListDataBean.DATABean();
            jcxmBean.setXMMC(listJCXM.get(i));
            jcxmBean.setXMCS(listJCFWZ.get(i));
            jcxmBean.setGUID_OBJ(listCSXMID.get(i));
            jcxmBean.setISCHECK(listLsHg.get(i));
            listData.add(jcxmBean);
        }
        adapter.notifyDataSetChanged();
        for (int i = 0; i < Data.getXmmc().size(); i++) {
            if (Data.getXmmc().get(i).getGUID_OBJ().equals(DBQualityData.getXmmcID())) {
                XmmcInfo = Data.getXmmc().get(i);
            }
        }
        for (int i = 0; i < Data.getFbgc().size(); i++) {
            if (Data.getFbgc().get(i).getGUID_OBJ().equals(DBQualityData.getFbgcID())) {
                FbgcInfo = Data.getFbgc().get(i);
            }
        }
        for (int i = 0; i < Data.getFxgc().size(); i++) {
            if (Data.getFxgc().get(i).getGUID_OBJ().equals(DBQualityData.getFxgcID())) {
                FxgcInfo = Data.getFxgc().get(i);
            }
        }
        if (XmmcInfo != null) {
            for (int i = 0; i < Data.getFbgc().size(); i++) {
                if (Data.getFbgc().get(i).getGCXMID().equals(XmmcInfo.getGUID_OBJ())) {
                    FbgcResult.add(Data.getFbgc().get(i).getXMMC());
                }
            }
        }
        if (FbgcInfo!=null){
            for (int i = 0; i < Data.getFxgc().size(); i++) {
                if (Data.getFxgc().get(i).getSJXMGUID().equals(FbgcInfo.getGUID_OBJ())) {
                    if (Data.getFxgc().get(i).getGCXMID().equals(XmmcInfo.getGUID_OBJ())){
                        FxgcResult.add(Data.getFxgc().get(i).getXMMC());
                    }
                }
            }
        }
        ArrayList<Drawable> listPicture = new ArrayList<>();
        ArrayList<String> listImgUrl = new ArrayList<>();
        if (!Utils.isNull(DBQualityData.getPIC())) {
            listImgUrl.addAll(Arrays.asList(DBQualityData.getPIC().split(",")));
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
        final int childPosition = layRel.getChildCount();
        qualityGredAdapter = new DbQualityGredAdapter(this, listPicture, listImgUrl, childPosition);
        caiJiPictureAddGrid.setAdapter(qualityGredAdapter);
        mapAdapter.put(childPosition, qualityGredAdapter);
        initpop();
        if (loadDataDialog != null && loadDataDialog.isShowing()) {
            loadDataDialog.cancel();
        }
    }

    @Override
    public void getResult(QualityBean basebean) {
        curingDao.deleteQualityId(Integer.valueOf(getIntent().getStringExtra("MOID")).intValue());
        MyApplication.app.customToast("上传成功");
        Intent intent = new Intent();
        setResult(2, intent);
        finish();
        addLog.setClickable(true);
        if (loadDataDialog != null && loadDataDialog.isShowing()) {
            loadDataDialog.cancel();
        }
    }

    private void initpop() {
        XmmcPop = new DbQualityPopupWindow(this, "请选择项目名称", XmmcResult, new DiseaseNewSelectObjectListener() {
            @Override
            public void selectPosition(int position) {
                edProgress.setText(XmmcResult.get(position));
                XmmcInfo = XmmcInfos.get(position);
                getFbgc();
            }
        });
        FbgcPop = new DbQualityPopupWindow(this, "请选择分部工程", FbgcResult, new DiseaseNewSelectObjectListener() {
            @Override
            public void selectPosition(int position) {
                edCheck.setText(FbgcResult.get(position));
                FbgcInfo = FbgcInfos.get(position);
                getFxgc();
            }
        });
        FxgcPop = new DbQualityPopupWindow(this, "请选择分项工程", FxgcResult, new DiseaseNewSelectObjectListener() {
            @Override
            public void selectPosition(int position) {
                edEvaluate.setText(FxgcResult.get(position));
                FxgcInfo = FxgcInfos.get(position);
                getJcxm();
            }
        });
    }

    private void getFbgc() {
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
        Log.e("张成昆",""+Data.getFbgc().size() );
        for (int i = 0; i < Data.getFbgc().size(); i++) {
            if (Data.getFbgc().get(i).getGCXMID().equals(XmmcInfo.getGUID_OBJ())) {
                FbgcResult.add(Data.getFbgc().get(i).getXMMC());
                FbgcInfos.add(Data.getFbgc().get(i));
            }
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

    private void getJcxm() {
        mPresenter.getListData(MyApplication.spUtils.getString("dqgydwid"), XmmcInfo.getGUID_OBJ(), FxgcInfo.getGUID_OBJ());
    }

    private void getFxgc() {
        FxgcResult.clear();
        FxgcInfos.clear();
        layXm.setVisibility(View.GONE);
        listview.setVisibility(View.GONE);
        if (!Utils.isNull(edEvaluate.getText().toString())) {
            edEvaluate.setText("");
            edEvaluate.setHint("请选择分项工程");
            FxgcInfo = null;
        }
        for (int i = 0; i < Data.getFxgc().size(); i++) {
            if (Data.getFxgc().get(i).getSJXMGUID().equals(FbgcInfo.getGUID_OBJ())) {
                if (Data.getFxgc().get(i).getGCXMID().equals(XmmcInfo.getGUID_OBJ())) {
                    FxgcResult.add(Data.getFxgc().get(i).getXMMC());
                    FxgcInfos.add(Data.getFxgc().get(i));
                }
            }
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
                    final DbQualityGredAdapter addPictureAdapter = mapAdapter.get(position);
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
                                    Bitmap bitmap = Tooklkit.getImageThumbnail(imgPath, Tooklkit.dip2px(DbQualityActivity.this, 480),
                                            Tooklkit.dip2px(DbQualityActivity.this, 480));
                                    if (bitmap != null) {
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
                                }
                            }).launch();    //启动压缩
                }
            }
        } else if (requestCode == CAMERA_CODE) {//拍照获取照片
            if (resultCode == -1) {
                if (!Utils.isNull(cameraPath) && childViewPosition != -1) {
                    Utils.dealBitmapRotate(cameraPath);
                    final DbQualityGredAdapter addPictureAdapter = mapAdapter.get(childViewPosition);
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
                                    Bitmap bitmap = Tooklkit.getImageThumbnail(imgPath, Tooklkit.dip2px(DbQualityActivity.this, 480),
                                            Tooklkit.dip2px(DbQualityActivity.this, 480));
                                    if (bitmap != null) {
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
                                }
                            }).launch();    //启动压缩
                }
            }
        }
    }
}
