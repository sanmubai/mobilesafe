package com.sanmubai.mobilesafe.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sanmubai.mobilesafe.R;

public class Setting4Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting4);
    }

    public void next(View view){
        startActivity(new Intent(this,LostFindActivity.class));
        finish();
    }

    public void previous(View view){
        startActivity(new Intent(this, Setting3Activity.class));
        finish();
    }

}
