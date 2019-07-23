package com.tyiroad.tyiroad.log.organization;

/**
 * Created by 张成昆 on 2019-7-15.
 */

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.tyiroad.tyiroad.R;

import java.util.List;
public class MOryAdapter extends BaseAdapter {
    private  int type;
    private List<ChildrenBean> Datas;
    private Context mContext;

    private ViewHolder viewHolder;

    public MOryAdapter(List<ChildrenBean> datas, Context mContext,int type) {
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


    @Override

    public View getView(int position, View convertView, ViewGroup parent) {

        //如果view未被实例化过，缓存池中没有对应的缓存
        if (convertView == null) {
            viewHolder = new ViewHolder();
            // 由于我们只需要将XML转化为View，并不涉及到具体的布局，所以第二个参数通常设置为null
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, null);

            //对viewHolder的属性进行赋值

            viewHolder.title = (TextView) convertView.findViewById(R.id.menu_item_textview);
            viewHolder.teco = (TextView) convertView.findViewById(R.id.teco);
            viewHolder.menu_item_ly = (RelativeLayout) convertView.findViewById(R.id.menu_item_ly);
            //通过setTag将convertView与viewHolder关联
            convertView.setTag(viewHolder);
        }else{//如果缓存池中有对应的view缓存，则直接通过getTag取出viewHolder
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (position==type){
            viewHolder.menu_item_ly.setBackgroundResource(R.color.whiteme);
            viewHolder.teco.setVisibility(View.VISIBLE);
        }else {
            viewHolder.menu_item_ly.setBackgroundResource(R.color.bg);
        }

        viewHolder.title.setText(Datas.get(position).getGYDWMC());


        return convertView;
    }
    // ViewHolder用于缓存控件，三个属性分别对应item布局文件的三个控件
    class ViewHolder{
        public TextView teco;
        public TextView title;
        public RelativeLayout menu_item_ly;
    }


}
