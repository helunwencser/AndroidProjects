package edu.cmu.lunwenh.mortgage.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import edu.cmu.lunwenh.mortgage.model.DBHelper;
import edu.cmu.lunwenh.mortgage.model.DBModel;
import edu.cmu.lunwenh.mortgage.model.Record;

/**
 * Created by lunwenh on 3/22/2016.
 */
public class DBController {
    private DBHelper dbHelper = null;

    /* singleton design pattern */
    public DBController(Context context) {
        if(dbHelper == null) {
            dbHelper = new DBHelper(context);
        }
    }

    /**
     * insert one record to databse
     * @param record
     *        the object contains one record
     * */
    public void insertRecord(Record record) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBModel.PURCHASEPRICE, record.getPurchasePrice());
        values.put(DBModel.DOWNPAYMENT, record.getDownPayment());
        values.put(DBModel.MORTGAGETERM, record.getMortgageTerm());
        values.put(DBModel.INTERESTRATE, record.getInterestRate());
        values.put(DBModel.PROPERTYTAX, record.getPropertyTax());
        values.put(DBModel.PROPERTYINSURANCE, record.getPropertyInsurance());
        values.put(DBModel.PMI, record.getPmi());
        values.put(DBModel.ZIPCODE, record.getZipCode());
        values.put(DBModel.FIRSTPAYMENTDATE, record.getFirstPaymentDate());
        values.put(DBModel.PAYOFFDATE, record.getPayoffDate());
        values.put(DBModel.TOTALPAYMENT, record.getTotalPayment());
        values.put(DBModel.TOTALMONTHLYPAYMENT, record.getTotalMonthlyPayment());

        long id = db.insert(DBModel.TABLE_NAME, null, values);
    }
}
