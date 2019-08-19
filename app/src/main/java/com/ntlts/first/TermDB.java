package com.ntlts.first;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class TermDB {
    private TermDB(){}
    public static class TermEntry implements BaseColumns {
        public static final String TABLE_NAME = "term";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_START_DATE = "startDate";
    }


}
