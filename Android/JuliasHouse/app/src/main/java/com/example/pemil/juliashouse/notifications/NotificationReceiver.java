package com.example.pemil.juliashouse.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NotificationReceiver extends BroadcastReceiver {

    public NotificationReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int id = intent.getIntExtra(NotificationUtils.ID, -1);
        Intent intent1 = new Intent(context, NotificationIntentService.class);
        intent1.putExtra(NotificationUtils.ID, id);
        context.startService(intent1);
    }
}
