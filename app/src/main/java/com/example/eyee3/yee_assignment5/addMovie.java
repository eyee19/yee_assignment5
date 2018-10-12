package com.example.eyee3.yee_assignment5;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

public class addMovie extends AppCompatActivity {

    Toolbar myToolbar;
    EditText name;
    EditText year;
    EditText filename;
    Button add;
    Button cancel;
    RelativeLayout newMovieParent;
    private static final String TAG = "addMovie";

    DatabaseHelper mDatabaseHelper;

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
        mDatabaseHelper = new DatabaseHelper(this);

        findViewById(R.id.newMovieParent).setOnTouchListener(new View.OnTouchListener() { //Makes keyboard disappear when user clicks outside of text boxes
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                return true;
            }
        });

        myToolbar.setTitle("Add Movie");
        setSupportActionBar(myToolbar);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = name.getText().toString();
                String newEntry3 = year.getText().toString();
                String newEntry4 = filename.getText().toString();

                if (name.length() != 0 || year.length() != 0 || filename.length() != 0) {
                    AddData(newEntry, newEntry3, newEntry4);
                    Intent intent = new Intent(addMovie.this, MainActivity.class);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
                else {
                    Toast.makeText(addMovie.this, "Name/Year/Filename cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void AddData(String newEntry, String newEntry3, String newEntry4) {
        boolean insertData = mDatabaseHelper.addData(newEntry, newEntry3, newEntry4);

        if (insertData) {
            Toast.makeText(addMovie.this, "Movie Successfully Added", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(addMovie.this, "Oh no! Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }
}
