package com.tyiroad.tyiroad.other;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tyiroad.tyiroad.Bean.OtherListbean;
import com.tyiroad.tyiroad.R;

import java.util.List;

/**
 * Created by 张成昆 on 2019-6-10.
 */

public class OtherListAdapter  extends BaseAdapter {
    private Context mContext;
    private List<OtherListbean.LISTDATABean> mDatas;
    private LayoutInflater mInflater;
    public boolean flage = false;

    public OtherListAdapter(Context mContext, List<OtherListbean.LISTDATABean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(this.mContext);
    }

    @Override
    public int getCount() {
        return mDatas.size();

    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) { //下拉项布局
            convertView = mInflater.inflate(R.layout.item_other_list, null);
            holder = new ViewHolder();
            //获取布局文件
            holder.cb = (CheckBox) convertView.findViewById(R.id.cb);
            holder.otherzt = (TextView) convertView.findViewById(R.id.other_zt);
            holder.othertime = (TextView) convertView.findViewById(R.id.other_time);
            holder.otherlx = (TextView) convertView.findViewById(R.id.other_lx);
            holder.otherdw = (TextView) convertView.findViewById(R.id.other_dw);
            holder.lay = (LinearLayout) convertView.findViewById(R.id.lay);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final OtherListbean.LISTDATABean dataBean = mDatas.get(position);
        if (dataBean != null) {
            if (dataBean.getSTATE().equals("1")) {
                holder.otherzt.setText("新申请");
                holder.otherzt.setTextColor( mContext.getResources().getColor(R.color.disease_flag_text_bg_dsc));
            }else if (dataBean.getSTATE().equals("2")) {
                holder.otherzt.setText("已上报");
                holder.otherzt.setTextColor( mContext.getResources().getColor(R.color.disease_flag_text_bg_ypf_yys_ywx));
            }else if (dataBean.getSTATE().equals("3")){
                holder.otherzt.setText("审核通过");
                holder.otherzt.setTextColor( mContext.getResources().getColor(R.color.pf_xin_tian_jia_text_color));
            }else if (dataBean.getSTATE().equals("4")){
                holder.otherzt.setText("审核不通过");
                holder.otherzt.setTextColor( mContext.getResources().getColor(R.color.pf_xin_bing_hai_text_color));
            }
            holder.othertime.setText(replaceIM(dataBean.getCREATETIME()));
            holder.otherlx.setText(dataBean.getLXMC());
            holder.otherdw.setText(dataBean.getGYDWMC());
            //根据isSelected来设置CheckBox的显示显示状况
            if (flage) {
                holder.cb.setVisibility(View.VISIBLE);
            } else {
                holder.cb.setVisibility(View.GONE);
            }
            holder.cb.setChecked(dataBean.isCheck);
            holder.cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dataBean.isCheck) {
                        dataBean.setCheck(false);
                    } else {
                        dataBean.setCheck(true);
                    }
                }
            });
            if (position % 2 == 0) {
                holder.lay.setBackgroundColor(mContext.getResources().getColor(R.color.transparent));
            } else {
                holder.lay.setBackgroundColor(mContext.getResources().getColor(R.color.direction_btn_bg_color));
            }
        }
        return convertView;
    }

    class ViewHolder {
        public CheckBox cb;
        public TextView otherzt;
        public TextView othertime;
        public TextView otherlx;
        public TextView otherdw;
        public LinearLayout lay;
    }
    public static String replaceIM(String str) {
        if (str == null) {
            return "";
        }else {
            if (str.contains("T")){
                return str.replace("T"," ");
            }else {
                return str;
            }
        }

    }
}
