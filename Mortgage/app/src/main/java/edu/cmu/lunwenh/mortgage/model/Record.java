package edu.cmu.lunwenh.mortgage.model;

/**
 * Created by lunwenh on 3/21/2016.
 */
public class Record {
    private int purchasePrice;
    private int downPayment;
    private int mortgageTerm;
    private double interestRate;
    private int propertyTax;
    private int propertyInsurance;
    private double pmi;
    private String zipCode;
    private String firstPaymentDate;
    private String payoffDate;
    private double totalPayment;
    private double totalMonthlyPayment;

    public Record() {

    }

    public int getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(int purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public int getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(int downPayment) {
        this.downPayment = downPayment;
    }

    public int getMortgageTerm() {
        return mortgageTerm;
    }

    public void setMortgageTerm(int mortgageTerm) {
        this.mortgageTerm = mortgageTerm;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public int getPropertyTax() {
        return propertyTax;
    }

    public void setPropertyTax(int propertyTax) {
        this.propertyTax = propertyTax;
    }

    public int getPropertyInsurance() {
        return propertyInsurance;
    }

    public void setPropertyInsurance(int propertyInsurance) {
        this.propertyInsurance = propertyInsurance;
    }

    public double getPmi() {
        return pmi;
    }

    public void setPmi(double pmi) {
        this.pmi = pmi;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getFirstPaymentDate() {
        return firstPaymentDate;
    }

    public void setFirstPaymentDate(String firstPaymentDate) {
        this.firstPaymentDate = firstPaymentDate;
    }

    public String getPayoffDate() {
        return payoffDate;
    }

    public void setPayoffDate(String payoffDate) {
        this.payoffDate = payoffDate;
    }

    public double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(double totalPayment) {
        this.totalPayment = totalPayment;
    }

    public double getTotalMonthlyPayment() {
        return totalMonthlyPayment;
    }

    public void setTotalMonthlyPayment(double totalMonthlyPayment) {
        this.totalMonthlyPayment = totalMonthlyPayment;
    }
}
