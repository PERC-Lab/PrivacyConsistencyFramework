package com.example.bohyun.ppd;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListOfPermissions extends AppCompatActivity {

    ListView listOfAppNames;
    ArrayList<String> ar = new ArrayList<String>();
    int counter = 10000;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_permissions);
        getSupportActionBar().setTitle("Permissions");

        PackageManager pm = getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        listOfAppNames = findViewById(R.id.listOFAppsFromPermissions);
        final StringBuffer appNameAndPermissions = new StringBuffer();


        for (ApplicationInfo applicationInfo : packages) {
            try {
                int count = 0 ;
                char ch ;
                String temp = "";
                String appName = applicationInfo.packageName ;
                for(int i = 0 ; i < appName.length() ; i++)
                {
                    if(appName.charAt(i) != '.') {
                        ch = appName.charAt(i);
                        if (count == 0) {
                            ch = (char) ((int) ch - 32);
                            count++;
                        }
                        temp+= ch;
                    }
                    else{
                        temp = "";
                        count = 0;
                    }
                }

                ar.add(temp + "\n");

                try {
                    PackageInfo packageInfo = pm.getPackageInfo(applicationInfo.packageName, PackageManager.GET_PERMISSIONS);
                    if(applicationInfo.uid < 2000){
                        continue;
                    }
                    appNameAndPermissions.append(counter++ + "\n");

                    //Get Permissions
                    String[] requestedPermissions = packageInfo.requestedPermissions;
                    if (requestedPermissions != null) {
                        for (int i = 0; i < requestedPermissions.length; i++) {
//                            Log.d("test", requestedPermissions[i]);
                            appNameAndPermissions.append(requestedPermissions[i] + "\n");
                        }

                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            } catch (Exception e) {
                //Error
            }
        }
        appNameAndPermissions.append(counter);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ar);
        listOfAppNames.setAdapter(arrayAdapter);

        listOfAppNames.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String selected = String.valueOf(parent.getItemAtPosition(position));
                        final StringBuffer passingPermissions = new StringBuffer();
                        int currentPosition = position + 10000;
                        int currentIndex = appNameAndPermissions.indexOf(Integer.toString(currentPosition));
                        int nextIndex = appNameAndPermissions.indexOf(Integer.toString(currentPosition+1));

                        passingPermissions.append(appNameAndPermissions.substring(currentIndex+6, nextIndex));

                        if (position >= 0) {
                            Intent intent = new Intent(getBaseContext(), Permissions.class);
                            intent.putExtra("PERMISSIONS", (Serializable)passingPermissions);
                            intent.putExtra("NAME", selected);
                            startActivityForResult(intent, 0);
                        }

                    }
                });
    }
}
