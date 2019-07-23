package com.tyiroad.tyiroad.code;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.logic.ImgFileListActivity;
import com.tooklkit.Tooklkit;
import com.tyiroad.tyiroad.MyApplication;
import com.tyiroad.tyiroad.R;
import com.tyiroad.tyiroad.disease.MinePopupWindow;
import com.tyiroad.tyiroad.handle.LoadDataDialog;
import com.tyiroad.tyiroad.mvp.MVPBaseActivity;
import com.tyiroad.tyiroad.utils.AppManager;
import com.tyiroad.tyiroad.utils.ImageUtil;
import com.tyiroad.tyiroad.utils.ToastUtils;
import com.tyiroad.tyiroad.utils.Utils;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;

import butterknife.Bind;
import butterknife.ButterKnife;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class CodeActivity extends MVPBaseActivity<CodeContract.View, CodePresenter> implements CodeContract.View {
    @Bind(R.id.pro_schedule)
    ContentLoadingProgressBar mProSchedule;
    @Bind(R.id.webview)
    MyWebView mWebview;
    @Bind(R.id.relativeLayout)
    RelativeLayout relativeLayout;
    private int REQUEST_CODE = 6;
    private MinePopupWindow minePopupWindow;
    private final int CHOOSE_PICTURE_CODE = 11;
    private final int CAMERA_CODE = 22;
    private String cameraPath;
    private int childViewPosition;
    private String currentDate;
    private LoadDataDialog loadDataDialog;
    private String strBlob;

    private String BridgeName = "";

    public void setCameraPath(String cameraPath) {
        this.cameraPath = cameraPath;
    }

    public void setChildViewPosition(int childViewPosition) {
        this.childViewPosition = childViewPosition;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setStatusBarColor(this, R.color.theme_color);
        setContentView(R.layout.act_code);
        ButterKnife.bind(this);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        currentDate = simpleDateFormat.format(calendar.getTime());
//启用数据库
        mWebview.getSettings().setDatabaseEnabled(true);

//设置定位的数据库路径
        String dir = this.getApplicationContext().getDir("Code_Activity", Context.MODE_PRIVATE).getPath();
        mWebview.getSettings().setGeolocationDatabasePath(dir);

//启用地理定位
        mWebview.getSettings().setGeolocationEnabled(true);

//开启DomStorage缓存
        mWebview.getSettings().setDomStorageEnabled(true);
        //帮助WebView处理各种通知、请求事件
        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mProSchedule.setVisibility(View.VISIBLE);
                //开始
                /**
                 * 网页重定向时会执行多次
                 */
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
                //网页加载成功
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //网址处理
                /**
                 * 可对指定网址进行拦截
                 */
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                //网页加载失败
                /**
                 * 此回调中可进行自定义错误页面，
                 * 遇到错误时示例代码:view.loadUrl("file://android_asset/error.html");
                 */
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mProSchedule.setVisibility(View.GONE);
                //网页加载完成
            }
        });

        //辅助WebView处理Javascript的对话框，网站图标，网站title，加载进度等
        mWebview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                //网页加载进度
                if (newProgress < 100) {
                    mProSchedule.setProgress(newProgress);
                }
            }
        });
        mWebview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);

            }

            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
                super.onGeolocationPermissionsShowPrompt(origin, callback);

            }
        });
        mWebview.loadUrl(MyApplication.BASEURLCODE + "gydwid=" + MyApplication.spUtils.getString("dqgydwid") + "|" + MyApplication.spUtils.getString("dqgydwmc") + "|" + MyApplication.spUtils.getString("dlr"));
        mWebview.addJavascriptInterface(new JsInterface(), "android");
    }

    private long firstTime = 0;

    public class JsInterface {
        @JavascriptInterface
        public void getPhotoBase() {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 2000) {
                Intent intent = new Intent(CodeActivity.this, SweepActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                firstTime = secondTime;
            } else {
            }
        }

        @JavascriptInterface
        public void getPhoto(String bridgeName) {
            Log.e("getPhotoBase: ", "进来了" + bridgeName);
            BridgeName = bridgeName + "\n";
            if (minePopupWindow == null) {
                minePopupWindow = new MinePopupWindow(CodeActivity.this, itemOnClick);
            }
            minePopupWindow.showAtLocation(relativeLayout, Gravity.BOTTOM, 0, 0);
        }

        @JavascriptInterface
        public void closeWebView() {//closeWebView
            Log.e("getPhotoBase: ", "进来了");
            finish();
        }
    }

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
            Uri imageUri = FileProvider.getUriForFile(this, MyApplication.TPDZ, file);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);//将拍取的照片保存到指定URI
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        }
        setCameraPath(path);
        setChildViewPosition(childViewPosition);
        startActivityForResult(intent, CAMERA_CODE);
    }

    public void getPicFromPhoto() {
        Intent intent = new Intent(this, ImgFileListActivity.class);
        intent.putExtra("position", childViewPosition);
        intent.putExtra("isLimitedNumber", true);
        intent.putExtra("maxsize", 1);
        startActivityForResult(intent, CHOOSE_PICTURE_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    if (!Utils.isNull(result)) {
                        mWebview.loadUrl(MyApplication.BASEURLCODE + "result=" + result);
                        Log.e("张成昆", MyApplication.BASEURLCODE + "result=" + result);
                    }
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(CodeActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        } else if (requestCode == CHOOSE_PICTURE_CODE) {
            if (data != null) {
                ArrayList<String> listSelectPic = data.getStringArrayListExtra("filelist");
                int position = data.getIntExtra("position", -1);
                if (position != -1 && listSelectPic != null && listSelectPic.size() > 0) {
                    String pathStr = listSelectPic.get(0);
                    final String savePathStr = pathStr.substring(0, pathStr.lastIndexOf("/"));
                    Luban.with(this)
                            .load(listSelectPic)                                  // 传人要压缩的图片列表
                            .ignoreBy(100)                                  // 忽略不压缩图片的大小
                            .setTargetDir(savePathStr)                     // 设置压缩后文件存储位置
                            .setCompressListener(new OnCompressListener() { //设置回调
                                @Override
                                public void onStart() {
                                    // TODO 压缩开始前调用，可以在方法内启动 loading UI
                                }

                                @Override
                                public void onSuccess(File file) {
                                    // TODO 压缩成功后调用，返回压缩后的图片文件
                                    String imgPath = file.getPath();
                                    String strBlob = Utils.bmpToBase64String(imgPath);
                                    mWebview.loadUrl("javascript:callH5(" + "'" + strBlob + "'" + ")");
                                }

                                @Override
                                public void onError(Throwable e) {
                                    // TODO 当压缩过程出现问题时调用
                                    Log.i("图片压缩失败", "");
                                }
                            }).launch();    //启动压缩
                }
            }
        } else if (requestCode == CAMERA_CODE) {
            if (resultCode == -1) {
                if (!Utils.isNull(cameraPath) && childViewPosition != -1) {
                    Utils.dealBitmapRotate(cameraPath);
                    Luban.with(this)
                            .load(cameraPath)                                  // 传人要压缩的图片列表
                            .ignoreBy(100)                                  // 忽略不压缩图片的大小
                            .setTargetDir(cameraPath.substring(0, cameraPath.lastIndexOf("/")))                        // 设置压缩后文件存储位置
                            .setCompressListener(new OnCompressListener() { //设置回调
                                @Override
                                public void onStart() {
                                    // TODO 压缩开始前调用，可以在方法内启动 loading UI
                                }

                                @Override
                                public void onSuccess(File file) {
                                    // TODO 压缩成功后调用，返回压缩后的图片文件
                                    String str = "正在加载...";
                                    showLoadingDialogMethod(str);
                                    String imgPath = file.getPath();
                                    Bitmap bitmap1 = ImageUtil.drawTextToCenter(CodeActivity.this, BitmapFactory.decodeFile(imgPath), BridgeName + currentDate, 50, Color.YELLOW);
                                    String result = null;
                                    ByteArrayOutputStream baos = null;
                                    try {
                                        if (bitmap1 != null) {
                                            baos = new ByteArrayOutputStream();
                                            bitmap1.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                                            baos.flush();
                                            baos.close();
                                            byte[] bitmapBytes = baos.toByteArray();
                                            result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
                                            mWebview.loadUrl("javascript:callH5(" + "'" + result + "'" + ")");
                                        }
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    } finally {
                                        try {
                                            if (baos != null) {
                                                baos.flush();
                                                baos.close();
                                            }
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    if (loadDataDialog != null && loadDataDialog.isShowing()) {
                                        loadDataDialog.cancel();
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    // TODO 当压缩过程出现问题时调用
                                    Log.i("图片压缩失败", "");
                                }
                            }).launch();    //启动压缩
                }
            }
        }
    }

    private void showLoadingDialogMethod(String str) {
        if (loadDataDialog == null) {
            loadDataDialog = new LoadDataDialog(this);
        }
        loadDataDialog.setTitleStr(str);
        loadDataDialog.show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebview.canGoBack()) {
            mWebview.goBack();//返回上个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
