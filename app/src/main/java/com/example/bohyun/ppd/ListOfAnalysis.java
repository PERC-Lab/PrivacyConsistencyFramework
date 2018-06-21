package com.example.bohyun.ppd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListOfAnalysis extends AppCompatActivity {

    InputStream analysisCSV;
    ListView analysisList;
    String[] temp;
    ArrayList<String> ar = new ArrayList<String>();
    final List<String[]> analysisDetail = new ArrayList();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_analysis);
        getSupportActionBar().setTitle("Analysis");

        analysisList = findViewById(R.id.analysis_list);

        analysisCSV = getResources().openRawResource(R.raw.applist);
        BufferedReader reader = new BufferedReader(new InputStreamReader(analysisCSV));

        try{
            String csvLine;
            while((csvLine = reader.readLine()) != null){
                temp = csvLine.split(",");
                analysisDetail.add(temp);
                ar.add(temp[0]);
            }
        }catch (Exception e){
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ar);
        analysisList.setAdapter(arrayAdapter);

        analysisList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String selected = String.valueOf(parent.getItemAtPosition(position));
                        Intent intent = new Intent(getBaseContext(), Analysis.class);

                        for(int i=0; i<analysisDetail.size(); i++){
                            if(Arrays.toString(analysisDetail.get(i)).contains(selected)){
                                temp = Arrays.toString(analysisDetail.get(i)).split(",");
                                intent.putExtra("INCONSISTENCY",temp[1].trim());
                            }
                        }

                        //check if selected item is in csv file
                        for(int i=0; i<analysisDetail.size(); i++){
                            if(Arrays.toString(analysisDetail.get(i)).contains(selected)){
                                temp = Arrays.toString(analysisDetail.get(i)).split(",");
                                intent.putExtra("COMMENTS",temp[2].trim());
                            }
                        }
//
                        intent.putExtra("SELECTED_APP", selected);

                        startActivityForResult(intent, 0);
                    }
                });
    }
}