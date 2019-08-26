package com.tyiroad.tyiroad.log.organization;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.multilevel.treelist.Node;
import com.tyiroad.tyiroad.MyApplication;
import com.tyiroad.tyiroad.R;
import com.tyiroad.tyiroad.mvp.MVPBaseActivity;
import com.tyiroad.tyiroad.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import android.view.View;
import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class OrganizationActivity extends MVPBaseActivity<OrganizationContract.View, OrganizationPresenter> implements OrganizationContract.View {
    private LinearLayout mRootView;

    private View view1;
    private View view2;
    private View view3;
    private ListView list1, list2;
    private MOryAdapter mListView1Adapter;
    private List<View> views = new ArrayList<View>(); //数据集合
    private OrMyAdapter2 mListView1Adapter2;
    private int type = 1;
    private List<ChildrenBean> javabean1 = new ArrayList<>();
    private List<ChildrenBean> javabean2 = new ArrayList<>();
    private List<ChildrenBean> javabean3 = new ArrayList<>();
    private List<ChildrenBean> javabean4 = new ArrayList<>();
    private List<ChildrenBean> javabean5 = new ArrayList<>();
    private int positions;
    private TextView sheng ,shi,qu;
    private TextView shite,qute;
    private int shiposition;
    private int shengposition;
    private TextView lift,right;
    private String guid_obj;
   

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setStatusBarColor(this, R.color.theme_color);
        setContentView(R.layout.act_to_stting);
        initview();
        getdate();
        liner();
    }

    private void liner() {
        shi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qu.setVisibility(View.GONE);
                qute.setVisibility(View.GONE);
                javabean2 = javabean1.get(shengposition).getChildren();
                mListView1Adapter = new MOryAdapter(javabean2, OrganizationActivity.this,shiposition);
                list1.setAdapter(mListView1Adapter);
                javabean3 = javabean2.get(shiposition).getChildren();
                mListView1Adapter2 = new OrMyAdapter2(javabean3, OrganizationActivity.this,1000);
                list2.setAdapter(mListView1Adapter2);
                type=2;
            }
        });
        sheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getdate();
                shi.setVisibility(View.GONE);
                shite.setVisibility(View.GONE);
                qu.setVisibility(View.GONE);
                qute.setVisibility(View.GONE);
            }
        });
        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (type == 1) {
                    javabean2 = javabean1.get(position).getChildren();
                    mListView1Adapter2 = new OrMyAdapter2(javabean2, OrganizationActivity.this,1000);
                    list2.setAdapter(mListView1Adapter2);
                    mListView1Adapter = new MOryAdapter(javabean1, OrganizationActivity.this,position);
                    list1.setAdapter(mListView1Adapter);
                    sheng.setText(javabean1.get(position).getGYDWMC());
                    shengposition = position;
                    guid_obj =javabean1.get(position).getGYDWID();
                } else if (type == 2) {
                    javabean3 = javabean2.get(position).getChildren();
                    mListView1Adapter2 = new OrMyAdapter2(javabean3, OrganizationActivity.this,1000);
                    list2.setAdapter(mListView1Adapter2);
                    mListView1Adapter = new MOryAdapter(javabean2, OrganizationActivity.this,position);
                    list1.setAdapter(mListView1Adapter);
                    shi.setVisibility(View.VISIBLE);
                    shite.setVisibility(View.VISIBLE);
                    shi.setText(javabean2.get(position).getGYDWMC());
                    shiposition=position;
                    guid_obj =javabean2.get(position).getGYDWID();
                } else if (type == 3) {
                    javabean4 = javabean3.get(position).getChildren();
                    mListView1Adapter2 = new OrMyAdapter2(javabean4, OrganizationActivity.this,1000);
                    list2.setAdapter(mListView1Adapter2);
                    mListView1Adapter = new MOryAdapter(javabean3, OrganizationActivity.this,position);
                    list1.setAdapter(mListView1Adapter);
                    qu.setText(javabean3.get(position).getGYDWMC());
                    guid_obj =javabean3.get(position).getGYDWID();
                } else if (type == 4) {
                    javabean5 = javabean4.get(position).getChildren();
                    mListView1Adapter2 = new OrMyAdapter2(javabean5, OrganizationActivity.this,1000);
                    list2.setAdapter(mListView1Adapter2);
                    mListView1Adapter = new MOryAdapter(javabean4, OrganizationActivity.this,position);
                    list1.setAdapter(mListView1Adapter);
                } else if (type == 5) {

                }

            }
        });
        list2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (type == 1) {
                    javabean3 = javabean2.get(position).getChildren();
                    if (javabean3.size()==0){
                        Log.e( "点击了onItemClick: ",javabean2.get(position).getGYDWID() );
                        guid_obj=javabean2.get(position).getGYDWID();
                        mListView1Adapter2 = new OrMyAdapter2(javabean2, OrganizationActivity.this,position);
                        list2.setAdapter(mListView1Adapter2);
                    }else {
                        type = 2;
                        mListView1Adapter.notifyDataSetChanged();
                        mListView1Adapter = new MOryAdapter(javabean2, OrganizationActivity.this,position);
                        list1.setAdapter(mListView1Adapter);
                        list2.setAdapter(null);
                        mListView1Adapter2 = new OrMyAdapter2(javabean3, OrganizationActivity.this,1000);
                        list2.setAdapter(mListView1Adapter2);
                        shite.setVisibility(View.VISIBLE);
                        shi.setVisibility(View.VISIBLE);
                        shi.setText(javabean2.get(position).getGYDWMC());
                        shiposition=position;
                        guid_obj =javabean2.get(position).getGYDWID();
                    }
                } else if (type == 2) {
                    javabean4=javabean3.get(position).getChildren();
                    if (javabean4.size()==0){
                        Log.e( "点击了onItemClick: ",javabean3.get(position).getGYDWID() );
                        guid_obj=javabean3.get(position).getGYDWID();
                        mListView1Adapter2 = new OrMyAdapter2(javabean3, OrganizationActivity.this,position);
                        list2.setAdapter(mListView1Adapter2);
                    }else {
                        type = 3;
                        mListView1Adapter = new MOryAdapter(javabean3, OrganizationActivity.this,position);
                        list1.setAdapter(mListView1Adapter);
                        list2.setAdapter(null);
                        mListView1Adapter2 = new OrMyAdapter2(javabean4, OrganizationActivity.this,1000);
                        list2.setAdapter(mListView1Adapter2);
                        qute.setVisibility(View.VISIBLE);
                        qu.setVisibility(View.VISIBLE);
                        qu.setText(javabean3.get(position).getGYDWMC());
                        guid_obj =javabean3.get(position).getGYDWID();
                    }
                } else if (type == 3) {
                    javabean5 = javabean4.get(position).getChildren();
                    if (javabean5.size()==0){
                        Log.e( "点击了onItemClick: ",javabean4.get(position).getGYDWID() );
                        guid_obj=javabean4.get(position).getGYDWID();
                        mListView1Adapter2 = new OrMyAdapter2(javabean4, OrganizationActivity.this,position);
                        list2.setAdapter(mListView1Adapter2);
                    }else {
                        type = 4;
                        mListView1Adapter = new MOryAdapter(javabean4, OrganizationActivity.this,position);
                        list1.setAdapter(mListView1Adapter);
                        list2.setAdapter(null);
                        mListView1Adapter2 = new OrMyAdapter2(javabean5, OrganizationActivity.this,1000);
                        list2.setAdapter(mListView1Adapter2);
                        guid_obj =javabean4.get(position).getGYDWID();
                    }
                } else if (type == 4) {
                    type = 5;
                    mListView1Adapter = new MOryAdapter(javabean5, OrganizationActivity.this,position);
                    list1.setAdapter(mListView1Adapter);
                    list2.setAdapter(null);
                    mListView1Adapter2 = new OrMyAdapter2(javabean5.get(position).getChildren(), OrganizationActivity.this,1000);
                    list2.setAdapter(mListView1Adapter2);
                }
//                list2.setAdapter(null);
            }
        });
        lift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dw = "";
                if (qu.getVisibility()==View.VISIBLE){
                    dw =qu.getText().toString();
                }else  if (shi.getVisibility()==View.VISIBLE){
                    dw =shi.getText().toString();
                }else {
                    dw = sheng.getText().toString();
                }
                Log.e("张成昆: ",guid_obj );
                Intent intent = new Intent();
                intent.putExtra("ID", guid_obj);
                intent.putExtra("DW", dw);
                setResult(2, intent);
                finish();
            }
        });
    }

    private void initview() {
        list1 = (ListView) findViewById(R.id.list1);
        list2 = (ListView) findViewById(R.id.list2);
        sheng = (TextView) findViewById(R.id.sheng);
        shi = (TextView) findViewById(R.id.shi);
        qu = (TextView) findViewById(R.id.qu);
        shite = (TextView) findViewById(R.id.shite);
        qute = (TextView) findViewById(R.id.qute);
        lift = (TextView) findViewById(R.id.lift);
        right = (TextView) findViewById(R.id.right);
    }

    private void getdate() {
        List<OrBean> Data = JSON.parseArray(MyApplication.app.spUtils.getString("GYDW"), OrBean.class);
        javabean1 = Data.get(0).getChildren();
        mListView1Adapter = new MOryAdapter(javabean1, OrganizationActivity.this,0);
        list1.setAdapter(mListView1Adapter);
        type = 1;
        javabean2 = javabean1.get(0).getChildren();
        mListView1Adapter2 = new OrMyAdapter2(javabean2, OrganizationActivity.this,1000);
        list2.setAdapter(mListView1Adapter2);
        sheng.setText(javabean1.get(0).getGYDWMC());
        shengposition = 0;
        guid_obj = javabean1.get(0).getGYDWID();
    }
}
