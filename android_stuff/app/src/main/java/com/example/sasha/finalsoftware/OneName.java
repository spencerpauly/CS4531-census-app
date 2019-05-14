package com.example.sasha.finalsoftware;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.cs4531.finalsoftware.R;

import java.util.ArrayList;

import static com.example.cs4531.finalsoftware.R.id.Year2G2;

public class OneName extends AppCompatActivity {

public String globalName;
public int year1, year2;
public EditText Name, y1, y2;


    public static ArrayList<CensusName> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_name);
    }

    public void graphButtonPressed(View myView) {
        Name = findViewById(R.id.theName);
        y1 = findViewById(R.id.Year1G1);
        y2 = findViewById(Year2G2);

        globalName = Name.getText().toString();
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
        if (globalName.equals("")){
            Toast.makeText(OneName.this,"Please enter a name",
                    Toast.LENGTH_LONG).show();
        }
        else {
            globalName = globalName.substring(0, 1).toUpperCase() + globalName.substring(1);
            System.out.println("Globalname: " + globalName);
            //System.out.println(globalName + year1 + year2);

            data = DatabaseConnection.singleSearch(globalName, year1, year2);
            //for (int i=0; i<data.size(); i++){
            //    System.out.println(data.get(i));
            //}
//            startActivity(myIntent);

            if (data.get(0).name.equals("empty")) {
                Toast.makeText(OneName.this, "Name not in database",
                        Toast.LENGTH_LONG).show();
            } else {
                Intent myIntent = new Intent(this, DisplayGraphOneName.class);
                startActivity(myIntent);
            }
        }

    }

//    public void switchToOneGraph(View myView) {
//        Intent myIntent = new Intent(this, DisplayGraphOneName.class);
//        startActivity(myIntent);
//    }
}
