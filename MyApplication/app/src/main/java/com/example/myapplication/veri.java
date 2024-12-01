package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class veri extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "randevu";
    public static final String TABLE_NAME = "users";

    public veri(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (sac1 TEXT PRIMARY KEY, sac2 TEXT, sac3 TEXT, sac4 TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String sac1, String sac2, String sac3, String sac4) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("sac1", sac1);
        contentValues.put("sac2", sac2);
        contentValues.put("sac3", sac3);
        contentValues.put("sac4", sac4);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }



    public boolean checkIfDateExists(String selectedDateTime) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"sac1", "sac2", "sac3", "sac4"};
        String selection = "sac1 = ? OR sac2 = ? OR sac3 = ? OR sac4 = ?";
        String[] selectionArgs = {selectedDateTime, selectedDateTime, selectedDateTime, selectedDateTime};
        Cursor cursor = db.query("users", columns, selection, selectionArgs, null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

}
