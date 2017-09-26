package com.example.miras.emailapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

public class EmailDetailsActivity extends AppCompatActivity {

    public TextView senderView;
    public TextView dateView;
    public TextView subjectView;
    public TextView titleView;
    public TextView contentView;
    public Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_details);

        Intent intent = getIntent();

        senderView = (TextView) findViewById(R.id.senderView);
        dateView = (TextView) findViewById(R.id.dateView);
        subjectView = (TextView) findViewById(R.id.subjectView);
        titleView = (TextView) findViewById(R.id.titleView);
        contentView = (TextView) findViewById(R.id.contentView);

        senderView.setText("<" + intent.getStringExtra("sender") + ">");
        dateView.setText(intent.getStringExtra("date"));
        subjectView.setText(intent.getStringExtra("subject"));
        titleView.setText(intent.getStringExtra("title"));
        contentView.setText(intent.getStringExtra("content"));

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
