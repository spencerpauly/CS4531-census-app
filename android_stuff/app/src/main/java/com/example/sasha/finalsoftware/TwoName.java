package com.example.sasha.finalsoftware;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.cs4531.finalsoftware.R;

import java.util.ArrayList;

public class TwoName extends AppCompatActivity {
    //public static ArrayList<ArrayList<CensusName>> data = new ArrayList<>();
    public static ArrayList<ArrayList<CensusName>> data = new ArrayList<>();
    //public ArrayList<> n2Data;
    public String globaln1, globaln2;
    public int year1, year2;
    public EditText n1, n2, y1, y2;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.two_name);
    }
    public void switchToTwoGraph(View myView) {
        //data = new ArrayList<>();
        data.clear();
        n1 = findViewById(R.id.NameOne);
        n2 = findViewById(R.id.NameTwo);
        y1= findViewById(R.id.Year1G2);
        y2 = findViewById(R.id.Year2G2);

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
        if (n1.getText().toString().equals("") || n2.getText().toString().equals("")){
            Toast.makeText(TwoName.this,"Please enter two names",
                    Toast.LENGTH_LONG).show();
        }
        else {
            String name1 = n1.getText().toString().substring(0, 1).toUpperCase() + n1.getText().toString().substring(1);
            String name2 = n2.getText().toString().substring(0, 1).toUpperCase() + n2.getText().toString().substring(1);

            data.add(DatabaseConnection.singleSearch(name1, year1, year2));
            data.add(DatabaseConnection.singleSearch(name2, year1, year2));


            //n1.add(DatabaseConnection.singleSearch());
            //n2.add(DatabaseConnection.singleSearch("Will", 1800,1881));

//            System.out.println("Made it too the end of this");
            if (data.get(0).get(0).name.equals("empty") || data.get(1).get(0).name.equals("empty")) {
                Toast.makeText(TwoName.this, "Name(s) not in database",
                        Toast.LENGTH_LONG).show();
            } else {
                Intent myIntent = new Intent(this, DisplayGraphTwoName.class);
                startActivity(myIntent);
            }

        }
    }
}
