package com.example.eyee3.yee_assignment5;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import static android.content.Context.NOTIFICATION_SERVICE;

public class service extends Service {
    private NotificationManager mNM;
    private String TAG = "LocalService";
    private  final IBinder serviceBinder = new MyLocalBinder();

    public service() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return serviceBinder;
    }

    public class MyLocalBinder extends Binder {
        service getService() {
            return service.this;
        }
    }

    @Override
    public void onCreate() {
        mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
    }

    private static final String TAGN = "serviceNotification";
    private static final int NOTIFICATION_ID = 101;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "SERVICE HAS STARTED");

        String name = intent.getStringExtra("name");
        String year = intent.getStringExtra("year");
        String file = intent.getStringExtra("file");

        CharSequence text = "Click to view details";
        CharSequence title = name + " (" + year + ")" + " is now playing";

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(service.this, "Chapman")
                .setSmallIcon(R.drawable.ic_play_arrow_black_24dp) // notification icon
                .setContentTitle(title) // title for notification
                .setContentText(text)// message for notification
                .setAutoCancel(false); // clear notification after click

        Intent intent2 = new Intent(service.this, viewMovie.class);
        intent2.putExtra("viewName", name);
        intent2.putExtra("viewYear", year);
        intent2.putExtra("viewFile", file);
        PendingIntent pi = PendingIntent.getActivity(service.this, 0, intent2, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pi);
        mNotificationManager.notify(TAGN, NOTIFICATION_ID, mBuilder.build());

        return START_STICKY;
    }

    /*private void showNotification() {
    }*/
}
