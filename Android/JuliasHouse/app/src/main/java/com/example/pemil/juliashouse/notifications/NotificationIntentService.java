package com.example.pemil.juliashouse.notifications;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.example.pemil.juliashouse.R;

public class NotificationIntentService extends IntentService {
    public NotificationIntentService() {
        super("NotificationIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //TODO - modify EmptyActivity to SitActivity when created
        Intent newIntent = new Intent(NotificationIntentService.this, SitActivity.class);
        newIntent.putExtra("test", "test");
        newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        //TODO - change request code to UNIQUE ID
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, newIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        //TODO - modify notification based on current sit - TO GET EXTRA
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, "ceva")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("titlu")
                .setContentText("context")
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setGroup(NotificationUtils.GROUP_KEY_NOTIFICATION)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // notificationId is a unique int for each notification that you must define
        //TODO - change id to UNIQUE ID
        notificationManager.notify(1234, mBuilder.build());
    }
}
