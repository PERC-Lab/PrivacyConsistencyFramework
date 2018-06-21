package com.example.bohyun.ppd;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import java.util.List;


public class PermissionMenu extends AppCompatActivity {

    Button permissionBtn;
    Button detailBtn;
    Button uidBtn;
    Button analysisBtn;

    public void onCreate(Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_menu);
        getSupportActionBar().setTitle("Menu");

        permissionBtn = findViewById(R.id.permission_btn);
        permissionBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Once the button "Permissions" is clicked, screen changes to ListOfPermissions
                startActivity(new Intent(PermissionMenu.this, ListOfPermissions.class));
            }
        });

        detailBtn = findViewById(R.id.detail_btn);
        detailBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Once the button "Details" is clicked, screen changes to ListOfDetails
                startActivity(new Intent(PermissionMenu.this, ListOfDetails.class));
            }
        });

        uidBtn = findViewById(R.id.uid_btn);
        uidBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Once the button "UIP" is clicked, screen changes to UID
                startActivity(new Intent(PermissionMenu.this, UID.class));
            }
        });

        analysisBtn = findViewById(R.id.analysis_btn);
        analysisBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Once the button "Analysis" is clicked, screen changes to Analysis
                startActivity(new Intent(PermissionMenu.this, ListOfAnalysis.class));
            }
        });
    }
}