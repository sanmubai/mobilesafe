package com.sanmubai.mobilesafe.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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

            TextView viewById = (TextView) findViewById(R.id.tv_lost_find_reenter);
            viewById.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(LostFindActivity.this, Setting1Activity.class));
                    finish();
                }
            });
        }else {
            startActivity(new Intent(this,Setting1Activity.class));
            finish();
        }
    }

}
