package com.tyiroad.tyiroad.monitoring.dbmonitoring;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.logic.ImgFileListActivity;
import com.tooklkit.Tooklkit;
import com.tyiroad.tyiroad.MyApplication;
import com.tyiroad.tyiroad.R;
import com.tyiroad.tyiroad.disease.MinePopupWindow;
import com.tyiroad.tyiroad.handle.inwaihandle.ShowImgActivity;
import com.tyiroad.tyiroad.utils.Utils;

import android.widget.RelativeLayout.LayoutParams;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by 张成昆 on 2019-7-1.
 */

public class DBMonitoringGreAdapter   extends BaseAdapter {

    private Context context;//上下文对象
    private ArrayList<Drawable> listPicture;// 图片集合
    private ArrayList<String> listImgUrl = new ArrayList<String>();// 选择的图片地址集合

    private final int CHOOSE_PICTURE_CODE = 1;
    private final int CAMERA_CODE = 2;
    private int childViewPosition;
    private MinePopupWindow minePopupWindow;


    public ArrayList<String> getListImgUrl() {
        return listImgUrl;
    }

    public ArrayList<Drawable> getListPicture() {
        return listPicture;
    }

    public DBMonitoringGreAdapter(Context context,
                                  ArrayList<Drawable> listPicture, ArrayList<String> listImgUrl, int childViewPosition) {
        this.context = context;
        this.listPicture = listPicture;
        this.listImgUrl = listImgUrl;
        this.childViewPosition = childViewPosition;
      
    }

  

    @Override
    public int getCount() {
        if (listPicture != null && listPicture.size() > 0) {
            return listPicture.size();
        }else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        if (listPicture != null && listPicture.size() > 0) {
            return listPicture.get(position);
        }else {
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
            vh.pictureImg.setImageDrawable(listPicture.get(position));
            if (position == listPicture.size() - 1) {
                vh.pictureCloseImg.setVisibility(View.GONE);
                if (context instanceof DBMonitoringActivity) {
                    vh.pictureImg.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (minePopupWindow == null) {
                                minePopupWindow = new MinePopupWindow((DBMonitoringActivity) context, itemOnClick);
                            }
                            minePopupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
                            Utils.hideInputWindow((DBMonitoringActivity)context);
                        }
                    });
                }
            } else {
                vh.pictureImg.setOnClickListener(null);
                vh.pictureCloseImg.setVisibility(View.VISIBLE);
                // 图片右上方的删除图片的按钮
                vh.pictureCloseImg.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        listImgUrl.remove(fpos);
                        listPicture.remove(fpos);
                        v.setOnClickListener(null);
                        notifyDataSetChanged();
                    }
                });
                vh.pictureImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, ShowImgActivity.class);
                        intent.putExtra("img2", listImgUrl);
                        intent.putExtra("position", fpos);
                        context.startActivity(intent);
                    }
                });
            }
      
        return convertView;
    }

    class ViewHolder {
        ImageView pictureImg;//选择的图片
        ImageView pictureCloseImg;//关闭按钮

    }

    @Override
    public int getViewTypeCount() {
        if (listPicture != null) {
            if (listPicture.size() > 0) {
                return listPicture.size();
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

    /**
     * 自定义pop监听
     */
    private View.OnClickListener itemOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.mine_camera_btn:
                    if (minePopupWindow.isShowing()) {
                        minePopupWindow.dismiss();
                    }
                    getPicFromCamera();
                    break;
                case R.id.mine_photo_btn:
                    if (minePopupWindow.isShowing()) {
                        minePopupWindow.dismiss();
                    }
                    getPicFromPhoto();
                    break;
                case R.id.mine_cancel_btn:
                    if (minePopupWindow.isShowing()) {
                        minePopupWindow.dismiss();
                    }
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 从相册获取
     */
    public void getPicFromPhoto() {
        Intent intent = new Intent(context, ImgFileListActivity.class);
        intent.putExtra("position", childViewPosition);
        intent.putExtra("isLimitedNumber",false);
        ((DBMonitoringActivity) context).startActivityForResult(intent, CHOOSE_PICTURE_CODE);
    }

    /**
     * 照相获取
     */
    public void getPicFromCamera() {
        String path = MyApplication.app.APP_FILE_SAVE_PATH
                + System.currentTimeMillis() + ".jpg";
        File file = new File(path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);//设置Action为拍照
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);//添加这一句表示对目标应用临时授权该Uri所代表的文件
            // 下面这句指定调用相机拍照后的照片存储的路径
            Uri imageUri = FileProvider.getUriForFile(context, MyApplication.TPDZ, file);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);//将拍取的照片保存到指定URI
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        }
        ((DBMonitoringActivity) context).setCameraPath(path);
        ((DBMonitoringActivity) context).setChildViewPosition(childViewPosition);
        ((DBMonitoringActivity) context).startActivityForResult(intent, CAMERA_CODE);
    }

}