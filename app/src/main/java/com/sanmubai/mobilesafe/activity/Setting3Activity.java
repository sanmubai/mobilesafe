package com.sanmubai.mobilesafe.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sanmubai.mobilesafe.R;

public class Setting3Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting3);
    }
    public void next(View view){
        startActivity(new Intent(this,Setting4Activity.class));
        finish();
    }

    public void previous(View view){
        startActivity(new Intent(this, Setting2Activity.class));
        finish();
    }
}
