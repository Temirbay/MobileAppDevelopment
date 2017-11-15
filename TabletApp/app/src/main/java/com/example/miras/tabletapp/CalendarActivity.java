package com.example.miras.tabletapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {

    private static int uniqueID = 0;
    private static Tablet tablet;
    public CompactCalendarView calendar;
    private ListView listView;
    public static List<Event> events = new ArrayList<>();
    public List<String> eventsShow;
    public ArrayAdapter<String> adapter;
    public static HashMap<Event, Boolean> added = new HashMap<>();
    NotificationManager manager;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Intent intent = getIntent();
        tablet = (Tablet) intent.getExtras().getSerializable("tablet");

        listView = (ListView)findViewById(R.id.listView);
        calendar = (CompactCalendarView) findViewById(R.id.calendar);

        calendar.setFirstDayOfWeek(Calendar.MONDAY);

        eventsShow = new ArrayList<>();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, eventsShow);
        listView.setAdapter(adapter);

        calendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                eventsShow.clear();
                for (int i = 0; i < events.size(); ++i) {
                    Calendar cal1 = Calendar.getInstance();
                    Calendar cal2 = Calendar.getInstance();
                    cal1.setTimeInMillis(events.get(i).getTimeInMillis());
                    cal2.setTimeInMillis(dateClicked.getTime());

                    int d1 = cal1.get(Calendar.DAY_OF_MONTH);
                    int m1 = cal1.get(Calendar.MONTH);
                    int y1 = cal1.get(Calendar.YEAR);

                    int d2 = cal2.get(Calendar.DAY_OF_MONTH);
                    int m2 = cal2.get(Calendar.MONTH);
                    int y2 = cal2.get(Calendar.YEAR);

                    int h1 = cal1.get(Calendar.HOUR_OF_DAY);
                    int mi1 = cal1.get(Calendar.MINUTE);

                    SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                    Date date = cal1.getTime();
                    if (d1 == d2 && m1 == m2 && y1 == y2) {
                        eventsShow.add(format.format(date) +  "\n" + events.get(i).getData().toString());
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {

            }
        });

        manager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);
            addScheduleToCalendar();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addScheduleToCalendar() {
        for (int i = 0; i < events.size(); ++i) {
            if (added.get(events.get(i)) == true) continue;
            Log.d("MAIN", "sadasd");

            NotificationManager manager = (NotificationManager)
                    getSystemService(Context.NOTIFICATION_SERVICE);

            Notification.Builder builder = new Notification.Builder(getApplicationContext())
                    .setContentTitle("TAKE A TABLET")
                    .setContentText(events.get(i).getData().toString())
                    .setAutoCancel(true)
                    .setSmallIcon(R.drawable.icon32);

            Notification notification = builder.build();
            manager.notify(uniqueID++, notification);

            added.put(events.get(i), true);
        }
    }


    public static void addEvents (List<Event> list) {
        for (int i = 0; i < list.size(); ++i) {
            events.add(list.get(i));
            added.put(events.get(i), false);
        }
    }
}
