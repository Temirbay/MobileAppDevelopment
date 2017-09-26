package com.example.miras.guessthemeaning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class StartMenuActivity extends AppCompatActivity{

    ArrayList<String> str = new ArrayList<>();
    Button start;
    Button add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);

        Intent intent = getIntent();
      //  str = intent.getStringArrayListExtra("word");
        start = (Button) findViewById(R.id.buttonStart1);
        add = (Button) findViewById(R.id.buttonAdd1);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
       //         intent.putExtra("string", str);
                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                Intent intent = new Intent(getBaseContext(), AddWordActivity.class);
                startActivity(intent);
            }
        });
    }

}
