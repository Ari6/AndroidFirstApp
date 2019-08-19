package com.ntlts.first;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TermDBHelper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_ENTRRIES =
            "CREATE TABLE " + TermDB.TermEntry.TABLE_NAME + " (" +
                    TermDB.TermEntry._ID + " INTEGER PRIMARY KEY," +
                    TermDB.TermEntry.COLUMN_NAME_TITLE + " TEXT," +
                    TermDB.TermEntry.COLUMN_NAME_START_DATE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TermDB.TermEntry.TABLE_NAME;

    //Do not forget to increment
    // the database version when you update the schema.

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Term.db";

    public TermDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) throws SQLException {
        db.execSQL(SQL_CREATE_ENTRRIES);
        ContentValues values = new ContentValues();
        values.put(TermDB.TermEntry.COLUMN_NAME_TITLE, "Default");
        values.put(TermDB.TermEntry.COLUMN_NAME_START_DATE, "2019-08-19 12:34:56");
        long newRowId = db.insert(TermDB.TermEntry.TABLE_NAME, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngreade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }
}