package com.example.miras.profileapptriple;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{

    RadioButton goToFirst;
    RadioButton goToSecond;
    RadioButton goToThird;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goToFirst = (RadioButton) findViewById(R.id.radioButton1);
        goToSecond = (RadioButton)findViewById(R.id.radioButton2);
        goToThird = (RadioButton) findViewById(R.id.radioButton3);

        goToFirst.setChecked(true);
        goToSecond.setChecked(false);
        goToThird.setChecked(false);

        goToFirst.setOnClickListener(this);
        goToSecond.setOnClickListener(this);
        goToThird.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.radioButton2:
                Intent intent = new Intent(this, Main2Activity.class);
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
