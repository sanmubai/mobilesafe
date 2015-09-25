package com.sanmubai.mobilesafe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sanmubai.mobilesafe.R;

public class Setting3Activity extends SettingBaseActivity {

    private EditText etNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting3);

        etNo = (EditText) findViewById(R.id.et_setting3_no);
    }

    public void showContacts(View view){
        System.out.println("联系人");

    }

    @Override
    public void showNext(){

        if (TextUtils.isEmpty(etNo.getText().toString())){
            Toast.makeText(this,"输入框为空！",Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(new Intent(this,Setting4Activity.class));
        finish();
        overridePendingTransition(R.anim.trans_next_in, R.anim.trans_next_out);
    }

    @Override
    public void showPrevious(){
        startActivity(new Intent(this, Setting2Activity.class));
        finish();
        overridePendingTransition(R.anim.trans_previous_in, R.anim.trans_previous_out);
    }
}
