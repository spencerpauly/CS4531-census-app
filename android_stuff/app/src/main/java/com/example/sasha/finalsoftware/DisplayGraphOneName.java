package com.example.sasha.finalsoftware;

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

public class DisplayGraphOneName extends AppCompatActivity {

    LineGraphSeries<DataPoint> series;
    LineGraphSeries<DataPoint> seriesGirl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_graph_one_name);
        String name;
        double y,x;
        //int numOfDates = 5;
//        if(!OneName.data.isEmpty()) {
            name = OneName.data.get(0).name;
//        }
//        else name = "error";



        GraphView graph = (GraphView) findViewById(R.id.graphOne);
        series = new LineGraphSeries<DataPoint>();
        seriesGirl = new LineGraphSeries<DataPoint>();
        series.setTitle(name + ": Boys");
        seriesGirl.setTitle(name + ": Girls");
        series.setColor(Color.BLUE);
        seriesGirl.setColor(Color.RED);

        for(int i = 0; i < OneName.data.size(); i++) {
            CensusName data = OneName.data.get(i);

            x = Integer.valueOf(data.year);
            y = Double.valueOf(data.percent);

            if(data.sex.equals("girl")){
                seriesGirl.appendData(new DataPoint(x,y),true,500);
            }
            else{
                series.appendData(new DataPoint(x, y), true, 500);
            }

        }
        if(!series.isEmpty()){
            graph.addSeries(series);
        }
        if(!seriesGirl.isEmpty()){
            graph.addSeries(seriesGirl);
        }
        graph.getLegendRenderer().setVisible(true);
//        graph.setTitle("Popularity of the name \""+ name+ "\" over time");
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        graph.getGridLabelRenderer().setHorizontalAxisTitle("Year");
        graph.getGridLabelRenderer().setVerticalAxisTitle("Popularity (%)");

    }

    public void displayNumbers(View myView) {

    }


}