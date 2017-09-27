package com.example.miras.worldflagsgame;

import android.content.DialogInterface;
import android.media.Image;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {
    LinearLayout mainLayout;

    ArrayList<String> flagnames;

    Map<String, String> descriptions = new HashMap<>();
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
    ImageButton kenya;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = (LinearLayout)findViewById(R.id.mainLayout);

        randomise();
        readAllFiles();
    }

    public void correct() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Correct");
        builder.setMessage(descriptions.get(name.getText()));

        builder.setNeutralButton("Got it", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.setIcon(R.drawable.correct);
        AlertDialog dialog = builder.create();
        dialog.show();
        next.setVisibility(View.VISIBLE);
    }
    public void incorrect() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Incorrect");
        builder.setMessage("OOOPS...Try AGAIN...");

        builder.setNeutralButton("Got it", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setIcon(R.drawable.incorrect);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void randomise() {
        mainLayout.removeAllViews();
        init();

        Random random = new Random();
        name.setText(flagnames.get(random.nextInt(9)));
        next.setText("Next");

        TableRow row = new TableRow(this);

        Boolean[] used = new Boolean[9];
        for (int i = 0; i < 9; ++i) used[i] = false;

        Random randomGenerator = new Random();
        for (int i = 1; i <= 9; ++i) {
            int x = randomGenerator.nextInt(9);
            while (used[x] == true)
                x = randomGenerator.nextInt(9);
            used[x] = true;
            Log.d("str", String.valueOf(x));

            if (x == 0) row.addView(kazakhstan);
            else if (x == 1) row.addView(germany);
            else if (x == 2) row.addView(costa);
            else if (x == 3) row.addView(france);
            else if (x == 4) row.addView(russia);
            else if (x == 5) row.addView(kenya);
            else if (x == 6) row.addView(taiwan);
            else if (x == 7) row.addView(korea);
            else if (x == 8) row.addView(india);

            if (i % 3 == 0) {
                table.addView(row);
                row = new TableRow(this);
            }
        }

        mainLayout.addView(name);
        mainLayout.addView(table);
        mainLayout.addView(next);
    }

    private void init() {
        flagnames = new ArrayList<>();
        name = new TextView(this);
        next = new Button(this);
        table = new TableLayout(this);

        germany = new ImageButton(this);
        france = new ImageButton(this);
        kazakhstan = new ImageButton(this);
        costa = new ImageButton(this);
        korea = new ImageButton(this);
        kenya = new ImageButton(this);
        russia = new ImageButton(this);
        taiwan = new ImageButton(this);
        india = new ImageButton(this);

        germany.setAdjustViewBounds(true);
        kazakhstan.setAdjustViewBounds(true);
        france.setAdjustViewBounds(true);
        costa.setAdjustViewBounds(true);
        korea.setAdjustViewBounds(true);
        kenya.setAdjustViewBounds(true);
        taiwan.setAdjustViewBounds(true);
        india.setAdjustViewBounds(true);
        russia.setAdjustViewBounds(true);


        TableRow.LayoutParams rowParams = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        rowParams.weight = 1;
        next.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        name.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        table.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        table.setWeightSum(3);

        next.setVisibility(View.INVISIBLE);
        name.setTextSize(50);


        kazakhstan.setLayoutParams(rowParams);
        germany.setLayoutParams(rowParams);
        costa.setLayoutParams(rowParams);
        france.setLayoutParams(rowParams);
        russia.setLayoutParams(rowParams);
        kenya.setLayoutParams(rowParams);
        taiwan.setLayoutParams(rowParams);
        korea.setLayoutParams(rowParams);
        india.setLayoutParams(rowParams);

        germany.setImageResource(R.drawable.germany);
        france.setImageResource(R.drawable.france);
        kazakhstan.setImageResource(R.drawable.kazakhstan);
        costa.setImageResource(R.drawable.costarica);
        korea.setImageResource(R.drawable.korea);
        kenya.setImageResource(R.drawable.kenia);
        russia.setImageResource(R.drawable.russia);
        taiwan.setImageResource(R.drawable.taiwan);
        india.setImageResource(R.drawable.india);

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
                else
                    incorrect();
            }
        });

        russia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().equals(russia.getTransitionName()))
                    correct();
                else
                    incorrect();
            }
        });

        france.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().equals(france.getTransitionName()))
                    correct();
                else
                    incorrect();
            }
        });

        germany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().equals(germany.getTransitionName()))
                    correct();
                else
                    incorrect();
            }
        });

        kenya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().equals(kenya.getTransitionName()))
                    correct();
                else
                    incorrect();
            }
        });

        taiwan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().equals(taiwan.getTransitionName()))
                    correct();
                else
                    incorrect();

            }
        });

        korea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().equals(korea.getTransitionName()))
                    correct();
                else
                    incorrect();
            }
        });

        india.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().equals(india.getTransitionName()))
                    correct();
                else
                    incorrect();
            }
        });

        costa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().equals(costa.getTransitionName()))
                    correct();
                else
                    incorrect();
            }
        });

        germany.setTransitionName("Germany");
        france.setTransitionName("France");
        kazakhstan.setTransitionName("Kazakhstan");
        costa.setTransitionName("Costa-Rica");
        korea.setTransitionName("North-Korea");
        kenya.setTransitionName("Kenya");
        russia.setTransitionName("Russia");
        taiwan.setTransitionName("Taiwan");
        india.setTransitionName("India");

        flagnames.add(new String("Germany"));
        flagnames.add(new String("France"));
        flagnames.add(new String("Kazakhstan"));
        flagnames.add(new String("Costa-Rica"));
        flagnames.add(new String("North-Korea"));
        flagnames.add(new String("Kenya"));
        flagnames.add(new String("Russia"));
        flagnames.add(new String("Taiwan"));
        flagnames.add(new String("India"));
    }


    public void readAllFiles() {
        try {
            InputStream is = getResources().openRawResource(R.raw.description);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String curline;
            if (is == null) return;
            while ((curline = br.readLine()) != null) {
                String[] pair = curline.split("#");
                descriptions.put(pair[0], pair[1]);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

