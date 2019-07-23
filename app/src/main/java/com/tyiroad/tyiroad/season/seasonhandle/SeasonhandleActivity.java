package com.tyiroad.tyiroad.season.seasonhandle;


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
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

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
import com.tyiroad.tyiroad.Bean.SUCCESBEAN;
import com.tyiroad.tyiroad.Bean.Seasonhandlebean;
import com.tyiroad.tyiroad.Bean.Seasonhandlejson;
import com.tyiroad.tyiroad.MyApplication;
import com.tyiroad.tyiroad.R;
import com.tyiroad.tyiroad.disease.MinePopupWindow;
import com.tyiroad.tyiroad.handle.LoadDataDialog;
import com.tyiroad.tyiroad.handle.inwaihandle.ShowImgActivity;
import com.tyiroad.tyiroad.kdxf.DictationResult;
import com.tyiroad.tyiroad.mvp.MVPBaseActivity;
import com.tyiroad.tyiroad.utils.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SeasonhandleActivity extends MVPBaseActivity<SeasonhandleContract.View, SeasonhandlePresenter> implements SeasonhandleContract.View {

    @Bind(R.id.go_back)
    LinearLayout goBack;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.right)
    TextView right;
    @Bind(R.id.activity_new_disease_zhe_zhao_layout)
    View activityNewDiseaseZheZhaoLayout;
    @Bind(R.id.luxian_warm_txt)
    TextView luxianWarmTxt;
    @Bind(R.id.disease_new_road_line_txt_label)
    TextView diseaseNewRoadLineTxtLabel;
    @Bind(R.id.disease_new_road_line_txt)
    TextView diseaseNewRoadLineTxt;
    @Bind(R.id.disease_new_road_line_layout)
    RelativeLayout diseaseNewRoadLineLayout;
    @Bind(R.id.qdzh_x)
    TextView qdzhX;
    @Bind(R.id.qdzh_te)
    TextView qdzhTe;
    @Bind(R.id.qdzh_dw)
    TextView qdzhDw;
    @Bind(R.id.qdzh_text1)
    TextView qdzhText1;
    @Bind(R.id.qdzh_lj)
    TextView qdzhLj;
    @Bind(R.id.qdzh_text2)
    TextView qdzhText2;
    @Bind(R.id.zdzh_x)
    TextView zdzhX;
    @Bind(R.id.zdzh_te)
    TextView zdzhTe;
    @Bind(R.id.zdzh_dw)
    TextView zdzhDw;
    @Bind(R.id.zdzh_text1)
    TextView zdzhText1;
    @Bind(R.id.zdzh_lj)
    TextView zdzhLj;
    @Bind(R.id.zdzh_text2)
    TextView zdzhText2;
    @Bind(R.id.disease_new_driver_direction_shang_txt)
    TextView diseaseNewDriverDirectionShangTxt;
    @Bind(R.id.disease_new_driver_direction_xia_txt)
    TextView diseaseNewDriverDirectionXiaTxt;
    @Bind(R.id.disease_custom_edit_item_bhlx_label_txt)
    TextView diseaseCustomEditItemBhlxLabelTxt;
    @Bind(R.id.disease_custom_edit_item_bhlx_txt_btn)
    TextView diseaseCustomEditItemBhlxTxtBtn;
    @Bind(R.id.bhlx)
    LinearLayout bhlx;
    @Bind(R.id.disease_custom_edit_item_bhmc_label_txt)
    TextView diseaseCustomEditItemBhmcLabelTxt;
    @Bind(R.id.disease_custom_edit_item_bhmc_txt_btn)
    TextView diseaseCustomEditItemBhmcTxtBtn;
    @Bind(R.id.bhmc)
    LinearLayout bhmc;
    @Bind(R.id.czwt_et)
    TextView czwtEt;
    @Bind(R.id.textView3)
    TextView textView3;
    @Bind(R.id.shigongqian)
    ImageView shigongqian;
    @Bind(R.id.shigongqiande)
    ImageView shigongqiande;
    @Bind(R.id.shigongzhong)
    ImageView shigongzhong;
    @Bind(R.id.shigongzhongde)
    ImageView shigongzhongde;
    @Bind(R.id.shigonghou)
    ImageView shigonghou;
    @Bind(R.id.shigonghoude)
    ImageView shigonghoude;
    @Bind(R.id.activity_disease_new_scrollview)
    ScrollView activityDiseaseNewScrollview;
    @Bind(R.id.disease_new_parent_layout)
    RelativeLayout diseaseNewParentLayout;
    @Bind(R.id.disease_new_driver_direction_quan_txt)
    TextView diseaseNewDriverDirectionQuanTxt;
    private int directionSelInt = 0;// 1上行-右 2下行-左 3双向-全幅
    private String dictationResultStr;
    private static String APPID = "5bf211f5";
    private List<String> xf = new ArrayList<>();
    private List<String> zong = new ArrayList<>();
    private boolean shigongqianyesbig = false;
    private boolean shigongzhongyesbig = false;
    private boolean shigonghoubig = false;
    private int type;
    private MinePopupWindow minePopupWindow;
    private ArrayList<String> sGQTP = new ArrayList<>();
    private ArrayList<String> sGZTP = new ArrayList<>();
    private ArrayList<String> sGHTP = new ArrayList<>();
    private String SGQTP = "";
    private String SGZTP = "";
    private String SGHTP = "";
    private int childViewPosition;
    private String cameraPath;
    private Gson gson = new Gson();
    private LoadDataDialog loadDataDialog;
    private String SGQ, SGZ, SGH;

    public void setChildViewPosition(int childViewPosition) {
        this.childViewPosition = childViewPosition;
    }

    public void setCameraPath(String cameraPath) {
        this.cameraPath = cameraPath;
    }

    private final int CHOOSE_PICTURE_CODE = 1;
    private final int CAMERA_CODE = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setStatusBarColor(this, R.color.theme_color);
        setContentView(R.layout.act_season_handle);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        ButterKnife.bind(this);
        title.setText("养护维修");
        String str = "正在加载...";
        showLoadingDialogMethod(str);
        initdate();
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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

    private int isSaveOk() {
        int result = 5;
        if (Utils.isNull(SGQTP)) {
            result = 1;
        }
        if (Utils.isNull(SGHTP)) {
            result = 2;
        }
        if (Utils.isNull(SGZTP)) {
            result = 3;
        }
        return result;
    }

    private void initdate() {
        mPresenter.getdate(getIntent().getStringExtra("ID"));
    }

    @Override
    public void getData(Seasonhandlebean videoVos2) {
        if (videoVos2.getJJXYH().size() > 0) {
            diseaseNewRoadLineTxt.setText(replaceNull(videoVos2.getJJXYH().get(0).getLXMC()));
            String zhStr = replaceNull(videoVos2.getJJXYH().get(0).getQDZH());
            if (zhStr.contains(".")) {
                String zhone = zhStr.substring(0, zhStr.indexOf("."));
                String zhtwo = zhStr.substring(zhStr.indexOf(".") + 1, zhStr.length());
                qdzhText1.setText(zhone);
                qdzhText2.setText(zhtwo);
            } else {
                qdzhText1.setText(zhStr);
            }
            String ZzhStr = replaceNull(videoVos2.getJJXYH().get(0).getZDZH());
            if (ZzhStr.contains(".")) {
                String zhone = ZzhStr.substring(0, ZzhStr.indexOf("."));
                String zhtwo = ZzhStr.substring(ZzhStr.indexOf(".") + 1, ZzhStr.length());
                zdzhText1.setText(zhone);
                zdzhText2.setText(zhtwo);
            } else {
                zdzhText1.setText(ZzhStr);
            }
            if (videoVos2.getJJXYH().get(0).getWZFX().equals("上行右")) {
                setShangXingSelect();
            } else if (videoVos2.getJJXYH().get(0).getWZFX().equals("下行左")){
                setXiaXingSelect();
            }else {
                setQuanFuSelect();
            }
            diseaseCustomEditItemBhlxTxtBtn.setText(replaceNull(videoVos2.getJJXYH().get(0).getSGDWMC()));
            diseaseCustomEditItemBhmcTxtBtn.setText(replaceNull(videoVos2.getJJXYH().get(0).getSGLXMC()));
            czwtEt.setText(replaceNull(videoVos2.getJJXYH().get(0).getRWYQ()));
            if (loadDataDialog != null && loadDataDialog.isShowing()) {
                loadDataDialog.cancel();
            }

            if (videoVos2.getPIC().size() > 0 && videoVos2.getPIC() != null) {
                if (videoVos2.getPIC().size() == 1) {
                    SGQ = videoVos2.getPIC().get(0).getFILEPATH();
                } else if (videoVos2.getPIC().size() == 2) {
                    SGZ = videoVos2.getPIC().get(1).getFILEPATH();
                } else {
                    SGH = videoVos2.getPIC().get(2).getFILEPATH();
                }
                if (SGQ != null) {
                    Glide.with(SeasonhandleActivity.this)
                            .asBitmap()
                            .apply(MyApplication.app.options)
                            .load(SGQ)
                            .into(shigongqian);
                }
                if (SGZ != null) {
                    Glide.with(SeasonhandleActivity.this)
                            .asBitmap()
                            .apply(MyApplication.app.options)
                            .load(SGZ)
                            .into(shigongzhong);
                }
                if (SGH != null) {
                    Glide.with(SeasonhandleActivity.this)
                            .asBitmap()
                            .apply(MyApplication.app.options)
                            .load(SGH)
                            .into(shigonghou);
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

    public static String replaceNull(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }

    private void setShangXingSelect() {
        if (directionSelInt != 1) {
            directionSelInt = 1;
            diseaseNewDriverDirectionShangTxt.setTextColor(getResources().getColor(R.color.white));
            diseaseNewDriverDirectionShangTxt.setBackgroundDrawable(getResources().getDrawable(R.drawable.month_btn_shap));
            diseaseNewDriverDirectionXiaTxt.setTextColor(getResources().getColor(R.color.select_month_text_bg));
            diseaseNewDriverDirectionXiaTxt.setBackgroundDrawable(getResources().getDrawable(R.drawable.direction_select_shap));
        }
    }

    private void setXiaXingSelect() {
        if (directionSelInt != 2) {
            directionSelInt = 2;
            diseaseNewDriverDirectionShangTxt.setTextColor(getResources().getColor(R.color.select_month_text_bg));
            diseaseNewDriverDirectionShangTxt.setBackgroundDrawable(getResources().getDrawable(R.drawable.direction_select_shap));
            diseaseNewDriverDirectionXiaTxt.setTextColor(getResources().getColor(R.color.white));
            diseaseNewDriverDirectionXiaTxt.setBackgroundDrawable(getResources().getDrawable(R.drawable.month_btn_shap));
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
}
