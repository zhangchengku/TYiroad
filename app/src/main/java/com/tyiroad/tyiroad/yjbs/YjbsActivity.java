package com.tyiroad.tyiroad.yjbs;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.google.gson.Gson;
import com.tooklkit.Tooklkit;
import com.tyiroad.tyiroad.Bean.AddBSbean;
import com.tyiroad.tyiroad.Bean.Basebean;
import com.tyiroad.tyiroad.Bean.YJbsBean;
import com.tyiroad.tyiroad.Bean.YJbsgpsBean;
import com.tyiroad.tyiroad.MyApplication;
import com.tyiroad.tyiroad.R;
import com.tyiroad.tyiroad.db.dbhelper.CuringDao;
import com.tyiroad.tyiroad.db.dbhelper.CuringDaoImpl;
import com.tyiroad.tyiroad.mvp.MVPBaseActivity;
import com.tyiroad.tyiroad.utils.BSTimeSelectPopupWindow;
import com.tyiroad.tyiroad.utils.DiseaseNewSelectObjectListener;
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
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class YjbsActivity extends MVPBaseActivity<YjbsContract.View, YjbsPresenter> implements YjbsContract.View {
    @Bind(R.id.go_back)
    LinearLayout goBack;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.right)
    TextView right;
    @Bind(R.id.lx_text)
    TextView lxText;
    @Bind(R.id.lx)
    LinearLayout lx;
    @Bind(R.id.qdzh_dw)
    TextView qdzhDw;
    @Bind(R.id.qdzh_text1)
    EditText qdzhText1;
    @Bind(R.id.qdzh_lj)
    TextView qdzhLj;
    @Bind(R.id.qdzh_text2)
    EditText qdzhText2;
    @Bind(R.id.qd)
    LinearLayout qd;
    @Bind(R.id.zdzh_dw)
    TextView zdzhDw;
    @Bind(R.id.zdzh_text1)
    EditText zdzhText1;
    @Bind(R.id.zdzh_lj)
    TextView zdzhLj;
    @Bind(R.id.zdzh_text2)
    EditText zdzhText2;
    @Bind(R.id.dw)
    RelativeLayout dw;
    @Bind(R.id.disease_new_driver_direction_quan_txt)
    TextView diseaseNewDriverDirectionQuanTxt;
    @Bind(R.id.disease_new_driver_direction_shang_txt)
    TextView diseaseNewDriverDirectionShangTxt;
    @Bind(R.id.disease_new_driver_direction_xia_txt)
    TextView diseaseNewDriverDirectionXiaTxt;
    @Bind(R.id.yjnr_text)
    TextView yjnrText;
    @Bind(R.id.yjdj_text)
    TextView yjdjText;
    @Bind(R.id.fssj_text)
    TextView fssjText;
    @Bind(R.id.zhlx_text)
    TextView zhlxText;
    @Bind(R.id.jtqk_text)
    TextView jtqkText;
    @Bind(R.id.sszq_et)
    EditText sszqEt;
    @Bind(R.id.czwt_et)
    EditText czwtEt;
    @Bind(R.id.czwt_bu)
    TextView czwtBu;
    @Bind(R.id.cai_ji_picture_add_grid)
    NoScroolGridView caiJiPictureAddGrid;
    @Bind(R.id.disease_custom_edit_item_cjtp_layout)
    LinearLayout diseaseCustomEditItemCjtpLayout;
    @Bind(R.id.disease_custom_edit_item_delete_txt)
    TextView diseaseCustomEditItemDeleteTxt;
    @Bind(R.id.disease_new_bh_content_layou)
    LinearLayout diseaseNewBhContentLayou;
    @Bind(R.id.plys)
    TextView plys;
    @Bind(R.id.shang)
    LinearLayout shang;
    @Bind(R.id.activity_disease_list_zhe_zhao_top_filter_layout)
    View activityDiseaseListZheZhaoTopFilterLayout;
    @Bind(R.id.yjnr_ly)
    LinearLayout yjnrLy;
    @Bind(R.id.yjdj_ly)
    LinearLayout yjdjLy;
    @Bind(R.id.zhlx_ly)
    LinearLayout zhlxLy;
    @Bind(R.id.jtqk_ly)
    LinearLayout jtqkLy;
    @Bind(R.id.disease_new_parent_layout)
    RelativeLayout diseaseNewParentLayout;
    @Bind(R.id.qd_ly)
    LinearLayout qdLy;
    @Bind(R.id.sj_ly)
    LinearLayout sjLy;
    private ArrayList<String> NrData = new ArrayList<>();
    private ArrayList<String> DjData = new ArrayList<>();
    private ArrayList<String> LxData = new ArrayList<>();
    private ArrayList<String> JtData = new ArrayList<>();
    private YsbsPopowindow NrPop;
    private YJbsBean.YJNRDTBean NrIn;
    private YJbsBean.YJDJDTBean DjIn;
    private YsbsPopowindow DjPop;
    private YJbsBean.ZHLXDTBean LxIn;
    private YsbsPopowindow LxPop;
    private YsbsPopowindow JtPop;
    private YJbsBean Bean;
    private YJbsBean.JTQKDTBean JtIn;
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    private CuringDao curingDao;
    private ArrayList<String> LxSData = new ArrayList<>();
    private YsbsPopowindow LxSPop;
    private SimpleDateFormat simpleDateFormat;
    private BSTimeSelectPopupWindow TimePop;
    private String cameraPath;//拍照获取图片的地址
    private int childViewPosition;//拍照获取图片的地址
    private BsGredAdapter bsGredAdapter;
    private Map<Integer, BsGredAdapter> mapAdapter = new HashMap<>();
    private YJbsgpsBean.GPSBean LxsIn;
    private List<YJbsgpsBean.GPSBean> LxsInlist = new ArrayList<>();
    private int directionSelInt = 1;// 1上行-右 2下行-左 3双向-全幅
    public void setChildViewPosition(int childViewPosition) {
        this.childViewPosition = childViewPosition;
    }
    public void setCameraPath(String cameraPath) {
        this.cameraPath = cameraPath;
    }
    private final int CHOOSE_PICTURE_CODE = 1;
    private final int CAMERA_CODE = 2;
    private Gson gson = new Gson();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setStatusBarColor(this, R.color.theme_color);
        setContentView(R.layout.act_yjbs);
        ButterKnife.bind(this);
        initdata();
        initview();
        initlistener();
    }

    private void initlistener() {
        lx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LxSPop.show(diseaseNewParentLayout);
            }
        });
        yjnrLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NrPop.show(diseaseNewParentLayout);
            }
        });
        yjdjLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DjPop.show(diseaseNewParentLayout);
            }
        });
        zhlxLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LxPop.show(diseaseNewParentLayout);
            }
        });
        jtqkLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JtPop.show(diseaseNewParentLayout);
            }
        });
        sjLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePop.show(diseaseNewParentLayout);
            }
        });
        diseaseNewDriverDirectionShangTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setShangXingSelect();
            }
        });
        diseaseNewDriverDirectionXiaTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setXiaXingSelect();
            }
        });
        diseaseNewDriverDirectionQuanTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setQuanFuSelect();
            }
        });
        plys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNetworkAvailable(YjbsActivity.this) == false) {//没网
                    MyApplication.app.customToast("您当前没有网络");
                } else {//有网
                    int result = isSaveOk();
                    if (result == 8) {
                        AddBSbean addBSbean = new AddBSbean();
                        addBSbean.setSJLCFL("0");
                        addBSbean.setCREATOR(MyApplication.spUtils.getString("dlr"));
                        addBSbean.setGYDWID(MyApplication.spUtils.getString("dqgydwid"));
                        addBSbean.setLXCODE(LxsIn.getLXID());
                        String zhStr="0.0";
                        String zhOneStr=qdzhText1.getText().toString();
                        String zhTwoStr=qdzhText2.getText().toString();
                        if(Utils.isNull(zhTwoStr)){
                            zhStr = zhOneStr;
                        }else{
                            zhStr = zhOneStr + "." + zhTwoStr;
                        }
                        addBSbean.setQDZH(zhStr);
                        String zdStr="0.0";
                        String zdOneStr=zdzhText1.getText().toString();
                        String zdTwoStr=zdzhText2.getText().toString();
                        if(Utils.isNull(zhTwoStr)){
                            zdStr = zdOneStr;
                        }else{
                            zdStr = zdOneStr + "." + zdTwoStr;
                        }
                        addBSbean.setZDZH(zdStr);
                        String xsfxStr = "";
                        if (directionSelInt == 1) {
                            xsfxStr = diseaseNewDriverDirectionShangTxt.getText().toString();
                        } else if (directionSelInt == 2) {
                            xsfxStr = diseaseNewDriverDirectionXiaTxt.getText().toString();
                        } else if (directionSelInt == 3) {
                            xsfxStr = diseaseNewDriverDirectionQuanTxt.getText().toString();
                        }
                        addBSbean.setWZMS(xsfxStr);
                        addBSbean.setYJNR(NrIn.getVALUE());
                        addBSbean.setYJDJ(DjIn.getVALUE());
                        addBSbean.setFSSJ(fssjText.getText().toString());
                        addBSbean.setZHLX(LxIn.getVALUE());
                        addBSbean.setJTQK(JtIn.getVALUE());
                        addBSbean.setSSZQ(sszqEt.getText().toString());
                        addBSbean.setBZ(czwtEt.getText().toString());
                        addBSbean.setSJQK(czwtEt.getText().toString());
                        addBSbean.setBZ("");
                        String imgStr = "";
                        ArrayList<String> listImgUrl = bsGredAdapter.getListImgUrl();
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
                                ArrayList<AddBSbean.PICBean> listPic = new ArrayList<>();
                                for (int i = 0; i < strArr.length; i++) {
                                    AddBSbean.PICBean picInfo = new AddBSbean.PICBean();
                                    String imgUrl = strArr[i];
                                    String nameStr = imgUrl.substring(imgUrl.lastIndexOf("/") + 1, imgUrl.length());
                                    String typeStr = imgUrl.substring(imgUrl.lastIndexOf(".") + 1, imgUrl.length());
                                    picInfo.setPicFileName(nameStr);
                                    picInfo.setPicFileType(typeStr);
                                    String strBlob = Utils.bmpToBase64String(imgUrl);
                                    picInfo.setPicDataBlob(strBlob);
                                    listPic.add(picInfo);
                                }
                                addBSbean.setPIC(listPic);
                            }
                        }
                        String json = gson.toJson(addBSbean);
                        mPresenter.addData(json);
                    }
                    }
            }
        });
    }
    private int isSaveOk() {
        if (Utils.isNull(qdzhText1.getText().toString())||Utils.isNull(qdzhText2.getText().toString())) {//起点为空
            MyApplication.app.customToast("请输入起点桩号");
            return 1;
        }else if (Utils.isNull(zdzhText1.getText().toString())||Utils.isNull(zdzhText2.getText().toString())) {//终点为空
            MyApplication.app.customToast("请输入终点桩号");
            return 2;
        } else if (Utils.isNull(yjnrText.getText().toString())) {//内容为空
            MyApplication.app.customToast("请输入预警内容");
            return 3;
        } else if (Utils.isNull(yjdjText.getText().toString())) {//等级为空
            MyApplication.app.customToast("请输入预警等级");
            return 4;
        } else if (Utils.isNull(zhlxText.getText().toString())) {//灾害类型为空
            MyApplication.app.customToast("请输入灾害类型");
            return 5;
        } else if (Utils.isNull(jtqkText.getText().toString())) {//交通情况为空
            MyApplication.app.customToast("请输入事件名称");
            return 6;
        }  else if (Utils.isNull(sszqEt.getText().toString())) {//所属镇区为空
            MyApplication.app.customToast("请输入所属镇区");
            return 7;
        }
        return 8;
    }
    private void setShangXingSelect() {
        if (directionSelInt != 1) {
            directionSelInt = 1;
            diseaseNewDriverDirectionShangTxt.setTextColor(getResources().getColor(R.color.white));
            diseaseNewDriverDirectionShangTxt.setBackgroundDrawable(getResources().getDrawable(R.drawable.month_btn_shap));
            diseaseNewDriverDirectionXiaTxt.setTextColor(getResources().getColor(R.color.select_month_text_bg));
            diseaseNewDriverDirectionXiaTxt.setBackgroundDrawable(getResources().getDrawable(R.drawable.direction_select_shap));
            diseaseNewDriverDirectionQuanTxt.setTextColor(getResources().getColor(R.color.select_month_text_bg));
            diseaseNewDriverDirectionQuanTxt.setBackgroundDrawable(getResources().getDrawable(R.drawable.direction_select_shap));
        }
    }
    private void setXiaXingSelect() {
        if (directionSelInt != 2) {
            directionSelInt = 2;
            diseaseNewDriverDirectionShangTxt.setTextColor(getResources().getColor(R.color.select_month_text_bg));
            diseaseNewDriverDirectionShangTxt.setBackgroundDrawable(getResources().getDrawable(R.drawable.direction_select_shap));
            diseaseNewDriverDirectionXiaTxt.setTextColor(getResources().getColor(R.color.white));
            diseaseNewDriverDirectionXiaTxt.setBackgroundDrawable(getResources().getDrawable(R.drawable.month_btn_shap));
            diseaseNewDriverDirectionQuanTxt.setTextColor(getResources().getColor(R.color.select_month_text_bg));
            diseaseNewDriverDirectionQuanTxt.setBackgroundDrawable(getResources().getDrawable(R.drawable.direction_select_shap));
        }
    }

    private void setQuanFuSelect() {
        if (directionSelInt != 3) {
            directionSelInt = 3;
            diseaseNewDriverDirectionShangTxt.setTextColor(getResources().getColor(R.color.select_month_text_bg));
            diseaseNewDriverDirectionShangTxt.setBackgroundDrawable(getResources().getDrawable(R.drawable.direction_select_shap));
            diseaseNewDriverDirectionXiaTxt.setTextColor(getResources().getColor(R.color.select_month_text_bg));
            diseaseNewDriverDirectionXiaTxt.setBackgroundDrawable(getResources().getDrawable(R.drawable.direction_select_shap));
            diseaseNewDriverDirectionQuanTxt.setTextColor(getResources().getColor(R.color.white));
            diseaseNewDriverDirectionQuanTxt.setBackgroundDrawable(getResources().getDrawable(R.drawable.month_btn_shap));
        }
    }
    private void initpopw() {
        if (NrPop == null) {
            NrPop = new YsbsPopowindow(this, "请选择预警内容", NrData, new DiseaseNewSelectObjectListener() {
                @Override
                public void selectPosition(int position) {
                    NrIn = Bean.getYJNRDT().get(position);
                    yjnrText.setText(NrData.get(position));
                }
            });
        } else {
            NrPop.notifityData();
        }
        if (DjPop == null) {
            DjPop = new YsbsPopowindow(this, "请选择预警等级", DjData, new DiseaseNewSelectObjectListener() {
                @Override
                public void selectPosition(int position) {
                    DjIn = Bean.getYJDJDT().get(position);
                    yjdjText.setText(DjData.get(position));
                }
            });
        } else {
            DjPop.notifityData();
        }
        if (LxPop == null) {
            LxPop = new YsbsPopowindow(this, "请选择灾害类型", LxData, new DiseaseNewSelectObjectListener() {
                @Override
                public void selectPosition(int position) {
                    LxIn = Bean.getZHLXDT().get(position);
                    zhlxText.setText(LxData.get(position));
                }
            });
        } else {
            LxPop.notifityData();
        }
        if (JtPop == null) {
            JtPop = new YsbsPopowindow(this, "请选择事件名称", JtData, new DiseaseNewSelectObjectListener() {
                @Override
                public void selectPosition(int position) {
                    JtIn = Bean.getJTQKDT().get(position);
                    jtqkText.setText(JtData.get(position));
                }
            });
        } else {
            JtPop.notifityData();
        }
        if (LxSPop == null) {
            LxSPop = new YsbsPopowindow(this, "请选路线", LxSData, new DiseaseNewSelectObjectListener() {
                @Override
                public void selectPosition(int position) {
                    lxText.setText(JtData.get(position));
                    LxsIn =   LxsInlist.get(position);
                }
            });
        } else {
            LxSPop.notifityData();
        }
        TimePop = new BSTimeSelectPopupWindow(this, new PopSelectListener() {
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
                lxText.setText(selDataStr);
            }
        });
    }

    private void initview() {
        title.setText("应急报送");
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar calendar = Calendar.getInstance();
        String currentDate = simpleDateFormat.format(calendar.getTime());
        fssjText.setText(currentDate);
        ArrayList<Drawable> listPicture = new ArrayList<>();
        ArrayList<String> listImgUrl = new ArrayList<>();
        Drawable addPicture = getResources().getDrawable(R.drawable.sjq_add_tupian_sel);
        listPicture.add(addPicture);
        final int childPosition = diseaseNewBhContentLayou.getChildCount();
        bsGredAdapter = new BsGredAdapter(this, listPicture, listImgUrl, childPosition);
        caiJiPictureAddGrid.setAdapter(bsGredAdapter);
        mapAdapter.put(childPosition, bsGredAdapter);
    }

    private void initdata() {
        if (locationClient == null) {
            initLocation();
        }
        startLocation();
    }

    @Override
    public void onRequestError(String msg) {

    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void getData2(YJbsBean videoVos2) {
        Bean = videoVos2;
        if (videoVos2.getYJNRDT() != null) {
            for (int i = 0; i < videoVos2.getYJNRDT().size(); i++) {
                NrData.add(videoVos2.getYJNRDT().get(i).getTEXT());
            }
        }
        if (videoVos2.getYJDJDT() != null) {
            for (int i = 0; i < videoVos2.getYJDJDT().size(); i++) {
                DjData.add(videoVos2.getYJDJDT().get(i).getTEXT());
            }
        }
        if (videoVos2.getZHLXDT() != null) {
            for (int i = 0; i < videoVos2.getZHLXDT().size(); i++) {
                LxData.add(videoVos2.getZHLXDT().get(i).getTEXT());
            }
        }
        if (videoVos2.getJTQKDT() != null) {
            for (int i = 0; i < videoVos2.getJTQKDT().size(); i++) {
                JtData.add(videoVos2.getJTQKDT().get(i).getTEXT());
            }
        }
        initpopw();
    }

    @Override
    public void getData(YJbsgpsBean videoVos2) {
        curingDao = new CuringDaoImpl(this);
        if (videoVos2.getSTATE().equals("1")) {
            LxsInlist = videoVos2.getGPS();
            for (int i = 0; i < videoVos2.getGPS().size(); i++) {
                if (i == 0) {
                    lxText.setText(curingDao.queryLxByLxid(videoVos2.getGPS().get(i).getLXID()).getLXMC());
                    LxsIn = videoVos2.getGPS().get(i);
                }
                LxSData.add(curingDao.queryLxByLxid(videoVos2.getGPS().get(i).getLXID()).getLXMC());
            }
            if (videoVos2.getGPS().size() > 0) {
                String zhStr =videoVos2.getGPS().get(0).getQDZH();
                if (!Utils.isNull(zhStr)) {
                    if (zhStr.contains(".")) {
                        String zhOneStr = zhStr.substring(0, zhStr.lastIndexOf("."));
                        String zhTwoStr = zhStr.substring(zhStr.lastIndexOf(".") + 1, zhStr.length());
                        qdzhText1.setText(zhOneStr);
                        qdzhText2.setText(zhTwoStr);
                    } else {
                        qdzhText1.setText(videoVos2.getGPS().get(0).getQDZH());
                        qdzhText2.setText("0");
                    }
                }
            }
            mPresenter.getdata();
        }
    }

    @Override
    public void getData3(Basebean videoVos2) {
        if (videoVos2.getSTATE().equals("1")){
            MyApplication.app.customToast(videoVos2.getMSG());
            finish();
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
                    final BsGredAdapter addPictureAdapter = mapAdapter.get(position);
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
                                    Bitmap bitmap = Tooklkit.getImageThumbnail(imgPath, Tooklkit.dip2px(YjbsActivity.this, 480),
                                            Tooklkit.dip2px(YjbsActivity.this, 480));
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
                    final BsGredAdapter addPictureAdapter = mapAdapter.get(childViewPosition);
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
                                    Bitmap bitmap = Tooklkit.getImageThumbnail(imgPath, Tooklkit.dip2px(YjbsActivity.this, 480),
                                            Tooklkit.dip2px(YjbsActivity.this, 480));
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
        if (!activityDiseaseListZheZhaoTopFilterLayout.isShown()) {
            activityDiseaseListZheZhaoTopFilterLayout.setVisibility(View.VISIBLE);
        }
    }

    public void hideZheZhaoView() {
        if (activityDiseaseListZheZhaoTopFilterLayout.isShown()) {
            activityDiseaseListZheZhaoTopFilterLayout.setVisibility(View.GONE);
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
                mPresenter.getZH(String.valueOf(longitude), String.valueOf(latitude));
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
}
