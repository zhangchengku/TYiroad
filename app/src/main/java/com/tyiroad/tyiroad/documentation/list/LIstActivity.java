package com.tyiroad.tyiroad.documentation.list;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.tyiroad.tyiroad.MyApplication;
import com.tyiroad.tyiroad.R;
import com.tyiroad.tyiroad.documentation.DocumentationActivity;
import com.tyiroad.tyiroad.documentation.GcBean;
import com.tyiroad.tyiroad.documentation.lookdoumentation.LookDoumentationActivity;
import com.tyiroad.tyiroad.mvp.MVPBaseActivity;
import com.tyiroad.tyiroad.quality.XmmcBean;
import com.tyiroad.tyiroad.utils.DiseaseNewSelectObjectListener;
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

public class LIstActivity extends MVPBaseActivity<LIstContract.View, LIstPresenter> implements LIstContract.View {


    @Bind(R.id.go_back)
    LinearLayout goBack;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.right)
    TextView right;
    @Bind(R.id.hea)
    RelativeLayout hea;
    @Bind(R.id.lx)
    TextView lx;
    @Bind(R.id.lxr)
    RelativeLayout lxr;
    @Bind(R.id.type)
    TextView type;
    @Bind(R.id.bhr)
    RelativeLayout bhr;
    @Bind(R.id.view)
    View views;
    @Bind(R.id.logrecycle)
    SwipeMenuListView logrecycle;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @Bind(R.id.add_log)
    TextView addLog;
    @Bind(R.id.ddd)
    LinearLayout ddd;
    @Bind(R.id.my)
    RelativeLayout my;
    @Bind(R.id.list_zhe_zhao)
    View listZheZhao;
    private String XMID = "";
    private String ZLLX = "";
    private String DATAID = "0";
    private String ACTION = "0";
    private ListBean ListDataInfo;
    private List<ListBean.DATABean> ListData = new ArrayList<>();
    private CommonAdapter<ListBean.DATABean> adapter;
    private List<XmmcBean.DATABean> GcmcInfos = new ArrayList<>();
    private ArrayList<String> GcmcResult = new ArrayList<>();
    private List<GcBean.DATABean.ZllxBean> ZllxInfos = new ArrayList<>();
    private ArrayList<String> ZllxResult = new ArrayList<>();
    private ListXmPopu listXmPopu;
    private ListXmPopu listLxPopu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_mocument_list);
        Utils.setStatusBarColor(this, R.color.theme_color);
        ButterKnife.bind(this);
        setadapter();
        initview();
        initdata();
        refreshDataMethod();
        linstener();
        initpopw();
    }

    private void initpopw() {
        if (listXmPopu == null) {
            listXmPopu = new ListXmPopu(LIstActivity.this, GcmcResult, new DiseaseNewSelectObjectListener() {
                @Override
                public void selectPosition(int position) {
                    if (GcmcResult.get(position).equals("所有项目")) {
                        ListData.clear();
                        XMID = "";
                        refreshLayout.autoRefresh();
                    } else {
                        ListData.clear();
                        XMID = GcmcInfos.get(position).getGUID_OBJ();
                        refreshLayout.autoRefresh();
                    }
                    lx.setText(GcmcResult.get(position));
                }
            });
        } else {
            listXmPopu.notifityData();
        }
        if (listLxPopu == null) {
            listLxPopu = new ListXmPopu(LIstActivity.this, ZllxResult, new DiseaseNewSelectObjectListener() {
                @Override
                public void selectPosition(int position) {
                    if (ZllxResult.get(position).equals("所有类型")) {
                        ListData.clear();
                        ZLLX = "";
                        refreshLayout.autoRefresh();
                    } else {
                        ListData.clear();
                        ZLLX = ZllxInfos.get(position).getID();
                        refreshLayout.autoRefresh();
                    }
                    type.setText(ZllxResult.get(position));
                }
            });
        } else {
            listLxPopu.notifityData();
        }
    }

    private void initdata() {
        mPresenter.getXmmcData();
    }

    private void linstener() {

        lxr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listXmPopu.show(views);
            }
        });
        bhr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listLxPopu.show(views);
            }
        });
        ddd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LIstActivity.this, DocumentationActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    private void initview() {
        title.setText("文档资料");
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                loadRefreshData();
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                loadMoreData();
            }
        });
    }

    @Override
    public void getData(ListBean videoVos2) {
        ListDataInfo = videoVos2;
        if (ListDataInfo != null && "1".equals(ListDataInfo.getSTATE())) {
            if (ListDataInfo.getDATA() != null) {
                if (ListDataInfo.getDATA().size() > 0) {
                    if ("0".equals(ACTION)) {
                        ListData.clear();
                    }
                    ListData.addAll(ListDataInfo.getDATA());
                    adapter.notifyDataSetChanged();
                }
            }
//            String isalso = ListDataInfo.getISALSO();
//            if (!Utils.isNull(isalso)) {
//                if ("0".equals(isalso)) {
//                    refreshLayout.setLoadmoreFinished(true);
//                } else if ("1".equals(isalso)) {
//                    refreshLayout.setLoadmoreFinished(false);
//                }
//            }
        }
        closeRefreshOrLoadMoreMethod();
    }

    @Override
    public void Deletes(Basebean videoVos2) {
        adapter.notifyDataSetChanged();
    }

    /**
     * 刷新数据
     */
    public void refreshDataMethod() {
        if (ListData != null && ListData.size() > 0) {
            ListData.clear();
            adapter.notifyDataSetChanged();
        }
        refreshLayout.autoRefresh();
    }

    /**
     * 下拉刷新数据
     */
    private void loadRefreshData() {
        refreshLayout.setLoadmoreFinished(false);
        ACTION = "0";
        DATAID = "0";
        callDataNetWork();
    }

    /**
     * 上拉加载更多数据
     */
    private void loadMoreData() {
        ACTION = "1";
        if (ListData != null && ListData.size() > 0) {
            DATAID = ListData.get(ListData.size() - 1).getGUID_OBJ();
        }
        callDataNetWork();
    }

    /**
     * 调用审核派发列表接口
     */
    private void callDataNetWork() {

        mPresenter.getList(XMID, ZLLX, DATAID, ACTION);
    }

    /**
     * 关闭刷新或加载更多
     */
    private void closeRefreshOrLoadMoreMethod() {
        if ("0".equals(ACTION)) {
            refreshLayout.finishRefresh();
        } else {
            refreshLayout.finishLoadmore();
        }
    }

    private void setadapter() {
        logrecycle.setMenuCreator(creator);
        adapter = new CommonAdapter<ListBean.DATABean>(this,
                R.layout.item_quality, ListData) {
            @Override
            protected void convert(ViewHolder holder, ListBean.DATABean tubiaoVo, int position) {
                holder.setText(R.id.item_name, tubiaoVo.getGCMC());
                holder.setVisible(R.id.item_zt,false);
                holder.setText(R.id.item_cjwz, "采集位置：" + replaceNull(tubiaoVo.getCJWZ()));
                holder.setText(R.id.item_gcbw,  tubiaoVo.getZLLX());
                holder.setText(R.id.item_time, tubiaoVo.getSCSJ());
                Glide.with(LIstActivity.this)
                        .asBitmap()
                        .apply(MyApplication.app.options)
                        .load(tubiaoVo.getIMAGEURL())
                        .into((ImageView) holder.getView(R.id.item_igm));
            }
        };
        logrecycle.setAdapter(adapter);
        logrecycle.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                mPresenter.Delete(ListData.get(position).getGUID_OBJ());
                ListData.remove(position);
                return true;
            }
        });
        logrecycle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(LIstActivity.this, LookDoumentationActivity.class);
                intent.putExtra("ID",ListData.get(position).getGUID_OBJ());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRequestError(String msg) {

    }

    @Override
    public void onRequestEnd() {

    }

    public static String replaceNull(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }

    @Override
    public void getXmmcDatas(List<XmmcBean.DATABean> DATA) {
        if (DATA.size() > 0) {
            GcmcInfos = DATA;
            for (int i = 0; i < DATA.size(); i++) {
                GcmcResult.add(DATA.get(i).getXMMC());
            }
            GcmcResult.add("所有项目");
        }
        mPresenter.getGcjdData();
    }

    @Override
    public void getGcjdDatas(GcBean GcBean) {
        if (GcBean.getDATA().size() > 0) {
            if (GcBean.getDATA().get(0).getZllx().size() > 0) {
                ZllxInfos = GcBean.getDATA().get(0).getZllx();
                for (int i = 0; i < GcBean.getDATA().get(0).getZllx().size(); i++) {
                    ZllxResult.add(GcBean.getDATA().get(0).getZllx().get(i).getTEXT());
                }
                ZllxResult.add("所有类型");
            }
        }
    }

    public void showFilterZheZhaoView() {
        if (!listZheZhao.isShown()) {
            listZheZhao.setVisibility(View.VISIBLE);
        }
    }

    public void hideFilterZheZhaoView() {
        if (listZheZhao.isShown()) {
            listZheZhao.setVisibility(View.GONE);
        }
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == 2) {
                ListData.clear();
                refreshLayout.autoRefresh();
            }
        }
    }
}
