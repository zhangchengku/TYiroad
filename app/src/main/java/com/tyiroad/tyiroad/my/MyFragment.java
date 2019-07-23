package com.tyiroad.tyiroad.my;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tyiroad.tyiroad.MyApplication;
import com.tyiroad.tyiroad.R;
import com.tyiroad.tyiroad.land.LandActivity;
import com.tyiroad.tyiroad.mvp.MVPBaseFragment;
import com.tyiroad.tyiroad.utils.Dialog.CommBtnListener;
import com.tyiroad.tyiroad.utils.Dialog.CommNotificationDialog;
import com.tyiroad.tyiroad.view.CircleImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MyFragment extends MVPBaseFragment<MyContract.View, MyPresenter> implements MyContract.View {
    @Bind(R.id.my_icon)
    CircleImageView myIcon;
    @Bind(R.id.username_te)
    TextView usernameTe;
    @Bind(R.id.zzjg_te)
    TextView zzjgTe;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.log_out_btn)
    Button logOutBtn;
    private View view;
    private ImageView myicon;
    private CommNotificationDialog logoutWarmDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fra_me, null);
        ButterKnife.bind(this, view);
        myicon = (ImageView) view.findViewById(R.id.my_icon);
        lintener();
        usernameTe.setText(MyApplication.spUtils.getString("dlr"));
        zzjgTe.setText(MyApplication.spUtils.getString("dqgydwmc"));
        return view;
    }

    private void lintener() {
        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (logoutWarmDialog == null) {
                    String title = "退出登录会清空当前数据";
                    String okStr = "确定";
                    String cancelStr = "取消";
                    logoutWarmDialog = new CommNotificationDialog(getActivity(), title, okStr, cancelStr, new CommBtnListener() {
                        @Override
                        public void CommOkBtnClick() {
                            dele();

                        }

                        @Override
                        public void CommCancelBtnClick() {

                        }
                    });
                }
                logoutWarmDialog.show();
            }
        });
    }

    private void dele() {
        MyApplication.app.curingDao.deleteAllBaseTableData();
        MyApplication.spUtils.clear();
        startActivity(new Intent(getActivity(), LandActivity.class));
        getActivity().finish();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
