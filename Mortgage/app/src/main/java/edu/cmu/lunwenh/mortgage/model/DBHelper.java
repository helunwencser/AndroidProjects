package edu.cmu.lunwenh.mortgage.model;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.Context;
/**
 * Created by lunwenh on 3/21/2016.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String INT_TYPE = " INTEGER";
    private static final String DOUBLE_TYPE = " DOUBLE";
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String DATABASE_NAME = "mortgage.db";

    /* create table */
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + DBModel.TABLE_NAME + " (" +
            DBModel._ID + " INTEGER AUTO_INCREMENT," +
            DBModel.PURCHASEPRICE + INT_TYPE + COMMA_SEP +
            DBModel.DOWNPAYMENT + INT_TYPE + COMMA_SEP +
            DBModel.MORTGAGETERM + INT_TYPE + COMMA_SEP +
            DBModel.INTERESTRATE + DOUBLE_TYPE + COMMA_SEP +
            DBModel.PROPERTYTAX + INT_TYPE + COMMA_SEP +
            DBModel.PROPERTYINSURANCE + INT_TYPE + COMMA_SEP +
            DBModel.PMI + DOUBLE_TYPE + COMMA_SEP +
            DBModel.ZIPCODE + TEXT_TYPE + COMMA_SEP +
            DBModel.FIRSTPAYMENTDATE + TEXT_TYPE + COMMA_SEP +
            DBModel.PAYOFFDATE + TEXT_TYPE + COMMA_SEP +
            DBModel.TOTALPAYMENT + DOUBLE_TYPE + COMMA_SEP +
            DBModel.TOTALMONTHLYPAYMENT + DOUBLE_TYPE + COMMA_SEP +
            "PRIMARY KEY (" + DBModel._ID + ")" +
    " )";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
