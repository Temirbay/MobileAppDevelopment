package com.example.miras.paintpencil;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Path;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import petrov.kristiyan.colorpicker.ColorPicker;

public class MainActivity extends AppCompatActivity {

    Button clear;
    Button colorPick;
    PaintBoard paint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clear = (Button) findViewById(R.id.bnReset);
        paint = (PaintBoard) findViewById(R.id.paint);
        colorPick = (Button) findViewById(R.id.bnColorPick);

        paint.setBackgroundColor(Color.BLACK);

        Paint newPaint = new Paint();
        newPaint.setColor(Color.WHITE);
        newPaint.setStyle(Paint.Style.STROKE);
        newPaint.setStrokeWidth(10);

        Path newPath = new Path();

        paint.paths.add(newPath);
        paint.paints.add(newPaint);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paint.clear = true;
                paint.invalidate();
            }
        });

        colorPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 ColorPicker colorPicker = new ColorPicker(MainActivity.this);
                ArrayList<String> colors = new ArrayList<>();
                colors.add("#82B926");
                colors.add("#a276eb");
                colors.add("#6a3ab2");
                colors.add("#666666");
                colors.add("#FFFF00");
                colors.add("#3C8D2F");
                colors.add("#FA9F00");
                colors.add("#FF0000");

                colorPicker
                        .setDefaultColorButton(Color.parseColor("#f84c44"))
                        .setColors(colors)
                        .setColumns(3)
                        .setRoundColorButton(true)
                        .setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                            @Override
                            public void onChooseColor(int position, int color) {
                                Paint newPaint = new Paint();
                                newPaint.setColor(color);
                                newPaint.setStyle(Paint.Style.STROKE);
                                newPaint.setStrokeWidth(10);

                                Path newPath = new Path();

                                paint.paths.add(newPath);
                                paint.paints.add(newPaint);
                            }

                            @Override
                            public void onCancel() {

                            }
                        }).show();
            }
        });
    }
}
