package com.example.bohyun.ppd;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Permissions extends AppCompatActivity {
//    LinearLayout layout;
    ListView listView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions);

        String app_name = getIntent().getStringExtra("NAME");
        String permissions = getIntent().getStringExtra("PERMISSIONS");
        getSupportActionBar().setTitle(app_name);

//        layout = findViewById(R.id.layout);
//        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.FILL_PARENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT
//        );
//        p.gravity = Gravity.LEFT;

        listView = findViewById(R.id.list_of_permissions_with_toggle);


        String[] parts = permissions.split("\n");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, parts);
        listView.setAdapter(arrayAdapter);
//        if(permissions.equals("")) {
//            TextView tv = new TextView(this);
//            tv.setText("No Permission Required");
//            layout.addView(tv,p);
//            return;
//        }
//        ArrayList<Switch> listOfSwitch = new ArrayList<Switch>();
//        for(int i = 0; i < parts.length; i++){
//            Switch sw = new Switch(this);
//            sw.setText(parts[i]);
//            sw.setChecked(true);
//            listOfSwitch.add(sw);
//            layout.addView(sw, p);
//        }





//        listOfPermissionsWithToggle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(Permissions.this, "Disabled", Toast.LENGTH_SHORT).show();
//
//            }
//        });


    }
}
