package com.example.bb.mobilesafe.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bb.mobilesafe.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        gvHome = (GridView) findViewById(R.id.gv_home);

        System.out.println("ok");

        gvHome.setAdapter(new Myadapter());
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