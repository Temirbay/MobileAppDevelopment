package com.example.miras.sqlitedemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    List<String> people = new ArrayList<>();

    GridView table;
    GraphView graph;
    int cntSize = 0;

    ArrayList<String> names = new ArrayList<>();
    ArrayList<Integer> ages = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = openOrCreateDatabase("pizza", MODE_PRIVATE, null);
        readSql();

        table = (GridView) findViewById(R.id.table);
        table.setNumColumns(3);
        Cursor cur = db.rawQuery("select * from person", null);
        if (cur.moveToFirst()) {
            while (cur.moveToNext()) {
                String name = cur.getString(cur.getColumnIndex("name"));
                int age = cur.getInt(cur.getColumnIndex("age"));
                String gender = cur.getString(cur.getColumnIndex("gender"));
                people.add(name);
                people.add(String.valueOf(age));
                people.add(gender);

                names.add(name);
                ages.add(age);

            }
            cur.close();
        }
        table.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, people));


        graph = (GraphView) findViewById(R.id.graph);
        /*graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMaxX(100);
        graph.getViewport().setMinX(0);
*/
        String[] nameArray = new String[names.size()];
        for (int i = 0; i < names.size(); ++i)
                nameArray[i] = names.get(i);

        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(nameArray);
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);


        DataPoint[] dataPoints = new DataPoint[ages.size()];
        for (int i = 0; i < ages.size(); ++i) {
            DataPoint point = new DataPoint(i, ages.get(i));
            dataPoints[i] = point;
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPoints);
        graph.addSeries(series);
    }

    private void readSql() {
        Scanner sc = new Scanner(getResources().openRawResource(R.raw.pizza));
        String q = "";
        while (sc.hasNextLine()) {
            q += sc.nextLine();
            if (q.trim().endsWith(";")) {
                db.execSQL(q);
                q = "";
}
        }
        sc.close();
    }

}
