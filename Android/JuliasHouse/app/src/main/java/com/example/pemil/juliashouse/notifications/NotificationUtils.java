package com.example.pemil.juliashouse.notifications;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.example.pemil.juliashouse.Models.Sit;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NotificationUtils {

    static final String HOME_SAFE = "home_safe";
    private List<Sit> allSits;

    private Context context;
    public static final String ID = "id";
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
            int countTimes = 0;
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            for (int i = 0; i < allSits.size(); i++) {
                // get startTime and EndTime to compute the time till notification
                long timeForStartDate = getTimeUntilNotification(allSits.get(i).getStartDate());
                long timeForEndDate = getTimeUntilNotification(allSits.get(i).getEndDate());

                //code for the start time
                Intent notifyIntentforStart = new Intent(context, NotificationReceiver.class);
                notifyIntentforStart.putExtra(ID, countTimes);
                PendingIntent pendingIntentforStart = PendingIntent.getBroadcast(context,
                        countTimes,
                        notifyIntentforStart,
                        0);

                countTimes++;
                alarmManager.set(AlarmManager.RTC_WAKEUP, timeForStartDate, pendingIntentforStart);

                //end time
                Intent notifyIntentForEnd = new Intent(context, NotificationReceiver.class);
                notifyIntentForEnd.putExtra(ID, countTimes);
                PendingIntent pendintIntentForEnd = PendingIntent.getBroadcast(context,
                        countTimes,
                        notifyIntentForEnd,
                        0);
                countTimes++;

                alarmManager.set(AlarmManager.RTC_WAKEUP, timeForEndDate, pendintIntentForEnd);

                //2 hours after end time
                Intent notifyIntentForHomeSafe = new Intent(context, NotificationReceiver.class);
                notifyIntentForHomeSafe.putExtra(ID, countTimes);
                notifyIntentForHomeSafe.putExtra(HOME_SAFE, false);
                PendingIntent pendintIntentForHomeSafe = PendingIntent.getBroadcast(context,
                        countTimes,
                        notifyIntentForHomeSafe,
                        0);
                countTimes++;

                alarmManager.set(AlarmManager.RTC_WAKEUP, timeForEndDate + 1000 * 60 * 60 * 2, pendintIntentForHomeSafe);
            }
        }
    }

    private long getTimeUntilNotification(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }
}
