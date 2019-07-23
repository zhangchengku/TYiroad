package com.tyiroad.tyiroad.monitoring.inmonitoring;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tyiroad.tyiroad.R;
import com.tyiroad.tyiroad.handle.LoadDataDialog;
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

public class INMonitoringActivity extends MVPBaseActivity<INMonitoringContract.View, INMonitoringPresenter> implements INMonitoringContract.View {


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
    @Bind(R.id.road_edit)
    EditText roadEdit;
    @Bind(R.id.yuyin)
    ImageView yuyin;
    @Bind(R.id.rrrrr)
    RelativeLayout rrrrr;
    @Bind(R.id.cai_ji_picture_add_grid)
    NoScroolGridView caiJiPictureAddGrid;
    @Bind(R.id.disease_new_bh_content_layou)
    LinearLayout diseaseNewBhContentLayou;
    @Bind(R.id.rq_te)
    TextView rqTe;
    @Bind(R.id.time_layout)
    LinearLayout timeLayout;
    @Bind(R.id.sgdw_te)
    TextView sgdwTe;
    @Bind(R.id.is_bridge_fw)
    TextView isBridgeFw;
    @Bind(R.id.handle_et)
    EditText handleEt;
    @Bind(R.id.handle_name)
    TextView handleName;
    @Bind(R.id.handle_time)
    TextView handleTime;
    @Bind(R.id.check_et)
    EditText checkEt;
    @Bind(R.id.check_name)
    TextView checkName;
    @Bind(R.id.check_time)
    TextView checkTime;
    @Bind(R.id.bg_layout)
    RelativeLayout bgLayout;
    private INMonitoringGreAdapter iNMonitoringGreAdapter;
    private LoadDataDialog loadDataDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        Utils.setStatusBarColor(this, R.color.theme_color);
        setContentView(R.layout.act_inmonitoringxq);
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
        title.setText("过程监控");
        mPresenter.initData(getIntent().getStringExtra("MOID"));
    }

    @Override
    public void onRequestError(String msg) {

    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void getData(INMmnitoringBean videoVos2) {
        edProgress.setText(videoVos2.getDATA().get(0).getXMMC());
        roadEdit.setText(videoVos2.getDATA().get(0).getXMMS());
        rqTe.setText(videoVos2.getDATA().get(0).getSCSJ());
        sgdwTe.setText(videoVos2.getDATA().get(0).getSCDWMC());
        ArrayList<String> listImgUrl = new ArrayList<>();
        for (int i = 0; i < videoVos2.getPICDATA().size(); i++) {
            listImgUrl.add(videoVos2.getPICDATA().get(i).getFILEPATH());
        }
        iNMonitoringGreAdapter = new INMonitoringGreAdapter(this, listImgUrl);
        caiJiPictureAddGrid.setAdapter(iNMonitoringGreAdapter);
        if (loadDataDialog != null && loadDataDialog.isShowing()) {
            loadDataDialog.cancel();
        }
    }

    private void showLoadingDialogMethod(String str) {
        if (loadDataDialog == null) {
            loadDataDialog = new LoadDataDialog(this);
        }
        loadDataDialog.setTitleStr(str);
        loadDataDialog.show();
    }
}
