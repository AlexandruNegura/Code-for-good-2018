package com.example.pemil.juliashouse.notifications;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.example.pemil.juliashouse.Models.Sit;

import java.util.List;

public class NotificationUtils {

    private List<Sit> allSits;

    private Context context;
    static final String GROUP_KEY_NOTIFICATION = "com.example.pemil.juliashouse.notifications.NOTIFICATION";
    static final String ID = "id";
    static final String CHANNEL_ID = "notifications_channel";
    private static final String CHANNEL_NAME = "Sits";
    private static final String CHANNEL_DESCRIPTION = "Enable/Disable notifications for sits";

    public NotificationUtils(Context ctx, List<Sit> sits) {
        context = ctx;
        allSits = sits;
    }

    public void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);
            channel.setDescription(CHANNEL_DESCRIPTION);
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
                //TODO - get startTime and EndTime to compute the time till notification
                long timeForStartDate = getTimeUntilNotification(allSits.get(i));
                long timeForEndDate = getTimeUntilNotification(allSits.get(i));

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
        return val;
    }
}
