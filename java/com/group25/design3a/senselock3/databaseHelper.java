package com.group25.design3a.senselock3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Aranan on 5/18/2017.
 **/

public class databaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "UserDetails.db";
    public static final String TABLE_NAME = "User_Data";
    //Field details
    public static final String COL_1 = "Email";
    public static final String COL_2 = "Name";
    public static final String COL_3 = "Postal_Address";
    public static final String COL_4 = "Password";

    public databaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table" + TABLE_NAME + "(Email TEXT, Name TEXT, Postal_Address TEXT, Password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);

    }
}