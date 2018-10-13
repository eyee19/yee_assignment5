package com.example.eyee3.yee_assignment5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.DatabaseErrorHandler;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "movieTable";
    private static final String COL1 = "ID";
    private static final String COL2 = "Name";
    private static final String COL3 = "Year";
    private static final String COL4 = "Filename";

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL2 + " TEXT NOT NULL, "
                + COL3 + " TEXT NOT NULL, "
                + COL4 + " TEXT NOT NULL)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String item, String item3, String item4) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item);
        contentValues.put(COL3, item3);
        contentValues.put(COL4, item4);

        Log.d(TAG, "addData: Adding " + item + ", " + item3 + ", " + item4 + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void remove(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        //String string = String.valueOf(id);
        db.execSQL("DELETE FROM movieTable WHERE'" + COL1 + "= id'");
        Log.d(TAG, "THE ID: " + id);
        Log.d(TAG, "THE COLUMN ID: " + COL1);
    }
}
