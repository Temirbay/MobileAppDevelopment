package com.example.miras.profileapptriple;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{
    RadioButton goToFirst;
    RadioButton goToSecond;
    RadioButton goToThird;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        goToFirst = (RadioButton) findViewById(R.id.radioButton1);
        goToSecond = (RadioButton)findViewById(R.id.radioButton2);
        goToThird = (RadioButton) findViewById(R.id.radioButton3);

        goToFirst.setChecked(false);
        goToSecond.setChecked(true);
        goToThird.setChecked(false);

        goToFirst.setOnClickListener(this);
        goToSecond.setOnClickListener(this);
        goToThird.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.radioButton1:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.radioButton3:
                Intent intent1 = new Intent(this, Main3Activity.class);
                startActivity(intent1);
                break;
            default: break;
        }
    }
}
