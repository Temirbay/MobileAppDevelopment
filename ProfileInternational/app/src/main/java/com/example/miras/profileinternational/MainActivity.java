package com.example.miras.profileinternational;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView birthdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        birthdate = (TextView) findViewById(R.id.birthdate);

        DateFormat dfm = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault());

        Toast.makeText(getApplicationContext(), Locale.getDefault().toString(), Toast.LENGTH_LONG).show();

        Date birthday = new Date();

        if (Locale.getDefault().toString().equals("fr_FR"))
            birthday = new Date(1993, 2, 15);

        if (Locale.getDefault().toString().equals("es_ES"))
            birthday = new Date(1987, 1, 2);

        if (Locale.getDefault().toString().equals("en_US"))
            birthday = new Date(1993, 6, 28);

        if (Locale.getDefault().toString().equals("kk_KZ"))
            birthday = new Date(1993, 1, 23);

        String cur = dfm.format(birthday);
        birthdate.setText(cur);
    }
}
