package com.tyiroad.tyiroad.log;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;


import com.tyiroad.tyiroad.R;
import com.tyiroad.tyiroad.log.addlog.AddLogItem;

import java.util.List;

/**
 * Created by lenovo on 2018/10/11.
 */

public class CheckBoxAdapter extends BaseAdapter {
    private Context context;
    private List<AddLogItem> list;
    private LayoutInflater layoutInflater;
    private TextView tv;
    private CheckBox cb;
    public CheckBoxAdapter(Context context, List<AddLogItem> list) {
        this.context = context;
        this.list = list;//list中checkbox状态为false
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null != list?list.get(position):null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewCache viewCache = new ViewCache();
        AddLogItem AddLogItem=(AddLogItem)getItem(position);
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.item_addxz, null);
            tv = (TextView) convertView.findViewById(R.id.neirongte);
            cb = (CheckBox) convertView.findViewById(R.id.cb);
            viewCache.tv = tv;
            viewCache.cb = cb;
            convertView.setTag(viewCache);
            final ViewCache finalViewHolder = viewCache;
            viewCache.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    AddLogItem info = (AddLogItem) finalViewHolder.cb.getTag();
                    info.setIsChcked(compoundButton.isChecked());
                }
            });
            convertView.setTag(viewCache);
            viewCache.cb.setTag(AddLogItem);
        }else {
            viewCache = (ViewCache) convertView.getTag();
            viewCache.cb.setTag(AddLogItem);
        }
        viewCache.tv.setText(AddLogItem.getLDMC());
        viewCache.cb.setChecked(AddLogItem.getIsChcked());
        return convertView;
    }
    public class ViewCache{
        TextView tv;
        CheckBox cb;
    }
}
