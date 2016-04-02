package edu.cmu.lunwenh.mortgage.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import edu.cmu.lunwenh.mortgage.R;

public class DisplayResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = this.getIntent();
        String totalPayment = intent.getStringExtra(MainActivity.TOTALPAYMENT);
        String totalMonthlyPayment = intent.getStringExtra(MainActivity.TOTALMONTHLYPAYMENT);
        String payoffDate = intent.getStringExtra(MainActivity.PAYOFFDATE);

        TextView totalPaymentView = (TextView)this.findViewById(R.id.totalPayment);
        totalPaymentView.setText(totalPayment);

        TextView totalMonthlyPaymentView = (TextView)this.findViewById(R.id.totalMonthlyPayment);
        totalMonthlyPaymentView.setText(totalMonthlyPayment);

        TextView payoffDateView = (TextView)this.findViewById(R.id.payoffDate);
        payoffDateView.setText(payoffDate);
    }
}
