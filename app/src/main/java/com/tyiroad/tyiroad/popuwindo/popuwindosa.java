package com.tyiroad.tyiroad.popuwindo;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.tooklkit.Tooklkit;
import com.tyiroad.tyiroad.R;
import com.tyiroad.tyiroad.db.dbhelper.dbInfo.RoadSectionInfo;
import com.tyiroad.tyiroad.log.CheckBoxAdapter;
import com.tyiroad.tyiroad.log.addlog.AddLogItem;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 10270 on 2018/10/25.
 */

public class popuwindosa extends PopupWindow {
    private Activity activity;
    private List<AddLogItem> listDataStr = new ArrayList<>();
    private popuwindowsslistener listener;
    private String titleStr;
    private ListView listView;

    private TextView headers;
    private TextView queding;
    private CheckBox cb;
    private List<AddLogItem> listDataStr2 = new ArrayList<>();
    private CheckBoxAdapter cbAdapter;

    public popuwindosa(Activity activity, String titleStr, List<AddLogItem> listDataStr, popuwindowsslistener listener){

        this.activity = activity;
        this.listDataStr=listDataStr;
        this.listener=listener;
        this.titleStr=titleStr;
        View contentView = LayoutInflater.from(activity).inflate(
                R.layout.lxlists, null);
        setContentView(contentView);
        setWidth(Tooklkit.getWidth(activity));
        setHeight(Tooklkit.getHeight(activity));
        setFocusable(true);
        setTouchable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new ColorDrawable(0));
        initViews(contentView);
    }

    private void initViews(View contentView){
        headers = (TextView)contentView.findViewById(R.id.header);
        headers.setText(titleStr);
        queding = (TextView)contentView.findViewById(R.id.queding);
        queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listDataStr2.clear();
                for (int i = 0; i < listDataStr.size(); i++) {
                    if (listDataStr.get(i).getIsChcked()){
                        listDataStr2.add(listDataStr.get(i));
                    }
                }
                if (listDataStr2.size()>0){
                    listener.selectPosition(listDataStr2);
                }
                close();
            }
        });
        listView=(ListView)contentView.findViewById(R.id.list);
        cbAdapter = new CheckBoxAdapter(activity, listDataStr);
        listView.setAdapter(cbAdapter);
    }

    /**
     * 显示筛选窗口
     */
    public void show(View anchor) {
        if (isShowing()) {
            return;
        }
        if (anchor != null) {
            showAtLocation(anchor, Gravity.BOTTOM, 0, 0);
        }
    }

    /**
     * 关闭筛选窗口
     */
    public void close() {
        if (isShowing()) {
            dismiss();
        }
    }

    public void notifityData(){
        cbAdapter.notifyDataSetChanged();
    }
}
