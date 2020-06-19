package com.lavishly.android;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;

import com.lavishly.android.SubsidiaryActivities.FancyActivity;

public class NotificationReceiverActivity extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent RepeatingIntent = new Intent(context, FancyActivity.class);
        RepeatingIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context,100, RepeatingIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.upward_right_arrow)
                .setContentTitle("Fancy")
                .setContentText("What Do You Fancy Today")
                .setAutoCancel(true);

        notificationManager.notify(100,builder.build());

    }
}