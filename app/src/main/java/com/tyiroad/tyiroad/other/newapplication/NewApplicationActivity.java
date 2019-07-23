package com.tyiroad.tyiroad.other.newapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.tyiroad.tyiroad.Bean.Basebean;
import com.tyiroad.tyiroad.Bean.OtherDetailsBean;
import com.tyiroad.tyiroad.MyApplication;
import com.tyiroad.tyiroad.R;
import com.tyiroad.tyiroad.handle.LoadDataDialog;
import com.tyiroad.tyiroad.mvp.MVPBaseActivity;
import com.tyiroad.tyiroad.utils.NoScroolGridView;
import com.tyiroad.tyiroad.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */
public class NewApplicationActivity extends MVPBaseActivity<NewApplicationContract.View, NewApplicationPresenter> implements NewApplicationContract.View {
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
    @Bind(R.id.act_other_time_lay)
    LinearLayout actOtherTimeLay;
    @Bind(R.id.qdzh_x)
    TextView qdzhX;
    @Bind(R.id.qdzh_te)
    TextView qdzhTe;
    @Bind(R.id.qdzh_dw)
    TextView qdzhDw;
    @Bind(R.id.act_other_qdzh)
    TextView actOtherQdzh;
    @Bind(R.id.qdzh_lj)
    TextView qdzhLj;
    @Bind(R.id.act_other_qdzh_m)
    TextView actOtherQdzhM;
    @Bind(R.id.zdzh_x)
    TextView zdzhX;
    @Bind(R.id.zdzh_te)
    TextView zdzhTe;
    @Bind(R.id.zdzh_dw)
    TextView zdzhDw;
    @Bind(R.id.act_other_zdzh)
    TextView actOtherZdzh;
    @Bind(R.id.zdzh_lj)
    TextView zdzhLj;
    @Bind(R.id.act_other_zdzh_m)
    TextView actOtherZdzhM;
    @Bind(R.id.act_other_shang)
    TextView actOtherShang;
    @Bind(R.id.act_other_xia)
    TextView actOtherXia;
    @Bind(R.id.act_other_shuang)
    TextView actOtherShuang;
    @Bind(R.id.act_other_gcxm)
    TextView actOtherGcxm;
    @Bind(R.id.act_other_gcxmdw)
    TextView actOtherGcxmdw;
    @Bind(R.id.act_other_dj)
    TextView actOtherDj;
    @Bind(R.id.act_other_sl)
    TextView actOtherSl;
    @Bind(R.id.act_other_photo_grid)
    NoScroolGridView actOtherPhotoGrid;
    @Bind(R.id.act_other_photo_grid_item)
    LinearLayout actOtherPhotoGridItem;
    @Bind(R.id.act_other_photo_lay)
    LinearLayout actOtherPhotoLay;
    @Bind(R.id.activity_disease_new_scrollview)
    ScrollView activityDiseaseNewScrollview;
    @Bind(R.id.activity_new_disease_zhe_zhao_layout)
    View activityNewDiseaseZheZhaoLayout;
    @Bind(R.id.Otherxq_Rly)
    RelativeLayout OtherxqRly;
    @Bind(R.id.otherxq_add)
    TextView otherxqAdd;
    private int directionSelInt = 1;
    private NewApplicationGridAdapter newApplicationGridAdapter;
    private Map<Integer, NewApplicationGridAdapter> mapAdapter = new HashMap<>();
    private LoadDataDialog loadDataDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setStatusBarColor(this, R.color.theme_color);
        setContentView(R.layout.act_other_newapplication);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        ButterKnife.bind(this);
        String str = "正在加载...";
        showLoadingDialogMethod(str);
        initdata();
        linstener();
    }
    private void showLoadingDialogMethod(String str) {
        if (loadDataDialog == null) {
            loadDataDialog = new LoadDataDialog(this);
        }
        loadDataDialog.setTitleStr(str);
        loadDataDialog.show();
    }
    private void linstener() {
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        otherxqAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = "正在加载...";
                showLoadingDialogMethod(str);
                mPresenter.AddData(getIntent().getStringExtra("dataid"),"2");
            }
        });
    }

    private void initdata() {
        title.setText("新申请");
        mPresenter.getDetails(getIntent().getStringExtra("dataid"));
    }

    @Override
    public void getData(OtherDetailsBean videoVo) {
        if (videoVo.getSTATE().equals("1")) {
            actOtherDw.setText(videoVo.getLISTDATA().get(0).getGYDWMC());
            actOtherLx.setText(videoVo.getLISTDATA().get(0).getLXMC());
            actOtherTime.setText(videoVo.getLISTDATA().get(0).getMODIFYTIME().replace("T"," "));
            String qdzh = videoVo.getLISTDATA().get(0).getQDZH() + "";
            actOtherQdzh.setText(qdzh.substring(0, qdzh.lastIndexOf(".")));
            actOtherQdzhM.setText(qdzh.substring(qdzh.lastIndexOf(".") + 1, qdzh.length()));
            String zdzh = videoVo.getLISTDATA().get(0).getZDZH() + "";
            actOtherZdzh.setText(zdzh.substring(0, zdzh.lastIndexOf(".")));
            actOtherZdzhM.setText(zdzh.substring(zdzh.lastIndexOf(".") + 1, zdzh.length()));
            String xsfxStr = videoVo.getLISTDATA().get(0).getWZFX();
            if (xsfxStr.equals(actOtherShang.getText())) {
                setShangXingSelect();
            } else if (xsfxStr.equals(actOtherXia.getText())) {
                setXiaXingSelect();
            } else {
                setQuanFuSelect();
            }
            actOtherGcxm.setText(videoVo.getLISTDATA().get(0).getGCXM());
            actOtherGcxmdw.setText(videoVo.getLISTDATA().get(0).getJLDW());
            actOtherDj.setText(videoVo.getLISTDATA().get(0).getDJ());
            actOtherSl.setText(videoVo.getLISTDATA().get(0).getSL());
            ArrayList<String> listImgUrl = new ArrayList<>();
            if (videoVo.getFILEDATA().size()>0){
                for (int i = 0; i < videoVo.getFILEDATA().size(); i++) {
                    listImgUrl.add( videoVo.getFILEDATA().get(i).getFILEPATH());
                }
            }else {
                listImgUrl.add(String.valueOf(R.drawable.morentu));
            }
            final int childPosition = OtherxqRly.getChildCount();
            newApplicationGridAdapter = new NewApplicationGridAdapter(this, listImgUrl);
            actOtherPhotoGrid.setAdapter(newApplicationGridAdapter);
            mapAdapter.put(childPosition, newApplicationGridAdapter);
        }
        if (loadDataDialog != null && loadDataDialog.isShowing()) {
            loadDataDialog.cancel();
        }
    }

    @Override
    public void getAddData(Basebean basebean) {
        if (loadDataDialog != null && loadDataDialog.isShowing()) {
            loadDataDialog.cancel();
        }
        if (basebean.getSTATE().equals("1")){
            MyApplication.app.customToast("上报成功");
            Intent intent = new Intent();
            setResult(2, intent);
            finish();
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
    public void onRequestError(String msg) {

    }

    @Override
    public void onRequestEnd() {

    }


}
