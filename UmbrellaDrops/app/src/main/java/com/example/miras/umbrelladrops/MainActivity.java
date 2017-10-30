package com.example.miras.umbrelladrops;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button start, pause;
    Umbrella umbrella;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (Button) findViewById(R.id.start);
        pause = (Button) findViewById(R.id.pause);
        umbrella = (Umbrella) findViewById(R.id.umbrella);

        umbrella.setBackgroundColor(Color.CYAN);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                umbrella.validate = true;
                umbrella.invalidate();
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                umbrella.validate = false;
            }
        });
    }
}
