package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "yeni";

    public DBHelper(Context context) {
        super(context, "yeni", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(ktxt TEXT primary key , stxt TEXT, txt TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users ");
    }

    public Boolean insertData(String ktxt, String stxt, String txt) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ktxt", ktxt);
        contentValues.put("stxt", stxt);
        contentValues.put("txt", txt);

        long result = MyDB.insert("users", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Boolean checkusername(String ktxt) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users  where ktxt = ?", new String[]{ktxt});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String ktxt, String stxt, String txt) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users  where ktxt = ? and stxt= ?  and txt=?",  new String[]{ktxt, stxt,txt});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
}
