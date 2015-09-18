package com.example.bb.mobilesafe.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bb.mobilesafe.R;
import com.example.bb.mobilesafe.utils.StreamUtil;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SplashActivity extends Activity {

    private static final int CODE_URL_UPDATE = 1;
    private static final int CODE_ENTER_HOME = 2;
    private static final int CODE_URL_ERROR = 3;
    private static final int CODE_READ_ERROR = 4;
    private static final int CODE_JSON_ERROR = 5;

    private String vname;
    private String vdesc;
    private int vcode;
    private String vurl;
    private TextView tvProgress;

    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case CODE_URL_UPDATE:
                    showUpdateDialog();
                    break;
                case CODE_ENTER_HOME:
                    enterHome();
                    break;
                case CODE_URL_ERROR:
                    Toast.makeText(SplashActivity.this, "网络链接错误", Toast.LENGTH_SHORT).show();
                    break;
                case CODE_READ_ERROR:
                    Toast.makeText(SplashActivity.this, "网络数据读取错误", Toast.LENGTH_SHORT).show();
                    break;
                case CODE_JSON_ERROR:
                    Toast.makeText(SplashActivity.this, "网络数据解析错误", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    private void enterHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    protected void showUpdateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("最新版本：" + vname);
        builder.setMessage(vdesc);

        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                enterHome();
            }
        });


        builder.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                download();
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                enterHome();
            }
        });

        builder.show();
    }

    private void download() {
//        System.out.println("下载中");

        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){

            tvProgress.setVisibility(View.VISIBLE);

            String target= Environment.getExternalStorageDirectory()+"/mobileSafe.apk";
            HttpUtils utils=new HttpUtils();

            utils.download(vurl, target, new RequestCallBack<File>() {
                @Override
                public void onLoading(long total, long current,
                                      boolean isUploading) {
                    super.onLoading(total, current, isUploading);
                    tvProgress.setText("下载进度:" + current * 100 / total + "%");
                }

                @Override
                public void onSuccess(ResponseInfo<File> arg0) {
                    // 跳转到系统下载页面
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    intent.setDataAndType(Uri.fromFile(arg0.result),
                            "application/vnd.android.package-archive");
                    // startActivity(intent);
                    startActivityForResult(intent, 0);// 如果用户取消安装的话,
                    // 会返回结果,回调方法onActivityResult
                }

                @Override
                public void onFailure(HttpException arg0, String arg1) {
                    Toast.makeText(SplashActivity.this, "下载失败!",
                            Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TextView tvVersion = (TextView) findViewById(R.id.tv_version);

        tvProgress = (TextView) findViewById(R.id.tv_progress);

        tvVersion.setText("版本号：" + getVersionName());

        checkVersion();

    }

    private int getVersionCode() {
        PackageManager pm = getPackageManager();
        try {
            PackageInfo pi = pm.getPackageInfo(getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return 0;
    }

    private String getVersionName() {
        PackageManager pm = getPackageManager();

        try {
            final PackageInfo pi = pm.getPackageInfo(getPackageName(), 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return "";
    }

    private void checkVersion() {
        final long startTime = System.currentTimeMillis();
        new Thread() {
            @Override
            public void run() {
                String path = "http://www.sanmubai.com/version.json";
                Message msg = Message.obtain();

                try {
                    URL url = new URL(path);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5000);
                    conn.setReadTimeout(5000);

                    conn.connect();

                    if (conn.getResponseCode() == 200) {
                        InputStream is = conn.getInputStream();

                        String res = StreamUtil.readFromStream(is);

                        JSONObject jo = new JSONObject(res);

                        vname = jo.getString("versionname");
                        vcode = jo.getInt("versioncode");
                        vdesc = jo.getString("description");
                        vurl = jo.getString("downloadurl");

                        if (vcode > getVersionCode()) {
                            msg.what = CODE_URL_UPDATE;
//                            System.out.println("有升级");
                        } else {
                            msg.what = CODE_ENTER_HOME;
//                            System.out.println("不用升级");
                        }


//                        System.out.println("网络返回："+res);
                    }


                } catch (MalformedURLException e) {
                    msg.what = CODE_URL_ERROR;
                    e.printStackTrace();
                } catch (IOException e) {
                    msg.what = CODE_READ_ERROR;
                    e.printStackTrace();
                } catch (JSONException e) {
                    msg.what = CODE_JSON_ERROR;
                    e.printStackTrace();
                } finally {
                    final long currentTime = System.currentTimeMillis();
                    if (currentTime - startTime < 2000) {
                        try {
                            Thread.sleep(2000 - currentTime + startTime);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    mhandler.sendMessage(msg);
                }
            }
        }.start();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        enterHome();
    }
}
