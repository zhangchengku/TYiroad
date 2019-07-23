package com.tyiroad.tyiroad.repair.lookrepairrely;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tyiroad.tyiroad.Bean.ItemBeanInfo;
import com.tyiroad.tyiroad.R;

import java.util.ArrayList;

/**
 * Created by 张成昆 on 2019-3-28.
 */

public class ReapirRelyAdapter extends BaseAdapter {


    private ArrayList<ItemBeanInfo> listda;
    Context context;
    LayoutInflater inflater;
    public ReapirRelyAdapter(Context context, ArrayList<ItemBeanInfo> listda) {
        this.inflater=LayoutInflater.from(context);
        this.context=context;
        this.listda=listda;
    }

    @Override
    public int getCount() {
        return listda.size();
    }

    @Override
    public Object getItem(int position) {
        return listda.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView){
            convertView = inflater.inflate(R.layout.item_look_rely,null);
            holder =new ViewHolder(convertView,position);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.nr.setText(listda.get(position).getXmmc());
        holder.editText.setText(listda.get(position).getJsgs());
        holder.dw.setText(listda.get(position).getJldw());
        return convertView;
    }
    class ViewHolder{
        TextView nr;
        TextView editText;
        TextView dw;
        public ViewHolder(View view,int pisition){
            nr = (TextView) view.findViewById(R.id.nr);
            editText= (TextView) view.findViewById(R.id.ed);
            dw= (TextView) view.findViewById(R.id.dw);

        }
    }



}