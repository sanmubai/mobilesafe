package com.sanmubai.mobilesafe.activity;

import android.content.Intent;
import android.os.Bundle;

import com.sanmubai.mobilesafe.R;

public class Setting1Activity extends SettingBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting1);
    }

    @Override
    public void showNext() {
        startActivity(new Intent(this, Setting2Activity.class));
        finish();
        overridePendingTransition(R.anim.trans_next_in, R.anim.trans_next_out);
    }

    @Override
    public void showPrevious() {

    }
}
