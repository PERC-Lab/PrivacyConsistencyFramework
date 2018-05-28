package com.example.bohyun.ppd;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class UID extends AppCompatActivity {
    ListView listOfUID;
    ArrayList<String> ar = new ArrayList<String>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uid);
        getSupportActionBar().setTitle("UID");

        listOfUID = findViewById(R.id.listOfUID);
        PackageManager pm = getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo applicationInfo : packages) {
            ar.add(applicationInfo.uid + ": " + applicationInfo.packageName +"\n");
        }
        Collections.sort(ar);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ar);
        listOfUID.setAdapter(arrayAdapter);
    }
}
