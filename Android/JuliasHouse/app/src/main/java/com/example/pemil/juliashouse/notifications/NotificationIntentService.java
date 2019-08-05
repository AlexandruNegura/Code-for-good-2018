package com.example.pemil.juliashouse.notifications;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pemil.juliashouse.R;
import com.example.pemil.juliashouse.SitActivity;

import java.util.HashMap;
import java.util.Map;

public class NotificationIntentService extends IntentService {
    private SharedPreferences sharedPref;
    private boolean isNotHome = false;
    private RequestQueue requestQueue;

    public NotificationIntentService() {
        super("NotificationIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        int sitId = intent.getIntExtra(NotificationUtils.ID, -1);
        sharedPref = getApplicationContext().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        int id = sharedPref.getInt("id", -1);

        Boolean isHomeSafeValid = intent.getBooleanExtra(NotificationUtils.HOME_SAFE, false);
        if (isHomeSafeValid != null && !isHomeSafeValid) {
            checkIfHomeSafe(sitId, id, intent);
            if (isNotHome) {
                createNotification(sitId, intent);
            }
        } else {
            createNotification(sitId, intent);
        }
    }

    private void createNotification(int id, Intent intent) {

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

    private void checkIfHomeSafe(long idSit, long idUser, Intent intent) {
        String requestUrl = "https://code-for-good.herokuapp.com/api/notification/alert-not-home/" + idUser + "/" + idSit;
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                requestUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("SCUCCCEES", response.toString());
                        isNotHome = Boolean.valueOf(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Failure Callback
                        Log.i("ErrorRRRRR", error.toString());
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                return params;
            }
        };
        // Adding the request to the queue along with a unique string tag
        getRequestQueue().add(stringRequest);
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        return requestQueue;
    }
}
