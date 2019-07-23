package com.tyiroad.tyiroad.log.looklog;


import android.os.Bundle;


import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tooklkit.Tooklkit;
import com.tyiroad.tyiroad.Bean.LookIntbean;
import com.tyiroad.tyiroad.R;
import com.tyiroad.tyiroad.db.dbhelper.CuringDaoImpl;
import com.tyiroad.tyiroad.log.lookdblog.LooklogGredAdapter;
import com.tyiroad.tyiroad.mvp.MVPBaseActivity;
import com.tyiroad.tyiroad.utils.Utils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class LookLogActivity extends MVPBaseActivity<LookLogContract.View, LookLogPresenter> implements LookLogContract.View {

    @Bind(R.id.go_back)
    LinearLayout goBack;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.right)
    TextView right;
    @Bind(R.id.xcdw)
    TextView xcdw;
    @Bind(R.id.lxre)
    RecyclerView lxre;
    @Bind(R.id.xclx_layout)
    LinearLayout xclxLayout;
    @Bind(R.id.xcrq)
    TextView xcrq;
    @Bind(R.id.xcrq_layout)
    LinearLayout xcrqLayout;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.dqtq)
    TextView dqtq;
    @Bind(R.id.dqtq_layout)
    LinearLayout dqtqLayout;
    @Bind(R.id.dclx)
    TextView dclx;
    @Bind(R.id.dclx_layout)
    LinearLayout dclxLayout;
    @Bind(R.id.disease_new_bh_content_layout)
    LinearLayout diseaseNewBhContentLayout;
    @Bind(R.id.layoutt)
    LinearLayout layoutt;
    private CuringDaoImpl curingDao;
    private TextView edqclj, edqcss, edqczc, edwjj, edgj, edlmyh, edgfj, edysc, czwtet;
    private GridView caiJiPictureAddGrid;
    private LinearLayout diseasenewbh;
    private List<String> nrlist = new ArrayList<>();
    private String cameraPath;//拍照获取图片的地址
    private int childViewPosition;//拍照获取图片的地址
    private String nrstring;
    private LooklogGredAdapter addPictureAdapter;
    private Gson gson = new Gson();
    private Map<Integer, LooklogGredAdapter> mapAdapter = new HashMap<>();
    private ArrayList<String> LXlist = new ArrayList<>();
    private ArrayList<String> ZHFWlist = new ArrayList<>();
    private CommonAdapter<String> lxrvadapter;

    public void setCameraPath(String cameraPath) {
        this.cameraPath = cameraPath;
    }

    public void setChildViewPosition(int childViewPosition) {
        this.childViewPosition = childViewPosition;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setStatusBarColor(this, R.color.theme_color);
        setContentView(R.layout.act_lookint);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        curingDao = new CuringDaoImpl(LookLogActivity.this);
        initview();
        mPresenter.testinfo(getIntent().getStringExtra("RZID"));
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setadapter() {
        lxrvadapter = new CommonAdapter<String>(this,
                R.layout.item_lxlog, LXlist) {
            @Override
            protected void convert(ViewHolder holder, final String tubiaoVo, int position) {
                if (ZHFWlist.size()>0){
                    holder.setText(R.id.neirongte, tubiaoVo + " " + replaceNull(ZHFWlist.get(position)));
                }else {
                    holder.setText(R.id.neirongte, tubiaoVo );
                }
            }
        };
        lxre.setAdapter(lxrvadapter);
    }

    private void initview() {
        title.setText("日志记录");
        lxre.setLayoutManager(new LinearLayoutManager(this));
        View diseaseView = LayoutInflater.from(LookLogActivity.this).inflate(R.layout.layout_lookintlog, null, false);
        diseaseNewBhContentLayout.addView(diseaseView);
        edqclj = (TextView) diseaseView.findViewById(R.id.ed_qclj);
        edqcss = (TextView) diseaseView.findViewById(R.id.ed_qcss);
        edqczc = (TextView) diseaseView.findViewById(R.id.ed_qczc);
        edwjj = (TextView) diseaseView.findViewById(R.id.ed_wjj);
        edgj = (TextView) diseaseView.findViewById(R.id.ed_gj);
        edlmyh = (TextView) diseaseView.findViewById(R.id.ed_lmyh);
        edgfj = (TextView) diseaseView.findViewById(R.id.ed_gfj);
        edysc = (TextView) diseaseView.findViewById(R.id.ed_ysc);
        czwtet = (TextView) diseaseView.findViewById(R.id.czwt_et);
        caiJiPictureAddGrid = (GridView) diseaseView.findViewById(R.id.cai_ji_picture_add_grid);
        diseasenewbh = (LinearLayout) diseaseView.findViewById(R.id.disease_new_bh_content_layou);
    }

    @Override
    public void getData(LookIntbean videoVos2) {
        if (videoVos2.getState().equals("1")) {
            nrlist = Arrays.asList(videoVos2.getXcrzMx().get(0).getXCNR().split(","));
            xcdw.setText(replaceNull(videoVos2.getXcrzMx().get(0).getXCR()));
            xcrq.setText(replaceNull(videoVos2.getXcrzMx().get(0).getXCSJ().replace("T", " ")));
            dqtq.setText(replaceNull(videoVos2.getXcrzMx().get(0).getTQ()));
            if (videoVos2.getXcrzMx().get(0).getDCLX() != null && curingDao.queryInvestigationById(videoVos2.getXcrzMx().get(0).getDCLX()) != null) {
                dclx.setText(curingDao.queryInvestigationById(videoVos2.getXcrzMx().get(0).getDCLX()).getDCMC());
            }
            czwtet.setText(replaceNull(videoVos2.getXcrzMx().get(0).getCLJL()));
            if (nrlist.size()>1){
                edqclj.setText(replaceNull(nrlist.get(0)));
            }
            if (nrlist.size()>2){
                edqcss.setText(replaceNull(nrlist.get(1)));
            }
            if (nrlist.size()>3){
                edqczc.setText(replaceNull(nrlist.get(2)));
            }
            if (nrlist.size()>4){
                edwjj.setText(replaceNull(nrlist.get(3)));
            }
            if (nrlist.size()>5){
                edgj.setText(replaceNull(nrlist.get(4)));
            }
            if (nrlist.size()>6){
                edlmyh.setText(replaceNull(nrlist.get(5)));
            }
            if (nrlist.size()>7){
                edgfj.setText(replaceNull(nrlist.get(6)));
            }
            if (nrlist.size()>8){
                edysc.setText(replaceNull(nrlist.get(7)));
            }
            ArrayList<Drawable> listPicture = new ArrayList<>();
            ArrayList<String> listImgUrl = new ArrayList<>();
            if (videoVos2.getPIC() == null || videoVos2.getPIC().equals("") || videoVos2.getPIC().size() == 0) {
                Drawable addPicture = getResources().getDrawable(R.drawable.morentu);
                listPicture.add(addPicture);
            } else {
                for (int i = 0; i < videoVos2.getPIC().size(); i++) {
                    listImgUrl.add(videoVos2.getPIC().get(i).getFILEPATH());
                }
                for (int i = 0; i < listImgUrl.size(); i++) {
                    Bitmap bitmap = null;
                    bitmap = Tooklkit.getImageThumbnail(listImgUrl.get(i), Tooklkit.dip2px(this, 480),
                            Tooklkit.dip2px(this, 480));
                    if (bitmap != null) {
                        Drawable drawable = new BitmapDrawable(bitmap);
                        listPicture.add(drawable);
                    }
                }
            }
            final int childPosition = diseasenewbh.getChildCount();
            addPictureAdapter = new LooklogGredAdapter(this, listImgUrl);
            caiJiPictureAddGrid.setAdapter(addPictureAdapter);
            mapAdapter.put(childPosition, addPictureAdapter);
            if (videoVos2.getXcrzMx().get(0).getLDMC().contains(",")) {
                LXlist.addAll(Arrays.asList(videoVos2.getXcrzMx().get(0).getLDMC().split(",")));//默认选择的id集合
            } else {
                LXlist.add(videoVos2.getXcrzMx().get(0).getLDMC());
            }
            if (!Utils.isNull(videoVos2.getXcrzMx().get(0).getZHFW())){
                if (videoVos2.getXcrzMx().get(0).getZHFW().contains(",")) {
                    ZHFWlist.addAll(Arrays.asList(videoVos2.getXcrzMx().get(0).getZHFW().split(",")));//桩号范围集合
                } else {
                    ZHFWlist.add(videoVos2.getXcrzMx().get(0).getZHFW());
                }
            }
            setadapter();
        }
    }

    @Override
    public void onRequestError(String msg) {

    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public static String replaceNull(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }
}

