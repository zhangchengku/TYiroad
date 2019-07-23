package com.tyiroad.tyiroad.other;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tyiroad.tyiroad.Bean.Basebean;
import com.tyiroad.tyiroad.Bean.OtherListbean;
import com.tyiroad.tyiroad.Bean.Otherheaderbean;
import com.tyiroad.tyiroad.MyApplication;
import com.tyiroad.tyiroad.R;
import com.tyiroad.tyiroad.handle.LoadDataDialog;
import com.tyiroad.tyiroad.mvp.MVPBaseActivity;
import com.tyiroad.tyiroad.other.OtherAddActivity.OtherAddActivity;
import com.tyiroad.tyiroad.other.examine.ExamineActivity;
import com.tyiroad.tyiroad.other.newapplication.NewApplicationActivity;
import com.tyiroad.tyiroad.other.noexamine.NoExamineActivity;
import com.tyiroad.tyiroad.other.reported.ReportedActivity;
import com.tyiroad.tyiroad.utils.DiseaseNewSelectObjectListener;
import com.tyiroad.tyiroad.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class OtherActivity extends MVPBaseActivity<OtherContract.View, OtherPresenter> implements OtherContract.View {


    @Bind(R.id.go_back)
    LinearLayout goBack;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.right)
    TextView right;
    @Bind(R.id.other_lx)
    TextView otherLx;
    @Bind(R.id.other_lx_lay)
    RelativeLayout otherLxLay;
    @Bind(R.id.other_dw)
    TextView otherDw;
    @Bind(R.id.other_dw_lay)
    RelativeLayout otherDwLay;
    @Bind(R.id.rr)
    LinearLayout rr;
    @Bind(R.id.rrr)
    LinearLayout rrr;
    @Bind(R.id.act_other_list)
    ListView actOtherList;
    @Bind(R.id.act_other_fresh)
    SmartRefreshLayout actOtherFresh;
    @Bind(R.id.act_other_updata)
    TextView actOtherUpdata;
    @Bind(R.id.act_other_add)
    TextView actOtherAdd;
    @Bind(R.id.shang)
    LinearLayout shang;
    @Bind(R.id.qx)
    TextView qx;
    @Bind(R.id.qrys)
    TextView qrys;
    @Bind(R.id.xia)
    LinearLayout xia;
    @Bind(R.id.updata_lay)
    RelativeLayout updataLay;
    @Bind(R.id.activity_disease_list_zhe_zhao_top_filter_layout)
    View activityDiseaseListZheZhaoTopFilterLayout;
    @Bind(R.id.cb)
    CheckBox cb;
    @Bind(R.id.zt_te)
    TextView ztTe;
    @Bind(R.id.zt_lay)
    RelativeLayout ztLay;

    private String dataid = "0";
    private String action = "0";
    private String gydwid = MyApplication.spUtils.getString("dqgydwid");
    private String lxcode = "";
    private ArrayList<OtherListbean.LISTDATABean> ListDate = new ArrayList<>();
    private OtherListAdapter adapter;
    private LoadDataDialog loadDataDialog;
    private ArrayList<String> LxData = new ArrayList<>();
    private ArrayList<String> DwData = new ArrayList<>();
    private OtherPop LxPop, DwPop;
    private Otherheaderbean DATEJson;
    private List<Otherheaderbean.YYLXDATABean> LXbean = new ArrayList<>();
    private List<Otherheaderbean.GYDWBean> DWbean = new ArrayList<>();
    private List<String> updates = new ArrayList<>();
    private ArrayList<String> ZtData = new ArrayList<>();
    private String zt ="";
    private OtherPop ZtPop;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setStatusBarColor(this, R.color.theme_color);
        setContentView(R.layout.act_other);
        ButterKnife.bind(this);
        title.setText("其他");
        String str = "正在加载...";
        showLoadingDialogMethod(str);
        initdate();
        initsener();
        initpopw();
        setPullRefresher();
        isQX();
    }

    private void isQX() {
        if (MyApplication.spUtils.getString("QX").equals("4")||MyApplication.spUtils.getString("QX").equals("5")){
            updataLay.setVisibility(View.VISIBLE);
        }else {
            updataLay.setVisibility(View.GONE);
        }
    }

    private void initpopw() {
        ZtData.add("所有状态");
        ZtData.add("新申请");
        ZtData.add("已上报");
        ZtData.add("审核通过");
        ZtData.add("审核不通过");
        if (ZtPop == null) {
            ZtPop = new OtherPop(this, ZtData, new DiseaseNewSelectObjectListener() {
                @Override
                public void selectPosition(int position) {
                    if (ZtData.get(position).equals("所有状态")) {
                          ztTe.setText("所有状态");
                          zt = "";
                    } else if (ZtData.get(position).equals("新申请")){
                        ztTe.setText("新申请");
                        zt = "1";
                    }else if (ZtData.get(position).equals("已上报")){
                        ztTe.setText("已上报");
                        zt = "2";
                    }else if (ZtData.get(position).equals("审核通过")){
                        ztTe.setText("审核通过");
                        zt = "3";
                    }else if (ZtData.get(position).equals("审核不通过")){
                        ztTe.setText("审核不通过");
                        zt = "4";
                    }
                    actOtherFresh.autoRefresh();
                }
            });
        } else {
            ZtPop.notifityData();
        }
        if (DwPop == null) {
            DwPop = new OtherPop(this, DwData, new DiseaseNewSelectObjectListener() {
                @Override
                public void selectPosition(int position) {
                    if (DwData.get(position).equals("管养单位")) {
                        gydwid = MyApplication.spUtils.getString("dqgydwid");
                        actOtherFresh.autoRefresh();
                    } else {
                        gydwid = DWbean.get(position - 1).getGYDWID();
                        actOtherFresh.autoRefresh();
                    }
                    otherDw.setText(DwData.get(position));
                }
            });
        } else {
            DwPop.notifityData();
        }
        if (LxPop == null) {
            LxPop = new OtherPop(this, LxData, new DiseaseNewSelectObjectListener() {
                @Override
                public void selectPosition(int position) {
                    if (LxData.get(position).equals("所有路线")) {
                        lxcode = "";
                        actOtherFresh.autoRefresh();
                    } else {
                        lxcode = LXbean.get(position - 1).getLXID();
                        actOtherFresh.autoRefresh();
                    }
                    otherLx.setText(LxData.get(position));
                }
            });
        } else {
            LxPop.notifityData();
        }
    }

    private void initsener() {
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        qrys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updates.clear();
                for (int i = 0; i < ListDate.size(); i++) {
                    if (ListDate.get(i).isCheck) {
                        updates.add(ListDate.get(i).getGUID_OBJ());
                    }
                }
               if (updates.size()>0){
                   String GUID_OBJ = "";
                   for (int i = 0; i < updates.size(); i++) {
                       if (i == 0) {
                           GUID_OBJ += updates.get(i);
                       } else {
                           GUID_OBJ += "," + updates.get(i);
                       }
                   }
                   mPresenter.AddData(GUID_OBJ,"2");
               }
            }
        });
        qx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cb.setVisibility(View.GONE);
                shang.setVisibility(View.VISIBLE);
                xia.setVisibility(View.GONE);
                adapter.flage = false;
                adapter.notifyDataSetChanged();
            }
        });
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    for (int i = 0; i < ListDate.size(); i++) {
                        ListDate.get(i).isCheck = true;
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    for (int i = 0; i < ListDate.size(); i++) {
                        ListDate.get(i).isCheck = false;
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });
        actOtherUpdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (actOtherFresh.isRefreshing() == true || actOtherFresh.isLoading() == true) {
                } else {
                    if (adapter != null) {
                        cb.setVisibility(View.VISIBLE);
                        shang.setVisibility(View.GONE);
                        xia.setVisibility(View.VISIBLE);
                        adapter.flage = true;
                        adapter.notifyDataSetChanged();
                    }
                }

            }
        });
        actOtherList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (ListDate.get(position).getSTATE().equals("1")){//新申请
                    Intent intent = new Intent(OtherActivity.this, NewApplicationActivity.class);
                    intent.putExtra("dataid", ListDate.get(position).getGUID_OBJ());
                    startActivityForResult(intent, 1);
                }else if (ListDate.get(position).getSTATE().equals("2")){//已上报
                    Intent intent = new Intent(OtherActivity.this, ReportedActivity.class);
                    intent.putExtra("dataid", ListDate.get(position).getGUID_OBJ());
                    startActivityForResult(intent, 1);
                }else if (ListDate.get(position).getSTATE().equals("3")){//审核通过
                    Intent intent = new Intent(OtherActivity.this, ExamineActivity.class);
                    intent.putExtra("dataid", ListDate.get(position).getGUID_OBJ());
                    startActivityForResult(intent, 1);
                }else if (ListDate.get(position).getSTATE().equals("4")){//审核不通过
                    Intent intent = new Intent(OtherActivity.this, NoExamineActivity.class);
                    intent.putExtra("dataid", ListDate.get(position).getGUID_OBJ());
                    intent.putExtra("DATE", new Gson().toJson(DATEJson));
                    startActivityForResult(intent, 1);
                }
            }
        });
        actOtherAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OtherActivity.this, OtherAddActivity.class);
                intent.putExtra("DATE", new Gson().toJson(DATEJson));
                startActivityForResult(intent, 1);
            }
        });
        otherLxLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LxPop.show(rrr);

            }
        });
        otherDwLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DwPop.show(rrr);

            }
        });
        ztLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZtPop.show(rrr);
            }
        });

    }

    private void setPullRefresher() {
        actOtherFresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                if (refreshlayout.isLoadmoreFinished()) {
                    refreshlayout.setLoadmoreFinished(false);
                }
                dataid = "0";
                action = "0";
                mPresenter.getlist(gydwid, lxcode, dataid, action,zt);

            }
        });
        actOtherFresh.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                action = "1";
                dataid = ListDate.get(ListDate.size() - 1).getGUID_OBJ();
                mPresenter.getlist(gydwid, lxcode, dataid, action,zt);
            }
        });
    }

    private void initdate() {
        mPresenter.getheader(gydwid);
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
    public void getData(OtherListbean videoVos2) {
        if (action.equals("1")) {
            ListDate.addAll(videoVos2.getLISTDATA());
            adapter.notifyDataSetChanged();
            actOtherFresh.finishLoadmore();
            if (videoVos2.getISALSO().equals("0")) {
                actOtherFresh.setLoadmoreFinished(true);
            }
        } else {
            ListDate.clear();
            ListDate.addAll(videoVos2.getLISTDATA());
            setadapter();
            actOtherFresh.finishRefresh();
        }
        if (loadDataDialog != null && loadDataDialog.isShowing()) {
            loadDataDialog.cancel();
        }
    }

    @Override
    public void getheaderData(Otherheaderbean videoVos2) {
        DATEJson = videoVos2;
        LXbean = videoVos2.getYYLXDATA();
        DWbean = videoVos2.getGYDW();
        LxData.clear();
        LxData.add("所有路线");
        if (videoVos2.getYYLXDATA().size() > 0) {
            for (int i = 0; i < videoVos2.getYYLXDATA().size(); i++) {
                LxData.add(videoVos2.getYYLXDATA().get(i).getLXMC());
            }
        }

        DwData.clear();
        DwData.add("管养单位");
        if (videoVos2.getGYDW().size() > 0) {
            for (int i = 0; i < videoVos2.getGYDW().size(); i++) {
                DwData.add(videoVos2.getGYDW().get(i).getGYDWMC());
            }
        }
        mPresenter.getlist(gydwid, lxcode, dataid, action,zt);
    }

    @Override
    public void getAddData(Basebean basebean) {
        if (basebean.getSTATE().equals("1")){
            for (int i = 0; i < ListDate.size(); i++) {
                ListDate.get(i).isCheck = false;
            }
            cb.setVisibility(View.GONE);
            shang.setVisibility(View.VISIBLE);
            xia.setVisibility(View.GONE);
            adapter.flage = false;
            adapter.notifyDataSetChanged();
            actOtherFresh.autoRefresh();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // RESULT_OK，判断另外一个activity已经结束数据输入功能，Standard activity result:
        // operation succeeded. 默认值是-1
        if (resultCode == 2) {
            if (requestCode == 1) {
                actOtherFresh.autoRefresh();
            }
        }
    }

    private void setadapter() {
        adapter = new OtherListAdapter(OtherActivity.this, ListDate);
        actOtherList.setAdapter(adapter);
    }

    private void showLoadingDialogMethod(String str) {
        if (loadDataDialog == null) {
            loadDataDialog = new LoadDataDialog(this);
        }
        loadDataDialog.setTitleStr(str);
        loadDataDialog.show();
    }
}
