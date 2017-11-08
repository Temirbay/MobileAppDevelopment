package com.example.miras.smsapp;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.provider.Telephony;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.content.Intent.ACTION_SEND;
import static android.content.Intent.EXTRA_TEXT;

public class MainActivity extends AppCompatActivity {


    public static final int READ_EXTERNAL_STORAGE_PERMISSION_CODE = 1198;

    public Button send;
    public EditText phoneNumber;
    public EditText text;
    SmsBroadcastReceiver smsreceiver;

    @Override
    protected void onStop() {
        unregisterReceiver(smsreceiver);
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        send = (Button)findViewById(R.id.bnSend);
        phoneNumber = (EditText) findViewById(R.id.editTextNumber);
        text = (EditText) findViewById(R.id.editTextContent);


        checkPermissions();

        smsreceiver = new SmsBroadcastReceiver();
        registerReceiver(smsreceiver, new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), SendIntentService.class);
                intent.setAction(SendIntentService.ACTION_SEND);
                intent.putExtra(SendIntentService.EXTRA_PHONENUMBER, phoneNumber.getText().toString());
                intent.putExtra(SendIntentService.EXTRA_TEXT, text.getText().toString());
                startService(intent);
            }
        });

    }

    private void checkPermissions() {
        if(ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.READ_PHONE_STATE},
                    READ_EXTERNAL_STORAGE_PERMISSION_CODE);


        if(ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_SMS) == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.READ_SMS},
                    READ_EXTERNAL_STORAGE_PERMISSION_CODE);


        if(ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.RECEIVE_SMS},
                    READ_EXTERNAL_STORAGE_PERMISSION_CODE);


        if(ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.SEND_SMS},
                    READ_EXTERNAL_STORAGE_PERMISSION_CODE);
    }

    public class SmsBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive (Context context , Intent intent ) {
            SmsMessage[] msgs = Telephony.Sms.Intents.getMessagesFromIntent(intent) ;
            for (int i = 0; i < msgs.length; ++i) {
                String smsBody = msgs[i].getMessageBody().toString() ;
                String address = msgs[i].getOriginatingAddress();

                text.setText(smsBody);
                phoneNumber.setText(address);
            }
        }
    }
}

