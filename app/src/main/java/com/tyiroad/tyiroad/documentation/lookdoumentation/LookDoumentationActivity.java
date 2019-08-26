package com.tyiroad.tyiroad.documentation.lookdoumentation;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.tyiroad.tyiroad.R;
import com.tyiroad.tyiroad.monitoring.inmonitoring.INMonitoringGreAdapter;
import com.tyiroad.tyiroad.mvp.MVPBaseActivity;
import com.tyiroad.tyiroad.utils.NoScroolGridView;
import com.tyiroad.tyiroad.utils.Utils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class LookDoumentationActivity extends MVPBaseActivity<LookDoumentationContract.View, LookDoumentationPresenter> implements LookDoumentationContract.View {
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
    @Bind(R.id.lay_rel)
    RelativeLayout layRel;
    private LookGredAdapter lookGredAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_lookdoumenta);
        Utils.setStatusBarColor(this, R.color.theme_color);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        ButterKnife.bind(this);
        initdata();
    }

    private void initdata() {
        title.setText("资料上传");
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mPresenter.getData(getIntent().getStringExtra("ID"));
    }

    @Override
    public void getDatas(LookBean.DATABean DATABean) {
        edProgress.setText(DATABean.getGCMC());
        edCheck.setText(DATABean.getGCJD());
        edEvaluate.setText(DATABean.getZLLX());
        edCompletedbridge.setText(DATABean.getZLMC());
        edWzxx.setText(DATABean.getSCSJ());
        edDw.setText(DATABean.getSCDW());
        edR.setText(DATABean.getSCR());
        isBridgeFw.setText(DATABean.getLOCATION());
        ArrayList<String> listImgUrl = new ArrayList<>();
        if (DATABean.getIMGLIST().size()>0){
            for (int i = 0; i < DATABean.getIMGLIST().size(); i++) {
                listImgUrl.add(DATABean.getIMGLIST().get(i).getIMGURL());
            }
        }else {
            listImgUrl.add("ssss");
        }


        lookGredAdapter = new LookGredAdapter(this, listImgUrl);
        caiJiPictureAddGrid.setAdapter(lookGredAdapter);
    }
    @Override
    public void onRequestError(String msg) {

    }

    @Override
    public void onRequestEnd() {

    }
}
