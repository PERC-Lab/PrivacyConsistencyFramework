package com.example.bohyun.ppd;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;


public class Permissions extends AppCompatActivity {
    TextView listOfPermissions;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions);

        listOfPermissions = findViewById(R.id.permissions_id);

        String app_name = getIntent().getStringExtra("NAME");
        String permissions = getIntent().getStringExtra("PERMISSIONS");

        listOfPermissions.setMovementMethod(new ScrollingMovementMethod());

        getSupportActionBar().setTitle(app_name);

        if(permissions != null) {
            listOfPermissions.setText(permissions);
        }
    }
}
