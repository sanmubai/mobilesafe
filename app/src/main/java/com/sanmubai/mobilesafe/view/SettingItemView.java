package com.sanmubai.mobilesafe.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sanmubai.mobilesafe.R;
/**
 *
 * Created by bb on 15-9-19.
 */
public class SettingItemView extends RelativeLayout {
    private static final String NAMESPACE = "http://schemas.android.com/apk/res/com.sanmubai.mobilesafe" ;
    private String mDescOff;
    private String mDescOn;
    private String mTitle;
    private TextView tvDesc;
    private CheckBox cb;
    private TextView tvTitle;

    public SettingItemView(Context context) {
        super(context);
        initView();
    }

    public SettingItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTitle =attrs.getAttributeValue(NAMESPACE, "settingtitle");
        mDescOn = attrs.getAttributeValue(NAMESPACE, "desc_on");
        mDescOff =attrs.getAttributeValue(NAMESPACE, "desc_off");
        initView();
    }

    public SettingItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    private void initView(){
        View.inflate(getContext(),R.layout.setting_list_item,this);

        tvTitle= (TextView) findViewById(R.id.tv_settingItem_title);
        tvDesc= (TextView) findViewById(R.id.tv_settingItem_desc);
        cb= (CheckBox) findViewById(R.id.cb_settingItem);

        setTitle(mTitle);
    }

    private void setTitle(String title){
        tvTitle.setText(title);
    }

    private void setDesc(String desc){
        tvDesc.setText(desc);
    }

    public void setChecked(boolean check){
        cb.setChecked(check);
        if(check){
            setDesc(mDescOn);
        }else {
            setDesc(mDescOff);
        }
    }

    public boolean isChecked(){
        return cb.isChecked();
    }
}
