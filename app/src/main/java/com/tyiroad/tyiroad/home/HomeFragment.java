package com.tyiroad.tyiroad.home;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.tyiroad.tyiroad.Bean.CdflInfo;
import com.tyiroad.tyiroad.Bean.SgRInfo;
import com.tyiroad.tyiroad.MyApplication;
import com.tyiroad.tyiroad.R;
import com.tyiroad.tyiroad.check.CheckActivity;
import com.tyiroad.tyiroad.code.CodeActivity;
import com.tyiroad.tyiroad.db.dbhelper.CuringDaoImpl;
import com.tyiroad.tyiroad.db.dbhelper.dbInfo.FilterBHInfo;
import com.tyiroad.tyiroad.db.dbhelper.dbInfo.FilterLxInfo;
import com.tyiroad.tyiroad.disease.DiseaseActivity;
import com.tyiroad.tyiroad.documentation.DocumentationActivity;
import com.tyiroad.tyiroad.documentation.list.LIstActivity;
import com.tyiroad.tyiroad.handle.HandleActivity;
import com.tyiroad.tyiroad.handle.LoadDataDialog;
import com.tyiroad.tyiroad.log.LogActivity;
import com.tyiroad.tyiroad.monitoring.MonitoringActivity;
import com.tyiroad.tyiroad.mvp.MVPBaseFragment;
import com.tyiroad.tyiroad.other.OtherActivity;
import com.tyiroad.tyiroad.quality.qualitylist.QualitylistActivity;
import com.tyiroad.tyiroad.repair.RepairActivity;
import com.tyiroad.tyiroad.season.SeasonActivity;
import com.tyiroad.tyiroad.utils.PermissionUtils;
import com.tyiroad.tyiroad.utils.Utils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class HomeFragment extends MVPBaseFragment<HomeContract.View, HomePresenter> implements HomeContract.View {


    @Bind(R.id.gs)
    TextView gs;
    @Bind(R.id.rcyh_recycle)
    RecyclerView rcyhRecycle;
    @Bind(R.id.scroll)
    ScrollView scroll;
    private List<CdflInfo> cdflInfo = new ArrayList<>();
    private CommonAdapter<CdflInfo.ZCDBean> adapter;
    private int[] guide_images = {
            R.drawable.gcys_img,
            R.drawable.xcbi_img,
            R.drawable.shpf_img,
            R.drawable.sgwx_img,
            R.drawable.xcrz_img,
            R.drawable.xcrz_img,
    };
    private String xcrzTitle = "巡查日志";
    private String xcbhTitle = "巡查病害";
    private String shpfTitle = "审核派发";
    private String sgwxTitle = "施工维修";
    private String gcysTitle = "工程验收";
    private String qtTitle = "其他";
    private String qlsmTitle = "桥梁扫码";
    private String jjxyhTitle = "季节性养护";
    private String zljyTitle = "质量检验";
    private String gcjkTitle = "过程监控";
    private String qlglTitle = "桥梁管理";
    private String wdzlTitle = "文档资料";
    private List<CdflInfo.ZCDBean> Datas = new ArrayList<>();
    private List<CdflInfo.ZCDBean> Datas2 = new ArrayList<>();
    private CommonAdapter<CdflInfo.ZCDBean> adapter2;
    private PermissionUtils permissionUtils;
    private ArrayList<String> permissionsOfSDCardAndCamera;
    private LoadDataDialog loadDataDialog;
    private CuringDaoImpl curingDao;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_home, null);
        ButterKnife.bind(this, view);
        curingDao = new CuringDaoImpl(getActivity());
        initData();
        linstener();
        checkPermissionsOfSDCardAndCamera();
        return view;
    }

    private void linstener() {
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                if (Datas.get(position ).getCDMC().equals("巡查日志")) {
                    Intent intent = new Intent(getActivity(), LogActivity.class);
                    startActivity(intent);
                } else if (Datas.get(position ).getCDMC().equals("巡查病害")) {
                    Intent intent = new Intent(getActivity(), DiseaseActivity.class);
                    startActivity(intent);
                } else if (Datas.get(position ).getCDMC().equals("审核派发")) {
                    Intent intent = new Intent(getActivity(), RepairActivity.class);
                    startActivity(intent);
                } else if (Datas.get(position ).getCDMC().equals("施工维修")) {
                    if (Utils.isNetworkAvailable(getContext()) == false) {//没网
                        Intent intent = new Intent(getActivity(), HandleActivity.class);
                        startActivity(intent);
                    } else {//有网
                        String str = "正在加载...";
                        showLoadingDialogMethod(str);
                        mPresenter.getData();
                    }
                } else if (Datas.get(position ).getCDMC().equals("工程验收")) {
                    Intent intent = new Intent(getActivity(), CheckActivity.class);
                    startActivity(intent);
                } else if (Datas.get(position ).getCDMC().equals("季节性养护")) {
                    Intent intent = new Intent(getActivity(), SeasonActivity.class);
                    startActivity(intent);
                } else if (Datas.get(position ).getCDMC().equals("其他")) {
                    Intent intent = new Intent(getActivity(), OtherActivity.class);
                    startActivity(intent);
                } else if (Datas.get(position ).getCDMC().equals("桥梁扫码")) {
                    Intent intent = new Intent(getActivity(), CodeActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    private void setAdapter() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        rcyhRecycle.setLayoutManager(gridLayoutManager);
        adapter = new CommonAdapter<CdflInfo.ZCDBean>(getActivity(), R.layout.item, Datas) {

            @Override
            protected void convert(ViewHolder holder, CdflInfo.ZCDBean zCDBean, int position) {
                if (zCDBean.getCDMC().equals("工程验收")) {
                    holder.setBackgroundRes(R.id.im, guide_images[0]);
                    holder.setText(R.id.te, zCDBean.getCDMC());
                } else if (zCDBean.getCDMC().equals("巡查病害")) {
                    holder.setBackgroundRes(R.id.im, guide_images[1]);
                    holder.setText(R.id.te, zCDBean.getCDMC());
                } else if (zCDBean.getCDMC().equals("审核派发")) {
                    holder.setBackgroundRes(R.id.im, guide_images[2]);
                    holder.setText(R.id.te, zCDBean.getCDMC());
                } else if (zCDBean.getCDMC().equals("施工维修")) {
                    holder.setBackgroundRes(R.id.im, guide_images[3]);
                    holder.setText(R.id.te, zCDBean.getCDMC());
                } else if (zCDBean.getCDMC().equals("巡查日志")) {
                    holder.setBackgroundRes(R.id.im, guide_images[4]);
                    holder.setText(R.id.te, zCDBean.getCDMC());
                } else if (zCDBean.getCDMC().equals("季节性养护")) {
                    holder.setBackgroundRes(R.id.im, guide_images[5]);
                    holder.setText(R.id.te, zCDBean.getCDMC());
                } else if (zCDBean.getCDMC().equals("其他")) {
                    holder.setBackgroundRes(R.id.im, guide_images[5]);
                    holder.setText(R.id.te, zCDBean.getCDMC());
                } else if (zCDBean.getCDMC().equals("桥梁扫码")) {
                    holder.setBackgroundRes(R.id.im, guide_images[5]);
                    holder.setText(R.id.te, zCDBean.getCDMC());
                }
            }
        };
        rcyhRecycle.setAdapter(adapter);
        if (Datas2.size()>0){
            HeaderAndFooterWrapper mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(adapter);
            View footView = LayoutInflater.from(getActivity()).inflate(R.layout.foot, null);
            RecyclerView footRecycler = (RecyclerView) footView.findViewById(R.id.foot_list);
            GridLayoutManager grid = new GridLayoutManager(getActivity(), 2);
            footRecycler.setLayoutManager(grid);
            adapter2 = new CommonAdapter<CdflInfo.ZCDBean>(getActivity(), R.layout.item, Datas2) {

                @Override
                protected void convert(ViewHolder holder, CdflInfo.ZCDBean zCDBean, int position) {
                    if (zCDBean.getCDMC().equals("质量检验")) {
                        holder.setBackgroundRes(R.id.im, guide_images[2]);
                        holder.setText(R.id.te, zCDBean.getCDMC());
                    } else if (zCDBean.getCDMC().equals("过程监控")) {
                        holder.setBackgroundRes(R.id.im, guide_images[3]);
                        holder.setText(R.id.te, zCDBean.getCDMC());
                    }else if (zCDBean.getCDMC().equals("文档资料")) {
                        holder.setBackgroundRes(R.id.im, guide_images[1]);
                        holder.setText(R.id.te, "文档资料");
                    }
                }
            };
            footRecycler.setAdapter(adapter2);
            mHeaderAndFooterWrapper.addFootView(footView);
            rcyhRecycle.setAdapter(mHeaderAndFooterWrapper);
            mHeaderAndFooterWrapper.notifyDataSetChanged();
            adapter2.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                    if (Datas2.get(position).getCDMC().equals("质量检验")) {
                        Intent intent = new Intent(getActivity(), QualitylistActivity.class);
                        startActivity(intent);
                    } else if (Datas2.get(position).getCDMC().equals("过程监控")) {
                        Intent intent = new Intent(getActivity(), MonitoringActivity.class);
                        startActivity(intent);
                    }else if (Datas2.get(position).getCDMC().equals("文档资料")) {
                        Intent intent = new Intent(getActivity(), LIstActivity.class);
                        startActivity(intent);
                    }
                }
                @Override
                public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                    return false;
                }
            });
        }
        scroll.smoothScrollTo(0, 20);
    }

    private void initData() {
        cdflInfo = JSON.parseArray(MyApplication.app.spUtils.getString("acfl"), CdflInfo.class);
        for (int i = 0; i < cdflInfo.get(0).getZCD().size(); i++) {
            String title = cdflInfo.get(0).getZCD().get(i).getCDMC();
            if (xcrzTitle.equals(title) || xcbhTitle.equals(title) || shpfTitle.equals(title) || sgwxTitle.equals(title) || gcysTitle.equals(title) || jjxyhTitle.equals(title) || qtTitle.equals(title) || qlsmTitle.equals(title)) {
                Datas.add(cdflInfo.get(0).getZCD().get(i));
            }
            if (wdzlTitle.equals(title)||zljyTitle.equals(title) || gcjkTitle.equals(title)) {
                Datas2.add(cdflInfo.get(0).getZCD().get(i));
            }
        }
        gs.setText(MyApplication.spUtils.getString("dqgydwmc"));
        setAdapter();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void showLoadingDialogMethod(String str) {
        if (loadDataDialog == null) {
            loadDataDialog = new LoadDataDialog(getActivity());
        }
        loadDataDialog.setTitleStr(str);
        loadDataDialog.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // “读写SD卡”的权限结果处理
        if (permissionUtils != null && permissionsOfSDCardAndCamera != null && permissionUtils.dealRequestPermissionsResult(
                permissionsOfSDCardAndCamera, requestCode, permissions, grantResults)) {
            permissionsOfSDCardAndCamera = null;
            return;
        }
    }

    public boolean checkPermissionsOfSDCardAndCamera() {
        if (permissionUtils == null) {
            permissionUtils = PermissionUtils
                    .newInstance(getActivity());
        }
        if (permissionsOfSDCardAndCamera == null) {
            permissionsOfSDCardAndCamera = new ArrayList<String>();
            permissionsOfSDCardAndCamera.add(Manifest.permission.CAMERA);
            permissionsOfSDCardAndCamera.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            permissionsOfSDCardAndCamera.add(Manifest.permission.RECORD_AUDIO);
            permissionsOfSDCardAndCamera.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            permissionsOfSDCardAndCamera.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            permissionsOfSDCardAndCamera.add(Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS);
            permissionsOfSDCardAndCamera.add(Manifest.permission.CHANGE_WIFI_STATE);
        }
        return permissionUtils.requestPermissions(permissionsOfSDCardAndCamera);
    }

    @Override
    public void onRequestError(String msg) {

    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void getData(SgRInfo videoVos2) {
        if (videoVos2.getSTATE().equals("1")) {
            if (loadDataDialog != null && loadDataDialog.isShowing()) {
                loadDataDialog.cancel();
            }
            if (videoVos2.getLXDATA() != null && videoVos2.getLXDATA().size() > 0) {
                ArrayList<FilterLxInfo> lxlistInfo = new ArrayList<>();
                for (int i = 0; i < videoVos2.getLXDATA().size(); i++) {
                    if (!Utils.isNull(videoVos2.getLXDATA().get(i).getLXID())
                            && !Utils.isNull(videoVos2.getLXDATA().get(i).getLXMC())) {
                        FilterLxInfo LXlist = new FilterLxInfo();
                        LXlist.setLXID(videoVos2.getLXDATA().get(i).getLXID());
                        LXlist.setLXMC(videoVos2.getLXDATA().get(i).getLXMC());
                        lxlistInfo.add(LXlist);
                    }
                }
                curingDao.deleteAllLXInfo();
                curingDao.addFilterLXInfo(lxlistInfo);
            }
            if (videoVos2.getBHLXDATA() != null && videoVos2.getBHLXDATA().size() > 0) {
                ArrayList<FilterBHInfo> bhlistInfo = new ArrayList<>();
                for (int i = 0; i < videoVos2.getBHLXDATA().size(); i++) {
                    if (!Utils.isNull(videoVos2.getBHLXDATA().get(i).getBHLXID())
                            && !Utils.isNull(videoVos2.getBHLXDATA().get(i).getBHLX())) {
                        FilterBHInfo BHlist = new FilterBHInfo();
                        BHlist.setBHMC(videoVos2.getBHLXDATA().get(i).getBHLX());
                        BHlist.setBHMCID(videoVos2.getBHLXDATA().get(i).getBHLXID());
                        bhlistInfo.add(BHlist);
                    }
                }
                curingDao.deleteAllBHInfo();
                curingDao.addFilterBHInfo(bhlistInfo);
            }
            Intent intent = new Intent(getActivity(), HandleActivity.class);
            intent.putExtra("videoVos", new Gson().toJson(videoVos2));
            startActivity(intent);
        }
    }
}