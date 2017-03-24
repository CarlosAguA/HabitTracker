package com.example.android.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by Paviliondm4 on 3/21/2017.
 */

/*
 ** Steps for creating Contract class
 * 1. Outer class named HabitContract
 * 2. Inner class name HabitEntry for each table in the db.
 * 3. String constants for the table name and each of the headings.
 */

    //1.
public class HabitContract {

    //2.
    public static abstract class HabitEntry implements BaseColumns {

        //3.
        public static final String TABLE_NAME = "habits";

        public static final String COLUMN_HABIT_NAME = "name" ;
        public static final String COLUMN_HABIT_HOURS = "hours" ;
        public static final String COLUMN_HABIT_COST = "cost" ;
        public static final String COLUMN_HABIT_INCOME = "income" ;
        public static final String COLUMN_HABIT_DESCRIPTION = "description" ;
        public static final String _ID = BaseColumns._ID ;

    }

}
