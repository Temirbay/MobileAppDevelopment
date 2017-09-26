package com.example.miras.worldflagsgame;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {
    LinearLayout mainLayout;

    ArrayList<String> flagnames = new ArrayList<>();

    TextView name;
    TableLayout table;
    Button next;

    ImageButton costa;
    ImageButton germany;
    ImageButton france;
    ImageButton kazakhstan;
    ImageButton taiwan;
    ImageButton korea;
    ImageButton india;
    ImageButton russia;
    ImageButton kenia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = (LinearLayout)findViewById(R.id.mainLayout);
        name = new TextView(this);
        next = new Button(this);
        table = new TableLayout(this);

        name.setText("Next");
        mainLayout.addView(name);
        next.setVisibility(View.INVISIBLE);

        init();
        randomise();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                randomise();
            }
        });

        kazakhstan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().equals(kazakhstan.getTransitionName()))
                    correct();
            }
        });

        russia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().equals(russia.getTransitionName()))
                    correct();
            }
        });

        france.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().equals(france.getTransitionName()))
                    correct();
            }
        });

        germany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().equals(germany.getTransitionName()))
                    correct();
            }
        });

        kenia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().equals(kenia.getTransitionName()))
                    correct();
            }
        });

        taiwan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().equals(taiwan.getTransitionName()))
                    correct();
            }
        });

        korea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().equals(korea.getTransitionName()))
                    correct();
            }
        });

        india.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().equals(india.getTransitionName()))
                    correct();
            }
        });

        costa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().equals(costa.getTransitionName()))
                    correct();
            }
        });

    }

    public void correct() {
        Toast toast = Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT);
        next.setVisibility(View.VISIBLE);
    }

    private void randomise() {
        mainLayout.removeAllViews();
        table.removeAllViews();

        Random random = new Random(9);
        name.setText(flagnames.get(random.nextInt(9)));

        table = new TableLayout(this);
        TableRow row = new TableRow(this);

        random = new Random(9);
        for (int i = 1; i <= 9; ++i) {
            int x = random.nextInt(9);

            if (x == 0) row.addView(kazakhstan);
            else if (x == 1) row.addView(germany);
            else if (x == 2) row.addView(costa);
            else if (x == 3) row.addView(france);
            else if (x == 4) row.addView(russia);
            else if (x == 5) row.addView(kenia);
            else if (x == 6) row.addView(taiwan);
            else if (x == 7) row.addView(korea);
            else if (x == 8) row.addView(india);

            if (i % 3 == 0) {
                table.addView(row);
                row.removeAllViews();
            }
        }

        mainLayout.addView(name);
        mainLayout.addView(table);
        mainLayout.addView(next);
    }

    private void init() {
        germany = new ImageButton(this);
        france = new ImageButton(this);
        kazakhstan = new ImageButton(this);
        costa = new ImageButton(this);
        korea = new ImageButton(this);
        kenia = new ImageButton(this);
        russia = new ImageButton(this);
        taiwan = new ImageButton(this);
        india = new ImageButton(this);

        germany.setImageResource(R.drawable.germany);
        france.setImageResource(R.drawable.france);
        kazakhstan.setImageResource(R.drawable.kazakhstan);
        costa.setImageResource(R.drawable.costarica);
        korea.setImageResource(R.drawable.korea);
        kenia.setImageResource(R.drawable.kenia);
        russia.setImageResource(R.drawable.russia);
        taiwan.setImageResource(R.drawable.taiwan);
        india.setImageResource(R.drawable.india);

        germany.setTransitionName("Germany");
        france.setTransitionName("France");
        kazakhstan.setTransitionName("Kazakhstan");
        costa.setTransitionName("Costa-Rica");
        korea.setTransitionName("North-Korea");
        kenia.setTransitionName("Kenia");
        russia.setTransitionName("Russia");
        taiwan.setTransitionName("Taiwan");
        india.setTransitionName("India");

        flagnames.add(new String("Germany"));
        flagnames.add(new String("France"));
        flagnames.add(new String("Kazakhstan"));
        flagnames.add(new String("Costa-Rica"));
        flagnames.add(new String("North-Korea"));
        flagnames.add(new String("Kenia"));
        flagnames.add(new String("Russia"));
        flagnames.add(new String("Taiwan"));
        flagnames.add(new String("India"));
    }
}

