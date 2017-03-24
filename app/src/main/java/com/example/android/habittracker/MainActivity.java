package com.example.android.habittracker;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android.habittracker.data.HabitContract.HabitEntry;
import com.example.android.habittracker.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    /** Database helper that will provide us access to the database */
    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayDatabaseInfo();

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                insertPet();
                displayDatabaseInfo();

            }
        });

    }

    /**
     * Method that display information in the onscreen TextView about the state of
     * the habits database.
     */
    private void displayDatabaseInfo() {
      /************** READ  METHOD ****************/
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        mDbHelper = new HabitDbHelper(this);

        /* Create and/or open a database to read from it */
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        /* The projection means the columns the cursor object will return */
        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_NAME,
                HabitEntry.COLUMN_HABIT_HOURS,
                HabitEntry.COLUMN_HABIT_COST,
                HabitEntry.COLUMN_HABIT_INCOME ,
                HabitEntry.COLUMN_HABIT_DESCRIPTION};

        /* Query method : Create cursor object for retrieving the row of the table */
        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME,          // The table to query
                projection,                    // The columns to return
                null,
                null,
                null,
                null,
                null);

        /* Find the textView with ID text_view_habit */
        TextView displayView = (TextView) findViewById(R.id.text_view_habit);

        try {

            // Display the number of rows in the Cursor (which reflects the number of rows in the
            // habits table in the database).
            displayView.setText("The habits table contains " + cursor.getCount() + " habits.\n\n");

            //Append the columns that will be visualized in the textView.
            displayView.append(HabitEntry._ID + " - "
                    +  HabitEntry.COLUMN_HABIT_NAME
                    + " - " + HabitEntry.COLUMN_HABIT_HOURS
                    + " - " + HabitEntry.COLUMN_HABIT_COST
                    + " - " + HabitEntry.COLUMN_HABIT_INCOME
                    + " - " + HabitEntry.COLUMN_HABIT_DESCRIPTION + "\n") ;

            /* Figure out the index of each column (y axis ) */
            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID ) ;
            int nameColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_NAME) ;
            int hoursColumnIndex =  cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_HOURS) ;
            int costColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_COST) ;
            int incomeColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_INCOME) ;
            int descriptionColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_DESCRIPTION) ;

             /* Iterate until cursor.moveToNext() returns false */
            while (cursor.moveToNext()) {
                //Save the values form the individual db field in variables
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentHours = cursor.getString(hoursColumnIndex);
                String currentCost = cursor.getString(costColumnIndex);
                String currentIncome = cursor.getString(incomeColumnIndex);
                String currentDescription = cursor.getString(descriptionColumnIndex ) ;

                displayView.append(("\n"
                        + currentID + " - "
                        + currentName + " - "
                        + currentHours + " - "
                        + currentCost + " - "
                        + currentIncome + " - "
                        + currentDescription ) ) ;
            }

        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    /**
     * Method that inserts dummy Data in the Lifestyle database / habits table.
     */
    private void insertPet(){
      /************** INSERT  METHOD ****************/
       /* Create instance of HabitDbHelper and an db instance for Updating,Creating or Deleting*/
        mDbHelper = new HabitDbHelper(this);

       /* Gets the database in write mode */
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

       /* Create strings with dummy info, and a ContentValues object where column names are the keys */
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_NAME, "Swimming");
        values.put(HabitEntry.COLUMN_HABIT_HOURS, 3 );
        values.put(HabitEntry.COLUMN_HABIT_COST,  850 );
        values.put(HabitEntry.COLUMN_HABIT_INCOME, 0 );
        values.put(HabitEntry.COLUMN_HABIT_DESCRIPTION, "Three times per week one hour ");

        /* Insert a new row for Habit: Swimming in the database, returning the ID of that new row. */
        db.insert(HabitEntry.TABLE_NAME, null, values);

    }
}
