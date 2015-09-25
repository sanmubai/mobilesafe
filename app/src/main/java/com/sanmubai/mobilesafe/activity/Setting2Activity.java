package com.sanmubai.mobilesafe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.sanmubai.mobilesafe.R;
import com.sanmubai.mobilesafe.view.SettingItemView;

public class Setting2Activity extends SettingBaseActivity {

    private SettingItemView bindSIM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting2);
        
        bindSIM = (SettingItemView) findViewById(R.id.setting_switch);

        boolean isSimBind = sp.getBoolean("isSimBind", false);

        if(isSimBind){
            bindSIM.setChecked(true);
        }else {
            bindSIM.setChecked(false);
        }

        bindSIM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bindSIM.isChecked()){
                    //取消绑定
                    bindSIM.setChecked(false);

                    sp.edit().remove("isSimBind").commit();
                }else {
                    bindSIM.setChecked(true);
                    sp.edit().putBoolean("isSimBind",true).commit();
                }
            }
        });
    }

    @Override
    public void showNext(){
        if(!bindSIM.isChecked()){
            Toast.makeText(this,"请绑定SIM卡。。。",Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(new Intent(this, Setting3Activity.class));
        finish();
        overridePendingTransition(R.anim.trans_next_in, R.anim.trans_next_out);
    }

    @Override
    public void showPrevious(){
        startActivity(new Intent(this,Setting1Activity.class));
        finish();
        overridePendingTransition(R.anim.trans_previous_in, R.anim.trans_previous_out);
    }


}
