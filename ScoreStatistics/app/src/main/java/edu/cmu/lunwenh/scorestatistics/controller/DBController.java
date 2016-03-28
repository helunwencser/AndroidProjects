package edu.cmu.lunwenh.scorestatistics.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import edu.cmu.lunwenh.scorestatistics.model.DBHelper;
import edu.cmu.lunwenh.scorestatistics.model.DBModel;
import edu.cmu.lunwenh.scorestatistics.model.Record;

/**
 * Created by lunwenh on 3/27/2016.
 */
public class DBController {
    private DBHelper dbHelper = null;

    public DBController(Context context) {
        if(dbHelper == null) {
            dbHelper = new DBHelper(context);
        }
    }

    /**
     * insert one record to databse
     * @param record
     *        the object contains one reocrd
     * */
    public void insertRecord(Record record) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBModel.ID, record.getId());
        contentValues.put(DBModel.Q1, record.getQ1());
        contentValues.put(DBModel.Q2, record.getQ2());
        contentValues.put(DBModel.Q3, record.getQ3());
        contentValues.put(DBModel.Q4, record.getQ4());
        contentValues.put(DBModel.Q5, record.getQ5());

        long id = db.insert(DBModel.TABLE_NAME, null, contentValues);
    }

    /**
     * read records from database
     * @return lists contains all records in database
     *
     * */
    public List<Record> selectRecords() {

        List<Record> res = new ArrayList<Record>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                DBModel.ID,
                DBModel.Q1,
                DBModel.Q2,
                DBModel.Q3,
                DBModel.Q4,
                DBModel.Q5
        };

        String sortOrder = DBModel.ID + " DESC";

        Cursor cursor = db.query(
                DBModel.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );
        if(cursor.moveToFirst()) {
            do {
                res.add(
                        new Record(
                                cursor.getString(0),
                                cursor.getInt(1),
                                cursor.getInt(2),
                                cursor.getInt(3),
                                cursor.getInt(4),
                                cursor.getInt(5)
                        )
                );
            } while(cursor.moveToNext());
        }
        return res;
    }
}
