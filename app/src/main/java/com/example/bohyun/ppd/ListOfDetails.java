package com.example.bohyun.ppd;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListOfDetails extends AppCompatActivity {

    ListView listOfPermissionNames;
    ArrayList<String> ar = new ArrayList<String>();
    InputStream permissionCSV, dangerousPermissionsCSV;
    String[] temp;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_details);
        getSupportActionBar().setTitle("Details");

        final List<String[]> dangerousPermissionRead = new ArrayList();
        final List<String[]> permissionRead = new ArrayList();

        final PackageManager pm = getPackageManager();
        final List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        listOfPermissionNames = findViewById(R.id.listOFPermissionsFromDetails);

        //read CSV files
        dangerousPermissionsCSV = getResources().openRawResource(R.raw.dangerous_permissions);
        BufferedReader reader = new BufferedReader(new InputStreamReader(dangerousPermissionsCSV));
        try{
            String csvLine;
            while((csvLine = reader.readLine()) != null){
                temp = csvLine.split(",");
                dangerousPermissionRead.add(temp);
            }
        }catch (Exception e){

        }

        permissionCSV = getResources().openRawResource(R.raw.permissions);
        BufferedReader reader2 = new BufferedReader(new InputStreamReader(permissionCSV));
        try{
            String csvLine2;
            while((csvLine2 = reader2.readLine()) != null){
                temp = csvLine2.split(",");
                permissionRead.add(temp);
            }
        }catch (Exception e){

        }



        for (ApplicationInfo applicationInfo : packages) {
            try {
                PackageInfo packageInfo = pm.getPackageInfo(applicationInfo.packageName, PackageManager.GET_PERMISSIONS);

                String[] requestedPermissions = packageInfo.requestedPermissions;

                if (requestedPermissions != null) {
                    for (int i = 0; i < requestedPermissions.length; i++) {
                        if(!ar.contains(requestedPermissions[i])){
                            ar.add(requestedPermissions[i] + "\n");

                        }
                    }
                }
            } catch (Exception e) {
                //Error
            }
        }


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ar);
        listOfPermissionNames.setAdapter(arrayAdapter);

        listOfPermissionNames.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String selected = String.valueOf(parent.getItemAtPosition(position));
                        Intent intent = new Intent(getBaseContext(), Details.class);

                        //check if selected item is in csv file
                        for(int i=0; i<permissionRead.size(); i++){
                            if(Arrays.toString(permissionRead.get(i)).contains(selected.substring(19, selected.length() -1))){
                                temp = Arrays.toString(permissionRead.get(i)).split(",");
                                intent.putExtra("DESCRIPTION",temp[1].substring(0, temp[1].length()-1).trim());
                            }
                        }

                        for(int i=0; i<dangerousPermissionRead.size(); i++){
                            if(Arrays.toString(dangerousPermissionRead.get(i)).contains(selected.substring(19, selected.length()-1))){
                                temp = Arrays.toString(dangerousPermissionRead.get(i)).split(",");
                                intent.putExtra("DANGEROUS_DESCRIPTION",temp[1].substring(0, temp[1].length()-1).trim());
                            }
                        }

                        intent.putExtra("NAME", selected);


                        final StringBuffer passingListOfApps = new StringBuffer();
                        for (ApplicationInfo applicationInfo: packages){
                            try {
                                PackageInfo packageInfo = pm.getPackageInfo(applicationInfo.packageName, PackageManager.GET_PERMISSIONS);
                                String[] requestedPermissions = packageInfo.requestedPermissions;

                                if (requestedPermissions != null) {
                                    for (int j = 0; j < requestedPermissions.length; j++) {
                                        if(requestedPermissions[j].equals(selected.trim())){
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
                                            passingListOfApps.append(temp + "\n");
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                //Error
                            }
                        }

                        intent.putExtra("LIST_OF_APPS", (Serializable)passingListOfApps);

                        startActivityForResult(intent, 0);
                    }
                });
    }
}