package com.tyiroad.tyiroad.log.addlog;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.GridView;
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
import com.tyiroad.tyiroad.Bean.AddLogjson;
import com.tyiroad.tyiroad.Bean.Basebean;
import com.tyiroad.tyiroad.MyApplication;
import com.tyiroad.tyiroad.R;
import com.tyiroad.tyiroad.db.dbhelper.CuringDaoImpl;
import com.tyiroad.tyiroad.db.dbhelper.dbInfo.LogRecordInfo;
import com.tyiroad.tyiroad.db.dbhelper.dbInfo.RoadSectionInfo;
import com.tyiroad.tyiroad.db.dbhelper.dbInfo.TypeOfInvestigation;
import com.tyiroad.tyiroad.db.dbhelper.dbInfo.XclxInfo;
import com.tyiroad.tyiroad.handle.LoadDataDialog;
import com.tyiroad.tyiroad.kdxf.DictationResult;
import com.tyiroad.tyiroad.mvp.MVPBaseActivity;
import com.tyiroad.tyiroad.popuwindo.popuwindosa;
import com.tyiroad.tyiroad.popuwindo.popuwindowsslistener;
import com.tyiroad.tyiroad.utils.DiseaeNewSelectObjectPopupWindow;
import com.tyiroad.tyiroad.utils.DiseaseNewSelectObjectListener;
import com.tyiroad.tyiroad.utils.PopSelectListener;
import com.tyiroad.tyiroad.utils.Utils;
import com.tyiroad.tyiroad.utils.WheelDateAndTimeSelectPopupWindow;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

public class AddLogActivity extends MVPBaseActivity<AddLogContract.View, AddLogPresenter> implements AddLogContract.View {
    @Bind(R.id.go_back)
    LinearLayout goBack;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.right)
    TextView right;
    @Bind(R.id.hea)
    RelativeLayout hea;
    @Bind(R.id.xcdw)
    TextView xcdw;
    @Bind(R.id.lxre)
    RecyclerView lxre;
    @Bind(R.id.xclx_layout)
    LinearLayout xclxLayout;
    @Bind(R.id.xcrq)
    TextView xcrq;
    @Bind(R.id.xcrq_layout)
    LinearLayout xcrqLayout;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.dqtq)
    TextView dqtq;
    @Bind(R.id.dqtq_layout)
    LinearLayout dqtqLayout;
    @Bind(R.id.dclx)
    TextView dclx;
    @Bind(R.id.dclx_layout)
    LinearLayout dclxLayout;
    @Bind(R.id.disease_new_bh_content_layout)
    LinearLayout diseaseNewBhContentLayout;
    @Bind(R.id.save)
    TextView save;
    @Bind(R.id.add_log)
    TextView addLog;
    @Bind(R.id.layoutt)
    LinearLayout layoutt;
    private CuringDaoImpl curingDao;
    private EditText edqclj, edqcss, edqczc, edwjj, edgj, edlmyh, edgfj, edysc,czwtet;
    private LinearLayout diseasenewbh;
    private GridView caiJiPictureAddGrid;
    private TextView czwtbu;
    private WheelDateAndTimeSelectPopupWindow TimePop;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private ArrayList<String> tqlist = new ArrayList<>();
    private DiseaeNewSelectObjectPopupWindow TqPop;
    private DiseaeNewSelectObjectPopupWindow DcLxPop;
    private ArrayList<String> listDclxResult = new ArrayList<>();
    private List<AddLogItem> PopList = new ArrayList<>();
    private popuwindosa ldPop;
    private AddlogGredAdapter addPictureAdapter;
    private List<XclxInfo> DBlist = new ArrayList<>();
    private AddLogItem PopInfo;
    private CommonAdapter<AddLogItem> Popadapter;
    private List<AddLogItem> ListData = new ArrayList<>();
    private List<TypeOfInvestigation> DclxInfoResult =new ArrayList<>();
    private String nrstring;
    private LoadDataDialog loadDataDialog;
    private Gson gson = new Gson();
    private String cameraPath;//拍照获取图片的地址
    private int childViewPosition;//拍照获取图片的地址
    private String LDIDString;
    private String LXMCString;
    private String imgStr;
    private final int CHOOSE_PICTURE_CODE = 1;
    private final int CAMERA_CODE = 2;
    public void setCameraPath(String cameraPath) {
        this.cameraPath = cameraPath;
    }
    public void setChildViewPosition(int childViewPosition) {
        this.childViewPosition = childViewPosition;
    }
    private Map<Integer, AddlogGredAdapter> mapAdapter = new HashMap<>();
    private static String APPID = "5bf211f5";
    private String dictationResultStr;
    private List<String> xf = new ArrayList<>();
    private List<String> zong = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setStatusBarColor(this, R.color.theme_color);
        setContentView(R.layout.act_addlog);
        ButterKnife.bind(this);
        curingDao = new CuringDaoImpl(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        initview();
        initpopu();
        setadapter();
        initdate();
        linstener();
    }

    private void linstener() {
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        xcrqLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePop.show(layoutt);
            }
        });
        dqtqLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TqPop.show(layoutt);
            }
        });
        dclxLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DcLxPop.show(layoutt);
            }
        });
        czwtbu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getkdxf(czwtet);
            }
        });
        Popadapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                ldPop.show(layoutt);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save.setClickable(false);
                getnr();
                showLoadingDialogMethod("正在加载...");
                LogRecordInfo logRecordInfo = new  LogRecordInfo();
                logRecordInfo.setGYDWID(MyApplication.spUtils.getString("dqgydwid"));
                logRecordInfo.setGYDWMC(MyApplication.spUtils.getString("dqgydwmc"));
                logRecordInfo.setLDMC(LXMCString);//路段名称
                logRecordInfo.setXCLD(LDIDString);//路段id
                logRecordInfo.setJLSJ(replaceNull(xcrq.getText().toString()));
                logRecordInfo.setTQ(replaceNull(dqtq.getText().toString()));
                logRecordInfo.setXCXZMC(replaceNull(dclx.getText().toString()));
                logRecordInfo.setCLJL(replaceNull(czwtet.getText().toString()));
                logRecordInfo.setQCLJ(replaceNull(edqclj.getText().toString()));
                logRecordInfo.setQCSS(replaceNull(edqcss.getText().toString()));
                logRecordInfo.setQCZC(replaceNull(edqczc.getText().toString()));
                logRecordInfo.setWJJ(replaceNull(edwjj.getText().toString()));
                logRecordInfo.setWG(replaceNull(edgj.getText().toString()));
                logRecordInfo.setLMZHYH(replaceNull(edlmyh.getText().toString()));
                logRecordInfo.setGFJ(replaceNull(edgfj.getText().toString()));
                logRecordInfo.setYSC(replaceNull(edysc.getText().toString()));
                logRecordInfo.setTPDZ(imgStr);
                int reture =  curingDao.addRzjl(logRecordInfo);
                if(reture!=0){
                    MyApplication.app.customToast("保存本地成功");
                    Intent intent = new Intent();
                    setResult(2, intent);
                    finish();
                }else {
                    MyApplication.app.customToast("保存本地失败");
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
                addLog.setClickable(false);
                getnr();
                showLoadingDialogMethod("正在加载...");
                if (!Utils.isNetworkAvailable(AddLogActivity.this)) {//没网
                    MyApplication.app.customToast("您当前没有网络");
                } else {//有网
                    AddLogjson addLogjsons = new AddLogjson();
                    List<AddLogjson.XCRZBean> addLogjsonlist = new ArrayList<AddLogjson.XCRZBean>();
                    AddLogjson.XCRZBean addLogjson = new AddLogjson.XCRZBean();
                    addLogjson.setGYDW(MyApplication.spUtils.getString("dqgydwid"));
                    addLogjson.setLDMC(LXMCString);//路段名称
                    addLogjson.setXCLD(LDIDString);//路段id
                    addLogjson.setXCSJ((xcrq.getText().toString()).replace("-", "/"));
                    addLogjson.setXCR(MyApplication.app.spUtils.getString("dlr"));
                    addLogjson.setCREATOR(MyApplication.app.spUtils.getString("dlr"));//空
                    if (!Utils.isNull(dclx.getText().toString())){
                        addLogjson.setXCXZ(curingDao.queryInvestigationByMc(dclx.getText().toString()).getDCID());
                    }
                    addLogjson.setCLJL(replaceNull(czwtet.getText().toString()));
                    addLogjson.setXCNR(nrstring);
                    addLogjson.setTQ(dqtq.getText().toString());
                    if (!Utils.isNull(imgStr)) {
                        String[] strArr = imgStr.split(",");
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
                    Log.i("张成昆", tijiaodates);
                    mPresenter.add(tijiaodates);

                }
            }
        });
    }

    private void initdate() {
        xcdw.setText(MyApplication.spUtils.getString("dqgydwmc"));
        xcrq.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));//默认日期
        dqtq.setText("晴");
        getlxResult(MyApplication.spUtils.getString("dqgydwid"));
        queryDclxInfoMethod();
        ArrayList<Drawable> listPicture = new ArrayList<>();
        ArrayList<String> listImgUrl = new ArrayList<>();
        Drawable addPicture = getResources().getDrawable(R.drawable.sjq_add_tupian_sel);
        listPicture.add(addPicture);
        final int childPosition = diseasenewbh.getChildCount();
        addPictureAdapter = new AddlogGredAdapter(this, listPicture, listImgUrl, childPosition);
        caiJiPictureAddGrid.setAdapter(addPictureAdapter);
        mapAdapter.put(childPosition, addPictureAdapter);
    }

    private void queryDclxInfoMethod() {
        DclxInfoResult = curingDao.queryAllInvestigation();
        if (DclxInfoResult != null) {
            listDclxResult.clear();
            for (int i = 0; i < DclxInfoResult.size(); i++) {
                TypeOfInvestigation typeInfo = DclxInfoResult.get(i);
                listDclxResult.add(typeInfo.getDCMC());
                if (i==0){
                    dclx.setText(DclxInfoResult.get(0).getDCMC());
                }
            }
        }
        DcLxPop.notifityData();
    }

    private void initpopu() {
        TimePop = new WheelDateAndTimeSelectPopupWindow(this, new PopSelectListener() {
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
                xcrq.setText(selDataStr);
            }
        });
        tqlist.add("晴");
        tqlist.add("多云");
        tqlist.add("小雨");
        tqlist.add("中雨");
        TqPop = new DiseaeNewSelectObjectPopupWindow(AddLogActivity.this, "请选择当前天气", tqlist, new DiseaseNewSelectObjectListener() {
            @Override
            public void selectPosition(int position) {
                dqtq.setText(tqlist.get(position));
            }
        });
        DcLxPop = new DiseaeNewSelectObjectPopupWindow(this, "请选择调查类型", listDclxResult, new DiseaseNewSelectObjectListener() {
            @Override
            public void selectPosition(int position) {
                dclx.setText(listDclxResult.get(position));
            }
        });
        ldPop = new popuwindosa(AddLogActivity.this, "请选择路线", PopList, new popuwindowsslistener() {
            @Override
            public void selectPosition(List<AddLogItem>  AddLogItems) {
                if (AddLogItems.size()>0){
                    ListData.clear();
                    ListData.addAll(AddLogItems);
                    Popadapter.notifyDataSetChanged();
                }
            }
        });
    }
    private void initview() {
        title.setText("日志记录");
        lxre.setLayoutManager(new LinearLayoutManager(this));
        View diseaseView = LayoutInflater.from(AddLogActivity.this).inflate(R.layout.layout_looklog, null, false);
        diseaseNewBhContentLayout.addView(diseaseView);
        edqclj = (EditText) diseaseView.findViewById(R.id.ed_qclj);
        edqcss = (EditText) diseaseView.findViewById(R.id.ed_qcss);
        edqczc = (EditText) diseaseView.findViewById(R.id.ed_qczc);
        edwjj = (EditText) diseaseView.findViewById(R.id.ed_wjj);
        edgj = (EditText) diseaseView.findViewById(R.id.ed_gj);
        edlmyh = (EditText) diseaseView.findViewById(R.id.ed_lmyh);
        edgfj = (EditText) diseaseView.findViewById(R.id.ed_gfj);
        edysc = (EditText) diseaseView.findViewById(R.id.ed_ysc);
        czwtet = (EditText) diseaseView.findViewById(R.id.czwt_et);
        czwtbu = (TextView) diseaseView.findViewById(R.id.czwt_bu);
        caiJiPictureAddGrid = (GridView) diseaseView.findViewById(R.id.cai_ji_picture_add_grid);
        diseasenewbh = (LinearLayout) diseaseView.findViewById(R.id.disease_new_bh_content_layou);
    }
    private void getlxResult(String dqgydwid) {
        DBlist = curingDao.queryAllXclx();
        if (DBlist != null&&DBlist.size()>0) {
            PopList.clear();
            for (int i = 0; i < DBlist.size(); i++) {
                XclxInfo sectionInfo = DBlist.get(i);
                AddLogItem AddLogItem =  new AddLogItem();
                AddLogItem.setIsChcked(false);
                AddLogItem.setLDID(sectionInfo.getVALUE());
                AddLogItem.setLDMC(sectionInfo.getTEXT());
                PopList.add(AddLogItem);
            }
            PopList.get(0).setIsChcked(true);
            ldPop.notifityData();
            ListData.add(PopList.get(0));
            Popadapter.notifyDataSetChanged();
        }
    }
    private void setadapter() {
        Popadapter = new CommonAdapter<AddLogItem>(this,
                R.layout.item_lxlog, ListData) {
            @Override
            protected void convert(ViewHolder holder, AddLogItem tubiaoVo, int position) {
                holder.setText(R.id.neirongte, tubiaoVo.getLDMC());
            }
        };
        lxre.setAdapter(Popadapter);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == CHOOSE_PICTURE_CODE) {
            if (intent != null) {
                ArrayList<String> listSelectPic = intent.getStringArrayListExtra("filelist");
                int position = intent.getIntExtra("position", -1);
                if (position != -1 && listSelectPic != null && listSelectPic.size() > 0) {
                    final AddlogGredAdapter addPictureAdapter = mapAdapter.get(position);
                    final ArrayList<Drawable> listPicture = addPictureAdapter.getListPicture();
                    final ArrayList<String> listImgUrl = addPictureAdapter.getListImgUrl();
                    String pathStr = listSelectPic.get(0);
                    final String savePathStr = pathStr.substring(0, pathStr.lastIndexOf("/"));   // 设置压缩后文件存储位置
                    Luban.with(this)
                            .load(listSelectPic)
                            .ignoreBy(100)
                            .setCompressListener(new OnCompressListener() {
                                @Override
                                public void onStart() {
                                }
                                @Override
                                public void onSuccess(File file) {
                                    String imgPath = file.getPath();
                                    listPicture.remove(listPicture.size() - 1);
                                    Bitmap bitmap = Tooklkit.getImageThumbnail(imgPath, Tooklkit.dip2px(AddLogActivity.this, 480),
                                            Tooklkit.dip2px(AddLogActivity.this, 480));
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
                                }
                            }).launch();
                }
            }
        } else if (requestCode == CAMERA_CODE) {
            if (resultCode == -1) {
                if (!Utils.isNull(cameraPath) && childViewPosition != -1) {
                    Utils.dealBitmapRotate(cameraPath);
                    final AddlogGredAdapter addPictureAdapter = mapAdapter.get(childViewPosition);
                    final ArrayList<Drawable> listPicture = addPictureAdapter.getListPicture();
                    final ArrayList<String> listImgUrl = addPictureAdapter.getListImgUrl();
                    Luban.with(this)
                            .load(cameraPath)
                            .ignoreBy(100)
                            .setCompressListener(new OnCompressListener() {
                                @Override
                                public void onStart() {
                                }
                                @Override
                                public void onSuccess(File file) {
                                    String imgPath = file.getPath();
                                    listPicture.remove(listPicture.size() - 1);
                                    Bitmap bitmap = Tooklkit.getImageThumbnail(imgPath, Tooklkit.dip2px(AddLogActivity.this, 480),
                                            Tooklkit.dip2px(AddLogActivity.this, 480));
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
                                }
                            }).launch();
                }
            }
        }
    }
    private void getnr() {
        nrstring = replaceNull2(edqclj.getText().toString()) + "," +
                replaceNull2(edqcss.getText().toString()) + "," +
                replaceNull2(edqczc.getText().toString()) + "," +
                replaceNull2(edwjj.getText().toString()) + "," +
                replaceNull2(edgj.getText().toString()) + "," +
                replaceNull2(edlmyh.getText().toString()) + "," +
                replaceNull2(edgfj.getText().toString()) + "," +
                replaceNull2(edysc.getText().toString());
         LDIDString ="";
         LXMCString ="";
        if (ListData != null && ListData.size() > 0) {
            for (int g = 0; g < ListData.size(); g++) {
                if (g == 0) {
                    LDIDString += ListData.get(g).getLDID();
                    LXMCString  += ListData.get(g).getLDMC();
                } else {
                    LDIDString += "," + ListData.get(g).getLDID();
                    LXMCString += "," + ListData.get(g).getLDMC();
                }
            }
        }
         imgStr = "";
        ArrayList<String> listImgUrl = addPictureAdapter.getListImgUrl();
        if (listImgUrl != null && listImgUrl.size() > 0) {
            for (int k = 0; k < listImgUrl.size(); k++) {
                if (k == 0) {
                    imgStr += listImgUrl.get(k);
                } else {
                    imgStr += "," + listImgUrl.get(k);
                }
            }
        }
    }
    public static String replaceNull(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }
    public static String replaceNull2(String str) {
        if (str.equals("")) {
            return "0";
        }
        return str;
    }
    private void showLoadingDialogMethod(String str) {
        if (loadDataDialog == null) {
            loadDataDialog = new LoadDataDialog(this);
        }
        loadDataDialog.setTitleStr(str);
        loadDataDialog.show();
    }
    private void getkdxf(final EditText kdaxfdate) {
        dictationResultStr = "[";
        // 语音配置对象初始化
        SpeechUtility.createUtility(AddLogActivity.this, SpeechConstant.APPID + "=" + APPID);
        // 1.创建SpeechRecognizer对象，第2个参数：本地听写时传InitListener
        SpeechRecognizer mIat = SpeechRecognizer.createRecognizer(AddLogActivity.this, null);
        // 交互动画
        final RecognizerDialog iatDialog = new RecognizerDialog(AddLogActivity.this, null);
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
    public void onRequestError(String msg) {

    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void getData(Basebean videoVos2) {
        if (videoVos2.getSTATE().equals("1")) {
            if (loadDataDialog != null && loadDataDialog.isShowing()) {
                loadDataDialog.cancel();
            }
            MyApplication.app.customToast("上传成功");
            Intent intent = new Intent();
            setResult(2, intent);
            finish();
        } else {
            if (loadDataDialog != null && loadDataDialog.isShowing()) {
                loadDataDialog.cancel();
            }
            save.setClickable(true);
        }
    }
}
