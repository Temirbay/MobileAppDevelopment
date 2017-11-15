package com.example.miras.tabletapp;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.graphics.Color;
import android.provider.CalendarContract;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;

import com.github.sundeepk.compactcalendarview.domain.Event;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class EventAddService extends IntentService {
    public static final String ACTION_ADDEVENT = "com.example.miras.tabletapp.action.ADDEVENT";

    public static final String EXTRA_PARAM1 = "com.example.miras.tabletapp.extra.PARAM1";

    public EventAddService() {
        super("EventAddService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADDEVENT.equals(action)) {
                Tablet param1 = (Tablet) intent.getExtras().getSerializable(EXTRA_PARAM1);
                handleActionAddEvent(param1);
            }
        }
    }

    private void handleActionAddEvent(Tablet tablet) {
        Calendar calendar = Calendar.getInstance();
        for (int j = 0; j < tablet.duration; ++j) {
            for (int i = 0; i < 24; i += (24 / tablet.frequency)) {
                Date date = calendar.getTime();
                date.setHours(i);
                date.setMinutes(0);
                date.setSeconds(0);
                Event newEvent = new Event(Color.GREEN, date.getTime(),
                        tablet.name + "\n" + tablet.description);
                CalendarActivity.events.add(newEvent);
                CalendarActivity.added.put(newEvent, false);
            }
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }
    }

}
