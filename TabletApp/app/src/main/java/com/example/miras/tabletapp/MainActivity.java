package com.example.miras.tabletapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.concurrent.RunnableFuture;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MAIN";

    private ProgressDialog progressDialog;
    private static String url = "http://www.json-generator.com/api/json/get/ceKGTHXtfm?indent=2";

    private ListView lv;
    private Button bn;

    ArrayList<HashMap<String, String> > tabletsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.listView);
        bn = (Button) findViewById(R.id.button);

        tabletsList = new ArrayList<>();

        new GetTablets().execute();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HashMap<String, String> cur = tabletsList.get(i);

                Tablet curTablet = new Tablet();
                curTablet.setName(cur.get("name"));
                curTablet.setType(cur.get("type"));
                curTablet.setDescription(cur.get("description"));
                curTablet.setDose(cur.get("dose"));
                curTablet.setQuantity(cur.get("quantity"));
                curTablet.setFrequency(cur.get("frequency"));
                curTablet.setDuration(cur.get("duration"));


                Intent intent = new Intent(getBaseContext(), EventAddService.class);
                intent.setAction(EventAddService.ACTION_ADDEVENT);
                intent.putExtra(EventAddService.EXTRA_PARAM1, curTablet);
                startService(intent);

            }
        });

        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), CalendarActivity.class);
                intent.putExtra("tablet", (Tablet)null);
                startActivity(intent);
            }
        });

        /*android.support.v4.app.NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setContentTitle("TAKE A TABLET")
                .setContentText("test")
                .setSmallIcon(android.R.drawable.ic_delete);

        Notification notification = builder.build();
        NotificationManager  manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(0, notification);*/


    }

    private class GetTablets extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            HttpHandler sh = new HttpHandler();
            String jsonStr = sh.makeServiceCall(url);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObject = new JSONObject(jsonStr);
                    JSONArray tablets = jsonObject.getJSONArray("tablets");

                    for (int i = 0; i < tablets.length(); ++i) {
                        JSONObject cur = tablets.getJSONObject(i);
                        String name = cur.getString("name");
                        String type = cur.getString("type");
                        String dose = cur.getString("dose");
                        String description = cur.getString("description");
                        String quantity = cur.getString("quantity");
                        String frequency = cur.getString("frequency");
                        String duration = cur.getString("duration");

                        HashMap<String, String> tablet = new HashMap<>();
                        tablet.put("name", name);
                        tablet.put("type", type);
                        tablet.put("dose", dose);
                        tablet.put("description", description);
                        tablet.put("quantity", quantity);
                        tablet.put("frequency", frequency);
                        tablet.put("duration", duration);

                        tabletsList.add(tablet);
                    }

                } catch (final JSONException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this,
                                    "JSon parsing error" + e.getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
            else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this,
                                "Could'n get from server",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this,
                    tabletsList,
                    R.layout.list_item,
                    new String[]{"name", "type", "dose", "description",
                            "quantity", "frequency", "duration"},
                    new int[] {R.id.name, R.id.type, R.id.dose, R.id.description,
                            R.id.quantity, R.id.frequency, R.id.duration}
            );

            lv.setAdapter(adapter);
        }
    }
}
