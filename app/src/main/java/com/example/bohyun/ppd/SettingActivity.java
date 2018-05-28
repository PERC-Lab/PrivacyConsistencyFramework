package com.example.bohyun.ppd;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.WindowManager;


public class SettingActivity extends PreferenceActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        addPreferencesFromResource(R.xml.setting_activity);
    }
}
