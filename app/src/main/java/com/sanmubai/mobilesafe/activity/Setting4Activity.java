package com.sanmubai.mobilesafe.activity;

import android.content.Intent;
import android.os.Bundle;

import com.sanmubai.mobilesafe.R;

public class Setting4Activity extends SettingBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting4);
    }

    @Override
    public void showNext(){
        //设置完成，保存标志


        sp.edit().putBoolean("isSet",true).commit();

        startActivity(new Intent(this, LostFindActivity.class));
        finish();
    }

    @Override
    public void showPrevious(){
        startActivity(new Intent(this, Setting3Activity.class));
        finish();
        overridePendingTransition(R.anim.trans_previous_in, R.anim.trans_previous_out);
    }

}
