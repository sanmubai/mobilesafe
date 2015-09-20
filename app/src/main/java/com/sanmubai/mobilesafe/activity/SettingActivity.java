package com.sanmubai.mobilesafe.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.sanmubai.mobilesafe.R;
import com.sanmubai.mobilesafe.view.SettingItemView;

public class SettingActivity extends Activity {

    private SharedPreferences sp;
    private SettingItemView vUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        vUpdate=(SettingItemView) findViewById(R.id.setting_switch);
        sp = getSharedPreferences("settingConfig",MODE_PRIVATE);
        boolean autoUpdate=sp.getBoolean("autoUpdate",true);

        vUpdate.setChecked(autoUpdate);

        vUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean status=vUpdate.isChecked();
                if(status){
                    vUpdate.setChecked(false);
                    sp.edit().putBoolean("autoUpdate",false).commit();
                }else{
                    vUpdate.setChecked(true);
                    sp.edit().putBoolean("autoUpdate",true).commit();
                }
            }
        });
    }



}
