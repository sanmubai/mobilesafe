package com.sanmubai.mobilesafe.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.sanmubai.mobilesafe.R;

public class LostFindActivity extends Activity {

    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //判断是否进入过设置向导，没进过，进入设置向导。否则进入设置后的页面
        sp=getSharedPreferences("settingConfig", MODE_PRIVATE);

        boolean isSet = sp.getBoolean("isSet", false);
        if(isSet){

            setContentView(R.layout.activity_lost_find);
        }else {
            startActivity(new Intent(this,Setting1Activity.class));
            finish();
        }
    }

}
