package com.example.eyee3.yee_assignment5;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class viewMovie extends AppCompatActivity {

    Toolbar myToolbar;
    TextView name;
    TextView year;
    TextView filename;
    Button stopPlaying;
    Button delete;
    Button back;
    private static final String TAG = "viewMovie";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_movie);
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        name = (TextView) findViewById(R.id.nameAdd);
        year = (TextView) findViewById(R.id.yearAdd);
        filename = (TextView) findViewById(R.id.fileAdd);
        stopPlaying = (Button) findViewById(R.id.stopPlaying);
        delete = (Button) findViewById(R.id.delete);
        back = (Button) findViewById(R.id.back);

        myToolbar.setTitle("View Movie");
        setSupportActionBar(myToolbar);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));

        Intent receivedInfo = getIntent();
        String setName = receivedInfo.getStringExtra("viewName");
        String setYear = receivedInfo.getStringExtra("viewYear");
        String setFile = receivedInfo.getStringExtra("viewFile");

        name.setText(setName);
        year.setText(setYear);
        filename.setText(setFile);

        stopPlaying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(viewMovie.this, service.class));

                ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
                for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
                    if ("com.example.eyee3.yee_assignment5".equals(service.service.getClassName())) {
                        Log.d(TAG, "SERVICE IS RUNNING");
                    }
                    else {
                        Log.d(TAG, "SERVICE HAS STOPPED");
                    }
                }
                NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.cancel("serviceNotification",101);
                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
