package com.example.p3.redevent.activity;

/**
 * Created by Nati on 26/05/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    final static String TABLE_NAME = "users";
    final static String LOGIN = "login";
    final static String PASS = "pass";
    final static String TELL = "fone";
   // final static String DESCRIPT = "descricao";
    //final static String ARTIST_STYLE...
    final static String _ID = "_id";
    final static String[] columns = { _ID, LOGIN };

    final private static String CREATE_CMD =

            "CREATE TABLE "+TABLE_NAME+" (" + _ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + LOGIN +" TEXT NOT NULL,"
                    + PASS +" TEXT NOT NULL,"
                    + TELL + " TEXT NOT NULL)";
     //               +DESCRIPT+" TEXT NOT NULL)";

    final private static String NAME = "user_db";
    final private static Integer VERSION = 1;
    final private Context mContext;

    public DatabaseOpenHelper(Context context) {
        super(context, NAME, null, VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CMD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // N/A
    }

    void deleteDatabase() {
        mContext.deleteDatabase(NAME);
    }
}
