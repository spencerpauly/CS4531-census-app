package com.example.sasha.finalsoftware;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.example.cs4531.finalsoftware.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class DisplayGraphTwoName extends AppCompatActivity {

    ArrayList<LineGraphSeries<DataPoint>> boySeries= new ArrayList<>();
    ArrayList<LineGraphSeries<DataPoint>> girlSeries= new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_graph_two_name);
        boySeries.clear();
        girlSeries.clear();

        double y;
        int x;

        int numOfNames = 2;
        String name1 = TwoName.data.get(0).get(0).name;
        String name2 = TwoName.data.get(1).get(0).name;

        GraphView graph = (GraphView) findViewById(R.id.graphTwo);
        graph.removeAllSeries();
        boySeries.add(new LineGraphSeries<DataPoint>());
        boySeries.add(new LineGraphSeries<DataPoint>());
        girlSeries.add(new LineGraphSeries<DataPoint>());
        girlSeries.add(new LineGraphSeries<DataPoint>());
        boySeries.get(0).setColor(Color.BLUE);
        boySeries.get(1).setColor(Color.BLACK);
        girlSeries.get(0).setColor(Color.RED);
        girlSeries.get(1).setColor(Color.GRAY);

        boySeries.get(0).setTitle(name1+": Boys");
        boySeries.get(1).setTitle(name2+": Boys");
        girlSeries.get(0).setTitle(name1+": Girls");
        girlSeries.get(1).setTitle(name2+": Girls");

        for(int i = 0; i < numOfNames; i++) {

            ArrayList<CensusName> singleName = TwoName.data.get(i);
            for(int j = 0; j < singleName.size(); j++){
                CensusName data = singleName.get(j);
                x = Integer.valueOf(data.year);
                y = Double.valueOf(data.percent);
                if(data.sex.equals("girl")){
                    girlSeries.get(i).appendData(new DataPoint(x,y),true,500);
                }
                else{
                    boySeries.get(i).appendData(new DataPoint(x, y), true, 500);
                }

            }
        }


        for(int i = 0;i<boySeries.size(); i++){
            if(!girlSeries.get(i).isEmpty()) {
                graph.addSeries(girlSeries.get(i));
            }
            if(!boySeries.get(i).isEmpty()) {
                graph.addSeries(boySeries.get(i));
            }
        }

        graph.getLegendRenderer().setVisible(true);
//        graph.setTitle("Popularity of the names \""+ name1+ "\" and \""+ name2 + "\" over time");
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        graph.getGridLabelRenderer().setHorizontalAxisTitle("Year");
        graph.getGridLabelRenderer().setVerticalAxisTitle("Popularity (%)");

    }

}
