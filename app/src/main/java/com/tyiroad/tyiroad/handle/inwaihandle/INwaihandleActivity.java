package com.tyiroad.tyiroad.handle.inwaihandle;


import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bumptech.glide.Glide;
import com.example.logic.ImgFileListActivity;
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
import com.tyiroad.tyiroad.Bean.Dingbean;
import com.tyiroad.tyiroad.Bean.ItemSgjdInfo;
import com.tyiroad.tyiroad.Bean.addhandlejson;
import com.tyiroad.tyiroad.Bean.waiHandleItembean;
import com.tyiroad.tyiroad.MyApplication;
import com.tyiroad.tyiroad.R;
import com.tyiroad.tyiroad.check.sgjdAdapter;
import com.tyiroad.tyiroad.db.dbhelper.CuringDaoImpl;
import com.tyiroad.tyiroad.db.dbhelper.dbInfo.ConstructionInfo;
import com.tyiroad.tyiroad.db.dbhelper.dbInfo.ConstructionUploadInfo;
import com.tyiroad.tyiroad.disease.MinePopupWindow;
import com.tyiroad.tyiroad.disease.SendTopicPictureGridAdapter;
import com.tyiroad.tyiroad.handle.LoadDataDialog;
import com.tyiroad.tyiroad.kdxf.DictationResult;
import com.tyiroad.tyiroad.mvp.MVPBaseActivity;
import com.tyiroad.tyiroad.utils.ListViewForScrollView;
import com.tyiroad.tyiroad.utils.NoScroolGridView;
import com.tyiroad.tyiroad.utils.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class INwaihandleActivity extends MVPBaseActivity<INwaihandleContract.View, INwaihandlePresenter> implements INwaihandleContract.View {
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    @Bind(R.id.go_back)
    LinearLayout goBack;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.right)
    TextView right;
    @Bind(R.id.disease_detail_unit_name_txt)
    TextView diseaseDetailUnitNameTxt;
    @Bind(R.id.disease_detail_road_line_txt)
    TextView diseaseDetailRoadLineTxt;
    @Bind(R.id.disease_detail_time_txt)
    TextView diseaseDetailTimeTxt;
    @Bind(R.id.disease_detail_to_examine_type_txt)
    TextView diseaseDetailToExamineTypeTxt;
    @Bind(R.id.disease_new_pile_number_k)
    TextView diseaseNewPileNumberK;
    @Bind(R.id.disease_new_pile_number_one_edit)
    EditText diseaseNewPileNumberOneEdit;
    @Bind(R.id.disease_new_pile_number_jia)
    TextView diseaseNewPileNumberJia;
    @Bind(R.id.disease_new_pile_number_two_edit)
    EditText diseaseNewPileNumberTwoEdit;
    @Bind(R.id.disease_new_pile_number_location_txt)
    TextView diseaseNewPileNumberLocationTxt;
    @Bind(R.id.disease_detail_driver_direction_txt)
    TextView diseaseDetailDriverDirectionTxt;
    @Bind(R.id.disease_custom_item_bhmc_txt)
    TextView diseaseCustomItemBhmcTxt;
    @Bind(R.id.disease_custom_item_bh_txt)
    TextView diseaseCustomItemBhTxt;
    @Bind(R.id.add_ddisease_list_view)
    ListViewForScrollView addDdiseaseListView;
    @Bind(R.id.cai_ji_picture_add_grid)
    NoScroolGridView caiJiPictureAddGrid;
    @Bind(R.id.shigongqian)
    ImageView shigongqian;
    @Bind(R.id.shigongqiande)
    ImageView shigongqiande;
    @Bind(R.id.shigonghou)
    ImageView shigonghou;
    @Bind(R.id.shigonghoude)
    ImageView shigonghoude;
    @Bind(R.id.activity_disease_detail_scrollview)
    ScrollView activityDiseaseDetailScrollview;
    @Bind(R.id.save)
    TextView save;
    @Bind(R.id.add_log)
    TextView addLog;
    @Bind(R.id.btn_close_soft)
    TextView btnCloseSoft;
    @Bind(R.id.layout_pop_soft)
    LinearLayout layoutPopSoft;
    @Bind(R.id.disease_custom_item_bhlx_txt)
    TextView diseaseCustomItemBhlxTxt;
    @Bind(R.id.czwt_et)
    EditText czwtet;
    @Bind(R.id.czwt_bu)
    TextView czwtBu;
    @Bind(R.id.shigongzhong)
    ImageView shigongzhong;
    @Bind(R.id.shigongzhongde)
    ImageView shigongzhongde;
    private final int CHOOSE_PICTURE_CODE = 1;
    private final int CAMERA_CODE = 2;
    private CuringDaoImpl curingDao;
    private ConstructionInfo indate;
    private List<String> BHimagelist = new ArrayList<>();
    private ArrayList<ItemSgjdInfo> diseaselist = new ArrayList<>();
    ;private int type;
    private List<String> GCXMlist = new ArrayList<>();
    private List<String> JSGSlist = new ArrayList<>();
    private List<String> SLlist = new ArrayList<>();
    private List<String> GCXMIDlist = new ArrayList<>();
    private List<String> DWlist = new ArrayList<>();
    private List<String> HDlist = new ArrayList<>();
    private sgjdAdapter adapter;
    private MinePopupWindow minePopupWindow;
    private Gson gson = new Gson();
    private LoadDataDialog loadDataDialog;
    private String gydwid = MyApplication.app.spUtils.getString("dqgydwid");
    public void setCameraPath(String cameraPath) {
        this.cameraPath = cameraPath;
    }
    private String cameraPath;
    private int childViewPosition;
    private String SGQTP = "";
    private boolean shigongqianyesbig = false;
    private  ArrayList<String> sGQTP = new ArrayList<>();

    private String SGZTP = "";
    private boolean shigongzhongyesbig = false;
    private  ArrayList<String> sGZTP = new ArrayList<>();
    private String dictationResultStr;
    private static String APPID = "5bf211f5";
    private List<String> xf = new ArrayList<>();
    private List<String> zong = new ArrayList<>();
    private String SGHTP = "";
    private boolean shigonghoubig = false;
    private  ArrayList<String> sGHTP = new ArrayList<>();
    public void setChildViewPosition(int childViewPosition) {
        this.childViewPosition = childViewPosition;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setStatusBarColor(this, R.color.theme_color);
        setContentView(R.layout.act_waihandle_in_xq);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        ButterKnife.bind(this);
        initdate();
        linstener();
    }

    private void linstener() {
        diseaseNewPileNumberLocationTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(locationClient==null){
                    initLocation();
                }
                startLocation();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savebd();
            }
        });
        shigongqian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shigongqianyesbig == false) {
                    type = 1;
                    if (minePopupWindow == null) {
                        minePopupWindow = new MinePopupWindow(INwaihandleActivity.this, itemOnClick);
                    }
                    minePopupWindow.showAtLocation(activityDiseaseDetailScrollview, Gravity.BOTTOM, 0, 0);
                } else {
                    sGQTP.clear();
                    sGQTP.add(SGQTP);
                    sGQTP.add(SGQTP);
                    Intent intent = new Intent(INwaihandleActivity.this, ShowImgActivity.class);
                    intent.putExtra("img2", sGQTP);
                    intent.putExtra("position", 0);
                    startActivity(intent);
                }
            }
        });
        shigongzhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shigongzhongyesbig == false) {
                    type = 3;
                    if (minePopupWindow == null) {
                        minePopupWindow = new MinePopupWindow(INwaihandleActivity.this, itemOnClick);
                    }
                    minePopupWindow.showAtLocation(activityDiseaseDetailScrollview, Gravity.BOTTOM, 0, 0);
                } else {
                    sGZTP.clear();
                    sGZTP.add(SGZTP);
                    Intent intent = new Intent(INwaihandleActivity.this, ShowImgActivity.class);
                    intent.putExtra("img2", sGZTP);
                    intent.putExtra("position", 0);
                    startActivity(intent);
                }
            }
        });
        shigonghou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shigonghoubig ==false){
                    type = 2;
                    if (minePopupWindow == null) {
                        minePopupWindow = new MinePopupWindow(INwaihandleActivity.this, itemOnClick);
                    }
                    minePopupWindow.showAtLocation(activityDiseaseDetailScrollview, Gravity.BOTTOM, 0, 0);
                }else {
                    sGHTP.clear();
                    sGHTP.add(SGHTP);
                    Intent intent = new Intent(INwaihandleActivity.this, ShowImgActivity.class);
                    intent.putExtra("img2", sGHTP);
                    intent.putExtra("position", 0);
                    startActivity(intent);
                }
            }
        });
        shigongqiande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(INwaihandleActivity.this)
                        .asBitmap()
                        .apply(MyApplication.app.options)
                        .load(R.drawable.morentu)
                        .into(shigongqian);
                shigongqiande.setVisibility(View.GONE);
                shigongqianyesbig =false;
                SGQTP = "";
            }
        });
        shigonghoude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(INwaihandleActivity.this)
                        .asBitmap()
                        .apply(MyApplication.app.options)
                        .load(R.drawable.morentu)
                        .into(shigonghou);
                shigonghoude.setVisibility(View.GONE);
                shigonghoubig =false;
                SGHTP = "";
            }
        });
        shigongzhongde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(INwaihandleActivity.this)
                        .asBitmap()
                        .apply(MyApplication.app.options)
                        .load(R.drawable.morentu)
                        .into(shigongzhong);
                shigongzhongde.setVisibility(View.GONE);
                shigongzhongyesbig =false;
                SGZTP = "";
            }
        });
        czwtBu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getkdxf(czwtet);
            }
        });
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        addLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNetworkAvailable(INwaihandleActivity.this)) {
                    int result=isSaveOk();
                    if(result==1){
                        MyApplication.app.customToast("请上传施工前图片");
                    }else if(result==2){
                        MyApplication.app.customToast("请上传施工后图片");
                    }else if(result==3){
                        MyApplication.app.customToast("请上传施工中图片");
                    }else{
                        addLog.setClickable(false);
                        showLoadingDataDialog();
                        addhandlejson addhandlejson = new addhandlejson();
                        addhandlejson.setBHGUID(indate.getBHID());
                        addhandlejson.setQDZH(replaceNull(diseaseNewPileNumberOneEdit.getText() + "." + diseaseNewPileNumberTwoEdit.getText()));
                        addhandlejson.setJLDW(MyApplication.app.spUtils.getString("dqgydwid"));
                        addhandlejson.setSGDW(MyApplication.app.spUtils.getString("dqgydwid"));
                        addhandlejson.setSGFZR(indate.getDCR());
                        addhandlejson.setBZ(czwtet.getText().toString());
                        ArrayList<addhandlejson.PICBean> listPic = new ArrayList<>();
                        if (!Utils.isNull(SGQTP)) {
                            addhandlejson.PICBean picInfo = new addhandlejson.PICBean();
                            String nameStr = SGQTP.substring(SGQTP.lastIndexOf("/") + 1, SGQTP.length());
                            String typeStr = SGQTP.substring(SGQTP.lastIndexOf(".") + 1, SGQTP.length());
                            picInfo.setPicFileName(nameStr);
                            picInfo.setPicFileType(typeStr);
                            String strBlob = Utils.bmpToBase64String(SGQTP);
                            Log.i("图片提交的二进制流", "=====" + strBlob);
                            picInfo.setPicDataBlob(strBlob);
                            picInfo.setWJLX("0");
                            listPic.add(picInfo);
                        }
                        if (!Utils.isNull(SGZTP)) {
                            addhandlejson.PICBean picInfo = new addhandlejson.PICBean();
                            String nameStr = SGZTP.substring(SGZTP.lastIndexOf("/") + 1, SGZTP.length());
                            String typeStr = SGZTP.substring(SGZTP.lastIndexOf(".") + 1, SGZTP.length());
                            picInfo.setPicFileName(nameStr);
                            picInfo.setPicFileType(typeStr);
                            String strBlob = Utils.bmpToBase64String(SGZTP);
                            Log.i("图片提交的二进制流", "=====" + strBlob);
                            picInfo.setPicDataBlob(strBlob);
                            picInfo.setWJLX("1");
                            listPic.add(picInfo);
                        }
                        if (!Utils.isNull(SGHTP)) {
                            addhandlejson.PICBean picInfo = new addhandlejson.PICBean();
                            String nameStr = SGHTP.substring(SGHTP.lastIndexOf("/") + 1, SGHTP.length());
                            String typeStr = SGHTP.substring(SGHTP.lastIndexOf(".") + 1, SGHTP.length());
                            picInfo.setPicFileName(nameStr);
                            picInfo.setPicFileType(typeStr);
                            String strBlob = Utils.bmpToBase64String(SGHTP);
                            Log.i("图片提交的二进制流", "=====" + strBlob);
                            picInfo.setPicDataBlob(strBlob);
                            picInfo.setWJLX("2");
                            listPic.add(picInfo);
                        }
                        addhandlejson.setPIC(listPic);
                        ArrayList<addhandlejson.CZFABean> listCzr = new ArrayList<>();
                        for (int i =0;i<adapter.get().size();i++){
                            addhandlejson.CZFABean   czfa =         new  addhandlejson.CZFABean();
                            czfa.setGCXMID(adapter.get().get(i).getGCXMID());
                            czfa.setHD(adapter.get().get(i).getHD());
                            czfa.setJSGS(adapter.get().get(i).getJSGS());
                            listCzr.add(czfa);
                        }
                        addhandlejson.setCZFA(listCzr);
                        ArrayList<addhandlejson>  datelist =  new ArrayList<>();
                        datelist.add(addhandlejson);
                        String json = gson.toJson(datelist);

                        mPresenter.add(json);
                    }
                } else {
                    LayoutInflater factory = LayoutInflater.from(INwaihandleActivity.this);//提示框
                    final View views = factory.inflate(R.layout.sgbdxqdialog, null);//这里必须是final的
                    TextView cancle = (TextView) views.findViewById(R.id.off_commit);
                    TextView commit = (TextView) views.findViewById(R.id.commit);
                    cancle.setText("取消");
                    commit.setText("确定");
                    final AlertDialog dialog = new AlertDialog.Builder(INwaihandleActivity.this).create();
                    cancle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                    commit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            savebd();
                            dialog.dismiss();
                        }
                    });
                    dialog.setView(views);
                    dialog.show();
                }
            }
        });
    }

    private void savebd() {
        int result=isSaveOk();
        if(result==1){
            MyApplication.app.customToast("请上传施工前图片");
        }else if(result==2){
            MyApplication.app.customToast("请上传施工后的图片");
        }else if(result==3){
            MyApplication.app.customToast("请上传施工中的图片");
        }else {
            save.setClickable(false);
            showLoadingDataDialog();
            String jsgs = "";
            String hd = "";
            ArrayList<ItemSgjdInfo> listjsgs = adapter.get();
            if (listjsgs != null && listjsgs.size() > 0) {
                for (int k = 0; k < listjsgs.size(); k++) {
                    if (k == 0) {
                        jsgs += replace0(listjsgs.get(k).getJSGS());
                        hd += replace0(listjsgs.get(k).getHD());
                    } else {
                        jsgs += "," + replace0(listjsgs.get(k).getJSGS());
                        hd+= "," + replace0(listjsgs.get(k).getHD());
                    }
                }
            }
            ConstructionUploadInfo constructionInfo = new ConstructionUploadInfo();
            constructionInfo.setDCR(replaceNull(indate.getDCR()));
            constructionInfo.setLXBM(replaceNull(indate.getLXBM()));
            constructionInfo.setDCSJ(replaceNull(indate.getDCSJ()));
            constructionInfo.setDCLX(replaceNull(indate.getDCLX()));
            constructionInfo.setQDZH(replaceNull(diseaseNewPileNumberOneEdit.getText() + "." + diseaseNewPileNumberTwoEdit.getText()));
            constructionInfo.setWZFX(replaceNull(indate.getWZFX()));
            constructionInfo.setTPDZ(replaceNull(indate.getTPDZ()));
            constructionInfo.setBHLX(replaceNull(indate.getBHLX()));
            constructionInfo.setBHLXID(replaceNull(indate.getBHLXID()));
            constructionInfo.setBHMC(replaceNull(indate.getBHMC()));
            constructionInfo.setSGMX(replaceNull(czwtet.getText().toString()));
            constructionInfo.setSGQTP(replaceNull(SGQTP));
            constructionInfo.setSGHTP(replaceNull(SGHTP));
            constructionInfo.setSGZTP(replaceNull(SGZTP));
            constructionInfo.setCZFAMC(replaceNull(indate.getCZFAMC()));
            constructionInfo.setGCXM(replaceNull(indate.getGCXM()));
            constructionInfo.setGCXMID(replaceNull(indate.getGCXMID()));
            constructionInfo.setJSGS(replaceNull(jsgs));
            constructionInfo.setDW(replaceNull(indate.getDW()));
            constructionInfo.setHD(replaceNull(hd));
            constructionInfo.setSL(replaceNull(indate.getSL()));
            constructionInfo.setBHID(replaceNull(indate.getBHID()));//病害id
            int s = curingDao.addSgwxUpload(constructionInfo);
            if (s != 0) {//ConstructionInfo
                int ss = curingDao.deleteSgwxById(indate.getSGWXID());
                save.setClickable(true);
                if (loadDataDialog.isShowing()) {
                    loadDataDialog.cancel();
                }
                if (ss != 0) {
                    MyApplication.app.customToast("保存本地成功");
                    Intent intent = new Intent();
                    setResult(2, intent);
                    finish();
                }
            }
        }
    }
    public static String replace0(String str) {
        if (str.equals("") || str == null) {
            return "0";
        }
        return str;
    }
    private int isSaveOk(){
        int result=5;
        if(Utils.isNull(SGQTP)){
            result=1;
        }
        if(Utils.isNull(SGHTP)){
            result=2;
        }
        if(Utils.isNull(SGZTP)){
            result=3;
        }
        return result;
    }
    private void initdate() {
        title.setText("施工明细");
        curingDao = new CuringDaoImpl(this);
        indate = curingDao.querySgwxByBhid(getIntent().getStringExtra("BHID"));
        diseaseDetailUnitNameTxt.setText(replaceNull(indate.getDCR()));
        diseaseDetailRoadLineTxt.setText(replaceNull(indate.getLXBM()));
        diseaseDetailTimeTxt.setText(replaceNull(indate.getDCSJ().replace("T"," ")));
        diseaseDetailToExamineTypeTxt.setText(replaceNull(indate.getDCLX()));
        if (indate.getQDZH() != null) {
            String zhfStr = indate.getQDZH().substring(0, indate.getQDZH().indexOf("."));
            String zhaStr = indate.getQDZH().substring(indate.getQDZH().indexOf(".") + 1, indate.getQDZH().length());
            diseaseNewPileNumberOneEdit.setText(zhfStr);
            diseaseNewPileNumberTwoEdit.setText(zhaStr);
        }
        diseaseDetailDriverDirectionTxt.setText(replaceNull(indate.getWZFX()));
        diseaseCustomItemBhlxTxt.setText(replaceNull(indate.getBHLX()));
        diseaseCustomItemBhmcTxt.setText(replaceNull(indate.getBHMC()));
        diseaseCustomItemBhTxt.setText(replaceNull(indate.getCZFAMC()));
//        diseaseCustomItemBhTxt.setText(replaceNull(indate.getSGMX()));
        if (indate.getTPDZ() != null) {
            if (indate.getTPDZ().contains(",") == true) {
                String str2 = indate.getTPDZ().replace(" ", "");
                BHimagelist = Arrays.asList(str2.split(","));
            } else {
                BHimagelist.add(indate.getTPDZ());
            }
        }
        ArrayList<String> listImgUrl = new ArrayList<>();
        if (BHimagelist.size() == 0) {
            listImgUrl.add(String.valueOf(R.drawable.morentu));
        } else {
            listImgUrl.addAll(BHimagelist);
        }
        SendTopicPictureGridAdapter addPictureAdapter = new SendTopicPictureGridAdapter(this, listImgUrl);
        caiJiPictureAddGrid.setAdapter(addPictureAdapter);
        if (indate.getGCXM().indexOf(",") != -1) {
            String GCXM = indate.getGCXM().replace(" ", "");
            GCXMlist = Arrays.asList(GCXM.split(","));//名称
            String JSGS = indate.getJSGS().replace(" ", "");
            JSGSlist = Arrays.asList(JSGS.split(","));//名称
            String DW = indate.getDW().replace(" ", "");
            DWlist = Arrays.asList(DW.split(","));//名称
            String GCXMID = indate.getGCXMID().replace(" ", "");
            GCXMIDlist = Arrays.asList(GCXMID.split(","));//名称
            String SL = indate.getSL().replace(" ", "");
            SLlist = Arrays.asList(SL.split(","));//名称
            String HD = indate.getHD().replace(" ", "");
            HDlist = Arrays.asList(HD.split(","));//名称
        } else {
            GCXMlist.add(indate.getGCXM());
            GCXMIDlist.add(indate.getGCXMID());
            JSGSlist.add(indate.getJSGS());
            DWlist.add(indate.getDW());
            SLlist.add(indate.getSL());
            HDlist.add(indate.getHD());
        }
        for (int i = 0; i < GCXMlist.size(); i++) {
            ItemSgjdInfo ItemBeanInfo = new ItemSgjdInfo();
            ItemBeanInfo.setGCXM(replaceNull(GCXMlist.get(i)));
            ItemBeanInfo.setJSGS(replaceNull(JSGSlist.get(i)));
            ItemBeanInfo.setDW(replaceNull(DWlist.get(i)));
            ItemBeanInfo.setGCXMID(GCXMIDlist.get(i));
            ItemBeanInfo.setSL(SLlist.get(i));
            ItemBeanInfo.setHD(HDlist.get(i));
            diseaselist.add(ItemBeanInfo);
        }
        adapter = new sgjdAdapter(this, diseaselist);
        addDdiseaseListView.setAdapter(adapter);

    }

    public static String replaceNull(String str) {
        if (str == null || str.equals("null")) {
            return "";
        }
        return str;
    }
    private View.OnClickListener itemOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.mine_camera_btn:
                    if (minePopupWindow.isShowing()) {
                        minePopupWindow.dismiss();
                    }
                    getPicFromCamera();
                    break;
                case R.id.mine_photo_btn:
                    if (minePopupWindow.isShowing()) {
                        minePopupWindow.dismiss();
                    }
                    getPicFromPhoto();
                    break;
                case R.id.mine_cancel_btn:
                    if (minePopupWindow.isShowing()) {
                        minePopupWindow.dismiss();
                    }
                    break;
                default:
                    break;
            }
        }
    };
    public void getPicFromPhoto() {
        Intent intent = new Intent(this, ImgFileListActivity.class);
        intent.putExtra("position", childViewPosition);
        intent.putExtra("isLimitedNumber",true);
        intent.putExtra("maxsize",1);
        startActivityForResult(intent, CHOOSE_PICTURE_CODE);
    }

    /**
     * 照相获取
     */
    public void getPicFromCamera() {
        String path = MyApplication.app.APP_FILE_SAVE_PATH
                + System.currentTimeMillis() + ".jpg";
        File file = new File(path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);//设置Action为拍照
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);//添加这一句表示对目标应用临时授权该Uri所代表的文件
            // 下面这句指定调用相机拍照后的照片存储的路径
            Uri imageUri = FileProvider.getUriForFile(this, MyApplication.TPDZ, file);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);//将拍取的照片保存到指定URI
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        }
        setCameraPath(path);
        setChildViewPosition(childViewPosition);
        startActivityForResult(intent, CAMERA_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == CHOOSE_PICTURE_CODE) {//从相册获取照片
            if (intent != null) {
                ArrayList<String> listSelectPic = intent.getStringArrayListExtra("filelist");
                int position = intent.getIntExtra("position", -1);
                if (position != -1 && listSelectPic != null && listSelectPic.size() > 0) {
                    String pathStr = listSelectPic.get(0);
                    final String savePathStr = pathStr.substring(0, pathStr.lastIndexOf("/"));
                    Luban.with(this)
                            .load(listSelectPic)                                  // 传人要压缩的图片列表
                            .ignoreBy(100)                                  // 忽略不压缩图片的大小
                            .setTargetDir(savePathStr)                     // 设置压缩后文件存储位置
                            .setCompressListener(new OnCompressListener() { //设置回调
                                @Override
                                public void onStart() {
                                    // TODO 压缩开始前调用，可以在方法内启动 loading UI
                                }

                                @Override
                                public void onSuccess(File file) {
                                    // TODO 压缩成功后调用，返回压缩后的图片文件
                                    String imgPath = file.getPath();
                                    Bitmap bitmap = Tooklkit.getImageThumbnail(imgPath, Tooklkit.dip2px(INwaihandleActivity.this, 480),
                                            Tooklkit.dip2px(INwaihandleActivity.this, 480));
                                    if (type == 1) {
                                        shigongqianyesbig = true;
                                        SGQTP = imgPath;
                                        shigongqian.setImageBitmap(bitmap);
                                        shigongqiande.setVisibility(View.VISIBLE);
                                    } else if (type == 2) {
                                        shigonghoubig = true;
                                        SGHTP = imgPath;
                                        shigonghou.setImageBitmap(bitmap);
                                        shigonghoude.setVisibility(View.VISIBLE);
                                    }else if (type == 3) {
                                        shigongzhongyesbig = true;
                                        SGZTP = imgPath;
                                        shigongzhong.setImageBitmap(bitmap);
                                        shigongzhongde.setVisibility(View.VISIBLE);
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
                    Luban.with(this)
                            .load(cameraPath)                                  // 传人要压缩的图片列表
                            .ignoreBy(100)                                  // 忽略不压缩图片的大小
                            .setTargetDir(cameraPath.substring(0, cameraPath.lastIndexOf("/")))                        // 设置压缩后文件存储位置
                            .setCompressListener(new OnCompressListener() { //设置回调
                                @Override
                                public void onStart() {
                                    // TODO 压缩开始前调用，可以在方法内启动 loading UI
                                }

                                @Override
                                public void onSuccess(File file) {
                                    // TODO 压缩成功后调用，返回压缩后的图片文件
                                    String imgPath = file.getPath();
                                    Bitmap bitmap = Tooklkit.getImageThumbnail(imgPath, Tooklkit.dip2px(INwaihandleActivity.this, 480),
                                            Tooklkit.dip2px(INwaihandleActivity.this, 480));
                                    if (type == 1) {
                                        SGQTP = imgPath;
                                        shigongqian.setImageBitmap(bitmap);
                                    } else if (type == 2) {
                                        SGHTP = imgPath;
                                        shigonghou.setImageBitmap(bitmap);
                                    }else if (type == 3) {
                                        SGZTP = imgPath;
                                        shigongzhong.setImageBitmap(bitmap);
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
    public void getData(waiHandleItembean videoVos2) {
        if (loadDataDialog.isShowing()) {
            loadDataDialog.cancel();
        }
        if (videoVos2 != null) {
            if ("1".equals(videoVos2.getSTATE())) {
                int ss = curingDao.deleteSgwxById(indate.getSGWXID());
                if (ss != 0) {
                    MyApplication.app.customToast("上传成功");
                    Intent intent = new Intent();
                    setResult(2, intent);
                    finish();
                }
            } else if (!Utils.isNull(videoVos2.getMSG())) {
                MyApplication.app.customToast(videoVos2.getMSG());
            }
        }
        addLog.setClickable(true);

    }

    @Override
    public void getData2(Dingbean videoVos2) {
        if (videoVos2.getSTATE().equals("1")){
            if (videoVos2.getGPS().size()>0){
                String zhStr=videoVos2.getGPS().get(0).getQDZH();
                if(!Utils.isNull(zhStr)){
                    if(zhStr.contains(".")){
                        String zhOneStr=zhStr.substring(0,zhStr.lastIndexOf("."));
                        String zhTwoStr=zhStr.substring(zhStr.lastIndexOf(".")+1,zhStr.length());
                        diseaseNewPileNumberOneEdit.setText(zhOneStr);
                        diseaseNewPileNumberTwoEdit.setText(zhTwoStr);
                    }else{
                        diseaseNewPileNumberOneEdit.setText(videoVos2.getGPS().get(0).getQDZH());
                        diseaseNewPileNumberTwoEdit.setText("0");
                    }
                    String str=diseaseNewPileNumberOneEdit.getText().toString();
                    diseaseNewPileNumberOneEdit.setSelection(str.length());
                }
            }   else {
                MyApplication.app.customToast("您当前位置暂时无法定位");
            }

        }
    }

    @Override
    public void onRequestError(String msg) {

    }

    @Override
    public void onRequestEnd() {

    }
    private void showLoadingDataDialog() {
        if (loadDataDialog == null) {
            loadDataDialog = new LoadDataDialog(this);
            loadDataDialog.setTitleStr("维修数据上传中");
        }
        loadDataDialog.show();
    }
    private void getkdxf(final EditText kdaxfdate) {
        dictationResultStr = "[";
        // 语音配置对象初始化
        SpeechUtility.createUtility(INwaihandleActivity.this, SpeechConstant.APPID + "=" + APPID);
        // 1.创建SpeechRecognizer对象，第2个参数：本地听写时传InitListener
        SpeechRecognizer mIat = SpeechRecognizer.createRecognizer(INwaihandleActivity.this, null);
        // 交互动画
        final RecognizerDialog iatDialog = new RecognizerDialog(INwaihandleActivity.this, null);
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
                    kdaxfdate.setText(addet(zong));
                    kdaxfdate.requestFocus();
                    kdaxfdate.setSelection(addet(zong).length());
                }
            }

            @Override
            public void onError(SpeechError error) {
                error.getPlainDescription(true);
            }

        });
        iatDialog.show();

    }
    public static String addet(List<String> list) {
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
                Log.i("定位结果","经度"+String.valueOf(longitude)+" 纬度"+String.valueOf(latitude));
                if (indate.getLXBM()!=null){
                    if (curingDao.queryLdByLdmc(indate.getLXBM())!=null){
                        mPresenter.getZH(curingDao.queryLdByLdmc(indate.getLXBM()).getLXID(),String.valueOf(longitude),String.valueOf(latitude),gydwid);
                    }
                }
            } else {//定位失败
                Log.i("定位失败","定位失败");
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

