package com.tyiroad.tyiroad.yjmx;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.tyiroad.tyiroad.R;
import com.tyiroad.tyiroad.mvp.MVPBaseActivity;
import com.tyiroad.tyiroad.utils.Utils;
import com.tyiroad.tyiroad.Bean.yjmxbean;
import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class YjmxActivity extends MVPBaseActivity<YjmxContract.View, YjmxPresenter> implements YjmxContract.View {


    @Bind(R.id.go_back)
    LinearLayout goBack;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.right)
    TextView right;
    @Bind(R.id.mc_mc_text)
    TextView mcMcText;
    @Bind(R.id.mc_text)
    TextView mcText;
    @Bind(R.id.mc_relative)
    RelativeLayout mcRelative;
    @Bind(R.id.jb_jb_text)
    TextView jbJbText;
    @Bind(R.id.jb_text)
    TextView jbText;
    @Bind(R.id.jb_relative)
    RelativeLayout jbRelative;
    @Bind(R.id.jg_jg_text)
    TextView jgJgText;
    @Bind(R.id.jg_text)
    TextView jgText;
    @Bind(R.id.jg_relative)
    RelativeLayout jgRelative;
    @Bind(R.id.sj_sj_text)
    TextView sjSjText;
    @Bind(R.id.sj_text)
    TextView sjText;
    @Bind(R.id.sj_relative)
    RelativeLayout sjRelative;
    @Bind(R.id.nr_nr_text)
    TextView nrNrText;
    @Bind(R.id.nr_relative)
    RelativeLayout nrRelative;
    @Bind(R.id.nr)
    TextView nr;
    @Bind(R.id.plys)
    TextView plys;
    @Bind(R.id.shang)
    LinearLayout shang;
    @Bind(R.id.disease_new_parent_layout)
    RelativeLayout diseaseNewParentLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setStatusBarColor(this, R.color.theme_color);
        setContentView(R.layout.act_yjmx);
        ButterKnife.bind(this);
        initview();
        initdata();
        initlistener();
    }

    private void initlistener() {
        shang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initview() {
        title.setText("应急预警");
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initdata() {
        mPresenter.getMX(getIntent().getStringExtra("GUID"));
    }

    @Override
    public void onRequestError(String msg) {

    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void getData2(yjmxbean videoVos2) {
        if (videoVos2.getYJYJDATA() != null) {
            if (videoVos2.getYJYJDATA().size() > 0) {
                yjmxbean.YJYJDATABean yJYJDATABean = videoVos2.getYJYJDATA().get(0);
                if (!Utils.isNull(yJYJDATABean.getYJNAME())) {
                    mcText.setText(yJYJDATABean.getYJNAME());
                }
                if (!Utils.isNull(yJYJDATABean.getYJJB())) {
                    if (yJYJDATABean.getYJJB().equals("1")) {
                        jbText.setText("省Ⅰ级");
                    } else if (yJYJDATABean.getYJJB().equals("2")) {
                        jbText.setText("省Ⅱ级");
                    } else if (yJYJDATABean.getYJJB().equals("3")) {
                        jbText.setText("省Ⅲ级");
                    } else if (yJYJDATABean.getYJJB().equals("4")) {
                        jbText.setText("省Ⅳ级");
                    }
                }
                if (!Utils.isNull(yJYJDATABean.getFBJG())) {
                    jgText.setText(yJYJDATABean.getFBJG());
                }
                if (!Utils.isNull(yJYJDATABean.getFBSJ())) {
                    sjText.setText(yJYJDATABean.getFBSJ().replace("T", " "));
                }
                if (!Utils.isNull(yJYJDATABean.getYJNR())) {
                    nr.setText(yJYJDATABean.getYJNR());
                } else {
                    nr.setVisibility(View.GONE);
                }
            }
        }
    }
}
