package com.example.sasha.finalsoftware;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.example.cs4531.finalsoftware.R;

import java.util.ArrayList;

public class WildcardName extends AppCompatActivity {
    public static ArrayList<ArrayList<CensusName>> data = new ArrayList<>();
    public static ArrayList<ArrayList<CensusName>> namesToGraph = new ArrayList<>();
    private static int maxNames;
    public EditText wild, y1, y2;
    public TextView to, portionofname, daterange, header;
    public LinearLayout wildScroll;
    public Button toGraph, wButton;
    public String wildcard;
    public int year1, year2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wildcard_name);


        maxNames = 5;

        
        to = findViewById(R.id.to);
        portionofname = findViewById(R.id.textView13);
        daterange = findViewById(R.id.textView14);
        header = findViewById(R.id.textView6);

        wButton = findViewById(R.id.displayWildcardButton);

        wildScroll = findViewById(R.id.linearWild);
        wildScroll.setVisibility(View.GONE);
        findViewById(R.id.allWildcard).setVisibility(View.GONE);

        toGraph = findViewById(R.id.wildGraph);
        toGraph.setVisibility(View.GONE);

        wild = findViewById(R.id.Wildcard);

        y1 = findViewById(R.id.year1Wild);
        y2 = findViewById(R.id.Year2Wild);

        wildScroll.removeAllViews();
        data.clear();
        namesToGraph.clear();

        
    }

    public void wildCard(View myView) {

        wildcard = wild.getText().toString();

        if(y1.getText().toString().equals("")){
            year1=1880;
        }else year1 = Integer.parseInt(y1.getText().toString());
        if(y2.getText().toString().equals("")){
            year2=1881;
        }else year2 = Integer.parseInt(y2.getText().toString());

        if(year1 < 1880) year1 = 1880;
        if(year1 > 2008) year1 = 2007;
        if(year2 < 1880) year2 = 1881;
        if(year2 > 2008) year2 = 2008;
        if (wildcard.equals("")){
            Toast.makeText(WildcardName.this,"Please enter a name",
                    Toast.LENGTH_LONG).show();
        }
        else {
            data.clear();
            ArrayList<CensusName> rawData = DatabaseConnection.wildcardSearch(wildcard, year1, year2);
            CensusName last = rawData.remove(0);
            ArrayList<CensusName> currentArray = new ArrayList<>();
            currentArray.add(last);

            while (!rawData.isEmpty()) {
                CensusName current = rawData.remove(0);
                if (current.name.equals(last.name)) {
                    currentArray.add(current);
                } else {
                    data.add(currentArray);
                    currentArray = new ArrayList<>();
                    currentArray.add(current);
                }
                last = current;

            }
            data.add(currentArray);
            //generateList();
            if (data.get(0).get(0).name.equals("empty")) {
                Toast.makeText(WildcardName.this, "Name not in database",
                        Toast.LENGTH_LONG).show();
            } else {

                wild.setVisibility(View.GONE);
                wButton.setVisibility(View.GONE);
                y1.setVisibility(View.GONE);
                y2.setVisibility(View.GONE);
                to.setVisibility(View.GONE);
                daterange.setVisibility(View.GONE);
                portionofname.setVisibility(View.GONE);


                wildScroll.setVisibility(View.VISIBLE);
                findViewById(R.id.allWildcard).setVisibility(View.VISIBLE);
                toGraph.setVisibility(View.VISIBLE);




                generateList();
            }
        }
    }

    public void switchToWildCardGraph(View myView) {
        Intent myIntent = new Intent(this, DisplayGraphWildCard.class);

        

//        for(int i = 0; i < data.size(); i++){
//            System.out.println("Array " + i + ":");
//            for(int j = 0; j <data.get(i).size(); j++){
//                System.out.println("\t" + data.get(i).get(j).name);
//            }
//        }
        System.out.println("Size of data array: " + data.size());
        startActivity(myIntent);
    }

    private void generateList(){
        //ScrollView mainLayout = findViewById(R.id.allWildcard);
        for(int i = 0; i<data.size();i++){
            final CheckBox checkBox = new CheckBox(this);
            checkBox.setText(data.get(i).get(0).name);
            checkBox.setChecked(false);
            final ArrayList<CensusName> temp = data.get(i);
            checkBox.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    if(checkBox.isChecked()) {
                        if (maxNames >= 1) {
                            namesToGraph.add(temp);
                            maxNames--;
                        } else {
                            Toast.makeText(WildcardName.this, "The maximum amount of names has been reached", Toast.LENGTH_LONG).show();
                            checkBox.setChecked(false);
                        }
                    }
                    else{
                        namesToGraph.remove(temp);
                        if(maxNames <5)
                            maxNames++;
                    }

                }
            });
            wildScroll.addView(checkBox);
        }
        //return true;
    }
    
}
