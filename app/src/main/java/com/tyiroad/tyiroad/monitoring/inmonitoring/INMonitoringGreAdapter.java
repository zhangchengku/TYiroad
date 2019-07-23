package com.tyiroad.tyiroad.monitoring.inmonitoring;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tooklkit.Tooklkit;
import com.tyiroad.tyiroad.MyApplication;
import com.tyiroad.tyiroad.R;
import com.tyiroad.tyiroad.handle.inwaihandle.ShowImgActivity;

import java.util.ArrayList;

import android.widget.RelativeLayout.LayoutParams;

/**
 * Created by 张成昆 on 2019-7-1.
 */

public class INMonitoringGreAdapter extends BaseAdapter {
    private Context context;//上下文对象
    private ArrayList<String> listPictureUrl;// 网络图片地址集合

    public INMonitoringGreAdapter(Context context,
                                  ArrayList<String> listPictureUrl) {
        this.context = context;
        this.listPictureUrl = listPictureUrl;
    }

    @Override
    public int getCount() {
        if (listPictureUrl != null && listPictureUrl.size() > 0) {
            return listPictureUrl.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        if (listPictureUrl != null && listPictureUrl.size() > 0) {
            return listPictureUrl.get(position);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.send_topic_picture_adapter_item, null);
            vh.pictureImg = (ImageView) convertView
                    .findViewById(R.id.send_topic_picture_adapter_item_img);
            vh.pictureCloseImg = (ImageView) convertView
                    .findViewById(R.id.send_topic_picture_adapter_item_close_img);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        final int fpos = position;
        vh.pictureCloseImg.setVisibility(View.GONE);
        int width = (Tooklkit.getWidth((Activity) context) - Tooklkit.dip2px(context, 10) * 3) / 4;
        LayoutParams params = new LayoutParams(width, width);
        vh.pictureImg.setLayoutParams(params);
        vh.pictureCloseImg.setVisibility(View.GONE);
        String imgUrl = listPictureUrl.get(position);
        Glide.with(context)
                .asBitmap()
                .apply(MyApplication.app.options)
                .load(imgUrl)
                .into(vh.pictureImg);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowImgActivity.class);
                intent.putExtra("img", listPictureUrl);
                intent.putExtra("position", fpos);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    class ViewHolder {
        ImageView pictureImg;//选择的图片
        ImageView pictureCloseImg;//关闭按钮
    }

    @Override
    public int getViewTypeCount() {
        if (listPictureUrl != null) {
            if (listPictureUrl.size() > 0) {
                return listPictureUrl.size();
            } else {
                return 1;
            }
        }
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}

