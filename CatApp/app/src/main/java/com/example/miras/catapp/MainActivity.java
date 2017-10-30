package com.example.miras.catapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.InputStream;

import fr.arnaudguyon.xmltojsonlib.XmlToJson;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InputStream inputStream = getResources().openRawResource(R.raw.file);
        XmlToJson xmlToJson = new XmlToJson.Builder(inputStream,null).build();

    }
}
