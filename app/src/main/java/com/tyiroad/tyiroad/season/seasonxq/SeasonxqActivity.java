package com.tyiroad.tyiroad.season.seasonxq;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.tyiroad.tyiroad.Bean.Seasonxqbean;
import com.tyiroad.tyiroad.R;
import com.tyiroad.tyiroad.mvp.MVPBaseActivity;
import com.tyiroad.tyiroad.utils.Utils;
import com.tyiroad.tyiroad.utils.adapter.CommonAdapter;
import com.tyiroad.tyiroad.utils.adapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SeasonxqActivity extends MVPBaseActivity<SeasonxqContract.View, SeasonxqPresenter> implements SeasonxqContract.View {
    @Bind(R.id.waihandle_rv)
    ListView waihandleRv;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @Bind(R.id.go_back)
    LinearLayout goBack;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.right)
    TextView right;
    private String jjxyhid;
    private String dataid = "0";
    private String action = "0";
    private String pagesize = "10";
    private List<Seasonxqbean.JJXYHMXBean> SeasonData = new ArrayList<>();
    private CommonAdapter<Seasonxqbean.JJXYHMXBean> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setStatusBarColor(this, R.color.theme_color);
        setContentView(R.layout.act_seasonxq);
        ButterKnife.bind(this);
        initview();
        jjxyhid = getIntent().getStringExtra("ID");
        initdata();
        setPullRefresher();
        refreshLayout.setEnableRefresh(false);
        linstener();
    }

    private void linstener() {
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setPullRefresher() {
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                action = "1";
                dataid = SeasonData.get(SeasonData.size() - 1).getGUID_OBJ();
                mPresenter.getlist(jjxyhid, dataid, action, pagesize);
            }
        });
    }

    private void initdata() {
        mPresenter.getlist(jjxyhid, dataid, action, pagesize);
    }

    private void initview() {
        title.setText("路段明细");
        View headView = LayoutInflater.from(getContext()).inflate(R.layout.season_header, null);
        TextView szlxlx = (TextView) headView.findViewById(R.id.sheader_szlx_lx);
        szlxlx.setText(getIntent().getStringExtra("LX"));
        TextView xsfxfx = (TextView) headView.findViewById(R.id.sheader_xsfx_fx);
        xsfxfx.setText(getIntent().getStringExtra("FX"));
        TextView sglxlx = (TextView) headView.findViewById(R.id.header_sglx_lx);
        sglxlx.setText(getIntent().getStringExtra("SG"));
        TextView djdj = (TextView) headView.findViewById(R.id.header_dj_dj);
        djdj.setText(getIntent().getStringExtra("DJ"));
        TextView dwdw = (TextView) headView.findViewById(R.id.header_dw_dw);
        dwdw.setText(getIntent().getStringExtra("DW"));
        waihandleRv.addHeaderView(headView);
    }

    @Override
    public void onRequestError(String msg) {

    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void getData(Seasonxqbean videoVos2) {
        if (action.equals("1")) {
            SeasonData.addAll(videoVos2.getJJXYHMX());
            adapter.notifyDataSetChanged();
            refreshLayout.finishLoadmore();
            if (videoVos2.getISALSO().equals("0")) {
                refreshLayout.setLoadmoreFinished(true);
            }
        } else {
            SeasonData = videoVos2.getJJXYHMX();
            adapter = new CommonAdapter<Seasonxqbean.JJXYHMXBean>(this,
                    R.layout.item_seasonxq, SeasonData) {
                @Override
                protected void convert(ViewHolder holder, Seasonxqbean.JJXYHMXBean tubiaoVo, int position) {
                    holder.setText(R.id.name, tubiaoVo.getLCZH());
                    holder.setText(R.id.gcl_te, tubiaoVo.getGCL() + "");
                    holder.setText(R.id.qt_te, tubiaoVo.getQT());
                    holder.setText(R.id.bz_te, tubiaoVo.getBZ());
                }
            };
            waihandleRv.setAdapter(adapter);
            refreshLayout.finishRefresh();
        }
//        if (loadDataDialog != null && loadDataDialog.isShowing()) {
//            loadDataDialog.cancel();
//        }
    }
}
