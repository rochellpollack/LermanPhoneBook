package com.example.rachel.lermanphonebook;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class LermanContactsDatabase extends SQLiteAssetHelper {

    //name and version of the database
    private static final String DATABASE_NAME = "LFDmini.db";
    private static final int DATABASE_VERSION = 1;



    public LermanContactsDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    /*
        returns the results of the query in a cursor object
     */
    public Cursor getContacts() {

        //opens the database
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        //columns to be returned by the query
        String [] sqlSelect = {"0 _id ", "FirstName", "LastName"};
        String sqlTables = "contacts";

        qb.setTables(sqlTables);
        Cursor c = qb.query(db, sqlSelect, null, null,
                null, null, null);

        c.moveToFirst();
        return c;

    }


}
