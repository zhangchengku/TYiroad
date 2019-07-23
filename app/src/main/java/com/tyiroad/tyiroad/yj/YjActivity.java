package com.tyiroad.tyiroad.yj;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tyiroad.tyiroad.Bean.YJlistBean;
import com.tyiroad.tyiroad.R;
import com.tyiroad.tyiroad.mvp.MVPBaseActivity;
import com.tyiroad.tyiroad.season.SeasonActivity;
import com.tyiroad.tyiroad.utils.DiseaseNewSelectObjectListener;
import com.tyiroad.tyiroad.utils.Utils;
import com.tyiroad.tyiroad.utils.adapter.CommonAdapter;
import com.tyiroad.tyiroad.utils.adapter.ViewHolder;
import com.tyiroad.tyiroad.yjmx.YjmxActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class YjActivity extends MVPBaseActivity<YjContract.View, YjPresenter> implements YjContract.View {

    @Bind(R.id.go_back)
    LinearLayout goBack;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.right)
    TextView right;
    @Bind(R.id.lx)
    TextView lx;
    @Bind(R.id.lxr)
    RelativeLayout lxr;
    @Bind(R.id.sg)
    TextView sg;
    @Bind(R.id.sgr)
    RelativeLayout sgr;
    @Bind(R.id.rr)
    LinearLayout rr;
    @Bind(R.id.rrr)
    LinearLayout rrr;
    @Bind(R.id.waihandle_rv)
    ListView waihandleRv;
    @Bind(R.id.activity_disease_list_zhe_zhao_top_filter_layout)
    View activityDiseaseListZheZhaoTopFilterLayout;
    private YjPopu DjPop;
    private ArrayList<String> listDjData = new ArrayList<>();
    private ArrayList<String> listLxData = new ArrayList<>();
    private YjPopu LxPop;
    private String yjdj = "";
    private String yjlx = "";
    private CommonAdapter<YJlistBean.YJYJLISTBean> adapter;
    private List<YJlistBean.YJYJLISTBean> listData = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setStatusBarColor(this, R.color.theme_color);
        setContentView(R.layout.act_yjyj);
        ButterKnife.bind(this);
        initview();
        initdata();
        initPop();
        linstener();
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

    private void linstener() {
        lxr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DjPop.show(rrr);

            }
        });
        sgr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LxPop.show(rrr);
            }
        });
        waihandleRv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(YjActivity.this, YjmxActivity.class);
                intent.putExtra("GUID", listData.get(position).getGUID_OBJ());
                startActivity(intent);
            }
        });
    }

    private void initPop() {
        listDjData.clear();
        listDjData.add("全部等级");
        listDjData.add("省Ⅰ级");
        listDjData.add("省Ⅱ级");
        listDjData.add("省Ⅲ级");
        listDjData.add("省Ⅳ级");
        listLxData.clear();
        listLxData.add("全部类型");
        listLxData.add("自然灾害");
        listLxData.add("事故灾害");
        listLxData.add("社会灾害");
        if (DjPop == null) {
            DjPop = new YjPopu(this, listDjData, new DiseaseNewSelectObjectListener() {
                @Override
                public void selectPosition(int position) {
                    if (listDjData.get(position).equals("全部等级")) {
                        listData.clear();
                        yjdj = "";
                        mPresenter.getlist(yjdj, yjlx);
                    } else {
                        if (listDjData.get(position).equals("省Ⅰ级")) {
                            yjdj = "1";
                            mPresenter.getlist(yjdj, yjlx);
                        } else if (listDjData.get(position).equals("省Ⅱ级")) {
                            yjdj = "2";
                            mPresenter.getlist(yjdj, yjlx);
                        } else if (listDjData.get(position).equals("省Ⅲ级")) {
                            yjdj = "3";
                            mPresenter.getlist(yjdj, yjlx);
                        } else if (listDjData.get(position).equals("省Ⅳ级")) {
                            yjdj = "4";
                            mPresenter.getlist(yjdj, yjlx);
                        }
                    }
                    lx.setText(listDjData.get(position));

                }
            });
        } else {
            DjPop.notifityData();
        }
        if (LxPop == null) {
            LxPop = new YjPopu(this, listLxData, new DiseaseNewSelectObjectListener() {
                @Override
                public void selectPosition(int position) {
                    listData.clear();
                    if (listLxData.get(position).equals("全部类型")) {
                        yjlx = "";
                        mPresenter.getlist(yjdj, yjlx);
                    } else {
                        if (listLxData.get(position).equals("自然灾害")) {
                            yjlx = "1";
                            mPresenter.getlist(yjdj, yjlx);
                        } else if (listLxData.get(position).equals("事故灾害")) {
                            yjlx = "2";
                            mPresenter.getlist(yjdj, yjlx);
                        } else if (listLxData.get(position).equals("社会灾害")) {
                            yjlx = "3";
                            mPresenter.getlist(yjdj, yjlx);
                        }
                    }
                    sg.setText(listLxData.get(position));
                }
            });
        } else {
            DjPop.notifityData();
        }
    }

    private void initdata() {
        mPresenter.getlist(yjdj, yjlx);
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
    public void getData2(YJlistBean videoVos2) {
        setadapter(videoVos2);
    }

    private void setadapter(YJlistBean videoVos2) {
        listData = videoVos2.getYJYJLIST();
        adapter = new CommonAdapter<YJlistBean.YJYJLISTBean>(this,
                R.layout.item_yj, listData) {
            @Override
            protected void convert(ViewHolder holder, final YJlistBean.YJYJLISTBean tubiaoVo, int position) {
                final String GUID = tubiaoVo.getGUID_OBJ();
                holder.setText(R.id.time, tubiaoVo.getFBSJ().replace("T" ," "));
                holder.setText(R.id.sheng, tubiaoVo.getYJJB());
                holder.setText(R.id.yj_name, tubiaoVo.getYJNAME());
                holder.setOnClickListener(R.id.gwzz, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(YjActivity.this, YjmxActivity.class);
                        intent.putExtra("GUID", GUID);
                        startActivity(intent);
                    }
                });
                holder.setOnClickListener(R.id.ck, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(YjActivity.this, YjmxActivity.class);
                        intent.putExtra("GUID", GUID);
                        startActivity(intent);
                    }
                });
            }
        };
        waihandleRv.setAdapter(adapter);
    }
}
