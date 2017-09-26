package com.example.miras.dictionary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class DictionaryActivity extends AppCompatActivity {

    ListView list;
    TextView textView;
    String filename = "raw/dictionary.txt";
    Map<String, String> dictionary;
    ArrayList<String> words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        list = (ListView) findViewById(R.id.listView1);
        textView = (TextView) findViewById(R.id.textView);
        words = new ArrayList<>();
        dictionary = new HashMap<>();

        readAllFiles (filename);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                textView.setText(dictionary.get(words.get(i)));
            }
        });
    }

    public void readAllFiles (String filename) {
        try {
            InputStream is = getResources().openRawResource(R.raw.dictionary);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String curline;
            if (is == null) return;
            while ((curline = br.readLine()) != null) {
                String[] pair = curline.split("#");
                dictionary.put(pair[0], pair[1]);
                words.add(pair[0]);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
