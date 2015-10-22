package com.knechtrickayzenpoletin.mirschmecktz;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by normal on 22.10.2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String _ID = BaseColumns._ID;
    public static final String TABLE_NAME = "meals";
    public static final String COLUMN_MEAL ="meal";
    public static final String COLUMN_SCHMECKT = "schmeckt";

    private static final String DATABASE_NAME = "my_meals.db";
    private static final int DATABASE_VERSION = 1;

    //create database
    private static final String DATABASE_CREATE = "create table " + TABLE_NAME + " ( " +
            _ID + " integer primary key autoincrement, " +
            COLUMN_MEAL + " text not null, " +
            COLUMN_SCHMECKT + " boolean" +");";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // TODO Auto-generated method stub

        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // TODO Auto-generated method stub
        Log.w(DatabaseHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

}
