package com.example.bohyun.ppd;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

public class Analysis extends AppCompatActivity {

    TextView tv1, tv2;
    ListView listOfUID;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);

        String app_name = getIntent().getStringExtra("SELECTED_APP");
        String inconsistency = getIntent().getStringExtra("INCONSISTENCY");
        String comments = getIntent().getStringExtra("COMMENTS");

        getSupportActionBar().setTitle(app_name);

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);

        tv1.setText("Inconsistency: " + inconsistency);
        tv2.setText("Comments: " + comments);
    }
}
