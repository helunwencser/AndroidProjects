package edu.cmu.lunwenh.mortgage.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import android.app.AlertDialog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.text.NumberFormat;
import java.text.DecimalFormat;

import edu.cmu.lunwenh.mortgage.R;
import edu.cmu.lunwenh.mortgage.util.DBController;
import edu.cmu.lunwenh.mortgage.exception.InputEmptyException;
import edu.cmu.lunwenh.mortgage.model.Record;

public class MainActivity extends AppCompatActivity {

    public final static String TOTALPAYMENT = "edu.cmu.lunwenh.mortgage.totalPayment";
    public final static String TOTALMONTHLYPAYMENT = "edu.cmu.lunwenh.mortgage.totalMonthlyPayment";
    public final static String PAYOFFDATE = "edu.cmu.lunwenh.mortgage.payoffDate";

    DBController dbController = null;
    String filename = "log.txt";
    FileOutputStream outputStream = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(dbController == null) {
            dbController = new DBController(this);
        }
        File file = new File(this.getFilesDir(), filename);
    }

    public void calculate(View view) {
        try{
            Intent intent = new Intent(this, DisplayResult.class);

            final EditText purchasePriceView = (EditText)this.findViewById(R.id.purchasePrice);
            String purchasePriceString = purchasePriceView.getText().toString();
            if(purchasePriceString == null || purchasePriceString.trim().length() == 0) {
                throw new InputEmptyException("purchase price is empty", 1);
            }
            int purchasePrice = Integer.parseInt(purchasePriceString);

            final EditText downPaymentView = (EditText)this.findViewById(R.id.downPayment);
            String downPaymentString = downPaymentView.getText().toString();
            if(downPaymentString == null || downPaymentString.trim().length() == 0) {
                throw new InputEmptyException("down payment is empty", 2);
            }
            final int downPayment = Integer.parseInt(downPaymentString);

            EditText mortgageTermView = (EditText)this.findViewById(R.id.mortgageTerm);
            String mortgageTermString = mortgageTermView.getText().toString();
            if(mortgageTermString == null || mortgageTermString.trim().length() == 0) {
                throw new InputEmptyException("mortgage term is empty", 3);
            }
            int mortgageTerm = Integer.parseInt(mortgageTermString);

            EditText interestRateView = (EditText)this.findViewById(R.id.interestRate);
            String interestRateString = interestRateView.getText().toString();
            if(interestRateString == null || interestRateString.trim().length() == 0) {
                throw new InputEmptyException("interest rate is empty", 4);
            }
            double interestRate = Double.parseDouble(interestRateString);

            EditText propertyTaxView = (EditText)this.findViewById(R.id.propertyTax);
            String propertyTaxString = propertyTaxView.getText().toString();
            if(propertyTaxString == null || propertyTaxString.trim().length() == 0) {
                throw new InputEmptyException("property tax is empty", 5);
            }
            int propertyTax = Integer.parseInt(propertyTaxString);

            EditText propertyInsuranceView = (EditText)this.findViewById(R.id.propertyInsurance);
            String propertyInsuraceString = propertyInsuranceView.getText().toString();
            if(propertyInsuraceString == null || propertyInsuraceString.length() == 0) {
                throw new InputEmptyException("property insurace is empty", 6);
            }
            int propertyInsurance = Integer.parseInt(propertyInsuraceString);

            EditText pmiView = (EditText)this.findViewById(R.id.pmi);
            double pmi = Double.parseDouble(pmiView.getText().toString());

            EditText zipCodeView = (EditText)this.findViewById(R.id.zipCode);
            String zipCode = zipCodeView.getText().toString();

            Spinner monthSpinner = (Spinner)this.findViewById(R.id.month);
            int month = monthSpinner.getSelectedItemPosition();

            Spinner yearSpinner = (Spinner)this.findViewById(R.id.year);
            int year = Integer.parseInt(String.valueOf(yearSpinner.getSelectedItem()));

            double totalPayment = this.getTotalPayment(purchasePrice, downPayment,
                    mortgageTerm, interestRate, propertyTax, propertyInsurance);

            double totalMonthlyPayment = this.getTotalMonthlyPayment(totalPayment, mortgageTerm);

            Date firstPaymentDate = new Date();
            firstPaymentDate.setYear(year);
            firstPaymentDate.setMonth(month);
            Date payoffDate = this.getPayOffDate(firstPaymentDate, mortgageTerm);
            String firstPaymentDateString = ((firstPaymentDate.getMonth() + 1) >= 10 ? "" : "0") + (firstPaymentDate.getMonth() + 1) + "/" + firstPaymentDate.getYear();
            String payoffDateString = ((payoffDate.getMonth() + 1) >= 10 ? "" : "0") + (payoffDate.getMonth() + 1) + "/" + payoffDate.getYear();

            NumberFormat formatter = new DecimalFormat("#0.00");

            Record record = new Record();
            record.setPurchasePrice(purchasePrice);
            record.setDownPayment(downPayment);
            record.setMortgageTerm(mortgageTerm);
            record.setInterestRate(interestRate);
            record.setPropertyTax(propertyTax);
            record.setPropertyInsurance(propertyInsurance);
            record.setPmi(pmi);
            record.setZipCode(zipCode);
            record.setFirstPaymentDate(firstPaymentDateString);
            record.setPayoffDate(payoffDateString);
            record.setTotalPayment(Double.parseDouble(formatter.format(totalPayment)));
            record.setTotalMonthlyPayment(Double.parseDouble(formatter.format(totalMonthlyPayment)));
            dbController.insertRecord(record);

            intent.putExtra(TOTALPAYMENT, formatter.format(totalPayment));
            intent.putExtra(TOTALMONTHLYPAYMENT, formatter.format(totalMonthlyPayment));
            intent.putExtra(PAYOFFDATE, payoffDateString);
            this.startActivity(intent);
        } catch (InputEmptyException e) {
            try{
                outputStream = this.openFileOutput(filename, Context.MODE_APPEND);
                String logInfo = e.getErrorInfo() + "\n";
                outputStream.write(logInfo.getBytes());
                outputStream.close();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            // set title
            alertDialogBuilder.setTitle("Information missing");

            // set dialog message
            alertDialogBuilder
                    .setMessage(e.getErrorInfo())
                    .setCancelable(false)
                    .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            dialog.cancel();
                        }
                    })
                    .setNegativeButton("No",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            dialog.cancel();
                        }
                    });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();
            return;
        }
    }


        /**
         * get down payment
         * @param price
         *        purchase price
         *
         * @param downPayment
         *        down payment
         *
         * @return  downpayment
         * */
    private int getDownPayment(int price, int downPayment) {
        return price*downPayment/100;
    }
    /**
     * get the total payment
     * @param price
     *        purchase price
     *
     * @param downPayment
     *        down payment
     *
     * @param term
     *        number of years
     *
     * @param interestRate
     *        interest rate
     *
     * @param tax
     *        propery tax
     *
     * @param insurance
     *        propery insurance
     *
     * @return total payment
     * */
    private double getTotalPayment(int price, int downPayment, int term, double interestRate, int tax, int insurance) {
        double totalPayment = (double)price*((100 - (double)downPayment)/100)*Math.pow(1 + interestRate/100, term) + (tax + insurance)*term;
        return totalPayment;
    }

    /**
     * get total monthly payment
     * @param totalPayment
     *        total payment
     *
     * @param term
     *        number of years
     *
     * @return monthly payment
     * */
    private double getTotalMonthlyPayment(double totalPayment, int term) {
        return totalPayment/(term*12);
    }

    /**
     * get pay off date
     * @param startDate
     *        start date
     *
     * @param term
     *        number of years
     *
     * @return pay off date
     * */
    private Date getPayOffDate(Date startDate, int term) {
        Date payoffdate = new Date();
        payoffdate.setYear(startDate.getYear() + term);
        payoffdate.setMonth(startDate.getMonth());
        return payoffdate;
    }
}
