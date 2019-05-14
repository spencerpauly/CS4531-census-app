package com.example.sasha.finalsoftware;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.cs4531.finalsoftware.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class DisplayGraphWildCard extends AppCompatActivity {

    ArrayList<LineGraphSeries<DataPoint>> boySeries= new ArrayList<>();
    ArrayList<LineGraphSeries<DataPoint>> girlSeries= new ArrayList<>();
    ArrayList<String> nameList = new ArrayList<>();

    int boyColors[] = {Color.BLUE, Color.RED, Color.BLACK, Color.GRAY, Color.GREEN };
    int girlColors[] = {Color.YELLOW, Color.CYAN, Color.MAGENTA, Color.rgb(240,40,240), Color.rgb(220,170,20)};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_graph_wildcard);

        boySeries.clear();
        girlSeries.clear();
        nameList.clear();

        double y;
        int x;

        int numOfNames = WildcardName.namesToGraph.size();

        for(int i = 0; i < numOfNames; i++){
            nameList.add(WildcardName.namesToGraph.get(i).get(0).name);
        }

        GraphView graph = findViewById(R.id.graphWild);

        for(int i = 0; i < nameList.size(); i++){
            graph.removeAllSeries();
            boySeries.add(new LineGraphSeries<DataPoint>());
            girlSeries.add(new LineGraphSeries<DataPoint>());
            boySeries.get(i).setColor(boyColors[i]);
            girlSeries.get(i).setColor(girlColors[i]);

            boySeries.get(i).setTitle(nameList.get(i)+": Boys");
            girlSeries.get(i).setTitle(nameList.get(i)+": Girls");
        }


        for(int i = 0; i < numOfNames; i++) {

            ArrayList<CensusName> singleName = WildcardName.namesToGraph.get(i);
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
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        graph.getGridLabelRenderer().setHorizontalAxisTitle("Year");
        graph.getGridLabelRenderer().setVerticalAxisTitle("Popularity (%)");

    }




}