package com.tyiroad.tyiroad.repair;


import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.tyiroad.tyiroad.Bean.Basebean;
import com.tyiroad.tyiroad.Bean.ToExamineInfo;
import com.tyiroad.tyiroad.Bean.repairPFjson;
import com.tyiroad.tyiroad.Bean.repairRKbean;
import com.tyiroad.tyiroad.MyApplication;
import com.tyiroad.tyiroad.R;
import com.tyiroad.tyiroad.examinedeal.ExamineDealFragment;
import com.tyiroad.tyiroad.examinedone.ExamineDoneFragment;
import com.tyiroad.tyiroad.mvp.MVPBaseActivity;
import com.tyiroad.tyiroad.thread.NetAPIExecutor;
import com.tyiroad.tyiroad.utils.Dialog.CommBtnListener;
import com.tyiroad.tyiroad.utils.Dialog.CommNotificationDialog;
import com.tyiroad.tyiroad.utils.DiseaeNewSelectObjectPopupWindow;
import com.tyiroad.tyiroad.utils.DiseaseNewSelectObjectListener;
import com.tyiroad.tyiroad.utils.PermissionUtils;
import com.tyiroad.tyiroad.utils.PopSelectListener;
import com.tyiroad.tyiroad.utils.Utils;
import com.tyiroad.tyiroad.utils.repairTimepopu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class RepairActivity extends MVPBaseActivity<RepairContract.View, RepairPresenter> implements RepairContract.View {
    @Bind(R.id.to_examine_back_layout)
    LinearLayout toExamineBackLayout;
    @Bind(R.id.to_examine_dai_ban_btn_txt)
    TextView toExamineDaiBanBtnTxt;
    @Bind(R.id.to_examine_yi_ban_btn_txt)
    TextView toExamineYiBanBtnTxt;

    @Bind(R.id.to_examine_pf_txt)
    TextView toExaminePfTxt;


    @Bind(R.id.activity_disease_list_zhe_zhao_top_filter_layout)
    View vFilterZheZhao;

    @Bind(R.id.to_examine_ssld_txt)
    TextView toExamineSsldTxt;
    @Bind(R.id.to_examine_sgdw_txt)
    TextView toExamineSgdwTxt;
    @Bind(R.id.to_examine_bottom_btn_layout)
    LinearLayout toExamineBottomBtnLayout;
    @Bind(R.id.to_examine_ssld_layout)
    LinearLayout toExamineSsldLayout;
    @Bind(R.id.to_examine_sgdw_layout)
    LinearLayout toExamineSgdwLayout;
    @Bind(R.id.activity_to_examine)
    LinearLayout activityToExamine;

    /******
     * 控件声明
     ******/
    private ToExamineWarmDialog warmDialog;//审核派发提示框
    private String pfnumber = "";//派发单号
    private String sgdwid = "";//施工单位Id
    private String sgfzr = "";
    private String htid = "";
    private String lxid = "";
    private String qdid = "";
    private String gydwid = "";
    private String bhlx = "";
    private String listtype = "0";//0:待办 1:已办
    private String startTime = "";
    private String endTime = "";
    private NetAPIExecutor netAPIExecutor;
    private final int TAG_BH_SHEN_HE = 2;//病害审核的网络标识
    private final int TAG_BH_PAI_FA = 3;//病害派发的网络标识
    private final int TAG_GET_PAI_FA_NUMBER = 4;//获取派发单号的网络标识
    private Gson gson = new Gson();
    private ArrayList<ToExamineInfo> listSelData;//当前选择的数据
    private ExamineDealFragment dealFragment;
    private ExamineDoneFragment doneFragment;
    private boolean isRefreshDealFragment;
    private boolean isRefreshDoneFragment;
    private PermissionUtils permissionUtils;//权限管理类
    private List<String> permissionsOfSDCardAndCamera;//读写sd卡权限 摄像头权限 位置权限
    private String guid_obj;

    private ArrayList<String> listDWResult = new ArrayList<>();
    private String dh;

    private DiseaeNewSelectObjectPopupWindow sgdwPop;
    private SimpleDateFormat simpleDateFormat;
    private repairTimepopu popupWindow;
    private String GZZLBH;//单号
    private ArrayList<String> listDWIDResult= new ArrayList<>();

    private String gydww;

    private CommNotificationDialog SBDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setStatusBarColor(this, R.color.theme_color);
        setContentView(R.layout.act_repair);
        ButterKnife.bind(this);
        MyApplication.GYDWID = "NO";
        MyApplication.GZZLBH = "NO";
        netAPIExecutor = NetAPIExecutor.newInstance(this);
        initView();
        showDealFragment();
    }

    private void initpopu() {
        sgdwPop = new DiseaeNewSelectObjectPopupWindow(this, "请选择施工单位", listDWResult, new DiseaseNewSelectObjectListener() {
            @Override
            public void selectPosition(int position) {
                toExamineSsldTxt.setText(listDWResult.get(position));
                MyApplication.GYDWID = listDWIDResult.get(position);
                gydww = listDWIDResult.get(position);

            }
        });
        popupWindow = new repairTimepopu(this, new PopSelectListener() {
            @Override
            public void selectResult(Object... result) {
                String selDataStr = result[0].toString();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
                try {
                    Date dateResult = sdf.parse(selDataStr);
                    selDataStr = simpleDateFormat.format(dateResult);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                toExamineSgdwTxt.setText(selDataStr);
            }
        });
    }

    private void initView() {
        gydwid = MyApplication.spUtils.getString("dqgydwid");
        mPresenter.addDisease(gydwid);
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        String currentDate = simpleDateFormat.format(calendar.getTime());
        toExamineSgdwTxt.setText(currentDate);
    }


    @OnClick({R.id.to_examine_back_layout, R.id.to_examine_dai_ban_btn_txt,
            R.id.to_examine_yi_ban_btn_txt,
            R.id.to_examine_pf_txt, R.id.to_examine_ssld_layout, R.id.to_examine_sgdw_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.to_examine_back_layout://返回按钮
                MyApplication.GYDWID= "NO";
                MyApplication.GZZLBH = "NO";
                finish();
                break;
            case R.id.to_examine_dai_ban_btn_txt://待办按钮
                    if (!"0".equals(listtype)) {
                        toExamineDaiBanBtnTxt.setTextColor(getResources().getColor(R.color.theme_color));
                        toExamineDaiBanBtnTxt.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_pf_shai_xuan_left_select));
                        toExamineYiBanBtnTxt.setTextColor(getResources().getColor(R.color.white));
                        toExamineYiBanBtnTxt.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_pf_shai_xuan_right_no_select));
                        toExamineBottomBtnLayout.setVisibility(View.VISIBLE);
                        listtype = "0";
                        showDealFragment();
                    }
                break;
            case R.id.to_examine_yi_ban_btn_txt://已办按钮
                    if (!"1".equals(listtype)) {
                        toExamineDaiBanBtnTxt.setTextColor(getResources().getColor(R.color.white));
                        toExamineDaiBanBtnTxt.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_pf_shai_xuan_left_no_select));
                        toExamineYiBanBtnTxt.setTextColor(getResources().getColor(R.color.theme_color));
                        toExamineYiBanBtnTxt.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_pf_shai_xuan_right_select));
                        toExamineBottomBtnLayout.setVisibility(View.GONE);
                        listtype = "1";
                        showDoneFragment();
                    }
                break;

            case R.id.to_examine_pf_txt://派发按钮
                if (Utils.isNetworkAvailable(this)) {
                    if (dealFragment != null) {
                        listSelData = dealFragment.getSelectInfoData("paifa");
                        if (listSelData != null) {
                            if (toExamineSsldTxt.getText().toString().equals("请选择施工单位")){
                                MyApplication.app.customToast("请选择施工单位");
                            }else{
                                showPaiFaTiShiDialog();
                            }

                        }
                    }
                } else {
                    MyApplication.app.customToast("您当前没有网络");
                }
                break;


            case R.id.to_examine_ssld_layout://施工单位
                if (sgdwPop != null ) {
                    sgdwPop.show(activityToExamine);
                }
                break;
            case R.id.to_examine_sgdw_layout://完成时间
                if (popupWindow != null ) {
                    popupWindow.show(activityToExamine);
                }
                break;
        }
    }

    /**
     * 显示派发提示框
     */
    private void showPaiFaTiShiDialog() {
        if (warmDialog == null) {
            warmDialog = new ToExamineWarmDialog(this, new CommBtnListener() {
                @Override
                public void CommOkBtnClick() {
                    paifaCommit();
                    warmDialog.close();
                }

                @Override
                public void CommCancelBtnClick() {

                }
            });
        }
        warmDialog.show();
    }

    /**
     * 派发
     */
    private void paifaCommit() {
        String creator = MyApplication.spUtils.getString("dlr");
        String bhStr = "";
        for (int i = 0; i < listSelData.size(); i++) {
            ToExamineInfo info = listSelData.get(i);
            if (i == listSelData.size() - 1) {
                bhStr += info.getBHID();
            } else {
                bhStr += info.getBHID() + ",";
            }
        }
        ArrayList<repairPFjson.PFDXXBean> PFDXXlist = new ArrayList<>();
        repairPFjson.PFDXXBean PFDXX=new repairPFjson.PFDXXBean();
        PFDXX.setCREATOR(creator);
        PFDXX.setGZZLBH(GZZLBH);
        PFDXX.setJLDW(gydww);
        PFDXX.setPFR(creator);
        PFDXX.setRWXDSJ(toExamineSgdwTxt.getText().toString());
        PFDXX.setSGDW(MyApplication.GYDWID);
        PFDXX.setYQWCSJ(toExamineSgdwTxt.getText().toString());
        PFDXXlist.add(PFDXX);
        ArrayList<repairPFjson.XCBHBean> XCBHlist = new ArrayList<>();
        repairPFjson.XCBHBean XCBH=new repairPFjson.XCBHBean();
        XCBH.setGUID_OBJ(bhStr);
        XCBHlist.add(XCBH);
        repairPFjson repairPFjson =  new repairPFjson();
        repairPFjson.setXCBH(XCBHlist);
        repairPFjson.setPFDXX(PFDXXlist);
        String JSON = gson.toJson(repairPFjson);
        mPresenter.paifaDisease(JSON);
    }
    private void showDealFragment() {
        if (dealFragment == null) {
            dealFragment = new ExamineDealFragment();
        }
        goToFragment(dealFragment);
    }

    private void showDoneFragment() {
        if (doneFragment == null) {
            doneFragment = new ExamineDoneFragment();
        }
        goToFragment(doneFragment);
    }

    Fragment mCurrentFragment;

    public void goToFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        if (!fragment.isAdded()) { // 先判断是否被add过
            if (mCurrentFragment != null) {
                transaction.hide(mCurrentFragment).add(R.id.fragment_container, fragment).commitAllowingStateLoss();
            } else {
                transaction.add(R.id.fragment_container, fragment).commitAllowingStateLoss();
            }
        } else {
            if (mCurrentFragment != null) {
                transaction.hide(mCurrentFragment).show(fragment).commitAllowingStateLoss();
            } else {
                transaction.show(fragment).commitAllowingStateLoss();
            }
        }
        mCurrentFragment = fragment;
        handler.sendEmptyMessageDelayed(1, 200);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    if ("0".equals(listtype)) {
                        if (isRefreshDealFragment) {
                            dealFragment.refreshDataMethod();
                            isRefreshDealFragment = false;
                        }
                    } else if ("1".equals(listtype)) {
                        if (isRefreshDoneFragment) {
                            doneFragment.refreshDataMethod();
                            isRefreshDoneFragment = false;
                        }
                    }
                    break;
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        if ("0".equals(listtype)) {
            dealFragment.refreshDataMethod();
        } else {
            doneFragment.refreshDataMethod();
        }
    }

    public String getSgdwid() {
        return sgdwid;
    }

    public String getSgfzr() {
        return sgfzr;
    }

    public String getHtid() {
        return htid;
    }

    public String getLxid() {
        return lxid;
    }

    public String getQdid() {
        return qdid;
    }

    public String getGydwid() {
        return gydwid;
    }

    public String getBhlx() {
        return bhlx;
    }

    public String getStartTime() {
        return toExamineSgdwTxt.getText().toString();
    }

    public  String getGZZLBH() {
        return GZZLBH;
    }
    /**
     * 进入新增病害页面
     */
    private void goToDiseaseNewActivity() {
//        Intent intent = new Intent(ToExamineActivity.this, DiseaseNewActivity.class);
//        intent.putExtra("bhcjztFlag", "1");
//        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // “读写SD卡”的权限结果处理
        if (permissionUtils != null && permissionsOfSDCardAndCamera != null && permissionUtils.dealRequestPermissionsResult(
                permissionsOfSDCardAndCamera, requestCode, permissions, grantResults)) {
            permissionsOfSDCardAndCamera = null;
            goToDiseaseNewActivity();
            return;
        }
    }

    /**
     * 对“读写SD卡”权限的检测
     *
     * @return true：需要请求权限；false：无需请求权限
     */
    public boolean checkPermissionsOfSDCardAndCamera() {
        if (permissionUtils == null) {
            permissionUtils = PermissionUtils
                    .newInstance(this);
        }
        if (permissionsOfSDCardAndCamera == null) {
            permissionsOfSDCardAndCamera = new ArrayList<String>();
            permissionsOfSDCardAndCamera
                    .add(Manifest.permission.CAMERA);
            permissionsOfSDCardAndCamera
                    .add(Manifest.permission.READ_EXTERNAL_STORAGE);
            permissionsOfSDCardAndCamera.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            permissionsOfSDCardAndCamera.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            permissionsOfSDCardAndCamera.add(Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS);
            permissionsOfSDCardAndCamera.add(Manifest.permission.CHANGE_WIFI_STATE);
        }
        return permissionUtils.requestPermissions(permissionsOfSDCardAndCamera);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
                if (dealFragment != null) {
                    listSelData = dealFragment.getSelectInfoData("paifa");
                    Log.e( "张成昆 ",listSelData.size()+"" );
                    if (listSelData != null) {
                        showPaiFaTiShiDialog();
                    }
                }
    }

    @Override
    public void onRequestError(String msg) {

    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void getData(repairRKbean videoVos2) {
        JSONArray jarr = JSONArray.parseArray(videoVos2.getGYDWXLK()); //JSON.parseArray(jsonStr);
        for (Iterator iterator = jarr.iterator(); iterator.hasNext(); ) {
            JSONObject job = (JSONObject)iterator.next();
            String GYDWMC = job.get("GYDWMC").toString();
            String GYDWID = job.get("GYDWID").toString();
            listDWResult.add(GYDWMC);
            listDWIDResult.add(GYDWID);
        }
        GZZLBH= videoVos2.getRWDH();
        MyApplication.GZZLBH =GZZLBH;
        initpopu();
    }

    @Override
    public void getData2(repairRKbean videoVos2) {
        Log.e("paifaDisease: ",videoVos2.getState());
        if (videoVos2.getState().equals("1")){
            MyApplication.app.customToast("派发成功");

            if ("0".equals(listtype) && dealFragment != null) {
                dealFragment.refreshDataMethod();
            }
        }else {
            MyApplication.app.customToast("派发失败");
        }
    }

    /**
     * 显示筛选遮罩view
     */
    public void showFilterZheZhaoView() {
        if (!vFilterZheZhao.isShown()) {
            vFilterZheZhao.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 隐藏筛选遮罩view
     */
    public void hideFilterZheZhaoView() {
        if (vFilterZheZhao.isShown()) {
            vFilterZheZhao.setVisibility(View.GONE);

        }
    }
    public void showZheZhaoView() {
//        if (!activityNewDiseaseZheZhaoLayout.isShown()) {
//            activityNewDiseaseZheZhaoLayout.setVisibility(View.VISIBLE);
//        }
    }

    /**
     * 隐藏遮罩view
     */
    public void hideZheZhaoView() {
//        if (activityNewDiseaseZheZhaoLayout.isShown()) {
//            activityNewDiseaseZheZhaoLayout.setVisibility(View.GONE);
//        }
    }
}
