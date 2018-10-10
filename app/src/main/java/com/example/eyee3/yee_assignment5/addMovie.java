package com.example.eyee3.yee_assignment5;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.support.v7.widget.Toolbar;

public class addMovie extends AppCompatActivity {

    Toolbar myToolbar;
    EditText name;
    EditText year;
    EditText filename;
    Button add;
    Button cancel;
    RelativeLayout newMovieParent;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        name = (EditText) findViewById(R.id.name);
        year = (EditText) findViewById(R.id.year);
        filename = (EditText) findViewById(R.id.filename);
        add = (Button) findViewById(R.id.add);
        cancel = (Button) findViewById(R.id.cancel);

        findViewById(R.id.newMovieParent).setOnTouchListener(new View.OnTouchListener() { //Makes keyboard disappear when user clicks outside of text boxes
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                return true;
            }
        });

        myToolbar.setTitle("New Movie");
        setSupportActionBar(myToolbar);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
        ActionBar ab = getSupportActionBar();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
