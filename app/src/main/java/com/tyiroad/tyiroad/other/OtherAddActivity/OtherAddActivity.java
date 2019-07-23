package com.tyiroad.tyiroad.other.OtherAddActivity;


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

import com.google.gson.Gson;
import com.tooklkit.Tooklkit;
import com.tyiroad.tyiroad.Bean.Basebean;
import com.tyiroad.tyiroad.Bean.OtherAddBean;
import com.tyiroad.tyiroad.Bean.Otherheaderbean;
import com.tyiroad.tyiroad.MyApplication;
import com.tyiroad.tyiroad.R;
import com.tyiroad.tyiroad.handle.LoadDataDialog;
import com.tyiroad.tyiroad.mvp.MVPBaseActivity;
import com.tyiroad.tyiroad.utils.DiseaseNewSelectObjectListener;
import com.tyiroad.tyiroad.utils.NoScroolGridView;
import com.tyiroad.tyiroad.utils.OtherAddTimePop;
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

public class OtherAddActivity extends MVPBaseActivity<OtherAddContract.View, OtherAddPresenter> implements OtherAddContract.View {

    @Bind(R.id.go_back)
    LinearLayout goBack;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.right)
    TextView right;
    @Bind(R.id.act_other_dw)
    TextView actOtherDw;
    @Bind(R.id.act_other_lx)
    TextView actOtherLx;
    @Bind(R.id.act_other_lx_lay)
    LinearLayout actOtherLxLay;
    @Bind(R.id.act_other_time)
    TextView actOtherTime;
    @Bind(R.id.qdzh_x)
    TextView qdzhX;
    @Bind(R.id.qdzh_te)
    TextView qdzhTe;
    @Bind(R.id.qdzh_dw)
    TextView qdzhDw;
    @Bind(R.id.act_other_qdzh)
    EditText actOtherQdzh;
    @Bind(R.id.qdzh_lj)
    TextView qdzhLj;
    @Bind(R.id.act_other_qdzh_m)
    EditText actOtherQdzhM;
    @Bind(R.id.zdzh_x)
    TextView zdzhX;
    @Bind(R.id.zdzh_te)
    TextView zdzhTe;
    @Bind(R.id.zdzh_dw)
    TextView zdzhDw;
    @Bind(R.id.act_other_zdzh)
    EditText actOtherZdzh;
    @Bind(R.id.zdzh_lj)
    TextView zdzhLj;
    @Bind(R.id.act_other_zdzh_m)
    EditText actOtherZdzhM;
    @Bind(R.id.act_other_shang)
    TextView actOtherShang;
    @Bind(R.id.act_other_xia)
    TextView actOtherXia;
    @Bind(R.id.act_other_shuang)
    TextView actOtherShuang;
    @Bind(R.id.act_other_gcxm)
    EditText actOtherGcxm;
    @Bind(R.id.act_other_gcxmdw)
    EditText actOtherGcxmdw;
    @Bind(R.id.act_other_dj)
    EditText actOtherDj;
    @Bind(R.id.act_other_sl)
    EditText actOtherSl;
    @Bind(R.id.act_other_photo_grid)
    NoScroolGridView actOtherPhotoGrid;
    @Bind(R.id.act_other_photo_grid_item)
    LinearLayout actOtherPhotoGridItem;
    @Bind(R.id.act_other_photo_lay)
    LinearLayout actOtherPhotoLay;
    @Bind(R.id.activity_disease_new_scrollview)
    ScrollView activityDiseaseNewScrollview;
    @Bind(R.id.otherxq_add)
    TextView otherxqAdd;
    @Bind(R.id.activity_new_disease_zhe_zhao_layout)
    View activityNewDiseaseZheZhaoLayout;
    @Bind(R.id.Otherxq_Rly)
    RelativeLayout OtherxqRly;
    @Bind(R.id.act_other_time_lay)
    LinearLayout actOtherTimeLay;
    private ArrayList<String> LxResult = new ArrayList<>();
    private List<Otherheaderbean.YYLXDATABean> LxResultint = new ArrayList<>();
    private Otherheaderbean.YYLXDATABean Lxbe;
    private OtherAddPop LxPop;
    private OtherAddTimePop TimePop;
    private SimpleDateFormat simpleDateFormat;
    private int directionSelInt = 1;// 1上行-右 2下行-左 3双向-全幅
    private OtherAddGridAdapter otherAddGridAdapter;
    private Gson gson = new Gson();
    private LoadDataDialog loadDataDialog;

    public void setCameraPath(String cameraPath) {
        this.cameraPath = cameraPath;
    }
    public void setChildViewPosition(int childViewPosition) {
        this.childViewPosition = childViewPosition;
    }
    private String cameraPath;//拍照获取图片的地址
    private int childViewPosition;//点击拍照获取的childveiw下标
    private Map<Integer, OtherAddGridAdapter> mapAdapter = new HashMap<>();
    private final int CHOOSE_PICTURE_CODE = 1;
    private final int CAMERA_CODE = 2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setStatusBarColor(this, R.color.theme_color);
        setContentView(R.layout.act_other_add);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        ButterKnife.bind(this);
        initpop();
        initview();
        linstener();
    }

    private void initpop() {
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        LxPop = new OtherAddPop(this, "请选择路线", LxResult, new DiseaseNewSelectObjectListener() {
            @Override
            public void selectPosition(int position) {
                actOtherLx.setText(LxResult.get(position));
                Lxbe = LxResultint.get(position);
            }
        });
        TimePop = new OtherAddTimePop(this, new PopSelectListener() {
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
                actOtherTime.setText(selDataStr);
            }
        });
    }
    private boolean iszh(String Qdzh,String QdzhM) {
        if(Utils.isNull(QdzhM)){
            QdzhM="0";
        }
        if (!Utils.isNull(Qdzh)) {
            Double zh = Double.parseDouble(Qdzh + "." + QdzhM);
            if (Lxbe != null) {
                Double qdzh = Double.parseDouble(Lxbe.getQDZH());
                Double zdzh = Double.parseDouble(Lxbe.getZDZH());
                if (qdzh <= zh && zh <= zdzh) {
                    return  true;
                } else {
                    MyApplication.app.customToast("您输入的桩号不在路线范围内，请重新输入");
                    return  false;
                }
            } else {
                MyApplication.app.customToast("请选择路线");
                return false;
            }
        }else {
            MyApplication.app.customToast("请输入起终点桩号");
            return  false;
        }
    }
    private void showLoadingDialogMethod(String str) {
        if (loadDataDialog == null) {
            loadDataDialog = new LoadDataDialog(this);
        }
        loadDataDialog.setTitleStr(str);
        loadDataDialog.show();
    }
    private void linstener() {
        otherxqAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Qdzh = actOtherQdzh.getText().toString();
                String QdzhM = actOtherQdzhM.getText().toString();
                String Zdzh = actOtherZdzh.getText().toString();
                String ZdzhM = actOtherZdzhM.getText().toString();
                if (iszh( Qdzh,QdzhM)==false){
                    return;
                }
                if (iszh( Zdzh,ZdzhM)==false){
                    return;
                }
                if (Utils.isNull(actOtherSl.getText().toString())){
                    MyApplication.app.customToast("请输入数量");
                    return;
                }
                if (Utils.isNull(actOtherDj.getText().toString())){
                    MyApplication.app.customToast("请输入单价");
                    return;
                }
                String str = "正在加载...";
                showLoadingDialogMethod(str);
                otherxqAdd.setClickable(false);
                OtherAddBean otherXqAddbean = new OtherAddBean();
                otherXqAddbean.setSTATE("1");
                otherXqAddbean.setCREATOR(MyApplication.spUtils.getString("dlr"));
                otherXqAddbean.setGYDW(MyApplication.spUtils.getString("dqgydwid"));
                otherXqAddbean.setGYDWMC(MyApplication.spUtils.getString("dqgydwmc"));
                otherXqAddbean.setLXCODE(Lxbe.getLXID());
                otherXqAddbean.setLXMC(Lxbe.getLXMC());
                otherXqAddbean.setQDZH(Double.parseDouble(Qdzh + "." + QdzhM)+"");
                otherXqAddbean.setZDZH(Double.parseDouble(Zdzh + "." + ZdzhM)+"");
                otherXqAddbean.setGCXM(actOtherGcxm.getText().toString());
                otherXqAddbean.setJLDW(actOtherGcxmdw.getText().toString());
                otherXqAddbean.setSL(actOtherSl.getText().toString());
                otherXqAddbean.setDJ(actOtherDj.getText().toString());
                otherXqAddbean.setGZL("10");
                otherXqAddbean.setSBSJ(actOtherTime.getText().toString());
                otherXqAddbean.setSBR(MyApplication.spUtils.getString("dlr"));
                String WZFX = "";
                if (directionSelInt == 1) {
                    WZFX = actOtherShang.getText().toString();
                } else if (directionSelInt == 2) {
                    WZFX = actOtherXia.getText().toString();
                } else if (directionSelInt == 3) {
                    WZFX = actOtherShuang.getText().toString();
                }
                otherXqAddbean.setWZFX(WZFX);
                String imgStr = "";
                ArrayList<String> listImgUrl = otherAddGridAdapter.getListImgUrl();
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
                        ArrayList<OtherAddBean.PICBean> listPic = new ArrayList<>();
                        for (int i = 0; i < strArr.length; i++) {
                            OtherAddBean.PICBean picInfo = new OtherAddBean.PICBean();
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
                        otherXqAddbean.setPIC(listPic);
                    }
                }
                String json = gson.toJson(otherXqAddbean);
                Log.e("测试",json);
                mPresenter.addOtherXq(json);
            }
        });
        actOtherLxLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LxPop.show(OtherxqRly);
            }
        });
        actOtherTimeLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePop.show(OtherxqRly);
            }
        });
        actOtherShang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setShangXingSelect();
            }
        });
        actOtherXia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setXiaXingSelect();
            }
        });
        actOtherShuang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setQuanFuSelect();
            }
        });
    }



    private void initview() {
        title.setText("新增记录");
        actOtherDw.setText(MyApplication.spUtils.getString("dqgydwmc"));
        String DATE = getIntent().getStringExtra("DATE");
        Otherheaderbean otherheaderbean = new Gson().fromJson(DATE, Otherheaderbean.class);
        getlxdate(otherheaderbean.getYYLXDATA());
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar calendar = Calendar.getInstance();
        String currentDate = simpleDateFormat.format(calendar.getTime());
        actOtherTime.setText(currentDate);
        ArrayList<Drawable> listPicture = new ArrayList<>();
        ArrayList<String> listImgUrl = new ArrayList<>();
        Drawable addPicture = getResources().getDrawable(R.drawable.sjq_add_tupian_sel);
        listPicture.add(addPicture);
        final int childPosition = OtherxqRly.getChildCount();
        otherAddGridAdapter = new OtherAddGridAdapter(this, listPicture, listImgUrl, childPosition);
        actOtherPhotoGrid.setAdapter(otherAddGridAdapter);
        mapAdapter.put(childPosition, otherAddGridAdapter);
    }

    private void getlxdate(List<Otherheaderbean.YYLXDATABean> yylxdata) {
        LxResultint = yylxdata;
        if (LxResultint != null && LxResultint.size() > 0) {
            LxResult.clear();
            for (int i = 0; i < LxResultint.size(); i++) {
                LxResult.add(LxResultint.get(i).getLXMC() + LxResultint.get(i).getQDZH() + "-" + LxResultint.get(i).getZDZH());
                if (i == 0) {
                    Lxbe = LxResultint.get(i);
                    actOtherLx.setText(LxResultint.get(i).getLXMC() + LxResultint.get(i).getQDZH() + "-" + LxResultint.get(i).getZDZH());
                }
            }
            LxPop.notifityData();
        }
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
    private void setShangXingSelect() {
        if (directionSelInt != 1) {
            directionSelInt = 1;
            actOtherShang.setTextColor(getResources().getColor(R.color.white));
            actOtherShang.setBackgroundDrawable(getResources().getDrawable(R.drawable.month_btn_shap));
            actOtherXia.setTextColor(getResources().getColor(R.color.select_month_text_bg));
            actOtherXia.setBackgroundDrawable(getResources().getDrawable(R.drawable.direction_select_shap));
            actOtherShuang.setTextColor(getResources().getColor(R.color.select_month_text_bg));
            actOtherShuang.setBackgroundDrawable(getResources().getDrawable(R.drawable.direction_select_shap));
        }
    }
    private void setXiaXingSelect() {
        if (directionSelInt != 2) {
            directionSelInt = 2;
            actOtherShang.setTextColor(getResources().getColor(R.color.select_month_text_bg));
            actOtherShang.setBackgroundDrawable(getResources().getDrawable(R.drawable.direction_select_shap));
            actOtherXia.setTextColor(getResources().getColor(R.color.white));
            actOtherXia.setBackgroundDrawable(getResources().getDrawable(R.drawable.month_btn_shap));
            actOtherShuang.setTextColor(getResources().getColor(R.color.select_month_text_bg));
            actOtherShuang.setBackgroundDrawable(getResources().getDrawable(R.drawable.direction_select_shap));
        }
    }

    private void setQuanFuSelect() {
        if (directionSelInt != 3) {
            directionSelInt = 3;
            actOtherShang.setTextColor(getResources().getColor(R.color.select_month_text_bg));
            actOtherShang.setBackgroundDrawable(getResources().getDrawable(R.drawable.direction_select_shap));
            actOtherXia.setTextColor(getResources().getColor(R.color.select_month_text_bg));
            actOtherXia.setBackgroundDrawable(getResources().getDrawable(R.drawable.direction_select_shap));
            actOtherShuang.setTextColor(getResources().getColor(R.color.white));
            actOtherShuang.setBackgroundDrawable(getResources().getDrawable(R.drawable.month_btn_shap));
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
                    final OtherAddGridAdapter addPictureAdapter = mapAdapter.get(position);
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
                                    Bitmap bitmap = Tooklkit.getImageThumbnail(imgPath, Tooklkit.dip2px(OtherAddActivity.this, 480),
                                            Tooklkit.dip2px(OtherAddActivity.this, 480));
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
                                    Log.i("图片压缩失败", "");
                                }
                            }).launch();    //启动压缩
                }
            }
        } else if (requestCode == CAMERA_CODE) {//拍照获取照片
            if (resultCode == -1) {
                if (!Utils.isNull(cameraPath) && childViewPosition != -1) {
                    Utils.dealBitmapRotate(cameraPath);
                    final OtherAddGridAdapter addPictureAdapter = mapAdapter.get(childViewPosition);
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
                                    Bitmap bitmap = Tooklkit.getImageThumbnail(imgPath, Tooklkit.dip2px(OtherAddActivity.this, 480),
                                            Tooklkit.dip2px(OtherAddActivity.this, 480));
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
    public void onRequestError(String msg) {

    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void getData(Basebean videoVos2) {
        if (loadDataDialog != null && loadDataDialog.isShowing()) {
            loadDataDialog.cancel();
        }
        otherxqAdd.setClickable(true);
        if (videoVos2.getSTATE().equals("1")){
            MyApplication.app.customToast("保存成功");
            Intent intent = new Intent();
            setResult(2, intent);
            finish();
        }
    }
}