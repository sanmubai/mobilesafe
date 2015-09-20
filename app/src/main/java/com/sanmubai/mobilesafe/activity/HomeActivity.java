package com.sanmubai.mobilesafe.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sanmubai.mobilesafe.R;
import com.sanmubai.mobilesafe.utils.MD5Utils;

public class HomeActivity extends Activity {

    private GridView gvHome;
    private String[] itemTitle = new String[]{"手机防盗", "通讯卫士", "软件管理", "进程管理",
            "流量统计", "手机杀毒", "缓存清理", "高级工具", "设置中心"};
    private int[] itemImg = new int[]{
            R.mipmap.home_safe,
            R.mipmap.home_callmsgsafe, R.mipmap.home_apps,
            R.mipmap.home_taskmanager, R.mipmap.home_netmanager,
            R.mipmap.home_trojan, R.mipmap.home_sysoptimize,
            R.mipmap.home_tools, R.mipmap.home_settings
    };
    private SharedPreferences sp;
    private String passSaved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        gvHome = (GridView) findViewById(R.id.gv_home);

//        System.out.println("ok");

        gvHome.setAdapter(new Myadapter());

        gvHome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        //手机防盗
                        showPassDialog();
                        break;
                    case 8:
                        //设置中心
                        startActivity(new Intent(HomeActivity.this,SettingActivity.class));
                        break;
                }
            }
        });
    }

    private void showPassDialog() {
        sp=getSharedPreferences("settingConfig", MODE_PRIVATE);
        passSaved=sp.getString("passwd",null);
        if(TextUtils.isEmpty(passSaved)){
            showSetPassDialog();
        }else{
            showInputPassDialog();
        }
    }

    private void showInputPassDialog() {
        AlertDialog.Builder ab=new AlertDialog.Builder(this);
        final AlertDialog dialog = ab.create();
        View view = View.inflate(this, R.layout.dialog_input_pass, null);
        dialog.setView(view, 0, 0, 0, 0);
        final EditText etpass = (EditText)view.findViewById(R.id.input_pass);

        Button btnOk = (Button) view.findViewById(R.id.input_pass_ok);
        Button btnCancel = (Button) view.findViewById(R.id.input_pass_cancel);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passwd=etpass.getText().toString();

                if(!TextUtils.isEmpty(passwd)){
                    if (MD5Utils.encode(passwd).equals(passSaved)){
                        //保存密码，跳转进设置向导
                        dialog.dismiss();
                        startActivity(new Intent(HomeActivity.this,LostFindActivity.class));

                    }else{
                        Toast.makeText(HomeActivity.this,"密码错误！",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(HomeActivity.this,"输入框内容不能为空！",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void showSetPassDialog() {
        AlertDialog.Builder ab=new AlertDialog.Builder(this);
        final AlertDialog dialog = ab.create();
        View view = View.inflate(this, R.layout.dialog_set_pass, null);
        dialog.setView(view, 0, 0, 0, 0);
        final EditText etpass = (EditText)view.findViewById(R.id.set_pass);
        final EditText etpassConfirm = (EditText)view.findViewById(R.id.set_confirmpass);

        Button btnOk = (Button) view.findViewById(R.id.set_pass_ok);
        Button btnCancel = (Button) view.findViewById(R.id.set_pass_cancel);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passwd=etpass.getText().toString();
                String passwdConfirm=etpassConfirm.getText().toString();

                if(!TextUtils.isEmpty(passwd) && !TextUtils.isEmpty(passwdConfirm)){
                    if (passwd.equals(passwdConfirm)){
                        //保存密码，跳转进设置向导
                        sp.edit().putString("passwd", MD5Utils.encode(passwd)).commit();
                        dialog.dismiss();
                        startActivity(new Intent(HomeActivity.this,LostFindActivity.class));

                    }else{
                        Toast.makeText(HomeActivity.this,"两次密码不一致",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(HomeActivity.this,"输入框内容不能为空！",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private class Myadapter extends BaseAdapter {

        @Override
        public int getCount() {
            return itemTitle.length;
        }

        @Override
        public Object getItem(int position) {
            return itemTitle[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=View.inflate(HomeActivity.this, R.layout.home_list_item, null);
            ImageView ivHomeItem = (ImageView) view.findViewById(R.id.iv_homeItem);
            TextView tvHomeItem= (TextView) view.findViewById(R.id.tv_homeItem);

            tvHomeItem.setText(itemTitle[position]);
            ivHomeItem.setImageResource(itemImg[position]);
            return view;
        }
    }
}