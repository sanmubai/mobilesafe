package com.sanmubai.mobilesafe.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sanmubai.mobilesafe.R;

public class Setting2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting2);
    }

    public void next(View view){
        System.out.println("222");
        startActivity(new Intent(this, Setting3Activity.class));
        finish();
        overridePendingTransition(R.anim.trans_next_in, R.anim.trans_next_out);
    }

    public void previous(View view){
        startActivity(new Intent(this,Setting1Activity.class));
        finish();
        overridePendingTransition(R.anim.trans_previous_in, R.anim.trans_previous_out);
    }


}
