package com.example.eyee3.yee_assignment5;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.eyee3.yee_assignment5.service.MyLocalBinder;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    Toolbar myToolbar;
    TextView movieLabel;
    ListView movieList;
    service MovieService;
    boolean isBound = false;
    String[] ListElements = new String[] {};
    final ArrayList<String> MoviesList = new ArrayList<String>(Arrays.asList(ListElements));
    DatabaseHelper mDatabaseHelper;
    private static final String TAG = "MainActivity";

    private ServiceConnection movieConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyLocalBinder binder = (com.example.eyee3.yee_assignment5.service.MyLocalBinder) service;
            MovieService = binder.getService();
            isBound = true;
            //Toast.makeText(MainActivity.this, "Service Started", Toast.LENGTH_SHORT).show();

            //String[] stockPrice = MovieService.getStock();

            //code to do stuff
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
            Toast.makeText(MainActivity.this, "Service Stopped", Toast.LENGTH_SHORT).show();
        }
    };

    void doUnbindService() {
        if (isBound) {
            unbindService(movieConnection);
            Toast.makeText(MainActivity.this, "Service Stopped", Toast.LENGTH_SHORT).show();
            isBound = false;
        }
    }

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

        movieList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, service.class);

                String currentString = movieList.getItemAtPosition(position).toString();
                String [] separated = currentString.split(Pattern.quote("\n"));
                i.putExtra("name", separated[0]);
                i.putExtra("year", separated[1]);
                i.putExtra("file", separated[2]);
                bindService(i, movieConnection, Context.BIND_AUTO_CREATE);
                startService(i);

                //startActivityForResult(i, 2);*/
            }
        });

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



        if(requestCode == 1) {
            if(resultCode == Activity.RESULT_OK) {
                Log.d(TAG, "Displaying data in Listview");
                Cursor info = mDatabaseHelper.getData();
                ArrayList<String> listData = new ArrayList<>();

                while (info.moveToNext()) {
                    String nameReturn = info.getString(1);
                    String yearReturn = info.getString(2);
                    String fileReturn = info.getString(3);

                    listData.add(nameReturn + "\n" + yearReturn + "\n" + fileReturn);
                    //MoviesList.add(nameReturn + "\n" + yearReturn + "\n" + fileReturn);
                }
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>
                        (MainActivity.this, android.R.layout.simple_list_item_1, listData);
                movieList.setAdapter(adapter);
            }
        }
    }
}
