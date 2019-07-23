package com.tyiroad.tyiroad.yjcz;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tyiroad.tyiroad.Bean.Yjczbean;
import com.tyiroad.tyiroad.Bean.Yjczlistbean;
import com.tyiroad.tyiroad.MyApplication;
import com.tyiroad.tyiroad.R;
import com.tyiroad.tyiroad.mvp.MVPBaseActivity;
import com.tyiroad.tyiroad.utils.DiseaseNewSelectObjectListener;
import com.tyiroad.tyiroad.utils.adapter.CommonAdapter;
import com.tyiroad.tyiroad.utils.adapter.ViewHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class YjczActivity extends MVPBaseActivity<YjczContract.View, YjczPresenter> implements YjczContract.View {

    @Bind(R.id.go_back)
    LinearLayout goBack;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.right)
    TextView right;
    @Bind(R.id.dw_te)
    TextView dwTe;
    @Bind(R.id.dw_re)
    RelativeLayout dwRe;
    @Bind(R.id.zl_te)
    TextView zlTe;
    @Bind(R.id.zl_re)
    RelativeLayout zlRe;
    @Bind(R.id.zt_te)
    TextView ztTe;
    @Bind(R.id.zt_re)
    RelativeLayout ztRe;
    @Bind(R.id.rr)
    LinearLayout rr;
    @Bind(R.id.waihandle_rv)
    ListView waihandleRv;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @Bind(R.id.activity_disease_list_zhe_zhao_top_filter_layout)
    View activityDiseaseListZheZhaoTopFilterLayout;
    private ArrayList<String> listDwData = new ArrayList<>();
    private ArrayList<String> listZlData = new ArrayList<>();
    private ArrayList<String> listZtData = new ArrayList<>();
    private YjczPopu DwPop, LxPop, ZtPop;
    private List<Yjczbean.CDRYDTBean> DwData = new ArrayList<>();
    private List<Yjczbean.ZHLXDTBean> ZlData = new ArrayList<>();
    private List<Yjczbean.STATEDTBean> ZtData = new ArrayList<>();
    private String time, gydwid, zhlx, state, orderby, dataid, action, pagesize;
    private CommonAdapter<Yjczlistbean.YJLISTBean> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_yjcz);
        ButterKnife.bind(this);
        initview();
        initdata();
        setPullRefresher();
    }

    private void setPullRefresher() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                if (refreshlayout.isLoadmoreFinished()) {
                    refreshlayout.setLoadmoreFinished(false);
                }
                dataid = "";
                action = "0";
                pagesize = "10";
                mPresenter.getlist(time, gydwid, zhlx, state, orderby, dataid, action, pagesize);

            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                dataid = "";
                action = "1";
                pagesize = "10";
                mPresenter.getlist(time, gydwid, zhlx, state, orderby, dataid, action, pagesize);
            }
        });
    }

    private void initdata() {
        mPresenter.getHead(MyApplication.spUtils.getString("dqgydwid"));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        Calendar calendar = Calendar.getInstance();
        time = simpleDateFormat.format(calendar.getTime());
        gydwid = MyApplication.spUtils.getString("dqgydwid");
        zhlx = "";
        state = "";
        orderby = "";
        dataid = "";
        action = "0";
        pagesize = "10";
    }

    private void initview() {
        title.setText("调度管理");
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initPop() {
        if (DwPop == null) {
            DwPop = new YjczPopu(this, listDwData, new DiseaseNewSelectObjectListener() {
                @Override
                public void selectPosition(int position) {
                    if (position == 0) {
                        gydwid = MyApplication.spUtils.getString("dqgydwid");
                    } else {
                        gydwid = DwData.get(position).getVALUE();
                    }

                }
            });
        } else {
            DwPop.notifityData();
        }
        if (LxPop == null) {
            LxPop = new YjczPopu(this, listZlData, new DiseaseNewSelectObjectListener() {
                @Override
                public void selectPosition(int position) {

                }
            });
        } else {
            LxPop.notifityData();
        }
        if (ZtPop == null) {
            ZtPop = new YjczPopu(this, listZtData, new DiseaseNewSelectObjectListener() {
                @Override
                public void selectPosition(int position) {

                }
            });
        } else {
            ZtPop.notifityData();
        }
    }

    public void showFilterZheZhaoView() {
        if (!activityDiseaseListZheZhaoTopFilterLayout.isShown()) {
            activityDiseaseListZheZhaoTopFilterLayout.setVisibility(View.VISIBLE);
        }
    }

    public void hideFilterZheZhaoView() {
        if (activityDiseaseListZheZhaoTopFilterLayout.isShown()) {
            activityDiseaseListZheZhaoTopFilterLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onRequestError(String msg) {

    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void getData(Yjczbean videoVos2) {
        DwData = videoVos2.getCDRYDT();
        ZlData = videoVos2.getZHLXDT();
        ZtData = videoVos2.getSTATEDT();
        listDwData.add("全部单位");
        listZlData.add("全部种类");
        listZtData.add("全部状态");
        for (int i = 0; i < videoVos2.getCDRYDT().size(); i++) {
            listDwData.add(videoVos2.getCDRYDT().get(i).getGYDW());
        }
        for (int i = 0; i < videoVos2.getZHLXDT().size(); i++) {
            listZlData.add(videoVos2.getZHLXDT().get(i).getTEXT());
        }
        for (int i = 0; i < videoVos2.getSTATEDT().size(); i++) {
            listZtData.add(videoVos2.getSTATEDT().get(i).getTEXT());
        }
        initPop();
        mPresenter.getlist(time, gydwid, zhlx, state, orderby, dataid, action, pagesize);
    }

    @Override
    public void getData2(Yjczlistbean videoVos2) {
        adapter = new CommonAdapter<Yjczlistbean.YJLISTBean>(this,
                R.layout.item_yjcz, videoVos2.getYJLIST()) {
            @Override
            protected void convert(ViewHolder holder, Yjczlistbean.YJLISTBean tubiaoVo, int position) {

            }
        };
        waihandleRv.setAdapter(adapter);
    }
}
