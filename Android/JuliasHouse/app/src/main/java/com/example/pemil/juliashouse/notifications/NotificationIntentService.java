package com.example.pemil.juliashouse.notifications;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.pemil.juliashouse.R;
import com.example.pemil.juliashouse.SitActivity;

import org.json.JSONObject;

public class NotificationIntentService extends IntentService {
    public NotificationIntentService() {
        super("NotificationIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
//        Boolean isHomeSafeValid = intent.getBooleanExtra(NotificationUtils.HOME_SAFE, false);
//        if (isHomeSafeValid != null) {
//            Boolean getValueFromDB = checkIfHomeSafe()
//        }

        createNotification(intent);
    }

    private void createNotification(Intent intent) {
        int id = intent.getIntExtra(NotificationUtils.ID, -1);
        //DONE - modify EmptyActivity to SitActivity when created
        Intent newIntent = new Intent(NotificationIntentService.this, SitActivity.class);
        newIntent.putExtra(NotificationUtils.ID, id / 3);
        newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        //DONE - change request code to UNIQUE ID
        PendingIntent pendingIntent = PendingIntent.getActivity(this, id, newIntent, 0);
        //DONE - modify notification based on current sit - TO GET EXTRA
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, NotificationUtils.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Sit no" + id)
                .setContentText("context for " + id)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setGroup("sit")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // notificationId is a unique int for each notification that you must define
        //DONE - change id to UNIQUE ID
        notificationManager.notify(id, mBuilder.build());
    }

    private boolean checkIfHomeSafe(long id) {
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        String requestUrl = "https://code-for-good.herokuapp.com/api/user/login";
        JSONObject postparams = new JSONObject();
        return false;
    }
}
