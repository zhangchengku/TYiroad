package com.tyiroad.tyiroad.quality.inquality;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.tyiroad.tyiroad.R;
import com.tyiroad.tyiroad.handle.LoadDataDialog;
import com.tyiroad.tyiroad.monitoring.inmonitoring.INMonitoringGreAdapter;
import com.tyiroad.tyiroad.mvp.MVPBaseActivity;
import com.tyiroad.tyiroad.quality.NoScroolGridView;
import com.tyiroad.tyiroad.utils.Utils;
import com.tyiroad.tyiroad.utils.adapter.CommonAdapter;
import com.tyiroad.tyiroad.utils.adapter.ViewHolder;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class INQualityActivity extends MVPBaseActivity<INQualityContract.View, INQualityPresenter> implements INQualityContract.View {

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
    private CommonAdapter<INQualityBean.DATABean.DATAJCXMMXBean> adapter;
    private INMonitoringGreAdapter iNMonitoringGreAdapter;
    private LoadDataDialog loadDataDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        Utils.setStatusBarColor(this, R.color.theme_color);
        setContentView(R.layout.act_quality);
        ButterKnife.bind(this);
        initview();

    }

    private void initview() {
        String str = "正在加载...";
        showLoadingDialogMethod(str);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title.setText("质量检验");
        layFoot.setVisibility(View.GONE);
        INQuality.setVisibility(View.GONE);
        mPresenter.initData(getIntent().getStringExtra("MOID"));
    }

    private void showLoadingDialogMethod(String str) {
        if (loadDataDialog == null) {
            loadDataDialog = new LoadDataDialog(this);
        }
        loadDataDialog.setTitleStr(str);
        loadDataDialog.show();
    }

    @Override
    public void onRequestError(String msg) {

    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void getData(INQualityBean.DATABean videoVos2) {
        edJcfw.setText(videoVos2.getDATAMX().get(0).getJCFW());
        edProgress.setText(videoVos2.getDATAMX().get(0).getXMMC());
        edCheck.setText(videoVos2.getDATAMX().get(0).getFBGCMC());
        edEvaluate.setText(videoVos2.getDATAMX().get(0).getFXGCMC());
        edCompletedbridge.setText(videoVos2.getDATAMX().get(0).getGCBW());
        edIncompletebridge.setText(videoVos2.getDATAMX().get(0).getJCR());
        isBridgeFw.setText(videoVos2.getDATAMX().get(0).getDLWZ());
        explainEt.setText(videoVos2.getDATAMX().get(0).getBHGSM());
        adapter = new CommonAdapter<INQualityBean.DATABean.DATAJCXMMXBean>(this,
                R.layout.lay, videoVos2.getDATAJCXMMX()) {
            @Override
            protected void convert(final ViewHolder holder, INQualityBean.DATABean.DATAJCXMMXBean tubiaoVo, final int position) {
                holder.setText(R.id.one, tubiaoVo.getJCXMMC());
                holder.setText(R.id.two, tubiaoVo.getJCFWZ());
                holder.setText(R.id.three, tubiaoVo.getHGDS());
                RadioGroup rgsex = (RadioGroup) holder.getView(R.id.rg_sex);
                final RadioButton rbMale = (RadioButton) holder.getView(R.id.rb_Male);
                final RadioButton rbFeMale = (RadioButton) holder.getView(R.id.rb_FeMale);
                if (tubiaoVo.getJCJG().equals("1")) {
                    rbMale.setChecked(true);
                } else {
                    rbFeMale.setChecked(true);
                }
                rbMale.setClickable(false);
                rbFeMale.setClickable(false);
            }
        };
        listview.setAdapter(adapter);
        ArrayList<String> listImgUrl = new ArrayList<>();
        for (int i = 0; i < videoVos2.getDATAPICMX().size(); i++) {
            listImgUrl.add(videoVos2.getDATAPICMX().get(i).getFILEPATH());
        }
        iNMonitoringGreAdapter = new INMonitoringGreAdapter(this, listImgUrl);
        caiJiPictureAddGrid.setAdapter(iNMonitoringGreAdapter);
        if (loadDataDialog != null && loadDataDialog.isShowing()) {
            loadDataDialog.cancel();
        }
    }
}