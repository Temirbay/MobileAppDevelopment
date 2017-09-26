package com.example.miras.guessthemeaning;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class AddWordActivity extends AppCompatActivity{

     Set<String> defaulValue = new HashSet<>();

    Button add;
    Button back;

    EditText word;
    EditText meaning;
    Boolean toadd = false;
    ArrayList<String> extra = new ArrayList<>();
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        add = (Button) findViewById(R.id.buttonAddWord);
        back = (Button) findViewById(R.id.button3);
        word = (EditText) findViewById(R.id.editTextWord);
        meaning = (EditText) findViewById(R.id.editTextMeaning);

        context = this.getApplicationContext();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                if (validate() == true) {
                    toadd = true;
                    String s = word.getText().toString() + "#" + meaning.getText().toString();
                    extra.add(s);
                    word.setText("");
                    meaning.setText("");
                }
                else {
                    Toast toast = Toast.makeText(getApplicationContext(),"Incorrect Input", Toast.LENGTH_SHORT);
                }

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                Intent intent = new Intent(getBaseContext(), StartMenuActivity.class);
                if (toadd == true) {
             //       intent.putExtra("word", extra);

                    SharedPreferences prefs = context.getSharedPreferences(
                            getResources().getString(R.string.myKey), MODE_PRIVATE);
                    SharedPreferences.Editor prefsEditor = prefs.edit() ;

                    Set<String> set = prefs.getStringSet("set", defaulValue);

                    for (String str : extra)
                        set.add(str);

        //            Log.d("miras111", prefsEditor.toString());
                    prefsEditor.putStringSet("set", set);
                    prefsEditor.apply();

                }
                startActivity(intent);
            }
        });
    }

    private boolean validate () {
        if (word.getText().toString() == ""
                || meaning.getText().toString() == "")  return false;
        return true;
    }

}