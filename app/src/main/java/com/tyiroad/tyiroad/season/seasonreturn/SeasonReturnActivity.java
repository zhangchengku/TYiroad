package com.tyiroad.tyiroad.season.seasonreturn;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tyiroad.tyiroad.Bean.Seasonhandlebean;
import com.tyiroad.tyiroad.MyApplication;
import com.tyiroad.tyiroad.R;
import com.tyiroad.tyiroad.handle.LoadDataDialog;
import com.tyiroad.tyiroad.mvp.MVPBaseActivity;
import com.tyiroad.tyiroad.season.seasonxq.SeasonxqActivity;
import com.tyiroad.tyiroad.utils.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SeasonReturnActivity extends MVPBaseActivity<SeasonReturnContract.View, SeasonReturnPresenter> implements SeasonReturnContract.View {

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
    @Bind(R.id.ldmx)
    TextView ldmx;
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
    @Bind(R.id.lryj)
    TextView lryj;
    @Bind(R.id.activity_disease_new_scrollview)
    ScrollView activityDiseaseNewScrollview;
    @Bind(R.id.disease_new_parent_layout)
    RelativeLayout diseaseNewParentLayout;
    @Bind(R.id.disease_new_driver_direction_quan_txt)
    TextView diseaseNewDriverDirectionQuanTxt;
    private LoadDataDialog loadDataDialog;
    private Seasonhandlebean.JJXYHBean JJXYH;
    private String SGQ, SGZ, SGH;
    private int directionSelInt = 0;// 1上行-右 2下行-左 3双向-全幅

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setStatusBarColor(this, R.color.theme_color);
        setContentView(R.layout.act_season_return);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        ButterKnife.bind(this);
        title.setText("养护验收");
        String str = "正在加载...";
        showLoadingDialogMethod(str);
        initdate();
        initsener();
    }

    private void showLoadingDialogMethod(String str) {
        if (loadDataDialog == null) {
            loadDataDialog = new LoadDataDialog(this);
        }
        loadDataDialog.setTitleStr(str);
        loadDataDialog.show();
    }

    private void initsener() {
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ldmx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SeasonReturnActivity.this, SeasonxqActivity.class);
                intent.putExtra("ID", getIntent().getStringExtra("ID"));
                intent.putExtra("LX", JJXYH.getLXMC());
                intent.putExtra("FX", JJXYH.getWZFX());
                intent.putExtra("SG", JJXYH.getSGLXMC());
                intent.putExtra("DJ", JJXYH.getDJ());
                intent.putExtra("DW", JJXYH.getSGLXDW());
                startActivityForResult(intent, 1);
            }
        });

    }


    private void initdate() {
        mPresenter.getdate(getIntent().getStringExtra("ID"));
    }

    @Override
    public void getData(Seasonhandlebean videoVos2) {
        JJXYH = videoVos2.getJJXYH().get(0);
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
        lryj.setText(replaceNull(videoVos2.getJJXYH().get(0).getYSYJ()));
        if (videoVos2.getPIC().size() > 0 && videoVos2.getPIC() != null) {
            SGQ = videoVos2.getPIC().get(0).getFILEPATH();
            SGZ = videoVos2.getPIC().get(1).getFILEPATH();
            SGH = videoVos2.getPIC().get(2).getFILEPATH();
            if (SGQ != null) {
                Glide.with(SeasonReturnActivity.this)
                        .asBitmap()
                        .apply(MyApplication.app.options)
                        .load(SGQ)
                        .into(shigongqian);
            }
            if (SGZ != null) {
                Glide.with(SeasonReturnActivity.this)
                        .asBitmap()
                        .apply(MyApplication.app.options)
                        .load(SGZ)
                        .into(shigongzhong);
            }
            if (SGH != null) {
                Glide.with(SeasonReturnActivity.this)
                        .asBitmap()
                        .apply(MyApplication.app.options)
                        .load(SGH)
                        .into(shigonghou);
            }
        }
        if (loadDataDialog != null && loadDataDialog.isShowing()) {
            loadDataDialog.cancel();
        }
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
    public static String replaceNull(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }

    @Override
    public void onRequestError(String msg) {

    }

    @Override
    public void onRequestEnd() {

    }
}


