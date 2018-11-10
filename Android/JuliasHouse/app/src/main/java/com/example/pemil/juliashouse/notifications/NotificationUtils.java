package com.example.pemil.juliashouse.notifications;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import java.util.List;

public class NotificationUtils {

    //TODO - change from Integer to Sit
    List<Integer> allSits;

    Context context;
    public static final String GROUP_KEY_NOTIFICATION = "com.example.pemil.juliashouse.notifications.NOTIFICATION";
    public static final String ID = "id";

    public NotificationUtils(Context ctx, List<Integer> sits) {
        context = ctx;
        allSits = sits;
    }

    public void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "channel_name";
            String description = "channel_description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("ceva", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void createNotificationGroup() {
        if (allSits != null) {
            int notificationCount = 0;
            for (int i = 0; i < allSits.size(); i++) {
                long timeForStartDate = getTimeUntilNotification(allSits[i]);
                long timeForEndDate = getTimeUntilNotification(allSits[i]);

                AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

                //check if needed 2 intents and 2 pendingIntents
                Intent notifyIntentforStart = new Intent(context, NotificationReceiver.class);
                notifyIntentforStart.putExtra(ID, notificationCount);
                PendingIntent pendingIntentforStart = PendingIntent.getBroadcast(context,
                        0,
                        notifyIntentforStart,
                        PendingIntent.FLAG_UPDATE_CURRENT);

                alarmManager.setExact(AlarmManager.RTC_WAKEUP, timeForStartDate, pendingIntentforStart);
                notificationCount++;

                Intent notifyIntentForEnd = new Intent(context, NotificationReceiver.class);
                notifyIntentforStart.putExtra(ID, notificationCount);
                PendingIntent pendintIntentForEnd = PendingIntent.getBroadcast(context,
                        0,
                        notifyIntentForEnd,
                        PendingIntent.FLAG_UPDATE_CURRENT);

                alarmManager.setExact(AlarmManager.RTC_WAKEUP, timeForEndDate, pendintIntentForEnd);
                notificationCount++;
            }
        }
    }

    //TODO - implement this method
    private int getTimeUntilNotification(int val) {
        return 0;
    }
}
