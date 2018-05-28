package com.example.bohyun.ppd;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Details extends AppCompatActivity {
    TextView details;
    ListView applist;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        details = findViewById(R.id.details);
        applist = findViewById(R.id.list_of_applications);

        String app_name = getIntent().getStringExtra("NAME");
        String description = getIntent().getStringExtra("DESCRIPTION");
        String dangerousDescription = getIntent().getStringExtra("DANGEROUS_DESCRIPTION");
        String list_of_apps = getIntent().getStringExtra("LIST_OF_APPS");

        getSupportActionBar().setTitle(app_name);

        if(description != null){
            details.setText(description);
        }else if(dangerousDescription != null){
            details.setText("Dangerous!\n" + dangerousDescription);
        }

        String[] parts = list_of_apps.split("\n");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, parts);
        applist.setAdapter(arrayAdapter);

    }

    private static int countLines(String str){
        String[] lines = str.split("\r\n|\r|\n");
        return  lines.length;
    }
}
