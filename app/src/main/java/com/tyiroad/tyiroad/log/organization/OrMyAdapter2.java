package com.tyiroad.tyiroad.log.organization;

/**
 * Created by 张成昆 on 2019-7-15.
 */

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tyiroad.tyiroad.R;

import java.util.List;

public class OrMyAdapter2  extends BaseAdapter {
    private  int type;
    private List<ChildrenBean> Datas;
    private Context mContext;

    public OrMyAdapter2(List<ChildrenBean> datas, Context mContext,int type) {
        Datas = datas;
        this.mContext = mContext;
        this.type = type;
    }

    /**
     * 返回item的个数
     * @return
     */
    @Override
    public int getCount() {
        return Datas.size();
    }

    /**
     * 返回每一个item对象
     * @param i
     * @return
     */
    @Override
    public Object getItem(int i) {
        return Datas.get(i);
    }

    /**
     * 返回每一个item的id
     * @param i
     * @return
     */
    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * 暂时不做优化处理，后面会专门整理BaseAdapter的优化
     * @param i
     * @param view
     * @param viewGroup
     * @return
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(mContext).inflate(R.layout.list_item,viewGroup,false);
        TextView textView1 = (TextView) view.findViewById(R.id.menu_item_textview);
        RelativeLayout menu_item_ly = (RelativeLayout) view.findViewById(R.id.menu_item_ly);
        if (type==1000){

        }else{
            if (i==type){
                menu_item_ly.setBackgroundResource(R.color.qr);
                textView1.setTextColor(mContext.getResources().getColor(R.color.back));
            }else {
                menu_item_ly.setBackgroundResource(R.color.whiteme);
            }
        }
        textView1.setText(Datas.get(i).getGYDWMC());
//        此处需要返回view 不能是view中某一个
        return view;
    }
}