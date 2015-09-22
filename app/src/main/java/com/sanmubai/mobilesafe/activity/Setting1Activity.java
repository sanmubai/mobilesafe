package com.sanmubai.mobilesafe.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sanmubai.mobilesafe.R;

public class Setting1Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting1);
    }

    public void next(View view){
//        Log.d("test","xia yi bu");
        startActivity(new Intent(this,Setting2Activity.class));
        finish();
        overridePendingTransition(R.anim.trans_next_in,R.anim.trans_next_out);
    }
}
