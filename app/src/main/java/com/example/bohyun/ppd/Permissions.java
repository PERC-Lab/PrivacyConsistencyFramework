package com.example.bohyun.ppd;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Permissions extends AppCompatActivity {
    LinearLayout layout;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions);

        String app_name = getIntent().getStringExtra("NAME");
        String permissions = getIntent().getStringExtra("PERMISSIONS");

        layout = findViewById(R.id.layout);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        p.gravity = Gravity.LEFT;


        String[] parts = permissions.split("\n");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, parts);
//        listOfPermissionsWithToggle.setAdapter(arrayAdapter);
        if(permissions.equals("")) {
            TextView tv = new TextView(this);
            tv.setText("No Permission Required");
            layout.addView(tv,p);
            return;
        }
        for(int i = 0; i < parts.length; i++){
            Switch sw = new Switch(this);
            sw.setText(parts[i]);
            layout.addView(sw, p);
        }

        getSupportActionBar().setTitle(app_name);

//        listOfPermissionsWithToggle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(Permissions.this, "Disabled", Toast.LENGTH_SHORT).show();
//
//            }
//        });


    }
}
