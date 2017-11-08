package com.example.miras.smsapp;

import android.Manifest;
import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;

public class SendIntentService extends IntentService {

    public static final String ACTION_SEND = "com.example.miras.smsapp.action.SEND";

    public static final String EXTRA_PHONENUMBER = "com.example.miras.smsapp.extra.PHONENUMBER";
    public static final String EXTRA_TEXT = "com.example.miras.smsapp.extra.TEXT";


    public SendIntentService() {
        super("SendIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_SEND.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PHONENUMBER);
                final String param2 = intent.getStringExtra(EXTRA_TEXT);
                handleActionSend(param1, param2);
            }
        }
    }

    private void handleActionSend(String param1, String param2) {
        SmsManager mgr = SmsManager.getDefault () ;
        mgr.sendTextMessage (param1, null, param2, null, null);
    }
}
