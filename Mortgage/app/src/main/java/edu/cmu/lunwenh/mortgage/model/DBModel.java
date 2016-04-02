package edu.cmu.lunwenh.mortgage.model;

import android.provider.BaseColumns;

/**
 * Created by lunwenh on 3/21/2016.
 */
public class DBModel implements BaseColumns{
    public static final String TABLE_NAME = "records";
    public static final String PURCHASEPRICE = "purchase_price";
    public static final String DOWNPAYMENT = "down_payment";
    public static final String MORTGAGETERM = "mortgage_term";
    public static final String INTERESTRATE = "interest_rate";
    public static final String PROPERTYTAX = "property_tax";
    public static final String PROPERTYINSURANCE = "property_insurance";
    public static final String PMI = "pmi";
    public static final String ZIPCODE = "zip_code";
    public static final String FIRSTPAYMENTDATE = "first_payment_date";
    public static final String PAYOFFDATE = "payoff_date";
    public static final String TOTALPAYMENT = "total_payment";
    public static final String TOTALMONTHLYPAYMENT = "total_monthly_payment";
}
