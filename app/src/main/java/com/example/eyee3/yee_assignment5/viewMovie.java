package com.example.eyee3.yee_assignment5;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

        myToolbar.setTitle("View Movie");
        setSupportActionBar(myToolbar);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
    }
}
