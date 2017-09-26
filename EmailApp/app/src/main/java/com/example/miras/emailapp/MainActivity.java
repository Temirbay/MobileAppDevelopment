package com.example.miras.emailapp;

import android.content.Intent;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter adapter;
    ArrayList listItems = new ArrayList();

    ArrayList<Mail> emails = new ArrayList<>();

    CheckBox sortByDate;
    CheckBox sortByTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sortByDate = (CheckBox) findViewById(R.id.checkBox);
        sortByTitle = (CheckBox) findViewById(R.id.checkBox2);

        listView = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, emails);
        listView.setAdapter(adapter);


        readAllFiles();
/*
        for (int i = 0; i < emails.size(); ++i) {
            View v = listView.getChildAt(i);
            v.setD
        }
*/
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Mail cur = emails.get(i);
                Intent intent = new Intent(getBaseContext(), EmailDetailsActivity.class);
                intent.putExtra("sender", cur.sender);
                intent.putExtra("date", cur.date);
                intent.putExtra("title", cur.title);
                intent.putExtra("content", cur.content);
                intent.putExtra("subject", cur.subject);
                startActivity(intent);
            }
        });

        sortByDate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked == true) {
                    for (int i = 0; i < emails.size(); ++i)
                        for (int j = i+1; j < emails.size(); ++j)
                            if (compareByDate(emails.get(i).date, emails.get(j).date))
                                Collections.swap(emails, i, j);
                }
                adapter.notifyDataSetChanged();
            }
        });

        sortByTitle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked == true) {
                    for (int i = 0; i < emails.size(); ++i)
                        for (int j = i+1; j < emails.size(); ++j)
                            if (compareByTitle(emails.get(i).title, emails.get(j).title))
                                Collections.swap(emails, i, j);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void readAllFiles() {
        try {
            InputStream is = getResources().openRawResource(R.raw.emails);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String curline;
            if (is == null) return;
            while ((curline = br.readLine()) != null) {
                String[] cur = curline.split("#");
                emails.add(new Mail(Integer.parseInt(cur[0].toString()), cur[1], cur[2], cur[3], cur[4], cur[5]));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean compareByDate (String date1, String date2) {
        int day1 = Character.getNumericValue(date1.charAt(0))*10 + Character.getNumericValue(date1.charAt(1));
        int month1 = Character.getNumericValue(date1.charAt(3))*10 + Character.getNumericValue(date1.charAt(4));
        int year1 = Character.getNumericValue(date1.charAt(6))*1000 + Character.getNumericValue(date1.charAt(7))*100
                + Character.getNumericValue(date1.charAt(8))*10 + Character.getNumericValue(date1.charAt(9));;

        int day2 = Character.getNumericValue(date2.charAt(0))*10 + Character.getNumericValue(date2.charAt(1));
        int month2 = Character.getNumericValue(date2.charAt(3))*10 + Character.getNumericValue(date2.charAt(4));
        int year2 = Character.getNumericValue(date2.charAt(6))*1000 + Character.getNumericValue(date2.charAt(7))*100
                + Character.getNumericValue(date2.charAt(8))*10 + Character.getNumericValue(date2.charAt(9));;

        if (year1 > year2) return true;
        else if (year1 < year2) return false;

        if (month1 > month2) return true;
        else if (month1 < month2) return false;

        if (day1 > day2) return true;
        else if (day1 < day2) return false;

        return false;
    }
    public boolean compareByTitle (String title1, String title2) {
        for (int i = 0; i < Math.min (title1.length(), title2.length()); ++i) {
            if (title1.charAt(i) > title2.charAt(i)) return true;
            else if (title1.charAt(i) < title2.charAt(i)) return false;
        }

        return false;
    }
}
