package com.example.miras.guessthemeaning;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity{

    Integer N = 0; // SIZE OF DICTIONARY
    Boolean answered = true;
    Integer answerIndex;

    ArrayList<String> extra = new ArrayList<>();
    ArrayList<String> words = new ArrayList<>();
    ArrayList<String> meanings = new ArrayList<>();
    ArrayList<String> listItems = new ArrayList<>();
    ArrayAdapter<String> adapter;


    Button next;
    Button back;
    TextView MainWord;
    ListView listView;
    MediaPlayer sound;
    private Set<String> defaulValue = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
     //   extra = intent.getStringArrayListExtra("string");
        sound = MediaPlayer.create(this, R.raw.audio);
        sound.start();
        /*
        if (extra != null && extra.size() > 0) {
            for (String str : extra) {
                String[] pair = str.split("#");
                words.add(pair[0]);
                meanings.add(pair[1]);
            }
        }*/

        next = (Button) findViewById(R.id.button);
        back = (Button) findViewById(R.id.button2);
        MainWord = (TextView) findViewById(R.id.textView);
        listView = (ListView) findViewById(R.id.listView);

        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(adapter);

        readAllFiles();
        readFromPref();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next.setText("Next");
                next.setVisibility(View.INVISIBLE);
                answered = false;
                if (listItems.size() > 0)
                for (int i = 0; i < 4; ++i) {
                    View v = listView.getChildAt(i);
                    v.setBackgroundColor(Color.WHITE);
                }
                listItems.clear();
                setAnswers();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                sound.stop();
                Intent intent = new Intent(getBaseContext(), StartMenuActivity.class);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (answered == true) return;
                if (i == answerIndex) {
                    view.setBackgroundColor(Color.GREEN);
                    answered = true;
                    next.setVisibility(View.VISIBLE);
//                    Toast toast = Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT);
  //                  toast.show();
                }
                else {
    //                Toast toast = Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_SHORT);
      //              toast.show();
                         view.setBackgroundColor(Color.RED);
                }
            }
        });
    }

    public void setAnswers () {
        Random randomGenerator = new Random();
        int index = randomGenerator.nextInt(words.size());
        MainWord.setText(words.get(index));


        Integer[] indexes = new Integer[4];
        indexes[0] = index;
        for (int i = 1; i <= 3; ++i) {
           int num = index;
            while (num == index) {
                num = randomGenerator.nextInt(words.size());
                for (int j = 0; j < i; ++j)
                    if (indexes[j] == num)
                        num = index;
            }
            indexes[i] = num;
        }

        int schuffle = randomGenerator.nextInt(100);
        for (int i = 1; i <= schuffle; ++i) {
            int x = randomGenerator.nextInt(4);
            int y = randomGenerator.nextInt(4);
            int temp = indexes[x];
            indexes[x] = indexes[y];
            indexes[y] = temp;
        }

        for (int i = 0; i <= 3; ++i) {
            listItems.add(meanings.get(indexes[i]));
            if (indexes[i] == index)
                answerIndex = i;
        }
        adapter.notifyDataSetChanged();
    }


    public void readAllFiles() {
        int index = 0;
        try {
            InputStream is = getResources().openRawResource(R.raw.dictionary);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String curline;
            if (is == null) return;
            while ((curline = br.readLine()) != null) {
                String[] pair = curline.split("#");
                words.add(pair[0]);
                meanings.add(pair[1]);
                N++;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromPref () {
        SharedPreferences prefs = getBaseContext().getSharedPreferences(
                getResources().getString(R.string.myKey), MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = prefs.edit() ;

        Set<String> set = prefs.getStringSet("set", defaulValue);
        for (String str : set) {
            String[] pair = str.split("#");
            Log.d("str", str);
            words.add(pair[0]);
            meanings.add(pair[1]);
            N++;
        }
    }
}
