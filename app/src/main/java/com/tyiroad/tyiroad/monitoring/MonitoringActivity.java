package com.tyiroad.tyiroad.monitoring;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tyiroad.tyiroad.Bean.Basebean;
import com.tyiroad.tyiroad.Bean.LogListbean;
import com.tyiroad.tyiroad.Bean.LoglistSbean;
import com.tyiroad.tyiroad.MyApplication;
import com.tyiroad.tyiroad.R;
import com.tyiroad.tyiroad.db.dbhelper.CuringDaoImpl;
import com.tyiroad.tyiroad.db.dbhelper.dbInfo.MonitoringInfo;
import com.tyiroad.tyiroad.db.dbhelper.dbInfo.QualityInfo;
import com.tyiroad.tyiroad.handle.LoadDataDialog;
import com.tyiroad.tyiroad.monitoring.dbmonitoring.DBMonitoringActivity;
import com.tyiroad.tyiroad.monitoring.inmonitoring.INMonitoringActivity;
import com.tyiroad.tyiroad.monitoring.monitoringxq.MonitoringXQActivity;
import com.tyiroad.tyiroad.mvp.MVPBaseActivity;
import com.tyiroad.tyiroad.quality.QualityActivity;
import com.tyiroad.tyiroad.utils.Dialog.CommBtnListener;
import com.tyiroad.tyiroad.utils.Dialog.CommNotificationDialog;
import com.tyiroad.tyiroad.utils.MonitoringPopupWindow;
import com.tyiroad.tyiroad.utils.PopSelectListener;
import com.tyiroad.tyiroad.utils.Utils;
import com.tyiroad.tyiroad.utils.adapter.CommonAdapter;
import com.tyiroad.tyiroad.utils.adapter.ViewHolder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MonitoringActivity extends MVPBaseActivity<MonitoringContract.View, MonitoringPresenter> implements MonitoringContract.View {

    @Bind(R.id.go_back)
    LinearLayout goBack;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.right)
    TextView right;
    @Bind(R.id.last_month_txt)
    TextView lastMonthTxt;
    @Bind(R.id.activity_disease_list_filter_time_start_txt)
    TextView activityDiseaseListFilterTimeStartTxt;
    @Bind(R.id.activity_disease_list_filter_time_layout)
    LinearLayout activityDiseaseListFilterTimeLayout;
    @Bind(R.id.next_month_txt)
    TextView nextMonthTxt;
    @Bind(R.id.month_filter_layout)
    RelativeLayout monthFilterLayout;
    @Bind(R.id.shangchuan)
    TextView shangchuan;
    @Bind(R.id.add_log)
    TextView addLog;
    @Bind(R.id.ddd)
    LinearLayout ddd;
    @Bind(R.id.my)
    RelativeLayout my;
    @Bind(R.id.logrecycle)
    SwipeMenuListView logrecycle;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private String TIME;
    private String DATAID = "0";
    private String ACTION="0";
    private CommonAdapter<MonitoringListBean.DATABean> adapter;
    private SimpleDateFormat simpleDateFormatResult = new SimpleDateFormat("yyyy/MM");
    private MonitoringPopupWindow popupWindow;
    private List<MonitoringListBean.DATABean> DATA = new ArrayList<>();
    private CuringDaoImpl curingDao;
    private List<MonitoringInfo> dbData = new ArrayList<>();
    private CommNotificationDialog commitWarmDialog;
    private LoadDataDialog loadDataDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setStatusBarColor(this, R.color.theme_color);
        setContentView(R.layout.act_monitoring);
        ButterKnife.bind(this);
        curingDao = new CuringDaoImpl(this);
        initview();
        setadapter();
        setPullRefresher();
        linstener();
    }

    private void linstener() {
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        shangchuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upDataialog();
            }
        });
        logrecycle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (refreshLayout.isRefreshing() || refreshLayout.isLoading()) {

                } else {
                    if (DATA.get(position).getXMZT().equals("未上传")) {//本地
                        Intent intent = new Intent(MonitoringActivity.this, DBMonitoringActivity.class);
                        intent.putExtra("MOID", DATA.get(position).getGUID_OBJ());
                        startActivityForResult(intent, 1);
                    } else if (DATA.get(position).getXMZT().equals("已上传")) {//网络
                        Intent intent = new Intent(MonitoringActivity.this, INMonitoringActivity.class);
                        intent.putExtra("MOID", DATA.get(position).getGUID_OBJ());
                        startActivity(intent);
                    }
                }
            }
        });
        addLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MonitoringActivity.this, MonitoringXQActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        activityDiseaseListFilterTimeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.show(my);
            }
        });
        lastMonthTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String startDateStr = activityDiseaseListFilterTimeStartTxt.getText().toString().replace("-", "/");
                Calendar calendar = Calendar.getInstance();
                try {
                    calendar.setTime(simpleDateFormatResult.parse(startDateStr));
                    calendar.add(Calendar.MONTH, -1);
                    startDateStr = simpleDateFormatResult.format(calendar.getTime());
                    calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
                    activityDiseaseListFilterTimeStartTxt.setText(startDateStr);
                    TIME = startDateStr.replace("/", "-");
                    DATA.clear();
                    if (dbData.size()>0){
                        for (int i = 0; i < dbData.size(); i++) {
                            MonitoringListBean.DATABean   BD =  new MonitoringListBean.DATABean();
                            BD.setGUID_OBJ(dbData.get(i).getMOID()+"");
                            BD.setXMMC(dbData.get(i).getXMMC());
                            BD.setXMZT("未上传");
                            BD.setXMMS(dbData.get(i).getXMMS());
                            BD.setLOCATION(dbData.get(i).getLOCATION());
                            BD.setSCSJ(dbData.get(i).getSCSJ());
                            String imgUrl = dbData.get(i).getPIC().split(",")[0];
                            BD.setTPDZ(imgUrl);
                            DATA.add(BD);
                        }
                    }
                    refreshLayout.autoRefresh();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        nextMonthTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String endDateStr = activityDiseaseListFilterTimeStartTxt.getText().toString().replace("-", "/");
                Calendar calendar = Calendar.getInstance();
                try {
                    calendar.setTime(simpleDateFormatResult.parse(endDateStr));
                    calendar.add(Calendar.MONTH, 1);
                    String startDateStr = simpleDateFormatResult.format(calendar.getTime());
                    activityDiseaseListFilterTimeStartTxt.setText(startDateStr);
                    TIME= startDateStr.replace("/", "-");
                    DATA.clear();
                    if (dbData.size()>0){
                        for (int i = 0; i < dbData.size(); i++) {
                            MonitoringListBean.DATABean   BD =  new MonitoringListBean.DATABean();
                            BD.setGUID_OBJ(dbData.get(i).getMOID()+"");
                            BD.setXMMC(dbData.get(i).getXMMC());
                            BD.setXMZT("未上传");
                            BD.setXMMS(dbData.get(i).getXMMS());
                            BD.setLOCATION(dbData.get(i).getLOCATION());
                            BD.setSCSJ(dbData.get(i).getSCSJ());
                            String imgUrl = dbData.get(i).getPIC().split(",")[0];
                            BD.setTPDZ(imgUrl);
                            DATA.add(BD);
                        }
                    }
                    refreshLayout.autoRefresh();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        popupWindow = new MonitoringPopupWindow(MonitoringActivity.this,new PopSelectListener() {
            @Override
            public void selectResult(Object... result) {
                String selDataStr = result[0].toString();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
                try {
                    Date dateResult = sdf.parse(selDataStr);
                    selDataStr = simpleDateFormatResult.format(dateResult);
                    activityDiseaseListFilterTimeStartTxt.setText(selDataStr);
                    TIME = selDataStr.replace("/", "-");
                    DATA.clear();
                    if (dbData.size()>0){
                        for (int i = 0; i < dbData.size(); i++) {
                            MonitoringListBean.DATABean   BD =  new MonitoringListBean.DATABean();
                            BD.setGUID_OBJ(dbData.get(i).getMOID()+"");
                            BD.setXMMC(dbData.get(i).getXMMC());
                            BD.setXMZT("未上传");
                            BD.setXMMS(dbData.get(i).getXMMS());
                            BD.setLOCATION(dbData.get(i).getLOCATION());
                            BD.setSCSJ(dbData.get(i).getSCSJ());
                            String imgUrl = dbData.get(i).getPIC().split(",")[0];
                            BD.setTPDZ(imgUrl);
                            DATA.add(BD);
                        }
                    }
                    refreshLayout.autoRefresh();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initview() {
        String str = "正在加载...";
        showLoadingDialogMethod(str);
        dbData = curingDao.queryAllMonitoring();
        if (dbData.size()>0){
            for (int i = 0; i < dbData.size(); i++) {
                MonitoringListBean.DATABean   BD =  new MonitoringListBean.DATABean();
                BD.setGUID_OBJ(dbData.get(i).getMOID()+"");
                BD.setXMMC(dbData.get(i).getXMMC());
                BD.setXMZT("未上传");
                BD.setXMMS(dbData.get(i).getXMMS());
                BD.setLOCATION(dbData.get(i).getLOCATION());
                BD.setSCSJ(dbData.get(i).getSCSJ());
                String imgUrl = dbData.get(i).getPIC().split(",")[0];
                BD.setTPDZ(imgUrl);
                DATA.add(BD);
            }
        }
        title.setText("过程监控");
        TIME = Utils.thisMonth().replace("-01","");
        activityDiseaseListFilterTimeStartTxt.setText(TIME);
        mPresenter.getList( TIME, DATAID, ACTION);
    }

    private void setPullRefresher() {

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                DATA.clear();
                if (dbData.size()>0){
                    for (int i = 0; i < dbData.size(); i++) {
                        MonitoringListBean.DATABean   BD =  new MonitoringListBean.DATABean();
                        BD.setGUID_OBJ(dbData.get(i).getMOID()+"");
                        BD.setXMMC(dbData.get(i).getXMMC());
                        BD.setXMZT("未上传");
                        BD.setXMMS(dbData.get(i).getXMMS());
                        BD.setLOCATION(dbData.get(i).getLOCATION());
                        BD.setSCSJ(dbData.get(i).getSCSJ());
                        String imgUrl = dbData.get(i).getPIC().split(",")[0];
                        BD.setTPDZ(imgUrl);
                        DATA.add(BD);
                    }
                }
                DATAID = "0";
                ACTION = "0";
                mPresenter.getList(TIME, DATAID, ACTION);
                refreshLayout.finishRefresh(1000);
                refreshlayout.finishRefresh(2000);
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                ACTION = "1";
                DATAID = DATA.get(DATA.size() - 1).getGUID_OBJ();
                mPresenter.getList(TIME, DATAID, ACTION);
                refreshLayout.finishLoadmore(1000);
                refreshlayout.finishLoadmore(2000);
            }
        });
    }

    private void setadapter() {
        logrecycle.setMenuCreator(creator);
        adapter = new CommonAdapter<MonitoringListBean.DATABean>(this,
                R.layout.item_quality, DATA) {
            @Override
            protected void convert(ViewHolder holder, MonitoringListBean.DATABean tubiaoVo, int position) {
                holder.setText(R.id.item_name, tubiaoVo.getXMMC());
                if (tubiaoVo.getXMZT().equals("未上传")){
                    holder.setTextColor(R.id.item_zt,getResources().getColor(R.color.bh_lx_pop_select_text_color));
                }else {
                    holder.setTextColor(R.id.item_zt,getResources().getColor(R.color.pf_xin_bing_hai_text_color));
                }
                holder.setText(R.id.item_zt, tubiaoVo.getXMZT());
                holder.setText(R.id.item_cjwz, "采集位置："+replaceNull(tubiaoVo.getLOCATION()));
                holder.setText(R.id.item_gcbw,tubiaoVo.getXMMS());
                holder.setText(R.id.item_time,tubiaoVo.getSCSJ());
                Glide.with(MonitoringActivity.this)
                        .asBitmap()
                        .apply(MyApplication.app.options)
                        .load(tubiaoVo.getTPDZ())
                        .into((ImageView)holder.getView(R.id.item_igm));
            }
        };
        logrecycle.setAdapter(adapter);
        logrecycle.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                if (DATA.get(position).getXMZT().equals("已上传")){
                    mPresenter.Delete(DATA.get(position).getGUID_OBJ());
                    DATA.remove(position);
                }
                return true;
            }
        });
    }
    public static String replaceNull(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }
    @Override
    public void getData(List<MonitoringListBean.DATABean> videoVos2) {
        DATA.addAll(videoVos2);
        adapter.setmDatas(DATA);
        if (loadDataDialog != null && loadDataDialog.isShowing()) {
            loadDataDialog.cancel();
        }
    }

    @Override
    public void Deletes(Basebean videoVos2) {
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == 2) {
                DATA.clear();
                dbData.clear();
                dbData = curingDao.queryAllMonitoring();
                if (dbData.size()>0){
                    for (int i = 0; i < dbData.size(); i++) {
                        MonitoringListBean.DATABean   BD =  new MonitoringListBean.DATABean();
                        BD.setXMMC(dbData.get(i).getXMMC());
                        BD.setXMZT("未上传");
                        BD.setXMMS(dbData.get(i).getXMMS());
                        BD.setLOCATION(dbData.get(i).getLOCATION());
                        BD.setSCSJ(dbData.get(i).getSCSJ());
                        String imgUrl = dbData.get(i).getPIC().split(",")[0];
                        BD.setTPDZ(imgUrl);
                        DATA.add(BD);
                    }
                }
                refreshLayout.autoRefresh();
            }
        }
    }
    @Override
    public void onRequestError(String msg) {

    }

    @Override
    public void onRequestEnd() {

    }
    /**
     * 显示立即上传提示对话框
     */
    private void upDataialog() {
        if (dbData.size() != 0) {
            String title = getString(R.string.log_update_quality);
            String titleStr = String.format(title, String.valueOf(dbData.size()));
            String cancelStr = "取消";
            String okStr = "确认";
            if (commitWarmDialog == null) {
                commitWarmDialog = new CommNotificationDialog(this, titleStr, okStr, cancelStr, new CommBtnListener() {
                    @Override
                    public void CommOkBtnClick() {
                        comitListDataMethod();
                    }

                    @Override
                    public void CommCancelBtnClick() {

                    }
                });
            }
            commitWarmDialog.setWarmTitle(titleStr);
            commitWarmDialog.show();
        }
    }

    private void comitListDataMethod() {

        Upload upload = new Upload(this, dbData);
        upload.show();

    }
    /**
     * 刷新数据
     */
    public void refreshDataMethod() {
        MyApplication.app.customToast("上传成功");
        DATA.clear();
        dbData.clear();
        dbData = curingDao.queryAllMonitoring();
        refreshLayout.autoRefresh();
    }
    private void showLoadingDialogMethod(String str) {
        if (loadDataDialog == null) {
            loadDataDialog = new LoadDataDialog(this);
        }
        loadDataDialog.setTitleStr(str);
        loadDataDialog.show();
    }
    SwipeMenuCreator creator = new SwipeMenuCreator() {

        @Override
        public void create(SwipeMenu menu) {
            // create "delete" item
            SwipeMenuItem deleteItem = new SwipeMenuItem(
                    getApplicationContext());
            // set item background
            deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                    0x3F, 0x25)));
            // set item width
            deleteItem.setWidth(dp2px(90));
            // set a icon
            deleteItem.setIcon(R.drawable.shanchu_btn);
            // add to menu
            menu.addMenuItem(deleteItem);
        }
    };

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
}

