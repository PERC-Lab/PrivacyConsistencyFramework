package com.example.bohyun.ppd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;




public class MainActivity extends AppCompatActivity {

    private Button checkBtn;
    private Button policyBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        requestWindowFeature(Window.FEATURE_NO_TITLE); // change AppCompatActivity to Activity to use this code
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        checkBtn = findViewById(R.id.btn_check);
        checkBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Once the button "permission check" is clicked, screen changes to PermissionMenu
                startActivity(new Intent(MainActivity.this, PermissionMenu.class));
            }
        });

//        policyBtn = findViewById(R.id.btn_policy);
//        policyBtn.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                //Once the button "permission check" is clicked, screen changes to PermissionMenu
//                startActivity(new Intent(MainActivity.this, PolicyCheck.class));
//            }
//        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            //Setting button is clicked, screen changes to SettingActivity
            case R.id.setting_actionbar:
                Intent intent = new Intent(this, SettingActivity.class);
                this.startActivity(intent);
                break;
        }
        return true;
    }

//    public void marketTest(){
//
//        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//        String udid = telephonyManager.getDeviceId();
//        MarketSession session = new MarketSession();
//        session.getContext().setAndroidId(udid);
//        session.setAuthSubToken(token);
//    }



}
