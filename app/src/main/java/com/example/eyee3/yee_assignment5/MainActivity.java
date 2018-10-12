package com.example.eyee3.yee_assignment5;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    Toolbar myToolbar;
    TextView movieLabel;
    ListView movieList;
    String[] ListElements = new String[] {};
    final ArrayList<String> MoviesList = new ArrayList<String>(Arrays.asList(ListElements));
    DatabaseHelper mDatabaseHelper;
    private static final String TAG = "MainActivity";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        movieLabel = (TextView) findViewById(R.id.movieLabel);
        movieList = (ListView) findViewById(R.id.moviesList);
        mDatabaseHelper = new DatabaseHelper(this);

        myToolbar.setTitle("Movie Tracker");
        setSupportActionBar(myToolbar);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                Intent newMovie = new Intent(MainActivity.this, addMovie.class);
                startActivityForResult(newMovie, 1);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (MainActivity.this, android.R.layout.simple_list_item_1, MoviesList);
        movieList.setAdapter(adapter);

        if(requestCode == 1) {
            if(resultCode == Activity.RESULT_OK) {
                Log.d(TAG, "Displaying data in Listview");
                Cursor info = mDatabaseHelper.getData();
                while (info.moveToNext()) {
                    MoviesList.add(info.getString(1));
                    MoviesList.add(info.getString(2));
                    MoviesList.add(info.getString(3));
                }

            }
        }
    }
}
