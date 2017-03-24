package com.example.android.habittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Paviliondm4 on 3/21/2017.
 */

/*
 ** Steps for creating DbHelper class
 * 1. Create a class that extends from SQLiteOpenHelper
 * 2. Create constants for database name and database version
 * 3. Create a constructor
 * 4. Implement onCreate() method
 * 5. Implement onUpgrade() method
 * 6. Create String that contains the SQL statement using contract constants to create habits table

/**
 * Database helper for Pets app. Manages database creation and version management.
 */
public class HabitDbHelper extends SQLiteOpenHelper {

    /** 2.
     * Database version. If you change the database schema, you must increment the database version.
     */
    public static final int DATABASE_VERSION = 1;

    /** 2. Name of the database file */
    public static final String DATABASE_NAME = "Lifestyle.db" ;

    /** 3.
     * Constructs a new instance of {@link HabitDbHelper}.
     *
     * @param context of the app
     */
    public HabitDbHelper (Context context){
        super(context,DATABASE_NAME , null , DATABASE_VERSION ) ;
    }

    /** 4.
     * This is called when the database is created for the first time.
     */
    public void onCreate(SQLiteDatabase db){

        //6. Create table statement using contract constants.
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_ENTRIES =  "CREATE TABLE " +  HabitContract.HabitEntry.TABLE_NAME + " ("
                + HabitContract.HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitContract.HabitEntry.COLUMN_HABIT_NAME  + " TEXT NOT NULL, "
                + HabitContract.HabitEntry.COLUMN_HABIT_HOURS + " INTEGER NOT NULL DEFAULT 0, "
                + HabitContract.HabitEntry.COLUMN_HABIT_COST + " INTEGER NOT NULL DEFAULT 0, "
                + HabitContract.HabitEntry.COLUMN_HABIT_INCOME + " INTEGER NOT NULL DEFAULT 0, "
                + HabitContract.HabitEntry.COLUMN_HABIT_DESCRIPTION + " TEXT);";

        // Execute a single SQL statement that is NOT a SELECT or any other SQL statement that returns data.
        db.execSQL(SQL_CREATE_ENTRIES) ;
    }

    /** 5.
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
