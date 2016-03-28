package edu.cmu.lunwenh.scorestatistics.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lunwenh on 3/27/2016.
 */
public class DBHelper extends SQLiteOpenHelper{
    private static final String INT_TYPE = " INTEGER";
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String DBTABASE_NAME = "scores.db";

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " +
            DBModel.TABLE_NAME + " (" +
            DBModel._ID + " INTEGER AUTO_INCREMENT," +
            DBModel.ID + TEXT_TYPE + COMMA_SEP +
            DBModel.Q1 + INT_TYPE + COMMA_SEP +
            DBModel.Q2 + INT_TYPE + COMMA_SEP +
            DBModel.Q3 + INT_TYPE + COMMA_SEP +
            DBModel.Q4 + INT_TYPE + COMMA_SEP +
            DBModel.Q5 + INT_TYPE +
            ")";
    public DBHelper(Context context) {
        super(context, DBTABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
