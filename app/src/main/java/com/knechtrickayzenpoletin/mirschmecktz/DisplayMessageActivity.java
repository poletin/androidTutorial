package com.knechtrickayzenpoletin.mirschmecktz;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import static java.security.AccessController.getContext;

public class DisplayMessageActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.animator.up_down, R.animator.down_up);
        setContentView(R.layout.activity_display_message);

    }

    public void onSaveEntry(View view){
        EditText textField = (EditText) findViewById(R.id.editText2);
        String text = textField.getText().toString();
        RadioButton schmecktButton = (RadioButton) findViewById(R.id.radioButtonSchmeckt);
        boolean schmeckt = schmecktButton.isChecked();
        try
        {
            DatabaseHelper DBhelper = new DatabaseHelper(getBaseContext());
            //put DB in write mode
            SQLiteDatabase db = DBhelper.getWritableDatabase();
            //put variables
            ContentValues values = new ContentValues();
            //values.put(DatabaseHelper.COLUMN_ID, 1);
            values.put(DatabaseHelper.COLUMN_MEAL, text);
            values.put(DatabaseHelper.COLUMN_SCHMECKT, schmeckt);

            //insert variables into DB
            long query = db.insert(DatabaseHelper.TABLE_NAME, null, values);

            //close DB
            db.close();
            Snackbar.make(view, "meal saved", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
        }
        catch(Exception e)
        {
            System.out.println("Error: "+e.getLocalizedMessage());
        }
    }

    public void onTestReadDB(View view){
        DatabaseHelper mDbHelper = new DatabaseHelper(getBaseContext());
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                DatabaseHelper._ID,
                DatabaseHelper.COLUMN_MEAL,
                DatabaseHelper.COLUMN_SCHMECKT
        };

// How you want the results sorted in the resulting Cursor
//        String sortOrder =
//                FeedEntry.COLUMN_NAME_UPDATED + " DESC";

        Cursor cursor = db.query(
                DatabaseHelper.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );
        cursor.moveToFirst();
        do{
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(mDbHelper._ID)
            );
            String meal = cursor.getString(cursor.getColumnIndex(mDbHelper.COLUMN_MEAL));
            int schmeckt = cursor.getInt(cursor.getColumnIndex(mDbHelper.COLUMN_SCHMECKT));
            System.out.println(itemId + " "+ meal+ " "+ schmeckt);
        }while (cursor.moveToNext());
    }

}
